package com.io.tea.android.util

object ValidationUtil {

    fun isValidationPhoneNumber(phoneNumber: String?): Boolean {
        if (phoneNumber == null) {
            return false
        }
        val regex = Regex("^[0-9]\\d{1,14}\$")
        return regex.matches(phoneNumber)
    }

    fun isValidationMailAddress(mailAddress: String?): Boolean {
        if (mailAddress == null) {
            return false
        }
        val regex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
        return regex.matches(mailAddress)
    }

    fun isValidationStringCount(input: String?, count: Int): Boolean {
        return input != null && input.length >= count
    }

    fun isValidationPasswordContainsRequired(input: String?): Boolean {
        if (input == null) {
            return false
        }
        var hasUpperCase = false
        var hasLowerCase = false
        var hasDigit = false
        for (char in input) {
            if (char.isUpperCase()) {
                hasUpperCase = true
            } else if (char.isLowerCase()) {
                hasLowerCase = true
            } else if (char.isDigit()) {
                hasDigit = true
            }
            if (hasUpperCase && hasLowerCase && hasDigit) {
                break
            }
        }
        return hasUpperCase && hasLowerCase && hasDigit
    }
}