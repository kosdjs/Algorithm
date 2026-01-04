# 백준 27211번: 도넛 행성

> 문제: https://www.acmicpc.net/problem/27211

### 문제 풀이

BFS

map[i][j] = $(i, j)$ 칸이 빈 칸인지 숲인지 저장하는 배열

visit[i][j] = $(i, j)$ 칸이 방문한 칸인지 확인하는 배열

count = 발견한 구역의 개수

queue = 빈 칸의 좌표를 저장하는 큐

방문하지 않은 칸들을 확인하며 빈 칸을 발견하면 빈 구역을 하나 발견한 것이므로 그 구역에 속한 모든 칸을 방문처리 해야 함

모든 좌표 i, j에 대해 visit[i][j]가 false, map[i][j]가 0이면 방문하지 않은 빈 칸이므로 빈 구역을 찾았다는 의미에서 count 1 증가하고 그 칸에서부터 BFS로 같은 구역의 모든 빈 칸을 방문처리 해야 함

BFS에서 이동 처리를 할 때 y는 0과 N - 1, x는 0과 M - 1이 이어져 있으므로 y, x가 0보다 작아지면 N, M을 더해주어야 하고, N, M과 크거나 같아지면 N, M을 빼주어야 함

이를 모든 칸에 대해 반복하면 count에 빈 구역의 개수가 저장되므로 출력하면 정답

### 풀이 설명

![](https://velog.velcdn.com/images/kosdjs/post/91323e6f-0829-4f92-87dd-14fc0086a8b6/image.png)

준겸이는 $N \times M$칸으로 이루어진 도넛 모양의 행성에 살고 있다. 준겸이가 살고 있는 행성에는 위 그림처럼 격자 모양으로 줄이 그어져 있다. 행성의 각 칸은 숲으로 막혀 있거나, 지나갈 수 있도록 비어 있다.

준겸이는 본인의 집이 있는 위치를 기준으로 삼아 $(0,0)$이라고 표시하기로 했다. 준겸이는 행성 위에서 상하좌우로 걸어 다닐 수 있다. 준겸이가 오른쪽으로 한 칸 걸어가면, 위치 $(0,1)$에 도달할 것이다. 마찬가지로 아래로 한 칸 걸어가면, 위치 $(1,0)$에 도달할 것이다. 준겸이가 
$(0,0)$에서 $M$칸 오른쪽으로 걸어가면, 한 바퀴를 돌아 다시 원래 자리로 되돌아오게 된다. 비슷하게 $(0,0)$에서 $N$칸 아래로 걸어가면, $(0,0)$으로 돌아오게 된다. 행성은 연결되어 있기 때문에, 준겸이가 $(0,0)$에서 왼쪽으로 한 칸 걸어가면 위치 $(0,M-1)$에 도달할 것이다. 마찬가지로 준겸이가 $(0,0)$에서 위로 한 칸 걸어가면 $(N-1, 0)$에 도달하게 된다.

준겸이는 행성을 탐험하려고 한다. 만약 준겸이가 비어 있는 어떤 칸 $A=(p_1,q_1)$에서 시작해, 숲에 막히지 않고 비어 있는 칸 $B=(p_2,q_2)$에 도달할 수 있다면 $A$와 $B$는 같은 구역이다. 반대로, 도달할 수 없다면 $A$와 $B$는 서로 다른 구역이다. 당신은 준겸이가 탐험할 수 있는 빈 구역의 개수가 몇 개인지 출력해야 한다.

격자의 위, 아래가 이어져있고, 왼쪽과 오른쪽이 이어져 있을 때 빈 칸인 구역이 몇개 있는지 확인하는 문제다. 빈 칸에서 상하좌우로 움직여서 숲에 막히지 않고 도달할 수 있는 빈 칸은 같은 구역이라고 했으므로 빈 칸에서 BFS를 이용하면 같은 구역인 칸이 어느 칸인지 알 수 있다.

따라서 BFS를 사용하기 위해 방문 여부를 저장하는 visit 배열을 만들고, 좌표를 저장할 큐를 만든다. 그리고 구역을 찾은 갯수를 저장하기 위해 count 변수를 정의한다. 그 이후에 모든 좌표에 대해 빈 칸을 찾으면 빈 구역을 찾은 것이므로 count를 1 증가시키고 그 칸에서부터 BFS를 통해 그 구역의 모든 칸을 방문 처리를 한다.

이때 BFS를 할 때 상하좌우 좌표를 움직일 동안 y, x가 0보다 작아지거나 N, M과 크거나 같아질 수 있다. 문제에 나와있듯이 0에서 위로 걸어가면 N - 1, 왼쪽으로 갈때는 M - 1이 되므로 0보다 작아질때는 y, x에 N, M을 더해주면 되고, N, M보다 크거나 같아지는 경우는 반대로 N - 1, M - 1에서 0으로 이동하는 것이므로 N, M을 빼주면 된다.

그렇게 모든 좌표에 대해 방문하지 않은 빈칸을 찾아서 BFS를 통해 모든 빈칸을 탐색하면 빈 구역의 개수가 count에 저장되므로 출력하면 정답이 된다.

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
    var count = 0
    val map = Array(N){IntArray(M)}
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
        }
    }
    val visit = Array(N){BooleanArray(M)}
    val queue = ArrayDeque<IntArray>()
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    for(i in 0 until N){
        for(j in 0 until M){
            if(map[i][j] == 0 && !visit[i][j]){
                visit[i][j] = true
                count++
                //bfs
                queue.add(intArrayOf(i, j))
                while(queue.isNotEmpty()){
                    val (y, x) = queue.removeFirst()
                    for(k in 0 until 4){
                        var ny = y + dy[k]
                        if(ny < 0) ny += N
                        else if(ny >= N) ny -= N
                        var nx = x + dx[k]
                        if(nx < 0) nx += M
                        else if(nx >= M) nx -= M
                        if(map[ny][nx] == 0 && !visit[ny][nx]){
                            visit[ny][nx] = true
                            queue.add(intArrayOf(ny, nx))
                        }
                    }
                }
            }
        }
    }
    println(count)
}
```