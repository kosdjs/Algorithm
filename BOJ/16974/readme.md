# 백준 16974번: 레벨 햄버거

> 문제: https://www.acmicpc.net/problem/16974

### 문제 풀이

DP

patty[i] = 레벨-i 버거의 패티 수

layer[i] = 레벨-i 버거의 패티와 빵의 수

answer = 먹은 패티의 수

half = 레벨-i 버거에 추가된 가운데 패티까지의 장 수

현재 먹는 버거의 레벨을 i, 버거의 아래 X장을 먹을 때 

X가 layer[i]와 같다면 버거 전체를 먹은 것이므로 answer에 patty[i]를 더해주고 반복문 탈출

레벨-i 버거를 half 장 먹으면 레벨-(i - 1) 버거와 패티 한장을 먹은것이므로 answer에 patty[i - 1] + 1을 더하고 반복문 탈출

그보다 많이 먹었다면 X - half 장을 레벨-(i - 1) 버거에서 먹는 것이므로 X를 half만큼 빼주고 i를 1 줄임

half 장보다 적게 먹었다면 맨 뒤의 빵과 레벨-(i - 1) 버거의 일부를 먹은 것이므로 X에서 맨 뒤의 빵 1장을 줄이고, i를 줄여 다시 반복함

이 반복을 X가 0보다 크고 i가 0 이상일때 동안 반복하면 answer에 먹은 총 패티수가 저장되므로 이를 출력하면 정답

### 풀이 설명

레벨-0 버거는 패티만으로 이루어져 있다. 레벨-L 버거는 햄버거번, 레벨-(L-1) 버거, 패티, 레벨-(L-1)버거, 햄버거번으로 이루어져 있다. (L ≥ 1)

예를 들어, 레벨-1 버거는 'BPPPB', 레벨-2 버거는 'BBPPPBPBPPPBB'와 같이 생겼다. (B는 햄버거번, P는 패티)

상도가 상근날드에 방문해서 레벨-N 버거를 시켰다. 상도가 햄버거의 아래 X장을 먹었을 때, 먹은 패티는 몇 장일까? 한 장은 햄버거번 또는 패티 한 장이다.

레벨-L 버거를 가운데 패티 한장을 빼고 반으로 나누면 레벨-(L - 1) 버거에 빵 한장이 붙은 상태가 된다. 따라서 현재 버거의 전체 장 수를 2로 나눈 몫만큼 먹었다면 레벨-(L - 1) 버거의 패티 수만큼 먹은 것이다.

즉, 버거의 전체 장 수와 패티 수를 레벨마다 저장해놓으면 현재 먹은 장 수에 따라 패티를 몇장 먹었는지 알 수 있다.

현재 버거의 레벨을 i, 현재 먹은 장 수를 X라고 하고, patty[i]가 현재 버거의 패티 수, layer[i]가 현재 버거의 장 수라고 하면 현재 버거에서 절반을 나누고 가운데 패티까지 더하면 layer[i] / 2 + 1장이 된다. 이를 half로 정의한다.

먹은 패티 수를 저장하기 위해 answer 변수를 정의하고 X에 따른 패티 수를 세기 시작한다. 만약 X가 layer[i]와 같다면 레벨-i 버거 전체를 먹은 것이므로 answer에 patty[i]를 더해주고 반복문을 빠져나온다.

만약 X가 half보다 크다면 레벨-i의 정 가운데 패티를 넘어서게 먹은 것이므로 레벨-(i - 1) 버거를 추가로 X - half 장 먹은 것이 된다. 따라서 X에서 half를 빼고 i를 1 감소시킨다.

X가 half와 같다면 레벨-i의 정 가운데 패티까지 먹은 것이므로 패티는 레벨-(i - 1)의 패티 수와 정 가운데 패티 만큼 먹은것이다. 따라서 answer에 patty[i - 1] + 1을 더해주고 반복문을 탈출한다.

X가 half보다 작다면 레벨-i 버거의 빵 한장과 레벨-(i - 1) 버거를 X - 1장 먹은 것이므로 X와 i를 1씩 감소시킨다.

이를 X가 0이 될때까지 반복하면 버거를 계속 반으로 나누며 먹은 패티의 수를 세어서 answer에 저장하게 된다. 따라서 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    var X = st.nextToken().toLong()
    val patty = LongArray(N + 1)
    val layer = LongArray(N + 1)
    patty[0] = 1
    layer[0] = 1
    for(i in 1..N){
        patty[i] = patty[i - 1] * 2 + 1
        layer[i] = layer[i - 1] * 2 + 3
    }
    var answer = 0L
    var idx = N
    while(X > 0 && idx >= 0){
        if(X == layer[idx]){
            answer += patty[idx]
            break
        }
        val half = layer[idx] / 2 + 1
        if(X > half){
            answer += patty[idx - 1] + 1
            X -= half
            idx--
        } else if(X == half){
            answer += patty[idx - 1] + 1
            X = 0
        } else {
            X--
            idx--
        }
    }
    println(answer)
}
```