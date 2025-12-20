# 백준 17144번: 미세먼지 안녕!

> 문제: https://www.acmicpc.net/problem/17144

### 문제 풀이

구현

purifyY = 공기청정기의 아랫칸의 y 좌표

purifyX = 공기청정기의 아랫칸의 x 좌표

map[i][j] = (i, j)칸의 상태

add[i][j] = (i, j)칸에 확산된 미세먼지의 양

spread() = 미세먼지가 확산되는 것을 구현한 함수

spreadAmount = 한 칸에 확산되어야 하는 미세먼지의 양

totalSpreadAmount = 현재 칸에서 확산되어 다른 칸으로 이동한 미세먼지의 양

purify() = 공기청정기가 작동하는 것을 구현한 함수

미세먼지가 확산되려면 미세먼지가 5 이상 있어야 함

map을 확인해 미세먼지가 5 이상 있는 칸에 대해 확산되는 미세먼지의 양 spreadAmount에 현재 미세먼지의 양 map[i][j]를 5로 나눈 몫을 저장함

상하좌우 칸을 확인해 칸이 존재하고 공기청정기가 아니라면 add에 확산되어야 하는 양인 spreadAmount를 더해주고 그만큼 칸에서 미세먼지가 확산되었으므로 totalSpreadAmount에 spreadAmount만큼을 더해줌

모든 칸을 확인하면 totalSpreadAmount에 총 확산된 미세먼지 양이 있으므로 그만큼을 map에서 빼줌

이를 모든 칸에 대해 실행하면 미세먼지 확산을 구현할 수 있음

공기청정기 작동은 윗 방향과 아랫 방향으로 나누어야 함

공기청정기를 작동하면 방향에 맞게 한칸씩 이동하는 것이고 공기청정기로 들어오는 칸에 있는 미세먼지는 정화되므로 공기청정기가 존재하는 칸에서 바람의 반대방향으로 한칸씩 이동하며 그 칸의 있는 값을 map에 저장하면 됨

그러나 첫 칸을 공기청정기가 존재하는 칸에서 시작하고 이 값이 -1인 것은 덮어씌워지면 안되므로 첫 칸인지 확인하는 변수 first를 true로 초기화하고 이 값이 true일 때는 이동만 하고 false를 대입함

이에 따라 마지막 칸에 도달하면 공기청정기가 존재하는 칸이고 공기청정기에서 나오는 바람은 미세먼지가 없는 바람이므로 공기청정기의 전 칸에 0을 대입해주어야 함

이에 따라 마지막 칸에 도달했는지인 변수 last를 false로 초기화하고 도달할 때 last에 true를 대입하고 전칸으로 이동해 map에 0을 대입해줌

이를 방향에 맞게 상단, 하단을 구현해주면 공기청정기의 작동을 구현할 수 있음

이렇게 구현한 미세먼지 확산과 공기청정기 작동을 T번 반복하고 map에 남은 미세먼지의 양을 count에 모두 더해 출력하면 정답

### 풀이 설명

공기청정기의 성능을 집을 크기가 R×C인 격자판으로 나타냈고, 1×1 크기의 칸으로 나눴다.

공기청정기는 항상 1번 열에 설치되어 있고, 크기는 두 행을 차지한다. 공기청정기가 설치되어 있지 않은 칸에는 미세먼지가 있고, (r, c)에 있는 미세먼지의 양은 Ar,c이다.

1초 동안 아래 적힌 일이 순서대로 일어난다.

