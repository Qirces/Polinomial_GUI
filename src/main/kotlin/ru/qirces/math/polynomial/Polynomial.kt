package ru.qirces.math.polynomial

import java.lang.StringBuilder
import kotlin.math.abs
import kotlin.math.max

open class Polynomial(vararg coeffs: Double) {
    protected val _coeff: MutableList<Double>
    val coeff: List<Double>
        get() = _coeff.toList()

    val order: Int
        get() = _coeff.size - 1

    init{
        _coeff = coeffs.toMutableList()
        while(abs(_coeff[_coeff.size - 1]) <= Double.MIN_VALUE && order > 0) _coeff.removeLast()
    }
    constructor(): this(0.0)

    override fun toString(): String {
        val sb = StringBuilder()
        _coeff.asReversed().forEachIndexed { index, d ->
            if (d != 0.0 && _coeff.size != 1) {
                if (index == 0) { if (d < Double.MIN_VALUE) sb.append("-")} else
                sb.append(if (d > Double.MIN_VALUE) "+" else "-")
                if (abs(d) == 1.0 && index < _coeff.size - 1)
                else sb.append(abs(d))
                if (index == _coeff.size - 2)
                    sb.append("x")
                 else  if (index == _coeff.size - 1)
                 else {
                    sb.append("x^")
                    sb.append(_coeff.size - index - 1)
                }
            } else if (coeff.size == 1) sb.append(d)
        }
        return sb.toString()
    }

    operator fun plus(other: Polynomial) : Polynomial {
        val (min, max) =
            if (order < other.order) arrayOf(coeff, other.coeff) else arrayOf(other.coeff, coeff)
        val res = max.toDoubleArray()
        min.forEachIndexed { i, v -> res[i] += v }
        return Polynomial(*res)
    }
    operator fun plusAssign(other: Polynomial) {
        val res = this + other
        this._coeff.clear()
        this._coeff.addAll(res._coeff)
    }

    operator fun unaryMinus() = this * (-1.0)
    operator fun unaryPlus() = Polynomial(*_coeff.toDoubleArray())

    operator fun minus(other: Polynomial) = this + (-other)
    operator fun minusAssign(other: Polynomial) = this.plusAssign(other * (-1.0))

    operator fun times(other: Polynomial) : Polynomial {
        val res = DoubleArray(order + other.order + 1)
        coeff.forEachIndexed {i, vi ->
            other.coeff.forEachIndexed { j, vj ->
                res[i+j] += vi * vj
            }
        }
        return Polynomial(*res)
    }

   // operator fun times(num: Double) = Polynomial(*DoubleArray(order + 1) {coeff[it]*num})
    operator fun timesAssign(num: Double)
    {
        _coeff.forEachIndexed{i, vi -> _coeff[i] *= num}
    }

    operator fun timesAssign(other: Polynomial)
    {
        val res = this * other
        this._coeff.clear()
        this._coeff.addAll(res.coeff)
    }

    operator fun times(num : Double) = Polynomial(*_coeff.map{it * num}.toDoubleArray())


    operator fun div(num: Double) = if (num == 0.0) { throw Exception("На ноль делить нельзя")} else this * (1.0 / num)
    operator fun divAssign(num: Double) = this.timesAssign(1/num)

    override operator fun equals(other: Any?) : Boolean {
        if (other !is Polynomial) return false
        _coeff.forEachIndexed { index, d -> if(abs(d - other._coeff[index]) > Double.MIN_VALUE) return false}
        return true
    }

    override fun hashCode(): Int {
        return _coeff.hashCode()
    }


    //operator fun invoke(x : Double) = DoubleArray(order + 1) {_coeff[it]*Math.pow(x, it.toDouble())}.sum()
    //operator fun invoke(x : Double) = _coeff.reduceIndexed{index, acc, d -> acc + d * x.pow(index.toDouble()) }
    operator fun invoke(x : Double) : Double {
        val p = 1.0
        return _coeff.reduce {acc, d -> p *+ x; acc + d * p}
    }

/*
Добавить функции сравенния даблов и использовать их. Добавить сравнение полиномов на равенство
-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_
Написать класс реализующий полином Ньютона. Конструктор, принимающий набор узлов, метод, позволяющий ддобавить один дополнительный узел.
Написать тест, в котором посчитать время, которое занимает построение полинома Лагранжа и полинома Ньжтона для 1000 узлов и для 1001 узла. System.currentTimeMillis()
 */
}