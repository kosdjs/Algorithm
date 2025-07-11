# 백준 15486번: 퇴사 2

> 문제: https://www.acmicpc.net/problem/15486

### 문제 풀이

DP

dp[N] = N일 째까지 얻을 수 있는 최대 비용

dp[N] = max(dp[N - 1], dp[N - T] + P) T는 상담 기간, P는 상담 비용

### 풀이 설명

dp[N]을 N일 째까지 얻을 수 있는 최대 비용으로 정의하면 N일까지 상담을 하는 경우와 N일에는 상담을 하지 않는 경우로 나뉜다.

N일까지 상담을 하는 경우는 상담 시작일 I, 상담 기간 T가 있으면 I + T - 1이 N일이 되는 경우이고 이 때 값은 dp[N - T + 1] + P가 된다.

N일에는 상담을 하지 않는 경우는 dp[N - 1]이 N - 1일 째까지 얻을 수 있는 최대 비용이므로 값은 dp[N - 1]이 된다.

모든 날짜에 대해 dp 테이블을 구해서 N일 째에 얻을 수 있는 최대 비용을 구하면 정답

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val dp = IntArray(N + 50){0}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        val T = st.nextToken().toInt()
        val P = st.nextToken().toInt()
        if(i > 0 && dp[i] < dp[i-1]) dp[i] = dp[i-1]
        if(dp[i + T] < dp[i] + P) dp[i + T] = dp[i] + P
    }
    if(dp[N] < dp[N-1]) dp[N] = dp[N-1]
    println(dp[N])
}
```