package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.util.*

class BinaryExpression(private val operator: BinaryOperator, private val leftExpression: Expression, private val rightExpression: Expression) : Expression(){

    fun getOperator(): BinaryOperator {
        return operator
    }

    fun getLeftExpression(): Expression {
        return leftExpression
    }

    fun getRightExpression(): Expression {
        return rightExpression
    }

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: BinaryExpression = other as BinaryExpression

                when {
                    leftExpression != that.leftExpression -> false
                    operator != that.operator -> false
                    rightExpression != that.rightExpression -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return Objects.hash(operator, leftExpression, rightExpression)
    }

    override fun toString(): String {
        return "($leftExpression $operator $rightExpression)"
    }

    override fun compile(seq: InstructionSequence): Unit {
        leftExpression.compile(seq)
        rightExpression.compile(seq)
        when (operator) {
            BinaryOperator.PLUS -> seq.append(Instruction(Opcode.ADD))
            BinaryOperator.MINUS -> seq.append(Instruction(Opcode.SUB))
            BinaryOperator.MULT -> seq.append(Instruction(Opcode.MUL))
            BinaryOperator.DIV -> seq.append(Instruction(Opcode.DIV))
        }
    }
}