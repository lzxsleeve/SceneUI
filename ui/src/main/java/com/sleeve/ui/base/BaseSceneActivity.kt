package com.sleeve.ui.base

import com.bytedance.scene.ui.SceneActivity
import com.sleeve.ui.util.Keyboard

/**
 * Activity 的基类--不可滑动
 * 修复输入法造成的内存泄漏
 *
 * Create by Sleeve on 2020/1/14
 */
abstract class BaseSceneActivity : SceneActivity(){

    override fun onDestroy() {
        Keyboard.fixInputMethodManagerLeak(this)
        super.onDestroy()
    }
}