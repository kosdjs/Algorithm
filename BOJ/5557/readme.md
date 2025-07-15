# 백준 5557번: 1학년

> 문제: https://www.acmicpc.net/problem/5557

### 문제 풀이

DP

연산해야 하는 현재 값을 $n$이라고 하면

prev[i] = 현재 값을 연산하기 전까지 i를 만들 수 있는 가지수
curr[i] = 현재 값까지 연산해서 i를 만들 수 있는 가지수

$curr[i] = prev[j - n] + prev[k + n]$ $(j - n = i, k + n = i)$

마지막 수를 제외한 모든 수에 대한 prev, curr를 구하고 마지막에 curr[마지막 값]을 출력하면 정답

### 풀이 설명

문제에 따라 등식을 만든다고 하면 항상 등호가 마지막 숫자 앞에 오기 때문에 마지막 숫자는 결과값이 되고, 나머지 숫자를 이용해 마지막 숫자를 만들어야 한다.

문제에서 연속된 숫자 사이에 덧셈, 뺄셈 기호를 넣는다고 했으므로 식을 다시 마지막 숫자와 나머지로 나눈다면 앞의 나머지 숫자의 결과에서 마지막 숫자를 더하거나 빼는 경우의 수로 나뉜다.

즉, 앞서 계산한 결과에 마지막 숫자를 계산하는 것이 되므로 DP로 문제를 풀 수 있다.

여기서 문제의 조건이 계산 결과가 항상 0 이상 20 이하여야 했으므로, 문제를 풀기 위해 이전 숫자까지 계산으로 만들 수 있는 수의 가지수를 prev, 현재 숫자까지 계산으로 만들 수 있는 수의 가지수를 curr라고 하고 이때 두 배열의 인덱스는 계산의 결과값으로 prev[i]는 이전 숫자까지의 계산이 i가 되는 경우의 가지수를 뜻한다.

처음 숫자부터 마지막 직전까지 숫자를 모두 확인해 prev, curr 배열을 채우면 앞에 덧셈, 뺄셈으로 계산할 수 있는 가지수를 모두 확인하는 것이고 이 때 구한 curr 배열에서 마지막 값이 결과값으로 나오는 가지수를 구하면 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    var prev: LongArray
    var curr = LongArray(21){0}
    curr[st.nextToken().toInt()] = 1
    for(i in 2 until N){
        prev = curr
        curr = LongArray(21){0}
        val num = st.nextToken().toInt()
        for(j in 0..20){
            if (prev[j] == 0.toLong()) continue
            if(j - num >= 0){
                curr[j - num] += prev[j]
            }
            if(j + num <= 20){
                curr[j + num] += prev[j]
            }
        }
    }
    val result = st.nextToken().toInt()
    println(curr[result])
}
```