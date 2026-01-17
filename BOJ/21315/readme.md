# 백준 21315번: 카드 섞기

> 문제: https://www.acmicpc.net/problem/21315

### 문제 풀이

구현, 브루트포스

target = 두 K에 따라 섞인 카드의 최종 결과

maxK = N에 따라 될 수 있는 K의 최댓값

cards = 섞이지 않은 카드의 처음 상태

first = 첫 번째 K에 따라 섞인 카드의 상태

second = 두 번째 K에 따라 섞인 카드의 상태

shuffle(K, cards, result) = K에 따라 cards의 상태에서 섞인 상태가 result에 저장되는 함수

카드를 섞는게 $2^K$개를 뒤에서 먼저 뽑은 나머지가 뒤로 간 이후 뽑은 카드들을 절반으로 나누면서 앞의 절반은 뒤로 보낸 카드들의 바로 앞으로 놓는 형태로 섞으므로 이를 반영해 shuffle(K, cards, result) 함수를 만듬

이 함수에 따라 첫 K인 K1을 1부터 maxK까지 반복하며 섞인 상태를 first에 저장함

그 뒤에 K2를 1부터 maxK까지 반복하며 first에서 섞인 상태를 second에 저장하고 target과 비교함

같으면 찾는 K1, K2를 찾은 것이므로 출력하고 함수 반환하면 정답

### 풀이 설명

카드를 섞는 과정을 다르게 표현해보면 $2^K$개의 카드를 뒤에서 뽑고 남은 카드들을 그대로 내려놓은 이후에 뽑은 카드를 계속 반으로 나누면서 앞의 카드들을 내려놓는다고 생각하면 된다.

따라서 맨 처음 뽑히지 않은 카드들이 맨 뒤로 가게 되고, 그 뒤에 절반씩 나누는 카드의 앞쪽 절반이 그 앞으로 계속 쌓이게 된다.

N이 1000 이하이고 $2^K$가 N보다 작아야 하므로 K는 최대 9정도 이므로 카드를 두번 섞는 모든 경우는 $K^2$정도가 된다.

따라서 모든 경우를 확인해도 시간 내에 풀 수 있는 문제이므로 모든 경우를 확인해 두 K를 찾아 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer
import kotlin.math.floor
import kotlin.math.log2

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val target = IntArray(N)
    for(i in 0 until N){
        target[i] = nextInt()
    }
    val maxK = floor(log2(N.toDouble())).toInt()
    val cards = IntArray(N){it + 1}
    fun shuffle(K: Int, cards: IntArray, result: IntArray){
        var cardsIdx = 0
        for(i in 1 shl K until result.size){
            result[i] = cards[cardsIdx++]
        }
        for(i in K - 1 downTo 0){
            var resultIdx = 1 shl i
            repeat((1 shl i)){
                result[resultIdx++] = cards[cardsIdx++]
            }
        }
        result[0] = cards.last()
    }
    val first = IntArray(N)
    val second = IntArray(N)
    for(K1 in 1..maxK){
        shuffle(K1, cards, first)
        for(K2 in 1..maxK){
            shuffle(K2, first, second)
            if(second.contentEquals(target)){
                println("$K1 $K2")
                return@run
            }
        }
    }
}
```