package BOJ_1339;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<N; i++){
            String s = br.readLine();
            int digit = 1;
            for (int j=s.length() - 1; j>=0; j--){
                char c = s.charAt(j);
                if(map.containsKey(c)){
                    int temp = map.get(c);
                    temp += digit;
                    map.replace(c, temp);
                } else {
                    map.put(c, digit);
                }
                digit *= 10;
            }
            //받은 문자열 자릿수 가져와서 저장하기
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2) - map.get(o1);
            }
        });
        //comparator 우선순위 자릿수 내림차순
        int answer = 0;
        int number = 9;
        for(int i=0; i<list.size(); i++){
            answer += map.get(list.get(i)) * number;
            number--;
        }
        System.out.println(answer);
    }
}
