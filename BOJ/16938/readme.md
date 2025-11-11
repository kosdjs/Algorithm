# 백준 16938번: 캠프 준비

> 문제: https://www.acmicpc.net/problem/16938

### 문제 풀이

백트래킹

sum = 현재 고른 문제들의 난이도 합

min = 현재 고른 문제들 중 난이도가 가장 낮은 문제의 난이도

max = 현재 고른 문제들 중 난이도가 가장 높은 문제의 난이도

idx = 마지막으로 고른 문제의 인덱스

answer = 조건에 맞는 문제를 고르는 방법의 수

재귀 함수를 이용해 문제를 고르는 모든 방법을 탐색하며 문제 조건에 맞는 방법인지 확인하는 방법

sum이 R보다 크다면 문제를 더 고를 필요가 없으므로 가지를 쳐서 함수를 끝냄

그게 아니라면 sum이 L보다 크거나 같고 max - min이 X보다 크거나 같은지 확인해 맞다면 answer를 증가시켜 조건에 맞도록 문제를 고르는 방법을 찾았다는 것을 나타냄

idx + 1부터 N - 1까지 문제의 인덱스를 살펴보며 현재까지 고르지 않았던 문제를 골라서 그때의 sum, min, max, idx를 최신화해 함수를 다시 호출함

i를 0부터 N - 1까지 반복하면서 solve(A[i], A[i], A[i], i)를 호출하면 모든 문제를 고르는 방법을 확인해 answer에 조건에 맞게 문제를 고르는 방법의 수가 저장되므로 answer를 출력하면 정답

### 풀이 설명

문제를 N개 가지고 있고, 모든 문제의 난이도를 정수로 수치화했다. i번째 문제의 난이도는 Ai이다. 문제는 두 문제 이상이어야 한다. 문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야 한다. 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 한다.

N이 15 이하이므로 문제를 고르는 조합을 확인하기 위해 재귀 함수를 이용한다. 정확히 어떤 문제를 골랐는지 구하는 것이 아니므로 필요한 정보인 난이도의 합 sum, 가장 쉬운 난이도 min, 가장 어려운 난이도 max , 조합을 구하기 위한 마지막 문제의 번호 i를 인수로 사용해 조건에 맞을 때 방법의 수 answer를 증가시킨다.

또한 문제 난이도의 합은 문제의 난이도가 양수이므로 문제를 하나 더 고를때마다 증가한다. 그러므로 문제 난이도의 합 sum이 R보다 크다면 문제를 더 고를 필요 없이 탐색을 중단하면 된다.

문제를 2개 이상 골라야 하고, 문제 난이도의 합이 L보다 크거나 같아야 하고, 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 하므로 sum이 L보다 크거나 같은지 비교하고 max - min이 X보다 크거나 같은지 확인한다.

문제를 하나 이상 고르는 조합만 확인하므로 문제가 하나일 때는 max - min이 항상 0이므로 이 경우엔 조건에 들어가지 않는다. 문제를 고르는 조합을 확인해야 하므로 가장 마지막에 고른 문제의 인덱스인 idx보다 번호가 큰 문제들만 고르기 위해 i를 idx + 1부터 N - 1까지 반복하며 i번 문제를 골랐을 때의 난이도 합 sum + A[i], min과 A[i] 중 낮은 난이도, max와 A[i] 중 높은 난이도, i번 인덱스를 인수로 함수를 다시 호출한다.

그러면 문제를 하나 이상 고르는 모든 경우를 탐색할 수 있으므로 조건에 맞는 방법의 수가 answer에 저장된다. 따라서 answer를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

var N = 0
var L = 0
var R = 0
var X = 0
var answer = 0
lateinit var A: IntArray

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    L = st.nextToken().toInt()
    R = st.nextToken().toInt()
    X = st.nextToken().toInt()
    A = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        A[i] = st.nextToken().toInt()
    }
    for(i in 0 until N){
        solve(A[i], A[i], A[i], i)
    }
    println(answer)
}

fun solve(sum: Int, min:Int, max: Int, idx: Int){
    if(sum > R){
        return
    } else {
        if(sum >= L && max - min >= X){
            answer++
        }
        for(i in idx + 1 until N){
            solve(sum + A[i], minOf(min, A[i]), maxOf(max, A[i]), i)
        }
    }
}
```