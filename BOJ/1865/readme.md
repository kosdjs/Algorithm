# 백준 1865번: 웜홀

> 문제: https://www.acmicpc.net/problem/1865

### 문제 풀이

최단 경로, 벨만-포드

dist = 벨만-포드 알고리즘을 사용하기 위한 거리 배열

edges = 도로, 웜홀을 모두 포함한 그래프의 간선들

possible = 특정 점에서 다시 돌아왔을 때 가중치가 음수가 되는 경로가 있는지, 즉, 음수 사이클 존재 여부

음수 사이클 존재 여부만 확인하기 위해 dist 배열을 0으로 초기화해 간선을 확인할 때 모든 점을 확인함

N - 1번 모든 간선을 확인하면서 거리 배열을 갱신하면서 안정화를 거침

마지막으로 모든 간선을 확인하면서 거리 배열이 갱신되는지 확인하며 음수 사이클 여부를 possible에 저장함

possible에 저장된 값에 따라 YES, NO를 출력하면 정답

### 풀이 설명

때는 2020년, 백준이는 월드나라의 한 국민이다. 월드나라에는 N개의 지점이 있고 N개의 지점 사이에는 M개의 도로와 W개의 웜홀이 있다. (단 도로는 방향이 없으며 웜홀은 방향이 있다.) 웜홀은 시작 위치에서 도착 위치로 가는 하나의 경로인데, 특이하게도 도착을 하게 되면 시작을 하였을 때보다 시간이 뒤로 가게 된다. 웜홀 내에서는 시계가 거꾸로 간다고 생각하여도 좋다.

시간 여행을 매우 좋아하는 백준이는 한 가지 궁금증에 빠졌다. 한 지점에서 출발을 하여서 시간여행을 하기 시작하여 다시 출발을 하였던 위치로 돌아왔을 때, 출발을 하였을 때보다 시간이 되돌아가 있는 경우가 있는지 없는지 궁금해졌다. 여러분은 백준이를 도와 이런 일이 가능한지 불가능한지 구하는 프로그램을 작성하여라.

문제에서 찾는 것이 특정 점으로 되돌아 올 수 있으며 이 경로의 가중치가 음수가 되는 음수 사이클이다. 이를 찾기 위해 벨만-포드 알고리즘을 사용할 수 있다.

단, 벨만-포드 알고리즘을 일반적으로 사용하면 특정 점에서 시작하는 경로를 찾기 때문에 시작 점에서 도달하지 못하는 점은 사이클이 있는지 확인할 수 없다. 따라서 이를 해결하기 위해 거리 배열을 전부 무한대로 초기화하지 않고 0으로 초기화해 모든 점을 도달 가능한 점으로 만들고 알고리즘을 적용하면 된다.

이에 따라 정점이 N개 이므로 N-1번 거리를 갱신하고 마지막으로 간선을 확인해 거리가 갱신된다면 음수 사이클이 존재하는 것이므로 문제 출력에 맞게 존재한다면 YES, 없다면 NO를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val TC = nextInt()
    repeat(TC){
        val N = nextInt()
        val M = nextInt()
        val W = nextInt()
        val dist = IntArray(N + 1)
        val edges = ArrayList<IntArray>()
        repeat(M){
            val S = nextInt()
            val E = nextInt()
            val T = nextInt()
            edges.add(intArrayOf(S, E, T))
            edges.add(intArrayOf(E, S, T))
        }
        repeat(W){
            val S = nextInt()
            val E = nextInt()
            val T = nextInt()
            edges.add(intArrayOf(S, E, T * -1))
        }
        repeat(N - 1){
            for((S, E, T) in edges){
                if(dist[E] > dist[S] + T) dist[E] = dist[S] + T
            }
        }
        var possible = false
        for((S, E, T) in edges){
            if(dist[E] > dist[S] + T) {
                possible = true
                break
            }
        }
        println(if (possible) "YES" else "NO")
    }
}
```