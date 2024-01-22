package io.swilliams.finch.service.exceptions

import java.lang.Exception

class FinchSecurityException(
    msg: String
): Exception() {
    override val message = msg
}