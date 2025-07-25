# 백준 17845번: 수강 과목

> 문제: https://www.acmicpc.net/problem/17845

### 문제 풀이

DP

$dp[j] = j$시간내에 들을 수 있는 과목들의 중요도 합의 최댓값

$dp[j] = max(dp[j], dp[j - T] + I)$

모든 과목에 대해 점화식에 따라 값을 구하면 $dp[N]$이 정답

### 풀이 설명

최대 시간 내에 들을 수 있는 과목들의 중요도 합의 최댓값을 구하는 것을 과목들을 순서대로 나열했을 때 과목 하나마다 수강할 때와 수강하지 않을 때로 나눠서 중요도의 합을 구하는 것으로 생각해볼 수 있다.

따라서 $dp[i][j]$를 $i$ 번째 과목까지 $j$ 시간내에 들을 수 있는 과목들의 중요도 합의 최댓값으로 정의할 수 있다.

이때 $i$ 번째 과목을 수강할 때와 수강하지 않을 때로 나뉜다고 했으므로 $i$ 번째 과목의 중요도를 $I_i$, 시간을 $T_i$ 라고 했을 때 점화식이 $dp[i][j] = \max \left( dp[i-1][j],\ dp[i-1][j - T_i] + I_i \right)$이 된다.

이때 점화식을 살펴보면 $dp[i]$ 행의 값을 결정할 때 $dp[i - 1]$ 행만 확인한다는 점에서 1차원 배열로 줄여서 이전 행의 값을 매번 덮어씌울 수 있다는 것을 알 수 있다.

따라서 $dp[j] = j$시간내에 들을 수 있는 과목들의 중요도 합의 최댓값로 정의를 하고 과목을 순서대로 확인하며 매 과목마다 $dp$ 테이블의 값을 변경해주면 된다.

이때 점화식이 $dp[j] = max(dp[j], dp[j - T] + I)$이 되고 2차원 배열때처럼 $j$ 를 오름차순으로 반복한다면 같은 과목을 여러번 수강하는 문제가 생길 수 있으므로 $j$를 내림차순으로 반복해야 한다.

이에 따라 $dp$ 테이블의 값을 구하면 $dp[N]$에 구하는 값이 저장되어있으므로 출력하면 된다.

### 소스 코드
```kotlin
import java.util.*

fun main() {
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dp = IntArray(N + 1) { 0 }
    for(i in 1..K) {
        val st = StringTokenizer(br.readLine())
        val I = st.nextToken().toInt()
        val T = st.nextToken().toInt()
        for (j in N downTo T)
            dp[j] = maxOf(dp[j], dp[j - T] + I)
    }
    println(dp[N])
}
```