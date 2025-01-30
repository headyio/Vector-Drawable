package com.heady.myanimatedvector

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heady.myanimatedvector.databinding.FragmentBlankBinding

class Blank2Fragment : Fragment(R.layout.fragment_blank) {

    private val binding by viewBindings(FragmentBlankBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorResPos = arguments?.getInt(ARG_SCREEN_TYPE) ?: 0
        binding.root.backgroundTintList = ColorStateList.valueOf(
            resources.getColor(colorResList.get(colorResPos), null)
        )
    }
}