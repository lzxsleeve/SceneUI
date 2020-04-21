package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.bytedance.scene.Scene
import com.bytedance.scene.animation.NavigationAnimationExecutor
import com.bytedance.scene.animation.animatorexecutor.HorizontalTransitionAnimatorExecutor
import com.bytedance.scene.interfaces.PushOptions
import com.bytedance.scene.ui.template.SwipeBackGroupScene
import com.sleeve.ui.R
import com.sleeve.ui.view.HeadBar

/**
 * 具有头部的 Scene -- 可以右滑关闭
 * 注：软键盘适配会存在一些不方便使用的地方，比如adjustResize需要要搭配android:fitsSystemWindows使用，但是会增加一个状态栏高度的空白区域，这时推荐使用BaseHeadBarAppScene
 *
 * Create by Sleeve on 2020/1/14
 */
abstract class BaseHeadBarScene : SwipeBackGroupScene() {

    /**
     * 显示内容的根布局
     */
    protected var mViewGroup: FrameLayout? = null
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
        setNavigationAnim(HorizontalTransitionAnimatorExecutor())
        findViewId<View>(R.id.bar_view).visibility = View.VISIBLE
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

    private fun setToolbar(layoutParent: View) {
        mHeadBar = layoutParent.findViewById(R.id.head_bar)
        initHeadBar(mHeadBar!!)
        mHeadBar?.setOnBackClick { view: View? -> onToolbarBackClick(view) }
    }

    /**
     * 页面默认动画设置，包含push和pop
     */
    fun setNavigationAnim(executor: NavigationAnimationExecutor) {
        requireNavigationScene().defaultNavigationAnimationExecutor = executor
    }

    /**
     * 返回键的点击监听，如需实现特殊需求重写此方法即可
     */
    protected fun onToolbarBackClick(view: View?) {
        pop()
    }


    /********* pop 和 push 方法 *********/

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

    fun start(scene: Scene, bundle: Bundle) {
        scene.setArguments(bundle)
        requireNavigationScene().push(scene)
    }

    fun start(scene: Scene, bundle: Bundle?, option: PushOptions) {
        if (bundle != null) {
            scene.setArguments(bundle)
        }
        requireNavigationScene().push(scene, option)
    }
}