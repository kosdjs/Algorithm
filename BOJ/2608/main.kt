package BOJ_2608

fun main(){
    fun toNumber(s: String): Int {
        var result = 0
        var idx = 0

        fun value(romeNum: Char) = when (romeNum) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> 0
        }

        while (idx < s.length) {
            val romeNum = s[idx]
            val v1 = value(romeNum)
            if (idx + 1 < s.length) {
                val nextRomeNum = s[idx + 1]
                val v2 = value(nextRomeNum)
                if (v1 < v2) {
                    result += (v2 - v1)
                    idx += 2
                    continue
                }
            }
            result += v1
            idx++
        }
        return result
    }
    val num = toNumber(readln()) + toNumber(readln())
    println(num)
    fun toRomeNumber(n: Int): String {
        var x = n
        val sb = StringBuilder()

        val nums = intArrayOf(
            1000, 900, 500, 400,
            100, 90, 50, 40,
            10, 9, 5, 4, 1
        )
        val romes = arrayOf(
            "M", "CM", "D", "CD",
            "C", "XC", "L", "XL",
            "X", "IX", "V", "IV", "I"
        )

        for (i in nums.indices) {
            while (x >= nums[i]) {
                x -= nums[i]
                sb.append(romes[i])
            }
        }
        return sb.toString()
    }
    println(toRomeNumber(num))
}