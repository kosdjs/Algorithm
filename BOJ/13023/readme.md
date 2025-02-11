# 13023번: ABCDE

문제: [https://www.acmicpc.net/problem/13023](https://www.acmicpc.net/problem/13023)

dfs를 시행하면서 깊이가 5가 되는 경로가 있는지 찾는 문제

탐색할 때 한번 탐색한 점을 방문 처리하고 되돌리지 않는다면 답에 해당되는 경로를 찾지 못할 수 있음 그래서 다음 점을 탐색하는 dfs 이후에 visit을 다시 false로 바꿔주어야 함.