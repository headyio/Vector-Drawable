/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.heady.myanimatedvector

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.viewbinding.ViewBinding

/**
 * Retrieves a view binding handle in a Fragment. The field is available only after
 * [Fragment.onViewCreated].
 *
 * ```
 *     private val binding by dataBindings(HomeFragmentBinding::bind)
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         binding.someView.someField = ...
 *     }
 * ```
 */
inline fun <reified BindingT : ViewBinding> Fragment.viewBindings(
    crossinline bind: (View) -> BindingT
) = object : Lazy<BindingT> {

    private var cached: BindingT? = null

    private val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            cached = null
        }
    }

    override val value: BindingT
        get() = cached ?: bind(requireView()).also {
            viewLifecycleOwner.lifecycle.addObserver(observer)
            cached = it
        }

    override fun isInitialized() = cached != null
}

/** these are useful extension function that allow you too remove the unnecessary
 * boilerplate code to implement viewbinding in android.
 * checkout the link available in the PR. */
inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) = lazy(LazyThreadSafetyMode.NONE) {
    bindingInflater.invoke(layoutInflater)
}

const val ARG_SCREEN_TYPE = "ARG_SCREEN_TYPE"

val colorResList = listOf(
    android.R.color.holo_purple,
    android.R.color.holo_green_light,
    android.R.color.holo_orange_light
)

enum class Tabs(
    val title: String,
    @DrawableRes val avdForwardIcon: Int,
    @DrawableRes val avdBackwardIcon: Int,
    @DrawableRes val staticIcon: Int,
) {
    HOME(
        title = "Home",
        avdForwardIcon = R.drawable.avd_bn_check_to_close,
        avdBackwardIcon = R.drawable.avd_bn_close_to_check,
        staticIcon = R.drawable.ic_close
    ),
    SEARCH(
        title = "Search",
        avdForwardIcon = R.drawable.avd_bn_check_to_close,
        avdBackwardIcon = R.drawable.avd_bn_close_to_check,
        staticIcon = R.drawable.ic_close
    ),
    PROFILE(
        title = "Profile",
        avdForwardIcon = R.drawable.avd_profile,
        avdBackwardIcon = R.drawable.avd_profile,
        staticIcon = R.drawable.ic_profile
    ),
    SETTINGS(
        title = "Settings",
        avdForwardIcon = R.drawable.avd_settings_forward,
        avdBackwardIcon = R.drawable.avd_settings_backward,
        staticIcon = R.drawable.ic_settings
    )
}