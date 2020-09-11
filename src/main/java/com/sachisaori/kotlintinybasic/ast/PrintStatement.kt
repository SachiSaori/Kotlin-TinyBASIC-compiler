package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.lang.StringBuilder
import java.util.*

class PrintStatement(private var values: List<StringExpression>):Statement() {
    constructor(vararg values: StringExpression) {
        this.values = Collections.unmodifiableList(values.toList())
    }

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: PrintStatement = other as PrintStatement

                when {
                    values != that.values -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    override fun toString(): String {
        val buf: StringBuilder = StringBuilder("PRINT ")
        for (i in values.indices) {
            buf.append(values[i])
            if (i != (values.size-1)) {
                buf.append(", ")
            }
        }
        return buf.toString()
    }

    override fun compile(seq: InstructionSequence) {
        for (value in values) {
            value.compile(seq)
            seq.append(Instruction(if (value is ImmediateString) Opcode.OUTS else Opcode.OUTI))
        }
        seq.append(Instruction(Opcode.PUSHS, "\n"), Instruction(Opcode.OUTS))
    }
}