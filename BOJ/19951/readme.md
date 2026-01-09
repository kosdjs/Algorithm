# 백준 19951번: 태상이의 훈련소 생활

> 문제: https://www.acmicpc.net/problem/19951

### 문제 풀이

누적합

ground[i] = 연병장의 i번 칸에 있는 흙의 양

sum[i] = i번 칸에 덮거나 파내야 할 흙의 양

모든 지시가 a번 칸부터 b번 칸까지의 범위이므로 범위가 달라서 합치기 어려우므로 1번 칸부터 a - 1칸까지 -k, 1번 칸부터 b번 칸까지 k로 나누어서 sum[a - 1]에 -k, sum[b]에 k를 각각 더해주어 저장함

이러면 sum[i]가 1번 칸부터 i번 칸까지 덮거나 파내야 하는 흙의 양의 누적합이 되므로 i번 칸에 덮거나 파내야 할 흙의 양을 제대로 구하기 위해 i를 N - 1부터 1까지의 순서로 sum[i]에 sum[i + 1]을 더해줌

그러면 sum[i]에 i번 칸에 덮거나 파내야할 흙의 양이 저장되어 있으므로 i를 1부터 N의 순서로 반복하며 ground[i]에 sum[i]를 더해주면 ground에 연병장의 각 칸의 최종 높이가 저장되므로 출력하면 정답

### 풀이 설명

1번부터 N번 칸까지 있고, 각 조교에게 a번 칸부터 b번 칸까지 k양의 흙을 덮거나 파내야 한다는 지시를 받는다. 매번 지시를 받을때마다 흙을 덮거나 파내면 덮어놓은 흙을 다시 파내는 일이 생길 수 있으므로 모든 조교의 지시를 모아 각 칸의 최종 높이를 구해 한 번에 일을 수행하려고 한다.

조교의 지시가 a번 칸부터 b번 칸까지의 범위이기 때문에 한번에 모아서 생각하기가 쉽지 않다. 그러므로 a번 칸부터 b번 칸까지 k양의 흙을 덮거나 파내는 것을 1번 칸부터 b칸 까지 k양의 흙을 덮거나 파내는 것, 1번 칸부터 a - 1번 칸까지 k양의 흙을 파내거나 덮는 것으로 나눠서 생각하면 1번 칸부터 특정 칸까지의 범위로 나타낼 수 있기 때문에 합치기 편해진다.

이에 따라 1번 칸부터 i번 칸까지 덮거나 파내야 할 흙의 양을 sum[i]로 정의하고 매 지시마다 sum[a - 1]에 -k를 저장하고, sum[b]에 k를 저장한다.

이렇게 저장하고 나면 현재는 sum[i]에 1번 칸부터 i번 칸까지 덮거나 파내야 할 흙의 양이 저장되어 있으므로 sum[i + 1]의 양 만큼 i번 칸에도 흙을 덮거나 파내야 한다는 뜻이 된다. 그러므로 i번 칸에 덮거나 파내야 할 흙의 양을 구하기 위해 i를 N - 1부터 1까지의 순서로 반복하며 sum[i]에 sum[i + 1]을 더해주어 i번 칸에 덮거나 파내야 할 흙의 양을 제대로 구해준다.

그 이후에 매 칸마다 sum에 저장된 값을 처음 연병장의 흙의 양 ground에 더해주면 모든 칸의 최종 높이를 구할 수 있으므로 이를 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val ground = IntArray(N + 1)
    for(i in 1..N){
        ground[i] = nextInt()
    }
    val sum = IntArray(N + 1)
    repeat(M){
        val a = nextInt()
        val b = nextInt()
        val k = nextInt()
        sum[b] += k
        sum[a - 1] -= k
    }
    for(i in N - 1 downTo 1){
        sum[i] += sum[i + 1]
    }
    val sb = StringBuilder()
    for(i in 1..N){
        ground[i] += sum[i]
        sb.append(ground[i]).append(" ")
    }
    print(sb)
}
```