package BOJ_10986

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.HashMap
import java.util.StringTokenizer

fun main(args: Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val A = IntArray(N)
    val sum = LongArray(N){0}
    val map = HashMap<Int, Int>()
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        A[i] = st.nextToken().toInt()
    }
    sum[0] = A[0].toLong()
    val tempMod = (sum[0] % M.toLong()).toInt()
    map[tempMod] = 1
    for(i in 1 until N){
        sum[i] = sum[i-1] + A[i].toLong()
        val mod = (sum[i] % M.toLong()).toInt()
        if(map.containsKey(mod)){
            map[mod] = map[mod]!! + 1
        } else {
            map[mod] = 1
        }
    }
    var answer : Long = 0
    if(map.containsKey(0)){
        answer += map[0]!!.toLong()
    }
    for(key in map.keys){
        answer += map[key]!!.toLong() * (map[key]!! - 1).toLong() / 2
    }
    println(answer)
}