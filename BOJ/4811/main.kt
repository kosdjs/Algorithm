package BOJ_4811

fun main(){
    val br = System.`in`.bufferedReader()
    val dp = Array(31){ LongArray(31) }
    for(i in 0..30){
        for(j in 0..30){
            if(i == 0){
                dp[i][j] = 1
            } else {
                if(j < 30){
                    dp[i][j] += dp[i - 1][j + 1]
                }
                if(j > 0){
                    dp[i][j] += dp[i][j - 1]
                }
            }
        }
    }
    var t = br.readLine().toInt()
    while(t != 0){
        println(dp[t][0])
        t = br.readLine().toInt()
    }
}