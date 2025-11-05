# 백준 18428번: 감시 피하기

> 문제: https://www.acmicpc.net/problem/18428

### 문제 풀이

브루트 포스

wall(i, j, level) = 현재 좌표 (i, j) 전까지 level 개의 장애물을 놓았을 때

teachers = 선생님들의 좌표

check(i, j) = 좌표 (i, j)에서 학생을 볼 수 있는지 여부

wall(0, 0, 0)을 실행하면 반복문에 따라 0, 0부터 장애물을 하나씩 놓으면서 재귀 함수 호출

이 때 level이 3이 되면 장애물 3개를 놓은 것이므로 선생님들이 학생을 볼 수 있는지 확인하기 위해 모든 선생님들의 좌표에 맞게 check 함수를 호출함

모든 선생님들이 학생을 볼 수 없으면 answer에 true를 저장해 장애물 3개로 모든 학생을 가릴 수 있다는 것을 표시함

따라서 answer에 따라 YES, NO를 출력하면 정답

### 풀이 설명

NxN 크기의 복도가 있다. 복도는 1x1 크기의 칸으로 나누어지며, 특정한 위치에는 선생님, 학생, 혹은 장애물이 위치할 수 있다. 현재 몇 명의 학생들은 수업시간에 몰래 복도로 빠져나왔는데, 복도로 빠져나온 학생들은 선생님의 감시에 들키지 않는 것이 목표이다.

각 선생님들은 자신의 위치에서 상, 하, 좌, 우 4가지 방향으로 감시를 진행한다. 단, 복도에 장애물이 위치한 경우, 선생님은 장애물 뒤편에 숨어 있는 학생들은 볼 수 없다. 또한 선생님은 상, 하, 좌, 우 4가지 방향에 대하여, 아무리 멀리 있더라도 장애물로 막히기 전까지의 학생들은 모두 볼 수 있다고 가정하자.

학생들은 복도의 빈 칸 중에서 장애물을 설치할 위치를 골라, 정확히 3개의 장애물을 설치해야 한다. 결과적으로 3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지 계산하고자 한다. NxN 크기의 복도에서 학생 및 선생님의 위치 정보가 주어졌을 때, 장애물을 정확히 3개 설치하여 모든 학생들이 선생님들의 감시를 피하도록 할 수 있는지 출력하는 프로그램을 작성하시오.

N이 최대 6이기 때문에 장애물을 3개 놓는 모든 경우를 완전 탐색할 수 있다. 따라서 모든 빈칸에 대해 장애물을 3개 놓는 조합을 확인하기 위해 wall(i, j, level) 재귀 함수를 정의한다.

이 함수는 i, j부터 모든 빈 좌표에 대해 장애물을 하나씩 놓으며 재귀 함수로 호출을 하고 장애물을 놓을 때마다 level을 증가시키고 level이 3일 때 모든 선생님들에 대해 학생을 볼 수 있는지 확인하고 함수를 끝내기 때문에 장애물 3개를 놓는 모든 조합을 탐색할 수 있다.

따라서 wall(0, 0, 0)을 호출하면 장애물 3개를 놓는 모든 조합에 대해 선생님들이 학생을 볼 수 있는지 확인할 수 있고 이때 모든 선생이 학생을 볼 수 없는 조합이 있다면 answer에 true를 저장해 모든 학생들이 선생님들의 감시를 피할 수 있는지 여부를 저장한다.

answer에 따라 YES, NO를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

var N = 0
lateinit var map: Array<IntArray>
var teachers = ArrayList<IntArray>()
var answer = false

fun main(){
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    map = Array(N){IntArray(N)}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until N){
            val c = st.nextToken()
            if(c == "T"){
                teachers.add(intArrayOf(i, j))
            }
            map[i][j] = if(c == "X") 0 else if(c == "S") 1 else 2
        }
    }
    wall(0, 0, 0)
    println(if(answer) "YES" else "NO")
}

fun wall(i: Int, j: Int, level: Int){
    if(level == 3){
        var find = false
        for((y, x) in teachers){
            if(check(y, x)){
                find = true
            }
        }
        if(!find){
            answer = true
        }
        return
    }
    for(x in j until N){
        if(map[i][x] == 0){
            map[i][x] = 3
            wall(i, x, level + 1)
            map[i][x] = 0
        }
    }
    for(y in i + 1 until N){
        for(x in 0 until N){
            if(map[y][x] == 0){
                map[y][x] = 3
                wall(y, x, level + 1)
                map[y][x] = 0
            }
        }
    }
}

fun check(i: Int, j: Int): Boolean{
    var y = i
    var x = j
    var find = false
    while(y > 0 && !find){
        y--
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    y = i
    while(x > 0 && !find){
        x--
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    x = j
    while(y < N - 1 && !find){
        y++
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    y = i
    while(x < N - 1 && !find){
        x++
        if(map[y][x] == 1){
            find = true
        }
        if(map[y][x] == 3){
            break
        }
    }
    return find
}
```