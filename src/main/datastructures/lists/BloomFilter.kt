package main.datastructures.lists

import java.util.*
import kotlin.math.log
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.round
import kotlin.sequences.asSequence as pleaseStreamThis

class BloomFilter<T> /*@JvmOverloads constructor*/(
        private val numElements: Int = 25,
        private val containerSize: Int = 1024 * 1024 * 8
) {
    val falsePositiveProb: Double = 1 - Math.E.pow(-(log(2.toDouble(), Math.E).pow(2)) * containerSize / numElements)
    private val numHashes: Int = max(round(log(2.toDouble(), Math.E) * containerSize / numElements).toInt(), 1)
    private val boundedRandom: BoundedRandom = BoundedRandom(containerSize, numHashes)
    private val hashes: BitSet = BitSet(containerSize)


    fun add(element: T) = boundedRandom.init(element).forEach { r -> hashes.set(r.currValue) }


    // ty dannie
    fun contains(element: T): Boolean = boundedRandom.init(element).pleaseStreamThis()
            .map { rand -> hashes.get(rand.currValue) }
            .any { res -> res == false }
            .not()


    fun containsAll(elements: Collection<T>): Boolean = elements.all(this::contains)


    fun isEmpty(): Boolean = hashes.toByteArray()
            .map { byte -> byte == 1.toByte() }
            .any()
            .not()


    /**
     * https://en.wikipedia.org/wiki/Bloom_filter#Approximating_the_number_of_items_in_a_Bloom_filter
     */
    fun approxElements(): Int {
        val ones = hashes.toByteArray().count { b -> b == 1.toByte() }
        return (-hashes.size() / numHashes.toDouble() * log(1 - (ones / hashes.size().toDouble()), Math.E)).toInt()
    }

    fun clear() = hashes.clear()


    // simplified this thanks to xaanit and alpha;helix
    // dannie https://discordapp.com/channels/208023865127862272/208023865127862272/723621182938808451
    override fun equals(other: Any?): Boolean =
            (other as? BloomFilter<*>)?.let { filter ->
                hashes == filter.hashes && numHashes == filter.numHashes
            } ?: false


    override fun hashCode(): Int = hashes.hashCode().xor(numHashes)


    class BoundedRandom(
            private val max: Int,
            private val totalCount: Int,
            private val rng: Random = Random()
    ) : Iterator<BoundedRandom>, Iterable<BoundedRandom> {
        var genCount: Int = 0
        var currValue: Int = -1

        fun <T> init(t: T): BoundedRandom {
            rng.setSeed(t.hashCode().toLong())
            return this
        }

        override fun iterator(): Iterator<BoundedRandom> {
            genCount = 0
            return this
        }

        override fun hasNext(): Boolean {
            return genCount < totalCount
        }

        override fun next(): BoundedRandom {
            genCount++
            currValue = rng.nextInt(max)
            return this
        }

    }
}