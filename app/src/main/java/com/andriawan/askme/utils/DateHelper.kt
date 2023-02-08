package com.andriawan.askme.utils

import com.andriawan.askme.utils.Constants.EMPTY
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

object DateHelper {

    const val DEFAULT_API_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DEFAULT_DISPLAY_FORMAT = "dd MMM yyyy"

    fun formatDate(
        timeString: String,
        beforeFormat: String = DEFAULT_API_FORMAT,
        afterFormat: String = DEFAULT_DISPLAY_FORMAT
    ): String {
        return try {
            val beforeFormatter = SimpleDateFormat(beforeFormat, Locale.getDefault())
            val afterFormatter = SimpleDateFormat(afterFormat, Locale.getDefault())
            val beforeDate = beforeFormatter.parse(timeString)
            if (beforeDate != null) afterFormatter.format(beforeDate) else EMPTY
        } catch (e: Exception) {
            Timber.e(e)
            EMPTY
        }
    }
}