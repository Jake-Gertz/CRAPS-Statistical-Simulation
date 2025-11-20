package crapsSimulation

import kotlin.random.Random

class Dice {
    private var randomSeed: Int = Random.nextInt(40000)
    private val rand = Random(randomSeed)

    fun roll(): Int {
        val rolledNumber = rand.nextInt(1,7)
        return rolledNumber
    }
}