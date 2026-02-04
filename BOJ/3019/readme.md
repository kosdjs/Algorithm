# 백준 3019번: 테트리스

> 문제: https://www.acmicpc.net/problem/3019

### 문제 풀이

구현, 브루트 포스

answer = 조건에 맞도록 블록을 놓을 수 있는 경우의 수

map[i] = i 칸의 높이 (i는 0부터 C - 1까지)

블록이 떨어졌을 때 바닥과 블록 사이에 공간이 남지 않으려면 블록을 놓으려는 칸들의 높이가 정확히 블록의 바닥면 모양만큼 높이 차이가 나야 함

예를 들어 3번 블록이 세워져 있을 때 왼쪽 칸을 i, 오른쪽 칸을 i + 1이라고 하면 왼쪽 칸의 바닥면이 오른쪽 칸의 바닥면보다 한 칸 높기 때문에 map[i]가 map[i + 1]보다 커야 놓을 수 있음

이런 식으로 입력된 블록의 모양 P에 따라 모든 C 칸에 블록을 놓을 수 있는 모든 조건을 확인해 가능할 때마다 answer를 1씩 증가시킴

그러면 answer에 놓을 수 있는 모든 경우의 수가 저장되므로 출력하면 정답

### 풀이 설명

블록을 놓을 때 바닥과 블록 사이에 공간이 남지 않도록 놓을 수 있는 방법의 수가 몇 개인지 구하는 문제이다.

이 때 주어지는 바닥의 열 C가 100 이하이고, 블록 하나가 회전되는 것에 따라 최대 4가지 모양이 되기 때문에 모든 경우를 살펴보아도 시행 횟수가 크지 않기 때문에 모든 경우의 수를 확인해도 된다.

입력으로 각 칸의 높이가 주어지고 블록의 종류는 정해져 있으므로 맨 앞 칸부터 블록의 너비만큼 높이를 확인해서 현재 블록을 떨어트렸을 때 남는 공간이 없는지 확인하면 된다.

예를 들어 1번 블록은 회전할 수 있는 경우의 수가 가로로 4칸, 세로로 4칸인 경우인 두가지 경우만 있으므로 세로로 4칸인 경우엔 모든 칸에 놓을 수 있고 가로로 4칸인 경우엔 가로로 높이가 같은 4칸이 있을 때만 놓을 수 있다.

이처럼 다른 블록도 회전할 수 있는 경우마다 어떤 높이에 놓을 수 있는지 확인해 모든 칸마다 확인해서 가능한 경우를 저장하면 된다. 이를 저장하기 위해 answer 변수를 정의하고 가능한 경우마다 answer를 1씩 증가시키면 된다.

이에 따라 입력받은 P에 따라 모든 칸에 놓을 수 있는 조건을 확인하면 answer에 놓을 수 있는 경우의 수가 저장되므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val C = nextInt()
    val P = nextInt()
    var answer = 0
    val map = IntArray(C)
    for(i in 0 until C){
        map[i] = nextInt()
    }
    when(P){
        1 -> {
            answer += C
            for(i in 0 until C - 3){
                if(map[i] == map[i + 1] && map[i + 1] == map[i + 2] && map[i + 2] == map[i + 3]) answer++
            }
        }
        2 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1]) answer++
            }
        }
        3 -> {
            for(i in 0 until C - 1){
                if(map[i] - 1 == map[i + 1]) answer++
                if(i < C - 2 && map[i] == map[i + 1] && map[i + 1] == map[i + 2] - 1) answer++
            }
        }
        4 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1] - 1) answer++
                if(i < C - 2 && map[i] - 1 == map[i + 1] && map[i + 1] == map[i + 2]) answer++
            }
        }
        5 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1] - 1) answer++
                if(map[i] - 1 == map[i + 1]) answer++
                if(i < C - 2){
                    if(map[i] - 1 == map[i + 1] && map[i + 1] == map[i + 2] - 1) answer++
                    if(map[i] == map[i + 1] && map[i + 1] == map[i + 2]) answer++
                }
            }
        }
        6 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1]) answer++
                if(map[i] - 2 == map[i + 1]) answer++
                if(i < C - 2){
                    if(map[i] == map[i + 1] && map[i + 1] == map[i + 2]) answer++
                    if(map[i] == map[i + 1] - 1 && map[i + 1] == map[i + 2]) answer++
                }
            }
        }
        7 -> {
            for(i in 0 until C - 1){
                if(map[i] == map[i + 1]) answer++
                if(map[i] == map[i + 1] - 2) answer++
                if(i < C - 2){
                    if(map[i] == map[i + 1] && map[i + 1] == map[i + 2]) answer++
                    if(map[i] == map[i + 1] && map[i + 1] - 1 == map[i + 2]) answer++
                }
            }
        }
    }
    println(answer)
}
```