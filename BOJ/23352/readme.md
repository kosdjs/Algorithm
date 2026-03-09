# 백준 23352번: 방탈출

> 문제: https://www.acmicpc.net/problem/23352

### 문제 풀이

브루트 포스, BFS

map[i][j] = (i, j) 칸에 적힌 숫자

visit[i][j] = BFS를 통해 해당 칸을 도달했는지 여부

max = 가장 긴 경로의 시작 방과 끝 방에 적힌 숫자의 합 중 최댓값

maxLength = 가장 긴 경로의 길이

모든 칸에 대해 해당 칸을 시작 방으로 잡고 BFS를 통해 다른 모든 칸까지의 최단 경로를 확인하면서 시작 방과 끝 방의 합을 확인하면 됨

시작 방이 (i, j) 칸이라면 이 칸에서부터 상하좌우로 탐색하며 도달한 칸 (y, x)가 현재 경로의 끝 방이고 현재 경로의 길이가 length이므로 length가 maxLength와 같다면 현재까지 찾은 최대 경로의 길이가 같으므로 map[i][j] + map[y][x]와 max에 저장된 값 중 최댓값을 max에 저장하면 됨

만약 maxLength보다 length가 더 크다면 최단 경로의 길이가 더 긴 경로를 찾은 것이므로 maxLength를 length로 갱신하고 max도 map[i][j] + map[y][x]로 갱신하면 됨

이를 모든 칸 (i, j)에 대해 반복하면 max가 찾는 최단 경로 중 가장 긴 경로에 대해 시작 방과 끝 방에 적힌 숫자의 합이므로 출력하면 정답

### 풀이 설명

N x M 격자판에서 상하좌우로 움직이며 0이 아닌 방으로 움직일 수 있을 때 두 방의 최단 경로가 가장 긴 경로 중 시작 방과 끝 방에 적힌 숫자의 합이 가장 큰 합을 구하는 문제이다.

N, M이 최대 50이기 때문에 최대 250칸이 있을 수 있고, 각 칸을 시작 칸으로 경로를 찾는다고 하면 각 칸마다 해당 칸에서부터 다른 250칸을 모두 탐색한다고 하면 62500번 탐색하게 된다.

따라서 모든 칸에서 BFS를 통해 도달할 수 있는 모든 최단 경로를 검사하면서 가장 긴 경로에 대해 시작 방과 끝 방에 적힌 숫자의 합을 확인하면 된다.

또한 BFS로 경로를 탐색할 때 해당 경로의 길이를 확인해야 하므로 큐에 도달한 좌표와 현재 경로의 길이를 같이 넘겨야 한다.

그러므로 모든 칸 (i, j)를 시작 방으로 잡으면 큐에 도달한 좌표 (i, j)와 현재 경로의 길이 0을 같이 큐에 넘기고 탐색을 시작한다.

큐에서 현재 좌표 (y, x)와 경로의 길이 length를 꺼내서 현재까지 최단 경로의 최대 길이 maxLength와 비교하며 같다면 현재까지 최단 경로 중 가장 긴 경로의 시작 방과 끝 방에 적힌 숫자의 합 중 최댓값 max와 현재 경로의 시작 방과 끝 방에 적힌 숫자의 합 map[i][j] + map[y][x]를 비교해 최댓값을 max에 저장하면 된다.

만약 현재 경로 length가 maxLength보다 크다면 더 긴 최단 경로를 찾은 것이므로 maxLength를 length로 갱신하고 max에 현재 경로의 시작 방과 끝 방에 적힌 숫자의 합 map[i][j] + map[y][x]로 갱신하면 된다.

이를 모든 칸 (i, j)에 대해 BFS로 모든 경로를 탐색하면 max에 문제에서 찾는 최단 경로 중 가장 긴 경로에 대해 시작 방과 끝 방에 적힌 숫자의 합 중 최댓값이 저장되므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
package BOJ_23352

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val map = Array(N){ IntArray(M){ nextInt() } }
    val visit = Array(N){ BooleanArray(M) }
    val dy = intArrayOf(-1, 0, 1, 0)
    val dx = intArrayOf(0, -1, 0, 1)
    val queue = ArrayDeque<IntArray>()
    var max = 0
    var maxLength = 0
    for(i in 0 until N){
        for(j in 0 until M){
            if(map[i][j] != 0){
                for(k in 0 until N){
                    visit[k].fill(false)
                }
                visit[i][j] = true
                queue.add(intArrayOf(i, j, 0))
                while (queue.isNotEmpty()){
                    val (y, x, length) = queue.removeFirst()
                    if(length == maxLength) max = maxOf(max, map[i][j] + map[y][x])
                    if(length > maxLength){
                        max = map[i][j] + map[y][x]
                        maxLength = length
                    }
                    for(k in 0 until 4){
                        val ny = y + dy[k]
                        val nx = x + dx[k]
                        if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue
                        if(map[ny][nx] == 0 || visit[ny][nx]) continue
                        visit[ny][nx] = true
                        queue.add(intArrayOf(ny, nx, length + 1))
                    }
                }
            }
        }
    }
    println(max)
}
```