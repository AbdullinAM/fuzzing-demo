package org.plan.research

/**
 * Task: fuzz a given fuzz target and find any buggy behavior in it
 *
 * What can assume:
 * 1. Fuzz target takes some number of input arguments and processes them.
 * 2. We may assume that any behavior of fuzz target can be observed by providing it arguments that
 * have no more than 10 characters and only contain lowercase English alphabet letters or digits
 */
const val FUZZ_TARGET = "fuzz-target.jar"
const val MAX_STR_LENGTH = 10
val CHARSET = ('a'..'z') + ('0'..'9')

/**
 * Represents the result of an executed process, capturing its exit code and any error message.
 *
 * @property exitCode The exit code returned by the executed process.
 * @property error A string containing any error message produced by the process.
 */
data class Execution(
    val exitCode: Int,
    val error: String,
)

/**
 * Executes the fuzz target with given arguments by invoking a Java process.
 *
 * @param args A list of arguments to be passed to the Java process.
 * @return An instance of Execution containing the exit code and error output of the process.
 */
fun execute(args: List<String>): Execution {
    val process = ProcessBuilder(listOf("java", "-jar", FUZZ_TARGET) + args).start()
    val exitCode = process.waitFor()
    val output = process.errorStream.bufferedReader().use { it.readText() }
    return Execution(exitCode, output)
}

/**
 * Evaluates the result of an executed process based on its exit code.
 *
 * @param result The result of the executed process containing exit code and error message.
 * @return Returns true if the execution demonstrates buggy behavior.
 */
fun isFailure(result: Execution): Boolean = TODO()

fun main() {
    TODO()
}
