package molecule.coreTests.setup

import org.scalactic.{Equality, TripleEquals}


trait TolerantEquality extends TripleEquals {


  implicit class tolerantEquality[T](lhs: T) {
    def ==~(rhs: Any)(implicit equality: Equality[T]): Unit = {
      (lhs, rhs) match {

        // Simple hack to allow tolerant-comparing Lists of small tuples
        case (l: List[_], r: List[_]) if l.nonEmpty && r.nonEmpty => l.head match {
          case _: Product3[_, _, _] =>
            l.zip(r).foreach {
              case ((a, b, c), (x, y, z)) =>
                try {
                  check(a, x)
                  check(b, y)
                  check(c, z)
                } catch {
                  case _: Exception => hardCheck(lhs, rhs)
                }
            }
          case _: Product2[_, _]    =>
            l.zip(r).foreach {
              case ((a, b), (x, y)) =>
                try {
                  check(a, x)
                  check(b, y)
                } catch {
                  case _: Exception => hardCheck(lhs, rhs)
                }
            }
          case _                    =>
            l.zip(r).foreach {
              case (a, x) =>
                try {
                  check(a, x)
                } catch {
                  case _: Exception => hardCheck(lhs, rhs)
                }
            }
        }

        case _ =>
          if (lhs !== rhs) {
            hardCheck(lhs, rhs)
          }
      }
    }
  }

  private def check(lhs: Any, rhs: Any): Unit = {
    lhs match {
      case v: Float      =>
        implicit val tolerance = tolerantFloatEquality(0.001F)
        if (v !== rhs) {
          throw new Exception("tolerant check failed")
        }
      case v: Double     =>
        implicit val tolerance = tolerantDoubleEquality(0.001)
        if (v !== rhs) {
          throw new Exception("tolerant check failed")
        }
      case v: BigDecimal =>
        implicit val tolerance = tolerantBigDecimalEquality(BigDecimal(0.001))
        if (v !== rhs) {
          throw new Exception("tolerant check failed")
        }
      case v             =>
        if (v !== rhs) {
          throw new Exception("tolerant check failed")
        }
    }
  }

  private def hardCheck(lhs: Any, rhs: Any): Unit = {
    Predef.assert(lhs == rhs,
      s"""
         |Got     : $lhs
         |Expected: $rhs
         |""".stripMargin
    )
  }


  def tolerantBigDecimalEquality(tolerance: BigDecimal): Equality[BigDecimal] = {
    new Equality[BigDecimal] {
      def areEqual(a: BigDecimal, b: Any): Boolean = {
        b match {
          case bBigDecimal: BigDecimal => (a <= bBigDecimal + tolerance) && (a >= bBigDecimal - tolerance)
          case _                       => false
        }
      }
      override def toString: String = s"TolerantBigDecimalEquality($tolerance)"
    }
  }

  def tolerantDoubleEquality(tolerance: Double): Equality[Double] = {
    new Equality[Double] {
      def areEqual(a: Double, b: Any): Boolean = {
        b match {
          case bDouble: Double => (a <= bDouble + tolerance) && (a >= bDouble - tolerance)
          case _               => false
        }
      }
      override def toString: String = s"TolerantDoubleEquality($tolerance)"
    }
  }

  def tolerantFloatEquality(tolerance: Float): Equality[Float] = {
    new Equality[Float] {
      def areEqual(a: Float, b: Any): Boolean = {
        b match {
          case bFloat: Float => (a <= bFloat + tolerance) && (a >= bFloat - tolerance)
          case _             => false
        }
      }
      override def toString: String = s"TolerantFloatEquality($tolerance)"
    }
  }

  def tolerantBigIntEquality(tolerance: BigInt): Equality[BigInt] = {
    new Equality[BigInt] {
      def areEqual(a: BigInt, b: Any): Boolean = {
        b match {
          case bBigInt: BigInt => (a <= bBigInt + tolerance) && (a >= bBigInt - tolerance)
          case _               => false
        }
      }
      override def toString: String = s"TolerantBigIntEquality($tolerance)"
    }
  }

  def tolerantLongEquality(tolerance: Long): Equality[Long] = {
    new Equality[Long] {
      def areEqual(a: Long, b: Any): Boolean = {
        b match {
          case bLong: Long => (a <= bLong + tolerance) && (a >= bLong - tolerance)
          case _           => false
        }
      }
      override def toString: String = s"TolerantLongEquality($tolerance)"
    }
  }

  def tolerantIntEquality(tolerance: Int): Equality[Int] = {
    new Equality[Int] {
      def areEqual(a: Int, b: Any): Boolean = {
        b match {
          case bInt: Int => (a <= bInt + tolerance) && (a >= bInt - tolerance)
          case _         => false
        }
      }
      override def toString: String = s"TolerantIntEquality($tolerance)"
    }
  }

  def tolerantShortEquality(tolerance: Short): Equality[Short] = {
    new Equality[Short] {
      def areEqual(a: Short, b: Any): Boolean = {
        b match {
          case bShort: Short => (a <= bShort + tolerance) && (a >= bShort - tolerance)
          case _             => false
        }
      }
      override def toString: String = s"TolerantShortEquality($tolerance)"
    }
  }

  def tolerantByteEquality(tolerance: Byte): Equality[Byte] = {
    new Equality[Byte] {
      def areEqual(a: Byte, b: Any): Boolean = {
        b match {
          case bByte: Byte => (a <= bByte + tolerance) && (a >= bByte - tolerance)
          case _           => false
        }
      }
      override def toString: String = s"TolerantByteEquality($tolerance)"
    }
  }


  //  private def check[T](lhs: T, rhs: Any)(implicit equality: Equality[T]): Unit = {
  //    //  private def check[T](lhs: T, rhs: Any): Unit = {
  //    if (lhs !== rhs) {
  //      // fallback from unsuccessful tolerant check to strict test in order to show left/right values
  //      lhs ==> rhs
  //    }
  //  }
}
