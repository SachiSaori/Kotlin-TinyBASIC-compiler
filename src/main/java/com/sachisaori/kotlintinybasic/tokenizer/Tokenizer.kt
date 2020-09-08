package com.sachisaori.kotlintinybasic.tokenizer

import com.sachisaori.kotlintinybasic.tokenizer.Token.Type

import java.io.Closeable
import java.io.IOException
import java.io.Reader
import java.io.StringReader
import java.lang.StringBuilder

class Tokenizer:Closeable {

    private fun isWhitespace(ch: Int): Boolean {
        return Character.isWhitespace(ch)
    }

    private fun isDigit(ch: Int): Boolean {
        return ch >= '0'.toInt() && ch <= '9'.toInt()
    }

    private fun isAlpha(ch: Int): Boolean {
        return ch >= 'A'.toInt() && ch <= 'Z'.toInt()
    }

    private val reader: Reader
    
    constructor(str: String) {
        this.reader = StringReader(str)
    }

    constructor(reader: Reader) {
        this.reader = reader
    }

    @Throws(IOException::class)
    private fun peek(): Int {
        reader.mark(1)
        try {
            return reader.read()
        } finally {
            reader.reset()
        }
    }

    @Throws(IOException::class)
    private fun nextStringToken(): Token {
        val buf = StringBuilder()
        while (true) {
            val ch: Int = reader.read()
            if (ch == -1) {
                throw IOException("Unexpected EOF within string")
            }
            else if (ch == '"'.toInt()) {
                break
            }
            buf.append(ch.toChar())
        }

        return Token(Type.STRING, buf.toString())
    }

    @Throws(IOException::class)
    fun nextToken(): Token {
        while (true) {
            val ch = reader.read()
            when(ch) {
                -1 -> return Token(Type.EOF)
                '\n'.toInt() -> return Token(Type.LF)
                '+'.toInt() -> return Token(Type.PLUS)
                '-'.toInt() -> return Token(Type.MINUS)
                '*'.toInt() -> return Token(Type.MULT)
                '/'.toInt() -> return Token(Type.DIV)
                '('.toInt() -> return Token(Type.LPAREN)
                ')'.toInt() -> return Token(Type.RPAREN)
                ','.toInt() -> return Token(Type.COMMA)
                '"'.toInt() -> return nextStringToken()
                '='.toInt() -> return Token(Type.EQ)
                '>'.toInt(), '<'.toInt() -> return nextRelationalOperatorToken(ch)
                else -> {
                    if (isAlpha(ch) && !isAlpha(peek())) {
                        return Token(Type.VAR, String(charArrayOf(ch.toChar())))
                    }
                    else if (isAlpha(ch)) {
                        return nextKeywordToken(ch)
                    }
                    else if (isDigit(ch)) {
                        return nextNumberToken(ch)
                    }
                    else if (!isWhitespace(ch)) {
                        throw IOException("Unexpected char: $ch")
                    }
                }
            }
        }
    }

    private fun nextNumberToken(first: Int): Token {
        val buf = StringBuilder()
        buf.append(first.toChar())
        while (true) {
            val ch = peek()
            if (!isDigit(ch)) {
                break
            }
            reader.skip(1)
            buf.append(ch.toChar())
        }
    return Token(Type.NUMBER, buf.toString())
    }

    @Throws(IOException::class)
    private fun nextKeywordToken(first: Int): Token {
        val buf = StringBuilder()
        buf.append(first.toChar())
        while (true) {
            val ch = peek()
            if (!isAlpha(ch)) {
                break
            }
            reader.skip(1)
            buf.append(ch.toChar())
        }
    return Token(Type.KEYWORD, buf.toString())
    }

    private fun nextRelationalOperatorToken(first: Int): Token {
        val second: Int = peek()
        if (first == '>'.toInt()) {
            return when (second) {
                '<'.toInt() -> {
                    reader.skip(1)
                    Token(Type.NE)
                }
                '='.toInt() -> {
                    reader.skip(1)
                    Token(Type.GTE)
                }
                else -> {
                    Token(Type.GT)
                }
            }
        } else {
            assert(first == '<'.toInt())

            return when (second) {
                '>'.toInt() -> {
                    reader.skip(1)
                    Token(Type.NE)
                }
                '='.toInt() -> {
                    reader.skip(1)
                    Token(Type.LTE)
                }
                else -> {
                    Token(Type.LT)
                }
            }
        }
    }

    @Throws(IOException::class)
    override fun close() {
        reader.close()
    }

}