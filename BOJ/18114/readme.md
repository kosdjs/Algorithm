# 백준 18114번: 블랙 프라이데이

> 문제: https://www.acmicpc.net/problem/18114

### 문제 풀이

투 포인터

무게가 C인 물건이 있으면 1 출력 후 반환

없으면 무게 기준으로 오름차순으로 정렬하고 투 포인터로 선택한 물건 2개의 무게 합이 C인 조합을 찾고 찾으면 1 출력 후 반환

없으면 물건을 앞에서부터 하나씩 선택하며 선택한 물건을 제외한 나머지 물건을 투 포인터로 선택하고 선택한 물건의 무게 합이 C인 조합을 찾으면 1 출력 후 반환

조합을 못찾으면 0 출력

### 풀이 설명

제시하는 양의 정수의 무게 C에 딱 맞게 물건들을 가져와야 하고, 선택할 수 있는 물건은 최대 3개까지이고, 같은 물건을 중복 선택하는 것은 불가능하다. 그리고 백화점에서 판매하는 물건들의 무게는 모두 다르므로 무게가 C가 되도록 하는 조합은 물건이 1개일때가 있고, 2개일 때가 있고, 3개일 수 있다.

또한 조합이 있는지만 확인하면 되기 때문에 가장 빨리 찾을 수 있는 조합부터 찾는 것이 좋다. 따라서 1개일 때를 먼저 찾는다.

1개일 때는 물건의 무게가 C이면 되므로 무게를 입력받을 때 무게가 C인 물건이 있다면 1을 출력하고 반환하면 된다.

2개일 때는 물건 2개의 무게합이 C인 조합을 찾아야 하므로 무게를 기준으로 오름차순으로 정렬해 가장 가벼운 물건과 가장 무거운 물건의 조합부터 확인을 한다. 이때 무게의 합이 C보다 크게 되면 무거운 물건의 무게를 줄여야 하기 때문에 무거운 물건보다 가벼운 물건을 찾는다. 무게의 합이 C보다 작으면 더 무겁게 만들어야 하므로 가벼운 물건보다 더 무거운 물건을 찾는다. 이렇게 무게를 조절하면서 합이 C가 되는 조합을 찾으면 1을 출력하고 반환하면 되고 찾지 못했다면 3개일 때의 조합을 찾아야 한다.

3개일 때의 조합을 찾는 방법은 1개의 물건을 미리 선택해놓고 나머지 물건들에서 2개의 물건을 선택하는 것이다. 이때 2개의 물건을 선택하는 방법은 방금 했던 방식처럼 투 포인터를 이용하는 것이 완전 탐색보다 빠르기 때문에 이 방식을 이용한다. 따라서 1개의 물건을 가장 가벼운 물건부터 순차적으로 선택하고 선택한 물건보다 무거운 물건들 중에서 투 포인터로 2개의 물건을 선택한다. 이 때 무게의 합이 C인 조합을 찾으면 1을 출력하고 반환한다. 모든 조합을 확인하고 나서 함수가 반환되지 않았다면 조합을 찾지 못한것이므로 0을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val w = IntArray(N)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        w[i] = st.nextToken().toInt()
        if(w[i] == C){
            println(1)
            return
        }
    }
    w.sort()
    var left = 0
    var right = N - 1
    while(left < right){
        val sum = w[left] + w[right]
        if(sum == C){
            println(1)
            return
        } else if(sum < C){
            left++
        } else {
            right--
        }
    }
    for(i in 0 until N - 2){
        val c = C - w[i]
        var left = i + 1
        var right = N - 1
        while(left < right){
            val sum = w[left] + w[right]
            if(sum == c){
                println(1)
                return
            } else if(sum < c){
                left++
            } else {
                right--
            }
        }
    }
    println(0)
}
```