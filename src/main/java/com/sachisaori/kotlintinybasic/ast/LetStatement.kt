package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.util.*

class LetStatement(private val name:  String, private val value: Expression): Statement() {
    fun getName(): String = name
    fun getValue(): Expression = value

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: LetStatement = other as LetStatement

                when {
                    name != that.name -> false
                    value != that.value -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return Objects.hash(name, value)
    }

    override fun toString(): String {
        return "LET $name = $value"
    }

    override fun compile(seq: InstructionSequence) {
        value.compile(seq)
        seq.append(Instruction(Opcode.STORE, name))
    }
}