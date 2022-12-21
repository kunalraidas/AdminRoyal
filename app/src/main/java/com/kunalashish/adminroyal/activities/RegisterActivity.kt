package com.kunalashish.adminroyal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.kunalashish.adminroyal.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailFocusListner()
        PasswordFocusListner()

        binding.btnRegister.setOnClickListener {

            submitForm()
        }
        binding.haveAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun submitForm() {
        val validEmail = binding.EmailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null

        if (validEmail && validPassword)
        {
            reserForm()
        }
        else
        {
            invalidForm()
        }
     }

    private fun reserForm() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun invalidForm() {
        var message = ""
        if (binding.EmailContainer.helperText == null) {
            message += "\n \n Email : " + binding.EmailContainer.helperText
        }
        if (binding.passwordContainer.helperText == null){
            message += "\n \n Password" + binding.passwordContainer.helperText
        }

        AlertDialog.Builder(
            this
        ).setTitle("Please Full fill Details")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_->
                // do nothing
            }

    }

    private fun emailFocusListner()
    {
        binding.EdtEmail.setOnFocusChangeListener { _, focused ->

            if(!focused){
                binding.EmailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail() : String?{
            val emailText = binding.EdtEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }
        return null
    }
    
    
    private fun PasswordFocusListner()
    {
        binding.edtPassword.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.passwordContainer.helperText = validPassword()
            }

        }
    }

    private fun validPassword(): String? {
        val password = binding.edtPassword.text.toString()
        if (password.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if (password.length > 16)
        {
            return "Maximum 16 Character Password"
        }
        if (password.matches(".*/[A-Z].*".toRegex()))
        {
            return "Must Contain 1 UpperCase Letter"
        }
        if (password.matches(".*/[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lowercase Letter"
        }
        if (password.matches(".*/[@#$%^&+-].*".toRegex()))
        {
            return "Must Contain 1 Special Character"
        }

        return null
    }
}