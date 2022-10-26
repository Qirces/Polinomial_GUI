package ru.qirces.graphics

import kotlin.math.abs
import kotlin.math.max

class Converter(
    xMin : Double,
    xMax : Double,
    yMin : Double,
    yMax : Double,
    width : Int,
    height : Int
) {
    var height : Int = 1
        get() = field
        set(value) {
            field = max(1, value)
        }

    var width : Int = 1
        get() = field
        set(value) {
            field = max(1, value)
        }


     var xMin : Double = 0.0
     var xMax : Double = 0.0
     var yMin : Double = 0.0
     var yMax : Double = 0.0



    var xEdges : Pair<Double, Double>
        get() = Pair(xMin, xMax)
        set(value) {
            if (value.first < value.second) {
                xMin = value.first

                xMax = value.second
            } else {
                xMin = value.second
                xMax = value.first
            }
            if (abs(xMax - xMin) < 0.1){
                xMin -= 0.1
                xMax += 0.1
            }
        }


    var yEdges : Pair<Double, Double>
        get() = Pair(yMin, yMax)
        set(value) {
            if (value.first < value.second) {
                yMin = value.first

                yMax = value.second
            } else {
                yMin = value.second
                yMax = value.first
            }
            if (abs(yMax - yMin) < 0.1){
                yMin -= 0.1
                yMax += 0.1
            }
        }

    val yDen
        get() = height / (yMax - yMin)

    val xDen
        get() = width / (xMax - xMin)


    init {
        this.height = height
        this.width = width
        this.xMin = xMin
        this.xMax = xMax
        this.yMin = yMin
        this.yMax = yMax
        yEdges = Pair(yMin, yMax)
        xEdges = Pair(xMin, xMax)

    }

    fun yCrt2Scr(y : Double) = ((yMax - y) * yDen).coerceIn(-1.0 * height, 2.0 * height).toInt()
    fun yScr2Crt(y : Int) =  -y / yDen + yMax

    fun xCrt2Scr(x : Double) = ((xMax - x) * xDen).coerceIn(-1.0 * height, 2.0 * height).toInt()
    fun xScr2Crt(x : Int) = x / xDen + xMin


}
