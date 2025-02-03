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

    private var previousPos = 0

    private val viewPager2PageChangeListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Tabs.entries.forEachIndexed { index, tabs ->
                when (index) {
                    position -> {
                        val tabView = TabView(
                            context = requireContext(),
                            avdForwardIcon = Tabs.entries.get(index).avdForwardIcon,
                            avdBackwardIcon = Tabs.entries.get(index).avdBackwardIcon,
                            staticIcon = Tabs.entries.get(index).staticIcon,
                            title = Tabs.entries.get(index).title,
                            isSelected = true,
                            isPreviouslySelected = false
                        )
                        binding.tabLayout.getTabAt(position)?.setCustomView(tabView)
                    }
                    previousPos -> {
                        val tabView = TabView(
                            context = requireContext(),
                            avdForwardIcon = Tabs.entries.get(index).avdForwardIcon,
                            avdBackwardIcon = Tabs.entries.get(index).avdBackwardIcon,
                            staticIcon = Tabs.entries.get(index).staticIcon,
                            title = Tabs.entries.get(index).title,
                            isSelected = false,
                            isPreviouslySelected = true
                        )
                        binding.tabLayout.getTabAt(index)?.setCustomView(tabView)
                    }
                    else -> {
                        val tabView = TabView(
                            context = requireContext(),
                            avdForwardIcon = Tabs.entries.get(index).avdForwardIcon,
                            avdBackwardIcon = Tabs.entries.get(index).avdBackwardIcon,
                            staticIcon = Tabs.entries.get(index).staticIcon,
                            title = Tabs.entries.get(index).title,
                            isSelected = false,
                            isPreviouslySelected = false
                        )
                        binding.tabLayout.getTabAt(index)?.setCustomView(tabView)
                    }
                }
            }
            previousPos = position
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
            val tabView = TabView(
                context = requireContext(),
                avdForwardIcon = Tabs.entries.get(position).avdForwardIcon,
                avdBackwardIcon = Tabs.entries.get(position).avdBackwardIcon,
                staticIcon = Tabs.entries.get(position).staticIcon,
                title = Tabs.entries.get(position).title,
                isSelected = tab.isSelected,
                isPreviouslySelected = false
            )
            tab.setCustomView(tabView)
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