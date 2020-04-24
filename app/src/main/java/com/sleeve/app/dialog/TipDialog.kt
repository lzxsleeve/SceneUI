package com.sleeve.app.dialog

import android.graphics.Color
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
        view.dialog_background.setOnClickListener(this)
        arguments?.let {
            view.dialog_content.text = it.getString("content")
            if (!it.getBoolean("isDark")) {
                setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

    override fun onClick(v: View?) {
        dismiss()
    }
}