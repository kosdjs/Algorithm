package BOJ_12782;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            String N = st.nextToken();
            String M = st.nextToken();
            int Ndiff = 0;
            int Mdiff = 0;
            int Ncount = 0; // 1의 개수
            int Mcount = 0;
            int index = 0;
            while(index < N.length()) {
                char n = N.charAt(index);
                char m = M.charAt(index);
                if(n == '1'){
                    Ncount++;
                    if(m == '0'){
                        Ndiff++;
                    }
                }
                if(m == '1'){
                    Mcount++;
                    if(n == '0'){
                        Mdiff++;
                    }
                }
                index++;
            }
            if(Ncount > Mcount){
                bw.write((Ncount - Mcount + Mdiff) + "\n");
            } else {
                bw.write((Mcount - Ncount + Ndiff) + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
