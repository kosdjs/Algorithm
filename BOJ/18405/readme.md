# 백준 18405번: 경쟁적 전염

> 문제: https://www.acmicpc.net/problem/18405

### 문제 풀이

BFS

(X,Y)에서 부터 상하좌우 방향으로 BFS를 진행해 거리가 S이내인 칸들 중 거리가 가장 짧고 바이러스의 숫자가 낮은 칸에 있는 바이러스를 출력하면 정답

### 풀이 설명

시험관에 존재하는 모든 바이러스는 1초마다 상, 하, 좌, 우의 방향으로 증식해 나가며, 매 초마다 번호가 낮은 종류의 바이러스부터 먼저 증식하고, 증식 과정에서 특정한 칸에 이미 어떠한 바이러스가 존재한다면, 그 곳에는 다른 바이러스가 들어갈 수 없기 때문에 현재 칸에서 거리가 가장 가까운 바이러스 중 번호가 낮은 종류의 바이러스가 현재 칸에 증식되게 된다.

시험관의 크기와 바이러스의 위치 정보가 주어졌을 때, S초가 지난 후에 (X,Y)에 존재하는 바이러스의 종류는 (X,Y)에서 거리가 S 이내인 칸들 중 거리가 가장 가까운 바이러스 중 번호가 낮은 종류의 바이러스가 된다.

따라서 이를 구하기 위해서 (X,Y)칸에서부터 BFS를 이용해 거리가 S인 칸들을 모두 탐색한다. 탐색하면서 거리가 가장 가까운 바이러스 중 번호가 낮은 종류의 바이러스를 찾기 위해 거리 정보를 minDist, 바이러스의 번호를 answer에 저장한다. 이때 거리가 S이내인 칸에 바이러스가 존재하지 않을 수 있으므로 answer를 0, minDist를 Int.MAX_VALUE로 초기화해준다.

BFS를 통해 칸을 탐색하면서 바이러스를 처음 찾으면 이 칸까지의 거리가 minDist보다 작을 것이므로 이 때 answer에 바이러스 종류, minDist에 이 칸까지의 거리를 대입해준다. 만약 거리가 minDist와 같은 바이러스를 찾는다면 answer에 저장된 바이러스 번호와 현재 찾은 바이러스의 번호를 비교해 더 낮은 번호를 저장한다.

이 과정을 통해 answer에 (X,Y)에 S초가 지난 후에 (X,Y)에 존재하는 바이러스의 번호가 저장되므로 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val map = Array(N + 1){ IntArray(N + 1) }
    val visit = Array(N + 1){ BooleanArray(N + 1) }
    for(i in 1..N){
        st = StringTokenizer(br.readLine())
        for(j in 1..N){
            map[i][j] = st.nextToken().toInt()
        }
    }
    val q = ArrayDeque<IntArray>()
    st = StringTokenizer(br.readLine())
    val S = st.nextToken().toInt()
    val X = st.nextToken().toInt()
    val Y = st.nextToken().toInt()
    var answer = 0
    var minDist = Int.MAX_VALUE
    visit[X][Y] = true
    if(map[X][Y] != 0){
        answer = map[X][Y]
    } else {
        q.add(intArrayOf(X, Y, 0))
        val dx = intArrayOf(-1, 0, 1, 0)
        val dy = intArrayOf(0, -1, 0, 1)
        while(q.isNotEmpty()){
            val (x, y, dist) = q.removeFirst()
            if(dist == S){
                continue
            }
            for(i in 0 until 4){
                val nx = x + dx[i]
                val ny = y + dy[i]
                if(nx > 0 && nx <= N && ny > 0 && ny <= N && !visit[nx][ny]){
                    visit[nx][ny] = true
                    q.add(intArrayOf(nx, ny, dist + 1))
                    if(map[nx][ny] != 0){
                        if(dist + 1 < minDist){
                            minDist = dist + 1
                            answer = map[nx][ny]
                        } else if(dist + 1 == minDist && map[nx][ny] < answer){
                            answer = map[nx][ny]
                        }
                    }
                }
            }
        }
    }
    println(answer)
}
```