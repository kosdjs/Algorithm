package BOJ_1584

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val map = Array(501){ IntArray(501) {0} }
    repeat(N){
        val st = StringTokenizer(br.readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()
        val minX = if (x1 < x2) x1 else x2
        val minY = if (y1 < y2) y1 else y2
        val maxX = if (x1 > x2) x1 else x2
        val maxY = if (y1 > y2) y1 else y2
        for(i in minX..maxX){
            for(j in minY..maxY){
                map[i][j] = 1
            }
        }
    }
    val M = br.readLine().toInt()
    repeat(M){
        val st = StringTokenizer(br.readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()
        val minX = if (x1 < x2) x1 else x2
        val minY = if (y1 < y2) y1 else y2
        val maxX = if (x1 > x2) x1 else x2
        val maxY = if (y1 > y2) y1 else y2
        for(i in minX..maxX){
            for(j in minY..maxY){
                map[i][j] = 2
            }
        }
    }
    val dist = Array(501){ IntArray(501) {Int.MAX_VALUE} }
    val deque = ArrayDeque<IntArray>()
    dist[0][0] = 0
    deque.addFirst(intArrayOf(0, 0))
    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, -1, 0, 1)
    while(deque.isNotEmpty()){
        val (x, y) = deque.removeFirst()
        if(x == 500 && y == 500) break
        for(k in 0 until 4){
            val nx = x + dx[k]
            val ny = y + dy[k]
            if(nx < 0 || nx > 500 || ny < 0 || ny > 500) continue
            if(dist[nx][ny] > dist[x][y] + map[nx][ny]){
                if(map[nx][ny] == 0){
                    dist[nx][ny] = dist[x][y] + map[nx][ny]
                    deque.addFirst(intArrayOf(nx, ny))
                } else if(map[nx][ny] == 1){
                    dist[nx][ny] = dist[x][y] + map[nx][ny]
                    deque.addLast(intArrayOf(nx, ny))
                }
            }
        }
    }
    if(dist[500][500] == Int.MAX_VALUE){
        println(-1)
    } else {
        println(dist[500][500])
    }
}