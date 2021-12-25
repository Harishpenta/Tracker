package com.demo.tracker.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.demo.tracker.R
import com.demo.tracker.utils.Constants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAllFieldsFromSharedPref()
        btnApplyChanges.setOnClickListener {
            val success = applyChangesToSHaredPref()
            if (success) {
                Snackbar.make(
                    requireActivity().findViewById(R.id.rootView),
                    "Saved Successfully",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                Snackbar.make(
                    requireActivity().findViewById(R.id.rootView),
                    "Please enter all the fields",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun applyChangesToSHaredPref(): Boolean {
        val name = etName.text.toString()
        val weight = etWeight.text.toString()
        if (name.isEmpty() || weight.isEmpty()) {
            return false
        }
        sharedPreferences.edit()
            .putString(Constants.KEY_FIRST_NAME, name)
            .putFloat(Constants.KEY_WEIGHT, weight.toFloat())
            .apply()

        val toolbarText = "Let's go, $name"
        requireActivity().tvToolbarTitle.text = toolbarText
        return true
    }

    private fun loadAllFieldsFromSharedPref() {
        val name = sharedPreferences.getString(Constants.KEY_FIRST_NAME, "")
        val weight = sharedPreferences.getFloat(Constants.KEY_WEIGHT, 80f)

        etName.setText(name)
        etWeight.setText(weight.toString())


    }
}