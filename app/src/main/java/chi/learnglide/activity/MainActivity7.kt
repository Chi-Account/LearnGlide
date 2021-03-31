package chi.learnglide.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnglide.R
import chi.learnglide.base.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity7 : AppCompatActivity() {

    private var loaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        if (!loaded) {
            loadImage()
            loaded = true
        } else {
            showImage()
        }
    }

    private fun loadImage() {
        Glide.with(this)
            .load(IMAGE_URL)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .preload()
    }

    private fun showImage() {
        Glide.with(this)
            .load(IMAGE_URL)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(imageView)
    }
}