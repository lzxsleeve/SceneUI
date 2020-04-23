package com.sleeve.app.dialog

import android.view.View
import android.widget.TextView
import com.sleeve.app.R
import com.sleeve.ui.dialog.BaseSceneDialog

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
        findViewId<TextView>(R.id.dialog_title).text = "标题"
        findViewId<View>(R.id.dialog_cancel).setOnClickListener(this)
        findViewId<View>(R.id.dialog_ok).setOnClickListener(this)
        val background = findViewId<View>(R.id.dialog_background)
        background.setOnClickListener(this)
        arguments?.let {
            background.setBackgroundColor(it.getInt("color"))
            findViewId<TextView>(R.id.dialog_content).text = it.getString("content")
        }
    }

    override fun onClick(v: View?) {
        dismiss()
    }
}