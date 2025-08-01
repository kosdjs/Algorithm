package BOJ_2623

import java.util.*

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val prev = IntArray(N + 1){0}
    val next = Array(N + 1){ArrayList<Int>()}
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val m = st.nextToken().toInt()
        var p = st.nextToken().toInt()
        var c = 0
        for(j in 1 until m){
            c = st.nextToken().toInt()
            if(!next[p].contains(c)){
                next[p].add(c)
                prev[c]++
            }
            p = c
        }
    }
    val queue = ArrayDeque<Int>()
    for(i in 1..N){
        if(prev[i] == 0){
            queue.add(i)
        }
    }
    val answer = ArrayList<Int>()
    while(queue.isNotEmpty()){
        val c = queue.removeFirst()
        answer.add(c)
        for(n in next[c]){
            prev[n]--
            if(prev[n] == 0){
                queue.add(n)
            }
        }
    }
    if(answer.size == N){
        for(a in answer){
            println(a)
        }
    } else {
        println(0)
    }
}