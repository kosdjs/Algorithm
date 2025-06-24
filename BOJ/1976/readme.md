> 문제: https://www.acmicpc.net/problem/1976

### 문제 풀이

Union Find

배열에 연결되어있는 노드 중 가장 번호가 빠른 노드(루트 노드)의 번호를 저장

합칠 때 루트 노드를 비교해 더 번호가 작은 노드를 루트 노드로 잡음

루트 노드를 찾을 때 배열에 값을 저장해 루트 노드를 바로 찾을 수 있게 함

가야할 도시의 번호의 루트 노드를 찾아 모두 같으면 방문 가능 아니면 불가능

### 풀이 설명

문제를 그래프로 접근하면 가야할 도시들이 전부 연결되어있어야 방문이 가능함

따라서 그래프가 연결되어있는지 확인하기 좋은 알고리즘인 Union Find 알고리즘을 사용해 도시의 연결 관계를 확인하고 가야할 모든 도시들이 연결되어있으면 YES를 출력하고 아니라면 NO를 출력하면 된다

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val M = br.readLine().toInt()
    val graph = Array(N + 1){IntArray(N + 1){0} }
    val root = IntArray(N + 1){it}
    for(i in 1..N){
        val st = StringTokenizer(br.readLine())
        for(j in 1..N){
            graph[i][j] = st.nextToken().toInt()
            if(graph[i][j] == 1){
                union(root, i, j)
            }
        }
    }
    var condition = true
    val st = StringTokenizer(br.readLine())
    var before = 0
    for(i in 1..M){
        val city = st.nextToken().toInt()
        if(before == 0){
            before = root[city]
        } else if(before != root[city]){
            condition = false
            break
        }
    }
    println(if (condition) "YES" else "NO")
}

fun union(root: IntArray, x: Int, y: Int){
    val xFind = find(root, x)
    val yFind = find(root, y)
    if(xFind < yFind){
        root[yFind] = xFind
    } else if(xFind > yFind){
        root[xFind] = yFind
    }
}

fun find(root: IntArray, x: Int): Int{
    if(root[x] == x){
        return x
    } else {
        root[x] = find(root, root[x])
        return root[x]
    }
}
```