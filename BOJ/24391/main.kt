package BOJ_24391

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val M = nextInt()
    val root = IntArray(N + 1){it}
    fun find(x: Int): Int{
        if(root[x] != x) root[x] = find(root[x])
        return root[x]
    }
    fun union(x: Int, y:Int){
        val xFind = find(x)
        val yFind = find(y)
        if(xFind < yFind){
            root[yFind] = xFind
        } else {
            root[xFind] = yFind
        }
    }
    repeat(M){
        val i = nextInt()
        val j = nextInt()
        union(i, j)
    }
    var prev = nextInt()
    var count = 0
    repeat(N - 1){
        val cur = nextInt()
        if(find(prev) != find(cur)){
            count++
        }
        prev = cur
    }
    println(count)
}