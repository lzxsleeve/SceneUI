package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bytedance.scene.ui.template.AppCompatScene
import com.sleeve.ui.R

/**
 * 无头部的 Scene
 * @exception BaseAppScene
 *
 * Create by lzx on 2020/4/21.
 */
abstract class BaseNoHeadAppScene : BaseAppScene() {

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
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

}