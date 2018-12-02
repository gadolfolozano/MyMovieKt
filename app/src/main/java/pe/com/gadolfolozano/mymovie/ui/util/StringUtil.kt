package pe.com.gadolfolozano.mymovie.ui.util

import java.util.regex.Pattern

class StringUtil private constructor() {

    init {
        throw IllegalStateException("Utility class")
    }

    companion object {
        fun validateEmail(emailAddress: String): Boolean {
            val regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$")
            val regMatcher = regexPattern.matcher(emailAddress)
            return regMatcher.matches()
        }
    }

}