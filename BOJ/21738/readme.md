# 백준 21738번: 얼음깨기 펭귄

> 문제: https://www.acmicpc.net/problem/21738

### 문제 풀이

BFS

answer = 펭귄이 떨어지지 않도록 깰 수 있는 얼음의 최대 개수

count = BFS로 찾아야 하는 지지대의 개수

graph[i] = i번 얼음과 연결된 얼음

queue = P번 얼음부터 BFS로 주변 얼음을 탐색하기 위한 큐

visit[i] = P번 얼음부터 i번 얼음까지 가는 거리(얼음의 개수), 방문하지 않은 얼음이면 -1

펭귄이 올라간 P번 얼음에서부터 가장 가까운(연결된 얼음이 적은) 지지대 2개를 찾으면 이 지지대와 연결된 얼음과 P번 얼음만 제외하고 나머지 모든 얼음을 깰 수 있으므로 이때 깬 얼음의 개수가 깰 수 있는 최대 얼음의 개수임

방문 여부와 해당 얼음까지 경로의 길이를 저장하도록 visit 배열을 IntArray로 만들고 -1로 초기화함

answer에 깰 수 있는 얼음의 최대 개수를 저장하기 위해 전체 개수 N에서 펭귄이 올라간 얼음의 개수 1을 뺀 값으로 초기화함

P번부터 BFS를 통해 찾기 위해 queue에 P를 넣고 visit[P]에 0을 대입함

queue에서 현재 얼음 A를 꺼내서 A와 연결된 얼음 B에 대해 visit[B]가 -1일때 visit[B]에 visit[A] + 1을 저장해 B에 대한 방문처리를 하고 B를 큐에 넣음

B가 S 이하라면 지지대이므로 count를 확인해 현재 찾는 지지대인지 확인함

찾는 지지대라면 지지대를 찾았으므로 count를 1 감소하고 answer에 visit[B]만큼 빼줌

count가 0이라면 반복문을 탈출함

이를 큐가 비거나 count가 0이 될때까지 반복하면 깰 수 있는 얼음의 최대 개수가 answer에 저장되므로 출력하면 정답

### 풀이 설명

![](https://velog.velcdn.com/images/kosdjs/post/2ec548c5-388a-44f8-83e1-67c7dac91987/image.png)

특수 얼음 깨기 펭귄 보드게임이 위의 그림처럼 있다고 한다. 빨간색 얼음은 지지대이고, 일반 얼음은 지지대 하나와 연결되어 있으면 깨지지 않고, 펭귄이 올라간 얼음은 지지대 두 개와 연결되어 있어야 깨지지 않는다고 한다. 이 게임에서 펭귄을 떨어트리지 않고 최대 몇 개의 얼음을 깰 수 있는지 구하는 문제이다.

펭귄이 떨어지지 않으려면 펭귄이 올라간 얼음이 깨져서는 안된다. 그러면 펭귄이 올라간 얼음이 깨지지 않을 조건인 지지대 두 개와 연결이 되어있도록 만드는 얼음은 깨서는 안된다는 것이다.

즉, 펭귄이 올라간 얼음과 지지대 두 개와 연결되어있는 얼음들은 깨지 않으며 나머지 얼음을 깨는 것이므로 펭귄이 올라간 얼음에서 지지대까지 연결된 얼음의 개수(거리)가 가장 적은 지지대 두 개를 찾아 해당 경로를 제외한 나머지 얼음을 모두 깨면 된다.

그러므로 펭귄이 올라간 얼음 P에서부터 BFS를 통해 이동해 가장 가까운 지지대 2개를 찾으면 된다.

답을 내기 위해 얼음의 연결 정보를 graph에 인접 리스트 형태로 저장하고 answer에 전체 얼음의 개수 N에서 펭귄이 올라간 얼음의 개수인 1을 뺀 값을 저장한다.

그 이후에 BFS를 구현하기 위한 현재 얼음을 저장하는 queue, 방문 여부 및 P 얼음에서부터 현재 얼음까지의 거리를 visit에 저장한다. 단, visit 배열은 방문하지 않은 얼음을 처리하기 위해 -1로 초기화한다.

얼음 P에서부터 가장 가까운 지지대 2개를 찾아야 하므로 찾아야 하는 지지대의 개수를 count에 저장하고 P에서부터 지지대를 찾을때마다 1씩 감소시킨다.

BFS를 통해 가장 가까운 지지대 2개를 찾으면 visit 배열에 저장된 거리만큼 answer를 감소시키면 그 지지대와 연결된 얼음의 개수를 빼준 것이므로 이 값이 깰 수 있는 얼음의 최대 개수가 된다.

따라서 answer를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val S = nextInt()
    val P = nextInt()
    var answer = N - 1
    var count = 2
    val graph = Array(N + 1){ArrayList<Int>()}
    for(i in 1 until N){
        val A = nextInt()
        val B = nextInt()
        graph[A].add(B)
        graph[B].add(A)
    }
    val queue = ArrayDeque<Int>()
    val visit = IntArray(N + 1){-1}
    queue.add(P)
    visit[P] = 0
    while(queue.isNotEmpty()){
        val A = queue.removeFirst()
        for(B in graph[A]){
            if(visit[B] == -1){
                visit[B] = visit[A] + 1
                queue.add(B)
                if(B <= S){
                    if(count > 0){
                        count--
                        answer -= visit[B]
                    } else {
                        break
                    }
                }
            }
        }
        if(count == 0) break
    }
    println(answer)
}
```