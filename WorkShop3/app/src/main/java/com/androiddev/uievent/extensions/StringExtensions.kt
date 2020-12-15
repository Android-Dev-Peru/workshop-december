package com.androiddev.uievent.extensions

fun String.isEmail(): Boolean {
    val emailRegex =
            Regex("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")
    return this.contains(".") && this.trim().length > 5 && emailRegex.matches(this.trim())
}

fun String.isThereUppercase() : Boolean {
    val uppercaseRegex = Regex(".*[A-Z].*")
    return uppercaseRegex.matches(this.trim())
}

fun String.isThereLowercase() : Boolean {
    val uppercaseRegex = Regex(".*[a-z].*")
    return uppercaseRegex.matches(this.trim())
}


fun String.isThereNumber() : Boolean {
    val uppercaseRegex = Regex(".*\\d.*")
    return uppercaseRegex.matches(this.trim())
}