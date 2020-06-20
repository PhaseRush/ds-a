package main.misc

// todo: http://www.luschny.de/math/factorial/FastFactorialFunctions.htm
private fun Int.factorial(): Long {
    return (2..this.toLong()).reduce(Long::times)
}

// https://www.reddit.com/r/UBC/comments/hcacoz/has_anyone_interviewed_with_sap_before/fve1ez9?utm_source=share&utm_medium=web2x
fun main() {
    val input = readLine()!!
    val evenFreqMap = input.groupingBy { it }.eachCount()
            .filterValues { freq -> freq % 2 == 0 }

    println("At least one palindrome: " +
            evenFreqMap.any())
    println("Number of possible palindromes: " +
            evenFreqMap.count().factorial())
}