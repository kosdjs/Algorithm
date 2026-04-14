package BOJ_23740

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    class Route(var s: Int, var e: Int, var c: Int): Comparable<Route>{
        override fun compareTo(other: Route): Int {
            return this.s - other.s
        }
    }
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = Array<Route>(N){Route(0,0,0)}
    for(i in 0 until N){
        arr[i] = Route(nextInt(), nextInt(), nextInt())
    }
    arr.sort()
    val result = ArrayList<Route>()
    var r = arr[0]
    for(i in 1 until N){
        if(arr[i].s > r.e){
            result.add(r)
            r = arr[i]
        } else {
            r.e = maxOf(r.e, arr[i].e)
            r.c = minOf(r.c, arr[i].c)
        }
    }
    result.add(r)
    val sb = StringBuilder()
    sb.append(result.size).append("\n")
    for(route in result){
        sb.append(route.s).append(" ").append(route.e).append(" ").append(route.c).append("\n")
    }
    print(sb)
}