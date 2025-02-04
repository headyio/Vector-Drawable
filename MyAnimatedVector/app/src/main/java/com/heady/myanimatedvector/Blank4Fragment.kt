package com.heady.myanimatedvector

import android.content.res.ColorStateList
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heady.myanimatedvector.databinding.FragmentBlank4Binding

class Blank4Fragment : Fragment(R.layout.fragment_blank_4) {

    private val binding by viewBindings(FragmentBlank4Binding::bind)

    private var isChecked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val colorResPos = arguments?.getInt(ARG_SCREEN_TYPE) ?: 0
        binding.root.backgroundTintList = ColorStateList.valueOf(
            resources.getColor(colorResList.get(colorResPos), null)
        )

        binding.ivVector.setImageResource(R.drawable.avd_bn_check_to_close)
        binding.ivVector.drawable.setTint(resources.getColor(R.color.white, null))

        binding.ivVector.setOnClickListener {
            val avd = if (isChecked) {
                R.drawable.avd_bn_close_to_check
            } else {
                R.drawable.avd_bn_check_to_close
            }
            binding.ivVector.setImageResource(avd)
            binding.ivVector.drawable.setTint(resources.getColor(R.color.white, null))
            (binding.ivVector.drawable as AnimatedVectorDrawable).start()
            isChecked = isChecked.not()
        }
    }
}