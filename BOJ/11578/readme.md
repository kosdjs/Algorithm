# 백준 11578번: 팀원 모집

> 문제: https://www.acmicpc.net/source/104263897

### 문제 풀이

브루트 포스, 비트 마스킹

solve = 모든 문제를 푸는 비트 마스킹

answer = 현재까지 발견한 모든 문제를 풀 수 있는 최소 인원

team[i] = i번 사람이 풀 수 있는 문제의 비트 마스킹

find(count, idx, mask) = 현재 count 명의 팀원을 뽑았고, idx번 사람부터 인원을 뽑을 수 있을 때 현재 팀원이 풀 수 있는 문제가 mask에 비트 마스킹 되어있으며 한 명씩 더 인원을 뽑아보며 문제를 뽑을 수 있고 현재 최소 인원보다 적을 동안만 사람을 뽑아보는 재귀 함수

문제, 사람이 최대 10까지이므로 모든 경우의 수 확인 가능함

문제는 풀거나 풀지 못하는 두가지 경우의 수만 있으며 사람마다 풀 수 있는 문제가 다르므로 이를 비트 마스킹으로 편하게 계산 가능

그래서 각자 풀 수 있는 문제를 team[i]에 비트 마스킹으로 저장함

팀원을 뽑는 모든 조합을 확인하기 위해 재귀함수 find(count, idx, mask)를 만들어 모든 조합을 확인하며 현재 풀 수 있는 문제 mask와 모든 문제를 푸는 경우의 solve와 같으면 최소 인원인지 answer와 count를 비교해 더 작은 값을 저장함

그러면 answer에 모든 문제를 풀 수 있는 최소 팀원의 수가 저장되므로 출력하면 정답

### 풀이 설명

N개의 문제와 각각 풀 수 있는 문제가 다른 M명의 사람이 있을 때 N개의 문제를 모두 풀 수 있도록 팀원을 뽑는 최소 인원을 구하는 문제이다.

N, M이 최대 10이기 때문에 팀원을 뽑는 모든 조합을 확인하면 팀원을 뽑는 모든 조합이 $2^{10}$가지 이므로 모든 조합이 10 문제를 풀 수 있는지 확인한다고 쳐도 확인할 가짓수가 대략 만가지 정도로 굉장히 적다.

따라서 모든 경우를 확인해도 풀 수 있는 문제이다. 여기서 뽑힌 팀원이 모든 문제를 풀 수 있는지 확인하는 방법을 생각해보면 뽑힌 팀원마다 풀 수 있는 문제가 다르므로 그 문제들을 비트 마스킹으로 나타내 or 연산을 통해 팀원들이 풀 수 있는 문제를 변수 하나로 압축할 수 있다.

또한 현재까지 최소 인원을 구해 놓는다고 하면 이 인원 수보다 많은 조합은 확인할 필요가 없으므로 조합을 재귀함수로 구하면서 가지치기를 하면 더 빠르게 구할 수 있다.

따라서 현재까지 최소 인원을 answer, 모든 문제를 푸는 비트 마스킹을 solve, 각 사람이 풀 수 있는 문제의 비트 마스킹을 team에 저장하고, 문제를 풀 팀원을 뽑는 조합을 만들며 최소 인원을 찾는 재귀 함수 find(count, idx, mask)를 만들어 문제를 풀 수 있다.

이에 따라 재귀 함수를 이용해 구한 최소 인원인 answer를 출력하면 정답이 되는데, 모든 문제를 풀 수 없는 경우가 있다고 했으므로 초깃값을 최대 인원수인 M + 1로 잡아놓고 저장된 값이 M + 1일 때만 -1을 출력하도록 하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    var solve = 0
    for(i in 1..N){
        solve = solve or (1 shl i)
    }
    val M = nextInt()
    var answer = M + 1
    val team = IntArray(M)
    for(i in 0 until M){
        val O = nextInt()
        var P = 0
        for(j in 0 until O){
            P = P or (1 shl nextInt())
        }
        team[i] = P
    }
    fun find(count: Int, idx: Int, mask: Int){
        if(count > answer) return
        if(mask == solve){
            answer = minOf(answer, count)
            return
        }
        for(i in idx until M){
            find(count + 1, i + 1, mask or team[i])
        }
    }
    find(0, 0, 0)
    println(if(answer == M + 1) -1 else answer)
}
```