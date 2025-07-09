# 백준 13398번: 연속합 2

> 문제: https://www.acmicpc.net/problem/13398

### 문제 풀이

DP

$dp[i][j]$ = i번째까지 연속되는 수의 합 중 최대, $j = 0$이면 연속되는 수 중간에 빠지는 값이 없음, $j = 1$이면 중간에 빠지는 수 하나 있음

$dp[i][0] = max(dp[i-1][0] + arr[i], arr[i])$
$dp[i][1] = max(dp[i-1][0], dp[i-1][1] + arr[i])$

구한 테이블에서 최댓값을 찾으면 정답

### 풀이 설명

연속된 수열의 합을 두 부분으로 나눈다면 수열의 마지막 수와 이전 수열의 합으로 나눌 수 있다.

현재 문제에서 사용하는 arr 배열로 설명을 하면 i번째 까지의 합은 arr[i]에 저장된 값과 arr[i - 1]까지의 합으로 생각할 수 있다.

이런 연속합이 최대가 되게 하려면 모든 i에 대해서 i번째 까지 연속된 수열의 합을 구하면 된다.

그렇다면 이를 구할 때 이전 부분의 연속합과 현재 값을 더하기 때문에 이전 부분의 연속합이 음수라면 현재 값만 가지고 가는 것이 더 큰 값이 될 것이다. 따라서 앞서 정의한 dp테이블에서 j = 0일 때의 점화식이 나온다.

수열에서 수를 하나 제거할 때도 위와 같이 마지막 수와 이전 수열의 합으로 생각해본다면 현재 수를 제외하거나 이미 이전 수열에서 수가 제거되었을 때로 나뉠 수 있다.

그렇기 때문에 현재 수를 제거했을 때와 이미 수가 하나 빠진 이전 수열의 합에 현재 수를 더했을 때를 비교해 더 큰 값을 저장하면 된다.

모든 i에 대해서 수를 빼거나 빼지 않았을 때의 합을 모두 구한 후 최댓값을 찾으면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer
import kotlin.math.max

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val arr = IntArray(n)
    for(i in 0 until n){
        arr[i] = st.nextToken().toInt()
    }
    val dp = Array(n){IntArray(2)}
    var answer = Int.MIN_VALUE
    dp[0][0] = arr[0]
    dp[0][1] = max(answer, arr[0])
    if(dp[0][0] > answer) answer = dp[0][0]
    if(dp[0][1] > answer) answer = dp[0][1]
    for(i in 1 until n){
        dp[i][0] = max(dp[i-1][0] + arr[i], arr[i])
        if(dp[i][0] > answer) answer = dp[i][0]
        dp[i][1] = max(dp[i-1][0], dp[i-1][1] + arr[i])
        if(dp[i][1] > answer) answer = dp[i][1]
    }
    println(answer)
}
```