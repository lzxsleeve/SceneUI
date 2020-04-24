package com.sleeve.ui.config

import com.bytedance.scene.animation.NavigationAnimationExecutor
import com.bytedance.scene.animation.animatorexecutor.HorizontalTransitionAnimatorExecutor
import com.bytedance.scene.interfaces.PopOptions
import com.bytedance.scene.interfaces.PushOptions

/**
 * Scene过渡动画配置
 *
 * Create by Sleeve on 2020/1/22
 */
object TransitionAnimatorConfig {
    private var popOptions: PopOptions
    private var pushOptions: PushOptions

    init {
        val animExecutor = HorizontalTransitionAnimatorExecutor()
        popOptions = PopOptions.Builder()
            .setAnimation(animExecutor)
            .build()
        pushOptions = PushOptions.Builder()
            .setAnimation(animExecutor)
            .build()
    }

    fun getPopOptions(): PopOptions {
        return popOptions
    }

    fun getPushOptions(): PushOptions {
        return pushOptions
    }

    /**
     * 设置Scene全局pop动画
     */
    fun setPopAnimation(anim: NavigationAnimationExecutor?) {
        if (anim == null) throw NullPointerException("Scene过渡动画不能设置为空")
        popOptions = PopOptions.Builder()
            .setAnimation(anim)
            .build()
    }

    /**
     * 设置Scene全局push动画
     */
    fun setPushAnimation(anim: NavigationAnimationExecutor?) {
        if (anim == null) throw NullPointerException("Scene过渡动画不能设置为空")
        pushOptions = PushOptions.Builder()
            .setAnimation(anim)
            .build()
    }
}