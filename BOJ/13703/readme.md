# 백준 13703번: 물벼룩의 생존확률

> 문제: https://www.acmicpc.net/problem/13703

### 문제 풀이

DP

prev[i] = 이전 상태에 수면 i 센티미터에 있는 경우의 수

cur[i] = 현재 상태에 수면 i 센티미터에 있는 경우의 수

처음에 수면 k 센티미터 밑에 있고 1초마다 위, 아래로 1 센티미터 움직인다고 했으므로 prev[k]를 1로 초기화 함

현재 몇 초가 지났는지인 time을 1부터 n까지 반복하면서 물벼룩이 죽지 않으며 현재 초에서 가능한 수심 i를 1부터 k + time까지 확인하면서 현재 수심 i로 이동할 수 있는 경우의 수 prev[i - 1]과 prev[i + 1]을 cur[i]에 더해줌

현재 상태를 cur에 반영해놓으면 다음 초에서는 이 배열이 이전 상태가 되므로 prev와 cur를 서로 바꾸고 다음 반복을 시작할 때 cur를 0으로 초기화함

이를 n번 반복하면 살아있는 모든 경우의 수가 prev에 저장되므로 배열에 저장된 값을 더한 합을 출력하면 정답

### 풀이 설명

수면 k 센티미터 밑에 있는 물 벼룩은 1초마다 $\frac{1}{2}$ 확률로 위, 아래로 1 센티미터 이동한다. 물벼룩이 수면에 닿자마자 죽는다고 할 때, n초 뒤에 살아있을 확률 $\frac{S}{2^n}$의 S를 구하는 문제이다.

먼저 구해야 하는 S는 분모가 2의 n승이므로 1초마다 위, 아래로 움직이고 n초 지났을 때의 전체 경우의 수이므로, n초 지났을 때 물벼룩이 살아있는 경우의 수를 구하는 문제이다.

매 초마다 이전 위치에서 위, 아래로 1 센티미터 이동하고 수면에 닿자마자 죽기 때문에 1초 전 물 벼룩의 위치를 알고 있다면 현재 물벼룩의 위치를 알 수 있게 된다. 즉, 1초 전의 이전 상태로 현재 상태를 구할 수 있기 때문에 DP로 문제를 풀 수 있다.

그러므로 이전 상태에 수면 i 센티미터에 있는 경우의 수를 prev[i], 현재 상태에 수면 i 센티미터에 있는 경우를 cur[i]로 저장해 매 초마다 수면에 닿지 않도록 움직이는 경우의 수를 저장하면 된다.

먼저 처음 상태에서 k 센티미터 밑에 있다고 했으므로 prev[k]를 1로 초기화하고 시간 time을 1부터 n까지 반복하며 현재 확인해야 하는 수심 i를 1부터 k + time까지 확인하며 prev[i - 1]과 prev[i + 1]을 확인하며 각각 이전 수심 i - 1, i + 1에서 위 또는 아래로 움직이면 수심 i로 올 수 있으므로 각각의 경우의 수 만큼 cur[i]를 증가시킨다.

매번 반복할 때 cur에 저장된 경우의 수가 다음 초에는 이전 상태가 되기 때문에 마지막에 prev에 저장하고 반복을 시작할 때는 새로운 경우의 수를 확인해야 하므로 cur 배열을 초기화해야 한다.

그렇게 n번 반복하면 n초 후에 수면 밑에서 생존한 경우의 수가 prev 배열에 저장되어 있으므로 prev 배열에 저장된 값의 합을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run{
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val k = nextInt()
    val n = nextInt()
    if(k == 0){
        println(0)
    } else {
        var prev = LongArray(k + n + 1)
        var cur = LongArray(k + n + 1)
        prev[k] = 1
        for(time in 1..n){
            cur.fill(0)
            for(i in 1..k + time){
                if(i != n + k) cur[i] += prev[i + 1]
                cur[i] += prev[i - 1]
            }
            prev = cur.also { cur = prev }
        }
        println(prev.sum())
    }
}
```