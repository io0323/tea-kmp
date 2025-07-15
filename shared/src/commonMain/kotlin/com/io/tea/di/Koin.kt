package com.io.tea.di

import com.io.tea.domain.api.AccountApi
import com.io.tea.domain.api.BannerApi
import com.io.tea.domain.api.KtorApi
import com.io.tea.domain.api.KtorApiImpl
import com.io.tea.domain.api.MyApi
import com.io.tea.domain.api.NoticeApi
import com.io.tea.domain.api.RegionsApi
import com.io.tea.domain.api.TeaApi
import com.io.tea.domain.repository.BannerRepository
import com.io.tea.domain.repository.CountryRepository
import com.io.tea.domain.repository.MyTeaRepository
import com.io.tea.domain.repository.NoticeRepository
import com.io.tea.domain.repository.RegionsRepository
import com.io.tea.domain.repository.TeaRepository
import com.io.tea.domain.usecase.FetchBannerListUseCase
import com.io.tea.domain.usecase.FetchCountryListUseCase
import com.io.tea.domain.usecase.FetchMyTeaListUseCase
import com.io.tea.domain.usecase.FetchNoAddedRegionUseCase
import com.io.tea.domain.usecase.FetchNoticeListUseCase
import com.io.tea.domain.usecase.FetchRegionDetailsUseCase
import com.io.tea.domain.usecase.FetchRegionListUseCase
import com.io.tea.domain.usecase.FetchTeaListUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val apiModule = module {
    single { AccountApi(get()) }
    single { BannerApi(get()) }
    single<KtorApi> { KtorApiImpl() }
    single { MyApi(get()) }
    single { TeaApi(get()) }

    factory { NoticeApi(get()) }
    factory { RegionsApi(get()) }
}

val repositoryModule = module {
    single { BannerRepository() }
    single { CountryRepository() }
    single { MyTeaRepository() }
    single { NoticeRepository() }
    single { RegionsRepository() }
    single { TeaRepository() }
}

val useCaseModule = module {
    single { FetchBannerListUseCase(get()) }
    single { FetchCountryListUseCase(get()) }
    single { FetchMyTeaListUseCase(get()) }
    single { FetchNoAddedRegionUseCase() }
    single { FetchNoticeListUseCase(get()) }
    single { FetchRegionDetailsUseCase() }
    single { FetchRegionListUseCase() }
    single { FetchTeaListUseCase(get()) }
}

// iosApp.swiftから呼ばれる
@Suppress("unused")
fun initKoin() = initKoin {}

internal fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        apiModule,
        repositoryModule,
        useCaseModule
    )
}
