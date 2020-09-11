package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.Instruction
import com.sachisaori.kotlintinybasic.stackir.InstructionSequence
import com.sachisaori.kotlintinybasic.stackir.Opcode
import java.util.*

class IfStatement(private val operator: RelationalOperator, private val leftExpression: Expression, private val rightExpression: Expression, private val statement: Statement) : Statement() {
    fun getOperator(): RelationalOperator = operator
    fun getLeftExpression(): Expression = leftExpression
    fun getRightExpression(): Expression = rightExpression
    fun getStatement(): Statement = statement

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other === null || javaClass != other.javaClass -> false
            else -> {
                val that: IfStatement = other as IfStatement

                return when {
                    leftExpression != that.leftExpression -> false
                    operator != that.operator -> false
                    rightExpression != that.rightExpression -> false
                    statement != that.statement -> false
                    else -> true
                }

            }
        }

    }

    override fun hashCode(): Int {
        return Objects.hash(operator, leftExpression, rightExpression, statement)
    }

    override fun toString(): String {
        return "IF $leftExpression $operator $rightExpression THEN $statement"
    }

    override fun compile(seq: InstructionSequence) {
        leftExpression.compile(seq)
        rightExpression.compile(seq)

        val thenLabel: String = seq.createGeneratedLabel()
        val endLabel: String = seq.createGeneratedLabel()

        val opcode: Opcode = when (operator) {
            RelationalOperator.EQ -> Opcode.JMPEQ
            RelationalOperator.NE -> Opcode.JMPNE
            RelationalOperator.LTE -> Opcode.JMPLTE
            RelationalOperator.LT -> Opcode.JMPLT
            RelationalOperator.GT -> Opcode.JMPGT
            RelationalOperator.GTE -> Opcode.JMPGTE
        }
        seq.append(
                Instruction(opcode, thenLabel),
                Instruction(Opcode.JMP, endLabel),
                Instruction(Opcode.LABEL, thenLabel)
        )
        statement.compile(seq)

        seq.append(Instruction(Opcode.LABEL, endLabel))
    }
}