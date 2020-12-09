package day7.part2

import day7.Bag
import day7.loadData

private const val SEARCH_BAG = "shiny gold"

fun main() {
    val result = countBags(loadData(), SEARCH_BAG)
    println("Result: $result")
}

private fun countBags(input: List<Bag>, bagName: String): Int {
    val bag: Bag = input.first { it.name == bagName }

    var count = 0
    for (member in bag.members) {
        count += member.count * (countBags(input, member.color) + 1)
    }
    return count
}