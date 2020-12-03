import java.io.File

fun readItems(fileName: String): List<String> =
    File(fileName)
        .readLines()