package BOJ_2660

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val graph = Array(n + 1){ArrayList<Int>()}
    val score = IntArray(n + 1){n}
    val queue = ArrayDeque<IntArray>()
    while(true){
        val st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        if(u == -1 && v == -1){
            break
        }
        graph[u].add(v)
        graph[v].add(u)
    }
    for(i in 1..n){
        val visit = BooleanArray(n + 1)
        var max = 0
        queue.add(intArrayOf(i, 0))
        visit[i] = true
        while(queue.isNotEmpty()){
            val (u, score) = queue.removeFirst()
            if(score > max){
                max = score
            }
            for(v in graph[u]){
                if(!visit[v]){
                    visit[v] = true
                    queue.add(intArrayOf(v, score + 1))
                }
            }
        }
        score[i] = max
    }
    var minScore = n
    var count = 0
    for(i in 1..n){
        if(minScore > score[i]){
            minScore = score[i]
            count = 1
        } else if(minScore == score[i]){
            count++
        }
    }
    println("$minScore $count")
    for(i in 1..n){
        if(score[i] == minScore){
            print("$i ")
        }
    }
}