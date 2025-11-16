package br.com.seucaio.gamedex.util.extension

val String.Companion.EMPTY get() = ""

fun String?.orDefault(default: String) = this ?: default
