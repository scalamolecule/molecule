package molecule.sql.h2.query

import java.net.URI
import java.util.{Date, UUID}
import molecule.core.util.JavaConversions
import molecule.sql.core.query.{ResolveBase, SqlQueryBase}

trait LambdasSet_h2 extends ResolveBase with JavaConversions { self: SqlQueryBase =>

  protected lazy val sql2setString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => sqlArray2set(row, n, valueString)
  protected lazy val sql2setInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => sqlArray2set(row, n, valueInt)
  protected lazy val sql2setLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => sqlArray2set(row, n, valueLong)
  protected lazy val sql2setFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => sqlArray2set(row, n, valueFloat)
  protected lazy val sql2setDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => sqlArray2set(row, n, valueDouble)
  protected lazy val sql2setBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => sqlArray2set(row, n, valueBoolean)
  protected lazy val sql2setBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => sqlArray2set(row, n, valueBigInt)
  protected lazy val sql2setBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => sqlArray2set(row, n, valueBigDecimal)
  protected lazy val sql2setDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => sqlArray2set(row, n, valueDate)
  protected lazy val sql2setUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => sqlArray2set(row, n, valueUUID)
  protected lazy val sql2setURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => sqlArray2set(row, n, valueURI)
  protected lazy val sql2setByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => sqlArray2set(row, n, valueByte)
  protected lazy val sql2setShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => sqlArray2set(row, n, valueShort)
  protected lazy val sql2setChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => sqlArray2set(row, n, valueChar)

  case class ResSet[T](
    tpe: String,
    sql2set: (Row, AttrIndex) => Set[T],
    sql2setOrNull: (Row, AttrIndex) => Any, // Allow null in optional nested rows
    set2sqlArray: Set[T] => String,
    set2sqls: Set[T] => Set[String],
    one2sql: T => String,
    array2set: (Row, Int) => Set[T],
    nestedArray2coalescedSet: (Row, Int) => Set[T],
    nestedArray2nestedSet: (Row, Int) => Set[Set[T]],
    array2setFirst: (Row, Int) => Set[T],
    array2setLast: (Row, Int) => Set[T],
    nestedArray2setAsc: Int => (Row, Int) => Set[T],
    nestedArray2setDesc: Int => (Row, Int) => Set[T],
    array2setSum: (Row, Int) => Set[T],
  )

