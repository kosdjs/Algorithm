# 백준 6593번: 상범 빌딩

> 문제: https://www.acmicpc.net/problem/6593

### 문제 풀이

BFS

시작 지점에서 동,서,남,북,상,하의 방향으로 BFS를 통해 탐색을 하며 끝 지점을 만나면 그때의 시간, 아니라면 불가능을 출력하면 정답

### 풀이 설명

상범 빌딩은 각 변의 길이가 1인 정육면체(단위 정육면체)로 이루어져있다. 각 정육면체는 금으로 이루어져 있어 지나갈 수 없거나, 비어있어서 지나갈 수 있게 되어있다. 당신은 각 칸에서 인접한 6개의 칸(동,서,남,북,상,하)으로 1분의 시간을 들여 이동할 수 있다. 즉, 대각선으로 이동하는 것은 불가능하다. 그리고 상범 빌딩의 바깥면도 모두 금으로 막혀있어 출구를 통해서만 탈출할 수 있다.

이때 상범 빌딩을 탈출할 수 있는지 여부와 그때의 최소 시간을 구하는 방법은 BFS를 이용하는 것이다. BFS를 사용하기 위해 방문 여부를 저장하는 visit 배열, 시작 지점의 좌표, 끝 지점의 좌표를 저장한다.

이동 방향이 동,서,남,북,상,하로 정해졌기 때문에 방향 처리를 하기 위해 dn, dr, dc배열을 만들어 반복문으로 쉽게 이동처리를 하도록 만든다.

큐에 시작지점의 좌표와 시간을 넣고 큐가 빌 때까지 큐에서 좌표와 시간을 꺼내 이동 처리를 한 후 이미 방문하지 않았고 이동이 가능한 칸에 대해 방문처리를 하고 큐에 좌표와 그때의 시간을 넣는다. 이때 방문한 칸이 끝 지점이라면 이때의 시간을 출력해주고 큐를 비운다.

이렇게 시작 지점에서부터 BFS를 통해 탐색을 진행한 후에 끝 지점의 visit이 false라면 시작 지점에서 끝 지점으로 이동할 수 없다는 뜻이므로 불가능하다고 출력하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    while(true){
        var st = StringTokenizer(br.readLine())
        val N = st.nextToken().toInt()
        val R = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        if(N == 0 && R == 0 && C == 0){
            break
        }
        var startN = 0
        var startR = 0
        var startC = 0
        var endN = 0
        var endR = 0
        var endC = 0
        val map = Array(N){Array(R){ CharArray(C) } }
        val visit = Array(N){Array(R){ BooleanArray(C) } }
        for(n in 0 until N){
            for(r in 0 until R){
                val s = br.readLine()
                for(c in 0 until C){
                    map[n][r][c] = s[c]
                    if(s[c] == 'S'){
                        startN = n
                        startR = r
                        startC = c
                    }
                    if(s[c] == 'E'){
                        endN = n
                        endR = r
                        endC = c
                    }
                }
            }
            br.readLine()
        }
        visit[startN][startR][startC] = true
        val queue = ArrayDeque<IntArray>()
        queue.add(intArrayOf(startN, startR, startC, 0))
        val dr = intArrayOf(0, -1, 0, 1, 0, 0)
        val dc = intArrayOf(-1, 0, 1, 0, 0, 0)
        val dn = intArrayOf(0, 0, 0, 0, -1, 1)
        while(queue.isNotEmpty()){
            val(n, r, c, time) = queue.removeFirst()
            for(i in 0 until 6){
                val nn = n + dn[i]
                val nr = r + dr[i]
                val nc = c + dc[i]
                if(nn >= 0 && nn < N && nr >= 0 && nr < R && nc >= 0 && nc < C && map[nn][nr][nc] != '#' && !visit[nn][nr][nc]){
                    visit[nn][nr][nc] = true
                    queue.add(intArrayOf(nn, nr, nc, time + 1))
                    if(map[nn][nr][nc] == 'E'){
                        println("Escaped in ${time + 1} minute(s).")
                        queue.clear()
                    }
                }
            }
        }
        if(!visit[endN][endR][endC]){
            println("Trapped!")
        }
    }
}
```