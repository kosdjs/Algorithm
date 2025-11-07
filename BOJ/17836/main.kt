package BOJ_17836

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val T = st.nextToken().toInt()
    var swordY = 0
    var swordX = 0
    val map = Array(N){IntArray(M)}
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            map[i][j] = st.nextToken().toInt()
            if(map[i][j] == 2){
                swordY = i
                swordX = j
            }
        }
    }
    var answer = T + 1
    var swordTime = T + 1
    var visit = Array(N){BooleanArray(M)}
    visit[0][0] = true
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(0, 0, 0))
    val dy = intArrayOf(0, -1, 0, 1)
    val dx = intArrayOf(-1, 0, 1, 0)
    while(queue.isNotEmpty()){
        val (y, x, dist) = queue.removeFirst()
        for(i in 0 until 4){
            val ny = y + dy[i]
            val nx = x + dx[i]
            if(ny >= 0 && ny < N && nx >= 0 && nx < M && !visit[ny][nx] && map[ny][nx] != 1){
                if(ny == swordY && nx == swordX){
                    swordTime = dist + 1
                }
                if(ny == N - 1 && nx == M - 1){
                    answer = dist + 1
                    queue.clear()
                    break
                } else {
                    visit[ny][nx] = true
                    queue.add(intArrayOf(ny, nx, dist + 1))
                }
            }
        }
    }
    val throughTime = N - 1 - swordY + M - 1 - swordX
    answer = minOf(answer, swordTime + throughTime)
    println(if(answer <= T) answer else "Fail")
}