package chi.learnglide.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnglide.R
import chi.learnglide.base.IMAGE_URL
import chi.learnglide.glide.transform.CircleTransformation
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.activity_main10.*

class MainActivity10 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main10)
    }

    fun onClick(view: View) {
        Glide.with(this)
            .load(IMAGE_URL)
            .bitmapTransform(
                BlurTransformation(this),
                CircleTransformation(this),
                GrayscaleTransformation(this)
            )
            .into(imageView)
    }
}