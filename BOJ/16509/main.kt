package BOJ_16509

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val R1 = nextInt()
    val C1 = nextInt()
    val R2 = nextInt()
    val C2 = nextInt()
    if(R1 == R2 && C1 == C2){
        println(0)
        return@run
    }
    val dy = intArrayOf(-3, -3, -2, 2, 3, 3, 2, -2)
    val dx = intArrayOf(-2, 2, 3, 3, 2, -2, -3, -3)
    val block = arrayOf(
        arrayOf(intArrayOf(-1, 0), intArrayOf(-2, -1)),
        arrayOf(intArrayOf(-1, 0), intArrayOf(-2, 1)),
        arrayOf(intArrayOf(0, 1), intArrayOf(-1, 2)),
        arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)),
        arrayOf(intArrayOf(1, 0), intArrayOf(2, 1)),
        arrayOf(intArrayOf(1, 0), intArrayOf(2, -1)),
        arrayOf(intArrayOf(0, -1), intArrayOf(1, -2)),
        arrayOf(intArrayOf(0, -1), intArrayOf(-1, -2))
    )
    val visit = Array(10){ BooleanArray(9) }
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(R1, C1, 0))
    visit[R1][C1] = true
    while(queue.isNotEmpty()){
        val(y, x, count) = queue.removeFirst()
        for(i in 0 until 8){
            val ny = y + dy[i]
            val nx = x + dx[i]
            if(ny < 0 || ny > 9 || nx < 0 || nx > 8 || visit[ny][nx]) continue
            //막히지 않았는지 판단해야 함
            var isBlocked = false
            for((diffY, diffX) in block[i]){
                val routeY = y + diffY
                val routeX = x + diffX
                if(routeY == R2 && routeX == C2){
                    isBlocked = true
                    break
                }
            }
            if(isBlocked) continue
            visit[ny][nx] = true
            if(ny == R2 && nx == C2){
                println("${count + 1}")
                return@run
            }
            queue.add(intArrayOf(ny, nx, count + 1))
        }
    }
    println(-1)
}