# 20186번: 수 고르기

문제: [https://www.acmicpc.net/problem/20186](https://www.acmicpc.net/problem/20186)

점수를 높게 받으려면 고른 수에서 왼쪽에 고른 수가 몇 개 있는지 빼기 때문에 최대한 큰 수를 고르는 것이 좋다.

숫자들을 받고 배열을 내림차순으로 정렬한 후에 배열의 앞에서 하나 씩 고르면서 앞의 수의 개수를 빼준다.

내림차순으로 정렬하면 순서가 달라지기 때문에 실제 순서에서 하나하나 계산하면 빼는 개수가 달라지기 때문에 실제 점수가 달라지지만 문제에서 구해야 하는 것은 점수의 총합이기 때문에 순서에 상관없이 빼는 개수의 총합은 정해져 있다.

예를 들어 3개를 뽑는다고 생각하면 첫번째 수는 앞에 고른 수가 없기 때문에 빼는 값이 0이고, 두 번째는 1, 세 번째는 2가 될것이다.

빼는 수의 총합을 수식으로 표현하면 결국 1부터 K-1까지의 총 합이기 때문에

$$
\sum_{N=1}^{K-1}N = \frac{K(K+1)}{2}
$$

이런 수식이 나오게 된다.