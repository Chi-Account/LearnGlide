package chi.learnglide.glide.target

import android.view.View
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.ViewTarget

class BackgroundViewTarget(view: View) : ViewTarget<View, GlideDrawable>(view) {

    override fun onResourceReady(
        resource: GlideDrawable?,
        glideAnimation: GlideAnimation<in GlideDrawable>?
    ) {
        view.background = resource
    }
}