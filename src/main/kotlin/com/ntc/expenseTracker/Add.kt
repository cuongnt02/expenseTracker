package com.ntc.expenseTracker

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.check
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.double

class Add: CliktCommand() {
    val amount by option().double().required().help("The amount spent <double>").check("The amount should not be negative") { it -> it >= 0 }
    val description by option().required().help { "The description of the expense <string>" }
    val category by option().help { "The spending area of the expense <string>" }

    override fun run() {
        ExpenseTrack.addExpense(amount, description, category)
        echo("Spent $amount on $description${if (category != null) ", this would be categorized as $category" else ""}")
    }


}