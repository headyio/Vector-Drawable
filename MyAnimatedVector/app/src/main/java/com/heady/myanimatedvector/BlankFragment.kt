package com.heady.myanimatedvector

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.heady.myanimatedvector.databinding.FragmentBlankBinding

class BlankFragment : Fragment(R.layout.fragment_blank) {

    private val binding by viewBindings(FragmentBlankBinding::bind)

    private val viewPager2PageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            val tab = binding.tabLayout.getTabAt(position)
            Tabs.entries.forEachIndexed { index, tabs ->
                if (index == position) {
                    tab?.setCustomView(TabView(requireContext(), true))
                } else {
                    binding.tabLayout.getTabAt(index)?.setCustomView(TabView(requireContext(), false))
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setUpViewPager()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(viewPager2PageChangeListener)
    }

    private fun FragmentBlankBinding.setUpViewPager() {
        /** This will ensure page doesn't load on switching time repeatedly.
         * This should be before setting viewPager adapter */
        viewPager.setOffscreenPageLimit(2)
        viewPager.adapter = OLPViewPagerAdapter(
            fragmentManager = childFragmentManager,
            lifecycle = lifecycle
        )
        viewPager.registerOnPageChangeCallback(viewPager2PageChangeListener)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = Tabs.entries[position].title
            tab.setCustomView(TabView(requireContext(), tab.isSelected))
        }.attach()
    }

    inner class OLPViewPagerAdapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(
        fragmentManager,
        lifecycle
    ) {
        override fun getItemCount(): Int = Tabs.entries.size
        override fun createFragment(position: Int): Fragment {
            return Blank4Fragment()
        }
    }
}