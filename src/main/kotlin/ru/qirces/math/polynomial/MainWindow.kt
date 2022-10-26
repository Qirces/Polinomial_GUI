package ru.qirces.graphics

import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*


class MainWindow : JFrame() {
    val jsXMin: JSpinner
    val jsXMax: JSpinner
    val nmXMin: SpinnerNumberModel
    val nmXMax: SpinnerNumberModel
    val lblXMin: JLabel
    val lblXMax: JLabel

    val jsYMin: JSpinner
    val jsYMax: JSpinner
    val nmYMin: SpinnerNumberModel
    val nmYMax: SpinnerNumberModel
    val lblYMin: JLabel
    val lblYMax: JLabel

    val pnlColor1: JPanel
    val pnlColor2: JPanel
    val pnlColor3: JPanel
    val lblclr1 : JLabel
    val lblclr2 : JLabel
    val lblclr3 : JLabel


    val mainPnl: GraphicsPanel
    val painters : List<Painter>

    val crtPntr : CortesianPainter
    val grPntr : GraphPainter

    val cnvrt : Converter

    val minSz = Dimension(600, 450)

    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = minSz
        nmXMin = SpinnerNumberModel(-5.0, -100.0, 4.8, 0.1)
        nmXMax = SpinnerNumberModel( 5.0, -4.8, 100.0, 0.1)
        jsXMin = JSpinner(nmXMin)
        jsXMax = JSpinner(nmXMax)
        jsXMax.addChangeListener{ _ -> nmXMin.maximum = nmXMax.value as Double - 2.0 * nmXMax.stepSize as Double}
        jsXMin.addChangeListener{ _ -> nmXMax.minimum = nmXMin.value as Double + 2.0 * nmXMin.stepSize as Double}
        lblXMin = JLabel()
        lblXMin.text = "Xmin: "
        lblXMax = JLabel()
        lblXMax.text = "Xmax: "

        nmYMin = SpinnerNumberModel(-5.0, -100.0, 4.8, 0.1)
        nmYMax = SpinnerNumberModel( 5.0, -4.8, 100.0, 0.1)
        jsYMin = JSpinner(nmYMin)
        jsYMax = JSpinner(nmYMax)
        jsYMax.addChangeListener{ _ -> nmYMin.maximum = nmYMax.value as Double - 2.0 * nmYMax.stepSize as Double}
        jsYMin.addChangeListener{ _ -> nmYMax.minimum = nmYMin.value as Double + 2.0 * nmYMin.stepSize as Double}
        lblYMin = JLabel()
        lblYMin.text = "Ymin: "
        lblYMax = JLabel()
        lblYMax.text = "Ymax: "

        mainPnl = GraphicsPanel()

