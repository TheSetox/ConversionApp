package com.thesetox.transaction

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module providing transaction feature dependencies.
 */
val transactionModule =
    module {
        singleOf(::TransactionDataRepository) { bind<TransactionRepository>() }
        singleOf(::AddTransactionUseCase)
        singleOf(::GetTransactionsUseCase)
    }
