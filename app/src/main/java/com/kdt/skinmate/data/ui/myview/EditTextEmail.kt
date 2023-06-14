package com.kdt.skinmate.data.ui.myview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.kdt.skinmate.R


class EditTextEmail : AppCompatEditText, View.OnTouchListener  {
    private lateinit var delButtonImage: Drawable

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        delButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_close) as Drawable
        setOnTouchListener(this)



        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) showDelButton() else hideDelButton()
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }
    private fun showDelButton() {
        setButtonDrawables(endOfTheText = delButtonImage)

    }

    private fun hideDelButton() {
        setButtonDrawables()
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText:Drawable? = null,
        endOfTheText:Drawable? = null,
        bottomOfTheText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val delButtonStart: Float
            val delButtonEnd: Float
            var isDelButtonClicked = false
            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                delButtonEnd = (delButtonImage.intrinsicWidth + paddingStart).toFloat()
                when {
                    event.x < delButtonEnd -> isDelButtonClicked = true
                }
            } else {
                delButtonStart = (width - paddingEnd - delButtonImage.intrinsicWidth).toFloat()
                when {
                    event.x > delButtonStart -> isDelButtonClicked = true
                }
            }
            if (isDelButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        delButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_close) as Drawable
                        showDelButton()
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        delButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_close) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideDelButton()
                        return true
                    }
                    else -> return false
                }
            }
        }
        return false
    }
}