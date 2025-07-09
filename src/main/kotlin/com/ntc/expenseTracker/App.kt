package com.ntc.expenseTracker

import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands

class App {
    companion object {
        @JvmStatic
        fun main(args: Array<String>): Unit = ExpenseTracker().subcommands(Add(), View()).main(args)
    }
}

