> 문제: https://www.acmicpc.net/problem/1753

### 문제 풀이

다익스트라 알고리즘을 활용한 최단경로 구하기

`dist[i]`를 시작점에서 i점 까지의 최단경로로 정의함, 값이 `Int.MAX_VALUE`라면 시작점부터 i점까지 가는 경로가 없음을 의미함

`visit[i]`는 이 점을 통하는 경로를 확인했는지를 의미함

#### 동작 순서

1. `dist` 배열에서 이전에 방문하지 않은 점 중 값이 제일 작은 점을 선택함
2. 그 점을 `u`라고 했을 때 방문처리를 하고 인접한 경로를 조사해 다음 점 `v`와 경로의 가중치 `w`를 구함
3. 현재 저장된 `dist[v]` 와 `dist[u] + w`를 비교해 더 작은 값을 `dist[v]`에 저장
4. 모든 인접한 점 `v`에 대해 처리가 끝나면 1번으로 돌아감. 이 과정을 그래프 탐색을 통해 방문할 수 있는 모든 점을 방문할 때까지 반복함

이러한 과정을 통해 시작점부터 i점까지의 최단 경로를 dist 배열에 구할 수 있음

### 풀이 설명

문제에서 주어진 시작점에서의 최단 경로를 구하는 문제이기 때문에 모든 경로의 최단 경로를 구하는 플로이드 워셜 알고리즘 보다는 다익스트라 알고리즘이 더 효율적임

또한 문제에서 정점은 최대 20,000개 간선이 최대 300,000개라고 했으므로 행렬로 그래프를 나타내기보다 인접 리스트로 그래프를 나타내는게 시간 복잡도가 더 줄어듬

- 우선순위 큐(힙) 사용 시: O((V + E) log V)
(V: 정점 개수, E: 간선 개수)

- 단순 배열 사용 시: O(V²)

우선순위 큐 사용시 주의해야 할 점은 요소가 정렬되는 것이 힙의 특성에 따라 요소를 삽입/삭제하는 과정에서 이루어지기 때문에 문제에 사용한 것 처럼 정렬 기준이 되는 값이 과정 중에 변경된다면 값을 다시 삽입/삭제하는 과정이 필요함

따라서 문제에서 우선순위 큐의 정렬 기준을 `dist`배열의 값으로 정했고 큐에서 점을 하나 꺼내면 인접 경로를 확인해 `dist`배열의 값을 변경하기 때문에 인접 점을 다시 큐에 삽입해야 함

또한 경로가 없다는 것을 나타내기 위한 값을 `Int.MAX_VALUE` 즉 `Int` 자료형의 최댓값으로 정했기 때문에 이 값에 가중치를 더하면 오버플로우가 일어나 값이 이상해질 수 있음, 이런 경우엔 모든 점을 확인하지 않고 시작점에서 탐색을 통해 도달할 수 있는 점만 확인해야 함. 우선순위 큐를 이용해 인접 점만 검사하기 때문에 이런 상황이 일어나지 않았지만 주의해야 함.

>다익스트라 알고리즘에서 경로의 최댓값은 정점의 최대 갯수와 가중치의 최댓값을 알면 구할 수 있다. 시작점과 끝점 사이를 모든 정점을 한 번씩 거쳐 도착하는데 이 때 경로의 가중치가 항상 최댓값일 경우를 구하면 된다.
- 정점의 갯수를 $V$, 가중치의 최댓값을 $w$라고 할 때 $(V - 1) \times w$

### 소스 코드
```kotlin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val V = st.nextToken().toInt()
    val E = st.nextToken().toInt()
    val start = br.readLine().toInt()
    val graph = ArrayList<ArrayList<IntArray>>()
    for(i in 0..V){
        graph.add(arrayListOf())
    }
    for (i in 1..E){
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        graph[u].add(intArrayOf(v, w))
    }
    val dist = IntArray(V + 1){ Int.MAX_VALUE }
    dist[start] = 0
    val visit = BooleanArray(V + 1){false}
    for (route in graph[start]){
        dist[route[0]] = route[1]
    }
    val pq = PriorityQueue<Int>{ o1, o2 -> dist[o1] - dist[o2] }
    pq.add(start)
    while (pq.isNotEmpty()){
        val u = pq.poll()
        if(visit[u]) continue
        visit[u] = true
        for(route in graph[u]){
            val v = route[0]
            val w = route[1]
            if(dist[v] > dist[u] + w){
                dist[v] = dist[u] + w
            }
            pq.add(v)
        }
    }
    for(i in 1..V){
        if(dist[i] != Int.MAX_VALUE){
            println(dist[i])
        } else {
            println("INF")
        }
    }
}
```