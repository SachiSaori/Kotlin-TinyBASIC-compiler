package com.sachisaori.kotlintinybasic

import com.sachisaori.kotlintinybasic.tokenizer.Tokenizer

import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class TinyBasicCompiler {
    @Throws(IOException::class)
    fun main(args: List<String>) {
        val inputPath = Paths.get(args[0])
        val outputPath = Paths.get(args[1])
    }
}