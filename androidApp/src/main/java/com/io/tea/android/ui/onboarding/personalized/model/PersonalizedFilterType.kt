package com.io.tea.android.ui.onboarding.personalized.model

enum class PersonalizedFilterType(
    val title: String,
    val filter: PersonalizedFilter,
) {
    AAA(title = "AAA", PersonalizedFilter(categories = arrayListOf(66031))),
    BBB(title = "BBB", PersonalizedFilter(categories = arrayListOf(66033, 66034))),
    CCC(title = "CCC", PersonalizedFilter(isFree = true)),
    DDD(title = "DDD", PersonalizedFilter(searchWord = "DDD")),
    EEE(title = "EEE", PersonalizedFilter(keywords = arrayListOf("EEE"))),
}
