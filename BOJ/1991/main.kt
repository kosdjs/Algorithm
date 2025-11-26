package BOJ_1991

import java.io.StreamTokenizer

class Node(val c: Char){
    var left: Node? = null
    var right: Node? = null
}

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    wordChars('A'.code, 'Z'.code)
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    fun nextChar(): Char{
        val tokenType = nextToken()
        return if(tokenType == StreamTokenizer.TT_WORD) sval[0] else '.'
    }
    val N = nextInt()
    fun find(cur: Node, c: Char): Node?{
        var result: Node? = null
        if(cur.c == c){
            result = cur
        }
        if(cur.left != null){
            val left = find(cur.left!!, c)
            if(left != null){
                result = left
            }
        }
        if(cur.right != null){
            val right = find(cur.right!!, c)
            if(right != null){
                result = right
            }
        }
        return result
    }
    val root = Node('A')
    repeat(N){
        val cur = find(root, nextChar())!!
        val left = nextChar()
        if(left != '.'){
            cur.left = Node(left)
        }
        val right = nextChar()
        if(right != '.'){
            cur.right = Node(right)
        }
    }
    val bw = System.out.bufferedWriter()
    fun preorder(cur: Node){
        bw.append(cur.c)
        if(cur.left != null) preorder(cur.left!!)
        if(cur.right != null) preorder(cur.right!!)
    }
    fun inorder(cur: Node){
        if(cur.left != null) inorder(cur.left!!)
        bw.append(cur.c)
        if(cur.right != null) inorder(cur.right!!)
    }
    fun postorder(cur: Node){
        if(cur.left != null) postorder(cur.left!!)
        if(cur.right != null) postorder(cur.right!!)
        bw.append(cur.c)
    }
    preorder(root)
    bw.newLine()
    inorder(root)
    bw.newLine()
    postorder(root)
    bw.flush()
    bw.close()
}