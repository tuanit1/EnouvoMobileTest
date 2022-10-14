package com.example.enouvomobiletest.extension

val PASSWORD_REGEX: Regex by lazy { "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$".toRegex() }

