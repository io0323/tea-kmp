package com.io.tea.android.ui.onboarding.personalized.model

import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
/**
 * 検索時の絞り込み条件
 *
 * @property searchWord String 検索文字
 * @property keywords List<String> キーワード
 * @property categories List<Int> カテゴリーID
 * @property isFree Boolean フリー
 */
data class PersonalizedFilter(
    val searchWord: String? = null,
    val keywords: List<String> = emptyList(),
    val categories: List<Int> = emptyList(),
    val isFree: Boolean? = null,
)
