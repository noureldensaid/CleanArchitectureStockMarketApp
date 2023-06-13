package com.example.stockmarket.explore_stokes_feature.data.repository

import com.example.stockmarket.explore_stokes_feature.data.csv.CSVParser
import com.example.stockmarket.explore_stokes_feature.data.local.StockDatabase
import com.example.stockmarket.explore_stokes_feature.data.mapper.toCompanyListing
import com.example.stockmarket.explore_stokes_feature.data.mapper.toCompanyListingEntity
import com.example.stockmarket.explore_stokes_feature.data.remote.StockApi
import com.example.stockmarket.explore_stokes_feature.domain.model.CompanyListing
import com.example.stockmarket.explore_stokes_feature.domain.repository.Repository
import com.example.stockmarket.explore_stokes_feature.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val db: StockDatabase,
    private val csvParser: CSVParser<CompanyListing>
) : Repository {

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<ResponseState<List<CompanyListing>>> {
        return flow {
            emit(ResponseState.Loading(true))

            val localListings = db.dao.searchCompanyListing(query)
            emit(ResponseState.Success(data = localListings.map { it.toCompanyListing() }))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val loadFromCache = !fetchFromRemote && !isDbEmpty
            if (loadFromCache) {
                emit(ResponseState.Loading(false))
                return@flow
            }

            val remoteListing = try {
                val response = api.getListings()
                // get parsed csv response
                csvParser.parse(response.byteStream())

            } catch (e: IOException) {
                e.printStackTrace()
                emit(ResponseState.Error("Couldn't load data..!"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ResponseState.Error("Couldn't load data..!"))
                null
            }

            // single source of truth as data always comes from database
            remoteListing?.let { newListings ->
                val dao = db.dao

                dao.clearCompanyListing()
                dao.insertCompanyListing(newListings.map { it.toCompanyListingEntity() })

                emit(ResponseState.Success(
                    data = dao.searchCompanyListing("").map { it.toCompanyListing() }
                ))
                emit(ResponseState.Loading(false))

            }

        }
    }
}