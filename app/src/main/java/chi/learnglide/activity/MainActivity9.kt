package chi.learnglide.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnglide.R
import chi.learnglide.base.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target

class MainActivity9 : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity9"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        Glide.with(applicationContext)
            .load(IMAGE_URL)
            .listener(object : RequestListener<String?, GlideDrawable?> {
                override fun onException(
                    e: Exception?,
                    model: String?,
                    target: Target<GlideDrawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.i(TAG, "listener onException: ${e?.message}")
                    return false
                }

                override fun onResourceReady(
                    resource: GlideDrawable?,
                    model: String?,
                    target: Target<GlideDrawable?>?,
                    isFromMemoryCache: Boolean,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.i(TAG, "listener onResourceReady")
                    return true
                }
            })
            .into(object : SimpleTarget<GlideDrawable>() {
                override fun onResourceReady(
                    resource: GlideDrawable?,
                    glideAnimation: GlideAnimation<in GlideDrawable>?
                ) {
                    Log.i(TAG, "into onResourceReady")
                }
            })
    }
}