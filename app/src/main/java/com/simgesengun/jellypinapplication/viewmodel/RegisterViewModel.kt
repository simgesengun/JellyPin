package com.simgesengun.jellypinapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simgesengun.jellypinapplication.repo.UserDaoRepository
import com.simgesengun.jellypinapplication.validator.EmailValidator
import com.simgesengun.jellypinapplication.validator.NameSurnameValidator
import com.simgesengun.jellypinapplication.validator.PasswordValidator
import com.simgesengun.jellypinapplication.validator.PhoneNumberValidator

class RegisterViewModel : ViewModel() {

    val udaor = UserDaoRepository()

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val nameSurname = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()

    val emailError = MutableLiveData<Int>()
    val passwordError = MutableLiveData<Int>()
    val nameSurnameError = MutableLiveData<Int>()
    val phoneNumberError = MutableLiveData<Int>()

    val success = MutableLiveData<Int>()

    private val passwordValidator = PasswordValidator()
    private val emailValidator = EmailValidator()
    private val nameSurnameValidator = NameSurnameValidator()
    private val phoneNumberValidator = PhoneNumberValidator()

    fun onSignupButtonClick() {
        emailError.value = emailValidator.validate(email.value)
        passwordError.value = passwordValidator.validate(password.value)
        nameSurnameError.value = nameSurnameValidator.validate(nameSurname.value)
        phoneNumberError.value = phoneNumberValidator.validate(phoneNumber.value)
        if(emailError.value == null && passwordError.value == null && nameSurnameError.value == null && phoneNumberError.value == null){
            newUser(email.value!!, password.value!!, nameSurname.value!!, phoneNumber.value!!)
            success.value = 1
        }
    }

    fun newUser(mail_address : String, password : String, name_surname : String, phone_number : String){
        udaor.newUser(mail_address, password, name_surname, phone_number)
    }
}