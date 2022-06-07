package com.example.sixthweekappflow.domain


import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.max

object GenerationPI {

    fun formula(n: Int): BigDecimal {
        var sum = BigDecimal(0)
        for (i in 0..n) {
            sum += subFormula(i)
        }
        return sum
    }

    private fun subFormula(n: Int): BigDecimal {
        val scale = max(n, 1000)
        val x = BigDecimal.ONE.divide(BigDecimal(16).pow(n))
        val a = BigDecimal.valueOf(4).divide(
            BigDecimal(n).multiply(BigDecimal.valueOf(8)).plus(BigDecimal.ONE),
            scale,
            RoundingMode.HALF_UP
        )
        val b = BigDecimal.valueOf(2).divide(
            BigDecimal(n).multiply(BigDecimal.valueOf(8)).plus(BigDecimal.valueOf(4)),
            scale,
            RoundingMode.HALF_UP
        )
        val c = BigDecimal.valueOf(1).divide(
            BigDecimal(n).multiply(BigDecimal.valueOf(8)).plus(BigDecimal.valueOf(5)),
            scale,
            RoundingMode.HALF_UP
        )
        val d = BigDecimal.valueOf(1).divide(
            BigDecimal(n).multiply(BigDecimal.valueOf(8)).plus(BigDecimal.valueOf(6)),
            scale,
            RoundingMode.HALF_UP
        )
        return x * (a - b - c - d)
    }


}





