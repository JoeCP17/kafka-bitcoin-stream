package org.bitcoin.api.controller

import org.bitcoin.api.controller.dto.AddCoinSymbolResponse
import org.bitcoin.api.controller.dto.AddCoinsymbolRequest
import org.bitcoin.api.service.CoinSymbolService
import org.springframework.web.bind.annotation.GetMapping
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

    // Socket 데이터 수신을 위한 Trigger API
    @GetMapping("/connect/coin-symbol")
    fun connectCoinSymbol() {
        coinSymbolService.connectCoinSymbol()
    }

}