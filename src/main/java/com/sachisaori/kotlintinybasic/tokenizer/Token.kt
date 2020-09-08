package com.sachisaori.kotlintinybasic.tokenizer

import java.util.Objects
import java.util.Optional

class Token {
    private val type: Type
    private val value: Optional<String>

    enum class Type {
        EOF,
        LF,
        VAR,
        KEYWORD,
        NUMBER,
        STRING,
        PLUS,
        MINUS,
        MULT,
        DIV,
        LPAREN,
        RPAREN,
        EQ,
        NE,
        GT,
        GTE,
        LT,
        LTE,
        COMMA
    }

    constructor(type: Type) {
        this.type = type
        this.value = Optional.empty()
    }

    constructor(type: Type, value: String) {
        this.type = type
        this.value = Optional.of(value)
    }

    fun getType(): Type {
        return type
    }
    
    fun getValue(): Optional<String> {
        return value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val token = other as Token
        if (type != token.type) return false
        if (value != token.value) return false
        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(type, value)
    }

    override fun toString(): String {
        return """${Token::class.java.simpleName} [type=$type, value=$value]"""
    }

}