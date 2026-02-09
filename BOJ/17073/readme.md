# 백준 17073번: 나무 위의 빗물

> 문제: https://www.acmicpc.net/problem/17073

### 문제 풀이

트리

edges[i] = i번 노드와 연결된 간선의 갯수

leafCount = 단말 노드의 갯수

단말 노드는 부모 노드만 연결되므로 연결된 간선의 갯수가 항상 1임

입력에서 간선 정보가 주어지므로 U, V에 대해 edges[U], edges[V]를 1씩 증가시켜 해당 노드의 연결되는 간선의 갯수를 셈

루트 노드는 항상 1번이므로 i를 2부터 N까지 반복하며 루트 노드를 제외한 노드 중 edges[i]가 1인 단말 노드의 갯수를 찾으면 leafCount를 1 증가시킴

전체 물의 양 W를 leafCount로 나눈 값을 출력하면 정답

### 풀이 설명

물이 움직일 때 자식 노드중 하나를 골라서 넘기기 때문에 모든 물이 단말 노드에 고여있다면 물이 더 이상 움직이지 않는다. 그러므로 문제에서 물이 움직이지 않을 때의 i번 정점은 단말 노드를 뜻하는 것이고 전체 물의 양이 W이므로 기댓값의 평균은 W를 단말 노드의 갯수로 나눈 값이 된다.

그러면 단말 노드의 갯수만 구하면 원하는 값을 바로 계산할 수 있다. 여기서 단말 노드의 특성을 생각하면 오직 부모 노드에만 연결되는 간선이 있으므로 연결되는 간선의 갯수가 항상 1이다. 단말 노드가 아닌 노드들은 일반적으로 항상 부모 노드와 자식 노드가 연결되어 있으므로 연결되는 간선의 갯수가 항상 2 이상이다.

단, 루트 노드의 경우 부모 노드가 없기 때문에 자식 노드가 1개만 있다면 연결되는 간선의 갯수가 1이 될 수 있다. 하지만 문제에서 항상 1번 노드가 루트 노드라고 했으니 1번을 제외한 나머지 노드의 연결된 간선의 갯수가 1인 노드의 갯수를 세면 된다.

이에 따라 단말 노드의 갯수를 구해 전체 물의 양 W를 나눈 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val W = nextInt()
    val edges = IntArray(N + 1)
    repeat(N - 1){
        val U = nextInt()
        val V = nextInt()
        edges[U]++
        edges[V]++
    }
    var leafCount = 0
    for(i in 2..N){
        if(edges[i] == 1) leafCount++
    }
    println(W.toDouble() / leafCount)
}
```