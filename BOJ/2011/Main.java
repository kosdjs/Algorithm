package BOJ_2011;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String code = br.readLine();
        int[] dp = new int[5001];
        int MOD = 1000000;

        if(code.charAt(0) == '0'){
            bw.write(0 + "\n");
        } else {
            dp[0] = 1;
            dp[1] = 1;
            for(int i=2; i<=code.length(); i++){
                if(code.charAt(i - 1) != '0'){
                    dp[i] += dp[i - 1] % MOD;
                }
                int t = ((code.charAt(i - 2) - '0') * 10) + (code.charAt(i - 1) - '0');
                if(t >= 10 && t <= 26){
                    dp[i] += dp[i - 2] % MOD;
                }
            }
            bw.write(dp[code.length()] % MOD + "\n");
        }
        bw.flush();
        bw.close();
    }
}
