package otus.gpb.homework.viewandresources

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private companion object {
        const val PREFS_NAME = "ThemePrefs"
        const val KEY_DARK_THEME = "dark_theme"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.contacts_button).setOnClickListener {
            startActivity(Intent(this, ContactsActivity::class.java))
        }

        findViewById<Button>(R.id.cart_button).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        findViewById<Button>(R.id.signin_button).setOnClickListener {
            showSignInDialog()
        }
    }

    private fun showSignInDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_signin, null)
        val emailInput = dialogView.findViewById<EditText>(R.id.email_input)

        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.login_dialog_title)
            .setView(dialogView)
            .setPositiveButton(R.string.sign_in_button_text) { _, _ ->
                val email = emailInput.text.toString()
                val intent = Intent(this, ContactsActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton(R.string.cancel_button_text, null)
            .show()
    }

    private fun applyTheme() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean(KEY_DARK_THEME, false)

        val mode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}