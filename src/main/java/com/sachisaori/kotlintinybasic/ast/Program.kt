package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import java.lang.StringBuilder
import java.util.*

class Program(vararg lines: Line) {
    private var lines: List<Line> = Collections.unmodifiableList(lines.toList())

    constructor(lines: List<Line>) {
        this.lines = Collections.unmodifiableList(lines)
    }

    fun getLines(): List<Line> = lines

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val program: Program = other as Program

                when {
                    lines != program.lines -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return lines.hashCode()
    }

    override fun toString(): String {
        val buf: StringBuilder = StringBuilder()
        for (line in lines) {
            buf.append(line).append("\n")
        }
        return buf.toString()
    }

    fun compile(): InstructionSequence {
        val seq: InstructionSequence = InstructionSequence()
        for (line in lines) {
            line.compile(seq)
        }
        return seq
    }
}