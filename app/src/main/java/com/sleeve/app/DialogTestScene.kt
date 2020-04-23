package com.sleeve.app

import android.os.Bundle
import android.view.View
import com.bytedance.scene.animation.animatorexecutor.DialogSceneAnimatorExecutor
import com.bytedance.scene.interfaces.PushOptions
import com.bytedance.scene.ktx.requireFragmentActivity
import com.sleeve.app.dialog.TipDialog
import com.sleeve.app.dialog.TipFragmentDialog
import com.sleeve.ui.base.BaseHeadBarScene
import com.sleeve.ui.view.HeadBar

/**
 * 弹窗测试
 *
 * Create by lzx on 2020/4/23.
 */
class DialogTestScene : BaseHeadBarScene(), View.OnClickListener {
    override fun initHeadBar(headBar: HeadBar) {
        headBar.setTitle("弹窗测试")
    }

    override fun getContentLayout(): Int {
        return R.layout.scene_dialog_test
    }

    override fun initView() {
        findViewId<View>(R.id.dialog_btn1).setOnClickListener(this)
        findViewId<View>(R.id.dialog_btn2).setOnClickListener(this)
        findViewId<View>(R.id.dialog_btn3).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dialog_btn1 -> {
                val bundle = Bundle()
                bundle.putString("content","我是无背景的Scene弹窗")

                requireNavigationScene().push(
                    TipDialog::class.java, bundle, PushOptions.Builder()
                        .setTranslucent(true).setAnimation(DialogSceneAnimatorExecutor()).build()
                )
            }
            R.id.dialog_btn2 -> {
                val bundle = Bundle()
                bundle.putInt("color", 0xaa000000.toInt())
                bundle.putString("content","我是有背景的Scene弹窗")

                val tipDialog = TipDialog()
                tipDialog.setArguments(bundle)
                requireNavigationScene().push(
                    tipDialog, PushOptions.Builder()
                        .setTranslucent(true).setAnimation(DialogSceneAnimatorExecutor()).build()
                )
            }
            R.id.dialog_btn3 -> {
                val dialog = TipFragmentDialog()
                dialog.setTextBtn(content = "我是一条内容")
                dialog.showDialog(requireFragmentActivity().supportFragmentManager)
            }
        }
    }
}