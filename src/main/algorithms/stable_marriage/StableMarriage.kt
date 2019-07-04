package stable_marriage

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*
import java.util.Arrays.asList
import java.util.stream.Collectors
import java.util.stream.IntStream
import kotlin.collections.HashMap

fun main() {
    val n = 3 // CHANGE THIS NUMBER FOR DIFFERENT SIZE TEST CASES
    val random = Random("2133".hashCode().toLong()) //use seed for reproducibility
    val men = init(n, "m")
    val women = init(n, "w")


    val shuffledMen = ArrayList(men) // these two temp lists is my lazy way of
    val shuffledWomen = ArrayList(women) // emulating python's random.sample
    for (i in 0 until n) {
        shuffledMen.shuffle(random)
        shuffledWomen.shuffle(random)
        men[i].prefs = LinkedList(shuffledWomen)
        women[i].prefs = LinkedList(shuffledMen)
        println(String.format("%s: [%s]\t%s: [%s]",
                men[i],
                men[i].prefs!!.stream().map { p -> p.id }.collect(Collectors.joining(" ")),
                women[i],
                women[i].prefs!!.stream().map { p -> p.id }.collect(Collectors.joining(" "))))
    }
    prettyPrint(stableMarriage(men))
}

/**
 * generates stable pairs of men -> women
 * note: we do not need the women for this function because all the information regarding women can be
 * generated / found from the men
 *
 * @param men input list of men
 * @return Bi directional hashmap of men -> women
 */
private fun stableMarriage(men: List<Person>): Map<Person, Person> {
    val pairs = HashMap<Person, Person>() // BiHashMap representing couples. Keys are men
    val todo = LinkedList(men) // unengaged men

    while (todo.isNotEmpty()) {
        val man = todo.pop()

        while (man.prefs!!.isNotEmpty()) {
            val woman = man.prefs!!.pop()
            if (woman.engagedTo == null) { // not engaged
                man.engagedTo = woman
                woman.engagedTo = man
                pairs[man] = woman
            } else if (woman.prefRank(man) < woman.prefRank(woman.engagedTo)) {
                val otherMan = woman.engagedTo

                otherMan!!.engagedTo = null // set other man to not engaged and push to unengaged
                todo.addLast(otherMan)
                pairs.remove(otherMan)

                man.engagedTo = woman // set the new pair
                woman.engagedTo = man
                pairs[man] = woman
            }

            if (man.engagedTo != null) break // man engaged
        }
    }

    return pairs
}

/**
 * Util function for printing generated pairs
 * @param pairs input map
 */
private fun prettyPrint(pairs: Map<Person, Person>) {
    pairs.forEach { (k, v) -> print(String.format("(%s : %s)\t", k, v)) }
    println()
}


/**
 * Util function for initializing lists of people
 *
 * @param n desired size
 * @param c prefix character
 * @return List of Person, each person having id = "c" + ${idx}
 */
private fun init(n: Int, c: String): List<Person> {
    return IntStream.range(0, n)
            .mapToObj { i -> Person(c + i) }
            .collect(Collectors.toList())
}

internal class Person(val id: String // includes gender and "index"
) {
    var prefs: LinkedList<Person>? = null // need linked list because want pop and indexof
    var engagedTo: Person? = null

    fun prefRank(other: Person?): Int {
        return prefs!!.indexOf(other)
    }

    override fun toString(): String {
        return id // so printing doesnt look dumb
    }
}


class Tests {

    @Test
    fun `test inits for men and women`() {
        val men = listOf(Person("m0"), Person("m1"), Person("m2"))
        init(3, "m").forEachIndexed { i, v -> assertEquals(men[i].id, v.id) }

        val women = listOf(Person("w0"), Person("w1"), Person("w2"))
        init(3, "w").forEachIndexed { i, v -> assertEquals(women[i].id, v.id) }
    }

    @Test
    fun `stableMarriage 3 trivial case`() {
        val men = init(3, "m")
        val women = init(3, "w")

        men[0].prefs = LinkedList(asList<Person>(women[0], women[1], women[2]))
        men[1].prefs = LinkedList(asList<Person>(women[1], women[2], women[0]))
        men[2].prefs = LinkedList(asList<Person>(women[2], women[0], women[1]))
        women[0].prefs = LinkedList(asList<Person>(men[0], men[1], men[2]))
        women[1].prefs = LinkedList(asList<Person>(men[1], men[2], men[0]))
        women[2].prefs = LinkedList(asList<Person>(men[2], men[0], men[1]))


        val pairs = HashMap<Person, Person>()
        pairs[men[0]] = women[0]
        pairs[men[1]] = women[1]
        pairs[men[2]] = women[2]

        assertEquals(pairs, stableMarriage(men))
    }

    @Test
    fun `stableMarriage 3 non trivial`() {
        val men = init(3, "m")
        val women = init(3, "w")

        men[0].prefs = LinkedList(asList<Person>(women[0], women[1], women[2]))
        men[1].prefs = LinkedList(asList<Person>(women[2], women[1], women[0]))
        men[2].prefs = LinkedList(asList<Person>(women[2], women[0], women[1]))
        women[0].prefs = LinkedList(asList<Person>(men[0], men[1], men[2]))
        women[1].prefs = LinkedList(asList<Person>(men[1], men[2], men[0]))
        women[2].prefs = LinkedList(asList<Person>(men[1], men[0], men[2]))


        val pairs = HashMap<Person, Person>()
        pairs[men[0]] = women[0]
        pairs[men[1]] = women[2]
        pairs[men[2]] = women[1]

        assertEquals(pairs, stableMarriage(men))
    }
}
