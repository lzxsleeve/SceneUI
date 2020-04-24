package com.sleeve.ui.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.annotation.IdRes
import com.bytedance.scene.Scene
import com.sleeve.ui.R

/**
 * 弹窗基类
 * 优点：轻量、没有FragmentDialog的内存泄漏，在没有特殊需求时推荐使用
 * 缺点：动画自定义不方便,弹窗与输入法模式会有兼容问题,需要在根布局设置android:fitsSystemWindows="true"
 *
 * Create by lzx on 2020/4/23.
 */
abstract class BaseDialogScene : Scene() {

    /**
     * 显示内容的根布局
     */
    protected var mViewGroup: View? = null
    protected var mParentView: FrameLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        // mParentView = FrameLayout(requireSceneContext())
        // ScrollView可以修复布局超出时上面部分被截取的问题
        mParentView = inflater.inflate(R.layout.scroll_view, null) as ScrollView

        mViewGroup = inflater.inflate(getContentLayout(), null)
        mParentView!!.addView(mViewGroup!!)
        // 设置默认layoutParams
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        setLayoutParams(layoutParams)

        // 设置mViewGroup无事件的点击事件，不设置的话点击会透传到mParentView
        mViewGroup?.setOnClickListener {}
        setCanceledOnTouchOutside(true)
        setBackgroundColor(0xaa000000.toInt())
        return mParentView!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    protected abstract fun getContentLayout(): Int

    protected abstract fun initView()

    /**
     * 设置LayoutParams
     */
    protected fun setLayoutParams(layoutParams: FrameLayout.LayoutParams) {
        mViewGroup!!.layoutParams = layoutParams
    }

    /**
     * 设置点击外部是否关闭,默认true
     */
    protected fun setCanceledOnTouchOutside(isCancel: Boolean) {
        if (isCancel) {
            mParentView?.setOnClickListener { dismiss() }
        } else {
            mParentView?.setOnClickListener { }
        }
    }

    /**
     * 设置背景是否暗色
     */
    protected fun setBackgroundColor(color: Int) {
        mParentView?.setBackgroundColor(color)
    }

    protected fun <V : View?> findViewId(@IdRes id: Int): V {
        return mViewGroup!!.findViewById<V>(id)
    }

    fun dismiss() {
        requireNavigationScene().pop()
    }

}