        pnlColor1 = JPanel()
        pnlColor1.background = Color.BLUE
        pnlColor1.addMouseListener(object : MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                JColorChooser.showDialog(
                    this@MainWindow,
                    "Выбор цвета полинома",
                    pnlColor1.background
                )?.let{
                    pnlColor1.background = it
                }
            }
        })
        lblclr1 = JLabel()
        lblclr1.text = "Цвет узлов"

        pnlColor2 = JPanel()
        pnlColor2.background = Color.GREEN
        pnlColor2.addMouseListener(object : MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                JColorChooser.showDialog(
                    this@MainWindow,
                    "Выбор цвета полинома",
                    pnlColor2.background
                )?.let{
                    pnlColor2.background = it
                }
            }
        })
        lblclr2 = JLabel()
        lblclr2.text = "Цвет графика"

        pnlColor3 = JPanel()
        pnlColor3.background = Color.RED
        pnlColor3.addMouseListener(object : MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                JColorChooser.showDialog(
                    this@MainWindow,
                    "Выбор цвета полинома",
                    pnlColor3.background
                )?.let{
                    pnlColor3.background = it
                }
            }
        })
        lblclr3 = JLabel()
        lblclr3.text = "Цвет производной"

        cnvrt = Converter(jsXMin.value as Double,jsXMax.value as Double, jsYMin.value as Double, jsXMax.value as Double, mainPnl.width,mainPnl.height)

        crtPntr = CortesianPainter(cnvrt)
        grPntr = GraphPainter(cnvrt)

        mainPnl.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                cnvrt.width = mainPnl.width
                cnvrt.height = mainPnl.height
                crtPntr.height = mainPnl.height
                crtPntr.width = mainPnl.width
                repaint()
            }
        })



        painters = mutableListOf()

        painters.add(crtPntr)
        painters.add(grPntr)

        mainPnl.apply {
            background = Color.WHITE
            addPainters(painters)
        }



        layout = GroupLayout(contentPane).apply {
            setHorizontalGroup(createSequentialGroup()
                .addGap(8)

                .addGroup(createParallelGroup()

                    .addComponent(mainPnl, GROW, GROW, GROW)

                    .addGroup(createSequentialGroup()
                    .addGroup(createParallelGroup()
                        .addGroup(createSequentialGroup()

                        .addComponent(lblXMin, SHRINK, SHRINK, SHRINK)
                        .addComponent(jsXMin, 70, SHRINK, SHRINK)
                        .addGap(16)
                        .addComponent(lblXMax, SHRINK, SHRINK, SHRINK)
                        .addComponent(jsXMax, 70, SHRINK, SHRINK)
                        )
                        .addGap(16)
                        .addGroup(createSequentialGroup()

                        .addComponent(lblYMin, SHRINK, SHRINK, SHRINK)
                        .addComponent(jsYMin, 70, SHRINK, SHRINK)
                        .addGap(16)
                        .addComponent(lblYMax, SHRINK, SHRINK, SHRINK)
                        .addComponent(jsYMax, 70, SHRINK, SHRINK)

                        )
                    )
                        .addGap(8,8, Int.MAX_VALUE)

                    .addGroup(createParallelGroup()
                        .addGroup(createSequentialGroup()
                            .addComponent(lblclr1, 70, SHRINK, SHRINK)
                            .addGap(8,8, 10)
                            .addComponent(pnlColor1, 20,20,20)
                        )

                        .addGroup(createSequentialGroup()
                        .addComponent(lblclr2, 70, SHRINK, SHRINK)
                            .addGap(8,8, 10)
                            .addComponent(pnlColor2, 20,20,20)
                        )

                        .addGroup(createSequentialGroup()
                        .addComponent(lblclr3, 70, SHRINK, SHRINK)
                            .addGap(8,8, 10)
                            .addComponent(pnlColor3, 20,20,20)
                        )
                    )
                        .addGap(8)

                    )
                )
                .addGap(8)

            )

            setVerticalGroup(createSequentialGroup()

                .addGap(8)
                .addComponent(mainPnl, GROW, GROW, GROW)
                .addGap(8)


                .addGroup(createParallelGroup()

                    .addComponent(lblXMin, SHRINK, SHRINK, SHRINK)
                    .addComponent(jsXMin, SHRINK, SHRINK, SHRINK)
                    .addComponent(lblXMax, SHRINK, SHRINK, SHRINK)
                    .addComponent(jsXMax, SHRINK, SHRINK, SHRINK)
                    .addComponent(lblclr1, 70, SHRINK, SHRINK)
                    .addComponent(pnlColor1, 20,20,20)


                )
                .addGap(16)

                .addGroup(createParallelGroup()

                    .addComponent(lblYMin, SHRINK, SHRINK, SHRINK)
                    .addComponent(jsYMin, SHRINK, SHRINK, SHRINK)
                    .addComponent(lblYMax, SHRINK, SHRINK, SHRINK)
                    .addComponent(jsYMax, SHRINK, SHRINK, SHRINK)
                    .addComponent(lblclr2, 70, SHRINK, SHRINK)
                    .addComponent(pnlColor2, 20,20,20)
                )
                .addGap(16)

                .addGroup(createParallelGroup()
                    .addComponent(lblclr3, 70, SHRINK, SHRINK)
                    .addComponent(pnlColor3, 20,20,20)
                )

                .addGap(8)
            )
        }
    }

    companion object{
        val SHRINK = GroupLayout.PREFERRED_SIZE
        val GROW = GroupLayout.DEFAULT_SIZE
    }
}