package molecule.db.sql.h2

import java.lang as ja
import java.math.BigDecimal as jBigDecimal
import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import upickle.default.{read, write}
import scala.reflect.ClassTag

// H2 helper functions for updates that are likely not possible to implement with H2 SQL.
// Create aliases in database to use functions.
// @see molecule.coreTests.domains.schema.RefsSchema_H2
object functions {

  // Remove values from Set or Seq
  // Example: Entity(id).iSeq.remove(1, 2)
  def removeFromArray_ID /*             */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_String /*         */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_Int /*            */ (base: Array[Integer], remove: Array[Integer]): Array[Integer] = removeFromArray(base, remove)
  def removeFromArray_Long /*           */ (base: Array[ja.Long], remove: Array[ja.Long]): Array[ja.Long] = removeFromArray(base, remove)
  def removeFromArray_Float /*          */ (base: Array[ja.Float], remove: Array[ja.Float]): Array[ja.Float] = removeFromArray(base, remove)
  def removeFromArray_Double /*         */ (base: Array[ja.Double], remove: Array[ja.Double]): Array[ja.Double] = removeFromArray(base, remove)
  def removeFromArray_Boolean /*        */ (base: Array[ja.Boolean], remove: Array[ja.Boolean]): Array[ja.Boolean] = removeFromArray(base, remove)
  def removeFromArray_BigInt /*         */ (base: Array[jBigDecimal], remove: Array[jBigDecimal]): Array[jBigDecimal] = removeFromArray(base, remove)
  def removeFromArray_BigDecimal /*     */ (base: Array[jBigDecimal], remove: Array[jBigDecimal]): Array[jBigDecimal] = removeFromArray(base, remove)
  def removeFromArray_Date /*           */ (base: Array[ja.Long], remove: Array[ja.Long]): Array[ja.Long] = removeFromArray(base, remove)
  def removeFromArray_Duration /*       */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_Instant /*        */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_LocalDate /*      */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_LocalTime /*      */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_LocalDateTime /*  */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_OffsetTime /*     */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_OffsetDateTime /* */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_ZonedDateTime /*  */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_UUID /*           */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_URI /*            */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)
  def removeFromArray_Byte /*           */ (base: Array[ja.Byte], remove: Array[ja.Byte]): Array[ja.Byte] = removeFromArray(base, remove)
  def removeFromArray_Short /*          */ (base: Array[ja.Short], remove: Array[ja.Short]): Array[ja.Short] = removeFromArray(base, remove)
  def removeFromArray_Char /*           */ (base: Array[String], remove: Array[String]): Array[String] = removeFromArray(base, remove)

  private def removeFromArray[T: ClassTag](base: Array[T], remove: Array[T]): Array[T] = {
    if (base == null)
      return null

    if (remove == null || remove.isEmpty)
      return base

    // Exclude all values from remove (could likely be optimized...)
    // For some reason we need to compare scala BigDecimals instead of Java BigDecimals
    val remaining = remove.head match {
      case _: jBigDecimal =>
        val base1   = base.asInstanceOf[Array[jBigDecimal]].map(BigDecimal(_))
        val remove1 = remove.asInstanceOf[Array[jBigDecimal]].map(BigDecimal(_))
        base1.filterNot(remove1.contains).map(_.bigDecimal).asInstanceOf[Array[T]]

      case _ => base.filterNot(remove.contains) // can't use diff since duplicate values are not removed
    }

    if (remaining.isEmpty)
      null // remove attribute entirely
    else
      remaining
  }


  // Adding pairs to Map attributes
  // Example: Entity(id).iMap.add("foo" -> 42)

  type bb = Array[Byte]
  def addPairs_ID /*             */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_ID, decodeMap_ID)
  def addPairs_String /*         */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_String, decodeMap_String)
  def addPairs_Int /*            */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Int, decodeMap_Int)
  def addPairs_Long /*           */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Long, decodeMap_Long)
  def addPairs_Float /*          */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Float, decodeMap_Float)
  def addPairs_Double /*         */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Double, decodeMap_Double)
  def addPairs_Boolean /*        */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Boolean, decodeMap_Boolean)
  def addPairs_BigInt /*         */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_BigInt, decodeMap_BigInt)
  def addPairs_BigDecimal /*     */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_BigDecimal, decodeMap_BigDecimal)
  def addPairs_Date /*           */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Date, decodeMap_Date)
  def addPairs_Duration /*       */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Duration, decodeMap_Duration)
  def addPairs_Instant /*        */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Instant, decodeMap_Instant)
  def addPairs_LocalDate /*      */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_LocalDate, decodeMap_LocalDate)
  def addPairs_LocalTime /*      */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_LocalTime, decodeMap_LocalTime)
  def addPairs_LocalDateTime /*  */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_LocalDateTime, decodeMap_LocalDateTime)
  def addPairs_OffsetTime /*     */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_OffsetTime, decodeMap_OffsetTime)
  def addPairs_OffsetDateTime /* */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_OffsetDateTime, decodeMap_OffsetDateTime)
  def addPairs_ZonedDateTime /*  */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_ZonedDateTime, decodeMap_ZonedDateTime)
  def addPairs_UUID /*           */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_UUID, decodeMap_UUID)
  def addPairs_URI /*            */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_URI, decodeMap_URI)
  def addPairs_Byte /*           */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Byte, decodeMap_Byte)
  def addPairs_Short /*          */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Short, decodeMap_Short)
  def addPairs_Char /*           */ (a: bb, b: bb): bb = addPairs(a, b, encodeMap_Char, decodeMap_Char)

  private def addPairs[T: ClassTag](
    base: bb,
    add: bb,
    decode: String => Map[String, T],
    encode: Map[String, T] => String
  ): bb = {
    val baseMap = if (base == null)
      Map.empty[String, T] // if attribute is not already set (in upserts)
    else
      decode(new String(base))

    if (add == null)
      return base

    val newPairs    = decode(new String(add))
    val expandedMap = baseMap ++ newPairs

    if (expandedMap.isEmpty)
      null // remove attribute entirely
    else
      encode(expandedMap).map(_.toByte).toArray
  }

  // Removing pairs from Map attributes (by String key)
  // Example: Entity(id).iMap.remove("foo")

  def removePairs_ID /*             */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_ID, decodeMap_ID)
  def removePairs_String /*         */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_String, decodeMap_String)
  def removePairs_Int /*            */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Int, decodeMap_Int)
  def removePairs_Long /*           */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Long, decodeMap_Long)
  def removePairs_Float /*          */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Float, decodeMap_Float)
  def removePairs_Double /*         */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Double, decodeMap_Double)
  def removePairs_Boolean /*        */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Boolean, decodeMap_Boolean)
  def removePairs_BigInt /*         */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_BigInt, decodeMap_BigInt)
  def removePairs_BigDecimal /*     */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_BigDecimal, decodeMap_BigDecimal)
  def removePairs_Date /*           */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Date, decodeMap_Date)
  def removePairs_Duration /*       */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Duration, decodeMap_Duration)
  def removePairs_Instant /*        */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Instant, decodeMap_Instant)
  def removePairs_LocalDate /*      */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_LocalDate, decodeMap_LocalDate)
  def removePairs_LocalTime /*      */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_LocalTime, decodeMap_LocalTime)
  def removePairs_LocalDateTime /*  */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_LocalDateTime, decodeMap_LocalDateTime)
  def removePairs_OffsetTime /*     */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_OffsetTime, decodeMap_OffsetTime)
  def removePairs_OffsetDateTime /* */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_OffsetDateTime, decodeMap_OffsetDateTime)
  def removePairs_ZonedDateTime /*  */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_ZonedDateTime, decodeMap_ZonedDateTime)
  def removePairs_UUID /*           */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_UUID, decodeMap_UUID)
  def removePairs_URI /*            */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_URI, decodeMap_URI)
  def removePairs_Byte /*           */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Byte, decodeMap_Byte)
  def removePairs_Short /*          */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Short, decodeMap_Short)
  def removePairs_Char /*           */ (a: bb, b: Array[String]): bb = removePairs(a, b, encodeMap_Char, decodeMap_Char)

  private def removePairs[T: ClassTag](
    base: bb,
    removeKeys: Array[String],
    decode: String => Map[String, T],
    encode: Map[String, T] => String
  ): bb = {
    if (base == null)
      return null

    val baseMap = decode(new String(base))

    if (removeKeys == null || removeKeys.isEmpty)
      return base

    //    val remainingMap = baseMap.removedAll(removeKeys)
    val remainingMap = baseMap.--(removeKeys)

    if (remainingMap.isEmpty)
      null // remove attribute entirely
    else
      encode(remainingMap).map(_.toByte).toArray
  }


  private lazy val encodeMap_ID             = (json: String) => read[Map[String, String]](json)
  private lazy val encodeMap_String         = (json: String) => read[Map[String, String]](json)
  private lazy val encodeMap_Int            = (json: String) => read[Map[String, Int]](json)
  private lazy val encodeMap_Long           = (json: String) => read[Map[String, Long]](json)
  private lazy val encodeMap_Float          = (json: String) => read[Map[String, Float]](json)
  private lazy val encodeMap_Double         = (json: String) => read[Map[String, Double]](json)
  private lazy val encodeMap_Boolean        = (json: String) => read[Map[String, Int]](json).map { case (k, v) => k -> (if (v == 1) true else false) }
  private lazy val encodeMap_BigInt         = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> BigInt(v) }
  private lazy val encodeMap_BigDecimal     = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> BigDecimal(v) }
  private lazy val encodeMap_Date           = (json: String) => read[Map[String, Long]](json).map { case (k, v) => k -> new Date(v) }
  private lazy val encodeMap_Duration       = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> Duration.parse(v) }
  private lazy val encodeMap_Instant        = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> Instant.parse(v) }
  private lazy val encodeMap_LocalDate      = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> LocalDate.parse(v) }
  private lazy val encodeMap_LocalTime      = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> LocalTime.parse(v) }
  private lazy val encodeMap_LocalDateTime  = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> LocalDateTime.parse(v) }
  private lazy val encodeMap_OffsetTime     = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> OffsetTime.parse(v) }
  private lazy val encodeMap_OffsetDateTime = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> OffsetDateTime.parse(v) }
  private lazy val encodeMap_ZonedDateTime  = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> ZonedDateTime.parse(v) }
  private lazy val encodeMap_UUID           = (json: String) => read[Map[String, UUID]](json)
  private lazy val encodeMap_URI            = (json: String) => read[Map[String, String]](json).map { case (k, v) => k -> new URI(v) }
  private lazy val encodeMap_Byte           = (json: String) => read[Map[String, Byte]](json)
  private lazy val encodeMap_Short          = (json: String) => read[Map[String, Short]](json)
  private lazy val encodeMap_Char           = (json: String) => read[Map[String, Char]](json)


  private lazy val decodeMap_ID             = (map: Map[String, String]) => write(map)
  private lazy val decodeMap_String         = (map: Map[String, String]) => write(map)
  private lazy val decodeMap_Int            = (map: Map[String, Int]) => write(map)
  private lazy val decodeMap_Long           = (map: Map[String, Long]) => write(map)
  private lazy val decodeMap_Float          = (map: Map[String, Float]) => write(map)
  private lazy val decodeMap_Double         = (map: Map[String, Double]) => write(map)
  private lazy val decodeMap_Boolean        = (map: Map[String, Boolean]) => write(map.map { case (k, v) => k -> (if (v) 1 else 0) })
  private lazy val decodeMap_BigInt         = (map: Map[String, BigInt]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_BigDecimal     = (map: Map[String, BigDecimal]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_Date           = (map: Map[String, Date]) => write(map.map { case (k, v) => k -> v.getTime })
  private lazy val decodeMap_Duration       = (map: Map[String, Duration]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_Instant        = (map: Map[String, Instant]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_LocalDate      = (map: Map[String, LocalDate]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_LocalTime      = (map: Map[String, LocalTime]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_LocalDateTime  = (map: Map[String, LocalDateTime]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_OffsetTime     = (map: Map[String, OffsetTime]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_OffsetDateTime = (map: Map[String, OffsetDateTime]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_ZonedDateTime  = (map: Map[String, ZonedDateTime]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_UUID           = (map: Map[String, UUID]) => write(map)
  private lazy val decodeMap_URI            = (map: Map[String, URI]) => write(map.map { case (k, v) => k -> v.toString })
  private lazy val decodeMap_Byte           = (map: Map[String, Byte]) => write(map)
  private lazy val decodeMap_Short          = (map: Map[String, Short]) => write(map)
  private lazy val decodeMap_Char           = (map: Map[String, Char]) => write(map)
}