# 백준 11657번: 타임머신

> 문제: https://www.acmicpc.net/problem/11657

### 문제 풀이

최단 경로, 벨만-포드

dist[i] = 1번 도시부터 i번 도시까지 가는 최단 경로, Long.MAX_VALUE라면 i번 도시로 가는 경로가 없는 것

edges = 두 도시 사이의 경로

hasNegativeCycle = 음수 사이클 존재 여부

1번 도시부터 다른 도시까지의 최단 경로를 구해야 하고 경로 중 가중치가 음수인 경우가 있으므로 벨만-포드 알고리즘을 사용해야 함

dist[1]을 0으로 초기화하고 나머지를 Long.MAX_VALUE로 초기화

dist가 Long.MAX_VALUE가 아닌 점들에 대해 모든 간선들을 확인해 최단 거리를 갱신을 함

이 과정을 N - 1번 반복해 최단 거리를 구하고 마지막으로 모든 간선을 다시 확인해서 음수 사이클 존재 여부를 확인함

음수 사이클이 존재한다면 -1 출력, 아니라면 dist[2]번부터 dist[N]까지 한줄에 하나씩 출력하면 정답

### 풀이 설명

N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 시간 C가 양수가 아닌 경우가 있다. C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.

1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

타임머신으로 시간을 되돌린다는 것은 음수 가중치를 가지는 경로가 있다는 뜻으로 벨만-포드 알고리즘을 이용해 최단 거리를 구해야 한다는 뜻이다. 또한 시작점이 1번 노드로 정해져 있으므로 거리 배열을 1번 노드를 제외한 나머지 노드는 최댓값으로 놓고 시작해야 한다.

여기서 N이 최대 500, M이 최대 6,000, 가중치가 최소 -10,000이므로 음의 사이클이 존재한다고 했을 때 거리 배열을 Int로 잡으면 언더플로우가 일어나 거리가 갱신되지 않아서 음의 사이클을 판별하지 못할 수 있다. 따라서 거리 배열을 Long으로 잡아야 한다.

이에 따라 거리 배열 dist를 정의한 후 1번 인덱스를 0으로 초기화하고 나머지 값은 Long.MAX_VALUE로 놓아서 방문하지 않았다는 것을 나타낸다. 이후에 모든 간선을 확인하며 현재까지 방문했던 노드를 거치는 최단 거리를 갱신하는 것을 N - 1번 반복해 최단 거리 배열을 안정화시킨다.

그 이후에 다시 모든 간선을 확인해 최단 거리 배열이 갱신되는지 확인해 음수 사이클 여부를 판단해 hasNegativeCycle에 저장한다. 이 값에 따라 -1을 출력하거나 dist 배열의 2번부터 N번까지를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt():Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val dist = LongArray(N + 1){Long.MAX_VALUE}
    dist[1] = 0
    val edges = ArrayList<IntArray>()
    repeat(M){
        val A = nextInt()
        val B = nextInt()
        val C = nextInt()
        edges.add(intArrayOf(A, B, C))
    }
    repeat(N - 1){
        for((A, B, C) in edges){
            if(dist[A] != Long.MAX_VALUE && dist[B] > dist[A] + C){
                dist[B] = dist[A] + C
            }
        }
    }
    var hasNegativeCycle = false
    for((A, B, C) in edges){
        if(dist[A] != Long.MAX_VALUE && dist[B] > dist[A] + C){
            hasNegativeCycle = true
            break
        }
    }
    if(hasNegativeCycle){
        println("-1")
    } else {
        for(i in 2..N){
            println("${if(dist[i] != Long.MAX_VALUE) dist[i] else -1}")
        }
    }
}
```