package com.kdt.skinmate.data.ui.onboarding

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kdt.skinmate.R
import java.text.SimpleDateFormat
import java.util.*


class OnboardingActivity : AppCompatActivity() {

    private lateinit var tvNameLabel: TextView
    private lateinit var etName: EditText
    private lateinit var tvGenderLabel: TextView
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var llCalendar: LinearLayout
    private lateinit var tvBirthdateLabel: TextView
    private lateinit var etBirthdate: EditText
    private lateinit var btnSelectBirthdate: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // Inisialisasi elemen-elemen UI

//        tvNameLabel = findViewById(R.id.tv_name_label)
//        edtName = findViewById(R.id.edt_nama)
//        tvGenderLabel = findViewById(R.id.tv_gender_label)
//        radioGroupGender = findViewById(R.id.radio_group_gender)
//        llCalendar = findViewById(R.id.ll_calendar)
//        tvBirthdateLabel = findViewById(R.id.tv_birthdate_label)
//        edtBirthdate = findViewById(R.id.edt_birthdate)
//        btnSelectBirthdate = findViewById(R.id.btn_select_birthdate)
//        btnNext = findViewById(R.id.btn_next)

        // Atur aksi klik untuk menampilkan kalender

//        btnSelectBirthdate.setOnClickListener {
//            llCalendar.visibility = View.VISIBLE
//            llCalendar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up))
//        }
//
//        // Atur aksi klik untuk tombol "Selanjutnya"
//        btnNext.setOnClickListener {
//            if (validateInput()) {
//                saveUserData()
//                navigateToNextScreen()
//            }
//        }
    }

//    private fun validateInput(): Boolean {
//        val name = etName.text.toString().trim()
//        val gender = when (radioGroupGender.checkedRadioButtonId) {
//            R.id.radioButtonMale -> "Laki-Laki"
//            R.id.radioButtonFemale -> "Perempuan"
//            else -> ""
//        }
//        val birthdate = etBirthdate.text.toString().trim()
//
//        if (name.isEmpty()) {
//            etName.error = "Nama harus diisi"
//            return false
//        }
//
//        if (gender.isEmpty()) {
//            Toast.makeText(this, "Pilih jenis kelamin", Toast.LENGTH_SHORT).show()
//            return false
//        }
//
//        if (birthdate.isEmpty()) {
//            etBirthdate.error = "Pilih tanggal lahir"
//            return false
//        }
//
//        return true
//    }
//
//    private fun saveUserData() {
//        val name = etName.text.toString().trim()
//        val gender = when (radioGroupGender.checkedRadioButtonId) {
//            R.id.radioButtonMale -> "Laki-Laki"
//            R.id.radioButtonFemale -> "Perempuan"
//            else -> ""
//        }
//        val birthdate = etBirthdate.text.toString().trim()
//
//        // Simpan data pengguna ke penyimpanan lokal (misalnya Shared Preferences atau Room Database)
//        // Implementasikan logika sesuai dengan kebutuhan aplikasi Anda
//    }
//
//    private fun navigateToNextScreen() {
//        // Navigasi ke halaman berikutnya setelah onboarding selesai
//        // Implementasikan logika navigasi sesuai dengan kebutuhan aplikasi Anda
//    }
}
