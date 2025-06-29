# 백준 16724번: 피리 부는 사나이

> 문제: https://www.acmicpc.net/problem/16724

### 문제 풀이

Union Find

지도 밖으로 나가는 방향이 주어지지 않음으로써 그래프는 무조건 사이클이 생기게 됨

SAFE ZONE을 최소로 두려면 사이클 갯수만큼 두면 됨

행의 좌표를 x, 열의 좌표를 y라고 했을 때 x, y가 더 작은 노드가 루트 노드

맵의 방향에 따라서 그래프를 탐색해 루트 노드를 찾음

모든 좌표를 확인하고 루트 노드가 되는 노드의 갯수를 세면 사이클의 갯수이므로 정답

### 풀이 설명

문제 조건에 따라 칸마다 방향이 다른 2차원 배열의 지도에서 지도 밖으로 나가는 방향이 주어지지 않는다면 항상 방향에 따라 이동한 후의 다음 칸의 방향도 지도 안의 다른 칸을 가리키는 방향을 가지고 있기 때문에 이동하다 보면 이미 왔던 칸에 도달할 수 밖에 없음

즉 모든 칸은 사이클에 연결되게 되어있음

그러므로 SAFE ZONE을 최소로 해 지도의 모든 구역에서 도달할 수 있게 하려면 연결되는 사이클 당 하나씩 두면 해결이 됨

Union Find를 이용해 같은 사이클에 도달할 수 있는 점을 합치면 최종적으로 루트 노드의 갯수가 사이클의 갯수가 됨으로 이를 세면 정답이 나옴

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val map = Array(N){CharArray(M)}
    val visit = Array(N){BooleanArray(M){false} }
    val isRoot = Array(N){BooleanArray(M){false} }
    val root = Array(N){Array(M){IntArray(2) } }
    var answer = 0
    for(i in 0 until N){
        val s = br.readLine()
        for(j in 0 until M){
            map[i][j] = s[j]
            root[i][j][0] = i
            root[i][j][1] = j
        }
    }
    for(i in 0 until N){
        for(j in 0 until M){
            if(visit[i][j]){
                continue
            }
            var prevX = i
            var prevY = j
            var x = if(map[i][j] == 'D') i + 1 else if(map[i][j] == 'U') i - 1 else i
            var y = if(map[i][j] == 'L') j - 1 else if(map[i][j] == 'R') j + 1 else j
            while(true){
                union(root, prevX, prevY, x, y)
                if(visit[x][y]){
                    break
                }
                visit[x][y] = true
                prevX = x
                prevY = y
                x = if(map[prevX][prevY] == 'D') prevX + 1 else if(map[prevX][prevY] == 'U') prevX - 1 else prevX
                y = if(map[prevX][prevY] == 'L') prevY - 1 else if(map[prevX][prevY] == 'R') prevY + 1 else prevY
            }
        }
    }
    for(i in 0 until N){
        for(j in 0 until M){
            val temp = find(root, i , j)
            val x = temp[0]
            val y = temp[1]
            if(!isRoot[x][y]){
                isRoot[x][y] = true
                answer++
            }
        }
    }
    println(answer)
}

fun find(root: Array<Array<IntArray>>, x: Int, y: Int): IntArray{
    if(root[x][y][0] == x && root[x][y][1] == y){
        return intArrayOf(root[x][y][0], root[x][y][1])
    } else {
        val temp = find(root, root[x][y][0], root[x][y][1])
        root[x][y][0] = temp[0]
        root[x][y][1] = temp[1]
        return intArrayOf(root[x][y][0], root[x][y][1])
    }
}

fun union(root: Array<Array<IntArray>>, ax: Int, ay: Int, bx: Int, by: Int){
    val aFind = find(root, ax, ay)
    val bFind = find(root, bx, by)
    val aFindX = aFind[0]
    val aFindY = aFind[1]
    val bFindX = bFind[0]
    val bFindY = bFind[1]
    if(aFindX < bFindX){
        root[bFindX][bFindY][0] = aFindX
        root[bFindX][bFindY][1] = aFindY
    } else if(aFindX > bFindX){
        root[aFindX][aFindY][0] = bFindX
        root[aFindX][aFindY][1] = bFindY
    } else {
        if(aFindY < bFindY){
            root[bFindX][bFindY][0] = aFindX
            root[bFindX][bFindY][1] = aFindY
        } else if(aFindY > bFindY){
            root[aFindX][aFindY][0] = bFindX
            root[aFindX][aFindY][1] = bFindY
        }
    }
}
```