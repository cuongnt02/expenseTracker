package com.ntc.expenseTracker.model

import kotlinx.serialization.Serializable

@Serializable
data class Expense(
    val amount: Double,
    val description: String,
    val category: String? = null
)
