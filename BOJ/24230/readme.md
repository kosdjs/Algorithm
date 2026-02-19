# 백준 24230번: 트리 색칠하기

> 문제: https://www.acmicpc.net/problem/24230

### 문제 풀이

트리, BFS

colors[i] = i번 정점의 색

graph[i] = i번 정점과 연결된 정점들

queue = 탐색해야 하는 정점이 들어있는 큐

visit[i] = i번 정점을 이미 탐색했는지 여부

answer = 정점을 색칠한 횟수

루트 노드에서부터 단말 노드까지 BFS로 탐색하며 현재 노드의 자식 노드의 색을 확인하며 현재 노드의 색과 다르면 자식 노드에 색을 칠한 것이므로 answer를 1 증가시킴

단, color[1]이 0이 아니라면 루트 노드가 하얀색이 아니므로 루트 노드부터 색을 칠한 것이므로 answer를 1로 초기화함

이에 따라 모든 노드의 자식 노드를 확인해 answer에 색이 바뀐 횟수를 반영하면 색을 칠한 횟수가 answer에 반영되므로 출력하면 정답

### 풀이 설명

1번 정점을 루트로 하는 트리가 주어지고, 해당 트리의 각 정점들이 어떤 색으로 칠해져있는지 주어졌을 때 특정 정점을 한 색으로 칠하면 그 정점을 루트로 하는 서브트리가 모두 같은 색으로 칠해진다고 했을 때 이 방법으로 최소 몇 번 칠해야 주어진 트리와 같이 되는지 구하는 문제이다.

정점에 색을 칠하면 해당 정점을 루트로 하는 서브트리 전체가 색이 칠해지므로 부모 노드와 자식 노드가 색이 다르다면 색이 다른 자식 노드를 루트 노드로 하는 서브트리에 색을 칠한것이 된다.

따라서 루트 노드에서 말단 노드까지 BFS로 탐색을 하며 부모 노드와 자식 노드가 색이 달라지는 경우가 얼마나 되는지 확인하면 된다.

입력으로 들어오는 트리를 저장하기 위해 정점의 연결 관계를 나타내기 위해 i번 정점과 연결된 정점의 리스트를 graph[i]로 저장하고, i번 정점의 색을 colors[i]에 저장한다. 또한 BFS로 트리를 탐색하기 위한 큐를 queue로 정의하고 탐색 유무를 저장할 visit 배열을 정의한다.

색을 칠한 횟수를 answer 변수에 담는데 자식 노드와 색이 달라질 때만 탐색을 하므로 루트 노드가 색이 있는지를 미리 확인해야 하므로 colors[1]을 확인해 하얀색이 아닌 경우엔 1로, 하얀색이라면 0으로 초기화한다.

트리의 루트 노드인 1번 정점부터 큐에 넣고 방문 처리를 하며 큐에서 정점 a를 하나씩 꺼내며 아직 방문하지 않은 자식 노드 b에 대해 colors 배열을 확인해 현재 정점 a의 색과 다르다면 정점 b에 색을 칠한 것이므로 answer를 1씩 증가시킨다.

이에 따라 모든 정점을 확인하며 자식 노드에서 색이 달라진 횟수를 확인하면 answer에 트리에 색을 칠한 횟수가 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val colors = IntArray(N + 1)
    val graph = Array(N + 1){ArrayList<Int>()}
    for(i in 1..N){
        colors[i] = nextInt()
    }
    repeat(N - 1){
        val a = nextInt()
        val b = nextInt()
        graph[a].add(b)
        graph[b].add(a)
    }
    val queue = ArrayDeque<Int>()
    queue.add(1)
    val visit = BooleanArray(N + 1)
    visit[1] = true
    var answer = if(colors[1] == 0) 0 else 1
    while(queue.isNotEmpty()){
        val a = queue.removeFirst()
        for(b in graph[a]){
            if(!visit[b]){
                visit[b] = true
                queue.add(b)
                if(colors[a] != colors[b]) answer++
            }
        }
    }
    println(answer)
}
```