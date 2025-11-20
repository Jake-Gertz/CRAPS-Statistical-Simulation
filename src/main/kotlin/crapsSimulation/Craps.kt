package crapsSimulation

class Craps(private val numRolls: Int) {
    private var numberOfSevens: Int = 0
    private var longestRoll: Int = 0

    //Array format is all dice combos in the following order
    // 0: 1 1 | 1: 1 2 | 2: 1 3 | 3: 1 4 | 4: 1 5 | 5: 1 6 | 6: 2 2 | 7: 2 3 | 8: 2 4 | 9: 2 5 | 10: 2 6
    // 11: 3 3 | 12: 3 4 | 13: 3 5 | 14: 3 6 | 15: 4 4 | 16: 4 5 | 17: 4 6 | 18: 5 5 | 19: 5: 6 | 20: 6 6
    private var rollData = arrayOf(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
    private var rollCombos = arrayOf(11,12,13,14,15,16,22,23,24,25,26,33,34,35,36,44,45,46,55,56,66)
    private var rolledNumberData = arrayOf(0,0,0,0,0,0,0,0,0,0,0)
    private var rolledHardsData = arrayOf(0,0,0,0,0,0)
    private var rollsOfLengthData = arrayOf(0,0,0,0,0,0)




    fun crapsGame (){
        val die1 = Dice()
        val die2 = Dice()
        var timesRolled = 0
        var currentRoll = 0

        while(timesRolled < numRolls){
            var number1: Int = die1.roll()
            var number2: Int = die2.roll()
            var indexOfCombo: Int

            rolledNumberData[number1+number2-2] = rolledNumberData[number1+number2-2] + 1

            if(number1 == number2){
                rolledHardsData[number1-1] = rolledHardsData[number1-1] + 1
            }

            if(number1+number2 != 7){
                currentRoll++
            } else {
                if(currentRoll > longestRoll){
                    longestRoll = currentRoll
                }
                if(currentRoll == 0){
                    rollsOfLengthData[5] = rollsOfLengthData[5] + 1
                } else if(currentRoll <= 5){
                    rollsOfLengthData[0] = rollsOfLengthData[0] + 1
                } else if(currentRoll <= 10){
                    rollsOfLengthData[1] = rollsOfLengthData[1] + 1
                } else if(currentRoll <= 15){
                    rollsOfLengthData[2] = rollsOfLengthData[2] + 1
                } else if(currentRoll <= 20){
                    rollsOfLengthData[3] = rollsOfLengthData[3] + 1
                } else {
                    rollsOfLengthData[4] = rollsOfLengthData[4] + 1
                }

                currentRoll = 0
                numberOfSevens++
            }

            if(number1 < number2){
                number1 *= 10
            } else {
                number2 *= 10
            }
            val rolledCombo: Int = number1+number2
            indexOfCombo = rollCombos.indexOf(rolledCombo)
            rollData[indexOfCombo] = rollData[indexOfCombo] + 1

            timesRolled++
        }
    }

    override fun toString(): String {
        System.out.flush()
        val avgRollDuration: Float = (numRolls.toFloat() - numberOfSevens) / numberOfSevens

        fun Float.format(digits: Int) = "%.${digits}f".format(this)

        fun comboString(index: Int, label: String): String {
            val count = rollData[index]
            val percentage = (count.toFloat() / numRolls) * 100
            return "$label: $count (${percentage.format(2)}%)"
        }

        val comboLabels = listOf(
            "1 & 1", "1 & 2", "1 & 3", "1 & 4", "1 & 5", "1 & 6", "2 & 2",
            "2 & 3", "2 & 4", "2 & 5", "2 & 6", "3 & 3", "3 & 4", "3 & 5",
            "3 & 6", "4 & 4", "4 & 5", "4 & 6", "5 & 5", "5 & 6", "6 & 6"
        )

        val comboTable = buildString {
            appendLine("\n\nRolled Combos with Percentages:")
            for (i in comboLabels.indices step 7) {
                val row = (i..<(i + 7).coerceAtMost(comboLabels.size)).joinToString("  |  ") { j ->
                    comboString(j, comboLabels[j])
                }
                appendLine(row)
                if (i + 7 < comboLabels.size) appendLine("------------------------------------------------------------------------------------------------------------------------------------------------------")
            }
        }

        val rolledNumbersSection = buildString {
            appendLine("Rolled Numbers & Percentages")
            for (i in rolledNumberData.indices) {
                val rollTotal = i + 2
                val count = rolledNumberData[i]
                val percent = (count.toFloat() / numRolls) * 100
                append("$rollTotal: $count   (${percent.format(2)}%)")
                if (i % 3 == 2) appendLine() else append("  |  ")
            }
            appendLine()
        }

        val hardWaysSection = buildString {
            appendLine("Rolled HardWays & Percentages")
            val hardLabels = listOf(2, 4, 6, 8, 10, 12)
            for (i in hardLabels.indices) {
                val label = "${hardLabels[i]} Hard"
                val count = rolledHardsData.getOrElse(i) { 0 }
                val percent = (count.toFloat() / numRolls) * 100
                append("$label: $count   (${percent.format(2)}%)")
                if (i % 3 == 2) appendLine() else append("  |  ")
            }
            appendLine()
        }

        val rollLengthsSection = buildString {
            appendLine("Length of Rolls and Percentages")
            val labels = listOf("1 - 5", "6 - 10", "11 - 15", "16 - 20", "20+", "0")
            for (i in labels.indices) {
                val count = rollsOfLengthData.getOrElse(i) { 0 }
                val percent = (count.toFloat() / numberOfSevens) * 100
                val label = if (labels[i] == "0") "Rolls of 0" else "Rolls of ${labels[i]}"
                append("$label: $count   (${percent.format(2)}%)")
                if (i % 3 == 2 || i == labels.lastIndex) appendLine() else append("  |  ")
            }
        }

        return """
        $comboTable

        $rolledNumbersSection
        $hardWaysSection
        $rollLengthsSection

        Longest Roll: $longestRoll
        Number of Sevens & Number of Rolls: $numberOfSevens
        Average Roll Duration: ${avgRollDuration.format(2)}
    """.trimIndent()
    }

}