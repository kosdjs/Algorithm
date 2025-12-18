# 백준 14502번: 연구소

> 문제: https://www.acmicpc.net/problem/14502

### 문제 풀이

BFS, 백트래킹, 브루트포스

map = 연구소의 초기 상태

empties = 빈 칸의 좌표를 저장하는 리스트

viruses = 바이러스의 초기 좌표를 저장하는 리스트

temp = 안전 영역을 측정하기 위한 바이러스 확산 이후의 연구소 상태를 따로 나타내는 배열

copy() = 벽을 3개 세운 이후의 연구소 상태를 temp로 복사하는 함수

bfs() = 벽이 세워진 이후의 연구소 상태 temp에서 바이러스가 확산된 이후의 안전 영역을 측정하는 함수

count = 바이러스가 확산된 이후에 남은 빈 칸의 개수 (안전 영역의 크기)

dfs(level, idx) = 연구소의 빈칸들 중 empties에서 idx부터 한칸씩 골라 벽을 3개 채우는 재귀 함수

answer = 안전 영역 크기의 최댓값

안전 영역을 벽을 3개 세울 때 마다 측정해야 하고 측정할 때마다 바이러스를 확산시켜야 하고 연구소 초기 상태는 보존해야 하기 때문에 측정할 배열을 별도로 두기 위해 temp 배열을 만듬

안전 영역을 측정할 때마다 벽을 3개 세우는 위치가 달라지므로 벽을 세운 이후의 상태를 복사하기 위해 copy() 함수를 만듬

temp에 벽이 3개 세워진 연구소 상태가 복사되므로 여기에서 바이러스가 확산된 이후의 안전 영역을 측정하기 위해 BFS를 이용해 바이러스를 확산시키고 남은 빈 칸을 측정해 answer에 저장된 크기보다 count가 크면 answer에 최댓값을 저장하는 bfs() 함수를 만듬

모든 빈칸들 중 3칸을 골라 벽을 세워야 하므로 empties에서 3칸을 조합으로 뽑아내는 dfs(level, idx) 함수를 정의함, 또한 이 함수에서 3칸이 뽑혔을 때마다 안전 영역을 측정해야 하므로 copy(), bfs() 함수를 이용해 측정함

그러면 dfs(0, 0)을 실행하면 모든 빈칸 중 3칸을 골라 벽을 3개 세우는 모든 상황에서 안전 영역을 측정할 수 있으므로 이를 실행하고 난 이후의 answer를 출력하면 정답

### 풀이 설명

연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 

일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.

벽을 3개 세우는 것과, 그 이후에 안전 영역의 크기를 측정하는 두 가지 단계로 나뉜다. 먼저 벽을 3칸 세우는 것은 모든 빈 칸에서 3칸을 뽑는 것이다. 따라서 백트래킹을 이용한 재귀 함수로 3칸을 고를 수 있다.

그렇게 3칸을 골라 벽을 세운 후에는 안전 영역의 크기를 측정해야 하는데, 이 때 안전 영역은 바이러스가 모두 퍼진 이후에 남은 빈 칸의 크기 임으로 BFS를 이용해 바이러스가 퍼진 이후의 빈 칸의 크기를 재면 된다.

따라서 벽 3개를 세우는 모든 경우의 안전 영역의 크기를 모두 측정해 안전 영역의 크기의 최댓값을 출력하면 정답이 된다.

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
    val map = Array(N){IntArray(M)}
    val empties = ArrayList<IntArray>()
    val viruses = ArrayList<IntArray>()
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
            if(map[i][j] == 0){
                empties.add(intArrayOf(i, j))
            } else if(map[i][j] == 2){
                viruses.add(intArrayOf(i, j))
            }
        }
    }
    val temp = Array(N){IntArray(M)}
    var answer = 0
    fun copy(){
        for(i in 0 until N){
            temp[i] = map[i].copyOf()
        }
    }
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    fun bfs(){
        var count = 0
        val queue = ArrayDeque<IntArray>()
        for(arr in viruses){
            queue.add(arr)
        }
        while(queue.isNotEmpty()){
            val(y, x) = queue.removeFirst()
            for(i in 0 until dy.size){
                val ny = y + dy[i]
                val nx = x + dx[i]
                if(ny >= 0 && ny < N && nx >= 0 && nx < M && temp[ny][nx] == 0){
                    temp[ny][nx] = 2
                    queue.add(intArrayOf(ny, nx))
                }
            }
        }
        for(i in 0 until N){
            for(j in 0 until M){
                if(temp[i][j] == 0) count++
            }
        }
        answer = maxOf(answer, count)
    }
    fun dfs(level: Int, idx: Int){
        if(level == 3){
            copy()
            bfs()
            return
        }
        for(i in idx until empties.size){
            val (y, x) = empties[i]
            map[y][x] = 1
            dfs(level + 1, i + 1)
            map[y][x] = 0
        }
    }
    dfs(0, 0)
    println(answer)
}
```