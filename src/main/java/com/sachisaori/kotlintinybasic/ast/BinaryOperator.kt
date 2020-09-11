package com.sachisaori.kotlintinybasic.ast

import java.io.*

enum class BinaryOperator(private val character: Char) {
    PLUS('+'),
    MINUS('-'),
    MULT('*'),
    DIV('/');

    override fun toString(): String {
        return character.toString()
    }
}