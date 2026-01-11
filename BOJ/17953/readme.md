# 백준 17953번: 디저트

> 문제: https://www.acmicpc.net/problem/17953

### 문제 풀이

DP

dp[i][j] = i + 1번째 날에 j + 1번째 디저트를 먹었을 때의 만족감의 합의 최댓값

먼저 입력에 따라 그 날의 디저트를 먹는 만족감을 dp배열에 먼저 입력받음

같은 디저트를 연속으로 먹으면 만족감이 반으로 감소한다고 했으므로 점화식이 다음과 같이 나옴

dp[i][j] = max(dp[i - 1][k] + dp[i][j], dp[i - 1][j] + (dp[i][j] / 2)) (k는 j와 다른 M 미만의 숫자)

이에 따라 dp배열을 채우면 dp[N - 1]이 N번째 날에 디저트를 먹었을 때의 만족감이므로 최댓값을 찾아 출력하면 정답

### 풀이 설명

날마다 디저트를 먹었을 때의 만족감이 다르고 하루에 하나씩만 디저트를 먹을 때 N일 동안 디저트를 먹을 때의 만족감의 합의 최댓값을 찾는 문제이다.

이를 구하는 방법을 생각해보면 각 날짜마다 디저트를 하나씩 먹기 때문에 만족감을 날짜마다 더해주고 이 합의 최댓값을 찾아야 하는 것이므로 N번째 날에 디저트를 먹는 만족감을 더해서 최댓값이 되려면 N - 1번째 날까지 먹은 디저트의 만족감의 합도 최댓값이 되어야 한다.

따라서 문제를 작은 단위의 문제로 나눌 수 있으므로 DP로 문제를 풀 수 있다는 뜻이다. 그러므로 문제를 풀기 위해 dp[i][j]를 i + 1번째 날에 j + 1번째 디저트를 먹었을 때의 만족감의 합의 최댓값으로 정의한다.

먼저 디저트와 날짜마다의 만족감이 입력으로 주어지므로 이를 먼저 dp배열에 입력을 받고, 연속으로 같은 디저트를 먹으면 만족감이 반으로 감소한다고 했으므로 k를 j가 아닌 다른 디저트의 번호라고 하면 다음과 같은 점화식이 나온다.

dp[i][j] = max(dp[i - 1][k] + dp[i][j], dp[i - 1][j] + (dp[i][j] / 2))

점화식에 따라 배열을 채우면 dp[N - 1]이 N번째 날에 디저트를 먹었을 때의 만족감의 합이 저장되어 있으므로 이 배열의 원소에서 최댓값을 찾아 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val dp = Array(N){IntArray(M)}
    for(j in 0 until M){
        for(i in 0 until N){
            dp[i][j] = nextInt()
        }
    }
    for(i in 1 until N){
        for(j in 0 until M){
            var max = 0
            for(k in 0 until M){
                max = maxOf(max, dp[i - 1][k] + if(j == k) (dp[i][j] / 2) else dp[i][j])
            }
            dp[i][j] = max
        }
    }
    println(dp[N - 1].maxOf { it })
}
```