package com.sachisaori.kotlintinybasic.ast

enum class RelationalOperator(private val string: String) {
    EQ("="),
    NE("<>"),
    GT(">"),
    GTE(">="),
    LT("<"),
    LTE("<=");

    fun toString(): String {
        return string
    }
}