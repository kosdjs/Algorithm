package BOJ_11058

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()

    val dp = Array(N + 1){LongArray(4)}
    for(i in 1..N){
        var max = 0L
        for(j in 0..3){
            if(dp[i - 1][j] > max){
                max = dp[i - 1][j]
            }
        }
        dp[i][0] = max + 1 // press A
        dp[i][1] = max // CTRL + A
        dp[i][2] = dp[i - 1][1] // CTRL + C
        max = 0
        for(j in 1..i){
            if(dp[i - j][2] == 0L) break
            if(max < dp[i - j][2] * (j + 1)){
                max = dp[i - j][2] * (j + 1)
            }
        }
        dp[i][3] = max // CTRL + V
    }
    var answer = 0L
    for(j in 0..3){
        if(answer < dp[N][j]){
            answer = dp[N][j]
        }
    }
    println(answer)
}