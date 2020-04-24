package com.sleeve.app

import com.sleeve.ui.base.BaseHeadBarAppScene
import com.sleeve.ui.view.HeadBar

/**
 * 说明
 *
 * Create by lzx on 2020/4/21.
 */
class SoftKeyboardScene : BaseHeadBarAppScene() {
    override fun initHeadBar(headBar: HeadBar) {
        headBar.setTitle("输入法相关")
    }

    override fun getContentLayout(): Int {
        return R.layout.scene_keyboard
    }

    override fun initView() {
        setSwipeEnabled(true)
    }
}