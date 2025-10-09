# 백준 27210번: 신을 모시는 사당

> 문제: https://www.acmicpc.net/problem/27210

### 문제 풀이

DP, 누적합

$min[i] = i$에서 끝나는 최소 연속 부분합

$max[i] = i$에서 끝나는 최대 연속 부분합

$num =$ 현재 돌상의 방향이 왼쪽이면 $1$, 오른쪽이면 $-1$

$min[i] = min(num, min[i - 1] + num)$

$max[i] = max(num, max[i - 1] + num)$

$|min[i]|, max[i]$ 중 최댓값을 찾아서 출력하면 정답

### 풀이 설명

신을 모시는 사당에는 신을 조각한 돌상 N개가 일렬로 놓여 있다. 각 돌상은 왼쪽 또는 오른쪽을 바라보고 서있다. 창영이는 연속한 몇 개의 돌상에 금칠을 하여 궁극의 깨달음을 얻고자 한다.

궁극의 깨달음을 얻기 위해서는 가능한 한 많은 금색 돌상들이 같은 방향을 바라보아야 한다. 방향이 다른 돌상은 깨달음에 치명적이다. 깨달음의 양은 아래와 같이 정의된다.

| (왼쪽을 바라보는 금색 돌상의 개수) - (오른쪽을 바라보는 금색 돌상의 개수) |

즉, 일렬로 놓여있는 돌상들 중에서 연속된 부분의 돌상을 뽑았을 때 이 돌상들이 최대한 같은 방향을 보고 있도록 뽑는 문제이다.

이를 왼쪽을 바라보는 돌상을 1, 오른쪽을 바라보는 돌상을 -1로 바꿔서 생각해보면 연속된 부분이 최대한 같은 방향을 보고 있으려면 왼쪽을 바라보는 돌상이 많다면 양수, 오른쪽을 바라보는 돌상이 많다면 음수가 나올 것이다.

따라서 연속 부분 누적합을 구했을 때의 최댓값이 왼쪽을 바라보는 돌상의 개수가 더 많을 때, 최솟값이 오른쪽을 바라보는 돌상의 개수가 더 많을 때이고 이 둘 중 절댓값이 더 큰 값이 깨달음의 양이 된다.

이를 DP를 사용해서 구하기 위해 다음과 같이 정의하고 값을 구한다.

$min[i] = i$에서 끝나는 최소 연속 부분합

$max[i] = i$에서 끝나는 최대 연속 부분합

$num =$ 현재 돌상의 방향이 왼쪽이면 $1$, 오른쪽이면 $-1$

$min[i] = min(num, min[i - 1] + num)$

$max[i] = max(num, max[i - 1] + num)$

그러면 $min, max$ 배열의 각각 최솟값 최댓값이 저장되므로 이 때의 절댓값이 큰 값을 찾아서 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer
import kotlin.math.abs

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val min = IntArray(N)
    val max = IntArray(N)
    var answer = 0
    for(i in 0 until N){
        val num = if(st.nextToken().toInt() == 1) 1 else -1
        if(i == 0){
            min[i] = num
            max[i] = num
        } else{
            min[i] = minOf(num, min[i - 1] + num)
            max[i] = maxOf(num, max[i - 1] + num)
        }
        answer = maxOf(answer, abs(min[i]), max[i])
    }
    println(answer)
}
```