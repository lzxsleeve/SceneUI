package com.sleeve.app.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.sleeve.app.R
import kotlinx.android.synthetic.main.dialog_tip.*

/**
 * 提示 dialog
 */
class TipFragmentDialog : DialogFragment() {

    var mListener: OnListener? = null
    private var mTitle: String = "温馨提示"
    private var mContent: String = ""
    private var mOk: String = ""

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = window.attributes
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            window.attributes = lp
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val window = dialog?.window
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.dialog_tip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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