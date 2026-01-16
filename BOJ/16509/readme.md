# 백준 16509번: 장군

> 문제: https://www.acmicpc.net/problem/16509

### 문제 풀이

구현, BFS

dy, dx = 현재 칸에서 상이 이동하는 좌표의 차이

block = 현재 이동하는 중간 경로의 좌표의 차이

visit = 해당 좌표를 방문한 적이 있는지 여부

상의 좌표 (R1, C1), 왕의 좌표 (R2, C2)를 입력받음

상의 좌표와 왕의 좌표가 같다면 이동할 필요 없이 바로 0을 출력하고 함수 반환

BFS를 실행하기 위해 visit 배열을 만들고 현재 좌표 visit[R1][C1]에 true 대입

현재 좌표와 이동 횟수를 저장하는 큐를 만들고 현재 좌표 R1, C1과 이동 횟수 0 삽입

큐가 빌 때까지 큐에 들어있는 좌표 y, x와 이동 횟수 count를 꺼냄

꺼낸 y, x에 대해 상의 이동 규칙에 맞도록 이동한 결과를 dy, dx를 이용해 그 때의 좌표 ny, nx를 구함

이 ny, nx가 좌표를 벗어나지 않고 방문한 적이 없다면 상의 이동규칙에 따라 중간이 막히지 않았는지 확인하기 위해 isBlocked를 false로 초기화하고 block의 좌표를 확인해 왕이 막고있을때만 true로 변경함

isBlocked가 true라면 이동하지 않도록 continue를 하고 아닐 경우에만 방문처리를 함

이동한 좌표가 왕의 좌표라면 더 이상 이동할 필요가 없으므로 현재 이동 횟수 count + 1을 출력하고 함수 반환

왕의 좌표가 아니라면 더 찾아야 하므로 이동한 좌표 ny, nx와 그 이동 횟수 count + 1을 큐에 넣음

이를 반복했을 때 함수가 반환되기 전에 반복문이 끝난다면 상이 왕의 좌표에 도달하지 못한 것이므로 -1을 출력하면 됨

### 풀이 설명

장기에서 상의 위치와 왕의 위치가 주어졌을 때 몇 번 이동해서 왕을 잡을 수 있는지 구하는 문제이다. 상을 이동하는 규칙이 정해져 있고 이동 횟수를 구하는 것이므로 BFS를 이용해 구할 수 있다.

상이 이동하는 규칙은 다음 그림과 같이 한 칸 직선으로 이동한 이후 대각선으로 두 칸 이동하는 것이다.

![](https://velog.velcdn.com/images/kosdjs/post/b658c7e9-03b7-4577-adaf-3150b0c23d13/image.png)

그러나 다음 그림과 같이 한 칸 직선, 두 칸 대각선으로 이동하는 경로에 다른 말이 있으면 이동을 하지 못한다.

![](https://velog.velcdn.com/images/kosdjs/post/a2487925-06b1-4d31-a4bc-6558613f977a/image.png)

그러므로 상이 이동할 때 중간 경로에 있는 칸들도 확인해야 한다는 뜻이다. 이제 이 규칙에 맞도록 상이 이동할 때 이동하는 횟수를 BFS로 구해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val R1 = nextInt()
    val C1 = nextInt()
    val R2 = nextInt()
    val C2 = nextInt()
    if(R1 == R2 && C1 == C2){
        println(0)
        return@run
    }
    val dy = intArrayOf(-3, -3, -2, 2, 3, 3, 2, -2)
    val dx = intArrayOf(-2, 2, 3, 3, 2, -2, -3, -3)
    val block = arrayOf(
        arrayOf(intArrayOf(-1, 0), intArrayOf(-2, -1)),
        arrayOf(intArrayOf(-1, 0), intArrayOf(-2, 1)),
        arrayOf(intArrayOf(0, 1), intArrayOf(-1, 2)),
        arrayOf(intArrayOf(0, 1), intArrayOf(1, 2)),
        arrayOf(intArrayOf(1, 0), intArrayOf(2, 1)),
        arrayOf(intArrayOf(1, 0), intArrayOf(2, -1)),
        arrayOf(intArrayOf(0, -1), intArrayOf(1, -2)),
        arrayOf(intArrayOf(0, -1), intArrayOf(-1, -2))
    )
    val visit = Array(10){ BooleanArray(9) }
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(R1, C1, 0))
    visit[R1][C1] = true
    while(queue.isNotEmpty()){
        val(y, x, count) = queue.removeFirst()
        for(i in 0 until 8){
            val ny = y + dy[i]
            val nx = x + dx[i]
            if(ny < 0 || ny > 9 || nx < 0 || nx > 8 || visit[ny][nx]) continue
            //막히지 않았는지 판단해야 함
            var isBlocked = false
            for((diffY, diffX) in block[i]){
                val routeY = y + diffY
                val routeX = x + diffX
                if(routeY == R2 && routeX == C2){
                    isBlocked = true
                    break
                }
            }
            if(isBlocked) continue
            visit[ny][nx] = true
            if(ny == R2 && nx == C2){
                println("${count + 1}")
                return@run
            }
            queue.add(intArrayOf(ny, nx, count + 1))
        }
    }
    println(-1)
}
```