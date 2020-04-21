package com.sleeve.app

import android.widget.TextView
import com.sleeve.ui.base.BaseHeadBarAppScene
import com.sleeve.ui.view.HeadBar

/**
 * 说明
 *
 *
 * Create by Sleeve on 2020/1/14
 */
class HeadBarScene : BaseHeadBarAppScene() {
    private var textView: TextView? = null
    override fun initHeadBar(headBar: HeadBar) {
        headBar.setTitle("标题")
    }

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        if (arguments != null) {
            val str = arguments!!.getString("text")
            textView = findViewId<TextView>(R.id.main_text)
            textView!!.text = str
        }
    }
}