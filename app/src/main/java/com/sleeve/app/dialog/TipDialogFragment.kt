package com.sleeve.app.dialog

import android.view.View
import androidx.fragment.app.FragmentManager
import com.sleeve.app.R
import com.sleeve.ui.dialog.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_tip.*

/**
 * 提示 dialog
 */
class TipDialogFragment : BaseDialogFragment() {

    var mListener: OnListener? = null
    private var mTitle: String = "温馨提示"
    private var mContent: String = ""
    private var mOk: String = ""

    override fun getDialogLayout(): Int {
        return R.layout.dialog_tip
    }

    override fun initDialogView() {
        dialog_title.text = mTitle
        dialog_content.text = mContent
        dialog_ok.text = mOk

        dialog_cancel.setOnClickListener {
            dismiss()
            mListener?.onCancel(it)
        }

        dialog_ok.setOnClickListener {
            dismiss()
            mListener?.onOk(it)
        }

        dialog_background.setOnClickListener {
            dismiss()
        }

        dialog_background.getChildAt(0).isClickable = true
    }

    fun showDialog(manager: FragmentManager) {
        if (!isAdded) {
            try {
                show(manager, "tip")
            } catch (e: Exception) {
            }
        }
    }

    @JvmOverloads
    fun setTextBtn(title: String? = "温馨提示", content: String, ok: String? = "确定") {
        title?.let { mTitle = it }
        mContent = content
        ok?.let { mOk = it }
    }

    interface OnListener {

        fun onOk(v: View) {
        }

        fun onCancel(v: View) {
        }
    }
}