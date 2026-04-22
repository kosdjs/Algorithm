# 백준 28706번: 럭키 세븐

> 문제: https://www.acmicpc.net/problem/28706

### 문제 풀이

DP, 비트 마스킹

curBits = 현재 단계에서 7로 나눈 나머지가 가능한 상태를 비트마스킹한 값

nextBits = 다음 단계에 7로 나눈 나머지가 가능한 상태를 비트마스킹한 값

처음 K에서 턴마다 특정 값을 더하거나 곱하는 두 가지 선택지가 주어짐

매 턴마다 선택지 둘 중 하나를 선택해 그 선택지대로 값을 더하거나 곱해야 함

모든 턴 이후에 나온 값이 7로 나누어 떨어질 수 있는 선택지가 있는지 찾아야 함

7로 나누어 떨어지는 것은 7로 나눈 나머지가 0이어야 하고 7로 나눈 나머지를 매 턴마다 구해놓으면 다음 턴에 구해놓은 나머지에 값을 더하거나 곱하고 다시 7로 나눈 나머지를 구할 수 있음

7로 나눈 나머지는 0부터 6까지밖에 안되므로 매 턴마다 가능한 나머지를 비트 마스킹으로 구현하면 좋음

따라서 현재 가능한 나머지를 curBits, 다음 상태를 nextBits에 비트마스킹으로 저장함

매 턴마다 op1, v1, op2, v2에 어떤 연산을 하는지, 어떤 값을 연산하는지를 입력받으므로 curBits에 저장된 나머지를 하나씩 확인하며 op1, v1, op2, v2를 연산해보며 나오는 나머지를 nextBits에 저장함

그리고 계산한 nextBits가 현재 턴이 끝난 이후의 상태이므로 다음 턴을 계산하기 위해 curBits에 값을 저장함

이렇게 N턴을 계산하면 curBits에 저장된것이 N턴 이후에 가능한 나머지들을 비트마스킹 한 것이므로 $2^0 = 1$이 나머지 0을 뜻하는 값이므로 curBits와 1을 and 연산해 1이 나온다면 나누어 떨어지는 것이므로 LUCKY, 아니라면 UNLUCKY를 출력하면 정답

### 풀이 설명

처음 숫자 1에서 N개의 턴이 주어지고 턴마다 특정 값을 더하거나 곱하는 선택지가 두가지가 주어진다고 했을 때 매 턴마다 주어지는 선택지 중 하나를 정해서 계산한다고 하면 마지막에 연산한 결과가 7로 나누어 떨어질 수 있는지 구하는 문제이다.

이를 구하기 위해 매 턴마다 연산 이후에 7로 나누어 떨어지는 지 확인해야 하는데 현재 턴에 더하거나 곱해야 하는 값을 y, 이전 턴에 연산이 완료된 값을 x, 이 x를 7로 나눈 몫을 a, 나머지를 b라고 하면 y를 더한 x + y는 7로 나눈 몫과 나머지로 표현하면 7a + b + y가 된다.

이에 따라 x + y를 7로 나눈 나머지는 7a가 나누어 떨어지므로 b + y를 7로 나눈 나머지가 된다. 또한 y를 곱한다고 하더라도 곱한 값을 계산하면 7ay + by가 되므로 결론적으로 이 값을 7로 나눈 나머지는 by를 7로 나눈 나머지가 된다.

따라서 7로 나눈 나머지에 값인 이전 상태에 매 턴마다 값을 더하거나 곱한 이후에 7로 나눈 나머지가 현재 상태가 되므로 이 문제를 DP로 풀 수 있다. 또한 상태가 7로 나눈 나머지이므로 값의 범위가 0부터 6까지이므로 이는 비트 마스킹으로 풀면 더 효율적으로 풀 수 있다.

따라서 현재 상태에서 가능한 7로 나눈 나머지를 curBits, 현재 턴이 지난 다음 단계에서 가능한 7로 나눈 나머지를 nextBits로 정의한다. 그리고 처음에 K가 1이라고 했으므로 curBits에 $2^1 = 2$를 저장한다.

그 이후에는 연산해야 하는 연산자와 값 op1, v1, op2, v2를 입력받을 때마다 nextBits를 0으로 초기화한 이후에 curBits에서 가능한 나머지 i마다 op1, v1, op2, v2를 연산한 이후에 7로 나눈 나머지를 nextBits에 or 연산으로 반영해준다.

계산이 끝나면 nextBits에 저장된 값이 현재 턴의 계산을 끝낸 이후에 가능한 7로 나눈 나머지이므로 curBits에 해당 값을 저장해준다.

이렇게 N턴을 계산한 이후에 curBits에 7로 나눈 나머지로 가능한 값이 비트마스킹으로 저장되어 있으므로 7로 나누어 떨어질때의 나머지 0일때 비트마스킹 $2^0 = 1$과 curBits를 and 연산해 1이 나온다면 LUCKY, 아니라면 UNLUCKY를 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val T = readLine().toInt()
    val sb = StringBuilder()
    for(t in 0 until T){
        val N = readLine().toInt()
        var curBits = 1 shl 1
        for(n in 0 until N){
            val s = readLine()
            val op1 = s[0]
            val v1 = s[2] - '0'
            val op2 = s[4]
            val v2 = s[6] -'0'
            var nextBits = 0
            for(i in 0 until 7){
                if(curBits and (1 shl i) != 0){
                    if(op1 == '+') nextBits = nextBits or (1 shl (i + v1) % 7)
                    else nextBits = nextBits or (1 shl (i * v1) % 7)
                    if(op2 == '+') nextBits = nextBits or (1 shl (i + v2) % 7)
                    else nextBits = nextBits or (1 shl (i * v2) % 7)
                }
            }
            curBits = nextBits
        }
        sb.append(if (curBits and 1 == 1) "LUCKY" else "UNLUCKY").append("\
")
    }
    print(sb)
}
```