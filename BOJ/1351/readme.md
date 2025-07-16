# 백준 1351번: 무한 수열

> 문제: https://www.acmicpc.net/problem/1351

### 문제 풀이

DP

$dp[i] = A_i = dp[i / P] + dp[i / Q]$

점화식에 따라 $A_N$ 을 구하면 정답

### 풀이 설명

문제에 점화식이 나와있고, 같은 수열의 더 작은 값을 계속해서 더하기 때문에 수열의 값을 기억하고 있으면 더 빠르게 구할 수 있다.

수열의 값을 기억하는 방법은 배열과 같은 테이블에 수열의 전체 값을 저장하는 방법이 있고, 특정 값을 처음 사용할 때 그 값을 구해놓고 저장한 후 나중에 그 값이 필요할 때 다시 사용하는 방법이 있다.

이 문제는 점화식에서 나와있듯이 P로 나눈 몫과 Q로 나눈 몫을 사용해 값을 구하기 때문에 실질적으로 사용하는 수는 그렇게 많지 않기 때문에 뒤의 방식이 더 적합하다.

따라서 $A_i$의 값을 처음 사용할 때 값을 구해서 Map을 사용해 $i$와 $A_i$의 값을 쌍으로 저장하고 다음에 $A_i$의 값이 필요할 때 값을 다시 계산하지 않고 저장된 값을 사용하면 된다.

이런 방식으로 $A_N$을 구하면 정답이다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toLong()
    val P = st.nextToken().toInt()
    val Q = st.nextToken().toInt()
    val dp = HashMap<Long, Long>()
    dp[0] = 1
    println(solve(dp, N, P, Q))
}

fun solve(dp: HashMap<Long, Long>, idx: Long, P: Int, Q: Int): Long{
    if(dp.contains(idx)){
        return dp[idx]!!
    } else {
        dp[idx] = solve(dp, idx / P, P, Q) + solve(dp, idx / Q, P, Q)
        return dp[idx]!!
    }
}
```