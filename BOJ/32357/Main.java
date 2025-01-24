package BOJ_32357;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int palindrome = 0;
        for(int i=0; i<N; i++){
            String s = br.readLine();
            int index = 0;
            boolean flag = true;
            while(index < s.length() / 2){
                if(s.charAt(index) == s.charAt(s.length() - 1 - index)){
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
                index++;
            }
            if(flag){
                palindrome++;
            }
        }
        int result = palindrome * (palindrome - 1);
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }
}
