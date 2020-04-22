package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bytedance.scene.ui.template.AppCompatScene
import com.sleeve.ui.R
import com.sleeve.ui.view.HeadBar

/**
 * 具有头部的 Scene
 * @exception AppCompatScene
 *
 * Create by lzx on 2020/4/21.
 */
abstract class BaseHeadBarAppScene : BaseAppScene() {

    protected var mHeadBar: HeadBar? = null

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        val layoutParent = inflater.inflate(R.layout.base_uif_toolbar, container, false) as ViewGroup // 设置头部
        setToolbar(layoutParent)
        mViewGroup = layoutParent.findViewById(R.id.frame_layout)
        val contentLayout = getContentLayout()
        if (contentLayout != 0) {
            inflater.inflate(contentLayout, mViewGroup, true)
        }
        return layoutParent
    }

    private fun setToolbar(layoutParent: View) {
        mHeadBar = layoutParent.findViewById(R.id.head_bar)
        initHeadBar(mHeadBar!!)
        mHeadBar?.setOnBackClick { view: View? -> onToolbarBackClick(view) }
    }

    /**
     * 返回键的点击监听，如需实现特殊需求重写此方法即可
     */
    protected fun onToolbarBackClick(view: View?) {
        pop()
    }
}