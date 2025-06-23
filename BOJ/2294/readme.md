> 문제: https://www.acmicpc.net/problem/2294

### 문제 풀이

DP

$dp[i]$는 동전의 합이 i원이 되는 동전의 최소 갯수, 동전으로 나타낼 수 없다면 k+1 저장

현재 확인하는 동전의 가치가 j원이라면
$dp[i] = MIN(dp[i - j] + 1, dp[j])$

동전을 가치가 적은 순으로 확인해 모든 dp배열을 확인하면 됨

### 풀이 설명

예제 상황에서 생각해보면 15원을 만들기 위해 1, 5, 12원짜리 동전을 최소 갯수로 사용해 몇개로 만들 수 있는지를 구하는 문제

여기서 만약 12원짜리 동전을 사용했다고 생각해보면 목표인 15원에서 12원을 뺀 3원이 남게 되고 동전의 갯수가 1개가 된다.

그렇다면 이 상황에서 동전의 갯수가 최소 갯수가 되게 하려면 3원을 최소 갯수로 나타내야 한다.

즉, 동전 하나를 사용하면 그 동전과 목표 금액과의 차액의 동전 최소 갯수에 1개를 더한 것이 최소 갯수가 된다는 것이다.

이를 점화식으로 나타내면 $dp[i] = MIN(dp[i - j] + 1, dp[j])$이 되고 현재 값과 비교하는 이유는 현재 사용하는 j원짜리 동전을 사용하지 않는것이 갯수가 더 적을수 있기 때문이다.

이에 따라 모든 동전의 경우에 dp배열을 확인하면 최종적으로 dp[k]에 문제에서 원하는 값이 나온다.

배열을 사용할 때 주의할 점이 동전 가치의 범위가 k보다 크기 때문에 배열의 범위에 벗어나는 동전은 확인하지 않아야 한다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val coin = IntArray(n)
    val dp = IntArray(k + 1){k + 1}
    for(i in 0 until n){
        coin[i] = br.readLine().toInt()
        if(coin[i] <= k){
            dp[coin[i]] = 1
        }
    }
    coin.sort()
    for(i in 0 until n){
        for(j in coin[i]..k){
            if(dp[j - coin[i]] + 1 < dp[j]){
                dp[j] = dp[j - coin[i]] + 1
            }
        }
    }
    if(dp[k] == k + 1) dp[k] = -1
    println(dp[k])
}
```