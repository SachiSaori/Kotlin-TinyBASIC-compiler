package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.util.*
import kotlin.math.exp

class UnaryExpression(private val operator: UnaryOperator, private val expression: Expression) : Expression() {
    fun getOperator(): UnaryOperator = operator
    fun getExpression(): Expression = expression

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: UnaryExpression = other as UnaryExpression

                when {
                    expression != that.expression -> false
                    operator != that.operator -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return Objects.hash(operator, expression)
    }

    override fun toString(): String {
        return "($operator ${expression})"
    }

    override fun compile(seq: InstructionSequence) {
        when (operator) {
            UnaryOperator.PLUS -> expression.compile(seq)
            UnaryOperator.MINUS -> {
                seq.append(Instruction(Opcode.PUSHI, 0))
                expression.compile(seq)
                seq.append(Instruction(Opcode.SUB))
            }
        }
    }
}