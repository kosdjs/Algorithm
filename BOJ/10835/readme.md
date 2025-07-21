# 백준 10835번: 카드게임

> 문제: https://www.acmicpc.net/problem/10835

### 문제 풀이

DP

$dp[i][j]$ = 왼쪽 카드 더미의 i 번째 카드, 오른쪽 카드 더미의 j 번째 카드가 앞에 있을 때 낼 수 있는 최대 점수

$dp[i][j] = max(dp[i + 1][j], dp[i + 1][j + 1])$ (오른쪽 카드가 왼쪽 카드보다 작지 않을 때)
$dp[i][j] = max(dp[i + 1][j], dp[i + 1][j + 1], dp[i][j + 1] + rightCards[j])$ (오른쪽 카드가 왼쪽 카드보다 작을 때)

$dp$ 테이블을 $N, N$부터 역순으로 $1, 1$까지 채우면 $dp[1][1]$이 정답

### 풀이 설명

왼쪽 카드 더미에 카드가 $i$ 장, 오른쪽 카드 더미에 카드가 $j$ 장 있을 때 할 수 있는 행동은 왼쪽 카드 하나 버리기, 왼쪽과 오른쪽 모두 카드 하나 버리기, 왼쪽 카드보다 오른쪽 카드가 작다면 오른쪽 카드 하나 버리기이다.

이 때 $dp[i][j]$를 왼쪽 카드 $i$장, 오른쪽 카드 $j$장이 있을 때 낼 수 있는 최대 점수라고 하면 비교해야 하는 값이 할 수 있는 행동에 따라 $dp[i + 1][j], dp[i + 1][j + 1], dp[i][j + 1] + rightCards[j]$로 나뉘게 된다. 여기서 인덱스를 보면 현재 인덱스를 계산하는데 다음 인덱스를 참고하기 때문에 반복문을 인덱스의 내림차순으로 해야 한다.

그리고 왼쪽 카드보다 오른쪽 카드가 작을 때만 오른쪽 카드 한 장을 버릴 수 있고 이때 점수가 나기 때문에 이 때 오른쪽 카드의 수를 점수에 더하고 비교한다. 이렇게 $dp$ 테이블을 구하고 나면 문제에서 주어진 왼쪽 카드 더미의 첫 번째 카드, 오른쪽 카드 더미의 첫 번째 카드가 앞에 나와 있을 때 낼 수 있는 최대 점수가 $dp[1][1]$에 저장되어 있으므로 이 값을 출력하면 된다.

$dp[i][j]$를 처음 상태에서 현재 왼쪽 카드 $i$장, 오른쪽 카드 $j$장이 남을 때까지의 누적 점수 최댓값으로 계산하면 안되는 이유가 현재 인덱스까지의 값에서 다음 인덱스로 가는 값이 3가지로 나뉘기 때문에 이는 오히려 경우의 수가 많아져 복잡해 지고, 다음 인덱스의 값을 먼저 참조해야하기 때문에 올바른 값이 나오지 않을 수 있기 때문이다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val leftCards = IntArray(N + 1)
    val rightCards = IntArray(N + 1)
    var st = StringTokenizer(br.readLine())
    for(i in 1..N){
        leftCards[i] = st.nextToken().toInt()
    }
    st = StringTokenizer(br.readLine())
    for(i in 1..N){
        rightCards[i] = st.nextToken().toInt()
    }
    val dp = Array(N + 2){IntArray(N + 2){ 0 }}
    for(i in N downTo 1){
        for(j in N downTo 1){
            dp[i][j] = maxOf(dp[i + 1][j], dp[i + 1][j + 1])
            if(leftCards[i] > rightCards[j]){
                dp[i][j] = maxOf(dp[i][j], dp[i][j + 1] + rightCards[j])
            }
        }
    }
    println(dp[1][1])
}
```