package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bytedance.scene.ui.template.AppCompatScene
import com.sleeve.ui.R

/**
 * 无头部的 Scene
 * @exception AppCompatScene
 *
 * Create by lzx on 2020/4/21.
 */
abstract class BaseNoHeadAppScene : BaseAppScene() {

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        // 显示内容的根布局
        mViewGroup = inflater.inflate(R.layout.base_uif, container, false) as FrameLayout
        // 添加内容布局
        val contentLayout = getContentLayout()
        if (contentLayout != 0) {
            inflater.inflate(contentLayout, mViewGroup, true)
        }

        // 需要支持SwipeBack则这里必须调用toSwipeBackFragment(view);
        return mViewGroup
    }

}