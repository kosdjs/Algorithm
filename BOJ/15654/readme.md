# 백준 15654번 N과 M (5)

> 문제: https://www.acmicpc.net/problem/15654

### 문제 풀이

재귀, 비트마스킹

arr = N개의 자연수를 오름차순으로 정렬한 배열

chosen = N개의 자연수 중 M개를 고른 배열

bits = 현재 arr에서 뽑은 수의 인덱스를 비트마스킹으로 표현한 값

arr에서 bits를 이용해 현재 뽑힌 수를 바로 파악하고 뽑히지 않은 값을 뽑는 함수 solve를 이용해 N개의 자연수를 M개 뽑는 순열의 모든 경우를 출력하면 정답

### 풀이 설명

N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오. N개의 자연수는 모두 다른 수이다. 수열은 사전 순으로 증가하는 순서로 출력해야 한다.

- N개의 자연수 중에서 M개를 고른 수열

N개의 자연수 중 M개를 고른 수열은 순서가 다르면 다른 수열로 친다. 즉, N개에서 M개를 순열로 뽑는 경우의 수를 모두 출력하면 된다.

따라서 재귀함수를 이용해 순열을 뽑으면 되는데 이때 수열을 사전 순으로 증가하는 순서로 출력해야 하므로 먼저 N개의 자연수를 오름차순으로 정렬해야 한다. 따라서 배열 arr에 자연수를 저장하고 오름차순으로 정렬해준다.

그 후에는 출력하기 위해 뽑은 수를 저장하는 배열 chosen을 정의하고 순열의 조건에 맞게 이미 뽑힌 수를 제외한 모든 수를 확인하기 위해 배열 arr에서의 인덱스를 비트 마스킹으로 나타낸 값 bits를 확인해 뽑혔는지 여부를 확인해준다.

오름차순으로 정렬된 배열 arr에서 앞에서부터 하나씩 이미 뽑히지 않았던 수를 하나씩 재귀함수를 이용해서 뽑아 M개 뽑혔을 때 뽑힌 수들을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt():Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = nextInt()
    }
    arr.sort()
    val bw = System.out.bufferedWriter()
    val chosen = IntArray(M)
    fun solve(level: Int, bits: Int){
        if(level == M){
            for(i in 0 until M){
                bw.append("${chosen[i]} ")
            }
            bw.newLine()
            return
        }
        for(i in 0 until N){
            val bit = 1 shl i
            if(bit and bits != 0) continue
            chosen[level] = arr[i]
            solve(level + 1, bits or bit)
        }
    }
    solve(0, 0)
    bw.flush()
    bw.close()
}
```