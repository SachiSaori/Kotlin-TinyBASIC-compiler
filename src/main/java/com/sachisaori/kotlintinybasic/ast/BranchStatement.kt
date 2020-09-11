package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.util.*

class BranchStatement(private val type: BranchType, private val target: Int) : Statement() {

    fun getType(): BranchType {
        return type
    }

    fun getTarger(): Int {
        return target
    }

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: BranchStatement = other as BranchStatement

                when {
                    target != that.target -> return false
                    type != that.type -> return false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return Objects.hash(type, target)
    }

    override fun toString(): String {
        return "$type $target"
    }

    override fun compile(seq: InstructionSequence) {

        val opcode: Opcode = when (type) {
            BranchType.GOTO -> Opcode.JMP
            BranchType.GOSUB -> Opcode.CALL
        }
        seq.append(Instruction(
                opcode,
                seq.createLineLabel(target)
        ))
    }
}