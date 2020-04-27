package com.sleeve.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bytedance.scene.navigation.OnBackPressedListener
import com.sleeve.app.elementdemo.ElementDemoScene
import com.sleeve.ui.base.BaseHeadBarAppScene
import com.sleeve.ui.view.HeadBar
import kotlinx.android.synthetic.main.scene_main.view.*
import java.util.concurrent.TimeUnit

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

        // 拦截返回键
        requireNavigationScene().addOnBackPressedListener(this, object : OnBackPressedListener {

            private var time: Long = 0

            override fun onBackPressed(): Boolean {
                if (time == 0L || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - time) > 2) {
                    Toast.makeText(requireSceneContext(), "再次点击退出应用", Toast.LENGTH_SHORT).show()
                    time = System.currentTimeMillis()
                    return true
                }
                return false
            }
        })

        view.home_btn1.setOnClickListener(this)
        view.home_btn2.setOnClickListener(this)
        view.home_btn3.setOnClickListener(this)
        view.home_btn4.setOnClickListener(this)
        view.home_btn5.setOnClickListener(this)
        view.home_btn6.setOnClickListener(this)
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
            R.id.home_btn5 -> {
                // ReuseGroupScene需要通过class跳转，否则不会存储
                start(ReuseDemoScene::class.java)
            }
            R.id.home_btn6 -> {
                start(ElementDemoScene())
            }
        }
    }

}
