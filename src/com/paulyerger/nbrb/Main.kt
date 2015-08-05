package com.paulyerger.nbrb

import com.paulyerger.nbrb.listener.NbrbCheck
import com.paulyerger.nbrb.listener.NbrbCheckRunner
import com.paulyerger.nbrb.listener.RatesInfoForm
import java.awt.*

/**
 * Created by Pavel on 26.07.2015.
 */

fun main(args: Array<String>) {
    val gui = RatesInfoForm()

    // tray
    if (!SystemTray.isSupported()) {
        throw RuntimeException("System tray are not supported")
    }


    val popupMenu = PopupMenu()

    val openMenuItem = MenuItem("Open")
    openMenuItem.addActionListener({ gui.setVisible(true) })
    popupMenu.add(openMenuItem)

    val exitMenuItem = MenuItem("Exit")
    exitMenuItem.addActionListener({ System.exit(0) })
    popupMenu.add(exitMenuItem)

    val trayImage = Toolkit.getDefaultToolkit().getImage(javaClass<String>().getResource("/tray_icon.png"));
    val trayIcon = TrayIcon(trayImage, "NBRB Notifier", popupMenu)
    trayIcon.setImageAutoSize(true)

    SystemTray.getSystemTray().add(trayIcon)

    // tray

    val nbrbCheck = NbrbCheck(gui)
    NbrbCheckRunner({ nbrbCheck.check() }).start();

}