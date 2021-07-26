package com.ggh.blinddate.model.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.net.URLDecoder
import java.nio.charset.Charset
import kotlin.jvm.Throws

/**
 * 创建时间：2020/7/14
 * 功能描述： 带有请求头的拦截器
 */
class HttpHeaderInterceptor : Interceptor {
    companion object {
        private const val TAG = "Retrofit"
        private const val isDeBug = true
        val instance: HttpHeaderInterceptor by lazy { HttpHeaderInterceptor() }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response { //  配置请求头
        val accessToken = "token"
        val tokenType = "tokenType"
        //        String token = AppConfig.getInstance().getToken();
        var request = chain.request()

        request.newBuilder()
//                .header(accessToken, TextUtils.isEmpty(token) ? "1" : token)
//                .header("Authorization", tokenType + " " + accessToken)
            .header("server", "nginx/1.15.11")
            .header("Content-Type", "application/json; charset=utf-8")
            .addHeader("Connection", "close")
            .addHeader("Accept-Encoding", "chunked")
            .build()
        val response = chain.proceed(request)
        if (isDeBug) {
            Log.i(
                TAG,
                String.format(
                    "LogUtil---->Retrofit\n请求链接：%s\n请求token：%s\n请求参数：%s\n请求响应:%s",
                    response.toString(),
                    request.headers()[accessToken],
                    getRequestInfo(request),
                    getResponseInfo(response)
                )
            )
        }
        return response
    }

    /**
     * 打印请求消息
     *
     * @param request 请求的对象
     */
    private fun getRequestInfo(request: Request?): String {
        var str = ""
        if (request == null) {
            return str
        }
        val requestBody = request.body() ?: return str
        try {
            val buffer = Buffer()
            requestBody.writeTo(buffer)
            //将返回的数据URLDecoder解码
            str = buffer.readUtf8()
            str = str.replace("%(?![0-9a-fA-F]{2})".toRegex(), "%25")
            str = URLDecoder.decode(str, "utf-8")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return str
    }

    /**
     * 打印返回消息
     *
     * @param response 返回的对象
     */
    private fun getResponseInfo(response: Response?): String {
        var str = ""
        if (response == null || !response.isSuccessful) {
            return str
        }
        val responseBody = response.body()
        val contentLength = responseBody!!.contentLength()
        val source = responseBody.source()
        try {
            source.request(Long.MAX_VALUE) // Buffer the entire body.
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val buffer = source.buffer()
        val charset = Charset.forName("utf-8")
        if (contentLength != 0L) {
            str = buffer.clone().readString(charset)
        }
        return str
    }
}