package BOJ_1918

fun main(){
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    val infix = br.readLine()
    val stack = CharArray(infix.length)
    var top = -1
    for(c in infix){
        if(c in 'A'..'Z'){
            bw.append(c)
        } else {
            if(c == '+' || c == '-'){
                if(top >= 0){
                    while(top >= 0 && stack[top] != '('){
                        bw.append(stack[top--])
                    }
                }
                stack[++top] = c
            } else if(c == '*' || c == '/'){
                if(top >= 0){
                    while(top >= 0 && (stack[top] == '*' || stack[top] == '/')){
                        bw.append(stack[top--])
                    }
                }
                stack[++top] = c
            } else if(c == '('){
                stack[++top] = c
            } else {
                while(top >= 0 && stack[top] != '('){
                    bw.append(stack[top--])
                }
                top--
            }
        }
    }
    while(top >= 0){
        bw.append(stack[top--])
    }
    bw.flush()
    bw.close()
}