package com.sdimosikvip.data.sources.remote

import com.sdimosikvip.common.exceptions.ClientException
import com.sdimosikvip.common.exceptions.NoConnectionException
import com.sdimosikvip.common.exceptions.ServerException
import com.sdimosikvip.common.exceptions.UncheckedException
import com.sdimosikvip.common.mapper.BaseUnidirectionalMapper
import com.sdimosikvip.common.model.Outcome
import com.sdimosikvip.data.network.ConnectionManager
import retrofit2.Response

abstract class BaseRemoteSource {

    protected suspend fun <R, D> getResult(
        connectionManager: ConnectionManager,
        mapper: BaseUnidirectionalMapper<R, D>,
        call: suspend () -> Response<R>,
    ): Outcome<D> {
        try {
            val isConnected = connectionManager.isConnected()

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    return Outcome.Success(mapper.transform(body))
                }
            }

            if (!isConnected) {
                return Outcome.Failure(NoConnectionException())
            }

            return when (response.code()) {
                in 400..499 -> Outcome.Failure(ClientException())
                in 500..599 -> Outcome.Failure(ServerException())
                else -> Outcome.Failure(UncheckedException())
            }
        } catch (e: Exception) {
            return Outcome.Failure(e)
        }
    }

    protected suspend fun <R> getResult(
        connectionManager: ConnectionManager,
        call: suspend () -> Response<R>,
    ): Outcome<R> {
        try {
            val isConnected = connectionManager.isConnected()

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    return Outcome.Success(body)
                }
            }

            if (!isConnected) {
                return Outcome.Failure(NoConnectionException())
            }

            return when (response.code()) {
                in 400..499 -> Outcome.Failure(ClientException())
                in 500..599 -> Outcome.Failure(ServerException())
                else -> Outcome.Failure(UncheckedException())
            }
        } catch (e: Exception) {
            return Outcome.Failure(e)
        }
    }
}