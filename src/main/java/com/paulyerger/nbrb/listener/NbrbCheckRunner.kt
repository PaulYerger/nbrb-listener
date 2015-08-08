package com.paulyerger.nbrb.listener

import java.util.Calendar
import java.util.Date
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit

/**
 * Created by Pavel on 03.08.2015.
 */
public class NbrbCheckRunner(val checkerFun: () -> Boolean) {
    private val timer = Timer(false)
    private val timerTask = NbrbCheckTimerTask()

    public fun start(): Unit = timerTask.run()


    private inner class NbrbCheckTimerTask : TimerTask() {
        override fun run() {
            val isCurrentValue = checkerFun()

            // if rates was updated or current time greater then max check time, then schedule next fix to tomorrow
            if (isCurrentValue and (Calendar.getInstance().get(Calendar.HOUR) < 14)) {
                this.cancel();
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