package com.fevziomurtekin.deezer.core.data

import com.fevziomurtekin.deezer.core.extensions.isNotNull
import com.fevziomurtekin.deezer.core.extensions.isSuccesAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalse
import com.fevziomurtekin.deezer.core.extensions.letOnTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.io.IOException

open class ApiCallback {

    suspend fun <T :Any> networkCall(
        call: suspend () -> ApiResponse<T>
    ) : ApiResult<T?> {
        Timber.d("networkCall : init")
        var networkReturn:ApiResult<T?> = ApiResult.Loading
        try {
            val response = call.invoke()
            Timber.d("networkCall : $response")
            response.isSuccesAndNotNull().letOnTrue{
                networkReturn = ApiResult.Success(response.data)
            }.letOnFalse{
                networkReturn = ApiResult.Error(IOException(response.errorMessage))
            }
        } catch (e: Exception) {
            Timber.d("networkCall - error : ${e.message}")
            networkReturn = ApiResult.Error(e)
        }
        Timber.d("networkCall : ${Json.encodeToString(networkReturn)}}")
        return networkReturn
    }

    @JvmName("selectData")
    suspend fun <T: Any> localCallFetch(
        call: suspend () -> List<T>?
    ) : DaoResult {
        var localReturn = DaoResult(isSucces = true,null)
        try {
            val response = call.invoke()
            Timber.d("localCallFetch : $response")
             response.isNullOrEmpty().letOnFalse {
               localReturn = DaoResult(true, response)
            }.letOnTrue {
                localReturn = DaoResult(false, Exception("Unexpected error in Dao"))
            }
        }catch (e:Exception){
            DaoResult(false,e)
        }
        return localReturn
    }

    /**
     * insert to data in Dao.
     */
    @JvmName("insertData")
    suspend fun localCallInsert(
        call: suspend () -> Unit
    ) : DaoResult {
        var localReturn = DaoResult(true,null)
        localReturn = try {
            call.invoke()
            DaoResult(true, "Data addition has been done succesfully...")
        }catch (e:Exception){
            DaoResult(false,e)
        }
        return localReturn
    }

}