package BOJ_1774

import java.util.StringTokenizer
import kotlin.math.pow
import kotlin.math.sqrt

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val root = IntArray(N + 1){it}
    val node = Array<IntArray>(N + 1){IntArray(2)}
    for(i in 1..N){
        st = StringTokenizer(br.readLine())
        node[i][0] = st.nextToken().toInt()
        node[i][1] = st.nextToken().toInt()
    }
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        union(root, a, b)
    }
    val graph = ArrayList<DoubleArray>()
    for(i in 1..N){
        for(j in i + 1..N){
            if(find(root, i) != find(root, j)){
                graph.add(doubleArrayOf(i.toDouble(), j.toDouble(), area(node, i, j)))
            }
        }
    }
    graph.sortWith{ o1, o2 -> if (o1[2] < o2[2]) -1 else if (o1[2] == o2[2]) 0 else 1 }
    var answer = 0.0
    for(edge in graph){
        if(find(root, edge[0].toInt()) != find(root, edge[1].toInt())){
            answer += edge[2]
            union(root, edge[0].toInt(), edge[1].toInt())
        }
    }
    println("%.2f".format(answer))
}

fun area(node: Array<IntArray>, a: Int, b: Int): Double{
    var result = 0.0
    result += (node[a][0] - node[b][0]).toDouble().pow(2)
    result += (node[a][1] - node[b][1]).toDouble().pow(2)
    result = sqrt(result)
    return result
}

fun union(root: IntArray, a: Int, b: Int){
    val aFind = find(root, a)
    val bFind = find(root, b)
    if(aFind < bFind){
        root[bFind] = aFind
    } else if(aFind > bFind){
        root[aFind] = bFind
    }
}

fun find(root: IntArray, idx: Int): Int{
    if(root[idx] == idx){
        return root[idx]
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}