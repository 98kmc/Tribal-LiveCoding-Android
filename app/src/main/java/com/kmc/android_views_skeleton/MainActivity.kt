package com.kmc.android_views_skeleton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.kmc.android_views_skeleton.databinding.ActivityMainBinding
import com.kmc.android_views_skeleton.utils.navigation.navigator.Navigator

class MainActivity : AppCompatActivity() {

    private lateinit var app: App
    private lateinit var binding: ActivityMainBinding

    private val didTapBackButton: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount == 1) finish()
            else app.navigator.popFragment(animated = true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(this, didTapBackButton)

        app = this.application as App
        app.navigator = Navigator(supportFragmentManager, this)
        app.start()
    }
}