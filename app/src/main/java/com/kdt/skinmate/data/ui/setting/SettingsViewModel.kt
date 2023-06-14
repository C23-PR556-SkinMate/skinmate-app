package com.kdt.skinmate.data.ui.setting

import android.content.Context
import android.content.SharedPreferences
import com.kdt.skinmate.data.database.Repository

class SettingsViewModel(context: Repository) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_pref_file", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun getUserEmail(): String? {
        return sharedPreferences.getString("user_email", null)
    }

    fun setUserEmail(email: String) {
        editor.putString("user_email", email)
        editor.apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString("user_name", null)
    }

    fun setUserName(name: String) {
        editor.putString("user_name", name)
        editor.apply()
    }

    fun getReminderTime(): String? {
        return sharedPreferences.getString("reminder_time", null)
    }

    fun setReminderTime(time: String) {
        editor.putString("reminder_time", time)
        editor.apply()
    }

    fun getSkinType(): String? {
        return sharedPreferences.getString("skin_type", null)
    }

    fun setSkinType(skinType: String) {
        editor.putString("skin_type", skinType)
        editor.apply()
    }

    fun getProfileImageUri(): String? {
        return sharedPreferences.getString("profile_image_uri", null)
    }

    fun setProfileImageUri(uri: String) {
        editor.putString("profile_image_uri", uri)
        editor.apply()
    }

    fun getSelectedSkinType(): String? {
        return sharedPreferences.getString("selected_skin_type", null)
    }

    fun setSelectedSkinType(skinType: String) {
        editor.putString("selected_skin_type", skinType)
        editor.apply()
    }
}

private fun Repository.getSharedPreferences(s: String, modePrivate: Int): SharedPreferences {

    return this.getSharedPreferences(s, modePrivate)
}

