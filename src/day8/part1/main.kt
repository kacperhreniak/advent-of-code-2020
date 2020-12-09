package day8.part1

import day8.Operation
import day8.loadData

fun main() {
    val input = loadData()
    var shouldWork: Boolean
    val state = State()

    do {
        val operation = input[state.index]
        state.handleOperation(operation)
        shouldWork = state.shouldWork(input.size)
    } while (shouldWork)

    println("Result: ${state.accumulator}")
}

private class State(
    val visited: HashSet<Int> = HashSet(),
    var accumulator: Int = 0,
    var index: Int = 0
) {
    fun handleOperation(operation: Operation) {
        visited.add(index)

        when (operation.type) {
            Operation.Type.JUMP -> handleJump(operation)
            Operation.Type.ACC -> handleAccumulator(operation)
            Operation.Type.NOP -> handleNop()
        }
    }

    private fun handleAccumulator(operation: Operation) {
        accumulator += operation.value
        index++
    }

    private fun handleJump(operation: Operation) {
        index += operation.value
    }

    private fun handleNop() {
        index++
    }

    fun shouldWork(inputSize: Int): Boolean = visited.contains(index).not() && index >= 0 && index < inputSize

}