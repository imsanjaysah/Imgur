package com.sanjay.imgur

import org.junit.Test
import java.lang.StringBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Collections.swap

class QuickSortTest {

    @Test
    fun test() {
        // write your code in Kotlin
        /*val A = intArrayOf(1, 3, 6, 4, 1, 2)
        val a = quickSort(A, 0, A.size -1)*/
        //print(a)
        //print(reverseWords("My name is sanjay"))
        print(getFirstTwoItemsWithoutPair(arrayOf(5, 23, 23, 66, 5, 23, 7, 0)))
    }

    fun quickSort(a: IntArray, left: Int, right: Int) {

        if (left >= right) {
            return
        }
        val pivot = a[right]

        var count = 1

        for (i in a.indices) {
            print(count)
            if (a[i] <= pivot) {
                swap(a.toList(), count, i)
                count++
            }

        }

        quickSort(a, 1, count - 2)
        quickSort(a, count, right)


    }

    fun reverseWords(inputWords: String): String {
        val words = inputWords.split(" ")
        val reversedString = StringBuilder()
        for (i in (words.size - 1) downTo 0) {
            reversedString.append(words[i] + " ")
        }
        return reversedString.toString()

    }

    fun getFirstTwoItemsWithoutPair(list: Array<Int>): Array<Int> {
        val pair = mutableListOf<Int>()
        for (i in list.indices) {
            if (list.indexOf(list[i]) == i && list.lastIndexOf(list[i]) == i) {
                pair.add(list[i])
            }
            if (pair.size == 2) break
        }
        return pair.toTypedArray()
    }

    private val dateTimeComparator: (String) -> LocalDateTime = {
        LocalDateTime.parse(it, DateTimeFormatter.ofPattern("dd mmm yyy"))
    }

    fun sortDates(dates: Array<String>): Array<String> {
        return dates.sortedBy(dateTimeComparator).toTypedArray()
    }
}