  lazy val resSetString    : ResSet[String]     = ResSet("String", sql2setString, null, set2sqlArrayString, set2sqlsString, one2sqlString, array2setString, nestedArray2coalescedSetString, nestedArray2nestedSetString, array2setFirstString, array2setLastString, nestedArray2setAscString, nestedArray2setDescString, nestedArray2setSumString)
  lazy val resSetInt       : ResSet[Int]        = ResSet("Int", sql2setInt, null, set2sqlArrayInt, set2sqlsInt, one2sqlInt, array2setInt, nestedArray2coalescedSetInt, nestedArray2nestedSetInt, array2setFirstInt, array2setLastInt, nestedArray2setAscInt, nestedArray2setDescInt, nestedArray2setSumInt)
  lazy val resSetLong      : ResSet[Long]       = ResSet("Long", sql2setLong, null, set2sqlArrayLong, set2sqlsLong, one2sqlLong, array2setLong, nestedArray2coalescedSetLong, nestedArray2nestedSetLong, array2setFirstLong, array2setLastLong, nestedArray2setAscLong, nestedArray2setDescLong, nestedArray2setSumLong)
  lazy val resSetFloat     : ResSet[Float]      = ResSet("Float", sql2setFloat, null, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat, array2setFloat, nestedArray2coalescedSetFloat, nestedArray2nestedSetFloat, array2setFirstFloat, array2setLastFloat, nestedArray2setAscFloat, nestedArray2setDescFloat, nestedArray2setSumFloat)
  lazy val resSetDouble    : ResSet[Double]     = ResSet("Double", sql2setDouble, null, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble, array2setDouble, nestedArray2coalescedSetDouble, nestedArray2nestedSetDouble, array2setFirstDouble, array2setLastDouble, nestedArray2setAscDouble, nestedArray2setDescDouble, nestedArray2setSumDouble)
  lazy val resSetBoolean   : ResSet[Boolean]    = ResSet("Boolean", sql2setBoolean, null, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean, array2setBoolean, nestedArray2coalescedSetBoolean, nestedArray2nestedSetBoolean, array2setFirstBoolean, array2setLastBoolean, nestedArray2setAscBoolean, nestedArray2setDescBoolean, nestedArray2setSumBoolean)
  lazy val resSetBigInt    : ResSet[BigInt]     = ResSet("BigInt", sql2setBigInt, null, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt, array2setBigInt, nestedArray2coalescedSetBigInt, nestedArray2nestedSetBigInt, array2setFirstBigInt, array2setLastBigInt, nestedArray2setAscBigInt, nestedArray2setDescBigInt, nestedArray2setSumBigInt)
  lazy val resSetBigDecimal: ResSet[BigDecimal] = ResSet("BigDecimal", sql2setBigDecimal, null, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, array2setBigDecimal, nestedArray2coalescedSetBigDecimal, nestedArray2nestedSetBigDecimal, array2setFirstBigDecimal, array2setLastBigDecimal, nestedArray2setAscBigDecimal, nestedArray2setDescBigDecimal, nestedArray2setSumBigDecimal)
  lazy val resSetDate      : ResSet[Date]       = ResSet("Date", sql2setDate, null, set2sqlArrayDate, set2sqlsDate, one2sqlDate, array2setDate, nestedArray2coalescedSetDate, nestedArray2nestedSetDate, array2setFirstDate, array2setLastDate, nestedArray2setAscDate, nestedArray2setDescDate, nestedArray2setSumDate)
  lazy val resSetUUID      : ResSet[UUID]       = ResSet("UUID", sql2setUUID, null, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID, array2setUUID, nestedArray2coalescedSetUUID, nestedArray2nestedSetUUID, array2setFirstUUID, array2setLastUUID, nestedArray2setAscUUID, nestedArray2setDescUUID, nestedArray2setSumUUID)
  lazy val resSetURI       : ResSet[URI]        = ResSet("URI", sql2setURI, null, set2sqlArrayURI, set2sqlsURI, one2sqlURI, array2setURI, nestedArray2coalescedSetURI, nestedArray2nestedSetURI, array2setFirstURI, array2setLastURI, nestedArray2setAscURI, nestedArray2setDescURI, nestedArray2setSumURI)
  lazy val resSetByte      : ResSet[Byte]       = ResSet("Byte", sql2setByte, null, set2sqlArrayByte, set2sqlsByte, one2sqlByte, array2setByte, nestedArray2coalescedSetByte, nestedArray2nestedSetByte, array2setFirstByte, array2setLastByte, nestedArray2setAscByte, nestedArray2setDescByte, nestedArray2setSumByte)
  lazy val resSetShort     : ResSet[Short]      = ResSet("Short", sql2setShort, null, set2sqlArrayShort, set2sqlsShort, one2sqlShort, array2setShort, nestedArray2coalescedSetShort, nestedArray2nestedSetShort, array2setFirstShort, array2setLastShort, nestedArray2setAscShort, nestedArray2setDescShort, nestedArray2setSumShort)
  lazy val resSetChar      : ResSet[Char]       = ResSet("Char", sql2setChar, null, set2sqlArrayChar, set2sqlsChar, one2sqlChar, array2setChar, nestedArray2coalescedSetChar, nestedArray2nestedSetChar, array2setFirstChar, array2setLastChar, nestedArray2setAscChar, nestedArray2setDescChar, nestedArray2setSumChar)

