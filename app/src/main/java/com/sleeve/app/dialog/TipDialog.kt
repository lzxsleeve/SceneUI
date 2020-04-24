package com.sleeve.app.dialog

import android.view.View
import com.sleeve.app.R
import com.sleeve.ui.dialog.BaseSceneDialog
import kotlinx.android.synthetic.main.dialog_tip.view.*

/**
 * 说明
 *
 * Create by lzx on 2020/4/23 0023.
 */
class TipDialog : BaseSceneDialog(), View.OnClickListener {

    override fun getContentLayout(): Int {
        return R.layout.dialog_tip
    }

    override fun initView() {
        view.dialog_title.text = "标题"
        view.dialog_cancel.setOnClickListener(this)
        view.dialog_ok.setOnClickListener(this)
        val background = view.dialog_background
        background.setOnClickListener(this)
        arguments?.let {
            background.setBackgroundColor(it.getInt("color"))
            view.dialog_content.text = it.getString("content")
        }
    }

    override fun onClick(v: View?) {
        dismiss()
    }
}