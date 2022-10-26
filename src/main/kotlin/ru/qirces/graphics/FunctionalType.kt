
/*
package ru.qirces.graphics

class FunctionalType {
    var func : (MutableList<((Int)->Int)?> = mutableListOf<(Int)->Int>()
    fun addEventListener(f: (Int)->Int){
        func.add(f)
    }
    fun removeEventListener(f : (Int)->Int){
        func.remove(f)
    }
    operator fun invoke(x : Int) = List(func.size){func[it](x)}.joinToString()
}

fun f1(x : Int) =  x * x
fun f2(x : Int) = x * x * x
fun main(){

    val t = FunctionalType()
    t.addEventListener{it}
    println("t(2) = ${t(2)}")
    t.addEventListener ::f1)
    t.addEventListener ::f2)

}

 */