package com.ntc.expenseTracker

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.Context

class ExpenseTracker: CliktCommand() {
    override fun help(context: Context): String = """
        This application manage expenses
        
        Available commands:
        add
        view
    """.trimIndent()
    override fun run() {
        echo("")
    }
}

