# 백준 12852번: 1로 만들기 2

> 문제: https://www.acmicpc.net/problem/12852

### 문제 풀이

DP

count[i] = i를 만드는 최소 연산 횟수

before[i] = i를 연산해서 만들 수 있는 수 중 가장 연산 횟수가 작은 수

count 배열을 Int.MAX_VALUE로 초기화 후 count[N]에 0 대입

i를 N 부터 2까지 반복하며 i로 연산 가능한 수에 대해 count 배열의 값을 확인해서 현재 count[i] + 1보다 크다면 count, before 배열 값 변경, 만약 연산 값이 1이라면 반복문 탈출

최소 연산 횟수 count[1] 출력 후 1을 만드는 방법을 출력하기 위해 list를 만듬

idx를 1부터 count[idx]가 0이 될 때까지 list에 before[idx]를 추가하고 idx에 before[idx]를 대입하면 연산 순서가 역순으로 들어감

list를 반전하고 출력하면 정답

### 풀이 설명

정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.

1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
2. X가 2로 나누어 떨어지면, 2로 나눈다.
3. 1을 뺀다.

정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들때 최소 연산 횟수를 구하는 것은 연산을 하면 항상 숫자가 감소하므로 배열을 만들어 N에서 그 수를 만들 때 최소 연산 횟수를 저장해놓으면 N에서 1을 만드는 최소 연산 횟수를 구할 수 있다.

하지만, 이 배열만으로는 연산 순서를 알아낼 수가 없다. 따라서 최소 연산 횟수일때 어떤 수에서 연산을 해야 이 수가 나오는지를 저장하기 위해 before 배열을 만든다.

N에서 특정 수를 만드는 최소 연산 횟수를 구하기 위해 count 배열을 만들고 이 수를 만들 수 있는 최소 연산 횟수를 저장하기 위해 초기에는 Int.MAX_VALUE 값을 저장해놓는다. 이후에 count[N]에 0을 대입하고 i를 N부터 2까지 다음과 같이 반복한다.

먼저 i를 3으로 나눌 수 있는지 확인한다. 나눌 수 있다면 연산 1번을 할 수 있는 것이므로 이 연산을 진행했을 때의 값이 i / 3이고 이 때의 count 배열의 값을 확인해 현재 i에서 연산을 한 것이 다른 수에서 연산한 것 보다 연산 횟수가 적다면 count 배열과 before 배열의 값을 각각 변경해서 최소 연산 횟수와 i에서 연산했음을 저장한다.

그 뒤에 다른 연산도 그 때의 count 배열 값을 확인해 최소 연산 횟수와 몇에서 연산했는지를 저장한다. 이렇게 반복을 마치고나면 count[1]에 1을 만들기 위한 최소 연산 횟수가 저장되어 있고, before 배열에 이 수를 연산해서 만들 수 있는 수 중 연산 횟수가 가장 적은 수가 저장되어 있다. 따라서 before[1] 부터 이 배열의 값을 쭉 확인하면 N에서 1을 최소 연산 횟수로 만들 때 나오는 수들이 역순으로 나온다.

이를 정방향으로 바꾸기 위해 리스트를 하나 만들어 역순으로 되어 있는 수들을 저장하고 리스트를 반전해 정방향으로 바꾼 뒤 출력해주면 정답이 된다.

### 소스 코드
```kotlin
fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val count = IntArray(N + 1){Int.MAX_VALUE}
    count[N] = 0
    val before = IntArray(N + 1)
    for(i in N downTo 2){
        if(count[i] == Int.MAX_VALUE){
            continue
        }
        if(i % 3 == 0){
            if(count[i / 3] > count[i] + 1){
                count[i / 3] = count[i] + 1
                before[i / 3] = i
                if(i / 3 == 1) {
                    break
                }
            }
        }
        if(i % 2 == 0){
            if(count[i / 2] > count[i] + 1){
                count[i / 2] = count[i] + 1
                before[i / 2] = i
                if(i / 2 == 1){
                    break
                }
            }
        }
        if(count[i - 1] > count[i] + 1){
            count[i - 1] = count[i] + 1
            before[i - 1] = i
        }
    }
    println(count[1])
    val list = mutableListOf(1)
    var idx = 1
    while(count[idx] > 0){
        list.add(before[idx])
        idx = before[idx]
    }
    list.reverse()
    for(num in list){
        print("$num ")
    }
}
```