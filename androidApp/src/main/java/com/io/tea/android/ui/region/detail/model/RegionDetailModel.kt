package com.io.tea.android.ui.region.detail.model

data class RegionDetailModel(
    val regionId: Long,
    val imageUrl: String,
    val description: String,
    // NOTE : (想定)ヘッダータイトル
    val headerTitle: String,
    // NOTE : (想定)Lot名
    val campaignName: String,
    // NOTE : (想定)Lot詳細
    val campaignDetail: String,
    // NOTE : (想定)期間 開始日
    val pointExpirationStart: String,
    // NOTE : (想定)期間 終了日
    val pointExpirationEnd: String,
    // NOTE : (想定)期間 開始日
    val campaignPeriodStart: String,
    // NOTE : (想定)期間 終了日
    val campaignPeriodEnd: String,
    // NOTE : (想定)期間 補足
    val pointExpirationNote: String,
    // NOTE : (想定)Lotサイト名
    val campaignSiteName: String,
    // NOTE : (想定)LotサイトURL
    val campaignSiteURL: String,
    // NOTE : 利用規約タイトル
    val tosTitle: String,
    // NOTE : 利用規約テキスト
    val tosText: String,
)