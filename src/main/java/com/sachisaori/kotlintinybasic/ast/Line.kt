package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.util.*

class Line(private val number: Int, private val statement: Statement) {
    fun getNumber(): Int = number
    fun getStatement(): Statement = statement

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val line: Line = other as Line

                when {
                    number != line.number -> false
                    statement != line.statement -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return Objects.hash(number, statement)
    }

    override fun toString(): String {
        return "$number $statement"
    }

    fun compile(seq: InstructionSequence):  Unit {
        seq.append(Instruction(Opcode.LABEL, seq.createLineLabel(number)))
        statement.compile(seq)
    }
}