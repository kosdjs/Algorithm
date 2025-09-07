package BOJ_20495

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    val N = br.readLine().toInt()
    val bw = System.out.bufferedWriter()
    val minArr = IntArray(N)
    val maxArr = IntArray(N)
    val curMin = IntArray(N)
    val arrMax = IntArray(N)
    for(i in 0 until N){
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        minArr[i] = a - b
        maxArr[i] = a + b
        curMin[i] = a - b
        arrMax[i] = a + b
    }
    minArr.sort()
    maxArr.sort()
    for(i in 0 until N){
        var left = 0
        var right = N - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(maxArr[mid] >= curMin[i]){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        bw.write("${left + 1} ")
        left = 0
        right = N - 1
        while(left <= right){
            val mid = (left + right) / 2
            if(minArr[mid] > arrMax[i]){
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        bw.write("${right + 1}\n")
    }
    bw.flush()
    bw.close()
}