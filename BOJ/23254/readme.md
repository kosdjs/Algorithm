# 백준 23254번: 나는 기말고사형 인간이야

> 문제: https://www.acmicpc.net/problem/23254

### 문제 풀이

그리디

hour = 시험까지 공부할 수 있는 남은 시간

aArr[i] = i번 과목의 기본 점수 a

bArr[i] = i번 과목을 한 시간하면 올릴 수 있는 점수 b

count[i] = i점을 올릴 수 있는 실질적인 시간

totalScore = 받을 수 있는 총점

각 과목마다 a, b가 주어지면 (100 - a) / b 시간은 시간마다 b점을 올릴 수 있고 마지막 한 시간은 (100 - a) % b 점을 올릴 수 있으므로 count[b]를 (100 - a) / b만큼 증가시키고, count[(100 - a) % b]를 1 증가시킴

모든 과목에 대해 count 배열에 공부할 수 있는 시간과 점수를 저장하면서 a점은 기본으로 얻을 수 있는 점수이므로 totalScore에 a를 각각 더해줌

그 이후 시간 당 얻을 수 있는 큰 점수부터 시간을 쓸 것이므로 100부터 1까지 count 배열을 확인하며 공부할 수 있는 시간인 hour와 count[i]를 비교해 가능한 공부 시간을 study에 저장하고 이때 얻을 수 있는 점수 study * i를 totalScore에 더해주고 공부한 시간 study를 hour에서 빼줌

이를 hour가 0이 되거나 공부해서 얻을 수 있는 모든 점수를 확인할때까지 반복하면 totalScore에 얻을 수 있는 최대 총점이 저장되므로 이를 출력하면 정답

### 풀이 설명

공부를 할 수 있는 시간이 24 X N 시간이 있고, M개의 과목이 있을 때 과목마다 기본 점수 a점을 받을 수 있고, 한 시간 공부할때마다 점수를 b점 올릴 수 있을 때 공부를 해서 최대로 받을 수 있는 총 점을 구하는 문제이다.

즉, 한정된 시간을 써서 점수를 올리는 문제이므로 시간마다 올라가는 b점이 높은 과목을 공부하면 되지만 한 과목의 점수는 최대 100점이므로 실질적으로 시간마다 올릴 수 있는 점수를 계산해야 한다.

한 과목의 총점이 100점, 공부를 하지 않아도 받을 수 있는 점수가 a점이므로 이 과목을 공부해서 올릴 수 있는 점수는 100 - a점이 된다. 그리고 과목을 한 시간 공부할 때 올릴 수 있는 점수가 b점이므로 b점을 온전히 올릴 수 있는 시간은 올릴 수 있는 점수 100 - a를 b로 나눈 몫이 된다. 

그 이후에 동일한 과목을 한 시간 공부한다고 하면 방금 나눈 값의 나머지로 남은 점수만 올릴 수 있기 때문에 이때는 b점보다는 항상 작은 점수를 올릴 수 밖에 없어진다.

한 시간에 올릴 수 있는 점수의 범위가 1점부터 100점까지밖에 없으니 해당 점수를 올릴 수 있는 시간을 배열에 저장하면 실제로 특정 점수가 올라가는 시간을 효율적으로 저장할 수 있다.

이를 저장하는 배열을 count[i]라고 하고 값이 시간, i가 점수라고 하면 매 과목의 a, b마다 count[b]를 (100 - a) / b만큼 증가시키고 count[(100 - a) % b]를 1 증가시켜 해당 과목을 b점 증가시킬 수 있는 시간과, 100점을 만들기 위해 점수를 올릴 수 있는 한 시간을 반영할 수 있다.

받을 수 있는 총점을 totalScore 변수로 선언하고 과목마다 받을 수 있는 기본 점수 a점씩을 더해주면 된다. 그 이후 count 배열에 해당 점수를 올릴 수 있는 시간이 저장되어 있으므로 높은 점수부터 확인하며 시간 내에 올릴 수 있는 점수를 totalScore에 더해주면 된다.

그러면 모든 과목에 대해 받을 수 있는 최대 점수가 totalScore에 저장되므로 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    var hour = nextInt() * 24
    val M = nextInt()
    val aArr = IntArray(M)
    val bArr = IntArray(M)
    for(i in 0 until M){
        aArr[i] = nextInt()
    }
    for(i in 0 until M){
        bArr[i] = nextInt()
    }
    val count = IntArray(101)
    var totalScore = 0L

    for (i in 0 until M) {
        totalScore += aArr[i] // 기본 점수 합산
        val diff = 100 - aArr[i]

        // b만큼씩 몇 번 더할 수 있는지
        count[bArr[i]] += (diff / bArr[i])
        // b보다 작은 나머지 점수 처리
        if (diff % bArr[i] > 0) {
            count[diff % bArr[i]] += 1
        }
    }

    // 큰 상승분(100)부터 차례대로 공부 시간 할당
    for (i in 100 downTo 1) {
        if (hour <= 0) break
        val study = minOf(hour, count[i])
        totalScore += study * i
        hour -= study
    }

    println(totalScore)
}
```