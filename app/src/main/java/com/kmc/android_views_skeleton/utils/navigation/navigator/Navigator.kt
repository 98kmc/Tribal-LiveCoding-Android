package com.kmc.android_views_skeleton.utils.navigation.navigator

import android.app.Activity
import android.content.Context
import android.os.Looper
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionInflater
import com.kmc.android_views_skeleton.R
import kotlin.math.min

class Navigator(
    private var fragmentManager: FragmentManager,
    private val context: Context
) : AppNavigator {

    val backStack: List<String>
        get() = (0..<fragmentManager.backStackEntryCount).mapNotNull {
            fragmentManager.getBackStackEntryAt(it).name
        }

    private val host = R.id.main_activity_container

    override fun pushFragment(fragment: FragmentView, animated: Boolean) {

        checkMainThread()
        val tag = "${fragment.group}/${fragment.name}"
        val transaction = fragmentManager.beginTransaction()
        transaction.add(host, fragment.instance, tag)
        transaction.addToBackStack(tag)

        if (animated) {

            fragment.instance.enterTransition = Slide(Gravity.END)
            fragment.instance.exitTransition = Slide(Gravity.START)
            fragment.instance.sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)

            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        transaction.commit()
    }

    override fun present(fragment: FragmentView, fullScreen: Boolean) {

        checkMainThread()

        val presentedFragment = PresentedBottomSheetFragment.newInstance(fullScreen)

        presentedFragment.showNow(fragmentManager, "${fragment.group}/${fragment.name}")
        val childFragmentManager = presentedFragment.childFragmentManager
        val childTransaction = childFragmentManager.beginTransaction()
        childTransaction.add(R.id.presented_view_container, fragment.instance)
        childTransaction.commit()
    }

    override fun popFragment(animated: Boolean) {
        checkMainThread()
        if (animated) {
            fragmentManager.popBackStackImmediate()
            return
        }

        val fragmentTag =
            fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).name
        val fragmentId =
            fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - 1).id
        val fragment = fragmentManager.findFragmentByTag(fragmentTag)
        if (fragmentId == 0) (context as Activity).finishAfterTransition()
        else {
            fragment?.let {
                removeFragment(it)
            }
        }
    }

    override fun popFragment(count: Int, animated: Boolean) {

        checkMainThread()
        val fragmentCount = min(count, fragmentManager.backStackEntryCount)

        if (animated) {
            for (i in 0 until fragmentCount) {
                fragmentManager.popBackStackImmediate()
            }
            return
        }

        for (i in 1..fragmentCount) {

            val fragmentTag =
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - i).name
            val fragmentId =
                fragmentManager.getBackStackEntryAt(fragmentManager.backStackEntryCount - i).id
            val fragment = fragmentManager.findFragmentByTag(fragmentTag)

            if (fragmentId == 0) (context as Activity).finish()
            else {
                fragment?.let {
                    removeFragment(it)
                }
            }
        }
    }

    override fun popTo(fragment: FragmentView, animated: Boolean) { }

    /***
     * Private Methods
     */
    private fun checkMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw IllegalStateException("Calling a Navigator method from a background thread.")
        }
    }

    private fun removeFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        fragment.enterTransition = Fade(Fade.OUT)
        fragment.exitTransition = Fade(Fade.IN)
        fragment.sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.remove(fragment)
        transaction.commitNow()
        fragmentManager.popBackStack()
    }
}