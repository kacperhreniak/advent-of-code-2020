package day8

import java.io.File

private const val FILEPATH = "src/day8/input.txt"

fun loadData(): List<Operation> = File(FILEPATH).readLines()
    .map {
        val data = it.split(" ")
        val type: Operation.Type = Operation.Type.fromLabel(data[0])
        val value = data[1].toInt()
        Operation(type, value)
    }

data class Operation(
    val type: Type,
    val value: Int
) {
    enum class Type(val label: String) {
        JUMP("jmp"),
        ACC("acc"),
        NOP("nop");

        companion object {
            fun fromLabel(label: String): Type = values().first { it.label == label }
        }
    }
}