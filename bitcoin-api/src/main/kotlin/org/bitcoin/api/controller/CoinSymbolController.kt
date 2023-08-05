package org.bitcoin.api.controller

import com.kafka.scheduledata.controller.dto.AddCoinSymbolResponse
import com.kafka.scheduledata.controller.dto.AddCoinsymbolRequest
import org.bitcoin.api.service.CoinSymbolService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class CoinSymbolController(
    val coinSymbolService: CoinSymbolService
) {

    @PostMapping("/add/coin-symbol")
    fun addCoinSymbol(@RequestBody symbol: AddCoinsymbolRequest): AddCoinSymbolResponse {
        return coinSymbolService.addCoinSymbol(symbol)
    }

}