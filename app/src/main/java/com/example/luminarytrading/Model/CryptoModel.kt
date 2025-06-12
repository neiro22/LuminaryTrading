package com.example.luminarytrading.Model

import java.io.Serializable

public class CryptoModel (
    var name: String,
    var symbol:String,
    var price:Double,
    var changePercent: Double,
    var lineData:ArrayList<Int>,
    var propertyAmount:Double,
    var propertySize:Double,
    var SellPrice1: Double,
    var SellPrice2: Double,
    var SellPrice3: Double,
    var SellPrice4: Double,
    var SellAmount1: Double,
    var SellAmount2: Double,
    var SellAmount3: Double,
    var SellAmount4: Double,

    var BuyPrice1: Double,
    var BuyPrice2: Double,
    var BuyPrice3: Double,
    var BuyPrice4: Double,
    var BuyPrice5: Double,
    var BuyPrice6: Double,
    var BuyPrice7: Double,
    var BuyAmount1: Double,
    var BuyAmount2: Double,
    var BuyAmount3: Double,
    var BuyAmount4: Double,
    var BuyAmount5: Double,
    var BuyAmount6: Double,
    var BuyAmount7: Double,

    var Open:Double,
    var Close:Double,
    var High:Double,
    var Low:Double,
    var DailyChange:Double,
    var DailyVol:Double,
    var SymbolLogo:String
):Serializable