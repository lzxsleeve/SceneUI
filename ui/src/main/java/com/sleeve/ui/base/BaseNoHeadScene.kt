package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sleeve.ui.R
import kotlinx.android.synthetic.main.base_uif.view.*

/**
 * 无头部的 Scene
 * @exception BaseScene
 *
 * Create by lzx on 2020/4/22.
 */
abstract class BaseNoHeadScene : BaseScene() {

    override fun onCreateSwipeContentView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): ViewGroup {
        val layoutParent = inflater.inflate(R.layout.base_uif, container, false) as ViewGroup
        // 显示内容的根布局
        mViewGroup = layoutParent.findViewById(R.id.frame_layout)
        // 添加内容布局
        val contentLayout = getContentLayout()
        if (contentLayout != 0) {
            inflater.inflate(contentLayout, mViewGroup, true)
        }
        return layoutParent
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 显示状态栏
        view.status_bar_view.visibility = View.VISIBLE
    }
}