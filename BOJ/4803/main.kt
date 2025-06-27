package BOJ_4803

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var case = 1
    while(true){
        var st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        if(n == 0 && m == 0){
            break
        }
        var count = 0
        val root = IntArray(n + 1){it}
        for(i in 1..m){
            st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            union(root, a, b)
        }
        for(i in 1..n){
            if(find(root, i) == i){
                count++
            }
        }
        if(count == 0){
            println("Case ${case}: No trees.")
        } else if(count == 1){
            println("Case ${case}: There is one tree.")
        } else {
            println("Case ${case}: A forest of ${count} trees.")
        }
        case++
    }
}

fun union(root: IntArray, a: Int, b: Int){
    val aFind = find(root, a)
    val bFind = find(root, b)
    if(aFind == bFind){
        root[aFind] = 0
        root[bFind] = 0
    } else {
        if(aFind < bFind){
            root[bFind] = aFind
        } else {
            root[aFind] = bFind
        }
    }
}

fun find(root: IntArray, idx: Int): Int{
    if(root[idx] == idx){
        return idx
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}