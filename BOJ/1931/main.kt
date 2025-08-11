package BOJ_1931

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val list = ArrayList<IntArray>()
    repeat(N){
        val st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        list.add(intArrayOf(start, end))
    }
    var time = 0
    var answer = 0
    list.sortWith { o1, o2 -> if (o1[1] == o2[1]) o1[0] - o2[0] else o1[1] - o2[1] }
    for((start, end) in list){
        if(start >= time){
            time = end
            answer++
        }
    }
    println(answer)
}