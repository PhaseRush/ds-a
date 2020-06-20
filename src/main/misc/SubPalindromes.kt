package main.misc

// https://www.reddit.com/r/UBC/comments/hcacoz/has_anyone_interviewed_with_sap_before/fve1ez9?utm_source=share&utm_medium=web2x
fun main() {
    // todo: http://www.luschny.de/math/factorial/FastFactorialFunctions.htm
    fun Int.factorial(): Long = (2..this.toLong()).reduce(Long::times)

    val input = readLine()!!
    val freqMap = input.groupingBy { it }.eachCount()

    println("Number of possible palindromes: " +
            freqMap.filterValues { freq -> freq % 2 == 0 }
                    .count()
                    .factorial()
                    .times(freqMap.filterValues { freq -> freq % 2 == 1 }
                            .count() + 1))
}