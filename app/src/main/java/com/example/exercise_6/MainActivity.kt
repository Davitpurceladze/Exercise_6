package com.example.exercise_6

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.green
import com.example.exercise_6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var users = mutableMapOf<String, User>()
    private var toggleDeleteUserContainer: Boolean = false
    private var deletedUsers: Int = 0

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firstNameFocusListener()
        lastNameFocusListener()
        emailFocusListener()
        ageFocusListener()


        binding.btnAddUser.setOnClickListener {
            if(validFirstName() == "Success" && validLastName() == "Success" && validEmail() == "Success" && validAge() == "Success") {
                addUser()
            }
        }

        binding.btnRemoveUser.setOnClickListener {
            if(toggleDeleteUserContainer) {
                binding.deleteUserContainer.visibility = View.GONE
            } else {
                binding.deleteUserContainer.visibility = View.VISIBLE
            }
            toggleDeleteUserContainer = !toggleDeleteUserContainer
        }

        binding.btnDeleteUser.setOnClickListener {
            deleteUser()
        }

        binding.btnUpdateUser.setOnClickListener {
            updateUser()
        }

    }

    private fun updateUser() {
        val firstNameValue = binding.firstNameEditText.text.toString()
        val lastNameValue = binding.lastNameEditText.text.toString()
        val emailValue = binding.emailEditText.text.toString()
        val ageValue = binding.ageEditText.text.toString()

        if(users.keys.contains(emailValue)) {
            users[emailValue] = User(firstNameValue, lastNameValue, emailValue, ageValue)
            Toast.makeText(this, "User: $emailValue updated successfully", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "User with $emailValue doesn't exist", Toast.LENGTH_LONG).show()
        }
    }

    private fun deleteUser() {
        val userEmail = binding.etDeleteUser.text.toString()
        if(userEmail.isEmpty()) {
            Toast.makeText(this, "Enter user email to delete it", Toast.LENGTH_LONG).show()
        } else {
            if(users.keys.contains(userEmail)){
                users.remove(userEmail)
                Toast.makeText(this, "User deleted successfully", Toast.LENGTH_LONG).show()
                deletedUsers++
                binding.tvDeletedUsers.text = "Deleted Users: $deletedUsers"
            } else {
                Toast.makeText(this, "User with $userEmail doesn't exist", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun printUser() {
        println(users.keys)
    }
    private fun addUser() {
        val firstNameValue = binding.firstNameEditText.text.toString()
        val lastNameValue = binding.lastNameEditText.text.toString()
        val emailValue = binding.emailEditText.text.toString()
        val ageValue = binding.ageEditText.text.toString()

        if(users.keys.contains(emailValue)) {
            Toast.makeText(this, "User with $emailValue already exist", Toast.LENGTH_LONG).show()
        } else {
            val newUser = User(firstNameValue, lastNameValue, emailValue, ageValue)
            users.put(emailValue, newUser)
            Toast.makeText(this, "User added successfully", Toast.LENGTH_LONG).show()
            binding.tvActiveUsers.text = "Active Users: ${users.size}"

        }
    }

    private  fun firstNameFocusListener() {
        binding.firstNameEditText.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.firstNameContainer.helperText = validFirstName()
            }
        }
    }
    private fun validFirstName(): String {

        val firstNameText = binding.firstNameEditText.text.toString()
        if(firstNameText.isEmpty()) {
            val helperTextColor = ContextCompat.getColor(this, R.color.error)
            binding.firstNameContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
            return "Error, field have to be filled"
        }
        val helperTextColor = ContextCompat.getColor(this, R.color.success)
        binding.firstNameContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
        return "Success"
    }

    private  fun lastNameFocusListener() {
        binding.lastNameEditText.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.lastNameContainer.helperText = validLastName()
            }
        }
    }
    private fun validLastName(): String {
        val lastNameText = binding.lastNameEditText.text.toString()
        if(lastNameText.isEmpty()) {
            val helperTextColor = ContextCompat.getColor(this, R.color.error)
            binding.lastNameContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
            return "Error, field have to be filled"
        }
        val helperTextColor = ContextCompat.getColor(this, R.color.success)
        binding.lastNameContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
        return "Success"
    }

    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }
    private fun validEmail(): String {
        val emailText = binding.emailEditText.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            val helperTextColor = ContextCompat.getColor(this, R.color.error)
            binding.emailContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
            return "Error, invalid email format, example: example@gmail.com"
        }

        val helperTextColor = ContextCompat.getColor(this, R.color.success)
        binding.emailContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
        return "Success"
    }

    private  fun ageFocusListener() {
        binding.ageEditText.setOnFocusChangeListener { _, focused ->
            if(!focused) {
                binding.ageContainer.helperText = validAge()
            }
        }
    }
    private fun validAge(): String {
        val ageText = binding.ageEditText.text.toString()
        if(ageText.isEmpty()) {
            val helperTextColor = ContextCompat.getColor(this, R.color.error)
            binding.ageContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
            return "Error, field have to be filled"
        }
        val helperTextColor = ContextCompat.getColor(this, R.color.success)
        binding.ageContainer.setHelperTextColor(ColorStateList.valueOf(helperTextColor))
        return "Success"
    }



}