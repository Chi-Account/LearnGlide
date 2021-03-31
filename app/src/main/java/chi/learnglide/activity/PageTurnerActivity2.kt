package chi.learnglide.activity

import android.os.Bundle
import chi.library.base.pageturner.BasePageTurnerActivity
import chi.library.base.pageturner.Page
import chi.library.util.LogUtil

class PageTurnerActivity2 : BasePageTurnerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.init(true)
    }

    override fun getPageList(): List<Page> =
        listOf(
            Page("SimpleTarget", MainActivity5::class.java),
            Page("ViewTarget", MainActivity6::class.java)
        )
}