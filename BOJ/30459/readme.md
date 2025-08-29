# 백준 30459번: 현수막 걸기

> 문제: https://www.acmicpc.net/problem/30459

### 문제 풀이

이분 탐색

밑변의 길이를 구하기 위해 말뚝의 위치 x를 오름차순으로 정렬 후 반복문으로 구한다.

밑변의 범위가 크지 않고 중복을 쉽게 방지하기 위해 BooleanArray의 인덱스를 밑변으로 만들어 가능한 밑변일 경우만 true를 저장

그 후 가능한 넓이를 구하기 위해 높이를 오름차순으로 정렬한 후 가능한 밑변에 대해 R보다 작거나 같은 넓이가 될 수 있는 최대 높이를 이분 탐색으로 구하고 이때 넓이 저장

이때 넓이 계산을 하기 쉽게 R에 미리 2배를 하고 밑변과 높이의 곱과 계산함

최종적으로 저장된 넓이가 있으면 출력, 없으면 -1 출력

### 풀이 설명

두 말뚝 사이의 공간이 현수막의 밑변이 되고, 깃대의 길이가 현수막의 높이가 되므로 가능한 밑변을 미리 구해놓으면 넓이 계산이 더 편해진다.

두 말뚝 사이의 공간은 말뚝의 위치가 더 높은 쪽에서 낮은 쪽의 위치를 빼면 나오기 때문에 이를 구하기 쉽게 말뚝 위치를 먼저 오름차순으로 정렬한다. 원래는 밑변을 Set에 저장하고 List로 변환해 정렬했었는데 밑변의 범위가 정해져있고 범위가 개수에 비해 더 적기 때문에 밑변을 인덱스로 하는 BooleanArray를 만들어 해당 인덱스의 밑변이 존재할 수 있는지만 저장하는 것이 더 효율적인 방법인 것을 알게 되었다.

따라서 BooleanArray를 만들기 위해 먼저 최대 밑변을 구하기 위해 정렬한 위치에서 가장 큰 값과 가장 작은 값의 차를 찾아 그 값까지 인덱스가 나오도록 배열의 크기를 정해서 만든다. 그 후에 반복문을 돌면서 가능한 밑변을 모두 찾아 배열에 true를 저장하도록 한다.

이렇게 저장된 밑변을 기준으로 넓이가 R을 넘지 않게 하는 깃대의 최대 높이인지 확인하기 위해서 깃대의 높이를 오름차순으로 정렬하고 이분 탐색으로 높이를 찾는다. 이때 찾은 높이가 넓이가 R을 넘지 않으면서 현재 밑변으로 만들 수 있는 넓이가 가장 넓은 현수막이 되므로 이때의 넓이를 밑변마다 모두 찾아서 최댓값을 찾으면 된다.

이때 최댓값을 찾으면 가능한 현수막이 있으므로 그 때의 넓이를 출력하면 되고, 찾지 못한다면 가능한 현수막이 없는 것이므로 -1을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val R = st.nextToken().toInt() * 2
    st = StringTokenizer(br.readLine())
    val x = IntArray(N)
    for(i in 0 until N){
        x[i] = st.nextToken().toInt()
    }
    x.sort()
    val maxWidth = x[N - 1] - x[0]
    val possibleWidth = BooleanArray(maxWidth + 1){false}
    for(i in 0 until N){
        for(j in i + 1 until N){
            possibleWidth[x[j] - x[i]] = true
        }
    }
    val sticks = IntArray(M)
    st = StringTokenizer(br.readLine())
    for(i in 0 until M){
        sticks[i] = st.nextToken().toInt()
    }
    sticks.sort()
    var max = 0
    for(width in 1..maxWidth){
        if(!possibleWidth[width]){
            continue
        }
        var left = 0
        var right = M - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(sticks[mid] * width <= R){
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        if(right >= 0){
            max = maxOf(max, width * sticks[right])
        }
    }
    if(max > 0){
        println("%.1f".format(max.toDouble() / 2))
    } else {
        println(-1)
    }
}
```