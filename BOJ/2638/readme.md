# 백준 2638번: 치즈

> 문제: https://www.acmicpc.net/problem/2638

### 문제 풀이

BFS, 구현

cheeseCount = 남은 치즈 개수

map = 현재 모눈종이의 상태

air = 외부 공기와 접촉된 면의 수

visit = 외부 공기 처리 여부

queue = 외부 공기인 칸을 저장하는 큐

가장자리는 항상 공기라고 했고 치즈 내부의 공기는 외부 공기가 아닌 것으로 간주한다고 했으므로 외부 공기는 가장자리부터 BFS로 탐색해야 함

큐에 (0, 0)을 저장하고 이 칸이 큐에 다시 들어가지 않게 하기 위해 visit[0][0]에 true를 대입

큐에서 외부 공기의 좌표 y, x를 꺼내 상하좌우의 칸 ny, nx에 대해 이 칸이 외부 공기와 맞닿은 칸이므로 air[ny][nx]를 1 증가시키고 만약 map[ny][nx]가 0이라면 이 칸도 외부 공기가 되므로 큐에 (ny, nx)를 넣고 큐에 중복으로 들어가지 않도록 visit[ny][nx]에 true를 대입함

이를 큐가 빌 때까지 반복하면 모든 외부 공기인 칸과 그 인접한 칸에 몇 칸이 외부 공기와 인접해있는지 air 배열에 저장됨

이제 모든 칸을 확인해 map[y][x]가 1이고 air[y][x]가 2보다 크거나 같은칸에 대해 map[y][x]에 0을 대입하고 cheeseCount를 1 감소시킴

여기까지 처리하면 치즈가 한 번 녹은 것이므로 time을 1 증가시킴

이 과정을 cheeseCount가 0이 될때까지 반복하면 모든 치즈가 녹는 것이고 모든 치즈가 녹을 때까지 걸리는 시간이 time에 저장되므로 출력하면 정답

### 풀이 설명

N×M의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다. 단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다. 이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다. 그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다. 따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.

![](https://velog.velcdn.com/images/kosdjs/post/d7733780-b875-4a79-b06c-08cf87f3c5d6/image.png)

<그림 1>

<그림 2>와 같이 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다. 그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다. 그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.

![](https://velog.velcdn.com/images/kosdjs/post/157b5dcc-9afd-4cd2-8ff7-55d0e1b2f97c/image.png)

<그림 2>

![](https://velog.velcdn.com/images/kosdjs/post/f5ceb596-f504-4679-818c-6434b6107b5d/image.png)

<그림 3>

모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다. 입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.

한 시간에 한 번씩 외부 공기와 2면 이상 닿은 치즈가 녹는 것이므로 치즈가 모두 녹는 데 몇 번 걸리는지 횟수를 세면 된다. 그리고 외부 공기와 몇 면이 닿았는지를 매 번 다시 계산해야 한다. 이를 저장하기 위해 air라는 배열을 정의한다.

빈 칸이어도 치즈로 둘러 쌓여있는 칸은 외부 공기로 치지 않기 때문에 먼저 외부 공기인 칸부터 찾아야 한다. 그러므로 가장자리부터 BFS를 이용해 외부 공기인 칸을 찾는다. 이렇게 찾은 칸들과 맞닿은 칸은 외부 공기와 1면이 닿는 것이므로 찾을 때마다 air배열에 값을 추가한다.

그 이후에 모든 칸을 확인하면서 치즈가 있으면서 외부 공기와 2면 이상 닿은 칸의 치즈가 녹는 것이므로 map의 값을 0으로 바꿔주고 cheeseCount의 값을 1 감소시켜 준다.

이 과정을 거치면 치즈가 한 번 녹은 것이므로 time을 1 증가시켜준다. 이 과정을 cheeseCount가 0이 아닐 동안 반복하면 모든 치즈가 녹을 때까지 반복하게 된다. 그리고 반복 횟수가 time에 저장되므로 이 값이 구해야 하는 모든 치즈가 녹는 시간이 되므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    var cheeseCount = 0
    val map = Array(N){IntArray(M)}
    val air = Array(N){IntArray(M)}
    val visit = Array(N){BooleanArray(M)}
    val queue = ArrayDeque<IntArray>()
    for(i in 0 until N){
        for(j in 0 until M){
            map[i][j] = nextInt()
            if(map[i][j] == 1) cheeseCount++
        }
    }
    var time = 0
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    while(cheeseCount > 0){
        visit[0][0] = true
        queue.add(intArrayOf(0,0))
        while(queue.isNotEmpty()){
            val (y, x) = queue.removeFirst()
            for(i in 0 until 4){
                val ny = y + dy[i]
                val nx = x + dx[i]
                if(ny >= 0 && ny < N && nx >= 0 && nx < M){
                    air[ny][nx]++
                    if(map[ny][nx] == 0 && !visit[ny][nx]){
                        visit[ny][nx] = true
                        queue.add(intArrayOf(ny, nx))
                    }
                }
            }
        }
        for(y in 0 until N){
            for(x in 0 until M){
                if(map[y][x] == 1 && air[y][x] >= 2){
                    map[y][x] = 0
                    cheeseCount--
                }
                air[y][x] = 0
                visit[y][x] = false
            }
        }
        time++
    }
    println(time)
}
```