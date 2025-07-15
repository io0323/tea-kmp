package com.io.tea.domain.usecase

import org.koin.core.component.KoinComponent

/**
 * 未追加のLot一覧を取得するユースケース
 */
class FetchNoAddedRegionUseCase : KoinComponent {
    operator fun invoke(regionListWithHeader: List<RegionListWithHeader>): List<RegionListWithHeader> {
        if (regionListWithHeader.isEmpty()) {
            return emptyList()
        }

        return regionListWithHeader.map {
            RegionListWithHeader(
                it.headerName,
                it.regionList.filter { region -> !region.isAdded }
            )
        }.toList()
    }
}