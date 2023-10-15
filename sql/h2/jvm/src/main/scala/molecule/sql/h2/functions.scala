package molecule.sql.h2

import java.math.{BigDecimal => jBigDecimal}
import java.time._
import java.util.Date
import java.{lang => ja}

object functions {

  def has_String(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_Int(array1: Array[Integer], array2: Array[Integer]): Boolean = has(array1, array2)
  def has_Long(array1: Array[ja.Long], array2: Array[ja.Long]): Boolean = has(array1, array2)
  def has_Float(array1: Array[ja.Float], array2: Array[ja.Float]): Boolean = has(array1, array2)
  def has_Double(array1: Array[ja.Double], array2: Array[ja.Double]): Boolean = has(array1, array2)
  def has_Boolean(array1: Array[ja.Boolean], array2: Array[ja.Boolean]): Boolean = has(array1, array2)
  def has_BigInt(array1: Array[jBigDecimal], array2: Array[jBigDecimal]): Boolean = has(array1, array2)
  def has_BigDecimal(array1: Array[jBigDecimal], array2: Array[jBigDecimal]): Boolean = has(array1, array2)
  def has_Date(array1: Array[Date], array2: Array[Date]): Boolean = has(array1, array2)
  def has_Duration(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_Instant(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_LocalDate(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_LocalTime(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_LocalDateTime(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_OffsetTime(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_OffsetDateTime(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_ZonedDateTime(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_UUID(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_URI(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)
  def has_Byte(array1: Array[ja.Byte], array2: Array[ja.Byte]): Boolean = has(array1, array2)
  def has_Short(array1: Array[ja.Short], array2: Array[ja.Short]): Boolean = has(array1, array2)
  def has_Char(array1: Array[String], array2: Array[String]): Boolean = has(array1, array2)

  private def has[T](array1: Array[T], array2: Array[T]): Boolean = {
    if (array1 == null || array2 == null)
      return false

    val lengthA = array1.length
    val lengthB = array2.length

    if (lengthB > lengthA)
      return false

    var count = 0
    var i     = 0
    var j     = 0
    while (i < array1.length) {
      j = 0
      while (j < array2.length) {
        if (array1(i) == array2(j))
          count += 1
        if (count == lengthB)
          return true
        j += 1
      }
      i += 1
    }
    false
  }

  def hasNo_String(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_Int(array1: Array[Integer], array2: Array[Integer]): Boolean = hasNo(array1, array2)
  def hasNo_Long(array1: Array[ja.Long], array2: Array[ja.Long]): Boolean = hasNo(array1, array2)
  def hasNo_Float(array1: Array[ja.Float], array2: Array[ja.Float]): Boolean = hasNo(array1, array2)
  def hasNo_Double(array1: Array[ja.Double], array2: Array[ja.Double]): Boolean = hasNo(array1, array2)
  def hasNo_Boolean(array1: Array[ja.Boolean], array2: Array[ja.Boolean]): Boolean = hasNo(array1, array2)
  def hasNo_BigInt(array1: Array[jBigDecimal], array2: Array[jBigDecimal]): Boolean = hasNo(array1, array2)
  def hasNo_BigDecimal(array1: Array[jBigDecimal], array2: Array[jBigDecimal]): Boolean = hasNo(array1, array2)
  def hasNo_Date(array1: Array[Date], array2: Array[Date]): Boolean = hasNo(array1, array2)
  def hasNo_Duration(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_Instant(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_LocalDate(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_LocalTime(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_LocalDateTime(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_OffsetTime(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_OffsetDateTime(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_ZonedDateTime(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_UUID(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_URI(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)
  def hasNo_Byte(array1: Array[ja.Byte], array2: Array[ja.Byte]): Boolean = hasNo(array1, array2)
  def hasNo_Short(array1: Array[ja.Short], array2: Array[ja.Short]): Boolean = hasNo(array1, array2)
  def hasNo_Char(array1: Array[String], array2: Array[String]): Boolean = hasNo(array1, array2)

  private def hasNo[T](array1: Array[T], array2: Array[T]): Boolean = {
    if (array1 == null || array2 == null)
      return true

    val lengthB = array2.length

    if (lengthB == 0)
      return true

    var i = 0
    var j = 0
    while (i < array1.length) {
      j = 0
      while (j < array2.length) {
        if (array1(i) == array2(j))
          return false
        j += 1
      }
      i += 1
    }
    true
  }
}