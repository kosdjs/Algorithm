# 백준 2671번: 잠수함식별

> 문제: https://www.acmicpc.net/problem/2671

### 문제 풀이

문자열, 정규표현식

입력받은 문자열 s를 (100+1+|01)+의 정규표현식으로 검사해 일치한다면 SUBMARINE, 아니면 NOISE를 출력하면 정답

isSubmarine = 잠수함 엔진 소리가 맞는지 여부

idx = 현재 패턴을 확인해야 하는 문자열 s의 인덱스

문자열 s의 인덱스 idx를 0부터 s.length보다 작을때까지 반복해 확인하며 문자 0이 오면 01이 되는지 확인해 맞다면 인덱스 2를 증가시키고 아니면 isSubmarine에 false를 대입하고 반복문을 탈출함

현재 idx가 가리키는 문자가 1이라면 100~1~인지 확인해야 하므로 뒤에 두 문자가 0인지 확인하고 그 뒤에 연속된 모든 0은 패턴에 포함되니 그 다음 문자에 1이 오는지 확인함

연속된 1을 확인하는 과정에서 다음 패턴 100~1~의 첫 1이 포함될 수 있으므로 마지막 1에 대해 뒤의 두 문자가 0이고 그 앞 문자가 1인지 확인함

위에 확인하는 과정 중 하나라도 조건에 맞지 않으면 isSubmarine에 false를 대입해주고 반복문을 탈출함

이렇게 모든 문자에 대해 패턴을 확인하면 isSubmarine에 문자열이 잠수함 엔진 소리가 맞는지 여부가 저장되므로 이에 따라 SUBMARINE, NOISE를 출력해주면 정답

### 풀이 설명

0과 1로 이루어진 문자열이 하나 주어졌을 때 그 문자열이 문제에서 제공하는 패턴과 일치하는지 검사하는 문제이다.

먼저 간단하게 푸는 방법은 정규표현식을 이용해 패턴을 검사하는 방법이 있다. 문제에서 반복을 표시하는 ~을 제외하면 정규표현식의 문법을 그대로 소개하고 있으므로 ~를 +로 변경하면 그대로 정규표현식이 된다.

이에 따라 정규표현식으로 주어진 문장을 검사해 일치하면 SUBMARINE, 아니면 NOISE를 출력하면 정답이 된다.

처음 생각한 방법으로는 문자열이 0과 1로만 이루어진 간단한 상태이고 문자열의 길이가 최대 150이므로 직접 앞에서부터 패턴을 검사하기 위해 인덱스로 문자를 하나하나 검사한다.

현재 인덱스의 문자가 0이라면 뒤의 문자가 1인지 검사해 01의 패턴인지 검사해 일치하면 인덱스를 2 증가시켜 다음 문자부터가 패턴인지 확인한다. 일치하지 않으면 패턴이 일치하지 않는 것이므로 더 이상 검사할 필요가 없다.

만약 현재 인덱스의 문자가 1이라면 패턴 100~1~인지 확인해야 하므로 뒤의 두 문자가 0이 맞는지 확인하면 된다. 그 이후에 0~에 따라 이어진 모든 0을 확인하고 그 뒤에 1~이 오는지 확인해야 한다.

1~을 확인하는 과정 중 1인 모든 인덱스를 넘기다 보면 다시 100~1~의 패턴의 맨 앞 1을 넘길수도 있다. 그러므로 마지막 1을 확인한다면 그 뒤에 0이 두개가 있고 그 앞에 1이 있는지 확인해서 현재 100~1~ 뒤에 따로 붙은 1이 존재하는지 구분하면 된다.

이렇게 인덱스 0부터 쭉 확인하며 패턴에 맞는지 확인하고 한 번이라도 패턴에 맞지 않는 문자가 온다면 NOISE, 모든 문자가 패턴에 맞는다면 SUBMARINE을 출력해주면 정답이 된다.

### 소스 코드
```kotlin
fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    print(if (s.matches(Regex("(100+1+|01)+"))) "SUBMARINE" else "NOISE")
}
```

```kotlin
fun main() = System.`in`.bufferedReader().run {
    val s = readLine()
    var isSubmarine = true
    var idx = 0
    while(idx < s.length){
        if(s[idx] == '0'){//01
            if(idx + 1 < s.length && s[idx + 1] == '1') idx += 2
            else {
                isSubmarine = false
                break
            }
        }
        else if (s[idx] == '1'){//100~1~
            if(idx + 2 < s.length && s[idx + 1] == '0' && s[idx + 2] == '0'){
                idx += 2
                while(idx < s.length && s[idx] == '0'){
                    idx++
                }
                if(idx < s.length && s[idx] == '1'){
                    while (idx < s.length && s[idx] == '1'){
                        idx++
                    }
                    if(idx + 1 < s.length && s[idx + 1] == '0' && s[idx - 2] == '1') idx--
                } else {
                    isSubmarine = false
                    break
                }
            } else {
                isSubmarine = false
                break
            }
        }
    }
    print(if (isSubmarine) "SUBMARINE" else "NOISE")
}
```