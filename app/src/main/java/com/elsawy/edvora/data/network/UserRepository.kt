package com.elsawy.edvora.data.network

import com.elsawy.edvora.data.Resource
import com.elsawy.edvora.data.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
   private val apiService: ApiService,
) {

   suspend fun getUserData(): Resource<User> {
      return try {
         val user = apiService.getUser()//.await()
         Resource.success(user)
      } catch (e: Exception) {
         Resource.error("there's an Exception in user Api", null)
      }
   }

}