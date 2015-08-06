package com.paulyerger.nbrb.view.desktop;

import com.paulyerger.nbrb.listener.RatesInfo;

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
        pack();
        setResizable(false);
    }

    public void updateInfo(RatesInfo data) {
        lastUpdateLabel.setText(data.getLastUpdateVal());

        currentDateLabel.setText(data.getCurrentDateVal());
        previousDateLabel.setText(data.getPreviousDateVal());

        prevEurLabel.setText(String.valueOf(data.getPrevEurVal()));
        prevUsdLabel.setText(String.valueOf(data.getPrevUsdVal()));
        prevRubLabel.setText(String.valueOf(data.getPrevRubVal()));

        curEurLabel.setText(String.valueOf(data.getCurEurVal()));
        curUsdLabel.setText(String.valueOf(data.getCurUsdVal()));
        curRubLabel.setText(String.valueOf(data.getCurRubVal()));

        difEurLabel.setText(String.valueOf(data.getDifEurVal()));
        changeDifLabelColor(difEurLabel, data.getDifEurVal());

        difUsdLabel.setText(String.valueOf(data.getDifUsdVal()));
        changeDifLabelColor(difUsdLabel, data.getDifUsdVal());

        difRubLabel.setText(String.valueOf(data.getDifRubVal()));
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
