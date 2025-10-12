# 백준 28069번: 김밥천국의 계단

> 문제: https://www.acmicpc.net/problem/28069

### 문제 풀이

DP

$dp[i] = i$번째 계단을 오를 수 있는 최소 행동 횟수

$dp[0]$을 $0$으로 초기화하고 나머지를 Int.MAX_VALUE로 초기화

$i$를 $0$부터 $N$까지 반복하며 다음 점화식을 확인하며 dp 배열의 값을 변경함

$dp[i + 1] = min(dp[i + 1], dp[i] + 1)$

$dp[i + i / 2] = min(dp[i + i / 2], dp[i] + 1)$

$dp[N]$이 $K$보다 작거나 같으면 minigimbob 출력 아니면 water 출력하면 정답

### 풀이 설명

가장 아래 계단의 번호가 0번이며, 위로 올라가면서 순서대로 번호가 붙어있습니다. 그중 
$N$번째 계단 옆에 김밥 가게가 있습니다.

매번 다음의 2가지 행동 중 하나를 선택해서 총 $K$번 행동할 수 있으며, 정확히 $K$번째 행동에서 $N$번째 계단에 도달하면 미니김밥을 먹을 수 있습니다.

1. 계단 한 칸을 올라갑니다.
2. 민희가 집에서 가지고 온 지팡이를 계단에 두드립니다. 만약 민희가 $i$ 번째 계단에서 지팡이를 두드리면  $i +\left \lfloor \cfrac{i}{2} \right \rfloor$번째 계단으로 순간이동합니다.

현재 0번째 계단에 있을 때 미니 김밥을 먹을 수 있는지를 구해야 한다.

먼저 두 번째 행동을 생각했을 때 0번째 계단에서 실행하면 제자리에서 횟수를 늘릴 수 있다. 따라서 $N$번째 계단까지 가는 최단 경로를 구했을 때 횟수가 부족하다면 0번째 계단에서 횟수를 늘려놓고 최단 경로로 이동하면 정확히 $K$번으로 맞출 수 있다.

따라서 $0$번째 계단에서 $N$번째 계단까지 가는 최단 경로를 구하면 되는데 이는 DP로 구할 수 있다. $dp[i]$를 $i$번째 계단까지 갈 수 있는 최소 횟수로 정의하고 0번째 계단에서 시작하기 때문에 $dp[0]$을 0으로 초기화 하고 나머지 $dp$배열의 값을 Int.MAX_VALUE로 초기화한다.

1번 행동은 그냥 한 칸을 올라가는 것이고, 2번 행동을 통해 도착하는게 더 빠를 수 있기 때문에 점화식이 다음과 같이 나온다.
$dp[i + 1] = min(dp[i + 1], dp[i] + 1)$

2번 행동의 점화식도 다른 경우로 가는 것이 더 빠를 수 있기 때문에 점화식이 다음과 같이 나온다.
$dp[i + i / 2] = min(dp[i + i / 2], dp[i] + 1)$

$0$에서부터 $N$까지인 $i$에 대해 앞서 나왔던 점화식에 따라 $dp$배열의 값을 구해 계단을 오르는 최소 횟수를 구한다.

그러면 $dp[N]$에 $N$번째 계단을 오를 때 최소 횟수가 저장되게 되고 이 값이 $K$보다 작거나 같다면 정확히 $K$번째 행동으로 $N$번째 계단을 오를 수 있기 때문에 minigimbob을 출력하고 아니라면 water를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dp = IntArray(N + 1){Int.MAX_VALUE}
    dp[0] = 0
    for(i in 0..N){
        if(i + 1 <= N){
            dp[i + 1] = minOf(dp[i + 1], dp[i] + 1)
        }
        if(i + i / 2 <= N){
            dp[i + i / 2] = minOf(dp[i + i / 2], dp[i] + 1)
        }
    }
    if(dp[N] <= K){
        println("minigimbob")
    } else {
        println("water")
    }
}
```