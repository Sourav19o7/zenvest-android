package com.zenvest.core.common.extensions

import java.text.NumberFormat
import java.util.Locale

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailRegex.matches(this)
}

fun String.isValidPhone(): Boolean {
    val phoneRegex = Regex("^[6-9]\\d{9}$")
    return phoneRegex.matches(this)
}

fun String.isValidOtp(): Boolean {
    return this.length == 6 && this.all { it.isDigit() }
}

fun String.maskEmail(): String {
    if (!this.contains("@")) return this
    val parts = this.split("@")
    val localPart = parts[0]
    val domain = parts[1]
    val maskedLocal = if (localPart.length > 2) {
        "${localPart.first()}${"*".repeat(localPart.length - 2)}${localPart.last()}"
    } else {
        localPart
    }
    return "$maskedLocal@$domain"
}

fun String.maskPhone(): String {
    if (this.length != 10) return this
    return "${this.take(2)}${"*".repeat(6)}${this.takeLast(2)}"
}

fun Number.formatAsINR(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("en", "IN"))
    format.maximumFractionDigits = 0
    return format.format(this)
}

fun Number.formatAsPercentage(): String {
    return "${this}%"
}

fun Double.formatDecimal(decimalPlaces: Int = 2): String {
    return String.format(Locale.getDefault(), "%.${decimalPlaces}f", this)
}
