package BOJ_9935

fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    val bomb = readLine()
    val end = bomb[bomb.length - 1]
    val stack = CharArray(s.length)
    var index = -1

    for (c in s) {
        stack[++index] = c
        if (index + 1 >= bomb.length && c == end) {
            var match = true
            for (j in bomb.indices) {
                if (stack[index - bomb.length + 1 + j] != bomb[j]) {
                    match = false
                    break
                }
            }
            if (match) {
                index -= bomb.length
            }
        }
    }

    val output = if (index == -1) "FRULA" else String(stack, 0, index + 1)
    println(output)
}