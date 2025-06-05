package BOJ_30805

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