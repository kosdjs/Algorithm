# 백준 15671번: 오델로

> 문제: https://www.acmicpc.net/problem/15671

### 문제 풀이

구현

board[r][c] = (r, c) 칸에 놓인 돌의 색, 0일 때 빈 칸, 1일 때 검은색, 2일 때 흰색

blackTurn = 현재 검은 돌의 차례인지

dr, dc = 위 방향부터 시계방향으로 8방향으로 움직일 때 행과 열을 움직이는 양

count = 검은 돌과 흰 돌의 갯수

돌을 하나씩 놓는 위치가 주어지고 검은 돌 흰 돌 번갈아 가면서 놓음

정상적으로 진행되는 위치만 주어지고 항상 이기는 상황만 주어진다고 했으므로 구현해야 할 것은 돌을 놓을 때 상대방의 돌을 뒤집는 것만 하면 됨

돌을 뒤집는 조건은 새로 놓은 돌에서부터 8방향으로 탐색하며 같은 색의 돌을 찾으면 그 사이에 전부 상대방의 돌만 있을때 사이에 있는 모든 상대방의 돌을 현재 돌을 놓은 색의 돌로 뒤집는 것임

그러므로 위에서부터 시계방향으로 한 방향으로 이동할 때 행과 열을 어떻게 이동할 지 dr, dc에 저장하고 방향마다 한 칸씩 이동하면서 같은 돌이 있는 칸을 만날때까지 이동하면 됨

그러나 이동하는 과정에서 빈 칸을 만나거나 판 밖으로 나가면 뒤집을 돌이 없는 것이므로 넘어가면 됨

이에 따라 돌을 놓을때마다 8방향으로 뒤집을 돌이 있는지 확인해 있으면 돌을 뒤집으면 모든 돌이 어떻게 있는지 board에 저장되므로 이를 출력하면서 검은 돌과 흰 돌의 갯수를 각각 count[0], count[1]에 저장함

그 이후에 count[0]과 count[1]을 비교해 검은 돌이 많으면 Black, 흰 돌이 많으면 White를 출력하면 정답

### 풀이 설명

6 x 6 칸의 판 위에서 진행되는 오델로 게임이 있을 때 처음부터 한 칸씩 놓이는 돌의 위치가 주어졌을 때 마지막에 판의 결과와 어느 색이 이겼는지 구하는 문제이다.

오델로는 검은 돌, 흰 돌 순서로 번갈아 가면서 돌 하나씩을 놓으며 돌을 하나 놓았을 때 그 돌을 기준으로 상하좌우 및 대각선 총 8방향으로 확인했을 때 새로 놓은 돌과 같은 색의 돌 사이에 전부 상대방의 돌만 있다면 그 돌들을 전부 뒤집어 같은 색으로 만드는 게임이다.

그리고 항상 처음 가운데 4칸에 왼쪽 위와 오른쪽 아래에 흰색 돌, 오른쪽 위와 왼쪽 아래에 검은 돌을 놓고 시작한다.

문제 조건에서 항상 올바르게 놓이는 위치만 주어진다고 했고, 항상 이기는 상황일 때만 주어진다고 했으므로 위치마다 돌을 놓으며 8방향으로 확인해 사이에 끼는 모든 상대의 돌을 뒤집는 것만 구현하면 된다.

이때 새로 놓은 돌의 좌표에서 8방향으로 한 칸씩 이동할 때 같은 돌을 발견하기 전에 빈 칸을 마주하거나 판을 벗어나게 된다면 뒤집을 돌이 없는 것이므로 그 방향은 더 탐색하지 않아도 된다.

이를 구현하기 위해 판을 board라는 이차원 배열로 만들고 이 배열에 들어가는 값이 0이면 빈칸, 1이면 검은 돌, 2면 흰 돌이 있는 것이다. 또한 현재 차례가 검은 돌인지 여부를 blackTurn으로 정의하고 값이 true라면 검은 돌의 차례, false라면 흰 돌의 차례로 처리하면 된다.

매번 돌의 위치를 r, c에 입력받고 현재 blackTurn에 따라 놓아야 하는 돌을 stone에 저장한다. 그 이후에 8방향으로 한 칸 이동할 때 행과 열을 어떻게 바꾸어야 하는지를 dr, dc 배열에 저장하고 이에 따라 8방향으로 한 칸씩 이동하면서 뒤집을 돌이 있는지 확인하면 된다.

이에 따라 모든 위치에 정해진 돌을 놓고나면 board에 게임이 끝난 이후의 판이 저장되어 있으므로 이를 출력하면 된다. 그 이후에 누가 이겼는지는 게임 판의 돌의 갯수를 세면 알 수 있으므로 count[0]에 검은 돌의 갯수, count[1]에 흰 돌의 갯수를 세서 저장하면 된다.

항상 누군가가 이기는 게임만 주어진다고 했으므로 count[0]과 count[1]을 비교해 검은 돌의 갯수가 많으면 Black, 흰 돌의 갯수가 많으면 White를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val board = Array(6){ IntArray(6) }
    board[2][2] = 2
    board[2][3] = 1
    board[3][2] = 1
    board[3][3] = 2
    var blackTurn = true
    val dr = intArrayOf(1, 1, 0, -1, -1, -1, 0, 1)
    val dc = intArrayOf(0, 1, 1, 1, 0, -1, -1, -1)
    val sb = StringBuilder()
    for(i in 0 until N){
        val r = nextInt() - 1
        val c = nextInt() - 1
        val stone = if(blackTurn) 1 else 2
        board[r][c] = stone
        for(k in 0 until 8){
            var nr = r + dr[k]
            var nc = c + dc[k]
            while(nr < 6 && nr >= 0 && nc < 6 && nc >= 0){
                if(board[nr][nc] == 0) break
                if(board[nr][nc] == stone){
                    while(nr != r || nc != c){
                        nr -= dr[k]
                        nc -= dc[k]
                        board[nr][nc] = stone
                    }
                    break
                }
                nr += dr[k]
                nc += dc[k]
            }
        }
        blackTurn = !blackTurn
    }
    var count = IntArray(2)
    for(r in 0 until 6){
        for(c in 0 until 6){
            when(board[r][c]){
                0 -> {
                    sb.append(".")
                }
                1 -> {
                    sb.append("B")
                    count[0]++
                }
                2 -> {
                    sb.append("W")
                    count[1]++
                }
            }
        }
        sb.append("\n")
    }
    sb.append(if(count[0] > count[1]) "Black" else "White")
    print(sb)
}
```