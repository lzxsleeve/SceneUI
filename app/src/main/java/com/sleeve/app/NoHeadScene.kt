package com.sleeve.app

import com.sleeve.ui.base.BaseNoHeadAppScene
import com.sleeve.ui.base.BaseNoHeadScene
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * 说明
 *
 * Create by lzx on 2020/4/24 0024.
 */
class NoHeadScene : BaseNoHeadAppScene() {

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setSwipeEnabled(true)
        view.main_text.text = "可以右滑关闭"
    }
}