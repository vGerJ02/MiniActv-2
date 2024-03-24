package com.example.android.whileinuselocation.UI

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.whileinuselocation.ForegroundOnlyLocationService
import com.example.android.whileinuselocation.SharedPreferenceUtil

private const val TAG = "MainActivity"
class ForegroundLocationViewModel: ViewModel() {


    private val _foregroundLocationEnabled = MutableLiveData<Boolean>()
    val foregroundLocationEnabled: LiveData<Boolean>
        get() = _foregroundLocationEnabled

    fun onForegroundLocationButtonClick(sharedPreferences: SharedPreferences, foregroundOnlyLocationService: ForegroundOnlyLocationService?) {
        val enabled = sharedPreferences.getBoolean(SharedPreferenceUtil.KEY_FOREGROUND_ENABLED, false)

        if (enabled) {
            foregroundOnlyLocationService?.unsubscribeToLocationUpdates()
            _foregroundLocationEnabled.value = false
        } else {
            if (foregroundPermissionApproved()) {
                foregroundOnlyLocationService?.subscribeToLocationUpdates()
                    ?: Log.d(TAG, "Service Not Bound")
                _foregroundLocationEnabled.value = true
            } else {
                requestForegroundPermissions()
            }
        }
    }

    private fun foregroundPermissionApproved(): Boolean {
        // Implement your permission check logic here
        return true
    }

    private fun requestForegroundPermissions() {
        // Implement your permission request logic here
    }


    enum class PermissionStatus {
        UNKNOWN, GRANTED, DENIED
    }

    enum class ServiceBindingStatus {
        BOUND, UNBOUND
    }
}