package chi.learnglide.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnglide.R
import chi.learnglide.base.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        Glide.with(this)
            .load(IMAGE_URL)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .placeholder(R.drawable.ic_load_image_placeholder)
            .error(R.drawable.ic_load_image_failure)
            .into(imageView)
    }
}