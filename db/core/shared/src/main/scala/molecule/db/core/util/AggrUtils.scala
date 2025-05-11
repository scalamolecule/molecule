package molecule.db.core.util

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
    implicit val int       : Avg[Int]        = (vs: Seq[Int]) => vs.sum / vs.size.toDouble
    implicit val long      : Avg[Long]       = (vs: Seq[Long]) => vs.sum.toDouble / vs.size.toDouble
    implicit val float     : Avg[Float]      = (vs: Seq[Float]) => vs.sum / vs.size.toDouble
    implicit val double    : Avg[Double]     = (vs: Seq[Double]) => vs.sum / vs.size.toDouble
    implicit val bigInt    : Avg[BigInt]     = (vs: Seq[BigInt]) => vs.sum.toDouble / vs.size.toDouble
    implicit val bigDecimal: Avg[BigDecimal] = (vs: Seq[BigDecimal]) => (vs.sum / vs.size).toDouble
    implicit val byte      : Avg[Byte]       = (vs: Seq[Byte]) => vs.sum / vs.size.toDouble
    implicit val short     : Avg[Short]      = (vs: Seq[Short]) => vs.sum / vs.size.toDouble
  }
  def averageOf[A](v: A, vs: A*)(implicit avg: Avg[A]): Double = avg.calc(v +: vs)
  def averageOf[A](vs: Seq[A])(implicit avg: Avg[A]): Double = avg.calc(vs)


  trait Variance[A] {
    def calc(vs: Seq[A]): Double
  }
  object Variance {
    implicit val int       : Variance[Int]        = (vs: Seq[Int]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    implicit val long      : Variance[Long]       = (vs: Seq[Long]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    implicit val float     : Variance[Float]      = (vs: Seq[Float]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    implicit val double    : Variance[Double]     = (vs: Seq[Double]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    implicit val bigInt    : Variance[BigInt]     = (vs: Seq[BigInt]) => {
      averageOf(vs.map(x => Math.pow(x.toDouble - averageOf(vs), 2)))
    }
    implicit val bigDecimal: Variance[BigDecimal] = (vs: Seq[BigDecimal]) => {
      averageOf(vs.map(x => Math.pow(x.toDouble - averageOf(vs), 2)))
    }
    implicit val byte      : Variance[Byte]       = (vs: Seq[Byte]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
    implicit val short     : Variance[Short]      = (vs: Seq[Short]) => {
      averageOf(vs.map(x => Math.pow(x - averageOf(vs), 2)))
    }
  }
  def varianceOf[A](v: A, vs: A*)(implicit variance: Variance[A]): Double = variance.calc(v +: vs)
  def varianceOf[A](vs: Seq[A])(implicit variance: Variance[A]): Double = variance.calc(vs)


  trait StdDev[A] {
    def calc(vs: Seq[A]): Double
  }
  object StdDev {
    implicit val int       : StdDev[Int]        = (vs: Seq[Int]) => math.sqrt(varianceOf(vs))
    implicit val long      : StdDev[Long]       = (vs: Seq[Long]) => math.sqrt(varianceOf(vs))
    implicit val float     : StdDev[Float]      = (vs: Seq[Float]) => math.sqrt(varianceOf(vs))
    implicit val double    : StdDev[Double]     = (vs: Seq[Double]) => math.sqrt(varianceOf(vs))
    implicit val bigInt    : StdDev[BigInt]     = (vs: Seq[BigInt]) => math.sqrt(varianceOf(vs))
    implicit val bigDecimal: StdDev[BigDecimal] = (vs: Seq[BigDecimal]) => math.sqrt(varianceOf(vs))
    implicit val byte      : StdDev[Byte]       = (vs: Seq[Byte]) => math.sqrt(varianceOf(vs))
    implicit val short     : StdDev[Short]      = (vs: Seq[Short]) => math.sqrt(varianceOf(vs))
  }
  def stdDevOf[A](v: A, vs: A*)(implicit stdDev: StdDev[A]): Double = stdDev.calc(v +: vs)
  def stdDevOf[A](vs: Seq[A])(implicit stdDev: StdDev[A]): Double = stdDev.calc(vs)
}
