package com.sleeve.ui.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.bytedance.scene.Scene
import com.bytedance.scene.animation.NavigationAnimationExecutor
import com.bytedance.scene.animation.animatorexecutor.HorizontalTransitionAnimatorExecutor
import com.bytedance.scene.interfaces.PushOptions
import com.bytedance.scene.interfaces.PushResultCallback
import com.bytedance.scene.ui.template.AppCompatScene
import com.sleeve.ui.view.HeadBar

/**
 * AppCompatScene 封装通用方法的基类
 * 注：非特殊情况，如软键盘适配问题时，推荐使用BaseScene
 *
 * Create by lzx on 2020/4/21.
 */
abstract class BaseAppScene : AppCompatScene() {
    /**
     * 显示内容的根布局
     */
    protected var mViewGroup: FrameLayout? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarVisible(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNavigationAnim(HorizontalTransitionAnimatorExecutor())
        initView()
    }

    /**
     * 设置标题
     */
    protected abstract fun initHeadBar(headBar: HeadBar)

    @LayoutRes
    protected abstract fun getContentLayout(): Int

    protected abstract fun initView()

    protected fun <V : View?> findViewId(@IdRes id: Int): V {
        return mViewGroup!!.findViewById<V>(id)
    }

    /**
     * 页面默认动画设置，包含push和pop
     */
    fun setNavigationAnim(executor: NavigationAnimationExecutor) {
        requireNavigationScene().defaultNavigationAnimationExecutor = executor
    }


    fun pop() {
        requireNavigationScene().pop()
    }

    fun popTo(clazz: Class<out Scene?>) {
        requireNavigationScene().popTo(clazz)
    }

    fun popToRoot() {
        requireNavigationScene().popToRoot()
    }

    fun start(scene: Scene) {
        requireNavigationScene().push(scene)
    }

    fun start(scene: Scene, option: PushOptions) {
        requireNavigationScene().push(scene, option)
    }

    /**
     * 跳转并弹出当前页面
     */
    fun startWithPop(scene: Scene) {
        start(scene, PushOptions.Builder().clearCurrent().build())
    }

    /**
     * 跳转页面并设置返回结果监听
     */
    fun startResultCallback(scene: Scene, resultCallback: PushResultCallback, builder: PushOptions.Builder = PushOptions.Builder()) {
        val options = builder.setPushResultCallback(resultCallback).build()
        start(scene, options)
    }
}