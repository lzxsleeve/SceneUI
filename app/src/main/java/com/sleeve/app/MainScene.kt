package com.sleeve.app

import android.os.Bundle
import android.view.View
import com.sleeve.ui.base.BaseHeadBarAppScene
import com.sleeve.ui.view.HeadBar
import kotlinx.android.synthetic.main.scene_main.view.*

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
        view.home_btn1.setOnClickListener(this)
        view.home_btn2.setOnClickListener(this)
        view.home_btn3.setOnClickListener(this)
        view.home_btn4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.home_btn1 -> {
                start(NoHeadScene())
            }
            R.id.home_btn2 -> {
                val scene = HeadBarScene()
                val bundle = Bundle()
                bundle.putString("text", "Hello, 你好啊")
                scene.setArguments(bundle)
                start(scene)
            }
            R.id.home_btn3 -> {
                start(SoftKeyboardScene())
            }
            R.id.home_btn4 -> {
                start(DialogTestScene())
            }
        }
    }

}
