package com.sachisaori.kotlintinybasic.stackir

enum class Opcode {
    PUSHS,
    PUSHI,
    LOAD,
    STORE,
    ADD,
    MUL,
    SUB,
    DIV,
    CALL,
    RET,
    JMP,
    JMPGT,
    JMPGTE,
    JMPLT,
    JMPLTE,
    JMPNE,
    JMPEQ,
    HLT,
    IN,
    OUTS,
    OUTI,
    LABEL
}