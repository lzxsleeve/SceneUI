package com.sleeve.app

import android.os.Bundle
import android.view.View
import com.bytedance.scene.ktx.requireFragmentActivity
import com.sleeve.app.dialog.TipDialog
import com.sleeve.app.dialog.TipDialogFragment
import com.sleeve.ui.base.BaseHeadBarAppScene
import com.sleeve.ui.base.BaseHeadBarScene
import com.sleeve.ui.view.HeadBar
import kotlinx.android.synthetic.main.scene_dialog_test.view.*

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
        view.dialog_btn1.setOnClickListener(this)
        view.dialog_btn2.setOnClickListener(this)
        view.dialog_btn3.setOnClickListener(this)
        view.dialog_btn4.setOnClickListener(this)
        view.dialog_btn5.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.dialog_btn1 -> {
                val bundle = Bundle()
                bundle.putBoolean("isDark", false)
                bundle.putString("content", "我是无背景的Scene弹窗")
                showSceneDialog(TipDialog(), bundle)
            }
            R.id.dialog_btn2 -> {
                val bundle = Bundle()
                bundle.putBoolean("isDark", true)
                bundle.putString("content", "我是有背景的Scene弹窗")
                showSceneDialog(TipDialog(), bundle)
            }
            R.id.dialog_btn3 -> {
                val dialog = TipDialogFragment()
                dialog.setTextBtn(content = "我是DialogFragment")
                dialog.showDialog(requireFragmentActivity().supportFragmentManager)
            }
            R.id.dialog_btn4 -> {
                val bundle = Bundle()
                bundle.putBoolean("isDark", true)
                bundle.putString("content", "当输入框位置偏下时会出现超出部分被截取的情况，可以对比原生DialogFragment看看，原生是截取上面部分。\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
                showSceneDialog(TipDialog(), bundle)
            }
            R.id.dialog_btn5 -> {
                val dialog = TipDialogFragment()
                dialog.setTextBtn(content = "我是DialogFragment，可以看看在输入框位置偏下时DialogFragment的处理情况，原生会顶掉上面的部分，焦点会保持在EditView上。\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
                dialog.showDialog(requireFragmentActivity().supportFragmentManager)
            }
        }
    }
}