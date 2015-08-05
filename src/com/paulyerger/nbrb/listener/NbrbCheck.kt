package com.paulyerger.nbrb.listener

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by Pavel on 03.08.2015.
 */
public class NbrbCheck(val gui: RatesInfoForm) {
    private final val ratesDateFormat = DateTimeFormatter.ofPattern("dd.MM.yy")
    private final val lastUpdateDateFormat = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yy")

    private var dateOfCurrentRate: LocalDate? = null;

    public fun check(): Boolean {
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

            gui.updateInfo(ratesInfo.toRatesInfoFormData())

            dateOfCurrentRate = curState.getCurrentDate()

            return true
        }

        return false
    }
}