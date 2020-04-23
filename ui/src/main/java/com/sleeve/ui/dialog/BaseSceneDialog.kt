package com.sleeve.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.bytedance.scene.Scene

/**
 * 弹窗基类
 * 优点：轻量、没有FragmentDialog的内存泄漏，在没有特殊需求时推荐使用
 * 缺点：动画自定义不方便
 * todo:需要封装一个通用的暗色背景
 *
 * Create by lzx on 2020/4/23.
 */
abstract class BaseSceneDialog : Scene() {

    /**
     * 显示内容的根布局
     */
    protected var mViewGroup: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        mViewGroup = inflater.inflate(getContentLayout(), null)
        return mViewGroup!!
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    protected abstract fun getContentLayout(): Int

    protected abstract fun initView()

    protected fun <V : View?> findViewId(@IdRes id: Int): V {
        return mViewGroup!!.findViewById<V>(id)
    }

    fun dismiss() {
        requireNavigationScene().pop()
    }

}