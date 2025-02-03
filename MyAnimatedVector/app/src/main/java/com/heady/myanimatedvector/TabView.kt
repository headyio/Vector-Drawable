package com.heady.myanimatedvector

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.material.imageview.ShapeableImageView

class TabView : LinearLayout {

    constructor(
        context: Context?,
    ) : super(context!!) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
    ) : super(context!!, attrs) {
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int,
    ) : super(context!!, attrs, defStyle) {
    }

    constructor(
        context: Context?,
        isSelected: Boolean,
        isPreviouslySelected: Boolean
    ) : super(context!!) {
        initView(isSelected, isPreviouslySelected)
    }

    private fun initView(
        isSelected: Boolean,
        isPreviouslySelected: Boolean
    ) {
        inflate(context, R.layout.tab_view, this)

        val image = findViewById<ShapeableImageView>(R.id.iv_vector)

        if (isPreviouslySelected) {
            image.setImageResource(R.drawable.avd_bn_check_to_close)
            image.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
            (image.drawable as AnimatedVectorDrawable).start()
        } else if (isSelected) {
            image.setImageResource(R.drawable.avd_bn_close_to_check)
            image.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.holo_purple, null))
            (image.drawable as AnimatedVectorDrawable).start()
        } else {
            image.setImageResource(R.drawable.ic_close)
            image.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
//            (image.drawable as AnimatedVectorDrawable).start()
        }
    }
}