package chi.learnglide.glide.progress

import okhttp3.Interceptor
import okhttp3.Response

class ProgressInterceptor : Interceptor {

    companion object {

        @JvmStatic
        private val listenerMap = HashMap<String, ProgressListener>()

        fun addListener(url: String, listener: ProgressListener) {
            listenerMap[url] = listener
        }

        fun removeListener(url: String) {
            listenerMap.remove(url)
        }

        fun getListener(url: String): ProgressListener? {
            return listenerMap[url]
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val url = request.url.toString()
        val responseBody = response.body
        val progressResponseBody = if (responseBody == null) {
            null
        } else {
            ProgressResponseBody(url, responseBody)
        }
        return response.newBuilder()
            .body(progressResponseBody)
            .build()
    }
}