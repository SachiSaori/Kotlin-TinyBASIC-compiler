package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode

class ReturnStatement: Statement() {
    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> true
        }

    }

    override fun hashCode(): Int {
        return 0
    }

    override fun toString(): String {
        return "RETURN"
    }

    override fun compile(seq: InstructionSequence) {
        seq.append(Instruction(Opcode.RET))
    }
}