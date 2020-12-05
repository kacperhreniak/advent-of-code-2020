package day5

import readItems

fun findSeatsCodes(filepath: String = "src/day5/input.txt") =
    readItems(filepath)
        .map {
            it.replace('F', '0')
                .replace('B', '1')
                .replace('L', '0')
                .replace('R', '1')
                .toInt(2)
        }
