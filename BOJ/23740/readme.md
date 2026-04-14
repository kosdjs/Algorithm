# 백준 23740번: 버스 노선 개편하기

> 문제: https://www.acmicpc.net/problem/23740

### 문제 풀이

스위핑

Route(s, e, c) = 시작점 s, 끝 점 e, 비용 c인 버스 노선

arr = N 개의 버스 노선을 오름차순으로 정렬한 배열

result = 개편 이후 남은 버스 노선들

r = 이전 노선까지 합친 개편된 노선

N 개의 버스 노선을 입력받으면 Route(s, e, c) 클래스로 만들어 배열 arr에 저장하고 오름차순으로 정렬함

그 이후에 개편 이후의 노선을 저장할 ArrayList를 result로 정의하고 개편을 하기 위해 배열의 첫 노선 arr[0]을 r에 대입함

그 이후에 i를 1부터 N 미만까지 반복하며 arr[i]의 시작점 s가 r의 구간 내인지 확인해 해당 노선이 개편할때 r과 합쳐지는지 확인함

구간 내로 겹친다면 r을 arr[i]와 합쳐야 하므로 r의 끝 점 r.e와 arr[i]의 끝 점 arr[i].e 중 최댓값을 r.e에 저장해 합친 구간을 저장하고 합쳤을 때의 최소 비용을 r.c에 저장하기 위해 r.c와 arr[i].c 중 최솟값을 저장함

구간 내로 겹치지 않는다면 r에 저장된 노선은 더 이상 개편할 여지가 없으므로 result에 r을 저장하고 현재 노선 arr[i]가 다음 노선부터 겹치는지 확인해야 하므로 r에 arr[i]를 대입해줌

반복문이 끝나면 마지막 노선까지 합친 노선이 r에 저장되어 있으므로 해당 노선도 result에 추가함

그러면 개편 이후의 노선들이 result에 저장되어 있는 것이므로 result.size와 result에 저장된 노선들의 s, e, c를 출력해주면 정답

### 풀이 설명

N 개의 버스 노선의 시작점 S, 끝 점 E, 비용 C가 주어질 때 두 버스 노선이 한 점 이상에서 겹치면 두 구간을 합치고 비용을 더 낮은 비용을 챙기는 새로운 노선을 만들어 버스 노선을 개편한다고 한다. 개편이 끝났을 때 버스 노선의 개수와 각 노선의 시작점, 끝 점, 비용을 구하는 문제이다.

두 노선이 한 점 이상에서 겹친다는 것은 한 노선의 시작점 또는 끝 점이 다른 노선의 시작점과 끝 점의 구간에 들어간다는 것이다. 또한 노선의 점이 정수로 주어지고 시작점이 항상 끝 점보다 작으므로 노선을 시작점을 기준으로 정렬하면 인접한 노선이 해당 노선과 가장 시작점이 가까운 노선이기 때문에 겹칠 확률이 높은 노선이 된다.

그러므로 N 개의 버스 노선을 시작점을 기준으로 오름차순으로 정렬한 이후 노선을 순서대로 살펴보면서 현재 노선이 이전 노선과 겹치는지 확인해 겹친다면 해당 노선까지 구간을 합친 새로운 노선을 다음 노선과 겹치는지 확인하면 되고, 겹치지 않는다면 이전까지 합쳐놓은 노선이 개편이 끝난 노선이기 때문에 해당 노선을 개편 이후의 노선으로 저장하면 된다.

이에 따라 노선 정보를 저장하기 위해 클래스 Route(s, e, c)를 정의하고 시작점을 기준으로 정렬하기 위해 Comparable 인터페이스를 상속받아 시작점 s를 기준으로 비교하도록 만든다. 그 이후엔 이 클래스를 이용해 N 개의 노선 정보를 입력받아 배열 arr에 저장하고 이 배열을 오름차순으로 정렬한다.

그 이후에 개편된 노선들의 개수를 아직 모르기 때문에 ArrayList 형식으로 개편된 노선을 저장하기 위해 result를 정의한다. 그 이후에 정렬된 배열 arr에서 노선을 하나씩 확인하면서 구간이 겹치는지 확인하기 위해 현재까지 개편한 노선 r에다가 배열에 저장된 첫 노선 arr[0]을 저장한다.

배열 arr에서 arr[1]부터 arr[N - 1]까지의 노선이 개편할 때 어떻게 되는지 확인하기 위해 인덱스 i를 1부터 N 미만까지 반복하며 arr[i]와 r을 비교해 arr[i]의 시작점 arr[i].s가 r.e보다 크다면 arr[i]가 현재 노선 r과 개편할 때 겹치는 노선이 아니기 때문에 r을 result에 저장하고 현재 노선 arr[i]가 다음 노선과 겹치는지 확인해야 하므로 r에다가 arr[i]를 대입해서 저장하면 된다.

만약 arr[i].s가 r.e보다 작거나 같다면 현재 노선을 개편할 때 겹치는 노선이므로 현재까지 겹친 노선의 끝 점이 더 길어지는지 확인하기 위해 r.e와 arr[i].e 중 최댓값을 r.e에 저장하고, 개편 이후의 비용은 두 노선 중 더 저렴한 비용이 된다고 했으므로 r.c와 arr[i].c 중 최솟값을 r.c에 저장해 두 노선을 합치면 된다.

그렇게 반복문이 끝나면 아직 마지막 노선까지 합친 노선 r이 result에 저장되지 않았으므로 result에 r을 추가해주면 된다. 그러면 개편 이후의 노선이 result에 저장되어 있으므로 개편 이후의 노선의 개수 result.size와 result에 저장된 노선 route마다 route.s, route.e, route.c를 출력해주면 정답이 된다.

### 소스 코드
```kotlin
import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    class Route(var s: Int, var e: Int, var c: Int): Comparable<Route>{
        override fun compareTo(other: Route): Int {
            return this.s - other.s
        }
    }
    fun nextInt(): Int{
        nextToken()
        return nval.toInt()
    }
    val N = nextInt()
    val arr = Array<Route>(N){Route(0,0,0)}
    for(i in 0 until N){
        arr[i] = Route(nextInt(), nextInt(), nextInt())
    }
    arr.sort()
    val result = ArrayList<Route>()
    var r = arr[0]
    for(i in 1 until N){
        if(arr[i].s > r.e){
            result.add(r)
            r = arr[i]
        } else {
            r.e = maxOf(r.e, arr[i].e)
            r.c = minOf(r.c, arr[i].c)
        }
    }
    result.add(r)
    val sb = StringBuilder()
    sb.append(result.size).append("\
")
    for(route in result){
        sb.append(route.s).append(" ").append(route.e).append(" ").append(route.c).append("\
")
    }
    print(sb)
}
```