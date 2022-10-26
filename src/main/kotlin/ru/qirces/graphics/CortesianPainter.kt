package ru.qirces.graphics

import java.awt.Color
import java.awt.Graphics
import kotlin.math.max

class CortesianPainter (converter: Converter) : Painter {

    override var height: Int = 1
        get() = field
        set(value) {field = max(1,value)}

    override var width: Int = 1
        get() = field
        set(value) {field = max(1,value)}



    private var xMin = 0.0
    private var xMax = 0.0
    private var yMin = 0.0
    private var yMax = 0.0

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


    private fun paintAxes(g:Graphics){
        val x0 = converter.xCrt2Scr(0.0)
        val y0 = converter.yCrt2Scr(0.0)
        g.color = Color.BLACK
        g.drawLine(0, y0, width, y0)
        g.drawLine(x0,0,x0, height)
    }

    private fun paintMarkup(g: Graphics){
        val x0 = converter.xCrt2Scr(0.0)
        val y0 = converter.yCrt2Scr(0.0)
        var tick = 0
        for( i in (xMin*10).toInt()..(xMax*10).toInt())
        {
            g.color = Color.magenta
            tick = 2;
            if ((i % 5)!=0) {
                tick += 1;
                g.color=Color.RED
            }
            if ((i % 10)!=0) {
                tick += 1;
                g.color=Color.DARK_GRAY
            }
            val x=converter.xCrt2Scr(i/10.0)
            g.drawLine(x, y0 - tick, x, y0 + tick);
        }
        for (i in (yMin*10).toInt()..(yMax*10).toInt()){
            g.color= Color.magenta
            tick = 2;
            if ((i % 5)!=0) {
                tick += 1;
                g.color=Color.RED
            }
            if ((i % 10)!=0) {
                tick += 1;
                g.color=Color.DARK_GRAY
            }
            val y = converter.yCrt2Scr(i / 10.0)
            g.drawLine( x0-tick, y, x0+tick, y);
        }

    }

    private fun paintLabel(g:Graphics){
        val x0 = converter.xCrt2Scr(0.0)
        val y0 = converter.yCrt2Scr(0.0)
        g.color= Color.BLUE
        for (i in  (xMin*10).toInt()..(xMax*10).toInt()) {
            if (i % 5 != 0 || i == 0) continue
            val x = converter.xCrt2Scr(i / 10.0)
            val d :Int
            if (i>0)
                d = 20
            else
                d=-10
            g.drawString((-i/10.0).toString(),x-8,y0+d)
        }
        for (i in  (yMin*10).toInt()..(yMax*10).toInt()) {
            if (i % 5!=0) continue;
            if (i==0) continue;
            val y = converter.yCrt2Scr(i / 10.0)
            val d:Int
            if (i>0)
                d=5
            else
                d=-30
            g.drawString((i/10.0).toString(),x0+d,y+5)
        }
    }

    override fun paint(g: Graphics?) {
        if (g != null) {
            paintAxes(g)
            paintMarkup(g)
            paintLabel(g)
        }
    }
}