# 백준 12931번: 두 배 더하기

> 문제: https://www.acmicpc.net/problem/12931

### 문제 풀이

그리디

answer = 연산 횟수

count = 0의 갯수

값 하나 1 빼기, 전체 값 2로 나누기의 두 가지 연산으로 B를 A로 만드는 횟수를 셈

값이 홀수일때만 1 빼고 최대한 전체를 2로 나누는 방향으로 횟수를 세서 출력하면 정답

### 풀이 설명

두 가지 연산 배열 값 1 증가와 전체 값 두 배가 있을 때 A를 B로 만드는 최소 연산 횟수를 구하는 문제이다. A의 초기 상태가 모든 값이 0이고 B의 값이 0 이상 1000 이하이기 때문에 최소한의 횟수로 전체 값을 증가시키는 것이 된다. 그러므로 값 하나를 1 증가 시키는 것보다 모든 값을 두 배로 만드는 것이 더 많은 값을 증가시키기 때문에 최대한 두 배로 만드는 연산을 많이 해야 한다.

전체 값을 두 배로 만들기 전에 필요한 값들을 1 더해주어야 두 배를 했을 때 값이 증가하고, 배열의 값마다 1을 더하는 것과 두 배를 하는 순서가 다를 수 있기 때문에 이를 고려해야 한다. 그리고 전체 값을 두 배로 만들면 항상 모든 배열의 값이 짝수가 되고 1을 더했을 때만 값이 홀수가 될 수 있다.

따라서 역순으로 생각했을 때, 전체 값을 2로 나누었을 때 홀수가 되는 값들은 B를 만들기 위해 두 배 연산 전에 1을 더해준 값들이며, 이 때문에 홀수인지 여부만 판단하면 1을 더하는 시점을 쉽게 알 수 있다.

결과적으로 B에서 A로 만드는 과정을 역산하며, 값이 짝수일 때는 2로 나누고 홀수일 때는 1을 빼는 연산 횟수를 세면, A를 B로 만드는 최소 연산 횟수를 구할 수 있다. 그러므로 풀이와 같이 연산 횟수를 세서 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val N = readLine()!!.toInt()
    val arr = IntArray(N)
    val st = StringTokenizer(readLine())
    for(i in 0 until N){
        arr[i] = st.nextToken().toInt()
    }
    var answer = 0
    var count = 0
    while(count != N){
        count = 0
        for(i in 0 until N){
            if(arr[i] == 0){
                count++
            } else {
                if(arr[i] % 2 == 1){
                    arr[i]--
                    answer++
                    if(arr[i] == 0){
                        count++
                    }
                }
            }
        }
        if(count == N){
            break
        } else {
            answer++
            for(i in 0 until N){
                arr[i] /= 2
            }
        }
    }
    println(answer)
}
```