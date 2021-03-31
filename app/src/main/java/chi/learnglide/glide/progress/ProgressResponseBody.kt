package chi.learnglide.glide.progress

import android.util.Log
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*

class ProgressResponseBody(
    url: String,
    private val responseBody: ResponseBody
) : ResponseBody() {

    companion object {
        private const val TAG = "ProgressResponseBody"
    }

    private lateinit var bufferedSource: BufferedSource

    private var listener: ProgressListener? = ProgressInterceptor.getListener(url)

    override fun contentLength(): Long = responseBody.contentLength()

    override fun contentType(): MediaType? = responseBody.contentType()

    override fun source(): BufferedSource {
        if (!::bufferedSource.isInitialized) {
            bufferedSource = ProgressSource(responseBody.source()).buffer()
        }
        return bufferedSource
    }

    inner class ProgressSource(source: Source) : ForwardingSource(source) {

        private var totalBytesRead: Long = 0

        private var currentProgress: Int = -1

        override fun read(sink: Buffer, byteCount: Long): Long {
            val bytesRead: Long = super.read(sink, byteCount)
            val totalBytes: Long = responseBody.contentLength()
            if (bytesRead == -1L) {
                totalBytesRead = totalBytes
            } else {
                totalBytesRead += bytesRead
            }

            val progress: Int = (100F * totalBytesRead / totalBytes).toInt()
            listener?.let {
                if (progress != currentProgress) {
                    it.onProgress(progress)
                    Log.i(TAG, "Progress: $progress")
                }
                if (totalBytesRead == totalBytes) {
                    listener = null
                }
            }
            currentProgress = progress
            return bytesRead
        }
    }
}