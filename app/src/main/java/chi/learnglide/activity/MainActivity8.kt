package chi.learnglide.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnglide.R
import chi.learnglide.base.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import kotlin.concurrent.thread

class MainActivity8 : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity8"
    }

    private var downloaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        if (!downloaded) {
            downloadImage2()
        } else {
            showImage()
        }
    }

    private fun downloadImage() {
        thread {
            val target: FutureTarget<File> = Glide.with(applicationContext)
                .load(IMAGE_URL)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            val file: File = target.get()
            Log.i(TAG, "File: ${file.absolutePath}")
            downloaded = true
        }
    }

    private fun downloadImage2() {
        Glide.with(applicationContext)
            .load(IMAGE_URL)
            .downloadOnly(object : SimpleTarget<File>() {
                override fun onResourceReady(
                    resource: File?,
                    glideAnimation: GlideAnimation<in File>?
                ) {
                    resource?.run {
                        Log.i(TAG, "File: $absolutePath")
                        downloaded = true
                    }
                }
            })
    }

    private fun showImage() {
        Glide.with(this)
            .load(IMAGE_URL)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(imageView)
    }
}