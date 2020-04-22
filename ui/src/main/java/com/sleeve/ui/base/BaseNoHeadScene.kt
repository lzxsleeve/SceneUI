package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.bytedance.scene.ui.template.SwipeBackGroupScene
import com.sleeve.ui.R

/**
 * 无头部的 Scene
 * @exception SwipeBackGroupScene
 *
 * Create by lzx on 2020/4/22.
 */
abstract class BaseNoHeadScene : BaseScene() {

    override fun onCreateSwipeContentView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): ViewGroup {
        // 显示内容的根布局
        mViewGroup = inflater.inflate(R.layout.base_uif, container, false) as FrameLayout
        // 添加内容布局
        val contentLayout = getContentLayout()
        if (contentLayout != 0) {
            inflater.inflate(contentLayout, mViewGroup, true)
        }

        // 需要支持SwipeBack则这里必须调用toSwipeBackFragment(view);
        return mViewGroup!!
    }
}