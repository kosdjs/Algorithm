package BOJ_1174

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val queue = ArrayDeque<Long>()
    for(i in 0L.. 9L){
        queue.add(i)
    }
    if(N <= 10){
        println(N - 1)
        return
    }
    var count = 10
    while(queue.isNotEmpty()){
        val num = queue.removeFirst()
        val lastDigit = num % 10
        for(i in 0 until lastDigit){
            val next = num * 10 + i
            count++
            if(count == N){
                println(next)
                return
            }
            queue.add(next)
        }
    }
    println(-1)
}