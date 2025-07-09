package com.ntc.expenseTracker

import com.github.ajalt.mordant.rendering.BorderType.Companion.SQUARE_DOUBLE_SECTION_SEPARATOR
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.Companion.rgb
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.rendering.TextStyles
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.Table
import com.github.ajalt.mordant.table.table
import com.ntc.expenseTracker.model.Expense
import com.ntc.expenseTracker.utils.readJSON
import com.ntc.expenseTracker.utils.writeJSON
import kotlinx.serialization.json.Json
import okio.FileNotFoundException
import okio.Path.Companion.toPath

object ExpenseTrack {
    var expenses = listOf<Expense>()
    var json = ""

    init {
        try {
            json = readJSON("expenses.json".toPath())
        } catch (_: FileNotFoundException) {
            store()
            json = readJSON("expenses.json".toPath())
        } finally {
            expenses = Json.decodeFromString(json)
        }
    }

    fun addExpense(amount: Double, description: String, category: String? = null) {
        expenses += Expense(amount, description, category)
        store()
    }

    fun store() {
        writeJSON("expenses.json".toPath()) { Json.encodeToString(expenses) }
    }

    fun view(): Table {
        return table {
            borderType = SQUARE_DOUBLE_SECTION_SEPARATOR
            borderStyle = rgb("#4b25b9")
            align = TextAlign.RIGHT
            tableBorders = Borders.ALL
            header {
                style = TextColors.brightRed + TextStyles.bold
                row {
                    cellBorders = Borders.BOTTOM
                    cells("No.", "Amount", "Description", "Category")
                }
            }
            body {
                style = TextColors.green
                cellBorders = Borders.TOP_BOTTOM
                rowStyles(TextStyle(), TextStyles.dim.style)
                for ((index, expense) in expenses.withIndex()) {
                    row(index, expense.amount, expense.description, expense.category ?: "")
                }
            }
            captionBottom("Your expenses based on inputs")
        }
    }
}