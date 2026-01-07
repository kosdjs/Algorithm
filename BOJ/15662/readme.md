# 백준 15662번: 톱니바퀴 (2)

> 문제: https://www.acmicpc.net/problem/15662

### 문제 풀이

구현

cogwheels[i][j] = i번 톱니바퀴의 j번 극

topIdx[i] = 현재 i번 톱니바퀴의 몇 번째 인덱스가 12시 방향에 있는지

rotate(n, d) = n번 톱니바퀴를 d 방향으로 회전하는 함수 (d가 -1일 때 반시계, 1일 때 시계 방향)

count = 위쪽 극이 S극인 톱니바퀴의 개수

n번째 톱니바퀴를 돌린다고 생각하면 그 톱니바퀴의 왼쪽, 오른쪽 방향으로 계속 인접한 톱니바퀴가 회전해야 하는지 확인해야 함

n번째 톱니바퀴부터 인접한 왼쪽 톱니바퀴가 돌아가는지 확인하기 위해 맞닿은 극을 확인하기 위해 현재 톱니바퀴의 번호를 cur로 나타내고 n을 저장함

그 이후에 현재 톱니바퀴 cur의 왼쪽 극을 확인하기 위해 topIdx[cur]를 이용해 왼쪽 극을 구하고 다음 톱니바퀴 cur - 1의 오른쪽 극을 확인하기 위해 topIdx[cur - 1]을 이용해 오른쪽 극을 구해서 비교함

두 극이 같다면 cur - 1번 톱니바퀴가 회전하지 않게 되므로 cur - 2번 톱니바퀴는 확인할 필요가 없어지므로 반복문을 탈출, 다르다면 cur - 1번 톱니바퀴가 cur번 톱니바퀴의 반대 방향으로 회전해야 하므로 cur - 2번 톱니바퀴도 확인해야 하므로 cur를 1 감소시키고 다시 반복문으로 들어감

이런 방식으로 n번의 왼쪽에 있는 모든 톱니바퀴를 확인하고 그 반대로 cur와 cur + 1번을 비교하는 방식으로 n번의 오른쪽에 있는 모든 톱니바퀴들도 확인해 회전 방향을 direction 배열에 저장함

저장한 방향에 따라 rotate(n, d) 함수를 이용해 돌아가야 하는 모든 톱니바퀴를 돌려줌

이를 K번 반복하면 topIdx 배열에 현재 톱니바퀴의 위쪽 극이 어떤 인덱스인지 저장되어 있으므로 1부터 T까지의 i에 대해 cogwheels[i][topIdx[i]]를 확인해 위쪽 극이 S극이라면 count를 1 증가시켜 위쪽 극이 S극인 톱니바퀴 개수를 세고 이를 출력하면 정답

### 풀이 설명

[톱니바퀴](https://velog.io/@kosdjs/%EB%B0%B1%EC%A4%80-14891%EB%B2%88-%ED%86%B1%EB%8B%88%EB%B0%94%ED%80%B4)와 비슷한 문제지만 살짝 다른 부분이 있다.

톱니바퀴의 개수가 고정되어 있지 않고, 돌리는 톱니바퀴와 극이 다를때만 돌아가는 부분이 다르기 때문에 이 부분만 조심해서 구현하면 된다.

T개의 톱니바퀴를 cogwheels[i][j]에 i번 번호에 맞게 저장을 한다. 그 이후에 K번 톱니바퀴를 돌리는 경우를 구현해야 한다.

다만 여기서 회전시키는 톱니바퀴와 맞닿은 톱니바퀴의 극이 다를때만 회전시켜야 하므로 돌리는 톱니바퀴의 왼쪽, 오른쪽 톱니바퀴를 확인해야 한다. 만약 왼쪽 톱니바퀴가 극이 달라서 돌아가게 된다면 그 왼쪽의 톱니바퀴의 극도 확인해야 하므로 먼저 돌려야하는 톱니바퀴의 왼쪽에 있는 톱니바퀴의 맞닿은 극을 쭉 확인해보며 극이 같아 돌아가지 않을때까지 톱니바퀴가 돌아가는 방향을 저장하고 마지막에 돌려야 한다.

따라서 현재 돌려야 하는 톱니바퀴의 번호를 n이라고 하면 direction[n]에 돌려야 하는 방향을 미리 저장해놓고 현재 확실하게 돌아가는 톱니바퀴의 번호를 cur라고 하고 n을 대입한 이후에 그 왼쪽 톱니바퀴의 번호가 cur - 1이므로 cur, cur - 1번의 톱니바퀴가 맞닿은 극을 확인해 다르다면 돌려야 하는 것이므로 direction[cur - 1]에 direction[cur]에 저장된 방향과 반대로 저장을 해주고 더 왼쪽 톱니바퀴를 확인하기 위해 cur를 1 감소시키고 이 과정을 다시 반복한다.

그러다가 cur와 cur - 1의 극이 같거나 cur가 1이 되어서 더 이상 왼쪽 톱니바퀴가 없을때까지 톱니바퀴가 돌아가는지 확인하면 마찬가지로 cur에 n을 대입하고 cur와 cur + 1번 톱니바퀴를 cur가 T가 될때까지 확인해 돌리는 방향을 확인한다.

이렇게 확인한 방향이 direction 배열에 저장되어 있으므로 rotate(n, d) 함수를 이용해 톱니바퀴를 직접 돌리면 된다. 그렇게 K번 톱니바퀴를 돌리면 topIdx 배열에 현재 톱니바퀴들의 12시 방향의 극을 나타내는 인덱스가 저장되어 있으므로 모든 톱니바퀴에 대해 12시 방향이 S극인지 확인해 개수를 세어서 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val T = nextInt()
    val cogwheels = Array(T + 1){IntArray(8)}
    val topIdx = IntArray(T + 1)
    for(i in 1..T){
        var cogwheel = nextInt()
        for(j in 7 downTo 0){
            cogwheels[i][j] = cogwheel % 10
            cogwheel /= 10
        }
    }
    fun rotate(n: Int, d: Int){
        topIdx[n] += d * -1
        if(topIdx[n] < 0) topIdx[n] += 8
        topIdx[n] %= 8
    }
    val K = nextInt()
    repeat(K){
        val n = nextInt()
        val d = nextInt()
        val direction = IntArray(T + 1)
        direction[n] = d
        var cur = n
        while(cur > 1){
            val curLeftIdx = if(topIdx[cur] - 2 < 0) topIdx[cur] - 2 + 8 else topIdx[cur] - 2
            val nextRightIdx = (topIdx[cur - 1] + 2) % 8
            if(cogwheels[cur][curLeftIdx] == cogwheels[cur - 1][nextRightIdx]){
                break
            }
            direction[cur - 1] = direction[cur] * -1
            cur--
        }
        cur = n
        while(cur < T){
            val curRightIdx = (topIdx[cur] + 2) % 8
            val nextLeftIdx = if(topIdx[cur + 1] - 2 < 0) topIdx[cur + 1] - 2 + 8 else topIdx[cur + 1] - 2
            if(cogwheels[cur][curRightIdx] == cogwheels[cur + 1][nextLeftIdx]){
                break
            }
            direction[cur + 1] = direction[cur] * -1
            cur++
        }
        for(i in 1..T){
            rotate(i, direction[i])
        }
    }
    var count = 0
    for(i in 1..T){
        if(cogwheels[i][topIdx[i]] == 1) count++
    }
    println(count)
}
```