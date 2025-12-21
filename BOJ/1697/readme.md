# 백준 1697번: 숨바꼭질

> 문제: https://www.acmicpc.net/problem/1697

### 문제 풀이

BFS

visit[i] = 점 i를 방문했는지 여부

큐에 첫 좌표 N과 그때의 시간 0을 넣고 큐에서 위치 x와 그 때의 시간 d를 꺼내서 다음으로 이동 또는 순간이동했을 때의 점의 위치 nx가 이미 방문했는지를 확인해 방문을 안했다면 방문처리를 하고 큐에 nx와 그때의 시간 d + 1을 넣음

nx가 K와 같다면 K로 이동하는 최단 시간을 찾은 것이므로 d + 1을 출력하고 함수를 반환함

만약 N이 K일때는 이동하고 난 위치만 검사하도록 구현했으므로 0을 출력하고 반환하도록 예외처리를 하면 정답

### 풀이 설명

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

N에서 K로 가는 가장 빠른 시간을 구해야 한다. 현재 이동하는 방법은 1칸 뒤, 1칸 앞, 현재 칸의 2배로 이동할 수 있고 이 때 1초가 걸린다고 했으므로 시간을 거리로 생각하고 BFS를 이용해 최단 거리를 구하면 이 때 거리가 구해야 하는 최단 시간이 된다.

이동 방식에 따라 같은 지점을 여러번 탐색할 수 있지만 어차피 가장 빠른 시간을 찾는 것이므로 한번 방문한 점을 다시 방문할 필요가 없다. 따라서 방문 여부를 저장하기 위해 visit 배열을 만든다.

그 뒤에 큐에 현재 좌표와 도달할 때의 거리를 넣고 큐에서 현재 위치 x와 x로 도달하기 위한 거리 d를 하나씩 꺼내서 x에서 이동했을 때의 위치 nx에 대해 방문하지 않은 위치일때만 방문처리를 하고 그 때의 거리 d + 1을 큐에 넣는다.

이동을 하다보면 nx가 K가 될 때가 있는데 BFS 특성상 가장 먼저 K로 도달하는 경우가 가장 거리가 짧으므로 그 때의 거리 d + 1을 출력하고 함수를 반환한다.

이런 방식으로 BFS를 구현하면 이동했을 때의 최단거리를 구하기 때문에 N이 K와 같을 때는 오답이 된다. 그러므로 N이 K와 같다면 0을 출력하고 함수를 반환하도록 예외 처리를 하면 정답이 된다.

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
    if(N == K){
        println(0)
        return
    }
    val visit = BooleanArray(100001)
    visit[N] = true
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(N, 0))
    while(queue.isNotEmpty()){
        val (x, d) = queue.removeFirst()
        for(nx in intArrayOf(x + 1, x - 1, x * 2)){
            if(nx == K){
                println(d + 1)
                return
            }
            if(nx >= 0 && nx <= 100000 && !visit[nx]){
                visit[nx] = true
                queue.add(intArrayOf(nx, d + 1))
            }
        }
    }
}
```