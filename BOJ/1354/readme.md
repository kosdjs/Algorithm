# 백준 1354번: 무한 수열 2

> 문제: https://www.acmicpc.net/problem/1354

### 문제 풀이

DP

$arr[i] = A_i (i < 3,500,000)$

재귀 함수 A를 이용해 $A_i$를 구함

$i <= 0$이면 $A_i$가 1이므로 1 반환

$i >= 3,500,000$이면 배열의 범위를 넘어가므로 재귀로 $A_i$를 구함

$i < 3,500,000$일 때 $arr[i] = 0$ 이면 아직 $A_i$를 구한적이 없으므로 값을 구해 배열에 저장한 후 반환하고, $arr[i]$가 0이 아니면 배열에 저장된 $A_i$ 값 반환

함수 A(N)의 결과값을 출력하면 정답

### 풀이 설명

N, P, Q, X, Y가 주어졌을 때 다음과 같은 무한 수열 A에 대해 $A_N$을 구하면 되는 문제이다.

- $A_i = 1 (i ≤ 0)$
- $A_i = A⌊i/P⌋-X + A⌊i/Q⌋-Y (i ≥ 1)$

수열 A가 더 작은 값의 수열 A 두개를 더해서 계산하기 때문에 이를 재귀 함수를 이용해 구한다. 이 때 이미 계산된 $A_i$ 값이라면 그 값을 사용해 계산을 줄인다.

하지만 N의 범위가 상당히 크기 때문에 모든 i에 대해 $A_i$를 구할 수는 없다.

따라서 일정 범위의 $A_i$ 까지만 구해놓고 큰 범위의 값은 계산을 하는 방법을 사용해야 한다.

따라서 $i$가 $3,500,000$보다 작은 $A_i$를 계산할 때 배열을 확인해 값이 있으면 사용을 하고 없으면 계산해 배열에 저장한다.

이에 따라 재귀함수를 사용해 $A_N$을 구해 출력하면 정답이 된다.

배열 대신에 맵을 사용해 필요한 모든 $A_i$를 구해놓고 사용하는 방법도 있지만 매번 i가 맵에 키로 존재하는지 확인하기 때문에 시간이 좀 걸린다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

val arr = LongArray(3500000)
var P = 0
var Q = 0
var X = 0
var Y = 0

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toLong()
    P = st.nextToken().toInt()
    Q = st.nextToken().toInt()
    X = st.nextToken().toInt()
    Y = st.nextToken().toInt()
    println(A(N))
}

fun A(N: Long): Long{
    if(N <= 0){
        return 1
    } else if(N >= 3500000){
        return A(N / P - X) + A(N / Q - Y)
    } else if(arr[N.toInt()] != 0L) {
        return arr[N.toInt()]
    } else {
        arr[N.toInt()] = A(N / P - X) + A(N / Q - Y)
        return arr[N.toInt()]
    }
}
```