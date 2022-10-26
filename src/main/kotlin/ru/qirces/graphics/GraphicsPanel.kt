package ru.qirces.graphics
import java.awt.Graphics
import javax.swing.*

class GraphicsPanel : JPanel() {

    private var painters: MutableList<Painter> = mutableListOf()

    fun addPainter(painter: Painter){
        painters.add(painter)
    }

    fun removePainter(painter: Painter){
        painters.remove(painter)
    }

    fun addPainters(painters: List<Painter>){
        this.painters.addAll(painters)
    }

    override fun paint(g : Graphics?) {
        super.paint(g)
        g?.let{ gr ->
            painters.forEach { it.paint(gr) }
        }
    }

}

//Создать класс CortesianPainter - класс создающий систему координат с разметкой