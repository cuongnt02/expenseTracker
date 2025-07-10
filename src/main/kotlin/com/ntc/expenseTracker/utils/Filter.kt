package com.ntc.expenseTracker.utils

fun evaluatePredicate(predicate: String, testValue: Double): Boolean {
    // Clean predicate
    val cleanPredicate = predicate.replace(" ", "").lowercase()

    val operators = listOf(">=", "<=", "==", "!=", ">", "<")
    val operator = operators.find { cleanPredicate.contains(it) }
        ?: throw IllegalArgumentException("Invalid operator passed")

    val parts = cleanPredicate.split(operator)
    if (parts.size != 2) {
        throw IllegalArgumentException("Invalid predicate format")
    }

    val value = parts[1].toDoubleOrNull()
        ?: throw IllegalArgumentException("Invalid numeric value")


    return when(operator) {
        ">=" -> testValue >= value
        "<=" -> testValue <= value
        "==" -> testValue == value
        "!=" -> testValue != value
        ">" -> testValue > value
        "<" -> testValue < value
        else -> false
    }
}