package com.demo.tracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.demo.tracker.R
import com.demo.tracker.services.PolyLines
import com.demo.tracker.utils.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO : Here we have to called this below method bcoz when the application killed
        //  or Activity Destroyed them the onCreate Method is called.
        navigateToTrackingFragmentIfNeeded(intent)
        setSupportActionBar(toolbar)
        // TODO : this will find and set the Nav Host Fragment with the Bottom Navigation View
        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        bottomNavigationView.setOnNavigationItemReselectedListener { /* NO-OP */ }

        // TODO : To  Display Bottom Navigation View Only in the selected Fragments
        navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.settingsFragment, R.id.runFragment, R.id.statisticsFragment ->
                    bottomNavigationView.visibility = View.VISIBLE
                else -> bottomNavigationView.visibility = View.GONE
            }
        }

    }

    // TODO : This method called only when application is
    //  in foreground and MainActivity is not destroyed
    override fun onNewIntent(mIntent: Intent?) {
        super.onNewIntent(mIntent)
        navigateToTrackingFragmentIfNeeded(mIntent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        intent?.let {
            if (it.action == ACTION_SHOW_TRACKING_FRAGMENT) {
                navHostFragment.findNavController().navigate(R.id.action_global_trackingFragment)
            }
        }
    }
}