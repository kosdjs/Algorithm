# 백준 2668번: 숫자고르기

> 문제: https://www.acmicpc.net/problem/2668

### 문제 풀이

DFS

answer[i] = i가 집합에 포함 되는지

그래프를 첫째 줄에서 둘째 줄의 수로 이동하는 방향 그래프로 만듬

1번부터 그래프를 DFS로 탐색해 본인이 오는 사이클이 되는지 확인함

사이클이 되면 그 사이클에 있는 모든 수들이 정답이 될 수 있는 수이므로 answer 배열 true 대입

answer 배열에 true 갯수를 세서 출력하고 i를 1부터 N까지 반복하며 answer[i]가 true인 i를 출력하면 정답

### 풀이 설명

세로 두 줄, 가로로 N개의 칸으로 이루어진 표가 있다. 첫째 줄의 각 칸에는 정수 1, 2, …, N이 차례대로 들어 있고 둘째 줄의 각 칸에는 1이상 N이하인 정수가 들어 있다. 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다. 이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾아야 한다.

먼저 첫째 줄과 둘째 줄의 숫자를 구분하기 위해 첫째 줄은 숫자 그대로 i라고 하면, 둘째 줄은 숫자가 정해져 있기 때문에 배열에 저장해 arr[i]로 구분한다.

그 이후에 첫째 줄의 숫자 i를 먼저 뽑았다고 생각했을 때 첫째 줄의 숫자와 뽑힌 숫자의 바로 밑 둘째 줄에 들어있는 숫자가 일치하려면 arr[i]도 첫째 줄에서 뽑아야 한다. 그러면 첫째 줄에서 뽑은 arr[i]의 둘째 줄에 들어있는 수도 집합에 들어가야 하기 때문에 arr[arr[i]]도 집합에 들어가야 한다.

따라서 첫째 줄에서 뽑은 수가 집합에 들어가면 그 둘째 줄에 들어있는 수도 집합에 들어가 있어야 하므로 이를 첫째 줄의 수에서 둘째 줄의 수의 방향으로 이동하는 방향 그래프로 나타낼 수 있다. 여기서 집합의 조건이 첫째 줄에서 뽑힌 정수와 둘째 줄에 들어있는 수가 일치해야 하므로 그래프적으로 봤을 때 이 수가 뽑힐 수 있는지 판단하는 것은 이 수에서 그래프 탐색을 실행했을 때 다시 자기 자신으로 돌아올 수 있는지 판단하는 것과 같게 된다.

따라서 1부터 N까지의 모든 수에서 DFS를 통해 자기 자신으로 돌아올 수 있는지 판별하고 가능하다면 집합에 들어올 수 있는 것이므로 answer 배열을 만들어 이 값이 집합에 들어올 수 있다면 true, 아니라면 false를 저장한다.

이 때 DFS를 통해 자기 자신으로 돌아 올 수있는 수를 찾았다면 이 수가 그래프 탐색을 할 때 지나왔던 수들도 집합에 포함될 수 있는 것이므로 이 수에 대해서도 answer 배열에 true를 대입해준다.

이를 모든 수에 대해 반복하면 answer 배열에 저장된 true의 갯수가 집합에 포함될 수 있는 정수의 최대 갯수이고, true가 되도록 하는 수들이 뽑힐 수 있는 정수이므로 갯수와 수들을 차례대로 출력해주면 정답이 된다.

### 소스 코드
```kotlin
fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val arr = IntArray(N + 1)
    for(i in 1..N){
        arr[i] = br.readLine().toInt()
    }
    val answer = BooleanArray(N + 1)
    for(i in 1..N){
        if(answer[i]){
            continue
        }
        val visit = BooleanArray(N + 1)
        var num = arr[i]
        while(num != i){
            visit[num] = true
            num = arr[num]
            if(visit[num]){
                break
            } else {
                visit[num] = true
            }
        }
        if(num == i){
            answer[i] = true
            num = arr[i]
            while(num != i){
                answer[num] = true
                num = arr[num]
            }
        }
    }
    var count = 0
    for(i in 1..N){
        if(answer[i]){
            count++
        }
    }
    println(count)
    for(i in 1..N){
        if(answer[i]){
            println(i)
        }
    }
}
```