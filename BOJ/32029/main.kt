package BOJ_32029

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val A = nextInt()
    val B = nextInt()
    val deadline = IntArray(N)
    for(i in 0 until N){
        deadline[i] = nextInt()
    }
    deadline.sort()
    var answer = 0
    for(X in 0 until A){
        var timeBeforeSleep = 0
        var countBeforeSleep = 0
        val reducedDuration = A - X
        for(i in 0 until N){
            var time = timeBeforeSleep + B * X
            var count = countBeforeSleep
            for(k in i until N){
                if(time + reducedDuration <= deadline[k]){
                    time += reducedDuration
                    count++
                }
            }
            answer = maxOf(answer, count)
            if(timeBeforeSleep + A <= deadline[i]){
                timeBeforeSleep += A
                countBeforeSleep++
            }
        }
    }
    println(answer)
}