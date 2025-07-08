# 백준 2225번: 합분해

> 문제: https://www.acmicpc.net/problem/2225

### 문제 풀이

DP

$dp[N][K] = dp[N - 1][K] + dp[N][K - 1]$

위의 점화식에 따라 테이블을 구하면 정답

### 풀이 설명

0부터 N까지의 정수 K개의 합이 N이 될 때, 마지막 정수를 L이라고 생각해보고 두 부분으로 나눠서 식을 작성하면

$O + O + ... + O + L = N$ (단, $O$는 $0$부터 $N$까지의 임의의 정수)

위와 같이 0부터 N까지의 정수 K - 1개와 L를 더해서 N을 만드는 식이 나오게 된다. 이를 변형한다면

$O + O + ... + O = N - L$ (단, $O$는 $0$부터 $N$까지의 임의의 정수)

위와 같이 바뀌게 되고 0부터 N까지의 정수 K - 1개를 더해 N - L이 되는 식을 만들 수 있다.

L이 문제 조건에 따라 0부터 N까지의 정수이기 때문에 각 L에 대해 K - 1개의 수를 더해 N - L을 만드는 모든 경우의 수를 더하면 모든 경우의 수를 확인할 수 있다

이에 따라 dp[N][K]를 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수라고 하면

$dp[N][K] = \sum_{L=0}^{N} dp[N - L][K - 1]$ 이 된다

그리고 이 식을 다시 풀게 되면 다음과 같은 식이 나오고

$\sum_{L=0}^{N} dp[N - L][K - 1] = dp[0][K - 1] + dp[1][K - 1] + ... + dp[N][K - 1]$

이를 $dp[N-1][K]$와 비교해본다면

$\sum_{L=0}^{N - 1} dp[N - L][K - 1] = dp[0][K - 1] + dp[1][K - 1] + ... + dp[N - 1][K - 1]$

위의 식에서 $dp[N][K - 1]$이 빠진 형태라는 것을 알 수 있다. 따라서 다음 식을 도출해낼 수 있다.

$dp[N][K] = dp[N - 1][K] + dp[N - 1][K - 1]$

이에 따라 dp값을 구하고 문제 조건에 맞게 출력하면 정답을 찾을 수 있다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dp = Array(N + 1){LongArray(K + 1){0} }
    for(j in 0..K){
        dp[0][j] = 1
    }
    for(i in 1..N){
        for(j in 1..K){
            dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000
        }
    }
    println(dp[N][K])
}
```