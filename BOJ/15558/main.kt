package BOJ_15558

fun main() = System.`in`.bufferedReader().run {
    val(N, k) = readLine().split(" ").map { it.toInt() }
    val map = Array(2){BooleanArray(N)}
    val visit = Array(2){BooleanArray(N)}
    for(i in 0 until 2){
        val s = readLine()
        for(j in 0 until N){
            map[i][j] = s[j] == '1'
        }
    }
    var clear = false
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(0, 0, 0))
    visit[0][0] = true
    while(queue.isNotEmpty()){
        val (i, j, time) = queue.removeFirst()
        if(j + 1 >= N){
            clear = true
            break
        } else if(map[i][j + 1] && !visit[i][j + 1]) {
            visit[i][j + 1] = true
            queue.add(intArrayOf(i, j + 1, time + 1))
        }
        if(j - 1 >= time + 1 && map[i][j - 1] && !visit[i][j - 1]){
            visit[i][j - 1] = true
            queue.add(intArrayOf(i, j - 1, time + 1))
        }
        if(j + k >= N){
            clear = true
            break
        } else if(map[(i + 1) % 2][j + k] && !visit[(i + 1) % 2][j + k]){
            visit[(i + 1) % 2][j + k] = true
            queue.add(intArrayOf((i + 1) % 2, j + k, time + 1))
        }
    }
    println(if(clear) 1 else 0)
}