# 백준 32347번: 시간을 돌리고 싶어

> 문제: https://www.acmicpc.net/problem/32347

### 문제 풀이

이분 탐색

$power[i] = i$일 또는 이후의 날짜 중 타임머신에 전원이 공급되는 가장 빠른 날짜

$power$ 배열의 인덱스와 값이 같아지는 날이 타임머신 사용 가능한 날짜, 이 날짜를 이용해 $T$를 이분 탐색하며 타임머신을 $K$번 이하로 사용하고 $1$일로 갈 수 있는지 확인

이분 탐색으로 찾은 $T$를 출력하면 정답

### 풀이 설명

타임머신은 최대 $K$번 사용할 수 있고, 타임머신을 사용하면 $T$일 전으로 갈 수 있다. 단, 현재로부터 $T$일 전이 $1$일 이전이라면 $1$일로 가게 된다.  $i$일에 타임머신을 사용하기 위해서는 $i$일에 타임머신에 전원이 공급되고 있어야 하고, 타임머신을 타고 과거로 간 뒤, 과거에서 시간을 보내는 것이 가능하다.

이때 $1$일로 돌아가기 위한 $T$의 최솟값을 구하는 것은 $T$의 값에 따라 과거로 돌아갈 수 있는 날짜가 정해지므로 이 값이 줄어들면 과거로 짧게 이동할 수 밖에 없으므로 이동 횟수가 늘어나고, 늘어나면 이동 횟수가 짧아지기 때문에 $K$번 이하로 이동하는 최솟값을 이분 탐색으로 구할 수 있음.

문제 조건에서 타임머신에 전원이 공급되었던 날에만 타임머신을 이용할 수 있었다고 했으므로 타임머신을 이용해 과거로 돌아가면 전원이 공급되었던 날까지 기다려야 다시 타임머신을 이용할 수 있다. 따라서 현재 날짜에서 다음 타임머신 이용 가능한 날을 쉽게 구하기 위해 $power$배열에 다음 타임머신 이용 가능 날짜를 저장한다.

이에 따라 $T$를 이분 탐색하며 타임머신을 $K$번 이하로 이용해 $1$일로 갈 수 있는 최솟값을 구해 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val power = IntArray(N + 1)
    st = StringTokenizer(br.readLine())
    for(i in 1..N){
        power[i] = st.nextToken().toInt()
    }
    for(i in N downTo 0){
        if(power[i] == 1){
            power[i] = i
        } else {
            power[i] = power[i + 1]
        }
    }
    var left = 1
    var right = N
    while(left <= right){
        val mid = (left + right) / 2
        var day = N
        var count = 0
        while(day > 1){
            if(power[day] == day){
                day -= mid
                count++
            } else {
                day = power[day]
            }
            if(count > K){
                break
            }
        }
        if(count > K){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(left)
}
```