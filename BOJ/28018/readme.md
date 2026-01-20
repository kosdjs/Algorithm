# 백준 28018번: 시간이 겹칠까?

> 문제: https://www.acmicpc.net/problem/28018

### 문제 풀이

누적합

sum[i] = 특정 시각 i에 대해 선택이 불가능한 좌석의 합

N명에 대해 S 시각부터 좌석을 사용하고 E 시각까지 좌석을 사용하므로 sum[S]를 1 증가시키고 sum[E + 1]을 1 감소시킴

그 이후에 반영을 하기 위해 i를 1부터 sum.size - 1까지 반복하며 sum[i]에 sum[i - 1]을 더해줌

그 이후에 Q개 만큼 time을 입력받으면 sum[time]이 특정 시각 time에 선택 불가능한 좌석의 합이므로 출력하면 정답

### 풀이 설명

좌석의 사용 시작, 끝 시간이 주어졌을 때 특정한 시각에 선택할 수 없는 좌석이 몇 개인지 구하는 문제이다. 선택할 수 없는 좌석을 구하려면 특정한 시각에 총 몇개의 좌석을 사용 중인지 구하면 된다. 그리고 좌석이 사용이 종료되는 시각에 곧바로 선택될 수 없다고 했으므로 종료 시각까지 좌석을 사용한다고 생각하면 편하다.

특정 시각에 좌석을 몇 개 사용 중인지는 누적합으로 구할 수 있다. 따라서 특정한 시각 i에 사용 중인 좌석의 합을 sum[i]로 정의하고 구하면 된다.

구할 때 매 시작 시간 S, 종료 시간 E에 대해 S 시각부터 좌석을 사용하고 E 시각까지 사용하는 것이므로 원래라면 sum[S]부터 sum[E]까지 모두 1을 더해주어야 한다.

하지만 이렇게 구하면 인원 수, 시간마다 반복을 해야하므로 시간 내에 구하지 못한다. 이를 조금 다르게 생각해보면 S에 좌석을 사용하기 시작하고 E + 1 시각부터 사용이 가능하도록 비우기 때문에 모든 인원마다 sum[S]를 1 증가시켜놓고 sum[E + 1]을 1 감소시켜놓은 후에 모든 sum[i]에 sum[i - 1]을 더해주어 시작 시간과 종료 시간을 반영해주면 된다.

그 이후에 입력되는 특정 시각 time에 대해 sum[time]이 사용 중이거나 사용 종료를 한 시각이라 선택 불가능한 좌석의 합이므로 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val sum = IntArray(1000002)
    repeat(N){
        val S = nextInt()
        val E = nextInt()
        sum[S]++
        sum[E + 1]--
    }
    for(i in 1 until sum.size){
        sum[i] += sum[i - 1]
    }
    val Q = nextInt()
    val sb = StringBuilder()
    repeat(Q){
        val time = nextInt()
        sb.append(sum[time]).append("\n")
    }
    print(sb)
}
```