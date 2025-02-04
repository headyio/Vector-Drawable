package com.heady.myanimatedvector

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
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
        @DrawableRes avdForwardIcon: Int,
        @DrawableRes avdBackwardIcon: Int,
        @DrawableRes staticIcon: Int,
        title: String,
        isSelected: Boolean,
        isPreviouslySelected: Boolean
    ) : super(context!!) {
        initView(
            avdForwardIcon,
            avdBackwardIcon,
            staticIcon,
            title,
            isSelected,
            isPreviouslySelected
        )
    }

    private fun initView(
        @DrawableRes avdForwardIcon: Int,
        @DrawableRes avdBackwardIcon: Int,
        @DrawableRes staticIcon: Int,
        title: String,
        isSelected: Boolean,
        isPreviouslySelected: Boolean
    ) {
        inflate(context, R.layout.tab_view, this)

        val ivVector = findViewById<ShapeableImageView>(R.id.iv_vector)
        val tvTitle = findViewById<MaterialTextView>(R.id.tv_title)

        tvTitle.text = title
        tvTitle.setTextColor(resources.getColor(android.R.color.darker_gray, null))
        tvTitle.setTypeface(null, Typeface.NORMAL)

        when {
            isPreviouslySelected -> {
                ivVector.setImageResource(avdForwardIcon)
                ivVector.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
                (ivVector.drawable as AnimatedVectorDrawable).start()
            }
            isSelected -> {
                ivVector.setImageResource(avdBackwardIcon)
                ivVector.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.holo_purple, null))
                tvTitle.setTextColor(resources.getColor(android.R.color.holo_purple, null))
                tvTitle.setTypeface(null, Typeface.BOLD)
                (ivVector.drawable as AnimatedVectorDrawable).start()
            }
            else -> {
                ivVector.setImageResource(staticIcon)
                ivVector.imageTintList = ColorStateList.valueOf(resources.getColor(android.R.color.darker_gray, null))
            }
        }
    }
}