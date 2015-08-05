package com.paulyerger.nbrb.listener

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by Pavel on 29.07.2015.
 */
public class NbrbPageState {
    private val ratesTableEl: Element
    private final val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy")

    init {
        val document = Jsoup.parse(URL("http://www.nbrb.by/"), 10000)
        ratesTableEl = document.getElementById("BodyHolder_tblRates") ?:
                throw PageParseException("Can not parse main rates table")
    }

    fun getCurrentDate() = getDateValOfTable(0, 2)

    fun getPreviousDate() = getDateValOfTable(0, 1)

    fun getCurrentRateOfEUR() = getRateValOfTable(1, 3)

    fun getCurrentRateOfUSD() = getRateValOfTable(2, 3)

    fun getCurrentRateOfRUB() = getRateValOfTable(3, 3)

    fun getPreviousRateOfEUR() = getRateValOfTable(1, 2)

    fun getPreviousRateOfUSD() = getRateValOfTable(2, 2)

    fun getPreviousRateOfRUB() = getRateValOfTable(3, 2)

    private fun getRateValOfTable(row: Int, col: Int): Float {
        return ratesTableEl.child(0)?.child(row)?.child(col)?.html()
                ?.replace(',', '.')?.replace("&nbsp;", "")
                ?.toFloat()
                ?: throw PageParseException("Rate, row ${row} and col ${col}")
    }

    private fun getDateValOfTable(row: Int, col: Int): LocalDate {
        return LocalDate.parse(ratesTableEl.child(0)?.child(row)?.child(col)?.html()
                ?.replace("&nbsp;", ""), dateFormatter)
    }
}