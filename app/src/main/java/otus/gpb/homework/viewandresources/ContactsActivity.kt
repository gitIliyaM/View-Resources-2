package otus.gpb.homework.viewandresources

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class ContactsActivity : AppCompatActivity() {

    private lateinit var dateInputEditText: TextInputEditText
    private lateinit var phoneTypeAutoComplete: AutoCompleteTextView
    private lateinit var themeToggleButton: View
    private companion object {
        const val PREFS_NAME = "ThemePrefs"
        const val KEY_DARK_THEME = "dark_theme"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        dateInputEditText = findViewById(R.id.date_input_edit_text)
        phoneTypeAutoComplete = findViewById(R.id.phone_type_autocomplete)
        themeToggleButton = findViewById(R.id.theme_toggle_button)

        dateInputEditText.setOnClickListener {
            showDatePicker()
        }

        setupPhoneTypeSpinner()

        themeToggleButton.setOnClickListener {
            toggleTheme()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = android.app.DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, selectedDayOfMonth)
                }
                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)

                dateInputEditText.setText(formattedDate)
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.show()
    }

    private fun setupPhoneTypeSpinner() {
        val phoneTypes = arrayOf("Mobile", "Home", "Work", "Fax", "Other")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, phoneTypes)

        phoneTypeAutoComplete.setAdapter(adapter)
    }

    private fun toggleTheme() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean(KEY_DARK_THEME, false)

        val newNightMode = if (isDarkTheme) {
            AppCompatDelegate.MODE_NIGHT_NO
        } else {
            AppCompatDelegate.MODE_NIGHT_YES
        }

        with(sharedPreferences.edit()) {
            putBoolean(KEY_DARK_THEME, newNightMode == AppCompatDelegate.MODE_NIGHT_YES)
            apply()
        }

        AppCompatDelegate.setDefaultNightMode(newNightMode)

        recreate()
    }

    fun onBackPressed(view: View) {
        finish()
    }
    private fun applyTheme() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean(KEY_DARK_THEME, false)

        val mode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}