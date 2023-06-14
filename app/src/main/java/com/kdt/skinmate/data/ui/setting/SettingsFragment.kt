package com.kdt.skinmate.data.ui.setting

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kdt.skinmate.R
import java.util.*

@Suppress("DEPRECATION")
class SettingsFragment : Fragment() {

    private lateinit var pref: SettingsViewModel
    private lateinit var imgProfile: ImageView
    private lateinit var tvEmail: TextView
    private lateinit var tvNama: EditText
    private lateinit var switchReminder: Switch
    private lateinit var btnSelectTime: Button
    private lateinit var tvSkinType: TextView

    private var selectedTime: String = ""
    private var selectedSkinType: String = ""
    private val GALLERY_REQUEST_CODE = 1001

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        imgProfile = view.findViewById(R.id.imgProfile)
        tvEmail = view.findViewById(R.id.tv_email)
        tvNama = view.findViewById(R.id.tv_name)
        switchReminder = view.findViewById(R.id.switch_mode)
        btnSelectTime = view.findViewById(R.id.btnSelectTime)
        tvSkinType = view.findViewById(R.id.tvSkinType)

//        // Set data pengguna yang sudah ada sebelumnya
//        val userData = getUserDataFromPreferences()
//        tvEmail.text = userData.email
//        tvNama.setText(userData.name)
//
//        // Tampilkan foto profil pengguna
//        val profileImageUri = getProfileImageUriFromPreferences()
//        Glide.with(this)
//            .load(profileImageUri)
//            .placeholder(R.drawable.profile)
//            .into(imgProfile)
//
//        // Tampilkan jenis kulit yang sudah dipilih sebelumnya
//        selectedSkinType = getSelectedSkinTypeFromPreferences()
//        tvSkinType.text = selectedSkinType

        // Handle klik foto profil

        imgProfile.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
        }
        // Handle klik keterangan jenis kulit
        tvSkinType.setOnClickListener {
            val skinTypes = arrayOf("Type A", "Type B", "Type C") // Daftar jenis kulit yang tersedia
            val selectedIndex = skinTypes.indexOf(selectedSkinType) // Index jenis kulit yang dipilih sebelumnya
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Select Skin Type")
                .setSingleChoiceItems(skinTypes, selectedIndex) { _, which ->
                    selectedSkinType = skinTypes[which]
                    tvSkinType.text = selectedSkinType
                    saveSelectedSkinTypeToPreferences(selectedSkinType)
                }
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .create()
            dialog.show()
        }
        // Handle klik tombol pilih jam pengingat
        btnSelectTime.setOnClickListener {
            showTimePickerDialog()
        }

        return view
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minuteOfDay ->
                val selectedHour = hourOfDay.toString().padStart(2, '0')
                val selectedMinute = minuteOfDay.toString().padStart(2, '0')
                selectedTime = "$selectedHour:$selectedMinute"
                // Tampilkan waktu yang dipilih pada button atau textview yang sesuai
                btnSelectTime.text = selectedTime
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

//    private fun getUserDataFromPreferences(): UserData {
//        val email = pref.getUserEmail().value ?: ""
//        val name = pref.getUserName().value ?: ""
//        val reminderTime = pref.getReminderTime().value ?: ""
//        val skinType = pref.getSkinType().value ?: ""
//        return UserData(email, name, reminderTime, skinType)
//    }
//
//    private fun getProfileImageUriFromPreferences(): Uri? {
//        val uriString = pref.getProfileImageUri().value ?: ""
//        return if (uriString.isNotEmpty()) Uri.parse(uriString) else null
//    }
//
//    private fun getSelectedSkinTypeFromPreferences(): String {
//        return pref.getSelectedSkinType().value ?: ""
//    }

    private fun saveUserDataToPreferences(userData: UserData) {
        pref.setUserEmail(userData.email)
        pref.setUserName(userData.name)
        userData.reminderTime?.let { pref.setReminderTime(it) }
        userData.skinType?.let { pref.setSkinType(it) }
    }

    private fun saveProfileImageUriToPreferences(imageUri: Uri?) {
        val uriString = imageUri?.toString() ?: ""
        pref.setProfileImageUri(uriString)
    }

    private fun saveSelectedSkinTypeToPreferences(skinType: String) {
        pref.setSelectedSkinType(skinType)
    }

    data class UserData(
        val email: String,
        val name: String,
        val reminderTime: String?,
        val skinType: String?
    )
}