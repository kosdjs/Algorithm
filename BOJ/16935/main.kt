package BOJ_16935

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()
    var M = st.nextToken().toInt()
    var halfN = N / 2
    var halfM = M / 2
    val R = st.nextToken().toInt()
    var arr = Array(N){ IntArray(M) }
    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        for(j in 0 until M){
            arr[i][j] = st.nextToken().toInt()
        }
    }
    st = StringTokenizer(br.readLine())
    repeat(R){
        val op = st.nextToken().toInt()
        if(op == 1){
            for(i in 0 until halfN){
                for(j in 0 until M){
                    val temp = arr[i][j]
                    arr[i][j] = arr[N - 1 - i][j]
                    arr[N - 1 - i][j] = temp
                }
            }
        } else if(op == 2){
            for(i in 0 until N){
                arr[i].reverse()
            }
        } else if(op == 3){
            val temp = Array(M){IntArray(N)}
            for(i in 0 until M){
                for(j in 0 until N){
                    temp[i][j] = arr[N - 1 - j][i]
                }
            }
            arr = temp
            val t = N
            N = M
            M = t
            halfN = N / 2
            halfM = M / 2
        } else if(op == 4){
            val temp = Array(M){IntArray(N)}
            for(i in 0 until M){
                for(j in 0 until N){
                    temp[i][j] = arr[j][M - 1 - i]
                }
            }
            arr = temp
            val t = N
            N = M
            M = t
            halfN = N / 2
            halfM = M / 2
        } else if(op == 5){
            for(i in 0 until halfN){
                for(j in 0 until halfM){
                    val temp = arr[i][j]
                    arr[i][j] = arr[halfN + i][j]
                    arr[halfN + i][j] = arr[halfN + i][halfM + j]
                    arr[halfN + i][halfM + j] = arr[i][halfM + j]
                    arr[i][halfM + j] = temp
                }
            }
        } else {
            for(i in 0 until halfN){
                for(j in 0 until halfM){
                    val temp = arr[i][j]
                    arr[i][j] = arr[i][halfM + j]
                    arr[i][halfM + j] = arr[halfN + i][halfM + j]
                    arr[halfN + i][halfM + j] = arr[halfN + i][j]
                    arr[halfN + i][j] = temp
                }
            }
        }
    }
    for(i in 0 until arr.size){
        for(j in 0 until arr[0].size){
            print("${arr[i][j]} ")
        }
        println()
    }
}