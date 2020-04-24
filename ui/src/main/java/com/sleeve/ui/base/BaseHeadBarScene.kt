package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bytedance.scene.ui.template.SwipeBackGroupScene
import com.sleeve.ui.R
import com.sleeve.ui.view.HeadBar
import kotlinx.android.synthetic.main.base_uif_toolbar.view.*

/**
 * 具有头部的 Scene -- 可以右滑关闭
 * @exception SwipeBackGroupScene
 *
 * Create by Sleeve on 2020/1/14
 */
abstract class BaseHeadBarScene : BaseScene() {

    protected var mHeadBar: HeadBar? = null

    override fun onCreateSwipeContentView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): ViewGroup {
        val layoutParent = inflater.inflate(R.layout.base_uif_toolbar, container, false) as ViewGroup // 设置头部
        setToolbar(layoutParent)
        mViewGroup = layoutParent.findViewById(R.id.frame_layout)
        val contentLayout = getContentLayout()
        if (contentLayout != 0) {
            inflater.inflate(contentLayout, mViewGroup, true)
        }
        return layoutParent
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view.status_bar_view.visibility = View.VISIBLE
    }

    /**
     * 设置标题
     */
    protected abstract fun initHeadBar(headBar: HeadBar)

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