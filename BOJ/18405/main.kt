package BOJ_18405

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val map = Array(N + 1){ IntArray(N + 1) }
    val visit = Array(N + 1){ BooleanArray(N + 1) }
    for(i in 1..N){
        st = StringTokenizer(br.readLine())
        for(j in 1..N){
            map[i][j] = st.nextToken().toInt()
        }
    }
    val q = ArrayDeque<IntArray>()
    st = StringTokenizer(br.readLine())
    val S = st.nextToken().toInt()
    val X = st.nextToken().toInt()
    val Y = st.nextToken().toInt()
    var answer = 0
    var minDist = Int.MAX_VALUE
    visit[X][Y] = true
    if(map[X][Y] != 0){
        answer = map[X][Y]
    } else {
        q.add(intArrayOf(X, Y, 0))
        val dx = intArrayOf(-1, 0, 1, 0)
        val dy = intArrayOf(0, -1, 0, 1)
        while(q.isNotEmpty()){
            val (x, y, dist) = q.removeFirst()
            if(dist == S){
                continue
            }
            for(i in 0 until 4){
                val nx = x + dx[i]
                val ny = y + dy[i]
                if(nx > 0 && nx <= N && ny > 0 && ny <= N && !visit[nx][ny]){
                    visit[nx][ny] = true
                    q.add(intArrayOf(nx, ny, dist + 1))
                    if(map[nx][ny] != 0){
                        if(dist + 1 < minDist){
                            minDist = dist + 1
                            answer = map[nx][ny]
                        } else if(dist + 1 == minDist && map[nx][ny] < answer){
                            answer = map[nx][ny]
                        }
                    }
                }
            }
        }
    }
    println(answer)
}