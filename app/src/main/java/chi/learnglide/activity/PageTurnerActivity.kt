package chi.learnglide.activity

import android.os.Bundle
import chi.library.base.pageturner.BasePageTurnerActivity
import chi.library.base.pageturner.Page
import chi.library.util.LogUtil

class PageTurnerActivity : BasePageTurnerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.init(true)
    }

    override fun getPageList(): List<Page> =
        listOf(
            Page("基本使用", MainActivity::class.java),
            Page("占位图", MainActivity2::class.java),
            Page("加载 GIF 图", MainActivity3::class.java),
            Page("指定图片大小", MainActivity4::class.java),
            Page("自定义 Target", PageTurnerActivity2::class.java),
            Page("预加载", MainActivity7::class.java),
            Page("仅下载", MainActivity8::class.java),
            Page("监听", MainActivity9::class.java),
            Page("图片变换", MainActivity10::class.java),
            Page("加载进度", MainActivity11::class.java)
        )
}