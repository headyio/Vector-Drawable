package com.heady.myanimatedvector

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heady.myanimatedvector.databinding.FragmentBlankBinding

class BlankFragment : Fragment(R.layout.fragment_blank) {

    private val binding by viewBindings(FragmentBlankBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorRes = when (arguments?.getInt(ARG_SCREEN_TYPE) ?: 1) {
            1 -> android.R.color.holo_purple
            2 -> android.R.color.holo_green_light
            else -> android.R.color.holo_orange_light
        }
        binding.root.backgroundTintList = ColorStateList.valueOf(resources.getColor(colorRes, null))
    }
}