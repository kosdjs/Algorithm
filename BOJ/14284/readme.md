# 백준 14284번: 간선 이어가기 2

> 문제: https://www.acmicpc.net/problem/14284

### 문제 풀이

다익스트라

$dist[i] = s$부터 $i$까지 연결되는 간선의 가중치 합의 최솟값

$Node(idx, cost) = s$부터 $idx$까지 가는 간선의 가중치 합이 $cost$인 경우

큐에 $Node(s, 0)$을 넣고 큐가 비거나 큐에서 t가 나올때까지 다음 순서를 반복함

1. 큐에서 $Node$를 꺼내 $cost$가 현재 $dist$ 배열의 값보다 크다면 반복문을 건너뜀

2. $Node$의 점 $cur$에 연결된 점 $next$에 대해 $cur$를 거쳐가는 비용이 현재 $dist$에 저장된 값보다 작은지 확인함

3. 값이 작다면 $dist$값 갱신 및 큐에 $Node(next, dist[next])$를 넣음

그 후에 $dist[t]$을 출력하면 정답

### 풀이 설명

문제에서 $s$와 $t$가 연결되는 순간에 간선 추가를 멈추고 이때 간선의 가중치의 합이 최소가 되게 순서를 바꾼다는 것은 주어진 간선들 중 $s$와 $t$ 사이의 최단거리가 되는 간선들만 연결하게 순서를 바꾼다는 뜻이 된다.

이때 가중치의 합은 최단거리의 가중치의 합이 되는 것이므로 $s$와 $t$ 사이의 최단거리를 구하면 된다. 그러므로 특정 정점 $s$에서 시작하는 최단거리를 구하는 문제이므로 다익스트라 알고리즘을 사용할 수 있다.

다익스트라 알고리즘을 사용하기 위해 $dist[i]$를 $s$부터 $i$까지 연결되는 간선의 가중치 합의 최솟값으로 정의하고, $Node(idx, cost)$를 $s$부터 $idx$까지 가는 간선의 가중치 합이 $cost$인 경우로 정의한다.

그 후 알고리즘에 맞게 계산하기 위해 큐에 $Node(s, 0)$을 넣고 큐에서 정점을 하나씩 꺼내 현재 확인하는 정점을 거치면 비용이 줄어드는 정점의 경우에만 $dist$ 배열의 값을 갱신하고 큐에 넣는다.

이 과정을 정점 $t$ 가 나올때까지 반복하면 $dist[t]$에 $s$에서 시작해 $t$까지 가는 최단거리가 저장되어 있으므로 이 값을 출력하면 된다.

### 소스 코드
```kotlin
import java.util.PriorityQueue
import java.util.StringTokenizer

data class Node(val Idx: Int, val cost: Int): Comparable<Node>{
    override fun compareTo(other: Node): Int {
        return this.cost - other.cost
    }
}

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val graph = Array(n + 1){ ArrayList<Pair<Int, Int>>() }
    repeat(m){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        graph[a].add(b to c)
        graph[b].add(a to c)
    }
    val dist = IntArray(n + 1){Int.MAX_VALUE}
    st = StringTokenizer(br.readLine())
    val s = st.nextToken().toInt()
    val t = st.nextToken().toInt()
    dist[s] = 0
    val pq = PriorityQueue<Node>()
    pq.add(Node(s, 0))
    while (pq.isNotEmpty()){
        val (cur, cost) = pq.poll()
        if(cur == t) break
        if(cost > dist[cur]) continue
        for((next, weight) in graph[cur]){
            if(dist[next] > dist[cur] + weight){
                dist[next] = dist[cur] + weight
                pq.add(Node(next, dist[next]))
            }
        }
    }
    println(dist[t])
}
```