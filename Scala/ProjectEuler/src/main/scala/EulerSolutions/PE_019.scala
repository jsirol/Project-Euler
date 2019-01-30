package EulerSolutions

import scala.annotation.tailrec

/*
You are given the following information, but you may prefer to do some research for yourself.
1 Jan 1900 was a Monday.
Thirty days has September,
April, June and November.
All the rest have thirty-one,
Saving February alone,
Which has twenty-eight, rain or shine.
And on leap years, twenty-nine.
A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */

object PE_019 extends App {

  trait dateTemplate {
    val year: Int
    val month: Int
    val day: Int
  }

   private class baseDate extends dateTemplate {
    val year = 1900
    val month = 1
    val day = 1
  }

  private class calendarUtils {
    def daysInMonth(year: Int, month: Int): Int = {
      if (List(4, 6, 9, 11).contains(month)) 30
      else if (List(1, 3, 5, 7, 8, 10, 12).contains(month)) 31
      else if (year % 4 == 0 && year % 400 == 0) 29
      else 28
    }
  }

  class Date(val year: Int, val month: Int, val day: Int) extends dateTemplate {

    private val base = new baseDate()
    private val calendarUtils = new calendarUtils()
    require(year >= base.year, "year must be at least 1900")
    require(month >= 1 && month <= 12, "month must be between 1 and 12")
    require(day >= 1 && day <= 31, "day must be between 1 and 31")

    // nice to have for e.g. weekday calculation
    private val daysPassed: Int = (0 to year - base.year).
      map(y =>
        if (month == 1) day
        else day + (1 until month).foldLeft(0)((s, m) => s + calendarUtils.daysInMonth(base.year+y, m))).
      sum - 1

    override def toString: String =
      year.toString + "-" + month.toString + "-" + day.toString

    def wday(): Int = {
      // find out how many days have passed since base date
      daysPassed % 7 + 1 // first day of base was monday (1)
    }



    // adds days to a Date ("one month at at time")
    // NB! Causes creation of multiple Date objects, which evaluate daysPassed a bit too heavily (could just add
    // days to currentDate's daysPassed). However for problem this solution is easily fast enough.
    def +(days: Int): Date = {
      @tailrec
      def addToMonth(currentDate: Date, daysLeftToAdd: Int): Date = {
        val monthDays = calendarUtils.daysInMonth(currentDate.year, currentDate.month)
        if (currentDate.day + daysLeftToAdd < monthDays)
          new Date(currentDate.year, currentDate.month, currentDate.day+daysLeftToAdd)
        else if (currentDate.month == 12)
          addToMonth(new Date(currentDate.year+1, 1, 1), daysLeftToAdd - (monthDays - currentDate.day))
        else
          addToMonth(new Date(currentDate.year, currentDate.month+1, 1), daysLeftToAdd - (monthDays - currentDate.day))
      }
      addToMonth(this, days)
    }

  }

  // counts how many of the given weekday (1-7, mon-sun) fall on the first of the month between firstDate and lastDate
  def countFirstWeekdays(firstDate: Date, lastDate: Date, weekdayToCount: Int): Int = {
    def countFirstWeekdaysIter(currentDate: Date, count: Int): Int = {
      if (currentDate.year > lastDate.year ||
        (currentDate.year == lastDate.year && currentDate.month > lastDate.month) ||
        (currentDate.year == lastDate.year && currentDate.month == lastDate.month && currentDate.day >= lastDate.day))
        count
      else {
        val nextDate = currentDate + 7
        if (nextDate.day == 1) countFirstWeekdaysIter(nextDate, count+1)
        else countFirstWeekdaysIter(nextDate, count)
      }
    }
    // find the first weekday since firstDate
    val firstWeekday = (0 to 6).map(x => ((firstDate + x).wday(), firstDate + x)).filter(x => x._1 == weekdayToCount).head._2
    if (firstWeekday.day == weekdayToCount)
      countFirstWeekdaysIter(firstWeekday, 1)
    else
      countFirstWeekdaysIter(firstWeekday, 0)
  }

  val t0 = System.nanoTime()
  val firstDate = new Date(1901, 1, 1)
  val lastDate = new Date(2000, 12, 31)
  val result = countFirstWeekdays(firstDate, lastDate, 7)
  println("Result: " + result) // 171
  println("Elapsed time: " + (System.nanoTime() - t0) / 1e9 + "s")
}
