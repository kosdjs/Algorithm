package BOJ_16500

fun main(){
    val br = System.`in`.bufferedReader()
    val S = br.readLine()
    val N = br.readLine().toInt()
    val A = Array<String>(N){""}
    for(i in 0 until N){
        A[i] = br.readLine()
    }
    val dp = BooleanArray(S.length + 1)
    dp[0] = true
    for(i in 0 until S.length){
        if(dp[i]){
            for(j in 0 until N){
                if(i + A[j].length > S.length){
                    continue
                }
                var find = true
                for(k in 0 until A[j].length){
                    if(A[j][k] != S[i + k]){
                        find = false
                        break
                    }
                }
                if(find){
                    dp[i + A[j].length] = true
                }
            }
        }
    }
    if(dp[S.length]){
        println(1)
    } else {
        println(0)
    }
}