package BOJ_1939

import java.util.StringTokenizer

fun main(){
    val br = System.`in`.bufferedReader()
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val root = IntArray(N+1){it}
    val bridge = ArrayList<IntArray>()
    for(i in 1..M){
        st = StringTokenizer(br.readLine())
        val A = st.nextToken().toInt()
        val B = st.nextToken().toInt()
        val C = st.nextToken().toInt()
        bridge.add(intArrayOf(A, B, C))
    }
    st = StringTokenizer(br.readLine())
    var answer = 0
    val start = st.nextToken().toInt()
    val end = st.nextToken().toInt()
    bridge.sortWith {o1, o2 -> o2[2] - o1[2]}
    for(b in bridge){
        union(root, b[0], b[1])
        if(find(root, start) == find(root, end)){
            answer = b[2]
            break
        }
    }
    println(answer)
}

fun union(root: IntArray, a: Int, b:Int){
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
        return idx
    } else {
        root[idx] = find(root, root[idx])
        return root[idx]
    }
}