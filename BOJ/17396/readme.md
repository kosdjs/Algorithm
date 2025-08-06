# 백준 17396번: 백도어

> 문제: https://www.acmicpc.net/problem/17396

### 문제 풀이

다익스트라

$dist[i] = 0$에서 $i$까지 가는 최소 시간

$Node(idx, cost) = 1$부터 $idx$까지 가는 시간이 $cost$인 경우

큐에 $Node(1, 0)$을 넣고 큐가 빌때까지 다음 순서를 반복함

1. 큐에서 $Node$를 꺼내 $cost$가 현재 $dist$ 배열의 값보다 크다면 반복문을 건너뜀

2. $Node$의 점 $cur$에 연결된 점 $next$에 대해 $cur$를 거쳐가는 비용이 현재 $dist$에 저장된 값보다 작은지 확인함 $($이때 $next$가 시야에 있고 $N - 1$이 아니라면 건너뜀$)$

3. 값이 작다면 $dist$값 갱신 및 큐에 $Node(next, dist[next])$를 넣음

그 후에 $dist[N - 1]$을 출력하면 정답

### 풀이 설명

현재 $0$번째 분기점에 있고 $N - 1$번째 분기점까지 시야에 걸리지 않고 가는 최소 시간을 구해야 하는 상황이고 이는 특정 시작점에서 음수가 아닌 비용이 있는 그래프의 최단 거리를 구하는 것과 같으므로 다익스트라 알고리즘으로 풀 수 있다.

여기서 시야에 걸리지 않아야 한다는 조건이 있기 때문에 마지막 $N - 1$번째 분기점을 제외한 나머지 분기점에서 시야가 있는 점은 방문하지 않으면 된다.

다익스트라 알고리즘을 사용하기 위해 $dist[i]$를 $0$에서 $i$까지 가는 최소 시간, $Node(idx, cost)$를 $1$부터 $idx$까지 가는 시간이 $cost$인 경우로 정의한다. 이때 $N$이 $100,000$ $t$가 $100,000$까지 이므로 시간의 최대 범위가 Int의 범위를 넘기 때문에 Long으로 계산해야 한다.

그 후에 $Node(0, 0)$을 큐에 넣고 큐에서 $N - 1$이 나올 때까지 시야에 있지 않은 다음 점의 시간을 확인해 현재 점을 거쳐가는 것이 더 빠른지 확인하고 더 빠를때만 큐에 $Node$를 넣어 시야에 닿지 않는 최소 시간을 찾는다.

큐에서 $N - 1$이 나오게 되면 $dist[N - 1]$에 최소 시간이 저장된 것이므로 경로를 더 확인할 필요 없이 반복문을 빠져나오고 $dist[N - 1]$을 출력하면 되고, 가능한 모든 경로를 탐색한 후에 $dist[N - 1]$에 Long.MAX_VALUE가 저장되어있다면 가능한 경로가 없는 것이므로 -1을 출력하면 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue

data class Node(val idx: Int, val cost: Long): Comparable<Node>{
    override fun compareTo(other: Node): Int {
        return if(this.cost < other.cost) -1 else if(this.cost == other.cost) 0 else 1
    }
}

fun main(){
    val br = System.`in`.bufferedReader()
    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val sight = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    val dist = LongArray(N){ Long.MAX_VALUE}
    val graph = Array(N){ ArrayList<Pair<Int, Int>>() }
    repeat(M){
        val (a, b, t) = br.readLine().split(" ").map { it.toInt() }
        graph[a].add(b to t)
        graph[b].add(a to t)
    }
    val pq = PriorityQueue<Node>()
    dist[0] = 0
    pq.add(Node(0, 0))
    while (pq.isNotEmpty()){
        val (cur, cost) = pq.poll()
        if(cur == N - 1) break
        if(cost > dist[cur]) continue
        for((next, weight) in graph[cur]){
            if(sight[next] == 1 && next != N - 1) continue
            if(dist[next] > dist[cur] + weight){
                dist[next] = dist[cur] + weight
                pq.add(Node(next, dist[next]))
            }
        }
    }
    if(dist[N - 1] == Long.MAX_VALUE){
        println(-1)
    } else {
        println(dist[N - 1])
    }
}
```