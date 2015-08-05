package com.paulyerger.nbrb.listener;

/**
 * Created by Pavel on 03.08.2015.
 */
public class RatesInfoFormData {
    private String lastUpdateVal;

    private String currentDateVal;
    private String previousDateVal;

    private Float prevEurVal;
    private Float prevUsdVal;
    private Float prevRubVal;

    private Float curEurVal;
    private Float curUsdVal;
    private Float curRubVal;

    private Float difEurVal;
    private Float difUsdVal;
    private Float difRubVal;

    public RatesInfoFormData(
            String lastUpdateVal,
            String currentDateVal,
            String previousDateVal,
            Float prevEurVal,
            Float prevUsdVal,
            Float prevRubVal,
            Float curEurVal,
            Float curUsdVal,
            Float curRubVal,
            Float difEurVal,
            Float difUsdVal,
            Float difRubVal
    ) {
        this.lastUpdateVal = lastUpdateVal;
        this.currentDateVal = currentDateVal;
        this.previousDateVal = previousDateVal;
        this.prevEurVal = prevEurVal;
        this.prevUsdVal = prevUsdVal;
        this.prevRubVal = prevRubVal;
        this.curEurVal = curEurVal;
        this.curUsdVal = curUsdVal;
        this.curRubVal = curRubVal;
        this.difEurVal = difEurVal;
        this.difUsdVal = difUsdVal;
        this.difRubVal = difRubVal;
    }

    public String getLastUpdateVal() {
        return lastUpdateVal;
    }

    public String getCurrentDateVal() {
        return currentDateVal;
    }

    public String getPreviousDateVal() {
        return previousDateVal;
    }

    public Float getPrevEurVal() {
        return prevEurVal;
    }

    public Float getPrevUsdVal() {
        return prevUsdVal;
    }

    public Float getPrevRubVal() {
        return prevRubVal;
    }

    public Float getCurEurVal() {
        return curEurVal;
    }

    public Float getCurUsdVal() {
        return curUsdVal;
    }

    public Float getCurRubVal() {
        return curRubVal;
    }

    public Float getDifEurVal() {
        return difEurVal;
    }

    public Float getDifUsdVal() {
        return difUsdVal;
    }

    public Float getDifRubVal() {
        return difRubVal;
    }
}
