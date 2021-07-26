package com.mcl.kotlin_mvvm.http

import com.ggh.blinddate.model.http.HttpHeaderInterceptor
import com.ggh.librarycommmon.constant.AppConstant
import com.ggh.librarycommmon.http.HttpTokenInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 公司：江苏刚刚好网络科技有限公司
 * 作者：Android 土三七
 * 文件名：HttpClient
 * 创建时间：2020/6/27
 * 功能描述：  Retrofit工厂，单例
 */

class RetrofitFactory private constructor() {

    /*
     *单例实现
     */
    companion object {
        private const val TAG = "LogUtil-->"
        private const val url = AppConstant.APP_HOST
        const val CONTENT_TYPE = "Content-Type"
        const val JSON_TYPE = "application/json"
        const val CHARSET = "charset"
        const val UTF_ENCODE = "UTF-8"
        //响应超时时间
        const val TIME_OUT = 60 * 60L
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit

    //初始化
    init {
        //通用拦截
        interceptor = Interceptor { chain ->
            val request: Request = chain.request()
                .newBuilder()
                .addHeader(CONTENT_TYPE, JSON_TYPE)
                .addHeader(CHARSET, UTF_ENCODE)
                .build()
            chain.proceed(request)
        }


        //Retrofit实例化
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            //.addConverterFactory(GsonConverterFactory.create()) //设置数据格式化工具
            .client(initClient())
            .build()
    }

    /*
        OKHttp创建
     */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpTokenInterceptor())
            .addInterceptor(interceptor)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }


    /*
        具体服务实例化
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}