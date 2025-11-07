# 백준 17836번: 공주님을 구해라!

> 문제: https://www.acmicpc.net/problem/17836

### 문제 풀이

BFS

swordY, swordX = 검이 있는 위치

swordTime = 검까지 벽을 뚫지 않고 도달하는 시간

answer = 공주의 위치까지 도달하는 최소 시간

throughTime = 검을 얻은 위치에서 공주의 위치까지 벽을 뚫고 도달하는 시간

BFS를 통해 벽을 뚫지 않고 검의 위치와 공주의 위치로 도달하는 시간을 구함 그리고 T시간 내에 탐색이 가능한지 여부를 판단해야 하므로 answer와 swordTime을 T + 1로 초기화

BFS를 통해 검의 위치에 도달하면 그 때의 거리를 swordTime, 공주의 위치에 도달하면 answer에 저장함

검의 위치에 도달하면 그때부터는 벽을 뚫고 다닐 수 있으므로 검의 위치부터 공주의 위치까지는 맨하탄 거리로 도달할 수 있으므로 throughTime에 맨하탄 거리를 저장

벽을 뚫지 않고 도달한 시간 answer와 검을 얻고 벽을 뚫어서 도달한 시간 swordTime + throughTime을 비교해 더 작은 값을 answer에 저장

이때 answer에 저장된 값이 T보다 작거나 같으면 T시간 내에 공주 구출 가능한 것이므로 저장된 값 출력하고 아니면 Fail 출력하면 정답

### 풀이 설명

용사는 마왕이 숨겨놓은 공주님을 구하기 위해 (N, M) 크기의 성 입구 (1,1)으로 들어왔다. 마왕은 용사가 공주를 찾지 못하도록 성의 여러 군데 마법 벽을 세워놓았다. 용사는 현재의 가지고 있는 무기로는 마법 벽을 통과할 수 없으며, 마법 벽을 피해 (N, M) 위치에 있는 공주님을 구출해야만 한다.

마왕은 용사를 괴롭히기 위해 공주에게 저주를 걸었다. 저주에 걸린 공주는 T시간 이내로 용사를 만나지 못한다면 영원히 돌로 변하게 된다. 공주님을 구출하고 프러포즈 하고 싶은 용사는 반드시 T시간 내에 공주님이 있는 곳에 도달해야 한다. 용사는 한 칸을 이동하는 데 한 시간이 걸린다. 공주님이 있는 곳에 정확히 T시간만에 도달한 경우에도 구출할 수 있다. 용사는 상하좌우로 이동할 수 있다.

성에는 이전 용사가 사용하던 전설의 명검 "그람"이 숨겨져 있다. 용사가 그람을 구하면 마법의 벽이 있는 칸일지라도, 단숨에 벽을 부수고 그 공간으로 갈 수 있다. "그람"은 성의 어딘가에 반드시 한 개 존재하고, 용사는 그람이 있는 곳에 도착하면 바로 사용할 수 있다. 그람이 부술 수 있는 벽의 개수는 제한이 없다.

BFS를 이용하면 용사의 위치에서 공주의 위치까지 도달하는 시간을 구할 수 있다. 최단 시간을 구하는 것이므로 공주의 위치보다 검의 위치가 더 멀면 검까지 가야 할 이유가 없다. 따라서 BFS로 용사의 위치에서 공주의 위치까지 가면서 검의 위치까지 도달한다면 검까지 도달하는 시간을 따로 저장해야 한다.

answer에 공주의 위치까지 벽을 뚫지 않고 BFS를 통해 가는 거리를 저장하고, swordTime에 검까지 벽을 뚫지 않고 도달하는 거리를 저장한다. 검을 얻고 나서는 벽을 뚫고 지나갈 수 있으므로 맨하탄 거리로 검의 위치부터 공주의 위치까지 도달하는 거리를 throughTime에 저장한다.

그 후에 answer에 저장된 값과 검까지 도달하고 공주의 위치까지 가는 거리 swordTime + throughTime을 비교해 더 작은 값을 answer에 저장한다. 그리고 이 값이 T보다 작거나 같다면 T시간 내에 도달할 수 있는 것이므로 answer에 저장된 값을 출력하면 되고, 아니라면 불가능하므로 Fail을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val T = st.nextToken().toInt()
    var swordY = 0
    var swordX = 0
    val map = Array(N){IntArray(M)}
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            map[i][j] = st.nextToken().toInt()
            if(map[i][j] == 2){
                swordY = i
                swordX = j
            }
        }
    }
    var answer = T + 1
    var swordTime = T + 1
    var visit = Array(N){BooleanArray(M)}
    visit[0][0] = true
    val queue = ArrayDeque<IntArray>()
    queue.add(intArrayOf(0, 0, 0))
    val dy = intArrayOf(0, -1, 0, 1)
    val dx = intArrayOf(-1, 0, 1, 0)
    while(queue.isNotEmpty()){
        val (y, x, dist) = queue.removeFirst()
        for(i in 0 until 4){
            val ny = y + dy[i]
            val nx = x + dx[i]
            if(ny >= 0 && ny < N && nx >= 0 && nx < M && !visit[ny][nx] && map[ny][nx] != 1){
                if(ny == swordY && nx == swordX){
                    swordTime = dist + 1
                }
                if(ny == N - 1 && nx == M - 1){
                    answer = dist + 1
                    queue.clear()
                    break
                } else {
                    visit[ny][nx] = true
                    queue.add(intArrayOf(ny, nx, dist + 1))
                }
            }
        }
    }
    val throughTime = N - 1 - swordY + M - 1 - swordX
    answer = minOf(answer, swordTime + throughTime)
    println(if(answer <= T) answer else "Fail")
}
```