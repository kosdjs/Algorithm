# 백준 3987번: 보이저 1호

> 문제: https://www.acmicpc.net/problem/3987

### 문제 풀이

DFS

map[i][j] = (i, j) 칸에 무엇이 있는지

visit[i][j][d] = (i, j) 칸을 d 방향으로 방문한 적이 있는지 여부

direction = 시그널의 시간이 최대가 되는 방향

answer = 시그널이 항성계 내부에 머무르는 최대 시간

dfs(i, j, count, d) = (i, j) 칸을 count의 시간에 d 방향으로 방문했을 때 시그널이 항성계 내부에 있는 시간

시작점 (PR, PC)에서 상, 하, 좌, 우 4방향으로 DFS로 격자를 탐색해 어느 방향이 최대 탐색 길이인지 찾으면 됨

항성계를 벗어나거나 블랙홀을 만나면 시그널의 전파가 끊기므로 좌표를 벗어나거나 블랙홀을 만날때까지의 거리를 반환하도록 함수를 만들면 됨

그러나 행성을 만나면 방향이 바뀌고 사이클이 생길 수 있다고 했으므로 방문 처리를 좌표 및 방향으로 해야함

그러므로 visit[i][j][d]에 (i, j)칸에 d 방향으로 방문했는지 여부를 저장하고 이를 함수에서 매번 확인해 사이클이라면 무한대로 돌기 때문에 반환하는 시간을 Int.MAX_VALUE로 반환함

이를 (i, j) 칸을 count의 시간에 d 방향으로 방문했을 때 시그널이 항성계 내부에 있는 시간을 반환하는 재귀 함수 dfs(i, j, count, d)로 정의하고 탐사선의 시작 좌표 (PR, PC)에서 모든 방향으로 함수가 반환하는 값이 제일 클 때의 방향과 시간을 direction, answer에 저장하고 출력하면 정답

### 풀이 설명

시작점 (PR, PC)에서부터 상, 하, 좌, 우 4방향으로 시그널을 보낼 경우 어떤 방향으로 보낼 경우에 시그널이 항성계 내부에 있는 시간이 최대가 되는지 판별하는 문제이다.

즉, 각 방향으로 DFS를 통해 내부에 얼마나 있는지 판별하면 되는 문제이다. 그러나 여기서 조심해야 할 점이 행성을 만나면 방향이 바뀐다는 점이고 이로 인해 사이클이 생길 수 있다는 점이다.

따라서 사이클을 판별하려면 이미 이 칸에 방문했는지 여부가 필요하다. 또한 이 칸을 방문할 때 어느 방향에서 왔는지도 중요하기 때문에 방문 여부를 칸과 방향에 따라 저장해야 한다.

이에 따라 (i, j) 칸에서 항성계 내부를 벗어나거나 블랙홀을 만날때까지 d 방향으로 시그널을 보내면 DFS로 한칸씩 탐색하며 얼마나 걸리는지 반환하는 재귀 함수 dfs(i, j, count, d)를 정의해 최대 시간을 구하면 된다.

각 방향에 따라 재귀 함수의 결과를 확인하고 그 때 시간이 최대가 된다면 방향을 direction, 그 때의 시간을 answer에 저장해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main() = System.`in`.bufferedReader().run {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = Array(N){ CharArray(M) }
    val visit = Array(N){Array(M){ BooleanArray(4) } }
    for(i in 0 until N){
        val s = readLine()
        for(j in 0 until M){
            map[i][j] = s[j]
        }
    }
    st = StringTokenizer(readLine())
    val PR = st.nextToken().toInt() - 1
    val PC = st.nextToken().toInt() - 1
    var direction = 0
    var answer = 0
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, 1, 0, -1)
    fun dfs(i: Int, j: Int, count: Int, d: Int): Int{
        if(i < 0 || j < 0 || i >= N || j >= M) return count - 1
        else if(map[i][j] == 'C') return count - 1
        else if(visit[i][j][d]) return Int.MAX_VALUE
        else{
            var nd = d
            if(map[i][j] == '/'){
                nd = when(d){
                    0 -> 1
                    1 -> 0
                    2 -> 3
                    3 -> 2
                    else -> -1
                }
            } else if(map[i][j] == '\\'){
                nd = when(d){
                    0 -> 3
                    1 -> 2
                    2 -> 1
                    3 -> 0
                    else -> -1
                }
            }
            visit[i][j][d] = true
            val result = dfs(i + dy[nd], j + dx[nd], count + 1, nd)
            visit[i][j][d] = false
            return result
        }
    }
    for(i in 0 until 4){
        val cur = dfs(PR, PC, 1, i)
        if(cur > answer){
            direction = i
            answer = cur
        }
    }
    println(when(direction){
        0 -> "U"
        1 -> "R"
        2 -> "D"
        3 -> "L"
        else -> "else"
    })
    println(if(answer == Int.MAX_VALUE) "Voyager" else answer)
}
```