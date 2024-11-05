package com.sachin.humrahi.ui.customView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.Button


@SuppressLint("AppCompatCustomView")
class ASBB : Button {

    constructor(context: Context) : this(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val tf = Typeface.createFromAsset(
            context.assets,
            "fonts/Acumin-Pro-Semibold.ttf"
        )
        typeface = tf
    }
}