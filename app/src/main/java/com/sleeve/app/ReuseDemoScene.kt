package com.sleeve.app

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bytedance.scene.group.ReuseGroupScene
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * 会被存储的Scene
 * ps:在关闭时会存储此Scene，修改过的UI也会被保存，重新启动时不会初始化，关闭时onDestroyView是有被调用的
 * pss:最多存储3个ReuseGroupScene,无法右滑关闭
 *
 * Create by lzx on 2020/4/26.
 */
class ReuseDemoScene : ReuseGroupScene() {
    private var clickTime = 0

    override fun onCreateNewView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): ViewGroup {
        SystemClock.sleep(1000)
        val layout = FrameLayout(requireSceneContext())
        val inflate = inflater.inflate(R.layout.activity_main, layout)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.main_button.visibility = View.VISIBLE
        view.main_button.setOnClickListener {
            clickTime++
            view.main_text.text = "Click Time : $clickTime"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("ReuseDemoScene", "555，我被关闭了")
    }
}