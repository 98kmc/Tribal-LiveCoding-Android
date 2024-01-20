package com.kmc.android_views_skeleton.infrastructure.navigation.navigator

import androidx.fragment.app.Fragment
 data class FragmentView(
    val name: String,
    val group: String,
    val instance: Fragment
)

fun newFragmentView(name: String, group: String = "", fragment: () -> Fragment) =
    FragmentView(name, group, fragment())
