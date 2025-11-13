package br.com.seucaio.gamedex.util.extension

fun Boolean?.orFalse() = this ?: false

fun Boolean?.orTrue() = this ?: true
