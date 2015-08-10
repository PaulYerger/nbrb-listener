package com.paulyerger.nbrb.listener

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit

/**
 * Created by Pavel on 03.08.2015.
 */
public class NbrbCheckRunner(val checkerFun: () -> LocalDate) {
    private val timer = Timer(false)
    private val timerTask = NbrbCheckTimerTask()

    public fun start(): Unit = timerTask.run()


    private inner class NbrbCheckTimerTask : TimerTask() {
        override fun run() {
            val currentDate = checkerFun()

            if (currentDate.getDayOfYear() == LocalDate.now().plus(1, ChronoUnit.DAYS).getDayOfYear()) {
                this.cancel()
                timer.purge()

                timer.schedule(NbrbCheckTimerTask(), getTomorrowStartTime(), TimeUnit.MINUTES.toMillis(2))
            }
        }

        private fun getTomorrowStartTime(): Date {
            val calendar = Calendar.getInstance()
            calendar.setTime(Date())
            calendar.add(Calendar.DATE, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 12)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            return calendar.getTime()
        }

    }
}