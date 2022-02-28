package com.elsawy.edvora.data.network

import com.elsawy.edvora.data.model.Ride
import com.elsawy.edvora.data.model.User
import retrofit2.http.GET

interface ApiService {
   @GET("/user")
   suspend fun getUser(): User

   @GET("/rides")
   suspend fun getRides(): List<Ride>
}