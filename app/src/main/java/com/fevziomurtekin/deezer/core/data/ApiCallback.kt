package com.fevziomurtekin.deezer.core.data

import com.fevziomurtekin.deezer.core.extensions.isSuccesAndNotNull
import com.fevziomurtekin.deezer.core.extensions.letOnFalse
import com.fevziomurtekin.deezer.core.extensions.letOnTrue
import java.io.IOException

open class ApiCallback {

    suspend fun <T :Any> networkCall(
        call: suspend () -> ApiResponse<T>
    ) : ApiResult<T?> {
        var networkReturn:ApiResult<T?> = ApiResult.Loading
          try {
            val response = call.invoke()
            response.isSuccesAndNotNull().letOnTrue{
                networkReturn = ApiResult.Success(response.data)
            }.letOnFalse{
                networkReturn = ApiResult.Error(IOException(response.errorMessage))
            }
        } catch (e: Exception) {
            networkReturn = ApiResult.Error(e)
        }
        return networkReturn
    }

    @JvmName("selectData")
    suspend fun <T: Any> localCall(
        call: suspend () -> List<T>?
    ) : DaoResult {
        var localReturn = DaoResult(isSucces = true,null)
        localReturn = try {
            val response = call.invoke()
            if (response != null) {
                DaoResult(true, response)
            } else {
                DaoResult(false, Exception("Unexpected error in Dao"))
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
    suspend fun localCall(
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