package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode

class VariableExpression(private val name: String): Expression() {
    fun getName(): String = name

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val variable: VariableExpression = other as VariableExpression

                when {
                    name != variable.name -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    override fun toString(): String {
        return name
    }

    override fun compile(seq: InstructionSequence) {
        seq.append(Instruction(Opcode.LOAD, name))
    }
}