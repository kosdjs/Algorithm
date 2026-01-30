# 백준 3967번: 매직 스타

> 문제: https://www.acmicpc.net/problem/3967

### 문제 풀이

브루트 포스, 백트래킹

arr[i] = 매직 스타의 왼쪽, 위에서부터 0부터 11까지의 칸이라고 하면 i칸에 들어간 숫자, 0이면 아직 뽑지 않은 수

picked[i] = 숫자 i가 뽑혔는지 여부

solve(idx, count) = 현재 count개 숫자를 뽑았고, idx부터 확인해 뽑히지 않은 숫자를 뽑아야 할 때 매직 스타를 채우는 재귀 함수

countLine(indices) = indices에 저장된 인덱스에 대해 arr 배열을 확인해 뽑히지 않은 숫자가 있거나 모든 숫자의 합이 26인지 확인해 해당 줄이 매직 스타의 조건에 맞는지 확인하는 함수

countLines() = countLine(indices) 함수를 이용해 매직 스타의 6개의 줄 모두를 확인하는 함수

convertToNum, Char = 문자를 숫자, 숫자를 문자로 바꾸는 함수

find = 사전 순으로 가장 앞선 매직 스타를 찾았는지

입력이 문자로 들어오지만 매직 스타의 조건은 숫자로 판별해야 하므로 입력으로 들어오는 문자에 따라 0부터 12까지의 숫자로 arr 배열에 저장, 이 때 0은 아직 뽑히지 않은 숫자

arr 배열에 저장된 숫자를 확인해 뽑힌 숫자의 개수를 count, 어떤 숫자가 뽑혔는지를 picked 배열에 저장함

숫자를 하나씩 뽑으며 매직 스타인지 확인해 총 12개를 뽑았을 때 매직 스타가 되면 출력을 하기 위해 현재 빈칸을 찾기 시작해야 하는 인덱스 idx, 뽑은 숫자의 갯수 count를 매개 변수로 받는 solve(idx, count) 재귀 함수를 만듬

재귀 함수가 매직 스타를 만들게 하기 위해서 idx부터 빈 칸을 찾으면 그 빈칸에 대해 picked를 이용해 뽑히지 않은 숫자를 하나씩 뽑아서 다음 칸을 확인하도록 재귀 호출해 12개의 숫자를 뽑음

이 때 뽑는 중간에 매직 스타의 조건이 깨져버리면 다음 숫자를 뽑을 필요가 없으므로 매직 스타의 조건을 확인하는 checkLines() 함수를 매번 확인함

사전 순으로 가장 앞선 매직 스타만 출력해야 하므로 처음으로 매직 스타를 찾으면 find를 true로 만들고 함수의 처음에 find를 확인해 true라면 함수를 반환하도록 만듬

이렇게 재귀 함수를 만들었다면 첫 빈칸을 찾아 사용해야 하므로 arr 배열을 확인해 첫 빈칸과 채워져있는 숫자의 갯수 count를 인수로 solve 함수를 호출해 사전 순으로 가장 앞선 매직 스타를 출력하면 정답

### 풀이 설명

일부만 채워진 매직 스타가 주어졌을 때 사전 순으로 가장 앞서도록 매직 스타를 채워야 하는 문제이다.

매직 스타는 총 12개의 칸이 존재하므로 모든 숫자를 순열로 뽑아 매직 스타에 맞게 배치한다고 하면 경우의 수가 12!이 되므로 모든 경우를 확인할 수는 없다.

다만 매직 스타가 되려면 같은 줄에 들어가는 4개의 숫자의 합이 26이 되어야 하기 때문에 굳이 모든 조합을 확인할 필요는 없고 하나 하나 뽑을 때 이 조건이 깨지게 된다면 다음 수를 뽑을 필요가 없어지므로 이를 백트래킹으로 처리할 수 있다.

