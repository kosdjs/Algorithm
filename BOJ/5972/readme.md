# 백준 5972번: 택배 배송

> 문제: https://www.acmicpc.net/problem/5972

### 문제 풀이

다익스트라

$dist[i] = 1$에서 $i$까지 가는 최소 비용

$Node(idx, cost) = 1$부터 $idx$까지 가는 비용이 $cost$인 경우

큐에 $Node(1, 0)$을 넣고 큐가 빌때까지 다음 순서를 반복함

1. 큐에서 $Node$를 꺼내 $cost$가 현재 $dist$ 배열의 값보다 크다면 반복문을 건너뜀

2. $Node$의 점 $cur$에 연결된 점 $next$에 대해 $cur$를 거쳐가는 비용이 현재 $dist$에 저장된 값보다 작은지 확인함

3. 값이 작다면 $dist$값 갱신 및 큐에 $Node(next, dist[next])$를 넣음

그 후에 $dist[N]$을 출력하면 정답

### 풀이 설명

이 문제에서 비용을 거리라고 생각하면 비용이 음수가 아니고 특정 시작점에서 특정 점까지의 최단 거리를 구하는 문제이므로 다익스트라 알고리즘을 사용하는 것이 좋다.

따라서 다익스트라 알고리즘을 사용하기 위해 $dist[i]$를 $1$에서 $i$까지 가는 최소 비용으로 정의하고 $Node(idx, cost)$를 $1$부터 $idx$까지 가는 비용이 $cost$인 경우로 정의했다.

큐에서 $Node$를 꺼내 $cost$가 현재 $dist$ 배열의 값보다 큰지 확인하는 이유는 거리가 갱신될 때마다 큐에 $Node$를 삽입하기 때문에 현재 큐에 들어가있는 $Node$의 $cost$가 최소 비용이 아닐 때가 있기 때문이다. 따라서 알고리즘대로 최소 비용일때만 계산을 하기 위해 $dist$에 저장된 최소 비용과 같을 때만 계산을 한다.

그 뒤에는 다익스트라 알고리즘에 따라 현재 확인하는 점을 거쳐가는 경로가 더 비용이 적은지 확인하고 이 경우에만 값을 갱신하고 큐에 $Node$를 넣어 다음 최소 비용으로 갈 수 있는 점을 확인하며 모든 점의 최소 비용을 구하면 된다.

이에 따라 구해진 $dist[N]$이 문제에서 요구하는 $1$에서 $N$까지 가는 최소 비용이기 때문에 이 값을 출력하면 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue

data class Node(val idx: Int, val cost: Int): Comparable<Node> {
    override fun compareTo(other: Node) = this.cost - other.cost
}

fun main() {
    val br = System.`in`.bufferedReader()
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val dist = IntArray(N + 1) { Int.MAX_VALUE }
    val graph = Array(N + 1) { ArrayList<Pair<Int, Int>>() }
    repeat(M) {
        val (A, B, C) = br.readLine().split(" ").map { it.toInt() }
        graph[A].add(B to C)
        graph[B].add(A to C)
    }
    dist[1] = 0
    val pq = PriorityQueue<Node>()
    pq.add(Node(1, 0))
    while (pq.isNotEmpty()) {
        val (cur, cost) = pq.poll()
        if (dist[cur] < cost) continue
        for ((next, weight) in graph[cur]) {
            if (dist[next] > dist[cur] + weight) {
                dist[next] = dist[cur] + weight
                pq.add(Node(next, dist[next]))
            }
        }
    }
    println(dist[N])
}
```