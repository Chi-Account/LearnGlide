package chi.learnglide.base

import android.content.Context
import chi.learnglide.glide.progress.ProgressInterceptor
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.GlideModule
import okhttp3.OkHttpClient
import java.io.InputStream

class AppGlideModule : GlideModule {

    companion object {
        private const val TAG = "AppGlideModule"
        const val DISK_CACHE_SIZE = 500 * 1024 * 1024
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(ExternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE))
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888)
    }

    override fun registerComponents(context: Context, glide: Glide) {
        val client = OkHttpClient.Builder()
            .addInterceptor(ProgressInterceptor())
            .build()
        glide.register(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(client)
        )
    }
}