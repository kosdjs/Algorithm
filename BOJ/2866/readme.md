# 백준 2866번: 문자열 잘라내기

> 문제: https://www.acmicpc.net/problem/2866

### 문제 풀이

이분 탐색

mid = (left + right) / 2

mid 행까지 지운 후 남은 문자열들 중 동일한 문자열이 발견된다면 right = mid - 1, 아니라면 left = mid - 1

이를 left가 right보다 커질때까지 반복하면 right 행까지 행을 지울 수 있음 행의 인덱스를 0부터 셌으니 right + 1을 출력하면 정답

### 풀이 설명

가장 위의 행을 지워도 테이블의 열을 읽어서 문자열이 중복되지 않는다면, 가장 위의 행을 지워주고, count의 개수를 1 증가시키는, 이 과정을 반복한다. 만약 동일한 문자열이 발견되는 경우, 반복을 멈추고 count의 개수를 출력 후 프로그램을 종료한다.

따라서 가장 위의 행을 하나 지울 때마다 count를 1 증가시키고, 항상 가장 위의 행을 지우기 때문에 가장 위의 행부터 특정 행까지 지웠을 때 동일한 문자열이 나오지 않도록 하는 행의 최대 갯수를 구하면 된다.

이를 구하기 위해 특정 행을 이분 탐색으로 구할 수 있다. 문자열을 배열에 저장했으므로 left를 0부터 right를 R - 1부터 탐색을 시작한다. mid는 (left + right) / 2이고 이 mid 행까지 행을 지웠다고 생각하면 mid + 1 행부터 시작하는 문자열이 남는 것이므로 각 열마다 남는 문자열을 구한다.

이렇게 구한 문자열들 중 동일한 문자열이 있는지 판단하기 위해 HashSet을 이용한다. 구한 모든 문자열을 HashSet에 넣으면 동일한 문자열이 없다면 모든 문자열이 하나씩 들어가 있기 때문에 size가 열의 수만큼 나올 것이고, 아니라면 동일한 문자열은 들어가지 않으므로 size가 열의 수보다 적게 된다.

이에 따라 mid 행까지 지웠을 때 동일한 문자열이 있는지 판별해 동일한 문자열이 있다면 mid 행까지 지울 수 없는 것이므로 right를 mid - 1로 바꿔서 다시 탐색을 한다. 동일한 문자열이 없다면 mid 행까지 지울 수 있고 더 많은 행을 지울 수 있을지 판단해야 하므로 left = mid + 1로 바꿔서 탐색을 한다.

이렇게 탐색하다보면 left가 right보다 커지게 되는데, 행의 최댓값을 찾아야 하고 mid 행을 지울 수 없을때마다 right를 mid - 1로 바꿨다는 것은 즉, 계속 지울 수 없는 행의 앞 행을 넣었다는 뜻이고 이를 계속 반복해서 지울 수 있는 가장 마지막 행이 들어가있게 된다. 이 값이 찾는 행이고 행을 인덱스로 봤기 때문에 갯수로 세려면 1을 더해야 하니 1을 더한 값을 출력하면 정답이 된다.

### 소스 코드
```kotlin
import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val st = StringTokenizer(br.readLine())
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val arr = Array(R){ CharArray(C) }
    for(r in 0 until R){
        val str = br.readLine()
        for(c in 0 until C){
            arr[r][c] = str[c]
        }
    }
    var left = 0
    var right = R - 1
    while(left <= right){
        val mid = (left + right) / 2
        val set = HashSet<String>()
        for(c in 0 until C){
            val sb = StringBuilder()
            for(i in mid + 1 until R){
                sb.append(arr[i][c])
            }
            set.add(sb.toString())
        }
        if(set.size == C){
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    println(right + 1)
}
```