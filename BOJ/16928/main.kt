package BOJ_16928

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = IntArray(101){it}
    repeat(N + M){
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        map[x] = y
    }
    val visit = BooleanArray(101)
    visit[1] = true
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(1, 0))
    var end = false
    while (queue.isNotEmpty()){
        val (cur, count) = queue.removeFirst()
        for(i in 1..6){
            if(cur + i < 100 && !visit[map[cur + i]]){
                visit[map[cur + i]] = true
                queue.add(intArrayOf(map[cur + i], count + 1))
            } else if(cur + i == 100){
                println(count + 1)
                end = true
                break
            }
        }
        if(end){
            break
        }
    }
}