  protected lazy val set2sqlArrayString    : Set[String] => String     = (set: Set[String]) => set.map(_.replace("'", "''")).mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlArrayInt       : Set[Int] => String        = (set: Set[Int]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayLong      : Set[Long] => String       = (set: Set[Long]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayFloat     : Set[Float] => String      = (set: Set[Float]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayDouble    : Set[Double] => String     = (set: Set[Double]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayBoolean   : Set[Boolean] => String    = (set: Set[Boolean]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayBigInt    : Set[BigInt] => String     = (set: Set[BigInt]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayBigDecimal: Set[BigDecimal] => String = (set: Set[BigDecimal]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayDate      : Set[Date] => String       = (set: Set[Date]) => set.map(date2str(_)).mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlArrayUUID      : Set[UUID] => String       = (set: Set[UUID]) => set.mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlArrayURI       : Set[URI] => String        = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlArrayByte      : Set[Byte] => String       = (set: Set[Byte]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayShort     : Set[Short] => String      = (set: Set[Short]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlArrayChar      : Set[Char] => String       = (set: Set[Char]) => set.mkString("ARRAY ['", "', '", "']")

  protected lazy val set2sqlsString    : Set[String] => Set[String]     = (set: Set[String]) => set.map(_.replace("'", "''")).map(v => s"'$v'")
  protected lazy val set2sqlsInt       : Set[Int] => Set[String]        = (set: Set[Int]) => set.map(_.toString)
  protected lazy val set2sqlsLong      : Set[Long] => Set[String]       = (set: Set[Long]) => set.map(_.toString)
  protected lazy val set2sqlsFloat     : Set[Float] => Set[String]      = (set: Set[Float]) => set.map(_.toString)
  protected lazy val set2sqlsDouble    : Set[Double] => Set[String]     = (set: Set[Double]) => set.map(_.toString)
  protected lazy val set2sqlsBoolean   : Set[Boolean] => Set[String]    = (set: Set[Boolean]) => set.map(_.toString)
  protected lazy val set2sqlsBigInt    : Set[BigInt] => Set[String]     = (set: Set[BigInt]) => set.map(_.toString)
  protected lazy val set2sqlsBigDecimal: Set[BigDecimal] => Set[String] = (set: Set[BigDecimal]) => set.map(_.toString)
  protected lazy val set2sqlsDate      : Set[Date] => Set[String]       = (set: Set[Date]) => set.map(d => "'" + date2str(d) + "'")
  protected lazy val set2sqlsUUID      : Set[UUID] => Set[String]       = (set: Set[UUID]) => set.map(v => s"'$v'")
  protected lazy val set2sqlsURI       : Set[URI] => Set[String]        = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).map(v => s"'$v'")
  protected lazy val set2sqlsByte      : Set[Byte] => Set[String]       = (set: Set[Byte]) => set.map(_.toString)
  protected lazy val set2sqlsShort     : Set[Short] => Set[String]      = (set: Set[Short]) => set.map(_.toString)
  protected lazy val set2sqlsChar      : Set[Char] => Set[String]       = (set: Set[Char]) => set.map(v => s"'$v'")


  protected lazy val array2setString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => sqlArray2set(row, n, valueString)
  protected lazy val array2setInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => sqlArray2set(row, n, valueInt)
  protected lazy val array2setLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => sqlArray2set(row, n, valueLong)
  protected lazy val array2setFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => sqlArray2set(row, n, valueFloat)
  protected lazy val array2setDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => sqlArray2set(row, n, valueDouble)
  protected lazy val array2setBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => sqlArray2set(row, n, valueBoolean)
  protected lazy val array2setBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => sqlArray2set(row, n, valueBigInt)
  protected lazy val array2setBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => sqlArray2set(row, n, valueBigDecimal)
  protected lazy val array2setDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => sqlArray2set(row, n, valueDate)
  protected lazy val array2setUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => sqlArray2set(row, n, valueUUID)
  protected lazy val array2setURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => sqlArray2set(row, n, valueURI)
  protected lazy val array2setByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => sqlArray2set(row, n, valueByte)
  protected lazy val array2setShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => sqlArray2set(row, n, valueShort)
  protected lazy val array2setChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => sqlArray2set(row, n, valueChar)


  protected def sqlNestedArrays2coalescedSet[T](row: Row, n: Int, j2s: Any => T): Set[T] = {
    val array = row.getArray(n)
    if (row.wasNull()) {
      Set.empty[T]
    } else {
      val outerArrayResultSet = array.getResultSet
      var set                 = Set.empty[T]
      while (outerArrayResultSet.next()) {
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          set += j2s(value)
        }
      }
      set
    }
  }
  protected lazy val nestedArray2coalescedSetString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2String)
  protected lazy val nestedArray2coalescedSetInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Int)
  protected lazy val nestedArray2coalescedSetLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Long)
  protected lazy val nestedArray2coalescedSetFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Float)
  protected lazy val nestedArray2coalescedSetDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Double)
  protected lazy val nestedArray2coalescedSetBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Boolean)
  protected lazy val nestedArray2coalescedSetBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2BigInt)
  protected lazy val nestedArray2coalescedSetBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2BigDecimal)
  protected lazy val nestedArray2coalescedSetDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Date)
  protected lazy val nestedArray2coalescedSetUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2UUID)
  protected lazy val nestedArray2coalescedSetURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2String).map(v => new URI(v))
  protected lazy val nestedArray2coalescedSetByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Byte)
  protected lazy val nestedArray2coalescedSetShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Short)
  protected lazy val nestedArray2coalescedSetChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Char)

  protected lazy val nestedArray2setAscString    : Int => (Row, Int) => Set[String]     = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2String).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscInt       : Int => (Row, Int) => Set[Int]        = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Int).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscLong      : Int => (Row, Int) => Set[Long]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Long).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscFloat     : Int => (Row, Int) => Set[Float]      = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Float).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscDouble    : Int => (Row, Int) => Set[Double]     = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Double).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscBoolean   : Int => (Row, Int) => Set[Boolean]    = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Boolean).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscBigInt    : Int => (Row, Int) => Set[BigInt]     = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2BigInt).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscBigDecimal: Int => (Row, Int) => Set[BigDecimal] = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2BigDecimal).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscDate      : Int => (Row, Int) => Set[Date]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Date).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscUUID      : Int => (Row, Int) => Set[UUID]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2UUID).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscURI       : Int => (Row, Int) => Set[URI]        = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2String).toList.sorted.take(size).map(s => new URI(s)).toSet
  protected lazy val nestedArray2setAscByte      : Int => (Row, Int) => Set[Byte]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Byte).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscShort     : Int => (Row, Int) => Set[Short]      = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Short).toList.sorted.take(size).toSet
  protected lazy val nestedArray2setAscChar      : Int => (Row, Int) => Set[Char]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Char).toList.sorted.take(size).toSet

  protected lazy val nestedArray2setDescString    : Int => (Row, Int) => Set[String]     = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2String).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescInt       : Int => (Row, Int) => Set[Int]        = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Int).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescLong      : Int => (Row, Int) => Set[Long]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Long).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescFloat     : Int => (Row, Int) => Set[Float]      = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Float).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescDouble    : Int => (Row, Int) => Set[Double]     = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Double).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescBoolean   : Int => (Row, Int) => Set[Boolean]    = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Boolean).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescBigInt    : Int => (Row, Int) => Set[BigInt]     = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2BigInt).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescBigDecimal: Int => (Row, Int) => Set[BigDecimal] = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2BigDecimal).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescDate      : Int => (Row, Int) => Set[Date]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Date).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescUUID      : Int => (Row, Int) => Set[UUID]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2UUID).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescURI       : Int => (Row, Int) => Set[URI]        = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2String).toList.sorted.takeRight(size).map(s => new URI(s)).toSet
  protected lazy val nestedArray2setDescByte      : Int => (Row, Int) => Set[Byte]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Byte).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescShort     : Int => (Row, Int) => Set[Short]      = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Short).toList.sorted.takeRight(size).toSet
  protected lazy val nestedArray2setDescChar      : Int => (Row, Int) => Set[Char]       = (size: Int) => (row: Row, n: Int) => sqlNestedArrays2coalescedSet(row, n, j2Char).toList.sorted.takeRight(size).toSet

  protected def onlyNumbers = throw new Exception("Casting only for numbers.")

  protected lazy val nestedArray2setSumString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => onlyNumbers
  protected lazy val nestedArray2setSumInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Int](row, n, j2Int).sum)
  protected lazy val nestedArray2setSumLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Long](row, n, j2Long).sum)
  protected lazy val nestedArray2setSumFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Float](row, n, j2Float).sum)
  protected lazy val nestedArray2setSumDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Double](row, n, j2Double).sum)
  protected lazy val nestedArray2setSumBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => onlyNumbers
  protected lazy val nestedArray2setSumBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[BigInt](row, n, j2BigInt).sum)
  protected lazy val nestedArray2setSumBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[BigDecimal](row, n, j2BigDecimal).sum)
  protected lazy val nestedArray2setSumDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => onlyNumbers
  protected lazy val nestedArray2setSumUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => onlyNumbers
  protected lazy val nestedArray2setSumURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => onlyNumbers
  protected lazy val nestedArray2setSumByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Byte](row, n, j2Byte).sum)
  protected lazy val nestedArray2setSumShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Short](row, n, j2Short).sum)
  protected lazy val nestedArray2setSumChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Char](row, n, j2Char).sum)


  protected lazy val j2String    : Any => String     = (v: Any) => v.asInstanceOf[String]
  protected lazy val j2Int       : Any => Int        = (v: Any) => v.toString.toInt
  protected lazy val j2Long      : Any => Long       = (v: Any) => v.asInstanceOf[Long]
  protected lazy val j2Float     : Any => Float      = (v: Any) => v.asInstanceOf[Float]
  protected lazy val j2Double    : Any => Double     = (v: Any) => v.asInstanceOf[Double]
  protected lazy val j2Boolean   : Any => Boolean    = (v: Any) => v.asInstanceOf[Boolean]
  protected lazy val j2BigInt    : Any => BigInt     = (v: Any) => BigInt(v.toString)
  protected lazy val j2BigDecimal: Any => BigDecimal = (v: Any) => BigDecimal(v.toString)
  protected lazy val j2Date      : Any => Date       = (v: Any) => v.asInstanceOf[Date]
  protected lazy val j2UUID      : Any => UUID       = (v: Any) => v.asInstanceOf[UUID]
  protected lazy val j2URI       : Any => URI        = (v: Any) => v.asInstanceOf[URI]
  protected lazy val j2Byte      : Any => Byte       = (v: Any) => v.asInstanceOf[Integer].toByte
  protected lazy val j2Short     : Any => Short      = (v: Any) => v.asInstanceOf[Integer].toShort
  protected lazy val j2Char      : Any => Char       = (v: Any) => v.asInstanceOf[String].charAt(0)


  protected def sqlNestedArrays2nestedSet[T](row: Row, n: Int, getValue: Any => T): Set[Set[T]] = {
    val array = row.getArray(n)
    if (row.wasNull()) {
      Set.empty[Set[T]]
    } else {
      val outerArrayResultSet = array.getResultSet
      var sets                = Set.empty[Set[T]]
      while (outerArrayResultSet.next()) {
        var set = Set.empty[T]
        outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
          set += getValue(value)
        }
        sets += set
      }
      sets
    }
  }

  protected lazy val nestedArray2nestedSetString    : (Row, Int) => Set[Set[String]]     = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2String)
  protected lazy val nestedArray2nestedSetInt       : (Row, Int) => Set[Set[Int]]        = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Int)
  protected lazy val nestedArray2nestedSetLong      : (Row, Int) => Set[Set[Long]]       = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Long)
  protected lazy val nestedArray2nestedSetFloat     : (Row, Int) => Set[Set[Float]]      = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Float)
  protected lazy val nestedArray2nestedSetDouble    : (Row, Int) => Set[Set[Double]]     = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Double)
  protected lazy val nestedArray2nestedSetBoolean   : (Row, Int) => Set[Set[Boolean]]    = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Boolean)
  protected lazy val nestedArray2nestedSetBigInt    : (Row, Int) => Set[Set[BigInt]]     = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2BigInt)
  protected lazy val nestedArray2nestedSetBigDecimal: (Row, Int) => Set[Set[BigDecimal]] = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2BigDecimal)
  protected lazy val nestedArray2nestedSetDate      : (Row, Int) => Set[Set[Date]]       = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Date)
  protected lazy val nestedArray2nestedSetUUID      : (Row, Int) => Set[Set[UUID]]       = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2UUID)
  protected lazy val nestedArray2nestedSetURI       : (Row, Int) => Set[Set[URI]]        = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, (v: Any) => new URI(j2String(v)))
  protected lazy val nestedArray2nestedSetByte      : (Row, Int) => Set[Set[Byte]]       = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Byte)
  protected lazy val nestedArray2nestedSetShort     : (Row, Int) => Set[Set[Short]]      = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Short)
  protected lazy val nestedArray2nestedSetChar      : (Row, Int) => Set[Set[Char]]       = (row: Row, n: Int) => sqlNestedArrays2nestedSet(row, n, j2Char)


  protected lazy val array2setFirstString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).min)
  protected lazy val array2setFirstInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Int).min)
  protected lazy val array2setFirstLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Long).min)
  protected lazy val array2setFirstFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Float).min)
  protected lazy val array2setFirstDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Double).min)
  protected lazy val array2setFirstBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Boolean).min)
  protected lazy val array2setFirstBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigInt).min)
  protected lazy val array2setFirstBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).min)
  protected lazy val array2setFirstDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Date).min)
  protected lazy val array2setFirstUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2UUID).min)
  protected lazy val array2setFirstURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => Set(new URI(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).min)) // URI saved as String
  protected lazy val array2setFirstByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Byte).min)
  protected lazy val array2setFirstShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Short).min)
  protected lazy val array2setFirstChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Char).min)

  protected lazy val array2setLastString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).max)
  protected lazy val array2setLastInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Int).max)
  protected lazy val array2setLastLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Long).max)
  protected lazy val array2setLastFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Float).max)
  protected lazy val array2setLastDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Double).max)
  protected lazy val array2setLastBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Boolean).max)
  protected lazy val array2setLastBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigInt).max)
  protected lazy val array2setLastBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).max)
  protected lazy val array2setLastDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Date).max)
  protected lazy val array2setLastUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2UUID).max)
  protected lazy val array2setLastURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => Set(new URI(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).max)) // URI saved as String
  protected lazy val array2setLastByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Byte).max)
  protected lazy val array2setLastShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Short).max)
  protected lazy val array2setLastChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Char).max)


  case class ResSetOpt[T](
    tpe: String,
    sql2setOpt: (Row, AttrIndex) => Option[Set[T]],
    set2sqlArray: Set[T] => String,
    set2sqls: Set[T] => Set[String],
    one2sql: T => String,
  )

  lazy val resOptSetString    : ResSetOpt[String]     = ResSetOpt("String", sql2setOptString, set2sqlArrayString, set2sqlsString, one2sqlString)
  lazy val resOptSetInt       : ResSetOpt[Int]        = ResSetOpt("Int", sql2setOptInt, set2sqlArrayInt, set2sqlsInt, one2sqlInt)
  lazy val resOptSetLong      : ResSetOpt[Long]       = ResSetOpt("Long", sql2setOptLong, set2sqlArrayLong, set2sqlsLong, one2sqlLong)
  lazy val resOptSetFloat     : ResSetOpt[Float]      = ResSetOpt("Float", sql2setOptFloat, set2sqlArrayFloat, set2sqlsFloat, one2sqlFloat)
  lazy val resOptSetDouble    : ResSetOpt[Double]     = ResSetOpt("Double", sql2setOptDouble, set2sqlArrayDouble, set2sqlsDouble, one2sqlDouble)
  lazy val resOptSetBoolean   : ResSetOpt[Boolean]    = ResSetOpt("Boolean", sql2setOptBoolean, set2sqlArrayBoolean, set2sqlsBoolean, one2sqlBoolean)
  lazy val resOptSetBigInt    : ResSetOpt[BigInt]     = ResSetOpt("BigInt", sql2setOptBigInt, set2sqlArrayBigInt, set2sqlsBigInt, one2sqlBigInt)
  lazy val resOptSetBigDecimal: ResSetOpt[BigDecimal] = ResSetOpt("BigDecimal", sql2setOptBigDecimal, set2sqlArrayBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal)
  lazy val resOptSetDate      : ResSetOpt[Date]       = ResSetOpt("Date", sql2setOptDate, set2sqlArrayDate, set2sqlsDate, one2sqlDate)
  lazy val resOptSetUUID      : ResSetOpt[UUID]       = ResSetOpt("UUID", sql2setOptUUID, set2sqlArrayUUID, set2sqlsUUID, one2sqlUUID)
  lazy val resOptSetURI       : ResSetOpt[URI]        = ResSetOpt("URI", sql2setOptURI, set2sqlArrayURI, set2sqlsURI, one2sqlURI)
  lazy val resOptSetByte      : ResSetOpt[Byte]       = ResSetOpt("Byte", sql2setOptByte, set2sqlArrayByte, set2sqlsByte, one2sqlByte)
  lazy val resOptSetShort     : ResSetOpt[Short]      = ResSetOpt("Short", sql2setOptShort, set2sqlArrayShort, set2sqlsShort, one2sqlShort)
  lazy val resOptSetChar      : ResSetOpt[Char]       = ResSetOpt("Char", sql2setOptChar, set2sqlArrayChar, set2sqlsChar, one2sqlChar)


  protected def sql2setOpt[T](row: Row, n: Int, getValue: Row => T): Option[Set[T]] = {
    val array = row.getArray(n)
    if (row.wasNull()) {
      Option.empty[Set[T]]
    } else {
      val arrayResultSet = array.getResultSet
      var set            = Set.empty[T]
      while (arrayResultSet.next()) {
        set += getValue(arrayResultSet)
      }
      if (set.isEmpty || set == Set(0L)) Option.empty[Set[T]] else Some(set)
    }
  }

  protected lazy val sql2setOptString    : (Row, Int) => Option[Set[String]]     = (row: Row, n: Int) => sql2setOpt(row, n, valueString)
  protected lazy val sql2setOptInt       : (Row, Int) => Option[Set[Int]]        = (row: Row, n: Int) => sql2setOpt(row, n, valueInt)
  protected lazy val sql2setOptLong      : (Row, Int) => Option[Set[Long]]       = (row: Row, n: Int) => sql2setOpt(row, n, valueLong)
  protected lazy val sql2setOptFloat     : (Row, Int) => Option[Set[Float]]      = (row: Row, n: Int) => sql2setOpt(row, n, valueFloat)
  protected lazy val sql2setOptDouble    : (Row, Int) => Option[Set[Double]]     = (row: Row, n: Int) => sql2setOpt(row, n, valueDouble)
  protected lazy val sql2setOptBoolean   : (Row, Int) => Option[Set[Boolean]]    = (row: Row, n: Int) => sql2setOpt(row, n, valueBoolean)
  protected lazy val sql2setOptBigInt    : (Row, Int) => Option[Set[BigInt]]     = (row: Row, n: Int) => sql2setOpt(row, n, valueBigInt)
  protected lazy val sql2setOptBigDecimal: (Row, Int) => Option[Set[BigDecimal]] = (row: Row, n: Int) => sql2setOpt(row, n, valueBigDecimal)
  protected lazy val sql2setOptDate      : (Row, Int) => Option[Set[Date]]       = (row: Row, n: Int) => sql2setOpt(row, n, valueDate)
  protected lazy val sql2setOptUUID      : (Row, Int) => Option[Set[UUID]]       = (row: Row, n: Int) => sql2setOpt(row, n, valueUUID)
  protected lazy val sql2setOptURI       : (Row, Int) => Option[Set[URI]]        = (row: Row, n: Int) => sql2setOpt(row, n, valueURI)
  protected lazy val sql2setOptByte      : (Row, Int) => Option[Set[Byte]]       = (row: Row, n: Int) => sql2setOpt(row, n, valueByte)
  protected lazy val sql2setOptShort     : (Row, Int) => Option[Set[Short]]      = (row: Row, n: Int) => sql2setOpt(row, n, valueShort)
  protected lazy val sql2setOptChar      : (Row, Int) => Option[Set[Char]]       = (row: Row, n: Int) => sql2setOpt(row, n, valueChar)
}
