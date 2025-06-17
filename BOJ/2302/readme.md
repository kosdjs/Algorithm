> 문제: https://www.acmicpc.net/problem/2302

### 문제 풀이

DP

dp[i]는 i개의 연속된 일반 좌석이 있을 때 가능한 배치 방법의 수

dp[i] = dp[i-1] + dp[i-2]

앞에서부터 vip 좌석 사이의 일반 좌석 수를 세서 dp 배열의 값을 참조해 곱하면 답

### 풀이 설명

문제에서 일반 좌석만 인접한 자리에 앉을 수 있다고 했기 때문에 일반 좌석에 따라 가짓수가 늘어남

일반 좌석이 일렬로 한 자리씩 늘어난다고 생각했을 때 새로 추가된 좌석이 인접한 자리는 하나이고 이 좌석이 교환할지 안할지 두가지 선택지가 있기 때문에 새로 추가했을 때는 새로 추가된 좌석이 교환하는 가짓수와 교환하지 않는 가짓수가 된다

새로 추가한 좌석이 n번째 좌석이라고 생각했을 때 이 좌석이 교환하지 않는다면, 1번부터 n-1번 까지 좌석 배치가 가능한 가짓수가 되고 이는 dp[n-1]이 된다

n번째 좌석이 n-1번째 좌석과 교체를 한다면, 1번부터 n-2번까지 좌석 배치가 가능한 가짓수가 되고 이가 dp[n-2]가 된다

즉 dp[n] = dp[n-1] + dp[n-2]가 된다.

여기서 dp[0]도 생각을 해야하는 이유가 모든 좌석이 vip좌석일때는 일반좌석이 없어서 좌석이 고정되기 때문에 1을 저장해놓아야 함

### 소스 코드
```kotlin
import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    val dp = IntArray(N+1)
    dp[0] = 1
    dp[1] = 1
    for(i in 2..N){
        dp[i] = dp[i-1] + dp[i - 2]
    }
    var answer = 1
    var last = 0
    for(i in 1..M){
        val vip = br.readLine().toInt()
        answer *= dp[vip - last - 1]
        last = vip
    }
    answer *= dp[N - last]

    println(answer)
}
```