1.미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
- (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
- 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
- 확산되는 양은 Ar,c/5이고 소수점은 버린다. 즉, ⌊Ar,c/5⌋이다.
- (r, c)에 남은 미세먼지의 양은 Ar,c - ⌊Ar,c/5⌋×(확산된 방향의 개수) 이다.

2.공기청정기가 작동한다.
- 공기청정기에서는 바람이 나온다.
- 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
- 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
- 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.

공기청정기의 바람은 다음과 같은 방향으로 순환한다.

![](https://velog.velcdn.com/images/kosdjs/post/020764cc-e58b-4a54-a9ff-c9b2a078e192/image.png)

문제의 조건대로 구현하면 되는 문제지만 조심해야 할 점들이 있다. 먼저 미세먼지가 확산되는 것은 동시에 일어나야 한다는 점이다. 이를 단순히 반복문에서 순서대로 처리를 하면 실제 확산되어야 하는 미세먼지 이외의 주변 칸에서 확산된 미세먼지가 추가되어서 확산이 잘못될 수 있다.

따라서 확산되는 미세먼지를 동시에 처리하기 위해 확산될 미세먼지를 별도로 관리해주어야 한다. 따라서 (i, j)칸에 확산될 미세먼지를 저장하기 위한 배열 add를 정의한다. 그 이후에 미세먼지가 확산되는 조건에 따라 map에서 미세먼지를 찾아 확산될 미세먼지를 add에 저장하고 그만큼을 다시 map에서 빼준다.

이렇게 확산을 구현하면 이제 공기청정기의 동작을 구현해야 하는데 위쪽, 아래쪽이 방향이 다르기 때문에 따로 구현을 해야 한다. 코드를 짤 때 공기청정기가 항상 1번 열에 존재한다는 조건을 빼먹었기 때문에 어느 칸에 있어도 공기청정기가 동작하는 것을 보장하게 짜놓았지만 이 코드보다 더 간단하게 짤 수 있다.

공기청정기가 항상 위, 아래 두칸을 차지한다고 했으므로 아랫칸의 공기청정기의 좌표를 purifyY, X에 저장한다. 그 이후에 공기청정기가 작동하면 방향에 맞게 한칸씩 이동한다고 했고, 공기청정기로 들어가는 칸은 없어지는 것이므로 바람의 방향의 반대 방향으로 한칸씩 땡겨온다고 생각하면 된다.

이에 따라 바람의 반대 방향으로 공기청정기가 존재하는 다음 칸부터 한칸씩 값을 가져오면 된다. 그렇게 한칸씩 값을 가져오다 보면 공기청정기가 존재하는 칸까지 순환하게 되는데 마지막 칸은 공기청정기에서 나오는 미세먼지가 없는 바람이므로 그 칸의 값을 0으로 만들어준다.

이렇게 구현한 미세먼지 확산과 공기청정기 작동을 T번 반복하고 map에 남은 미세먼지의 값을 모두 더해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val R = nextInt()
    val C = nextInt()
    val T = nextInt()
    val map = Array(R){IntArray(C)}
    val add = Array(R){IntArray(C){0} }
    var purifierY = 0 // 하단 좌표
    var purifierX = 0
    for(i in 0 until R){
        for(j in 0 until C){
            map[i][j] = nextInt()
            if(map[i][j] == -1){
                purifierY = i
                purifierX = j
            }
        }
    }
    fun spread(){
        val dy = intArrayOf(-1, 0, 1, 0)
        val dx = intArrayOf(0, -1, 0, 1)
        for(i in 0 until R){
            for(j in 0 until C){
                if(map[i][j] >= 5){
                    val spreadAmount = map[i][j] / 5
                    var totalSpreadAmount = 0
                    for(k in 0 until 4){
                        val ny = i + dy[k]
                        val nx = j + dx[k]
                        if(ny >= 0 && ny < R && nx >= 0 && nx < C && map[ny][nx] >= 0){
                            add[ny][nx] += spreadAmount
                            totalSpreadAmount += spreadAmount
                        }
                    }
                    map[i][j] -= totalSpreadAmount
                }
            }
        }
        for(i in 0 until R){
            for(j in 0 until C){
                if(add[i][j] != 0){
                    map[i][j] += add[i][j]
                    add[i][j] = 0
                }
            }
        }
    }
    fun purify(){
        //상단 반시계방향
        var y = purifierY - 1
        var x = purifierX
        var first = true // 공기청정기 칸은 덮어씌우면 안됨
        while(x > 0){
            if(first){
                first = false
                x--
                continue
            }
            map[y][x] = map[y][x - 1]
            x--
        }
        while(y > 0){
            if(first){
                first = false
                y--
                continue
            }
            map[y][x] = map[y - 1][x]
            y--
        }
        while(x < C - 1){
            map[y][x] = map[y][x + 1]
            x++
        }
        while(y < purifierY - 1) {
            map[y][x] = map[y + 1][x]
            y++
        }
        var last = false // 정화된 마지막 칸이 이미 나왔는지
        if(x == purifierX){
            last = true
            map[y + 1][x] = 0
        }
        while(x > purifierX){
            map[y][x] = map[y][x - 1]
            x--
        }
        if(!last){
            last = true
            map[y][x + 1] = 0
        }
        //하단 시계방향
        y = purifierY
        x = purifierX
        first = true // 공기청정기 칸은 덮어씌우면 안됨
        while(x > 0){
            if(first){
                first = false
                x--
                continue
            }
            map[y][x] = map[y][x - 1]
            x--
        }
        while(y < R - 1){
            if(first){
                first = false
                y++
                continue
            }
            map[y][x] = map[y + 1][x]
            y++
        }
        while(x < C - 1){
            map[y][x] = map[y][x + 1]
            x++
        }
        while(y > purifierY){
            map[y][x] = map[y - 1][x]
            y--
        }
        last = false // 정화된 마지막 칸이 이미 나왔는지
        if(x == purifierX){
            last = true
            map[y - 1][x] = 0
        }
        while(x > purifierX){
            map[y][x] = map[y][x - 1]
            x--
        }
        if(!last){
            last = true
            map[y][x + 1] = 0
        }
    }
    repeat(T){
        spread()
        purify()
    }
    var count = 0
    for(i in 0 until R){
        for(j in 0 until C){
            if(map[i][j] > 0){
                count += map[i][j]
            }
        }
    }
    println(count)
}
```