# 백준 3980번: 선발 명단

> 문제: https://www.acmicpc.net/problem/3980

### 문제 풀이

백트래킹

visit[i] = i번 선수가 뽑혔는지 여부

s[i][j] = i번 선수가 j 포지션에서 뛰었을 때의 능력치

solve(j, sum) = 11명을 뽑을 때까지 선수들을 포지션마다 뽑는 함수, j는 현재 뽑은 인원, sum은 현재까지 능력치의 합

answer = 11명을 포지션에 맞게 뽑았을 때의 능력치의 합 중 최댓값

모든 선수 i에 대해 visit[i]가 false, s[i][j]가 0이 아닌 선수는 현재 포지션에서 뛸 수 있는 것이므로 visit[i]를 true로 만들어 지금 뽑은 선수라는 것을 나타내고, 현재 선수를 포함한 뽑은 인원수 j + 1, 현재 선수까지의 능력치의 합 sum + s[i][j]를 다시 재귀함수에 넘김

이를 j가 11이 될때까지 반복하면 11명을 뽑았을 때의 능력치의 합이 sum이 되고 이를 포지션에 맞게 뽑을 수 있는 모든 경우를 확인하는 것이므로 이 sum의 최댓값을 answer에 저장하고 출력하면 정답

### 풀이 설명

챔피언스 리그 결승전을 앞두고 있는 맨체스터 유나이티드의 명장 퍼거슨 감독은 이번 경기에 4-4-2 다이아몬드 전술을 사용하려고 한다.

오늘 결승전에 뛸 선발 선수 11명은 미리 골라두었지만, 어떤 선수를 어느 포지션에 배치해야 할지 아직 결정하지 못했다.

수석코치 마이크 펠란은 11명의 선수가 각각의 포지션에서의 능력을 0부터 100까지의 정수로 수치화 했다. 0은 그 선수가 그 포지션에 적합하지 않다는 뜻이다.

이때, 모든 선수의 포지션을 정하는 프로그램을 작성하시오. 모든 포지션에 선수를 배치해야 하고, 각 선수는 능력치가 0인 포지션에 배치될 수 없다.

그러므로 11명을 각 포지션에 한 명씩 배치해보며 가능한 배치일 때 능력치의 합 중 최대 합을 구하면 되는 문제다.

여기서 각 선수들마다 능력치가 0이라면 그 포지션에 뛸 수 없는 것이고 이미 다른 포지션으로 뽑힌 선수는 다시 뽑히면 안되기 때문에 이미 뽑혔는지 여부를 저장해야 한다. 그러므로 뽑혔는지 여부를 visit 배열에 저장한다.

이제 모든 포지션에 대한 선수들을 뽑아야 하므로 재귀 함수를 이용해 선수들을 뽑는다. 이 때 인수로 들어가는 j는 현재 뽑은 선수의 수이고, sum은 현재 뽑은 선수들의 능력치의 합이다. j가 11이 되기 전까지 모든 선수들의 visit[i], s[i][j]를 확인해 이미 뽑힌 선수가 아니고 현재 포지션에서 뛸 수 있는 선수를 골라 뽑은 이후에 j + 1과 현재 선수의 능력치 s[i][j]를 인수로 다시 재귀 함수를 호출하면 된다.

이에 따라 재귀 함수에서 j가 11이 되면 모든 선수를 포지션에 맞게 뽑은 것이므로 이때의 sum 값 중 최댓값을 answer에 저장해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val C = nextInt()
    val sb = StringBuilder()
    repeat(C){
        val visit = BooleanArray(11)
        val s = Array(11){IntArray(11)}
        for(i in 0 until 11){
            for(j in 0 until 11){
                s[i][j] = nextInt()
            }
        }
        var answer = 0
        fun solve(j: Int, sum: Int){
            if(j == 11){
                answer = maxOf(answer, sum)
                return
            }
            for(i in 0 until 11){
                if(visit[i] || s[i][j] == 0) continue
                visit[i] = true
                solve(j + 1, sum + s[i][j])
                visit[i] = false
            }
        }
        solve(0, 0)
        sb.append(answer).append("\n")
    }
    print(sb)
}
```