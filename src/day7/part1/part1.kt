package day7.part1

import day7.Bag
import day7.loadData


private const val SEARCH_BAG = "shiny gold"

fun main() {
    val result = loadData().let { bags ->
        lookForInvocations(bags, SEARCH_BAG)
            .toSet()
    }.size

    println("Result: $result")
}

private fun lookForInvocations(input: List<Bag>, bagName: String): Set<String> {
    val parentResult = input.filter { it.members.any { member -> member.color.contains(bagName) } }
        .map { it.name }
        .toSet()

    val childResult = mutableSetOf<String>()
    for (color in parentResult.iterator()) {
        childResult += lookForInvocations(input, color)
    }
    return parentResult + childResult
}