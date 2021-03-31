package chi.learnglide.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnglide.R
import chi.learnglide.base.IMAGE_URL
import chi.learnglide.glide.progress.ProgressInterceptor
import chi.learnglide.glide.progress.ProgressListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main10.imageView
import kotlinx.android.synthetic.main.activity_main11.*

class MainActivity11 : AppCompatActivity(), ProgressListener {

    companion object {
        private const val TAG = "MainActivity11"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main11)
    }

    fun onClick(view: View) {
        ProgressInterceptor.addListener(IMAGE_URL, this)
        Glide.with(this)
            .load(IMAGE_URL)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .into(object : GlideDrawableImageViewTarget(imageView) {
                override fun onLoadStarted(placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    Log.i(TAG, "onLoadStarted")
                    progressBar.progress = 0
                    progressBar.visibility = View.VISIBLE
                }

                override fun onResourceReady(
                    resource: GlideDrawable?,
                    animation: GlideAnimation<in GlideDrawable>?
                ) {
                    super.onResourceReady(resource, animation)
                    Log.i(TAG, "onResourceReady")
                    progressBar.visibility = View.GONE
                    ProgressInterceptor.removeListener(IMAGE_URL)
                }

                override fun onLoadFailed(e: Exception?, errorDrawable: Drawable?) {
                    super.onLoadFailed(e, errorDrawable)
                    Log.i(TAG, "onLoadFailed")
                    progressBar.visibility = View.GONE
                    ProgressInterceptor.removeListener(IMAGE_URL)
                }
            })
    }

    override fun onProgress(progress: Int) {
        runOnUiThread {
            progressBar.progress = progress
        }
    }
}