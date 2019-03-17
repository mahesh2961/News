package `in`.news.utils

import java.sql.Timestamp


class Util
{
    companion object {

        /**
         * @return elapsed time in mins,hours,days
         * @param utcTimeString
         */
        fun getElapsedTime(utcTimeString: Long): String
        {
            var timeElapsedInSeconds = (System.currentTimeMillis() - utcTimeString) / 1000

            if (timeElapsedInSeconds < 60) {
                return "less than 1min ago"
            } else if (timeElapsedInSeconds < 3600) {
                timeElapsedInSeconds /= 60
                return timeElapsedInSeconds.toString() + "mins ago"
            } else if (timeElapsedInSeconds < 86400) {
                timeElapsedInSeconds /= 3600
                return timeElapsedInSeconds.toString() + "hrs ago"
            } else {
                timeElapsedInSeconds /= 86400
                return timeElapsedInSeconds.toString() + "days ago"
            }
        }


        /**
         * @return concatenated string of source and timestamp
         * @param sourceName
         * @param date
         */
        fun getSourceAndElapsedTime(sourceName: String?, date: Timestamp): String {
            return sourceName + " • " + getElapsedTime(date.getTime())
        }

    }


}