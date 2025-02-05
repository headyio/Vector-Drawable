package com.heady.myanimatedvector

import android.content.res.ColorStateList
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.heady.myanimatedvector.databinding.FragmentBlank4Binding

class Blank4Fragment : Fragment(R.layout.fragment_blank_4) {

    companion object {
        fun newInstance(position: Int): Blank4Fragment {
            return Blank4Fragment().apply {
                arguments = Bundle().apply { putInt(ARG_SCREEN_TYPE, position) }
            }
        }
    }

    private val binding by viewBindings(FragmentBlank4Binding::bind)

    private var isChecked = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val screenPos = arguments?.getInt(ARG_SCREEN_TYPE) ?: 0
        binding.root.backgroundTintList = ColorStateList.valueOf(
            resources.getColor(colorResList.get(0), null)
        )

        binding.ivVector.setImageResource(Tabs.entries.get(screenPos).animatedSelector)
        binding.ivVector.drawable.setTint(resources.getColor(R.color.white, null))

        binding.ivVector.setOnClickListener {
            val avd = if (isChecked) {
                Tabs.entries.get(screenPos).avdBackwardIcon
            } else {
                Tabs.entries.get(screenPos).avdForwardIcon
            }
            binding.ivVector.setImageResource(avd)
            binding.ivVector.drawable.setTint(resources.getColor(R.color.white, null))
            (binding.ivVector.drawable as AnimatedVectorDrawable).start()
            isChecked = isChecked.not()
        }
    }
}