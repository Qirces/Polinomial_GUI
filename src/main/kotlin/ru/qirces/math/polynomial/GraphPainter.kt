package ru.qirces.graphics

import java.awt.Graphics
import kotlin.math.max

class GraphPainter(converter: Converter) : Painter {
    override var height: Int = 1
        set(value) {field = max(1,value) }


    override var width: Int = 1
        set(value) {field = max(1,value) }


    override fun paint(g: Graphics?) {
       // if (g != null) {

        //}
    }

    private var xMin = 0.0
    private var xMax = 0.0
    private var yMin = 0.0
    private var yMax = 0.0

    val x0 = converter.xCrt2Scr(0.0)
    val y0 = converter.yCrt2Scr(0.0)

    private val converter: Converter

    init {
        this.converter = converter
        this.height = converter.height
        this.width = converter.width
        this.xMin = converter.xMin
        this.xMax = converter.xMax
        this.yMin = converter.yMin
        this.yMax = converter.yMax
    }
}