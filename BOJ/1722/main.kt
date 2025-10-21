package BOJ_1722

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val visit = BooleanArray(21){false}
    var count = 1L
    for(i in 2..N){
        count *= i
    }
    if(st.nextToken().toInt() == 1){
        //번호로 순열 출력
        var num = st.nextToken().toLong() - 1
        for(i in N downTo 1){
            count /= i
            var unusedCount = 0
            var unusedNum = 0
            while(unusedCount <= num / count){
                unusedNum++
                if(!visit[unusedNum]){
                    unusedCount++
                }
            }
            print("$unusedNum ")
            visit[unusedNum] = true
            num %= count
        }
    } else {
        //순열로 번호 출력
        var answer = 0L
        for(i in N downTo 1){
            count /= i
            val num = st.nextToken().toInt()
            var unusedCount = 0
            var unusedNum = 0
            while(unusedNum < num){
                unusedNum++
                if(!visit[unusedNum]){
                    unusedCount++
                }
            }
            answer += count * (unusedCount - 1)
            visit[num] = true
        }
        println(answer + 1)
    }
}