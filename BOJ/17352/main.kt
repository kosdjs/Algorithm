package BOJ_17352

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val root = IntArray(N + 1){it}
    fun find(x: Int): Int{
        if(root[x] == x){
            return x
        } else {
            root[x] = find(root[x])
            return root[x]
        }
    }
    fun union(a: Int, b: Int){
        val aFind = find(a)
        val bFind = find(b)
        if(aFind < bFind){
            root[bFind] = aFind
        } else {
            root[aFind] = bFind
        }
    }
    repeat(N - 2){
        val u = nextInt()
        val v = nextInt()
        union(u, v)
    }
    for(i in 1..N){
        if(i == find(i)){
            print("$i ")
        }
    }
}