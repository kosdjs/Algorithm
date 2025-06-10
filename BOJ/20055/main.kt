package BOJ_20055

import java.io.*
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val A = IntArray(N * 2)
    st = StringTokenizer(br.readLine())
    for(i in 0 until N * 2){
        A[i] = st.nextToken().toInt()
    }
    var answer = 1
    var count = 0
    var idx = 0
    val upperBelt = IntArray(N){0}
    while (true){
        //1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
        idx--
        if(idx < 0){
            idx += N * 2
        }
        for(i in N-1 downTo 1){
            upperBelt[i] = upperBelt[i-1]
        }
        upperBelt[0] = 0
        upperBelt[N-1] = 0
        //2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다
        // 만약 이동할 수 없다면 가만히 있는다.
        for(i in N-2 downTo 0){
            if(upperBelt[i] == 1 && upperBelt[i + 1] == 0){
                var idxA = idx + i + 1
                if(idxA >= N * 2){
                    idxA -= N * 2
                }
                if(A[idxA] > 0){
                    A[idxA]--
                    upperBelt[i+1] = 1
                    upperBelt[i] = 0
                    if(A[idxA] == 0){
                        count++
                    }
                }
            }
        }
        //3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
        if(A[idx] > 0){
            A[idx]--
            upperBelt[0] = 1
            if(A[idx] == 0){
                count++
            }
        }
        //4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
        if(count >= K){
            break
        } else {
            answer++
        }
    }
    println(answer)
}