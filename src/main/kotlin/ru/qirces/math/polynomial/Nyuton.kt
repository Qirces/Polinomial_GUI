package ru.qirces.math.polynomial

class Nyuton() : Polynomial() {

    val map = mutableMapOf<Double,Double>()

    constructor( mapp : MutableMap<Double,Double>) : this()
    {
        map.putAll(mapp)

        val res = Polynomial(0.0)
        for (i in 0 until map.size)
            res+=culculation(i)

        _coeff.removeLast()
        _coeff.addAll(res.coeff)
    }

    constructor(other : Nyuton, x:Double, y:Double) : this()
    {
        map.putAll(other.map)
        _coeff.removeLast()
        _coeff.addAll(other.coeff)

        map[x] = y

        this += culculation(map.size - 1)

    }

    private fun culculation(i : Int): Polynomial
    {
        val res : Polynomial
        var sum = 0.0

        val xl = arrayListOf<Double>()
        val yl = arrayListOf<Double>()

        map.forEach { (x,y) ->
            xl.add(x)
            yl.add(y)
        }

        for(j in 0 .. i)
        {
            var product : Double = yl[j]

            for(l in 0..i) if(l!=j) product /= xl[j] - xl[l]

            sum+=product
        }

        res = Polynomial(sum)

        for (j in 0 until i) res *= Polynomial(-xl[j],1.0)

        return res
    }

    fun addNode(x:Double,y:Double) = Nyuton(this,x,y)
}

/*
  init{
      val res = Polynomial()
      map.forEach{ (x, y) ->
          mapp[x] = y
          res += interPoly(x) * y * interPoly()
      }
      _coeff.clear()
      _coeff.addAll(res.coeff)
  }

  private fun interPoly(x: Double) : Polynomial {
      val res = Polynomial(1.0)
      mapp.forEach{ (_x) -> if (_x != x) res *= Polynomial(1.0/(x - _x)) }
      return res
  }
  private fun interPoly() : Polynomial {
      val res = Polynomial(1.0)
      mapp.forEach{ (_x) -> res *= Polynomial(-_x,1.0) }
      return res
  }

  private fun addNode(poly: Polynomial, x:Double, y: Double) : Polynomial{
      val res = Polynomial(y)
      mapp.forEach{ (_x) -> if (_x != x) res *= Polynomial(1.0/(x - _x)) }
      return res + poly
  }
  */
