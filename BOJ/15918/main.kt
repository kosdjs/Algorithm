package BOJ_15918

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val n = nextInt()
    val x = nextInt()
    val y = nextInt()
    val arr = IntArray(n * 2 + 1)
    val visit = BooleanArray(n + 1)
    arr[x] = y - x - 1
    arr[y] = y - x - 1
    visit[y - x - 1] = true
    var answer = 0
    fun pick(count: Int, idx: Int){
        for(i in 1..n){
            if(!visit[i] && idx + i + 1 <= n * 2 && arr[idx + i + 1] == 0){
                visit[i] = true
                arr[idx] = i
                arr[idx + i + 1] = i
                if(count == n - 1){
                    answer++
                } else {
                    for(j in (idx + 1)..(n * 2)){
                        if(arr[j] == 0){
                            pick(count + 1, j)
                            break
                        }
                    }
                }
                visit[i] = false
                arr[idx] = 0
                arr[idx + i + 1] = 0
            }
        }
    }
    for(i in 1..(n * 2)){
        if(arr[i] == 0){
            pick(1, i)
            break
        }
    }
    println(answer)
}