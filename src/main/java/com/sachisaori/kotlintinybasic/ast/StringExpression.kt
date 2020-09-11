package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.InstructionSequence

abstract class StringExpression {
    abstract fun compile(seq: InstructionSequence)
}