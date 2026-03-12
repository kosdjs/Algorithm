# 백준 9519번: 졸려

> 문제: https://www.acmicpc.net/problem/9519

### 문제 풀이

구현

s = 눈을 X번 깜빡이고 난 이후의 단어

rewind(arr) = 단어 arr을 눈을 깜빡이기 전의 단어로 되돌리는 함수

isCycle = X번 이내에 원래 상태로 되돌아오는지 여부

단어를 되돌리는 과정을 여러번 반복하면 원래의 단어로 되돌아오는 경우가 있음

처음 상태를 s에 저장하고 arr을 X번 되돌리며 count를 1씩 증가하다가 s와 동일한 상태가 되면 isCycle에 true를 저장하고 반복문을 빠져나옴

만약 isCycle이 true라면 X번 반복하기 전에 원래 상태로 되돌아온 것이므로 X를 count로 나눈 나머지만큼 다시 되돌려야 원래 상태로 돌아올 수 있음

X를 count로 나눈 나머지만큼 되돌린 arr을 출력하면 정답

### 풀이 설명

눈을 한 번 깜빡이면 단어의 앞 절반은 홀수 자리에 오름차순으로, 뒤 절반은 짝수 자리에 내림차순으로 나열되도록 섞일 때 X번 깜빡인 이후의 단어가 오면 원래 단어를 찾는 문제이다.

일단 X가 최대 10억이므로 X번을 직접 되돌리는 것은 불가능할 수 있다. 그러나 단어가 섞이는 것이 규칙성이 있기 때문에 일정 횟수를 반복하다 보면 원래 단어로 되돌아갈 가능성이 있다.

그러므로 원래 단어로 되돌아오는 횟수를 c라고 하면 X = ac + b라는 식으로 나타낼 수 있다. 즉, X번을 돌리는게 아니라 c를 찾으면 X를 c로 나눈 나머지인 b번만 되돌리면 된다는 뜻이다.

처음 상태를 s에 저장하고 X번 이전에 원래 상태로 돌아오는지 여부를 isCycle에 저장하고 그때의 되돌린 횟수를 count에 저장한다. 그리고 단어를 되돌리는 함수를 rewind(arr)로 정의한다.

그 이후에 s를 X번 되돌리는 중간에 원래 상태로 돌아오는지 확인해야 하므로 임의의 변수 arr에 s를 복사해서 저장하고 이 arr을 rewind(arr) 함수를 이용해 되돌리기 시작한다.

이때 한번 함수를 실행할때마다 count를 1씩 증가시켜 횟수를 기록한다. 만약 X번 반복하기 전에 arr이 s와 같은 상태가 된다면 count번만큼 되돌리면 원래 상태로 돌아오는 것이므로 isCycle에 true를 저장하고 반복문을 빠져나가면 된다.

그 이후 원래 단어를 찾으려면 X를 count로 나눈 나머지만큼 다시 되돌려야 하므로 arr을 X를 count로 나눈 나머지만큼 되돌려 arr에 원래 단어를 저장하고 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val X = readLine().toInt()
    val s = readLine().toCharArray()
    var arr = s.copyOf()
    fun rewind(arr: CharArray): CharArray{
        val result = CharArray(arr.size)
        var i = 0
        var j = 0
        while(j < arr.size){
            result[i] = arr[j]
            j += 2
            i++
        }
        j = arr.size - if(arr.size % 2 == 0) 1 else 2
        while(j >= 0){
            result[i] = arr[j]
            j -= 2
            i++
        }
        return result
    }
    var count = 0
    var isCycle = false
    for(i in 0 until X){
        arr = rewind(arr)
        count++
        if(s.contentEquals(arr)){
            isCycle = true
            break
        }
    }
    if(isCycle){
        repeat(X % count){
            arr = rewind(arr)
        }
    }
    println(arr)
}
```