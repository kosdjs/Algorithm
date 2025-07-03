package BOJ_4386

import java.util.ArrayList
import java.util.StringTokenizer
import kotlin.math.pow
import kotlin.math.sqrt

fun main(){
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val root = IntArray(n){it}
    val node = Array(n){DoubleArray(2)}
    for(i in 0 until n){
        val st = StringTokenizer(br.readLine())
        node[i][0] = st.nextToken().toDouble()
        node[i][1] = st.nextToken().toDouble()
    }
    val graph = ArrayList<DoubleArray>()
    for(i in 0 until n - 1){
        for(j in i+1 until n){
            graph.add(doubleArrayOf(i.toDouble(), j.toDouble(), distance(node, i, j)))
        }
    }
    graph.sortWith{o1, o2 -> (o1[2] - o2[2]).toInt()}
    var count = 0
    var answer = 0.0
    for(edge in graph){
        if(count == n - 1) break
        val a = edge[0].toInt()
        val b = edge[1].toInt()
        if(find(root, a) != find(root, b)){
            union(root, a, b)
            answer += edge[2]
            count++
        }
    }
    println(String.format("%.2f", answer))
}

fun distance(node: Array<DoubleArray>, i: Int, j: Int): Double{
    var result = 0.0
    if(node[i][0] > node[j][0]){
        result += (node[i][0] - node[j][0]).pow(2)
    } else {
        result += (node[j][0] - node[i][0]).pow(2)
    }

    if(node[i][1] > node[j][1]){
        result += (node[i][1] - node[j][1]).pow(2)
    } else {
        result += (node[j][1] - node[i][1]).pow(2)
    }
    return sqrt(result)
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