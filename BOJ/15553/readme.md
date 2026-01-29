# 백준 15553번: 난로

> 문제: https://www.acmicpc.net/problem/15553

### 문제 풀이

그리디

diff = 연이은 친구가 방문하는 중간 시간

answer = 난로를 틀어야 하는 최소 시간

이전 친구가 방문한 시간을 prev, 현재 친구가 방문한 시간을 cur라고 하면 중간 시간은 cur와 prev 사이의 시간이므로 cur - prev - 1이 됨

이에 따라 모든 중간 시간을 diff에 저장하고 오름차순으로 정렬함

먼저 N명의 친구가 방문하는 1시간 동안은 무조건 난로를 틀어야 하므로 answer에 N을 대입함

diff에서 N - K개의 중간 시간을 answer에 더하면 난로를 틀어야 하는 최소 시간이므로 출력하면 정답

### 풀이 설명

친구가 한 번 방문하면 한 시간동안 있고, N명의 친구가 각각 겹치지 않게 올 때 친구가 있을 동안에는 난로가 켜져 있어야 한다. 그리고 난로는 성냥으로 켜야 하는데 성냥이 K개 있을 때 난로를 최소한으로 키면서 친구가 있을 동안 난로가 켜져있도록 하려면 최소 몇시간 난로를 틀어야 하는지 구하는 문제이다.

먼저 N명의 친구가 각각 다른 N시간에 방문하는데 친구가 있을동안에는 난로가 켜져있어야 하므로 일단 최소 N시간은 난로를 틀어야 한다는 것이다. 그리고 난로를 K번만 틀 수 있다는 것은 N - K번은 연이은 친구가 방문하는 중간 사이에도 난로를 틀어야 한다는 뜻이다.

예시처럼 친구가 1, 3, 6 시간에 방문을 할 때 성냥이 2개밖에 없다면 1번은 연이은 친구가 방문하는 중간 사이에 난로를 틀어야 하기 때문에 1, 3 시간에 방문하는 친구 사이가 더 시간이 짧기 때문에 이 때 중간 시간 1시간을 난로를 더 틀기 때문에 4시간을 틀어야 하는 것이다.

따라서 친구가 방문하는 N시간은 무조건 난로를 틀고 N - K만큼 친구가 방문하는 중간 시간에 난로를 더 트는 것이므로 중간 시간을 모두 구해놓고 시간이 낮은 순서대로 N - K개의 중간 시간을 고르면 되는 문제이다.

그러므로 diff라는 이름의 배열을 정의해 N - 1개의 중간 시간을 넣고 오름차순으로 정렬을 한다. 그 이후에 최소 시간을 저장할 변수 answer를 정의하고 먼저 N명이 방문하는 N시간은 무조건 난로를 틀어야 하기 때문에 N으로 초기화한다.

그 이후에 diff를 오름차순으로 정렬했기 때문에 배열의 앞에서 N - K개의 중간 시간을 꺼내서 answer에 더하면 K개의 성냥으로 N명이 방문했을 때 틀어야 할 난로의 최소 시간이 되는 것이므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val K = nextInt()
    val diff = IntArray(N - 1)
    var prev = nextInt()
    for(i in 0 until N - 1){
        val cur = nextInt()
        diff[i] = cur - prev - 1
        prev = cur
    }
    diff.sort()
    var answer = N
    for(i in 0 until N - K){
        answer += diff[i]
    }
    println(answer)
}
```