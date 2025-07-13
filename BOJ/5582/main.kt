package BOJ_5582

fun main(){
    val br = System.`in`.bufferedReader()
    val A = br.readLine()
    val B = br.readLine()
    val dp = Array(A.length){ IntArray(B.length){0} }
    var result = 0
    for(i in 0 until A.length){
        for(j in 0 until B.length){
            if(A[i] == B[j]){
                dp[i][j]++
                if(i > 0 && j > 0){
                    dp[i][j] += dp[i - 1][j - 1]
                }
                if(dp[i][j] > result) result = dp[i][j]
            }
        }
    }
    println(result)
}