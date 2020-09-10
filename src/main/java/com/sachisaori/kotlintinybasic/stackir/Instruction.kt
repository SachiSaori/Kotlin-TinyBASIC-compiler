package com.sachisaori.kotlintinybasic.stackir

import java.util.*

class Instruction {
    private val opcode: Opcode
    private val stringOperand: Optional<String>
    private val integerOperand: Optional<Int>

    constructor(opcode: Opcode) {
        this.opcode = opcode
        this.stringOperand = Optional.empty()
        this.integerOperand = Optional.empty()
    }

    constructor(opcode: Opcode, operand: String) {
        this.opcode = opcode
        this.stringOperand = Optional.of(operand)
        this.integerOperand = Optional.empty()
    }

    constructor(opcode: Opcode, operand: Int) {
        this.opcode = opcode
        this.stringOperand = Optional.empty()
        this.integerOperand = Optional.of(operand)
    }

    fun getOpcode(): Opcode {
        return opcode
    }

    fun getStringOperand(): Optional<String> {
        return stringOperand
    }

    fun getIntegerOperand(): Optional<Int> {
        return integerOperand
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that: Instruction = other as Instruction
        if (integerOperand != that.integerOperand) return false
        if (opcode != that.opcode) return false
        if (stringOperand != that.stringOperand) return false
        return true
    }

    override fun hashCode(): Int {
        return Objects.hash(opcode, stringOperand, integerOperand)
    }

    override fun toString(): String {
        return when {
            stringOperand.isPresent -> "$opcode ${stringOperand.get()}"
            integerOperand.isPresent -> "$opcode ${integerOperand.get()}"
            else -> opcode.toString()
        }
    }
}