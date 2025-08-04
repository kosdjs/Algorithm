package BOJ_13549

import java.util.StringTokenizer

fun main(){
    val max = 100001
    val dist = IntArray(max){Int.MAX_VALUE}
    val visit = BooleanArray(max){false}
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    dist[N] = 0
    visit[N] = true
    val deque = ArrayDeque<Int>()
    deque.add(N)
    while (deque.isNotEmpty()){
        val x = deque.removeFirst()
        if(x * 2 < max && !visit[x * 2]){
            dist[x * 2] = dist[x]
            visit[x * 2] = true
            deque.addFirst(x * 2)
        }
        if(x > 0 && !visit[x - 1]){
            dist[x - 1] = dist[x] + 1
            visit[x - 1] = true
            deque.addLast(x - 1)
        }
        if(x + 1 < max && !visit[x + 1]){
            dist[x + 1] = dist[x] + 1
            visit[x + 1] = true
            deque.addLast(x + 1)
        }
    }
    println(dist[K])
}