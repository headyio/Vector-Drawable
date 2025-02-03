package com.heady.myanimatedvector

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class TabView : LinearLayout {

    constructor(
        context: Context?,
    ) : super(context!!)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
    ) : super(context!!, attrs)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int,
    ) : super(context!!, attrs, defStyle)

    constructor(
        context: Context?,
        title: String,
        isSelected: Boolean,
        isPreviouslySelected: Boolean
    ) : super(context!!) {
        initView(title, isSelected, isPreviouslySelected)
    }

    private fun initView(
        title: String,
        isSelected: Boolean,
        isPreviouslySelected: Boolean
    ) {
        inflate(context, R.layout.tab_view, this)

        val ivVector = findViewById<ShapeableImageView>(R.id.iv_vector)
        val tvTitle = findViewById<MaterialTextView>(R.id.tv_title)

        tvTitle.text = title
        tvTitle.setTextColor(resources.getColor(android.R.color.darker_gray, null))

        if (isPreviouslySelected) {
            ivVector.setImageResource(R.drawable.avd_bn_check_to_close)
            ivVector.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
            (ivVector.drawable as AnimatedVectorDrawable).start()
        } else if (isSelected) {
            ivVector.setImageResource(R.drawable.avd_bn_close_to_check)
            ivVector.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.holo_purple, null))
            tvTitle.setTextColor(resources.getColor(android.R.color.holo_purple, null))
            (ivVector.drawable as AnimatedVectorDrawable).start()
        } else {
            ivVector.setImageResource(R.drawable.ic_close)
            ivVector.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
        }
    }
}