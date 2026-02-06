# 백준 1245번: 농장 관리

> 문제: https://www.acmicpc.net/problem/1245

### 문제 풀이

BFS

farm[i][j] = (i, j) 칸의 높이

visit[i][j] = (i, j) 칸을 방문했는지 여부

answer = 산봉우리의 갯수

isPeak = 현재 검사하는 높이가 산봉우리인지 여부

모든 좌표 (i, j)에 대해 방문하지 않은 칸에 대해 방문처리를 하고 BFS를 이용하기 위해 해당 칸을 큐에 넣음

큐에서 칸을 하나씩 꺼내서 인접한 칸 ny, nx에 대한 높이 farm[ny][nx]와 현재 검사하는 인접한 격자의 높이 farm[i][j]를 비교해 더 크다면 현재 검사하는 인접한 격자는 산봉우리가 아니므로 isPeak에 false를 대입함

인접한 칸 ny, nx가 방문되지 않았고 현재 높이와 같다면 방문처리를 하고 큐에 넣어서 높이가 같은 인접한 격자를 모두 방문처리를 해줌

큐가 모두 빌때까지 검사를 한 이후에 isPeak가 true라면 현재 검사한 격자가 산봉우리이므로 answer를 1 증가시킴

모든 칸에 대해 검사하면 산봉우리의 갯수가 answer에 저장되므로 출력하면 정답

### 풀이 설명

N x M 크기의 격자가 주어지면 이 격자에서 산봉우리가 몇 개 있는지 찾는 문제이다.

산봉우리는 같은 높이를 가지는 하나의 격자 혹은 인접한 격자들의 집합으로 이루어져 있다. 여기서 "인접하다"의 정의는 X좌표 차이와 Y좌표 차이가 모두 1 이하인 경우이다. 또한 산봉우리와 인접한 격자는 모두 산봉우리의 높이보다 작아야한다.

즉, 같은 높이의 인접한 격자들을 확인할 때 이 격자들과 인접한 격자의 높이가 현재 확인하는 격자의 높이가 낮아야만 산봉우리라는 소리이다. 이 때 인접한 칸이 X좌표 차이와 Y좌표 차이가 모두 1 이하이므로 주변 8칸을 검사해야 한다. 인접한 칸을 확인하기 때문에 BFS로 검사할 수 있다.

따라서 한 칸씩 확인하면서 같은 높이의 인접한 격자들을 BFS로 방문처리를 하면서 검사하는 모든 인접한 칸의 높이가 현재 높이보다 낮다면 현재 검사하는 인접한 격자가 산봉우리이므로 이런 인접한 격자를 모두 찾으면 된다.

그러므로 BFS를 이용해 산봉우리를 찾아 갯수를 answer에 저장해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val farm = Array(N){IntArray(M)}
    val visit = Array(N){BooleanArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            farm[i][j] = nextInt()
        }
    }
    var answer = 0
    val queue = ArrayDeque<IntArray>()
    val dy = intArrayOf(1, 1, 0, -1, -1, -1, 0, 1)
    val dx = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    var isPeak = true
    for(i in 0 until N){
        for(j in 0 until M){
            if(!visit[i][j]){
                isPeak = true
                visit[i][j] = true
                queue.add(intArrayOf(i, j))
                while(queue.isNotEmpty()){
                    val (y, x) = queue.removeFirst()
                    for(k in 0 until 8){
                        val ny = y + dy[k]
                        val nx = x + dx[k]
                        if(ny >= 0 && ny < N && nx >= 0 && nx < M){
                            if(farm[ny][nx] > farm[i][j]) isPeak = false
                            if(!visit[ny][nx] && farm[ny][nx] == farm[i][j]){
                                visit[ny][nx] = true
                                queue.add(intArrayOf(ny, nx))
                            }
                        }
                    }
                }
                if(isPeak) answer++
            }
        }
    }
    println(answer)
}
```