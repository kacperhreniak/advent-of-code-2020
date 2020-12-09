package day8.part2

import day8.Operation
import day8.loadData


fun main() {
    val input = loadData()
    val testedOperation = HashSet<Operation>()

    var state = State()

    do {
        val operation = input[state.index].run {
            state.swapOperationIfCan(testedOperation, this)
        }
        state.handleOperation(operation)
        if (state.isVisitedAgain()) {
            state = State()
        }
        val shouldWork = state.shouldWork(input.size)
    } while (shouldWork)

    println("Result: ${state.accumulator}")
}


private class State(
    val visited: HashSet<Int> = HashSet(),
    var isOperationChanged: Boolean = false,
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

    fun shouldWork(inputSize: Int): Boolean = visited.size != inputSize && index >= 0 && index < inputSize

    fun isVisitedAgain() = visited.contains(index)

    fun swapOperationIfCan(operations: HashSet<Operation>, operation: Operation): Operation {
        fun shouldSwapOperation(): Boolean = isOperationChanged.not() && operations.contains(operation).not() &&
                (operation.type == Operation.Type.JUMP || operation.type == Operation.Type.NOP)

        fun findNewOperationType(operationType: Operation.Type) = when (operationType) {
            Operation.Type.JUMP -> Operation.Type.NOP
            Operation.Type.NOP -> Operation.Type.JUMP
            else -> operationType
        }

        return takeIf { shouldSwapOperation() }?.run {
            isOperationChanged = true
            operations.add(operation)
            operation.copy(type = findNewOperationType(operation.type))
        } ?: operation
    }
}