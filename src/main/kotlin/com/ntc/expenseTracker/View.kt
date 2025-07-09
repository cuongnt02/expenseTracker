package com.ntc.expenseTracker

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.mordant.terminal.Terminal

class View: CliktCommand() {
    override fun run() {
        val t = Terminal()
        t.println(ExpenseTrack.view())
    }
}