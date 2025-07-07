package com.ntc.expenseTracker.model

data class Expense(
    val amount: Double,
    val description: String,
    val category: String? = null
)
