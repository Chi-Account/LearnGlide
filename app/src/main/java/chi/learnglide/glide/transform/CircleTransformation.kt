package chi.learnglide.glide.transform

import android.content.Context
import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import kotlin.math.min

class CircleTransformation : BitmapTransformation {

    constructor(context: Context) : super(context)

    constructor(bitmapPool: BitmapPool) : super(bitmapPool)

    override fun getId(): String = "chi.learnglide.glide.transform.BitmapTransformation"

    override fun transform(
        pool: BitmapPool?,
        toTransform: Bitmap?,
        outWidth: Int,
        outHeight: Int
    ): Bitmap? {
        if (toTransform == null) return null

        val config = if (toTransform.config != null) {
            toTransform.config
        } else {
            Bitmap.Config.ARGB_8888
        }
        val toReuse: Bitmap? = pool?.get(outWidth, outHeight, config)
        val diameter = min(toTransform.width, toTransform.height)
        val transformed: Bitmap = toReuse ?: Bitmap.createBitmap(diameter, diameter, config)

        val canvas = Canvas(transformed)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val bitmapShader = BitmapShader(
            toTransform,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        val dx = (toTransform.width - diameter) / 2
        val dy = (toTransform.height - diameter) / 2
        if (dx != 0 || dy != 0) {
            val matrix = Matrix()
            matrix.setTranslate(-dx.toFloat(), -dy.toFloat())
            bitmapShader.setLocalMatrix(matrix)
        }
        paint.shader = bitmapShader
        val radius = diameter / 2.0F
        canvas.drawCircle(radius, radius, radius, paint)

        if (toReuse != null && toReuse != transformed && !pool.put(toReuse)) {
            toReuse.recycle()
        }
        return transformed
    }
}