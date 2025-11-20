# Kotlin Craps Statistical Simulator

## Author: Jake Gertz

This project is a high-performance statistical simulator for the game of Craps, written in Kotlin. It is designed to run a very large number of dice rolls (e.g., one billion) to gather detailed statistics about roll frequencies, combinations, and game flow patterns.

The simulation tracks not only the sum of the dice but also the specific combinations, the frequency of "hard ways," and the length of roll streaks between "sevens."

## Features

* **High-Volume Simulation:** Capable of running billions of rolls to generate a large statistical sample.
* **Combination Tracking:** Tracks the frequency of all 21 unique dice combinations (e.g., 1-1, 1-2, 1-3...).
* **Sum Tracking:** Tracks the frequency of all possible roll sums (2 through 12).
* **Hard Way Analysis:** Specifically counts the occurrences of "Hard Ways" (doubles: 1-1, 2-2, 3-3, 4-4, 5-5, 6-6).
* **Roll Duration Analysis:** Provides detailed stats on the number of rolls that occur *between* sevens, including:
    * The single longest roll streak without a seven.
    * The average duration of a roll streak.
    * A histogram of roll streak lengths (e.g., how many streaks lasted 0 rolls, 1-5 rolls, 6-10 rolls, etc.).
* **Formatted Output:**Presents all collected data in a clean, human-readable report at the end of the simulation.

## Project Structure

* **`Main.kt`**: The main entry point for the application. It initializes the `Craps` simulator with a set number of rolls (hard-coded to 1,000,000,000) and prints the final report.
* **`Craps.kt`**: The core simulation engine. This class contains the `crapsGame()` loop, which runs the simulation and tracks all statistical data in various arrays. It also contains the `toString()` method to format the final report.
* **`Dice.kt`**: A simple helper class that simulates a single 6-sided die.

## How to Run

### Prerequisites

You must have the Kotlin command-line compiler (`kotlinc`) and the Java Runtime Environment (JRE) installed.

### Compilation and Execution

1.  **Compile the Project:**
    Open your terminal, navigate to the directory containing the `.kt` files, and run the following command to compile them into a single JAR (Java Archive) file:

    ```bash
    kotlinc Main.kt Craps.kt Dice.kt -include-runtime -d CrapsSimulator.jar
    ```

2.  **Run the Simulation:**
    Once compiled, you can run the simulation using `java`:

    ```bash
    java -jar CrapsSimulator.jar
    ```

    The simulation will run (this may take some time given the 1 billion rolls) and then print the formatted statistical report to your console.

### Modifying the Number of Rolls

To change the number of rolls, open `Main.kt` and edit the number passed to the `Craps` constructor:

```kotlin
// In Main.kt
fun main() {
    // Change this value to run more or fewer rolls
    val crapsRound = Craps(1000000000) 
    crapsRound.crapsGame()
    println(crapsRound.toString())
}
