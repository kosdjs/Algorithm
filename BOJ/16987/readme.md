# 백준 16987번: 계란으로 계란치기

> 문제: https://www.acmicpc.net/problem/16987

### 문제 풀이

브루트 포스

egg[i][0] = i번째 계란의 내구도

egg[i][1] = i번째 계란의 무게

solve(level) = level번째 계란을 들었을 때 어떤 계란을 칠지

몇 번째 계란인지 나타내는 것을 배열과 맞추기 위해 0번부터 N - 1번으로 생각하고 solve 함수가 가장 왼쪽인 0번부터 시작해 level이 N - 1번까지 실행 되어야 모든 계란을 들었던 것이므로 level이 N이 되었을때 깨진 계란의 개수를 세어 answer에 최댓값을 저장하고 출력하면 정답

### 풀이 설명

각 계란에는 내구도와 무게가 정해져있다. 계란으로 계란을 치게 되면 각 계란의 내구도는 상대 계란의 무게만큼 깎이게 된다. 그리고 내구도가 0 이하가 되는 순간 계란은 깨지게 된다. 일렬로 놓여있는 계란에 대해 왼쪽부터 차례로 들어서 한 번씩만 다른 계란을 쳐 최대한 많은 계란을 깨는 문제이다.

즉, 일렬로 놓여있는 계란들이 있을 때 가장 왼쪽에 있는 계란부터 하나씩 집어서 다른 계란을 한번만 친다면 계란을 최대 몇개 깰 수 있는지 구하는 문제다.

계란의 개수가 최대 8개이므로 완전 탐색으로 구할 수 있는 문제다. 따라서 재귀 함수를 이용해 완전 탐색을 한다.

solve 함수의 인덱스로 들어가는 level이 가장 왼쪽 계란을 0번째 계란이라고 했을때 몇 번째 계란을 들고있는지 나타내는 변수이고 이는 문제에서 계란을 한번 치면 다음 계란으로 넘어가기 때문에 함수가 한번 진행되면 1씩 증가하도록 했다.

이에 따라 가장 오른쪽 계란이 N - 1번째 계란이므로 level이 N이 될때 깨진 계란의 수를 세면 된다. 문제 조건에 맞게 level이 N이 아니라면 현재 level의 계란이 깨져있는지 확인하고 이미 깨져있다면 오른쪽의 계란으로 넘어간다.

깨져있지 않다면 현재 계란을 들어서 다른 계란을 칠 수 있다는 것이므로 가장 왼쪽의 계란부터 계란의 내구도를 확인해 깨져있지 않은 계란만 친다. 이 결과로 현재 들고 있는 계란과 내려친 계란의 내구도를 깎고 나서 다음 solve(level + 1)을 호출한다.

그 후에 다른 계란을 내려쳤을때의 경우도 확인해야 하기 때문에 깎아놨던 내구도를 계란의 무게만큼 다시 돌려놓는다.

이를 모든 경우의 수를 확인하면 answer에 모든 경우의 수에서 계란이 깨진 최대 개수를 저장해 놓은 것이므로 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

var N = 0
lateinit var egg: Array<IntArray>
var answer = 0

fun main(){
    val br = System.`in`.bufferedReader()
    N = br.readLine().toInt()
    egg = Array(N){IntArray(2)}
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        egg[i] = intArrayOf(st.nextToken().toInt(), st.nextToken().toInt())
    }
    solve(0)
    println(answer)
}

fun solve(level: Int){
    if(level == N){
        var sum = 0
        for(i in 0 until N){
            if(egg[i][0] <= 0){
                sum++
            }
        }
        answer = maxOf(answer, sum)
    } else {
        if(egg[level][0] <= 0){
            solve(level + 1)
        } else {
            var count = 0
            for(i in 0 until N){
                if(i != level && egg[i][0] > 0){
                    count++
                    egg[i][0] -= egg[level][1]
                    egg[level][0] -= egg[i][1]
                    solve(level + 1)
                    egg[i][0] += egg[level][1]
                    egg[level][0] += egg[i][1]
                }
            }
            if(count == 0){
                solve(level + 1)
            }
        }
    }
}
```