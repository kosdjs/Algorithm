# 백준 13549번: 숨바꼭질 3

> 문제: https://www.acmicpc.net/problem/13549

### 문제 풀이

다익스트라, 0 - 1 bfs

$dist[i] = N$에서부터 $i$까지 가는 최소 시간

$visit[i] = i$ 방문 여부

큐에 시작지점 $N$을 넣고 다음 순서를 모든 점에 대해 반복

1. 큐에서 $dist$값이 가장 작은 점 $x$를 꺼냄

2. $x$에서 이동할 수 있고 방문되지 않은 점에 대해 $dist$값 변경 및 방문처리 후 큐에 삽입

이 과정을 거쳐 $dist[K]$의 값을 구하고 출력하면 정답

### 풀이 설명

N부터 K까지 가는 최소 시간을 구해야 하는 문제이므로 시간을 거리라고 생각하면 두 점까지의 최소 거리를 찾는 문제이고 시작점이 정해져있기 때문에 다익스트라 알고리즘을 사용할 수 있다.

그리고 문제에서 이동하는 방법이 걷거나 순간이동을 하는 것이라고 나와있고, 이때 걸리는 시간이 0과 1이므로 현재 확인해야 하는 점들 중 최소 시간이 걸리는 점을 찾기 위해 데크(`deque`)를 사용해 순간이동하는 점을 데크의 앞에, 걸어서 이동하는 점을 데크에 뒤에 넣에서 데크의 앞에서부터 확인하면 빠르게 찾을 수 있다.

그러므로 다익스트라 알고리즘을 사용하기 위해 $dist[i]$를 $N$부터 $i$까지 가는 최소 시간, $visit[i]$를 $i$ 방문 여부로 정의한 후 $dist[N]$에 $0$을 대입하고 $visit[N]$에 $true$를 대입해 방문처리를 해준 후 데크의 앞에 $N$을 삽입한다. 그 후 데크에 앞에서부터 점을 하나씩 살펴보면서 순간이동할 수 있는 점이 방문되지 않았다면 $dist$값을 변경하고 방문처리를 한 후에 데크에 앞에 넣고, 걸을 수 있는 점이 방문되지 않았다면 $dist$값을 변경하고 방문처리를 한 후에 데크에 뒤에 넣는다.

이 과정을 통해 모든 점을 방문하면 $dist[K]$이 $N$부터 $K$까지 가는데 걸리는 최소 시간이므로 이 값을 출력해주면 정답이다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val max = 100001
    val dist = IntArray(max){Int.MAX_VALUE}
    val visit = BooleanArray(max){false}
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    dist[N] = 0
    visit[N] = true
    val deque = ArrayDeque<Int>()
    deque.add(N)
    while (deque.isNotEmpty()){
        val x = deque.removeFirst()
        if(x * 2 < max && !visit[x * 2]){
            dist[x * 2] = dist[x]
            visit[x * 2] = true
            deque.addFirst(x * 2)
        }
        if(x > 0 && !visit[x - 1]){
            dist[x - 1] = dist[x] + 1
            visit[x - 1] = true
            deque.addLast(x - 1)
        }
        if(x + 1 < max && !visit[x + 1]){
            dist[x + 1] = dist[x] + 1
            visit[x + 1] = true
            deque.addLast(x + 1)
        }
    }
    println(dist[K])
}
```