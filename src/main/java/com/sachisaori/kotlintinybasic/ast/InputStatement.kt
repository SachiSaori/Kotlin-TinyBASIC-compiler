package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.lang.StringBuilder
import java.util.*

class InputStatement(private var names: List<String>): Statement() {
    constructor(vararg names: String) {
        this.names = Collections.unmodifiableList(names.toList())
    }

    fun getNames(): List<String> = names

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: InputStatement = other as InputStatement

                when {
                    names != that.names -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return names.hashCode()
    }

    override fun toString(): String {
        val buf: StringBuilder = StringBuilder("INPUT ")
        for (i in names.indices) {
            buf.append(names[i])
            if (i != (names.size-1)) {
                buf.append(", ")
            }
        }
        return buf.toString()
    }

    override fun compile(seq: InstructionSequence) {
        for (name in names) {
            seq.append(Instruction(Opcode.IN), Instruction(Opcode.STORE, name))
        }
    }
}