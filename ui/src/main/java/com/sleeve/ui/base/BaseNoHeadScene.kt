package com.sleeve.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.bytedance.scene.animation.NavigationAnimationExecutor
import com.bytedance.scene.animation.animatorexecutor.HorizontalTransitionAnimatorExecutor
import com.bytedance.scene.ui.template.AppCompatScene
import com.sleeve.ui.R

/**
 * 说明
 *
 * Create by lzx on 2020/4/21.
 */
abstract class BaseNoHeadScene : AppCompatScene() {

    /**
     * 显示内容的根布局
     */
    protected var mViewGroup: FrameLayout? = null

    override fun onCreateContentView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        // 显示内容的根布局
        mViewGroup = inflater.inflate(R.layout.base_uif, container, false) as FrameLayout
        // 添加内容布局
        val contentLayout = getContentLayout()
        if (contentLayout != 0) {
            inflater.inflate(contentLayout, mViewGroup, true)
        }

        // 需要支持SwipeBack则这里必须调用toSwipeBackFragment(view);
        return mViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarVisible(false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setNavigationAnim(HorizontalTransitionAnimatorExecutor())
        initView()
    }

    protected abstract fun initView()

    @LayoutRes
    protected abstract fun getContentLayout(): Int

    /**
     * 页面默认动画设置，包含push和pop
     */
    fun setNavigationAnim(executor: NavigationAnimationExecutor) {
        requireNavigationScene().defaultNavigationAnimationExecutor = executor
    }

}