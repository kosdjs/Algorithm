# 백준 23048번: 자연수 색칠하기

> 문제: https://www.acmicpc.net/problem/23048

### 문제 풀이

수학

count = 조건에 맞게 색칠할 때 필요한 색의 수

colors[i] = 숫자 i에 칠한 색, 0이라면 색칠되지 않은 것

서로소는 두 수의 최대 공약수가 1인 것이므로 특정 수의 배수는 서로소가 아님

1은 무조건 다른 수와 서로소이므로 독립적인 색이 필요함

colors[1]을 1로 해놓고 count를 1로 초기화 함

i를 2부터 N까지 반복하며 colors[i]가 0이라면 새로운 색으로 칠하기 위해 count를 1 증가시키고 모든 i의 배수 idx에 대해 colors[idx]에 count를 저장해 배수를 모두 같은 색으로 칠함

그러면 count에 필요한 색의 최소 갯수가 저장되므로 이를 출력하고 colors에 저장된 색깔들을 출력하면 정답

### 풀이 설명

서로소인 두 자연수를 다른 색으로 칠하도록 자연수 1부터 N까지를 색칠한다고 했을 때 색이 최소 몇 개 필요한지 구하는 문제이다.

서로소는 두 수의 최대 공약수가 1이라는 것이므로 1을 제외한 수의 배수는 서로소가 아니기 때문에 같은 색으로 칠할 수 있다.

따라서 낮은 수부터 색칠되어있지 않은 수를 새로운 색으로 칠하고 그 수의 배수를 전부 같은 색으로 칠하면 최소한으로 색을 사용할 수 있다.

그러므로 색의 최소 갯수를 저장할 변수를 count라고 선언하고 1이 항상 다른 수와 다른 색이어야 하므로 1의 색인 1로 초기화 한다. 그 다음 해당 숫자에 색칠된 색을 저장하기 위해 배열 colors를 정의하고 1의 색인 colors[1]을 1로 초기화한다.

그 뒤에 색칠되어있지 않은 수를 찾아보기 위해 i를 2부터 N까지 반복하며 colors[i]를 확인한다. 만약 colors[i]가 0이라면 색칠되지 않은 수를 찾은 것이므로 해당 수를 count번의 색으로 칠하기 위해 count를 1 증가시킨다.

그 이후에 i의 모든 배수 idx에 대해 colors[idx]에 count를 저장해 같은 색으로 모두 칠해준다. 이 과정을 모든 i에 대해 반복하면 count에 필요한 색의 최소 갯수, colors 배열에 각 숫자가 어떤 색으로 칠해졌는지 저장되어 있으므로 이를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    nextToken()
    val N = nval.toInt()
    var count = 1
    var colors = IntArray(N + 1)
    colors[1] = 1
    for(i in 2..N){
        if(colors[i] == 0){
            count++
            var idx = i
            while(idx <= N){
                colors[idx] = count
                idx += i
            }
        }
    }
    val sb = StringBuilder()
    sb.append(count).append("\n")
    for(i in 1..N){
        sb.append(colors[i]).append(" ")
    }
    print(sb)
}
```