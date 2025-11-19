# 백준 1149번: RGB거리

> 문제: https://www.acmicpc.net/problem/1149

### 문제 풀이

DP

prevR, G, B = 이전 집을 빨강, 초록, 파랑으로 칠했을 때의 최소 비용
curR, G, B = 현재 집을 빨강, 초록, 파랑으로 칠할 때의 최소 비용

현재 집의 색을 빨강, 초록, 파랑으로 칠하려면 이전 집이 현재 집으로 칠하는 색과 달라야 하므로 다음과 같은 점화식이 나옴

curR = min(prevG, prevB) + R (R은 현재 집을 빨강으로 칠하는 비용)
curG = min(prevR, prevB) + G (G는 현재 집을 초록으로 칠하는 비용)
curB = min(prevR, prevB) + B (B는 현재 집을 파랑으로 칠하는 비용)

이에 따라 점화식에 맞게 curR, G, B를 구하고 매번 prevR, G, B에 대입해 N번 집을 빨강, 초록, 파랑으로 칠할 때의 최소 비용을 prevR, G, B에 저장하고 이 중 최솟값을 출력하면 정답

### 풀이 설명

RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.

집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.

- 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
- N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
- i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

마지막 조건을 읽어보면 인접한 번호의 집과는 다른 색을 칠해야 한다는 것을 나타내는 것이므로 다음과 같이 다시 쓸 수 있다.

- i(2 ≤ i ≤ N)번 집의 색은 i-1번 집의 색과 같지 않아야 한다.

인접한 번호 중 작은 번호을 이전 집, 큰 번호를 현재 집이라고 하면 현재 집을 빨강으로 칠하려면 이전 집이 초록 또는 파랑으로 칠해져있어야 한다. 현재 집을 초록으로 칠하려면 이전 집이 빨강 또는 파랑, 현재 집을 파랑으로 칠하려면 이전 집이 빨강 또는 초록으로 칠해져있어야 한다.

따라서 DP를 이용해 이전 집을 빨강, 초록, 파랑으로 칠했을 때의 비용 최솟값을 prevR, prevG, prevB로 정의해놓고 현재 집을 빨강, 초록, 파랑으로 칠할 때의 최소 비용을 curR, curG, curB로 정의해놓으면 다음과 같은 점화식이 성립한다.

curR = min(prevG, prevB) + R (R은 현재 집을 빨강으로 칠하는 비용)
curG = min(prevR, prevB) + G (G는 현재 집을 초록으로 칠하는 비용)
curB = min(prevR, prevB) + B (B는 현재 집을 파랑으로 칠하는 비용)

이에 따라 1번 집부터 N번 집까지 현재 집을 빨강, 초록, 파랑으로 칠하는 최소 비용을 curR, curG, curB에 구하고 다음 집의 최소 비용을 구하기 위해 구한 값을 각각 prevR, prevG, prevB에 저장한다.

그러면 최종적으로 N번 집을 빨강, 초록, 파랑으로 칠했을 때의 최소 비용이 prevR, prevG, prevB에 저장되므로 이 중 최솟값이 N번 집까지 규칙에 맞게 색을 칠했을 때의 최소 비용이므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var prevR = 0
    var prevG = 0
    var prevB = 0
    for(i in 0 until N){
        val curR = nextInt() + minOf(prevG, prevB)
        val curG = nextInt() + minOf(prevR, prevB)
        val curB = nextInt() + minOf(prevR, prevG)
        prevR = curR
        prevG = curG
        prevB = curB
    }
    println(minOf(prevR, prevG, prevB))
}
```