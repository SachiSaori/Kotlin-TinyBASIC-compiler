package com.sachisaori.kotlintinybasic.ast

enum class UnaryOperator(private val char: Char) {
    PLUS('+'),
    MINUS('-');

    fun toString(): String = char.toString()
}