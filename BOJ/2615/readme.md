# 백준 2615번: 오목

> 문제: https://www.acmicpc.net/problem/2615

### 문제 풀이

구현

map[i][j] = i 번째 세로 줄, j 번째 가로 줄에 있는 바둑 돌의 색

visit[k][i][j] = i 번째 세로 줄, j 번째 가로 줄에서 k의 방향으로 탐색을 했는지

count = i, j에서부터 같은 색의 돌이 몇개인지

가장 왼쪽의 칸에서부터 탐색이 되지 않은 칸이라면 가로 줄의 방향, 세로 줄의 방향, 오른쪽 아래 대각선의 방향, 오른쪽 위 대각선의 방향으로 같은 색의 돌이 몇 개인지 센다.

같은 색의 돌이 정확히 5개인 경우에만 이긴 것으로 처리해 누가 이겼는지, 어느 위치인지를 answer, y, x에 저장하고 출력하면 정답

### 풀이 설명

바둑판에는 19개의 가로줄과 19개의 세로줄이 그려져 있는데 가로줄은 위에서부터 아래로 1번, 2번, ... ,19번의 번호가 붙고 세로줄은 왼쪽에서부터 오른쪽으로 1번, 2번, ... 19번의 번호가 붙는다.

같은 색의 바둑알이 연속적으로 다섯 알을 놓이면 그 색이 이기게 된다. 여기서 연속적이란 가로, 세로 또는 대각선 방향 모두를 뜻한다. 하지만 여섯 알 이상이 연속적으로 놓인 경우에는 이긴 것이 아니다.

입력으로 바둑판의 어떤 상태가 주어졌을 때, 검은색이 이겼는지, 흰색이 이겼는지 또는 아직 승부가 결정되지 않았는지를 판단하는 프로그램을 작성하시오. 단, 검은색과 흰색이 동시에 이기거나 검은색 또는 흰색이 두 군데 이상에서 동시에 이기는 경우는 입력으로 들어오지 않는다.

첫줄에 검은색이 이겼을 경우에는 1을, 흰색이 이겼을 경우에는 2를, 아직 승부가 결정되지 않았을 경우에는 0을 출력한다. 검은색 또는 흰색이 이겼을 경우에는 둘째 줄에 연속된 다섯 개의 바둑알 중에서 가장 왼쪽에 있는 바둑알(연속된 다섯 개의 바둑알이 세로로 놓인 경우, 그 중 가장 위에 있는 것)의 가로줄 번호와, 세로줄 번호를 순서대로 출력한다.

따라서 가장 왼쪽, 위쪽 칸부터 그 칸에서부터 가로, 세로, 오른쪽 아래, 오른쪽 위 방향으로 같은 색의 돌이 몇개 있는지 세어서 5개 있으면 이긴 것으로 처리하면 된다.

여기서 같은 색의 돌이 6개 이상 있을 때 같은 색의 돌이 있는 칸을 그 방향에 대한 방문처리를 하지 않다면 앞에 있는 몇개의 돌을 제외하고 직선상으로 5개가 되는 상황을 이겼다고 처리할 수 있기 때문에 칸마다 특정 방향으로 탐색을 진행했는지 저장할 필요가 있다.

따라서 visit[k][i][j]를 i, j칸에서 k방향으로 탐색을 진행했는지로 정의한다. 여기서 k = 0이면 가로, 1이면 세로, 2면 오른쪽 아래, 3이면 오른쪽 위 방향이다. 그리고 가장 왼쪽에 있는 바둑알을 출력해야 하므로 i, j를 가장 왼쪽 칸부터 확인할 수 있도록 반복한다.

따라서 모든 칸에 대해 바둑 돌이 놓여져 있으면 모든 방향에 대해 탐색하지 않은 방향을 탐색해 같은 색의 돌이 몇개 있는지 센다. 정확히 5개 놓여져 있을 때만 이긴 것이므로 누가 이긴 것인지, 좌표가 어떻게 되는지를 저장한다.

모든 칸을 확인한 후에 누가 이긴 것인지 출력하고 이긴 사람이 있을 때만 좌표를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val map = Array(20){ IntArray(20) }
    val visit = Array(4){Array(20){ BooleanArray(20) }}
    for(i in 1..19){
        val st = StringTokenizer(br.readLine())
        for(j in 1..19){
            map[i][j] = st.nextToken().toInt()
        }
    }
    var answer = 0
    var y = 0
    var x = 0
    for(j in 1..19){
        for(i in 1..19){
            if(map[i][j] != 0){
                var count = 1
                if(!visit[0][i][j]){
                    visit[0][i][j] = true
                    var tx = j + 1
                    while(tx <= 19 && map[i][tx] == map[i][j]){
                        visit[0][i][tx] = true
                        count++
                        tx++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
                if(!visit[1][i][j]){
                    count = 1
                    var ty = i + 1
                    while(ty <= 19 && map[ty][j] == map[i][j]){
                        visit[1][ty][j] = true
                        count++
                        ty++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
                if(!visit[2][i][j]){
                    count = 1
                    var tx = j + 1
                    var ty = i + 1
                    while(tx <= 19 && ty <= 19 && map[ty][tx] == map[i][j]){
                        visit[2][ty][tx] = true
                        count++
                        tx++
                        ty++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
                if(!visit[3][i][j]){
                    count = 1
                    var tx = j + 1
                    var ty = i - 1
                    while(ty >= 1 && tx <= 19 && map[ty][tx] == map[i][j]){
                        visit[3][ty][tx] = true
                        count++
                        ty--
                        tx++
                    }
                    if(count == 5){
                        answer = map[i][j]
                        y = i
                        x = j
                    }
                }
            }
        }
    }
    println(answer)
    if(answer != 0){
        println("$y $x")
    }
}
```