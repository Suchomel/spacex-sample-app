package cz.gemsi.spacex.library.date.system

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun LocalDate.mediumFormat(): String {
    return this.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
}