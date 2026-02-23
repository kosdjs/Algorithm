package BOJ_1239

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = IntArray(N){nextInt()}
    var selectedArr = IntArray(N)
    val visit = BooleanArray(N)
    var answer = 0
    fun pickNum(count: Int){
        if(count == N){
            var result = 0
            for(i in 0 until N){
                var sum = selectedArr[i]
                var idx = i + 1
                while(sum < 50 && idx < N){
                    sum += selectedArr[idx]
                    idx++
                }
                if(sum == 50) result++
            }
            answer = maxOf(answer, result - 1)
        } else {
            for(i in 0 until N){
                if(!visit[i]){
                    visit[i] = true
                    selectedArr[count] = arr[i]
                    pickNum(count + 1)
                    visit[i] = false
                    selectedArr[count] = 0
                }
            }
        }
    }
    pickNum(0)
    println(answer)
}