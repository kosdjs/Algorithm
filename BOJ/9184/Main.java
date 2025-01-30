package BOJ_9184;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][][] dp = new int[21][21][21];
        for(int a=0; a<21; a++){
            for(int b=0; b<21; b++){
                for(int c=0; c<21; c++){
                    if(a == 0 || b == 0 || c == 0){
                        dp[a][b][c] = 1;
                    } else if(a < b && b < c){
                        dp[a][b][c] = dp[a][b][c-1] + dp[a][b-1][c-1] - dp[a][b-1][c-1];
                    } else {
                        dp[a][b][c] = dp[a-1][b][c] + dp[a-1][b-1][c] + dp[a-1][b][c-1] - dp[a-1][b-1][c-1];
                    }
                }
            }
        }
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1 && c == -1){
                break;
            }
            if(a <= 0 || b <= 0 || c<= 0){
                bw.write("w(" + a + ", " + b + ", " + c + ") = " + 1 + "\n");
            } else if(a > 20 || b > 20 || c > 20){
                bw.write("w(" + a + ", " + b + ", " + c + ") = " + dp[20][20][20] + "\n");
            } else {
                bw.write("w(" + a + ", " + b + ", " + c + ") = " + dp[a][b][c] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
