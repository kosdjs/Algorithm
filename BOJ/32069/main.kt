package BOJ_32069

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val L = st.nextToken().toLong()
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val queue = ArrayDeque<LongArray>()
    st = StringTokenizer(br.readLine())
    val set = HashSet<Long>()
    for(i in 0 until N){
        val a = st.nextToken().toLong()
        queue.add(longArrayOf(a, 0L))
        set.add(a)
    }
    var count = 0
    val sb = StringBuilder()
    while(count < K){
        val (x, dist) = queue.removeFirst()
        sb.append("$dist\n")
        count++
        if(x > 0 && !set.contains(x - 1)){
            queue.add(longArrayOf(x - 1, dist + 1))
            set.add(x - 1)
        }
        if(x < L && !set.contains(x + 1)){
            queue.add(longArrayOf(x + 1, dist + 1))
            set.add(x + 1)
        }
    }
    print(sb)
}