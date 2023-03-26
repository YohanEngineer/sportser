package com.sportser.sportser_coach.service.registration

import com.sportser.sportser_coach.service.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationRemoteDataSource(apiRegistration: ApiRegistration): RegistrationDataSource {

    private val services = ApiRegistration.build()
    private var callRegistration: Call<String>? = null

    override fun sendRegistration(callback: OperationCallback<String>, email: String) {
        print("Send registration with " + email)
        callRegistration = services.getRegistration(email)
        callRegistration?.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.body() != null){
                   val responseData: String = response.body()!!
                   print(response.body())
                    callback.onSuccess(responseData)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                print(t)
            }
        })
    }

    override fun deleteRegistration(callback: OperationCallback<String>, email: String) {
        print("Delete registration with " + email)
        callRegistration = services.deleteRegistration(email)
        callRegistration?.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.body() != null){
                    val responseData: String = response.body()!!
                    print(response.body())
                    callback.onSuccess(responseData)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                print(t)
            }
        })
    }
}