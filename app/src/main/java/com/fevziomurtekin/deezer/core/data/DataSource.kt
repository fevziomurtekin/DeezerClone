package com.fevziomurtekin.deezer.core.data

import com.fevziomurtekin.deezer.core.extensions.isSuccessAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalse
import com.fevziomurtekin.deezer.core.extensions.letOnTrue
import retrofit2.Response
import java.io.IOException

open class DataSource {

    suspend fun <T :Any> networkCall(
        call: suspend () -> Response<T>
    ) : ApiResult<T?> {
        var networkReturn:ApiResult<T?> = ApiResult.Loading
        try {
            val response = call.invoke()
            response.isSuccessAndNotNull().letOnTrue{
                networkReturn = ApiResult.Success(response.body())
            }.letOnFalse{
                networkReturn = ApiResult.Error(IOException(response.errorBody()?.string().orEmpty()))
            }
        } catch (e: IllegalArgumentException) {
            networkReturn = ApiResult.Error(e)
        }
        return networkReturn
    }

    @JvmName("selectData")
    suspend fun <T: Any> localCallFetch(
        call: suspend () -> List<T>?
    ) : DaoResult {
        var localReturn = DaoResult(isSucces = true,null)
        try {
            val response = call.invoke()
             response.isNullOrEmpty().letOnFalse {
               localReturn = DaoResult(true, response)
            }.letOnTrue {
                localReturn = DaoResult(false, Exception("Unexpected error in Dao"))
            }
        }catch (e:IllegalArgumentException){
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
        val localReturn: DaoResult
        localReturn = try {
            call.invoke()
            DaoResult(true, "Data addition has been done succesfully...")
        }catch (e:IllegalArgumentException){
            DaoResult(false,e)
        }
        return localReturn
    }
}
