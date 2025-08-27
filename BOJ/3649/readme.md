# 백준 3649번: 로봇 프로젝트

> 문제: https://www.acmicpc.net/problem/3649

### 문제 풀이

투 포인터

레고 조각을 arr에 저장 후 오름차순으로 정렬

left = 0, right = n - 1에서 시작해 두 조각의 합이 x보다 작다면 left++, 크다면 right--

합이 x와 같다면 구멍을 막을 두 조각을 찾은 것이므로 답 출력

### 풀이 설명

구멍의 너비는 x 센티미터이고, 두 레고 조각으로 구멍을 막아야 한다. 구멍에 넣을 두 조각의 길이의 합은 구멍의 너비와 정확하게 일치해야 한다. 그리고 막을 수 있는 두 조각이 여러 쌍인 경우 두 조각의 길이의 차가 더 큰 쌍을 찾아야 한다.

따라서 이런 두 조각을 찾는 것은 두 조각의 길이의 합이 결국 x 센티미터가 되게 만들어야 하는 것으로 볼 수 있고 임의의 두 조각의 길이의 합이 x 센티미터와 차이가 난다면 이를 x 센티미터로 만들려면 한 조각을 다른 조각으로 교체하는 방법밖에는 없다. 따라서 합이 x 센티미터보다 크다면 한 조각을 더 짧은 조각으로 교체해야 하고 합이 더 작다면 한 조각을 더 긴 조각으로 교체해야 한다.

따라서 이를 쉽게 확인하기 위해 레고 조각을 길이 기준으로 정렬해야 한다. 오름차순으로 정렬하면 인덱스가 더 작은 조각이 더 짧은 조각이 되고 인덱스가 더 큰 조각이 더 긴 조각이 된다. 따라서 정렬되어 있을 때 두 조각의 합이 x 센티미터보다 짧다면 한 조각을 인덱스가 더 큰 조각으로 교체하면 되고, 합이 더 길다면 한 조각을 인덱스가 더 작은 조각으로 교체하면 된다.

따라서 두 조각의 길이의 차가 더 크고 길이의 합이 x 센티미터가 되는 쌍을 찾기 위해서 가장 짧은 조각과 가장 긴 조각의 쌍부터 확인하면서 합에 따라 조각을 교체하면서 답을 찾으면 된다.

### 소스 코드
```kotlin
fun main(){
    val br = System.`in`.bufferedReader()
    while(true){
        val t = br.readLine() ?: break
        val x = t.toInt() * 10000000
        val n = br.readLine().toInt()
        val arr = IntArray(n)
        for(i in arr.indices){
            arr[i] = br.readLine().toInt()
        }
        arr.sort()
        var find = false
        var left = 0
        var right = n - 1
        while(left < right){
            val sum = arr[left] + arr[right]
            if(sum < x){
                left++
            } else if(sum > x){
                right--
            } else {
                println("yes ${arr[left]} ${arr[right]}")
                find = true
                break
            }
        }
        if(!find){
            println("danger")
        }
    }
}
```