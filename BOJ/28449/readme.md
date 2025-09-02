# 백준 28449번: 누가 이길까

> 문제: https://www.acmicpc.net/problem/28449

### 문제 풀이

누적합

hi[i] = 코딩 실력이 i 보다 작거나 같은 HI 팀 인원수 누적합

arc = 현재 ARC 팀원의 코딩 실력

N - hi[arc] = 코딩 실력이 arc인 참가자를 이길 수 있는 HI 팀 인원수

hi[arc - 1] = 코딩 실력이 arc인 참가자에게 지는 HI 팀 인원수

hi[arc] - hi[arc - 1] = 코딩 실력이 arc인 HI 팀 인원수

모든 ARC 팀원들에 대해 이기는, 지는, 비기는 HI 팀 인원수를 다 더해서 출력하면 정답

### 풀이 설명

대결을 하면 더 높은 코딩실력을 가진 참가자가 승리하고, 두 참가자의 코딩실력이 같다면 무승부가 된다. 따라서 코딩실력을 기준으로 인원수를 누적합으로 저장해놓으면 현재 참가자에게 이기는, 지는, 비기는 인원수를 쉽게 구할 수 있다.

따라서 HI 팀의 참가자를 코딩실력을 기준으로 누적합으로 인원을 계산한다. 그러고 나면 현재 확인하는 ARC 팀 참가자의 코딩실력이 arc라고 하면 hi[arc]가 HI 팀의 코딩 실력이 arc보다 작거나 같은 인원수이기 때문에 전체 인원수 N에서 이 인원수를 빼면 HI 팀의 코딩 실력이 arc보다 큰 인원수이므로 현재 ARC 팀 참가자를 이기는 인원수가 된다.

코딩실력이 정수로 주어진다고 했으므로 hi[arc - 1]은 코딩실력이 arc보다 작은 HI 팀의 인원수가 되고 이는 현재 ARC 팀 참가자에게 지는 인원수가 된다. hi[arc]가 HI 팀의 코딩실력이 arc보다 작거나 같은 인원수라고 했으므로 여기서 코딩실력이 arc보다 작은 인원수 hi[arc - 1]을 빼주면 정확히 코딩실력이 arc인 인원수만 남는다. 즉, hi[arc] - hi[arc - 1]이 현재 ARC 팀 참가자와 비기는 HI 팀의 인원수가 된다.

따라서 모든 ARC 팀 참가자에 대해 N - hi[arc], hi[arc - 1], hi[arc] - hi[arc - 1]를 구해주면 각각 승리, 패배, 무승부 횟수가 되므로 이를 저장해서 출력하면 정답이 된다. 이때 N이 100,000 이하, M이 100,000 이하이므로 N * M이 Int 범위를 넘어서므로 승리, 패배, 무승부 횟수는 Long으로 지정해야 오버플로우가 나지 않는다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val hi = IntArray(100001)
    st = StringTokenizer(br.readLine())
    repeat(N){
        hi[st.nextToken().toInt()]++
    }
    for(i in 1..100000){
        hi[i] += hi[i-1]
    }
    var win = 0L
    var lose = 0L
    var draw = 0L
    st = StringTokenizer(br.readLine())
    for(i in 0 until M){
        val arc = st.nextToken().toInt()
        win += N - hi[arc]
        lose += hi[arc - 1]
        draw += hi[arc] - hi[arc - 1]
    }
    println("$win $lose $draw")
}
```