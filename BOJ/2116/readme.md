# 백준 2116번: 주사위 쌓기

> 문제: https://www.acmicpc.net/problem/2116

### 문제 풀이

브루트 포스

dices[i][j] = i + 1번 주사위의 j면의 숫자 (j는 0 부터 5까지 A부터 F까지 대응)

answer = 옆면의 숫자의 합의 최댓값

solve(level, bottom, sum) = 현재 주사위의 인덱스 level, 바닥면의 인덱스 bottom, 이전 주사위까지의 옆면 숫자의 최댓값 합 sum

level이 n이라면 모든 주사위의 옆면 최댓값 합이 sum에 저장되어있으므로 이 값의 최댓값을 answer에 저장하면 됨

level이 n보다 작다면 아직 위에 쌓을 주사위가 남아있는 것이므로 현재 주사위의 윗면의 숫자와 같은 다음 주사위의 아랫면을 찾아 nextBottom에 저장하고 현재 주사위의 옆면 숫자의 최댓값을 찾아 max에 저장한 후에 solve(level + 1, nextBottom, sum + max)를 호출해 현재 주사위의 옆면 숫자의 최댓값을 반영해준다.

solve(0, 0, 0)부터 solve(0, 5, 0)까지 호출해 모든 경우를 탐색하면 answer에 옆면 숫자의 합의 최댓값이 저장되므로 이를 출력하면 정답

### 풀이 설명

주사위의 모양은 모두 크기가 같은 정육면체이며 각 면에는 1부터 6까지의 숫자가 하나씩 적혀있다. 그러나 보통 주사위처럼 마주 보는 면에 적혀진 숫자의 합이 반드시 7이 되는 것은 아니다.

주사위 쌓기 놀이는 아래에서부터 1번 주사위, 2번 주사위, 3번 주사위, … 의 순서로 쌓는 것이다. 쌓을 때 다음과 같은 규칙을 지켜야 한다: 서로 붙어 있는 두 개의 주사위에서 아래에 있는 주사위의 윗면에 적혀있는 숫자는 위에 있는 주사위의 아랫면에 적혀있는 숫자와 같아야 한다. 다시 말해서, 1번 주사위 윗면의 숫자는 2번 주사위 아랫면의 숫자와 같고, 2번 주사위 윗면의 숫자는 3번 주사위 아랫면의 숫자와 같아야 한다. 단, 1번 주사위는 마음대로 놓을 수 있다.

이렇게 쌓아 놓으면 긴 사각 기둥이 된다. 이 사각 기둥에는 4개의 긴 옆면이 있다. 이 4개의 옆면 중에서 어느 한 면의 숫자의 합이 최대가 되도록 주사위를 쌓고자 한다. 이렇게 하기 위하여 각 주사위를 위 아래를 고정한 채 옆으로 90도, 180도, 또는 270도 돌릴 수 있다. 한 옆면의 숫자의 합의 최댓값을 구하는 프로그램을 작성하시오.

1번 주사위에 따라 쌓이는 긴 사각 기둥이 결정되기 때문에 긴 사각 기둥 6개만 확인하면 된다. 따라서 재귀 함수를 이용해 현재 몇 번째 주사위인지, 바닥면이 무엇인지, 이전 주사위까지 옆면 최댓값의 합이 몇인지를 넘겨서 계산하면 된다.

이를 계산하기 위해 재귀 함수를 이용한다. 인수로 현재 확인하는 주사위의 인덱스 level, 현재 아랫면의 인덱스 bottom, 이전 주사위까지의 옆면 숫자의 최댓값 합 sum을 사용한다.

이 재귀함수 solve를 이용해서 첫 주사위부터 마지막 주사위까지 아랫면과 윗면이 맞게 되었을 때 옆면 숫자의 최댓값 합을 구할 수 있다. 모든 경우를 계산하기 위해 solve(0, 0, 0)부터 solve(0, 5, 0)까지 호출해 첫 주사위의 모든 면이 바닥면이었을 때를 계산해 준다.

그러면 구하는 옆면 숫자의 합의 최댓값이 answer에 저장되고 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

lateinit var dices: Array<IntArray>
val opposite = intArrayOf(5, 3, 4, 1, 2, 0)
var answer = 0
var n = 0

fun main(){
    val br = System.`in`.bufferedReader()
    n = br.readLine().toInt()
    dices = Array(n){ IntArray(6) }
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        for(j in 0 until 6){
            dices[i][j] = st.nextToken().toInt()
        }
    }
    for(i in 0 until 6){
        solve(0, i, 0)
    }
    println(answer)
}

fun solve(level: Int, bottom: Int, sum: Int){
    if(level == n){
        answer = maxOf(answer, sum)
        return
    }
    var max = 0
    var nextBottom = 0
    for(i in 0 until 6){
        if(level + 1 < n && dices[level + 1][i] == dices[level][opposite[bottom]]){
            nextBottom = i
        }
        if(i == bottom || i == opposite[bottom]){
            continue
        }
        max = maxOf(max, dices[level][i])
    }
    solve(level + 1, nextBottom, sum + max)
}
```