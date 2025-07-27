package molecule.core.util

trait AggrUtils {

  def getMedian(list: Seq[Double]): Double = {
    val values = list.sorted
    val count  = values.length
    if (count % 2 == 1) {
      val indexOfMiddleValue = (count - 1) / 2
      values(indexOfMiddleValue)
    } else {
      val i2      = count / 2
      val middle1 = values(i2 - 1)
      val middle2 = values(i2)
      (middle1 + middle2) / 2
    }
  }


  trait Avg[A] {
    def calc(vs: Seq[A]): Double
  }
  object Avg {
    given Avg[Int] = (vs: Seq[Int]) => vs.sum / vs.size.toDouble
    given Avg[Long] = (vs: Seq[Long]) => vs.sum.toDouble / vs.size.toDouble
    given Avg[Float] = (vs: Seq[Float]) => vs.sum / vs.size.toDouble
    given Avg[Double] = (vs: Seq[Double]) => vs.sum / vs.size.toDouble
    given Avg[BigInt] = (vs: Seq[BigInt]) => vs.sum.toDouble / vs.size.toDouble
    given Avg[BigDecimal] = (vs: Seq[BigDecimal]) => (vs.sum / vs.size).toDouble
    given Avg[Byte] = (vs: Seq[Byte]) => vs.sum / vs.size.toDouble
    given Avg[Short] = (vs: Seq[Short]) => vs.sum / vs.size.toDouble
  }
  def averageOf[A](v: A, vs: A*)(using avg: Avg[A]): Double = avg.calc(v +: vs)
  def averageOf[A](vs: Seq[A])(using avg: Avg[A]): Double = avg.calc(vs)


  trait Variance[A] {
    def calc(vs: Seq[A]): Double
  }
  object Variance {
    given Variance[Int] = (vs: Seq[Int]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    given Variance[Long] = (vs: Seq[Long]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    given Variance[Float] = (vs: Seq[Float]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    given Variance[Double] = (vs: Seq[Double]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    given Variance[BigInt] = (vs: Seq[BigInt]) => {
      averageOf(vs.map(x => Math.pow(x.toDouble - averageOf(vs), 2)))
    }
    given Variance[BigDecimal] = (vs: Seq[BigDecimal]) => {
      averageOf(vs.map(x => Math.pow(x.toDouble - averageOf(vs), 2)))
    }
    given Variance[Byte] = (vs: Seq[Byte]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    given Variance[Short] = (vs: Seq[Short]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
  }
  def varianceOf[A](v: A, vs: A*)(using variance: Variance[A]): Double = variance.calc(v +: vs)
  def varianceOf[A](vs: Seq[A])(using variance: Variance[A]): Double = variance.calc(vs)


  trait StdDev[A] {
    def calc(vs: Seq[A]): Double
  }
  object StdDev {
    given StdDev[Int]        = (vs: Seq[Int]) => math.sqrt(varianceOf(vs))
    given StdDev[Long]       = (vs: Seq[Long]) => math.sqrt(varianceOf(vs))
    given StdDev[Float]      = (vs: Seq[Float]) => math.sqrt(varianceOf(vs))
    given StdDev[Double]     = (vs: Seq[Double]) => math.sqrt(varianceOf(vs))
    given StdDev[BigInt]     = (vs: Seq[BigInt]) => math.sqrt(varianceOf(vs))
    given StdDev[BigDecimal] = (vs: Seq[BigDecimal]) => math.sqrt(varianceOf(vs))
    given StdDev[Byte]       = (vs: Seq[Byte]) => math.sqrt(varianceOf(vs))
    given StdDev[Short]      = (vs: Seq[Short]) => math.sqrt(varianceOf(vs))
  }
  def stdDevOf[A](v: A, vs: A*)(using stdDev: StdDev[A]): Double = stdDev.calc(v +: vs)
  def stdDevOf[A](vs: Seq[A])(using stdDev: StdDev[A]): Double = stdDev.calc(vs)
}
