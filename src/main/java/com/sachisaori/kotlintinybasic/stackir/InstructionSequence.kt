package com.sachisaori.kotlintinybasic.stackir

import java.util.*

class InstructionSequence {
    private val instructions: MutableList<Instruction> = ArrayList()
    private var labelCounter: Int = 0

    fun createLineLabel(line: Int): String {
        return "line_$line"
    }
    fun createGeneratedLabel(): String {
        return "generated_${labelCounter++}"
    }
    fun append(vararg instructions: Instruction): Unit {
        this.instructions.addAll(instructions)
    }
    fun getInstructions(): List<Instruction> {
        return Collections.unmodifiableList(instructions)
    }
}
