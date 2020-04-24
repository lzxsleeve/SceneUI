package com.sleeve.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.bytedance.scene.Scene

/**
 * 自定义程度较高的DialogScene
 *
 * Create by lzx on 2020/4/24.
 */
abstract class BaseCustomDialogScene : Scene() {
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