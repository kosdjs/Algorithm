# 백준 7490번: 0 만들기

> 문제: https://www.acmicpc.net/problem/7490

### 문제 풀이

구현, DFS

step = 현재 연산자 다음의 숫자

calcOp = 연산해야 하는 공백을 제외한 연산자

left = 연산의 왼쪽 항

right = 연산의 오른쪽 항

lastOp = 공백을 포함한 마지막 연산자

1부터 N까지 공백, 덧셈, 뺄셈의 모든 경우를 탐색하기 위해 DFS로 탐색함

모든 경우를 계산해 값이 0이 나오는 연산만 출력하면 정답

### 풀이 설명

1부터 N까지의 수를 오름차순으로 쓴 수열에 숫자 사이에 +(덧셈), -(뺄셈), ' '(공백)을 넣어 수식을 만들고 그 값이 0이 되는 수식을 모두 찾는 문제이다.

수식을 왼쪽에서부터 연산자를 하나씩 연산한다고 생각하면 연산자의 왼쪽 항, 연산자, 연산자의 오른쪽 항으로 나눌 수 있다. 이때 숫자 사이에 연산자 대신 공백이 올 수 있기 때문에 공백을 처리하기 전에 연산을 해버리면 값이 달라질 수 있다.

따라서 공백을 처리하기 위해 현재 연산자를 찾았을 때 바로 연산하지 않고 다음 연산자를 찾을때까지 공백을 처리하고 그 후에 연산을 해야 한다. 이를 구현하기 위해 오른쪽 항의 공백을 처리하고 난 후 연산해야 하는 연산자를 calcOp에 저장한다.

현재 연산자가 덧셈이라면 calcOp에 연산해야 하는 연산자가 들어가 있고, 이 연산의 왼쪽 항이 left, 오른쪽 항이 right에 저장되어 있을 것이다. 즉 left calcOp right가 현재 연산자의 왼쪽 항이 되므로 left에 그 값을 연산해서 저장한다. 그 후에 연산자 다음 숫자가 step이므로 right에 step을 저장하고 다음 연산자와 숫자를 계산하기 위해 step을 증가시키고 lastOp, calcOp에 덧셈을 뜻하는 '+'을 저장해 다시 함수를 호출한다.

현재 연산자가 뺄셈이라면 덧셈과 동일하게 동작하고 step을 증가시키고 lastOp, calcOp이 뺄셈이 되므로 '-'를 저장하고 다시 함수를 호출한다.

현재 연산자가 공백이라면 연산을 아직 하면 안되기 때문에 오른쪽 항만 숫자를 붙이도록 right * 10 + step을 계산해 대입하고 step을 증가시킨 후 마지막 연산자를 뜻하는 lastOp에 공백을 대입하고 공백을 전부 처리하고 연산을 하기 위해 이전에 입력된 calcOp는 그대로 대입해서 함수를 다시 호출한다.

이렇게 진행하다보면 step이 N보다 커질때가 있는데 이러면 수식의 끝까지 온 것이므로 마지막 연산자를 확인해 계산을 해 수식의 값을 찾는다. 그 이후에 이 값이 0이라면 문제에 맞는 수식을 찾은것이므로 이 수식을 출력해준다.

이 함수를 왼쪽 항에 0, 오른쪽 항에 1, 현재 연산자 뒤에 오는 숫자 2, 마지막 연산자와 계산해야 하는 연산자에 덧셈을 넣어서 호출하면 모든 수식을 출력할 수 있다. 이를 모든 테스트 케이스에 대해 실행하면 정답이 된다.

### 소스 코드
```kotlin
val sb = StringBuilder()

fun main(){
    val br = System.`in`.bufferedReader()
    val t = br.readLine().toInt()
    repeat(t){
        sb.append("1")
        val N = br.readLine().toInt()
        calc(N, 2, 0, 1, '+', '+')
        sb.setLength(0)
        println()
    }
}

fun calc(N: Int, step: Int, left: Int, right: Int, lastOp: Char, calcOp: Char){
    if(step > N){
        var sum = 0
        if (lastOp == '+'){
            sum = left + right
        } else if(lastOp == '-'){
            sum = left - right
        } else {
            if(calcOp == '+'){
                sum = left + right
            } else {
                sum = left - right
            }
        }
        if(sum == 0){
            println(sb)
        }
    } else {
        sb.append(" $step")
        calc(N, step + 1, left, right * 10 + step, ' ', calcOp)
        sb.setLength(sb.length - 2)
        sb.append("+$step")
        calc(N, step + 1, if(calcOp == '+') left + right else left - right, step, '+', '+')
        sb.setLength(sb.length - 2)
        sb.append("-$step")
        calc(N, step + 1, if(calcOp == '+') left + right else left - right, step, '-', '-')
        sb.setLength(sb.length - 2)
    }
}
```