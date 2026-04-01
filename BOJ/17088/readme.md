# 백준 17088번: 등차수열 변환

> 문제: https://www.acmicpc.net/problem/17088

### 문제 풀이

브루트 포스

arr = 수열 B

answer = 등차수열을 만들기 위한 최소 연산 횟수

op1 = 수열 B의 첫 번째 원소를 연산할 때 더하는 수

op2 = 수열 B의 두 번째 원소를 연산할 때 더하는 수

first = 연산 이후 첫 번째 원소

second = 연산 이후 두 번째 원소

diff = 등차수열의 공차

count = 해당 등차수열을 만들기 위한 연산 횟수

prev = 연산을 할 원소의 이전 원소

possible = 현재 공차로 등차 수열을 만들 수 있는지 여부

target = 현재 공차의 등차 수열일 때 현재 자리에 와야하는 수

change = 등차수열이 되게 하는 수와 현재 수의 차이

등차 수열은 인접한 두 원소의 차이가 모두 같은 수열임

따라서 첫 두 원소가 정해지면 세 번째 원소부터는 이전 원소에 첫 두 원소의 차이 만큼을 더해 구할 수 있음

수에 1을 더하거나 빼는 연산이 가능하고 연산을 하지 않는 경우도 있으므로 첫 두 원소는 각각 3가지 경우이므로 첫 두 원소를 구하는 방법은 9가지 방법이 있음

그렇게 정해놓고 다음 자리부터 등차 수열이 되려면 필요한 수 target과 B의 원소를 비교해 가능한지 여부를 possible에 저장함

이때 연산 횟수는 연산을 할 때마다 count를 1 증가시킴

모든 원소를 확인해봤을 때 가능하면 answer와 count 중 최솟값을 answer에 저장함

모든 경우의 수를 확인하면 answer가 Int.MAX_VALUE라면 등차수열을 만들지 못하는 것이므로 -1을 출력, 아니라면 answer가 등차수열을 만들기 위한 연산의 최소 횟수이므로 출력하면 정답

### 풀이 설명

각각의 수에 1을 더하거나 뺄 수 있는 수열 B가 주어졌을 때 등차 수열로 만들 수 있다면 필요한 연산 횟수의 최솟값을 구하는 문제이다.

수열 B의 길이 N이 1부터 100,000까지 이므로 수 하나마다 1을 더하거나 그대로 두거나 1을 빼거나 세가지 선택지가 있으므로 전체 경우의 수는 $3^{100,000}$이 되므로 전체 경우의 수를 모두 확인하기는 힘들다.

하지만 등차 수열의 경우 수열의 첫 두 숫자가 정해지면 그 두 숫자의 차이만큼 뒤의 숫자가 계속 증가해야 하므로 그 숫자를 연산을 통해 만들지 못한다면 그 등차 수열을 만들 수 없는 것이므로 가지치기가 가능하다.

그렇게 가지치기를 한다면 처음 두 원소를 정하는 9가지와 나머지 원소가 등차수열에 맞게 변할 수 있는지 확인하는 (N - 2)번의 단계가 필요하므로 대략 900,000 횟수로 줄어드므로 이 경우는 모든 경우를 확인할 수 있다.

이에 따라 수열 B를 배열 arr에 입력받고 만약 수열의 길이 N이 1이라면 등차 수열의 조건을 확인할 필요없이 바로 등차 수열이 되는 것이므로 바로 0을 출력하고 함수를 반환하면 된다.

그 외의 경우에는 숫자에 연산을 하거나 하지 않을때 더해지는 값이 -1에서 1 사이이므로 각각 첫 두 원소에 더해야 하는 값을 op1, op2로 반복시키며 값을 더한 첫 두 원소를 first, second에 저장한다.

그렇게 구한 두 원소의 차를 diff에 저장해 공차를 저장한다. 그 이후에 연산 횟수를 저장하기 위한 변수 count를 정의하고 첫 두 원소에 더하는 수 op1, op2가 각각 0이 아닐때 연산을 한 것이므로 count에 1씩을 더해준다.

그 이후 등차수열의 다음 수를 구하기 위해 이전 수를 prev로 정의하고 두 번째 원소인 second로 초기화한다. 등차수열이 연산을 통해 만들어질 수 있는지 여부를 possible 변수에 저장한다.

그 이후에 세 번째 원소부터 등차 수열이 이루어지려면 되어야 하는 수 target에 이전 원소 prev에 공차 diff를 더한 값을 저장한다.

그리고 그 target과 현재 원소 arr[idx]와의 차이를 change에 저장하고 이 값이 -1과 1사이의 값인지 확인한다. 사이의 값이라면 등차수열이 되도록 연산을 통해 만들 수 있는 것이므로 이 값이 0인지 확인해 아닐때만 연산을 한 것이므로 이때만 count를 1 증가시킨다.

만약 -1과 1사이가 아니라면 연산을 통해 등차수열을 만들 수 없는 것이므로 possible에 false를 저장하고 반복문을 탈출한다.

그 이후 반복문이 끝났을때 possible이 true라면 등차수열을 count회의 연산으로 만들 수 있는 것이므로 answer에 count와의 최솟값을 저장해준다.

그렇게 모든 경우에 대해 반복하면 answer에 Int.MAX_VALUE가 저장되어 있다면 등차 수열을 만들 수 없는 것이므로 -1을, 아니라면 등차수열을 만들기 위해 필요한 연산의 최소 횟수가 저장되어 있는 것이므로 answer에 저장된 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = IntArray(N)
    for(i in 0 until N){
        arr[i] = nextInt()
    }
    var answer = Int.MAX_VALUE
    if(N == 1){
        println(0)
        return@run
    }
    for(op1 in -1..1){
        for(op2 in -1..1){
            val first = arr[0] + op1
            val second = arr[1] + op2
            val diff = second - first

            var count = 0
            if(op1 != 0) count++
            if(op2 != 0) count++

            var prev = second
            var possible = true

            for(idx in 2 until N){
                val target = prev + diff
                val change = target - arr[idx]

                if (change in -1..1) {
                    if (change != 0) count++
                    prev = target
                } else {
                    possible = false
                    break
                }
            }
            if(possible) answer = minOf(answer, count)
        }
    }
    println(if(answer == Int.MAX_VALUE) -1 else answer)
}
```