따라서 백트래킹으로 처리하기 위해 매직 스타의 조건을 충족할때만 다음 빈 칸에 숫자를 뽑는 재귀함수 solve(idx, count)를 정의한다. 이때 idx는 앞에서 뽑은 숫자의 칸의 인덱스이고, count는 현재 뽑은 숫자의 갯수이다. idx를 이용해 빈 칸을 더 빠르게 찾을 수 있고, count를 이용해 매직 스타를 구성하는 12개의 숫자를 모두 뽑았는지 검사할 수 있다.

또한 사전 순으로 가장 앞서는 매직 스타를 출력해야 하므로 숫자를 뽑을 때 안 뽑힌 숫자 중 가장 빠른 숫자부터 뽑아야 하고 처음 매직 스타를 이루는 12개의 숫자를 뽑았을 때 그 뒤에 재귀함수에서는 매직 스타를 출력하면 안되므로 외부에 find 변수를 만들어 매직 스타를 찾았는지 여부를 저장해야 한다.

arr 배열의 인덱스 i마다 arr[i]를 확인해 0이 되는 첫 빈칸에 대해 solve(i, count)를 실행해 현재 입력에 대해 사전 순으로 가장 앞서는 매직 스타를 한 번 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val arr = IntArray(12)
    val picked = BooleanArray(13)
    var count = 0
    var find = false
    fun convertToNum(c: Char): Int = if(c == 'x') 0 else c - 'A' + 1
    readLine().run {
        arr[0] = convertToNum(this[4])
    }
    readLine().run {
        arr[1] = convertToNum(this[1])
        arr[2] = convertToNum(this[3])
        arr[3] = convertToNum(this[5])
        arr[4] = convertToNum(this[7])
    }
    readLine().run {
        arr[5] = convertToNum(this[2])
        arr[6] = convertToNum(this[6])
    }
    readLine().run {
        arr[7] = convertToNum(this[1])
        arr[8] = convertToNum(this[3])
        arr[9] = convertToNum(this[5])
        arr[10] = convertToNum(this[7])
    }
    readLine().run {
        arr[11] = convertToNum(this[4])
    }
    for(num in arr){
        if(num != 0) count++
        picked[num] = true
    }
    fun checkLine(indices: IntArray): Boolean{
        var sum = 0
        for(idx in indices){
            if(arr[idx] == 0) return true
            else sum += arr[idx]
        }
        return if(sum == 26) true else false
    }
    fun checkLines(): Boolean = checkLine(intArrayOf(0, 2, 5, 7))
            && checkLine(intArrayOf(0, 3, 6, 10))
            && checkLine(intArrayOf(1, 2, 3, 4))
            && checkLine(intArrayOf(1, 5, 8, 11))
            && checkLine(intArrayOf(4, 6, 9, 11))
            && checkLine(intArrayOf(7, 8, 9, 10))
    fun solve(idx: Int, count: Int){
        if(find) return
        if(!checkLines()) return
        if(count == 12){
            find = true
            val sb = StringBuilder()
            fun convertToChar(num: Int): Char = 'A' + num - 1
            sb.append("....${convertToChar(arr[0])}....").append("\n")
            sb.append(".${convertToChar(arr[1])}.${convertToChar(arr[2])}.${convertToChar(arr[3])}.${convertToChar(arr[4])}.").append("\n")
            sb.append("..${convertToChar(arr[5])}...${convertToChar(arr[6])}..").append("\n")
            sb.append(".${convertToChar(arr[7])}.${convertToChar(arr[8])}.${convertToChar(arr[9])}.${convertToChar(arr[10])}.").append("\n")
            sb.append("....${convertToChar(arr[11])}....").append("\n")
            print(sb)
            return
        }
        for(i in idx until 12){
            if(arr[i] != 0) continue
            for(num in 1..12){
                if(picked[num]) continue
                arr[i] = num
                picked[num] = true
                solve(i + 1, count + 1)
                arr[i] = 0
                picked[num] = false
            }
            break
        }
    }
    for(i in arr.indices){
        if(arr[i] == 0){
            solve(i, count)
            break
        }
    }
}
```