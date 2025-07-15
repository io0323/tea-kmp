package com.io.tea.android.ui.common.state

/**
 * SMS認証画面へ遷移する前の画面
 */
enum class SmsSource {
    PHONE {
        override val value: String
            get() = name.lowercase()
    },
    EMAIL {
        override val value: String
            get() = name.lowercase()
    };

    abstract val value: String

    companion object {
        fun fromString(value: String): SmsSource? {
            return when (value) {
                PHONE.value -> PHONE
                EMAIL.value -> EMAIL
                else -> null
            }
        }
    }
}
