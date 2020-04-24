package com.sleeve.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes

/**
 * 弹窗基类
 *
 * Create by lzx on 2020/04/13.
 */
abstract class BaseDialogFragment : FixLeakDialogFragment() {

    private var dialogGravity = Gravity.CENTER
    private var animation = 0

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (animation != 0) {
                window.setWindowAnimations(animation)
            }
            val lp = window.attributes
            lp.gravity = dialogGravity
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = lp
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val window = dialog?.window
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(getDialogLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDialogView()
    }

    @LayoutRes
    protected abstract fun getDialogLayout(): Int

    protected abstract fun initDialogView()

    /**
     * 设置对齐方式,在onStart前设置
     */
    protected fun setGravity(gravity: Int) {
        dialogGravity = gravity
    }

    /**
     * 设置动画,在onStart前设置
     */
    protected fun setAnimation(@StyleRes anim: Int) {
        animation = anim
    }

    /**
     * 设置点击外部不可关闭，默认可以关闭
     */
    protected fun setCanceledOnTouchOutside() {
        dialog?.setCanceledOnTouchOutside(false)
    }

    /**
     * 设置返回键不可关闭弹窗
     */
    protected fun setBackKeyClose() {
        dialog?.setOnKeyListener { _, keyCode, _ -> keyCode == KeyEvent.KEYCODE_BACK }
    }
}