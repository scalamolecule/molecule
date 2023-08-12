package molecule.coreTests.setup

import org.scalactic.{Equality, TripleEquals}
import utest._


trait TolerantEquality extends TripleEquals {

  implicit class tolerantEquality[T](left: T) {
    def ==~(right: Any)(implicit equality: Equality[T]): Unit = {
      if (left !== right) {
        // fallback from unsuccessful tolerant check to strict test in order to show left/right values
        left ==> right
      }
    }
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
}
