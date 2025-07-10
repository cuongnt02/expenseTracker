package com.ntc.expenseTracker

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.associate
import com.github.ajalt.clikt.parameters.options.help
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.validate
import com.github.ajalt.clikt.parameters.types.enum
import com.github.ajalt.mordant.terminal.Terminal
import com.ntc.expenseTracker.model.ExpenseField
import com.ntc.expenseTracker.utils.evaluatePredicate

class View : CliktCommand() {
    val sort by option().enum<ExpenseField> { it -> it.name.lowercase() }
        .help { "Option to sort by each field <amount|description|category>" }
    val filter: Map<String, String>? by option().associate().help { "Filter key to filter values" }
        .validate {
            it.keys.forEach { key ->
                require(key in ExpenseField.entries.map { entry -> entry.toString().lowercase() }) {
                    "Invalid key: $key, please check the manual for filter keys"
                }
            }
        }

    override fun run() {
        val t = Terminal()
        val expenses = run {
            t.println("Command run")
            var expenseList = ExpenseTrack.expenses
            sort?.let { sortOption ->
                expenseList = when (sortOption) {
                    ExpenseField.AMOUNT -> expenseList.sortedBy { it.amount }
                    ExpenseField.DESCRIPTION -> expenseList.sortedBy { it.description }
                    ExpenseField.CATEGORY -> expenseList.sortedBy { it.category }
                }
            }

            filter?.let { filterOptions ->
                filterOptions.forEach { entry, value ->
                    expenseList = when (ExpenseField.valueOf(entry.uppercase())) {
                        ExpenseField.AMOUNT -> expenseList.filter {
                            evaluatePredicate(value, it.amount)
                        }
                        ExpenseField.DESCRIPTION -> expenseList.filter { value.toRegex().find(it.description) != null }
                        ExpenseField.CATEGORY -> expenseList.filter {
                            if (it.category != null) value.toRegex().find(it.category) != null else false
                        }
                    }
                }
            }

            expenseList
        }

        t.println(ExpenseTrack.view(expenses))
    }
}