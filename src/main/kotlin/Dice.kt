package org.example

import kotlin.random.Random

class Dice {
    private var randomSeed: Int = Random.nextInt(40000)

    fun roll(): Int {
        val rolledNumber = Random(randomSeed).nextInt(6) + 1
        randomSeed = Random.nextInt(40000)
        return rolledNumber
    }
}