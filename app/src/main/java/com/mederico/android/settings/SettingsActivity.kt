package com.mederico.android.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mederico.android.R
import com.mederico.android.databinding.ActivitySettingsBinding
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private var firstTime: Boolean = true

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_DARK_MODE = "key_dark_mode"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        CoroutineScope(Dispatchers.IO).launch {
            getSetting().filter { firstTime }.collect { settingsModel ->
                if (settingsModel != null) {
                    runOnUiThread {
                        binding.swSettingBluetooth.isChecked = settingsModel.bluetooth
                        binding.swSettingVibration.isChecked = settingsModel.vibration
                        binding.swSettingDarkMode.isChecked = settingsModel.darkmode
                        binding.rgSettingVolume.setValues(settingsModel.volume.toFloat())
                        firstTime=!firstTime
                    }
                }

            }

        }
        initUI()
    }

    private fun initUI() {
        binding.rgSettingVolume.addOnChangeListener { _, value, _ ->
            Log.i("UI", "Selected $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }
        binding.swSettingBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveCheckSetting(value, KEY_BLUETOOTH)
            }
        }
        binding.swSettingVibration.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveCheckSetting(value, KEY_VIBRATION)
            }
        }
        binding.swSettingDarkMode.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveCheckSetting(value, KEY_DARK_MODE)
            }
            if (value) {
                enableDarkMode()
            } else {
                disableDarkMode()
            }
        }
    }

    private suspend fun saveVolume(volume: Int) {
        dataStore.edit {
            it[intPreferencesKey(VOLUME_LVL)] = volume
        }
    }

    private suspend fun saveCheckSetting(check: Boolean, key: String) {
        dataStore.edit {
            it[booleanPreferencesKey(key)] = check
        }
    }

    private fun getSetting(): Flow<SettingsModel> {
        return dataStore.data.map { config ->
            SettingsModel(
                volume = config[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = config[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                darkmode = config[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                vibration = config[booleanPreferencesKey(KEY_VIBRATION)] ?: true
            )
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}