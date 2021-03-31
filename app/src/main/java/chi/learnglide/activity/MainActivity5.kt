package chi.learnglide.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import chi.learnglide.R
import chi.learnglide.base.IMAGE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val simpleTarget = object : SimpleTarget<GlideDrawable>() {
            override fun onResourceReady(
                resource: GlideDrawable?,
                glideAnimation: GlideAnimation<in GlideDrawable>?
            ) {
                imageView.setImageDrawable(resource)
            }
        }
        Glide.with(this)
            .load(IMAGE_URL)
            .into(simpleTarget)

        /*val simpleTarget2 = object : SimpleTarget<Bitmap>() {
            override fun onResourceReady(
                resource: Bitmap?,
                glideAnimation: GlideAnimation<in Bitmap>?
            ) {
                imageView.setImageBitmap(resource)
            }
        }
        Glide.with(this)
            .load(IMAGE_URL)
            .asBitmap()
            .into(simpleTarget2)*/
    }
}