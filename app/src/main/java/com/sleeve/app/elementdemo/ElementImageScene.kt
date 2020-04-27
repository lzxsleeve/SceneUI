package com.sleeve.app.elementdemo

import android.os.Build
import com.bytedance.scene.animation.animatorexecutor.AlphaNavigationSceneAnimatorExecutor
import com.bytedance.scene.animation.animatorexecutor.Android8DefaultSceneAnimatorExecutor
import com.bytedance.scene.animation.animatorexecutor.NoAnimationExecutor
import com.sleeve.app.R
import com.sleeve.ui.base.BaseHeadBarScene
import com.sleeve.ui.view.HeadBar
import kotlinx.android.synthetic.main.scene_element_image.view.*

/**
 * 说明
 *
 * Create by lzx on 2020/4/27.
 */
class ElementImageScene : BaseHeadBarScene() {
    override fun initHeadBar(headBar: HeadBar) {
        headBar.setTitle("查看大图")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headBar.transitionName = "headbar"
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.scene_element_image
    }

    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mStatusBar?.transitionName = "bar_view"
        }
        arguments?.let {
            val imgRes = it.getInt("imgRes")
            view.image_detail.setImageResource(imgRes)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val name = it.getString("name")
                view.image_detail.transitionName = name
            }
        }
    }
}