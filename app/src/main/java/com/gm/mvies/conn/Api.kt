package com.zym.zymresseler.conn

import com.gm.mvies.feature.Genres
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("genre/movie/list?language=en")
    fun getGenres() : Call<Genres>

//    @Headers("Content-Type: application/json")
//    @POST("privateapi/auth/post/getToken")
//    fun getOneMapToken(
//        @Body data:OneMapModel
//    ): Call<OneMapModel>
//
//    @Headers("Content-Type: application/json")
//    @GET("commonapi/search")
//    fun getAddressData(
//        @Query("searchVal") searchVal: String, @Query("returnGeom") returnGeom: String, @Query("getAddrDetails") getAddrDetails: String
//    ): Call<OneMapModel>
//
//
//    @GET("add-on/list")
//    fun addOnList(@Header("Authorization") token: String, @Query("type_id") type_id: String
//    ): Call<ResponseModel>
//
//    @GET("add-on/type-list")
//    fun addOnTypeList(@Header("Authorization") token: String
//    ): Call<ResponseModel>
//
//
//    @GET("user-has-pin/")
//    fun hasPin(@Header("Authorization") token: String
//    ): Call<ResponseModelV1>
//
//    @PUT("set-pin/")
//    fun setPin(@Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//
//
//    @GET("bundle/list/")
//    fun bundleList(@Header("Authorization") token: String
//    ): Call<ResponseModel>
//
//    @GET("version/")
//    fun cekAPP(@Header("Authorization") token: String
//    ): Call<Access>
//
//    @GET("bundle/list/")
//    fun bundleList(@Header("Authorization") token: String, @Query("is_bundle_combo") is_bundle_combo: Int, @Query("serial_number") serial_number : String
//    ): Call<ResponseModel>
//
//    @GET("telecommunication/list")
//    fun getTeico(@Header("Authorization") token: String, @Query("bundle_id") bundle_id : String
//    ): Call<ResponseModelV1>
//
//    @GET("nationality/list")
//    fun getNationality(@Header("Authorization") token: String
//    ): Call<ResponseModel>
//
//    @GET("pass-type/list")
//    fun getPassType(@Header("Authorization") token: String
//    ): Call<ResponseModel>
//
//
//    @GET("account-management/contact/")
//    fun getCustomerRegisterByMe(@Header("Authorization") token: String, @Query("page") page: Int,
//                                @Query("limit") limit: Int, @Query("search") search: String
//    ): Call<ResponseModel>
//
////    @GET("customer/detail/")
////    fun detailCustomer(@Header("Authorization") token: String, @Query("id") id: String?
////    ): Call<ResponseModelV1>
//
//    @GET("customer/detail/{serviceId}")
//    fun detailCustomer(@Header("Authorization") token: String, @Path("serviceId") id: String
//    ): Call<ResponseModelV1>
//
//
//
//    @GET("customer/list/")
//    fun getCustomerList(@Header("Authorization") token: String
//    ): Call<ResponseModel>
//
//    @GET("customer/expired-plan/{id}")
//    fun expiredCustomerPlan(@Header("Authorization") token: String, @Path("id") id: String
//    ): Call<ResponseModelV1>
//
//    @GET("account-management/role-list/")
//    fun roleAccess(@Header("Authorization") token: String
//    ): Call<ResponseModel>
//
//    @GET("account-management/transaction-history/")
//    fun getTransactionHistory(@Header("Authorization") token: String, @Query("type") type: String
//    ): Call<ResponseModel>
//    @GET("account-management/transaction-type-list/")
//    fun getTransactionType(@Header("Authorization") token: String
//    ): Call<ResponseStringModel>
//
//    @GET("account-management/monthly-report/")
//    fun monthlyReport(@Header("Authorization") token: String
//    ): Call<ResponseModel>
//
//    @GET("account-management/user/detail/{id}")
//    fun userDetail(@Header("Authorization") token: String, @Path("id") id: String
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("account-management/add-payment-methods/")
//    fun addCard(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//
//
//
//
//    @GET("msisdn/list/")
//    fun simNumberList(@Header("Authorization") token: String, @Query("is_golden_number") cGolden: Boolean, @Query("limit") limit: Int
//    ): Call<ResponseModel>
//
//    @GET("msisdn/list/")
//    fun luckyNumber(@Header("Authorization") token: String, @Query("is_golden_number") cGolden: Boolean, @Query("limit") limit: Int, @Query("is_lucky_number") is_lucky_number: Boolean, @Query("search") search: String
//    ): Call<ResponseModelV1>
//
//
//
//    @GET("singpass/generate-url/")
//    fun generateUrl(@Header("Authorization") token: String
//    ): Call<ResponseModelV1>
//
//
//    @Headers("Content-Type: application/json")
//    @POST("order/validate-customer")
//    fun checkEmail(
//        @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("plan/check-nric")
//    fun checkNRIC(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseBooleanModel>
//
//    @Headers("Content-Type: application/json")
//    @POST("verify-pin/")
//    fun verifyPIN(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("plan/check-imsi")
//    fun checkIMSI(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("plan/check-credit-eligible")
//    fun checkKredit(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseBooleanModel>
//
//
//    @FormUrlEncoded
//    @POST("login/")
//    fun login(
//        @Field("phone_number") phoneNumber: String?, @Field("otp") otp: String?
//    ): Call<Access>
//
//    @FormUrlEncoded
//    @POST("request-otp/")
//    fun requestOTP(
//        @Field("phone_number") phoneNumber: String?
//    ): Call<ResponseModelV1>
//
//    @Headers("Accept: application/json")
//    @FormUrlEncoded
//    @POST("profile/")
//    fun profile(
//        @Header("Authorization") token: String, @Field("email") email: String?, @Field("password") password: String?
//    ): Call<ResponseModelV1>
//
//    @Multipart
//    @POST("plan/verify-by-id")
//    fun verifyById(@Part("Authorization") token: String,
//                   @Part("full_name") full_name: String?, @Part("nric") nric: String?
//        , @Part("birth_date") birth_date: String?, @Part("nationality_id") nationality_id: String?
//        , @Part("pass_type_id") pass_type_id: String?, @Part("postal_code") postal_code: String?
//        , @Part("building_name") building_name: String?, @Part("street_name") street_name: String?
//        , @Part("floor") floor: String?, @Part("unit") unit: String?, @Part("contact_number") contact_number: String?
//        , @Part front: MultipartBody.Part, @Part back: MultipartBody.Part , @Part("email") email: String?
//    ): Call<ResponseModelV1>
//
//
//    @Headers("Accept: application/json")
//    @Multipart
//    @POST("plan/verify-by-id")
//    fun verifyById(@Header("Authorization") token: String,
//                   @Part("full_name") full_name: RequestBody, @Part("nric") nric: RequestBody
//                   , @Part("birth_date") birth_date: RequestBody, @Part("nationality_id") nationality_id: RequestBody
//                   , @Part("pass_type_id") pass_type_id: RequestBody, @Part("postal_code") postal_code: RequestBody
//                   , @Part("building_name") building_name: RequestBody, @Part("street_name") street_name: RequestBody
//                   , @Part("floor") floor: RequestBody, @Part("unit") unit: RequestBody,
//                   @Part("contact_number") contact_number: RequestBody,@Part("email") email:RequestBody,
//                   @Part front: MultipartBody.Part, @Part back: MultipartBody.Part
//    ): Call<ResponseModelV1>
//
//    @Headers("Accept: application/json")
//    @Multipart
//    @POST("plan/verify-by-id")
//    fun verifyById(@Header("Authorization") token: String, @Part("service_id") service_id: RequestBody,
//                   @Part front: MultipartBody.Part, @Part back: MultipartBody.Part
//    ): Call<ResponseModelV1>
//
//
//    @Headers("Content-Type: application/json")
//    @POST("plan/renew/")
//    fun renewPlan(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("device/check")
//    fun checkDevice(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("plan/buy-add-on/")
//    fun buyAddon(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("add-on/summary/")
//    fun addonSummary(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("account-management/create-user/")
//    fun createUser(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("account-management/create-reseller/")
//    fun createStandartUser(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("account-management/top-up/")
//    fun topUp(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//
//    @Headers("Content-Type: application/json")
//    @POST("singpass/get-info/")
//    fun singpassGetInfo(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//
//
//    @Headers("Content-Type: application/json")
//    @POST("account-management/order/credits-and-sim/")
//    fun orderCreditSimcards(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("plan/summary/")
//    fun orderSummary(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @POST("plan/sign-up/")
//    fun signUp(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//    @Headers("Content-Type: application/json")
//    @POST("plan/new-line/")
//    fun newLine(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
//
//    @Headers("Content-Type: application/json")
//    @GET("braintree/get-token/")
//    fun getToken(
//        @Header("Authorization") token: String
//    ): Call<ResponseModelV1>
//
//
//    @Headers("Content-Type: application/json")
//    @POST("account-management/get-payment-methods/")
//    fun getPayment(
//        @Header("Authorization") token: String, @Body data:RawRequest
//    ): Call<ResponseModelV1>
}