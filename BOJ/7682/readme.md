# 백준 7682번: 틱택토

> 문제: https://www.acmicpc.net/problem/7682

### 문제 풀이

구현

x, oCount = X와 O말의 갯수

x, oLines = X와 O로 이루어진 줄의 갯수

calLines(c) = c로 이루어진 줄이 판에 몇개 있는지

checkLine(indices, c) = 판에서 indices에 저장된 인덱스가 가리키는 칸에 모두 문자 c가 있는지

isValid = 판이 정상적으로 진행된 최종 결과인지

판에서 X말과 O말의 갯수를 셈

3 x 3칸의 판이므로 줄을 이룰 수 있는 3칸은 가로 3줄, 세로 3줄, 대각선 2줄밖에 없으므로 이 각각 X말과 O말이 이 줄을 이룰 수 있는지 확인함

정상적으로 끝나면 두 말이 모두 줄을 이룰 수 없으므로 xLines, oLines가 모두 1 이상이면 isValid에 false 대입

만약 모든 칸에 말이 있지 않고 어느 말도 줄을 이루지 못했으면 아직 게임이 끝나지 않은 것이므로 isValid에 false 대입

만약 O말이 줄을 이루어서 이겼다면 O말이 X말의 갯수와 같고, X말이 줄을 이루어서 이겼으면 X말이 O말보다 갯수가 하나 많아야 하므로 이 때 isValid에 false 대입

X랑 O말을 번갈아하면서 놓으므로 xCount, oCount의 차이를 비교해 0보다 작거나 1보다 크면 isValid에 false 대입

isValid에 저장된 값에 따라 valid, invalid를 출력해주면 정답

### 풀이 설명

틱택토 판을 입력받으면 그 틱택토 판이 정상적으로 진행된 게임의 최종 상태인지 판별해야 한다.

정상적으로 진행된 게임의 최종 상태는 한 말이 줄을 완성해 끝난 경우이거나, 아무 말도 줄을 완성하지 못해 9칸을 모두 채운 상태가 있을 수 있다. 그리고 항상 X말을 먼저 놓고 번갈아 말을 놓기 때문에 X말과 O말의 갯수 차이가 1개 이하로 나야 한다.

따라서 정상적으로 진행된 게임은 두 가지 요소의 영향을 받는다. 먼저 X와 O말이 몇개가 있는지, 각각 3칸을 이은 줄이 몇 개가 있는지를 알면 판이 정상적인 게임의 결과인지 판단할 수 있다.

xCount, oCount에 판에 존재하는 X, O말의 갯수를 저장하고 모든 줄을 확인해 xLines, oLines에 각 말이 줄을 이루는 갯수를 저장한다. 그리고 여러 요소를 확인해 이 판이 정상적으로 끝난 판인지 저장하기 위해 isValid에 true를 저장해 비정상적인 게임의 요소가 검사되지 않으면 정상적인 판이라는 것을 나타낸다.

정상적인 게임이 진행될 때 한 사람이 줄을 이루면 게임이 끝나기 때문에 두 사람이 모두 줄을 이루는 판은 정상적인 게임이 낼 수 없는 결과이므로 xLines와 oLines가 모두 1 이상이면 isValid에 false를 저장하면 된다.

또한 한 사람이 줄을 이룰 때까지 판에 계속 말을 놓고 판이 가득차면 게임이 끝나기 때문에 판에 말이 가득차지 않고 모든 사람이 줄을 이루지 못했으면 게임이 끝나지 않은 것이므로 최종 결과가 아니다. 그러므로 xCount와 oCount를 더한 값이 9가 아닐 때 xLines, oLines가 모두 0이라면 isValid에 false를 저장하면 된다.

이제 O말을 가진 사람이 이겼을 때를 살펴보면 X말부터 번갈아서 말을 놓는데 마지막으로 O말을 놓았을 때 게임이 끝나므로 O말과 X말이 같은 개수로 판에 배치되어야 한다. 따라서 oLines가 1 이상이고 xLines가 0일 때 xCount - oCount가 1이라면 비정상적인 배치이므로 isValid에 false를 저장하면 된다.

X말을 가진 사람이 이겼다면 반대로 마지막 말이 X말인 것이므로 X말이 O말보다 1개 많아야 한다. 따라서 oLines가 0이고 xLines가 1 이상이고 xCount - oCount가 0이 된다면 비정상적인 배치이므로 isValid에 false를 저장하면 된다.

X말을 먼저 놓고 번갈아 놓는다는 조건을 지금 게임이 이겼을 때에만 확인하고 있으나 전체적인 경우를 살펴보면 항상 X말이 먼저 배치되고 그 다음에 O말이 배치되므로 X말이 항상 1개 많거나 O말의 갯수와 같아야 한다. 따라서 xCount - oCount가 1보다 크거나 0보다 작다면 isValid에 false를 저장하면 된다.

여기까지 확인한다면 게임 결과가 비정상적일 때의 모든 조건을 확인한 것이므로 isValid가 true라면 정상적인 게임 결과이므로 valid를, false라면 하나라도 비정상적일 때의 조건을 만족하는 것이므로 invalid를 출력하면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val sb = StringBuilder()
    while(true){
        val s = readLine()
        if(s == "end") break
        var xCount = 0
        var oCount = 0
        for(c in s){
            if(c == 'X') xCount++
            else if(c == 'O') oCount++
        }
        fun checkLine(indices: IntArray, c: Char): Boolean{
            return s[indices[0]] == c && s[indices[1]] == c && s[indices[2]] == c
        }
        fun calLines(c: Char): Int{
            var result = 0
            if(checkLine(intArrayOf(0, 1, 2), c)) result++
            if(checkLine(intArrayOf(3, 4, 5), c)) result++
            if(checkLine(intArrayOf(6, 7, 8), c)) result++
            if(checkLine(intArrayOf(0, 3, 6), c)) result++
            if(checkLine(intArrayOf(1, 4, 7), c)) result++
            if(checkLine(intArrayOf(2, 5, 8), c)) result++
            if(checkLine(intArrayOf(0, 4, 8), c)) result++
            if(checkLine(intArrayOf(2, 4, 6), c)) result++
            return result
        }
        var isValid = true
        val oLines = calLines('O')
        val xLines = calLines('X')
        if(oLines > 0 && xLines > 0) isValid = false
        if(xCount + oCount != 9 && oLines == 0 && xLines == 0) isValid = false
        if(oLines > 0 && xLines == 0 && xCount - oCount == 1) isValid = false
        if(oLines == 0 && xLines > 0 && xCount - oCount == 0) isValid = false
        if(xCount - oCount > 1 || xCount - oCount < 0) isValid = false
        sb.append(if(isValid) "valid" else "invalid").append("\n")
    }
    print(sb)
}
```