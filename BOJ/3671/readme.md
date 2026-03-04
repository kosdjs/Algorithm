# 백준 3671번: 산업 스파이의 편지

> 문제: https://www.acmicpc.net/problem/3671

### 문제 풀이

브루트 포스

isPrime[i] = 숫자 i가 소수인지 여부

makeNum(cur, length) = 현재 숫자가 cur이고 뽑은 숫자의 갯수가 length일때 현재 숫자가 소수인지 확인하고 숫자를 더 뽑을 수 있으면 더 뽑아서 확인하는 함수

answer = 만들 수 있는 수에서 나오는 소수의 갯수

만들어지는 수가 최대 9,999,999이므로 소수인지 여부를 미리 구해놓으면 소수 판별이 빠름

에라토스테네스의 체를 이용해 소수 여부를 isPrime 배열에 저장함

주어진 종이에서 수를 만들어야 하므로 현재 숫자가 소수인지 확인하며 숫자를 더 뽑아서 수를 만드는 함수 makeNum(cur, length)를 만듬

만든 함수를 이용해 소수가 될때마다 answer를 1 증가시키면 answer에 소수의 갯수가 저장되므로 출력하면 정답

### 풀이 설명

종이 조각에 적혀있는 숫자를 몇개 뽑아 배열한 숫자가 소수가 되는 수가 몇개 있는지 구하는 문제이다.

숫자가 적혀있는 종이 조각은 최대 7개이므로 7자리까지 소수인지 여부를 미리 구해놓으면 뽑은 숫자가 소수인지 판단하는데 드는 시간을 줄일 수 있다. 에라토스테네스의 체를 이용해 최대 7자리 숫자인 9,999,999까지의 소수 여부를 isPrime 배열에 저장한다.

이제 종이 조각에 적혀잇는 숫자를 뽑아 숫자를 만드는 방법을 생각해야 하는데 숫자를 뽑을때마다 뒤에 붙이는 것이므로 어떤 숫자가 있는지 센 이후에 남은 숫자 중 하나의 숫자를 뽑아서 뒤에 붙이는 방식을 생각할 수 있다.

이 방식을 구현하기 위해 남은 숫자를 numCount 배열에 저장하고 현재 숫자가 cur이고 뽑은 숫자의 갯수가 length일때 현재 숫자가 소수인지 확인하고 숫자를 더 뽑을 수 있으면 숫자를 뽑는 makeNum(cur, length) 함수를 만든다.

그 이후에 첫자리에 숫자가 0이 되면 의미가 없기 때문에 numCount 배열을 참고해 0을 제외한 1에서 9까지의 숫자 중 뽑을 수 있는 숫자를 뽑아 makeNum(cur, length) 함수에 넘겨 현재 숫자가 첫자리가 되는 숫자를 모두 만들어보며 소수가 될때마다 만들어지는 소수의 갯수를 저장하는 변수 answer를 1씩 증가시킨다.

그러면 주어진 종이로 만들 수 있는 모든 수에 대해 소수의 갯수가 answer에 저장되므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import kotlin.math.sqrt

fun main() = System.`in`.bufferedReader().run {
    val c = readLine().toInt()
    val isPrime = BooleanArray(10000000){true}
    isPrime[0] = false
    isPrime[1] = false
    for(i in 2..sqrt(isPrime.size.toDouble()).toInt()){
        if(isPrime[i]){
            var idx = i * 2
            while(idx < isPrime.size){
                isPrime[idx] = false
                idx += i
            }
        }
    }
    val sb = StringBuilder()
    repeat(c){
        val s = readLine()
        val numCount = IntArray(10)
        for(i in 0 until s.length){
            numCount[s[i].code - '0'.code]++
        }
        var answer = 0
        fun makeNum(cur: Int, length: Int){
            if(isPrime[cur]) answer++
            if(length < s.length){
                for(i in 0..9){
                    if(numCount[i] > 0){
                        numCount[i]--
                        makeNum(cur * 10 + i, 1)
                        numCount[i]++
                    }
                }
            }
        }
        for(i in 1..9){
            if(numCount[i] > 0){
                numCount[i]--
                makeNum(i, 1)
                numCount[i]++
            }
        }
        sb.append(answer).append("\n")
    }
    print(sb)
}
```