package lk.ac.kln.stu.ps2017050restapiassignment.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import lk.ac.kln.stu.ps2017050restapi.model.Comment
import lk.ac.kln.stu.ps2017050restapi.model.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RetrofitAPI {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPost(@Path("id") id: Int): Call<Post>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Call<List<Comment>>
}

object PostApi {
    val retrofitService: RetrofitAPI by lazy {
        retrofit.create(RetrofitAPI::class.java)
    }
}