package BOJ_5639

import java.io.StreamTokenizer

class Node(val value: Int){
    var left: Node? = null
    var right: Node? = null
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun nextInt(): Int?{
        if(nextToken() != StreamTokenizer.TT_EOF){
            return nval.toInt()
        } else {
            return null
        }
    }
    var num = nextInt()
    val list = ArrayList<Int>()
    while(num != null){
        list.add(num)
        num = nextInt()
    }
    fun buildBST(start: Int, end: Int): Node? {
        if(start > end){
            return null
        }
        val curValue = list[start]
        val cur = Node(curValue)
        var rightSubTreeStart = start + 1
        while (rightSubTreeStart <= end && list[rightSubTreeStart] < curValue){
            rightSubTreeStart++
        }
        cur.left = buildBST(start + 1, rightSubTreeStart - 1)
        cur.right = buildBST(rightSubTreeStart, end)
        return cur
    }
    val root = buildBST(0, list.size - 1)
    val bw = System.out.bufferedWriter()
    fun postTraversal(cur: Node?){
        if(cur == null){
            return
        }
        postTraversal(cur.left)
        postTraversal(cur.right)
        bw.write(cur.value.toString())
        bw.newLine()
    }
    postTraversal(root)
    bw.flush()
    bw.close()
}