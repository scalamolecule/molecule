package molecule.coreTests.util

trait AggrUtils {

  // Down-casting BigInt/BigDecimal calculations to double just for tests


//  trait Median[A] {
//    def calc(vs: Seq[A]): Double
//  }
//  object Median {
//    implicit val int       : Median[Int]        = (vs: Seq[Int]) => math.sqrt(varianceOf(vs: _*))
//    implicit val long      : Median[Long]       = (vs: Seq[Long]) => math.sqrt(varianceOf(vs: _*))
//    implicit val float     : Median[Float]      = (vs: Seq[Float]) => {
//      val sortedSeq = vs.sortWith(_ < _)
//      if (vs.size % 2 == 1)
//        sortedSeq(sortedSeq.size / 2)
//      else {
//        val (up, down) = sortedSeq.splitAt(vs.size / 2)
//        (up.last + down.head) / 2.0f
//      }
//    }
//    implicit val double    : Median[Double]     = (vs: Seq[Double]) => math.sqrt(varianceOf(vs: _*))
//    implicit val bigInt    : Median[BigInt]     = (vs: Seq[BigInt]) => math.sqrt(varianceOf(vs: _*))
//    implicit val bigDecimal: Median[BigDecimal] = (vs: Seq[BigDecimal]) => math.sqrt(varianceOf(vs: _*))
//    implicit val byte      : Median[Byte]       = (vs: Seq[Byte]) => math.sqrt(varianceOf(vs: _*))
//    implicit val short     : Median[Short]      = (vs: Seq[Short]) => math.sqrt(varianceOf(vs: _*))
//  }
//  def medianOf[A](vs: A*)(implicit median: Median[A]): Double = median.calc(vs)


  trait Avg[A] {
    def calc(vs: Seq[A]): Double
  }
  object Avg {
    implicit val int       : Avg[Int]        = (vs: Seq[Int]) => vs.sum / vs.size.toDouble
    implicit val long      : Avg[Long]       = (vs: Seq[Long]) => vs.sum.toDouble / vs.size.toDouble
    implicit val float     : Avg[Float]      = (vs: Seq[Float]) => vs.sum / vs.size.toDouble
    implicit val double    : Avg[Double]     = (vs: Seq[Double]) => vs.sum / vs.size.toDouble
    implicit val bigInt    : Avg[BigInt]     = (vs: Seq[BigInt]) => (vs.sum / vs.size).toDouble
    implicit val bigDecimal: Avg[BigDecimal] = (vs: Seq[BigDecimal]) => (vs.sum / vs.size).toDouble
    implicit val byte      : Avg[Byte]       = (vs: Seq[Byte]) => vs.sum / vs.size.toDouble
    implicit val short     : Avg[Short]      = (vs: Seq[Short]) => vs.sum / vs.size.toDouble
  }
  def averageOf[A](vs: A*)(implicit avg: Avg[A]): Double = avg.calc(vs)


  trait Variance[A] {
    def calc(vs: Seq[A]): Double
  }
  object Variance {
    implicit val int       : Variance[Int]        = (vs: Seq[Int]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x - m, 2)): _*)
    }
    implicit val long      : Variance[Long]       = (vs: Seq[Long]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x - m, 2)): _*)
    }
    implicit val float     : Variance[Float]      = (vs: Seq[Float]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x - m, 2)): _*)
    }
    implicit val double    : Variance[Double]     = (vs: Seq[Double]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x - m, 2)): _*)
    }
    implicit val bigInt    : Variance[BigInt]     = (vs: Seq[BigInt]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x.toDouble - m, 2)): _*)
    }
    implicit val bigDecimal: Variance[BigDecimal] = (vs: Seq[BigDecimal]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x.toDouble - m, 2)): _*)
    }
    implicit val byte      : Variance[Byte]       = (vs: Seq[Byte]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x - m, 2)): _*)
    }
    implicit val short     : Variance[Short]      = (vs: Seq[Short]) => {
      val m = averageOf(vs: _*)
      averageOf(vs.map(x => Math.pow(x - m, 2)): _*)
    }
  }
  def varianceOf[A](vs: A*)(implicit variance: Variance[A]): Double = variance.calc(vs)


  trait StdDev[A] {
    def calc(vs: Seq[A]): Double
  }
  object StdDev {
    implicit val int       : StdDev[Int]        = (vs: Seq[Int]) => math.sqrt(varianceOf(vs: _*))
    implicit val long      : StdDev[Long]       = (vs: Seq[Long]) => math.sqrt(varianceOf(vs: _*))
    implicit val float     : StdDev[Float]      = (vs: Seq[Float]) => math.sqrt(varianceOf(vs: _*))
    implicit val double    : StdDev[Double]     = (vs: Seq[Double]) => math.sqrt(varianceOf(vs: _*))
    implicit val bigInt    : StdDev[BigInt]     = (vs: Seq[BigInt]) => math.sqrt(varianceOf(vs: _*))
    implicit val bigDecimal: StdDev[BigDecimal] = (vs: Seq[BigDecimal]) => math.sqrt(varianceOf(vs: _*))
    implicit val byte      : StdDev[Byte]       = (vs: Seq[Byte]) => math.sqrt(varianceOf(vs: _*))
    implicit val short     : StdDev[Short]      = (vs: Seq[Short]) => math.sqrt(varianceOf(vs: _*))
  }
  def stdDevOf[A](vs: A*)(implicit stdDev: StdDev[A]): Double = stdDev.calc(vs)
}
