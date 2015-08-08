package com.paulyerger.nbrb.listener


/**
 * Created by Pavel on 03.08.2015.
 */

public class RatesInfo(
        val lastUpdateVal: String,
        val currentDateVal: String,
        val previousDateVal: String,
        val prevEurVal: Float,
        val prevUsdVal: Float,
        val prevRubVal: Float,
        val curEurVal: Float,
        val curUsdVal: Float,
        val curRubVal: Float,
        val difEurVal: Float,
        val difUsdVal: Float,
        val difRubVal: Float
) {
}