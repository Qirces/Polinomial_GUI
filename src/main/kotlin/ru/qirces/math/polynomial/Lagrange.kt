package ru.qirces.math.polynomial

class Lagrange(val map : MutableMap<Double,Double>) : Polynomial() {

    init{
        val res = Polynomial()
        map.forEach{ (x, y) -> res += fundamentalPoly(x)*y }
        _coeff.clear()
        _coeff.addAll(res.coeff)
    }

    private fun fundamentalPoly(x: Double) : Polynomial {
        val res = Polynomial(1.0)
        map.forEach{ (_x) -> if (_x != x) res *= Polynomial(-_x,1.0) /(x - _x) }
        return res
    }
}