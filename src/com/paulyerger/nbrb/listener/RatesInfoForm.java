package com.paulyerger.nbrb.listener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pavel on 03.08.2015.
 */
public class RatesInfoForm extends JFrame {
    private JLabel lastUpdateLabel;

    private JLabel currentDateLabel;
    private JLabel previousDateLabel;

    private JLabel prevEurLabel;
    private JLabel prevUsdLabel;
    private JLabel prevRubLabel;

    private JLabel curEurLabel;
    private JLabel curUsdLabel;
    private JLabel curRubLabel;

    private JLabel difEurLabel;
    private JLabel difUsdLabel;
    private JLabel difRubLabel;

    private JPanel mainPanel;

    public RatesInfoForm() throws HeadlessException {
        setTitle("Nbrb listener");
        add(mainPanel);
    }

    public void updateInfo(RatesInfoFormData data) {
        lastUpdateLabel.setText(data.getLastUpdateVal());

        currentDateLabel.setText(data.getCurrentDateVal());
        previousDateLabel.setText(data.getPreviousDateVal());

        prevEurLabel.setText(data.getPrevEurVal().toString());
        prevUsdLabel.setText(data.getPrevUsdVal().toString());
        prevRubLabel.setText(data.getPrevRubVal().toString());

        curEurLabel.setText(data.getCurEurVal().toString());
        curUsdLabel.setText(data.getCurUsdVal().toString());
        curRubLabel.setText(data.getCurRubVal().toString());

        difEurLabel.setText(data.getDifEurVal().toString());
        changeDifLabelColor(difEurLabel, data.getDifEurVal());

        difUsdLabel.setText(data.getDifUsdVal().toString());
        changeDifLabelColor(difUsdLabel, data.getDifUsdVal());

        difRubLabel.setText(data.getDifRubVal().toString());
        changeDifLabelColor(difRubLabel, data.getDifRubVal());
    }

    private void changeDifLabelColor(JLabel label, Float dif) {
        Color textColor;

        switch (Double.compare(dif, 0)) {
            case 1:
                textColor = Color.GREEN;
                break;
            case -1:
                textColor = Color.RED;
                break;
            default:
                textColor = Color.BLACK;
        }

        label.setForeground(textColor);
    }
}
