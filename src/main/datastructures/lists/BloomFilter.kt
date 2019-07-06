package main.datastructures.lists

import java.util.*
import kotlin.math.log
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.round

class BloomFilter<T> /*@JvmOverloads constructor*/(
        private val numElements: Int = 25,
        private val containerSize: Int = 1024 * 1024 * 8
) {
    val falsePositiveProb: Double = 1 - Math.E.pow(-(log(2.toDouble(), Math.E).pow(2)) * containerSize / numElements)
    private val numHashes: Int = max(round(log(2.toDouble(), Math.E) * containerSize / numElements).toInt(), 1)
    private val boundedRandom: BoundedRandom = BoundedRandom(containerSize, numHashes)
    private val hashes: BitSet = BitSet(containerSize)


    fun add(element: T) {
        boundedRandom.init(element).forEach { r -> hashes.set(r.currValue) }
    }

    fun contains(element: T): Boolean {
        boundedRandom.init(element)
        for (r in boundedRandom) {
            if (!hashes.get(r.currValue)) return false
        }
        return true
    }

    fun containsAll(elements: Collection<T>): Boolean {
        for (e in elements) {
            if (!contains(e)) return false
        }
        return true
    }

    fun isEmpty(): Boolean {
        for (b in hashes.toByteArray()) {
            if (b == 1.toByte()) return false
        }
        return true
    }

    fun clear() {
        hashes.clear()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BloomFilter<*>

        return hashes == other.hashes && numHashes == other.numHashes
    }

    override fun hashCode(): Int {
        return hashes.hashCode().xor(numHashes)
    }


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