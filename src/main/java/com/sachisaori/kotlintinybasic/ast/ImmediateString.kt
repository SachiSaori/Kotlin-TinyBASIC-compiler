package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode

class ImmediateString(private val value: String):StringExpression() {
    fun getValue(): String = value
    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass === other.javaClass -> false
            else -> {
                val that: ImmediateString = other as ImmediateString

                when {
                    value != that.value -> false
                    else -> true
                }
            }
        }

    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return "\" $value \""
    }

    override fun compile(seq: InstructionSequence) {
        seq.append(Instruction(Opcode.PUSHS, value))
    }
}