package com.sachisaori.kotlintinybasic.ast

import com.sachisaori.kotlintinybasic.stackir.InstructionSequence

abstract class Statement {
    abstract fun compile(seq: InstructionSequence)
}