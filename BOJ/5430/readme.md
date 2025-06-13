> 문제: https://www.acmicpc.net/problem/5430

### 문제 풀이

문자열 + 구현 문제

입력에서 배열로 바꾸기 위해 대괄호를 자르고 ","를 기준으로 split해서 리스트에 저장함

현재 함수가 R이면 단순히 리스트를 바로 뒤집는 것이 아닌 cursor에 현재 방향이 앞인지 뒤인지 저장함

현재 함수가 D이면 리스트가 비었는지 확인 후 비어있지 않다면 현재 리스트의 방향을 체크해 앞이면 앞의 요소, 뒤면 뒤의 요소를 삭제함, 비어있다면 error 출력 후 정상적으로 끝났는지를 확인하는 condition을 false로 바꿈

모든 함수를 처리하고 condition을 확인해 cursor를 통해 현재 방향이 뒤라면 리스트를 뒤집어주고 현재 배열을 출력함

### 풀이 설명

입력을 받을 때 배열이 비어있을 수 있고 이 경우를 생각하지 않으면 오류가 발생한다

두가지 함수가 R이면 배열의 순서를 뒤집고 D이면 첫 번째 수를 버리는 것이기 때문에 그대로 구현하면 된다고 생각할 수 있는데 배열이 뒤집혔을 때 함수 D를 수행하면 사실 뒤집기 전에 배열에서 마지막 수를 버리는 것이 된다

따라서 R이 나왔을 때 D를 수행할 방향을 지정하고, 모든 함수를 수행한 후에 방향이 뒤라면 그 때 배열을 뒤집기만 해도 된다

또한 함수를 모두 끝냈을 때 배열이 비어있을 경우도 있으므로 이 때도 확인이 필요함

### 소스 코드
```kotlin
import java.io.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val T = br.readLine().toInt()
    for (i in 1..T){
        val op = br.readLine()
        val n = br.readLine().toInt()
        var numberString = br.readLine()
        numberString = numberString.substring(1, numberString.length-1)
        val arr = ArrayList<Int>()
        if(numberString.isNotEmpty()){
            for(s in numberString.split(",")){
                arr.add(s.toInt())
            }
        }
        var cursor = 0 // 0이면 앞, 1이면 뒤
        var condition = true
        for(c in op){
            if(c == 'R'){
                cursor = if(cursor == 0) 1 else 0
            } else {
                if(arr.isNotEmpty()){
                    if(cursor == 0){
                        arr.removeFirst()
                    } else {
                        arr.removeLast()
                    }
                } else {
                    println("error")
                    condition = false
                    break
                }
            }
        }
        if (condition){
            if(arr.isNotEmpty()){
                if (cursor == 1){
                    arr.reverse()
                }
                print("[${arr[0]}")
                for(i in 1 until arr.size){
                    print(",${arr[i]}")
                }
                println("]")
            } else {
                println("[]")
            }
        }
    }
}
```