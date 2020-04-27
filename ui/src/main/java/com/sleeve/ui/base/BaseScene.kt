package com.sleeve.ui.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.bytedance.scene.Scene
import com.bytedance.scene.animation.NavigationAnimationExecutor
import com.bytedance.scene.animation.animatorexecutor.DialogSceneAnimatorExecutor
import com.bytedance.scene.animation.animatorexecutor.HorizontalTransitionAnimatorExecutor
import com.bytedance.scene.interfaces.PushOptions
import com.bytedance.scene.interfaces.PushResultCallback
import com.bytedance.scene.ui.template.SwipeBackGroupScene
import com.bytedance.scene.ui.view.StatusBarView

/**
 * SwipeBackGroupScene 封装通用方法的基类, 默认可以右滑关闭
 * ps: 软键盘适配会存在一些不方便使用的地方，比如adjustResize需要要搭配android:fitsSystemWindows使用，但是会增加一个状态栏高度的空白区域，这时推荐使用BaseAppScene
 * pss: 其实全部都使用BaseAppScene也没什么问题
 *
 * Create by lzx on 2020/4/21.
 */
abstract class BaseScene : SwipeBackGroupScene() {
    /**
     * 显示内容的根布局
     */
    protected var mViewGroup: FrameLayout? = null
    protected var mStatusBar: StatusBarView? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNavigationAnim(HorizontalTransitionAnimatorExecutor())
        initView()
    }

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
        start(scene, PushOptions.Builder().build())
    }

    fun start(scene: Scene, option: PushOptions) {
        requireNavigationScene().push(scene, option)
    }

    /**
     * 使用class进行跳转
     * ps:使用ReuseGroupScene时必须使用class跳转，其他时候并无太大区别
     */
    fun start(clazz: Class<out Scene?>, argument: Bundle? = null, pushOptions: PushOptions = PushOptions.Builder().build()) {
        requireNavigationScene().push(clazz, argument, pushOptions)
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

    /**
     * 显示弹窗
     */
    fun showSceneDialog(scene: Scene, bundle: Bundle? = null) {
        if (bundle != null) {
            scene.setArguments(bundle)
        }
        val option = PushOptions.Builder().setTranslucent(true).setAnimation(DialogSceneAnimatorExecutor()).build()
        requireNavigationScene().push(scene, option)
    }

}