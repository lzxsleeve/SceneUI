package com.sleeve.app.elementdemo

import android.os.Build
import android.os.Bundle
import android.util.ArrayMap
import android.view.View
import com.bytedance.scene.animation.SharedElementSceneTransitionExecutor
import com.bytedance.scene.animation.interaction.scenetransition.AutoSceneTransition
import com.bytedance.scene.animation.interaction.scenetransition.SceneTransition
import com.bytedance.scene.animation.interaction.scenetransition.visiblity.Slide
import com.bytedance.scene.interfaces.PushOptions
import com.sleeve.app.R
import com.sleeve.ui.base.BaseHeadBarScene
import com.sleeve.ui.view.HeadBar
import kotlinx.android.synthetic.main.scene_element_demo.view.*

/**
 * 说明
 *
 * Create by lzx on 2020/4/26.
 */
class ElementDemoScene : BaseHeadBarScene(), View.OnClickListener {
    override fun initHeadBar(headBar: HeadBar) {
        headBar.setTitle("共享元素Demo")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            headBar.transitionName = "headbar"
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.scene_element_demo
    }

    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mStatusBar?.transitionName = "bar_view"
        }
        view.img_1.setOnClickListener(this)
        view.img_2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val bundle = Bundle()
        val map: ArrayMap<String, SceneTransition> = ArrayMap()
        map["headbar"] = AutoSceneTransition()
        map["bar_view"] = AutoSceneTransition()
        when (v?.id) {
            R.id.img_1 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    v.transitionName = "img_1"
                    map["img_1"] = AutoSceneTransition()
                }
                bundle.putInt("imgRes", R.mipmap.img_element_demo_1)
                bundle.putString("name", "img_1")
            }
            R.id.img_2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    v.transitionName = "img_2"
                    map["img_2"] = AutoSceneTransition()
                }
                bundle.putInt("imgRes", R.mipmap.img_element_demo_2)
                bundle.putString("name", "img_2")
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transitionExecutor = SharedElementSceneTransitionExecutor(map, Slide())
            val options = PushOptions.Builder().setAnimation(transitionExecutor).build()
            start(ElementImageScene::class.java, bundle, options)
        } else {
            start(ElementImageScene::class.java, bundle)
        }
    }
}