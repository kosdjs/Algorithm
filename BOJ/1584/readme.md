# 백준 1584번: 게임

> 문제: https://www.acmicpc.net/problem/1584

### 문제 풀이

다익스트라, 0-1 bfs

$map[i][j] = (i, j)$칸의 구역 구분 $0$일 때 안전한 구역, $1$일 때 위험한 구역, $2$일 때 죽음의 구역

$dist[i][j] = (0, 0)$부터 $(i, j)$까지 갈 때 잃는 생명의 최솟값

$dist[0][0]$에 $0$을 대입하고 데크에 $(0, 0)$을 넣고 다음 순서를 반복함

1. 꺼낸 점이 목표지점인 $(500, 500)$라면 반복문을 탈출함

2. 데크의 앞에서 점을 하나 꺼내고 상하좌우가 지도를 벗어나는지 체크함

3. 지도에서 벗어나지 않는 상하좌우의 점이 현재 점을 거쳐 더 적게 생명을 잃고 도달할 수 있는지 확인함

4. 도달 가능한 점이면 그 점이 안전한 구역인지 위험한 구역인지 구분한 후 안전한 구역을 데크의 앞으로, 위험한 구역을 데크의 뒤에 삽입함

이 순서에 따라 반복하면 $dist[500][500]$의 값이 Int.MAX_VALUE이면 도달할 수 없는 것이고, 아니라면 그 값이 잃는 생명의 최솟값이기 때문에 -1 또는 값을 그대로 출력하면 정답

### 풀이 설명

다른 점에 도달할 때 잃는 생명을 비용이라고 생각하면 특정 점 $(0, 0)$에서 시작해 다른 점에 도달하는 최소 비용을 구하는 문제이므로 다익스트라 알고리즘이 사용 가능하다.

또한 다른 한 점에 도달할 때 드는 비용이 0 또는 1이기 때문에 데크를 사용한 0-1 bfs를 사용하면 더 빠르게 풀 수 있다.

이렇게 풀기 위해 $map[i][j]$을 $(i, j)$칸의 구역 구분 $0$일 때 안전한 구역, $1$일 때 위험한 구역, $2$일 때 죽음의 구역으로 정의하고, $dist[i][j]$를 $(0, 0)$부터 $(i, j)$까지 갈 때 잃는 생명의 최솟값으로 정의한다.

그리고 데크에 시작점 $(0, 0)$을 넣고 데크의 앞에서부터 다음 점을 확인해 현재 점을 거쳐 다음 점을 가는게 더 적은 비용으로 가는지 확인한 후 다음 점이 안전한 구역이면 추가되는 비용이 0이고, 위험한 구역이면 추가되는 비용이 1이므로 안전한 구역인 경우에는 비용이 증가하지 않기 때문에 비용이 최소가 되는 다음 점을 뽑기 쉽게 하기 위해 데크의 앞에 넣는다. 위험한 구역이면 비용이 증가하므로 데크의 뒤에 넣는다.

이렇게 $(500, 500)$에 도달하거나 데크가 빌때까지 탐색을 했다면 $dist[500][500]$이 $(0, 0)$부터 $(500, 500)$에 도달하는데 드는 최소 비용이 저장되었거나 도달 불가능하다면 Int.MAX_VALUE가 저장되어 있을 것이다. 따라서 이 값에 따라 -1 또는 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val map = Array(501){ IntArray(501) {0} }
    repeat(N){
        val st = StringTokenizer(br.readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()
        val minX = if (x1 < x2) x1 else x2
        val minY = if (y1 < y2) y1 else y2
        val maxX = if (x1 > x2) x1 else x2
        val maxY = if (y1 > y2) y1 else y2
        for(i in minX..maxX){
            for(j in minY..maxY){
                map[i][j] = 1
            }
        }
    }
    val M = br.readLine().toInt()
    repeat(M){
        val st = StringTokenizer(br.readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()
        val minX = if (x1 < x2) x1 else x2
        val minY = if (y1 < y2) y1 else y2
        val maxX = if (x1 > x2) x1 else x2
        val maxY = if (y1 > y2) y1 else y2
        for(i in minX..maxX){
            for(j in minY..maxY){
                map[i][j] = 2
            }
        }
    }
    val dist = Array(501){ IntArray(501) {Int.MAX_VALUE} }
    val deque = ArrayDeque<IntArray>()
    dist[0][0] = 0
    deque.addFirst(intArrayOf(0, 0))
    val dx = intArrayOf(-1, 0, 1, 0)
    val dy = intArrayOf(0, -1, 0, 1)
    while(deque.isNotEmpty()){
        val (x, y) = deque.removeFirst()
        if(x == 500 && y == 500) break
        for(k in 0 until 4){
            val nx = x + dx[k]
            val ny = y + dy[k]
            if(nx < 0 || nx > 500 || ny < 0 || ny > 500) continue
            if(dist[nx][ny] > dist[x][y] + map[nx][ny]){
                if(map[nx][ny] == 0){
                    dist[nx][ny] = dist[x][y] + map[nx][ny]
                    deque.addFirst(intArrayOf(nx, ny))
                } else if(map[nx][ny] == 1){
                    dist[nx][ny] = dist[x][y] + map[nx][ny]
                    deque.addLast(intArrayOf(nx, ny))
                }
            }
        }
    }
    if(dist[500][500] == Int.MAX_VALUE){
        println(-1)
    } else {
        println(dist[500][500])
    }
}
```