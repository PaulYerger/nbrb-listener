package com.paulyerger.nbrb

import com.paulyerger.nbrb.listener.NbrbCheck
import com.paulyerger.nbrb.listener.NbrbCheckRunner
import com.paulyerger.nbrb.view.desktop.NbrbDesktopView

/**
 * Created by Pavel on 26.07.2015.
 */

fun main(args: Array<String>) {
    val desktopView = NbrbDesktopView()
    val nbrbCheck = NbrbCheck({ desktopView.onInfoUpdate(it) })

    NbrbCheckRunner({ nbrbCheck.check() }).start();
}