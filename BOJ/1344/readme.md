# 백준 1344번: 축구

> 문제: https://www.acmicpc.net/problem/1344

### 문제 풀이

수학

A = 한 구간에 A팀이 득점할 확률

B = 한 구간에 B팀이 득점할 확률

nonPrime = 득점이 가능한 점수 중 소수가 아닌 점수

nonPrimeA, nonPrimeB = A팀, B팀이 소수가 아닌 점수로 득점할 확률

A팀이 i점 득점할 확률은 $\binom{18}{i} \times A^i \times (1 - A)^{18 - i}$

적어도 한 팀이 골을 소수로 득점하는 경우는 전체 경우에서 두 팀이 모두 소수가 아닌 점수로 득점하는 경우를 뺀 경우임

이에 따라 nonPrimeA, nonPrimeB를 구하고 곱해서 1에서 빼서 출력하면 정답

### 풀이 설명

축구 경기는 90분동안 이루어지고, 분석을 쉽게하기 위해서 경기를 5분 간격으로 나눴다. 경기가 진행되는 동안 각 간격에서 A팀이 득점할 확률과 B팀이 득점할 확률이 주어진다. 그리고, 각 간격에서 두 팀은 각각 많아야 한 골을 득점할 수 있다.

경기가 끝난 후 적어도 한 팀이 골을 소수로 득점하려면 A팀, B팀이 모두 소수로 득점하거나, A팀만 소수로 득점하거나, B팀만 소수로 득점해야 함. 즉, 전체 경우에서 A팀, B팀이 모두 소수로 득점하지 않는 경우를 제외한 경우이다.

따라서 문제에서 요구하는 확률을 구하려면 A팀, B팀이 모두 소수로 득점하지 않는 확률을 구해 1에서 빼면 된다.

경기가 90분이고 구간이 5분 간격이기 때문에 한 경기는 18구간으로 이루어지게 된다. 따라서 i점을 득점한다고 하면 18개의 구간중 i개 구간에서 득점하는 것이고 이는 18개의 구간 중 i개의 구간을 뽑는 것과 같다. 즉 18개 중 i개를 뽑는 것이 되고, 이때 확률은 p가 구간에서 득점할 확률이라고 하면 i개 구간에서 득점하고 나머지 구간에서 득점을 하지 않아야 하기 때문에 $p^i \times (1 - p)^{18 - i}$이 된다.

즉, 한 팀이 한 구간에서 득점할 확률이 p일 때 i점을 득점할 확률은

$\binom{18}{i} \times p^i \times (1 - p)^{18 - i}$

가 된다.

한 구간에서 한 팀은 최대 한 골을 넣을 수 있기 때문에 경기가 18구간이므로 점수는 최대 18점이 될 수 있다. 또한 득점을 아예 하지 못할수도 있기 때문에 최저 점수는 0점이다. 이 점수 내에서 소수가 아니고 경기 내에 낼 수 있는 점수는 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18점이 있다. 따라서 이 점수를 낼 수 있는 모든 확률을 구해서 A, B팀이 둘다 소수로 점수를 내지 못하는 확률을 구해서 1에서 빼서 출력하면 정답이 된다.

### 소스 코드
```kotlin
import kotlin.math.pow

fun main(){
    val br = System.`in`.bufferedReader()
    val A = br.readLine().toInt() / 100.0
    val B = br.readLine().toInt() / 100.0
    val nonPrime = intArrayOf(0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18)
    var nonPrimeA = 0.0
    var nonPrimeB = 0.0
    for(i in nonPrime){
        nonPrimeA += comb(18, i) * A.pow(i) * (1 - A).pow(18 - i)
        nonPrimeB += comb(18, i) * B.pow(i) * (1 - B).pow(18 - i)
    }
    println((1 - (nonPrimeA * nonPrimeB)))
}

fun comb(n: Int, r: Int): Double{
    var result = 1.0
    var count = r
    if(r > n - r){
        count = n - r
    }
    for(i in 0 until count){
        result *= n - i
        result /= count - i
    }
    return result
}
```