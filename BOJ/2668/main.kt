package BOJ_2668

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val arr = IntArray(N + 1)
    for(i in 1..N){
        arr[i] = br.readLine().toInt()
    }
    val answer = BooleanArray(N + 1)
    for(i in 1..N){
        if(answer[i]){
            continue
        }
        val visit = BooleanArray(N + 1)
        var num = arr[i]
        while(num != i){
            visit[num] = true
            num = arr[num]
            if(visit[num]){
                break
            } else {
                visit[num] = true
            }
        }
        if(num == i){
            answer[i] = true
            num = arr[i]
            while(num != i){
                answer[num] = true
                num = arr[num]
            }
        }
    }
    var count = 0
    for(i in 1..N){
        if(answer[i]){
            count++
        }
    }
    println(count)
    for(i in 1..N){
        if(answer[i]){
            println(i)
        }
    }
}