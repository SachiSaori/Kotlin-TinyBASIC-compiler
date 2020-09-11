package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.util.*

class ImmediateExpression(private val value: Int): Expression() {
    fun getValue(): Int = value

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: ImmediateExpression = other as ImmediateExpression

                return when {
                    value != that.value -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return value
    }

    override fun toString(): String {
        return value.toString()
    }

    override fun compile(seq: InstructionSequence) {
        seq.append(Instruction(Opcode.PUSHI, value))
    }
}