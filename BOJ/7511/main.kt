package BOJ_7511

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val t = nextInt()
    val sb = StringBuilder()
    for(i in 1..t){
        sb.append("Scenario $i:\n")
        val n = nextInt()
        val k = nextInt()
        val root = IntArray(n){it}
        fun find(x: Int): Int{
            if(root[x] != x){
                root[x] = find(root[x])
            }
            return root[x]
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
        repeat(k){
            val a = nextInt()
            val b = nextInt()
            union(a, b)
        }
        val m = nextInt()
        repeat(m){
            val u = nextInt()
            val v = nextInt()
            sb.append(if(find(u) == find(v)) 1 else 0).append("\n")
        }
        sb.append("\n")
    }
    print(sb)
}