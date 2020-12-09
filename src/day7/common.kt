package day7

import java.io.File

private const val FILEPATH = "src/day7/input.txt"

fun loadData(): List<Bag> = File(FILEPATH).readText()
    .replace("bags", "bag")
    .replace("bag", "")
    .replace(".", "")
    .split("\r\n")
    .map {
        val data = it.split(" contain ")
        val members = data[1].split(" , ")
        return@map if (members[0].contains("no other")) {
            Bag(data[0].trim(), emptyList())
        } else {
            val memberWithCount = members.map { memberPart ->
                memberPart.split(" ").run {
                    Member(get(0).toInt(), get(1) + " "+ get(2))
                }
            }

            Bag(data[0].trim(), memberWithCount)
        }
    }

data class Bag(
    val name: String,
    val members: List<Member>
)

data class Member(
    val count: Int,
    val color: String
)

