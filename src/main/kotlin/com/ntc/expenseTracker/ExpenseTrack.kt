package com.ntc.expenseTracker

import com.ntc.expenseTracker.model.Expense

object ExpenseTrack {
    var expenses = emptyList<Expense>()

    fun addExpense(amount: Double, description: String, category: String? = null) {
        expenses += Expense(amount, description, category)
    }
}