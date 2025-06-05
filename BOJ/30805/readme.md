> 문제: https://www.acmicpc.net/problem/30805

### 문제 풀이

그리디 알고리즘

1. 수열 A, B에서 공통 수열이 될 수 있는 최댓값을 찾아 저장함
2. 수열 A, B에서 1번에서 찾았던 값 이후의 수 중에서 공통 수열이 될 수 있는 최댓값을 찾아 저장함

위의 과정을 수열이 끝날때까지 반복함

### 풀이 설명

수열 A, B의 공통 수열 중 사전 순으로 가장 나중인 수열은 공통 수열에 들어갈 수 있는 수 중 최댓값으로 시작함

따라서 두 수열에서 공통 수열에 들어갈 수 있는 최댓값을 수열의 앞에서부터 찾아야 함

최댓값을 찾았다면 수열 A, B를 각각 찾은 최댓값까지의 수열과 그 뒤의 수열로 나눌 수 있음

만약 최댓값까지의 수열 중 공통 수열에 들어갈 수 있는 값을 뽑는 순간 최댓값보다 작은 값이 들어가기 때문에 사전 순으로 앞으로 갈 수밖에 없음

그러므로 나눈 수열의 앞은 확인할 필요가 없어짐

이제 뒤의 수열을 확인해야 하는데 여기서도 똑같이 사전 순으로 나중이 되는 값을 찾으려면 공통 수열에 들어갈 수 있는 최댓값을 찾으면 됨

그렇게 수열을 나눠보면서 공통 수열이 될 수 있는 최댓값들을 찾아내면 답

### 소스 코드
```kotlin
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())
    val A = IntArray(N + 1)
    for(i in 1..N){
        A[i] = st.nextToken().toInt()
    }
    val M = br.readLine().toInt()
    st = StringTokenizer(br.readLine())
    val B = IntArray(M + 1)
    for(i in 1..M){
        B[i] = st.nextToken().toInt()
    }
    //공통 부분 수열중 사전 순으로 가장 나중인 수열이 LCS의 부분 수열이 아닐 수 있음
    //부분 수열이 될 수 있는 수 중 가장 큰 값을 찾아서 그 값을 답에 저장하고
    //각 수열에서 그 뒤에 부분에서 다시 부분 수열이 될 수 있는 수중 가장 큰 값 찾기
    var idxA = 1
    var idxB = 1
    var answer = ArrayList<Int>()
    while(idxA <= N && idxB <= M){
        var max = 0
        var maxIdxA = 0
        var maxIdxB = 0
        for(i in idxA..N){
            for(j in idxB..M){
                if(A[i] == B[j] && A[i] > max){
                    max = A[i]
                    maxIdxA = i
                    maxIdxB = j
                }
            }
        }
        if(max > 0){
            answer.add(max)
            idxA = maxIdxA+1
            idxB = maxIdxB+1
        } else {
            break
        }
    }
    println(answer.size)
    for(num in answer){
        print("$num ")
    }
}
```