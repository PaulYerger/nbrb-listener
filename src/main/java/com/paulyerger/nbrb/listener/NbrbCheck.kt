package com.paulyerger.nbrb.listener

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Pavel on 03.08.2015.
 */
public class NbrbCheck(vararg listeners: (info: RatesInfo) -> Unit) {
    private final val ratesDateFormat = DateTimeFormatter.ofPattern("dd.MM.yy")
    private final val lastUpdateDateFormat = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yy")

    private var dateOfCurrentRate: LocalDate? = null

    private val changeRateListeners: List<(info: RatesInfo) -> Unit>

    init {
        changeRateListeners = listeners.toList()
    }

    public fun check(): LocalDate {
        val curState = NbrbPageState()

        // update if empty state or state was changed
        if (dateOfCurrentRate == null || (dateOfCurrentRate?.equals(curState.getCurrentDate()) == false)) {

            val difEUR = curState.getCurrentRateOfEUR() - curState.getPreviousRateOfEUR()
            val difUSD = curState.getCurrentRateOfUSD() - curState.getPreviousRateOfUSD()
            val difRUB = curState.getCurrentRateOfRUB() - curState.getPreviousRateOfRUB()

            val ratesInfo = RatesInfo(LocalDateTime.now().format(lastUpdateDateFormat),
                    curState.getCurrentDate().format(ratesDateFormat), curState.getPreviousDate().format(ratesDateFormat),
                    curState.getPreviousRateOfEUR(), curState.getPreviousRateOfUSD(), curState.getPreviousRateOfRUB(),
                    curState.getCurrentRateOfEUR(), curState.getCurrentRateOfUSD(), curState.getCurrentRateOfRUB(),
                    difEUR, difUSD, difRUB)

            // notify listeners
            changeRateListeners forEach { l -> l(ratesInfo) }

            dateOfCurrentRate = curState.getCurrentDate()

        }

        return curState.getCurrentDate()
    }


}