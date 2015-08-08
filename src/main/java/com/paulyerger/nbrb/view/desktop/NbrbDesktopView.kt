package com.paulyerger.nbrb.view.desktop

import com.paulyerger.nbrb.listener.RatesInfo
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

/**
 * Created by Pavel on 06.08.2015.
 */

public class NbrbDesktopView {

    private val infoWindow = RatesInfoForm()

    init {
        if (!SystemTray.isSupported()) {
            throw RuntimeException("System tray are not supported")
        }

        val popupMenu = getPopupMenu()

        val trayImage = Toolkit.getDefaultToolkit().getImage(javaClass<String>().getResource("/tray_icon.png"));
        val trayIcon = TrayIcon(trayImage, "NBRB Notifier", popupMenu)
        trayIcon.setImageAutoSize(true)

        trayIcon.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                if (e.getClickCount() == 2) infoWindow.setVisible(true)
            }
        })

        SystemTray.getSystemTray().add(trayIcon)
    }

    public fun onInfoUpdate(info: RatesInfo): Unit {
        infoWindow.updateInfo(info)
        infoWindow.setVisible(true)
        infoWindow.setState(Frame.MAXIMIZED_BOTH)
    }

    private fun getPopupMenu(): PopupMenu {
        val popupMenu = PopupMenu()

        val openMenuItem = MenuItem("Open")
        openMenuItem.addActionListener({ infoWindow.setVisible(true) })
        popupMenu.add(openMenuItem)

        val exitMenuItem = MenuItem("Exit")
        exitMenuItem.addActionListener({ System.exit(0) })
        popupMenu.add(exitMenuItem)

        return popupMenu
    }
}