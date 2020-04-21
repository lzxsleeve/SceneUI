package com.sleeve.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.sleeve.ui.base.BaseHeadBarAppScene
import com.sleeve.ui.view.HeadBar

/**
 * 首页
 *
 * Create by Sleeve on 2020/1/14
 */
class MainScene : BaseHeadBarAppScene(), View.OnClickListener {

    override fun initHeadBar(headBar: HeadBar) {
        headBar.setCenterTitle("首页")
    }

    override fun getContentLayout(): Int {
        return R.layout.scene_main
    }

    override fun initView() {
        findViewId<Button>(R.id.home_btn1).setOnClickListener(this)
        findViewId<Button>(R.id.home_btn2).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.home_btn1 -> {
//                start(HeadBarScene::class.java)
                val scene = HeadBarScene()
                val bundle = Bundle()
                bundle.putString("text", "Hello, 你好啊")

                start(scene, bundle)
            }
            R.id.home_btn2 -> {
                start(SoftKeyboardScene())
            }
        }
    }

}
