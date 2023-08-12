package molecule.sql.core.query

import java.lang.{Double => jDouble, Float => jFloat, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.sql.{ResultSet => RS}
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

trait LambdasSet extends ResolveBase with JavaConversions { self: Base =>

  protected lazy val sql2setString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => sqlArray2set[String](row, n, valueString)
  protected lazy val sql2setInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => sqlArray2set[Int](row, n, valueInt)
  protected lazy val sql2setLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => sqlArray2set[Long](row, n, valueLong)
  protected lazy val sql2setFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => sqlArray2set[Float](row, n, valueFloat)
  protected lazy val sql2setDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => sqlArray2set[Double](row, n, valueDouble)
  protected lazy val sql2setBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => sqlArray2set[Boolean](row, n, valueBoolean)
  protected lazy val sql2setBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => sqlArray2set[BigInt](row, n, valueBigInt)
  protected lazy val sql2setBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => sqlArray2set[BigDecimal](row, n, valueBigDecimal)
  protected lazy val sql2setDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => sqlArray2set[Date](row, n, valueDate)
  protected lazy val sql2setUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => sqlArray2set[UUID](row, n, valueUUID)
  protected lazy val sql2setURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => sqlArray2set[URI](row, n, valueURI)
  protected lazy val sql2setByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => sqlArray2set[Byte](row, n, valueByte)
  protected lazy val sql2setShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => sqlArray2set[Short](row, n, valueShort)
  protected lazy val sql2setChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => sqlArray2set[Char](row, n, valueChar)


  protected lazy val j2sSetString    : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  // Datomic can return both Integer or Long
  protected lazy val j2sSetInt       : AnyRef => AnyRef = (v: AnyRef) => Set(v.toString.toInt)
  protected lazy val j2sSetLong      : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetFloat     : AnyRef => AnyRef = {
    case v: jFloat  => Set(v.asInstanceOf[AnyRef])
    case v: jDouble => Set(v.toFloat.asInstanceOf[AnyRef])
  }
  protected lazy val j2sSetDouble    : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetBoolean   : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetBigInt    : AnyRef => AnyRef = {
    case v: jBigInt => Set(BigInt(v))
    case v          => Set(BigInt(v.toString))
  }
  protected lazy val j2sSetBigDecimal: AnyRef => AnyRef =
    (v: AnyRef) => Set(BigDecimal(v.asInstanceOf[jBigDecimal]))
  protected lazy val j2sSetDate      : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetUUID      : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetURI       : AnyRef => AnyRef = (v: AnyRef) => Set(v)
  protected lazy val j2sSetByte      : AnyRef => AnyRef = {
    case v: Integer => Set(v.toByte)
    case v: jLong   => Set(v.toByte)
  }
  protected lazy val j2sSetShort     : AnyRef => AnyRef = {
    case v: Integer => Set(v.toShort)
    case v: jLong   => Set(v.toShort)
  }
  protected lazy val j2sSetChar      : AnyRef => AnyRef =
    (v: AnyRef) => Set(v.asInstanceOf[String].charAt(0))

  private lazy val set2setString    : AnyRef => AnyRef = jset2set
  private lazy val set2setInt       : AnyRef => AnyRef = jset2set((v: AnyRef) => v.toString.toInt)
  private lazy val set2setLong      : AnyRef => AnyRef = jset2set
  private lazy val set2setFloat     : AnyRef => AnyRef = jset2set
  private lazy val set2setDouble    : AnyRef => AnyRef = jset2set
  private lazy val set2setBoolean   : AnyRef => AnyRef = jset2set
  private lazy val set2setBigInt    : AnyRef => AnyRef = jset2set((v: AnyRef) => BigInt(v.toString))
  private lazy val set2setBigDecimal: AnyRef => AnyRef = jset2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val set2setDate      : AnyRef => AnyRef = jset2set
  private lazy val set2setUUID      : AnyRef => AnyRef = jset2set
  private lazy val set2setURI       : AnyRef => AnyRef = jset2set
  private lazy val set2setByte      : AnyRef => AnyRef = jset2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val set2setShort     : AnyRef => AnyRef = jset2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val set2setChar      : AnyRef => AnyRef = jset2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

  private def jset2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet

  private def jset2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(value).toSet


  private lazy val set2setsString    : AnyRef => AnyRef = jset2setsT[String](_.asInstanceOf[String])
  private lazy val set2setsInt       : AnyRef => AnyRef = jset2setsT[Int](_.toString.toInt)
  private lazy val set2setsLong      : AnyRef => AnyRef = jset2setsT[Long](_.asInstanceOf[Long])
  private lazy val set2setsFloat     : AnyRef => AnyRef = jset2setsT[Float](_.asInstanceOf[Float])
  private lazy val set2setsDouble    : AnyRef => AnyRef = jset2setsT[Double](_.asInstanceOf[Double])
  private lazy val set2setsBoolean   : AnyRef => AnyRef = jset2setsT[Boolean](_.asInstanceOf[Boolean])
  private lazy val set2setsBigInt    : AnyRef => AnyRef = jset2setsT[BigInt]((v: Any) => BigInt(v.toString))
  private lazy val set2setsBigDecimal: AnyRef => AnyRef = jset2setsT[BigDecimal]((v: Any) => BigDecimal(v.toString))
  private lazy val set2setsDate      : AnyRef => AnyRef = jset2setsT[Date](_.asInstanceOf[Date])
  private lazy val set2setsUUID      : AnyRef => AnyRef = jset2setsT[UUID](_.asInstanceOf[UUID])
  private lazy val set2setsURI       : AnyRef => AnyRef = jset2setsT[URI](_.asInstanceOf[URI])
  private lazy val set2setsByte      : AnyRef => AnyRef = jset2setsT[Byte](_.asInstanceOf[Integer].toByte)
  private lazy val set2setsShort     : AnyRef => AnyRef = jset2setsT[Short](_.asInstanceOf[Integer].toShort)
  private lazy val set2setsChar      : AnyRef => AnyRef = jset2setsT[Char](_.asInstanceOf[String].charAt(0))

  private def jset2setsT[T](value: Any => T): AnyRef => AnyRef = (v: AnyRef) => {
    var sets = Set.empty[Set[T]]
    var set  = Set.empty[T]
    v.asInstanceOf[jSet[_]].asScala.foreach { row =>
      set = Set.empty[T]
      row.asInstanceOf[jSet[_]].asScala.foreach(v => set = set + value(v))
      sets += set
    }
    sets.asInstanceOf[AnyRef]
  }


  private lazy val vector2setString    : AnyRef => AnyRef = jvector2set
  private lazy val vector2setInt       : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.toString.toInt)
  private lazy val vector2setLong      : AnyRef => AnyRef = jvector2set
  private lazy val vector2setFloat     : AnyRef => AnyRef = jvector2set
  private lazy val vector2setDouble    : AnyRef => AnyRef = jvector2set
  private lazy val vector2setBoolean   : AnyRef => AnyRef = jvector2set
  private lazy val vector2setBigInt    : AnyRef => AnyRef = jvector2set((v: AnyRef) => BigInt(v.toString))
  private lazy val vector2setBigDecimal: AnyRef => AnyRef = jvector2set((v: AnyRef) => BigDecimal(v.toString))
  private lazy val vector2setDate      : AnyRef => AnyRef = jvector2set
  private lazy val vector2setUUID      : AnyRef => AnyRef = jvector2set
  private lazy val vector2setURI       : AnyRef => AnyRef = jvector2set
  private lazy val vector2setByte      : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  private lazy val vector2setShort     : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  private lazy val vector2setChar      : AnyRef => AnyRef = jvector2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))


  case class ResSet[T](
    tpe: String,
    sql2set: (Row, AttrIndex) => Set[T],
    sql2setOrNull: (Row, AttrIndex) => Any, // Allow null in optional nested rows
    set2sql: Set[T] => String,
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

  lazy val resSetString    : ResSet[String]     = ResSet("String", sql2setString, null, set2sqlString, set2sqlsString, one2sqlString, array2setString, nestedArray2coalescedSetString, nestedArray2nestedSetString, array2setFirstString, array2setLastString, nestedArray2setAscString, nestedArray2setDescString, nestedArray2setSumString)
  lazy val resSetInt       : ResSet[Int]        = ResSet("Int", sql2setInt, null, set2sqlInt, set2sqlsInt, one2sqlInt, array2setInt, nestedArray2coalescedSetInt, nestedArray2nestedSetInt, array2setFirstInt, array2setLastInt, nestedArray2setAscInt, nestedArray2setDescInt, nestedArray2setSumInt)
  lazy val resSetLong      : ResSet[Long]       = ResSet("Long", sql2setLong, null, set2sqlLong, set2sqlsLong, one2sqlLong, array2setLong, nestedArray2coalescedSetLong, nestedArray2nestedSetLong, array2setFirstLong, array2setLastLong, nestedArray2setAscLong, nestedArray2setDescLong, nestedArray2setSumLong)
  lazy val resSetFloat     : ResSet[Float]      = ResSet("Float", sql2setFloat, null, set2sqlFloat, set2sqlsFloat, one2sqlFloat, array2setFloat, nestedArray2coalescedSetFloat, nestedArray2nestedSetFloat, array2setFirstFloat, array2setLastFloat, nestedArray2setAscFloat, nestedArray2setDescFloat, nestedArray2setSumFloat)
  lazy val resSetDouble    : ResSet[Double]     = ResSet("Double", sql2setDouble, null, set2sqlDouble, set2sqlsDouble, one2sqlDouble, array2setDouble, nestedArray2coalescedSetDouble, nestedArray2nestedSetDouble, array2setFirstDouble, array2setLastDouble, nestedArray2setAscDouble, nestedArray2setDescDouble, nestedArray2setSumDouble)
  lazy val resSetBoolean   : ResSet[Boolean]    = ResSet("Boolean", sql2setBoolean, null, set2sqlBoolean, set2sqlsBoolean, one2sqlBoolean, array2setBoolean, nestedArray2coalescedSetBoolean, nestedArray2nestedSetBoolean, array2setFirstBoolean, array2setLastBoolean, nestedArray2setAscBoolean, nestedArray2setDescBoolean, nestedArray2setSumBoolean)
  lazy val resSetBigInt    : ResSet[BigInt]     = ResSet("BigInt", sql2setBigInt, null, set2sqlBigInt, set2sqlsBigInt, one2sqlBigInt, array2setBigInt, nestedArray2coalescedSetBigInt, nestedArray2nestedSetBigInt, array2setFirstBigInt, array2setLastBigInt, nestedArray2setAscBigInt, nestedArray2setDescBigInt, nestedArray2setSumBigInt)
  lazy val resSetBigDecimal: ResSet[BigDecimal] = ResSet("BigDecimal", sql2setBigDecimal, null, set2sqlBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal, array2setBigDecimal, nestedArray2coalescedSetBigDecimal, nestedArray2nestedSetBigDecimal, array2setFirstBigDecimal, array2setLastBigDecimal, nestedArray2setAscBigDecimal, nestedArray2setDescBigDecimal, nestedArray2setSumBigDecimal)
  lazy val resSetDate      : ResSet[Date]       = ResSet("Date", sql2setDate, null, set2sqlDate, set2sqlsDate, one2sqlDate, array2setDate, nestedArray2coalescedSetDate, nestedArray2nestedSetDate, array2setFirstDate, array2setLastDate, nestedArray2setAscDate, nestedArray2setDescDate, nestedArray2setSumDate)
  lazy val resSetUUID      : ResSet[UUID]       = ResSet("UUID", sql2setUUID, null, set2sqlUUID, set2sqlsUUID, one2sqlUUID, array2setUUID, nestedArray2coalescedSetUUID, nestedArray2nestedSetUUID, array2setFirstUUID, array2setLastUUID, nestedArray2setAscUUID, nestedArray2setDescUUID, nestedArray2setSumUUID)
  lazy val resSetURI       : ResSet[URI]        = ResSet("URI", sql2setURI, null, set2sqlURI, set2sqlsURI, one2sqlURI, array2setURI, nestedArray2coalescedSetURI, nestedArray2nestedSetURI, array2setFirstURI, array2setLastURI, nestedArray2setAscURI, nestedArray2setDescURI, nestedArray2setSumURI)
  lazy val resSetByte      : ResSet[Byte]       = ResSet("Byte", sql2setByte, null, set2sqlByte, set2sqlsByte, one2sqlByte, array2setByte, nestedArray2coalescedSetByte, nestedArray2nestedSetByte, array2setFirstByte, array2setLastByte, nestedArray2setAscByte, nestedArray2setDescByte, nestedArray2setSumByte)
  lazy val resSetShort     : ResSet[Short]      = ResSet("Short", sql2setShort, null, set2sqlShort, set2sqlsShort, one2sqlShort, array2setShort, nestedArray2coalescedSetShort, nestedArray2nestedSetShort, array2setFirstShort, array2setLastShort, nestedArray2setAscShort, nestedArray2setDescShort, nestedArray2setSumShort)
  lazy val resSetChar      : ResSet[Char]       = ResSet("Char", sql2setChar, null, set2sqlChar, set2sqlsChar, one2sqlChar, array2setChar, nestedArray2coalescedSetChar, nestedArray2nestedSetChar, array2setFirstChar, array2setLastChar, nestedArray2setAscChar, nestedArray2setDescChar, nestedArray2setSumChar)

  protected lazy val set2sqlString    : Set[String] => String     = (set: Set[String]) => set.map(_.replace("'", "''")).mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlInt       : Set[Int] => String        = (set: Set[Int]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlLong      : Set[Long] => String       = (set: Set[Long]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlFloat     : Set[Float] => String      = (set: Set[Float]) => set.map(_.toString.toDouble).mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlDouble    : Set[Double] => String     = (set: Set[Double]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlBoolean   : Set[Boolean] => String    = (set: Set[Boolean]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlBigInt    : Set[BigInt] => String     = (set: Set[BigInt]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlBigDecimal: Set[BigDecimal] => String = (set: Set[BigDecimal]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlDate      : Set[Date] => String       = (set: Set[Date]) => set.map(date2str(_)).mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlUUID      : Set[UUID] => String       = (set: Set[UUID]) => set.mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlURI       : Set[URI] => String        = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).mkString("ARRAY ['", "', '", "']")
  protected lazy val set2sqlByte      : Set[Byte] => String       = (set: Set[Byte]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlShort     : Set[Short] => String      = (set: Set[Short]) => set.mkString("ARRAY [", ", ", "]")
  protected lazy val set2sqlChar      : Set[Char] => String       = (set: Set[Char]) => set.mkString("ARRAY ['", "', '", "']")

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


  private lazy val array2setString    : (Row, Int) => Set[String]     = { (row: Row, n: Int) => val set = sqlArray2set[String](row, n, valueString); if (row.wasNull()) Set.empty[String] else set }
  private lazy val array2setInt       : (Row, Int) => Set[Int]        = { (row: Row, n: Int) => val set = sqlArray2set[Int](row, n, valueInt); if (row.wasNull()) Set.empty[Int] else set }
  private lazy val array2setLong      : (Row, Int) => Set[Long]       = { (row: Row, n: Int) => val set = sqlArray2set[Long](row, n, valueLong); if (row.wasNull()) Set.empty[Long] else set }
  private lazy val array2setFloat     : (Row, Int) => Set[Float]      = { (row: Row, n: Int) => val set = sqlArray2set[Float](row, n, valueFloat); if (row.wasNull()) Set.empty[Float] else set }
  private lazy val array2setDouble    : (Row, Int) => Set[Double]     = { (row: Row, n: Int) => val set = sqlArray2set[Double](row, n, valueDouble); if (row.wasNull()) Set.empty[Double] else set }
  private lazy val array2setBoolean   : (Row, Int) => Set[Boolean]    = { (row: Row, n: Int) => val set = sqlArray2set[Boolean](row, n, valueBoolean); if (row.wasNull()) Set.empty[Boolean] else set }
  private lazy val array2setBigInt    : (Row, Int) => Set[BigInt]     = { (row: Row, n: Int) => val set = sqlArray2set[BigInt](row, n, valueBigInt); if (row.wasNull()) Set.empty[BigInt] else set }
  private lazy val array2setBigDecimal: (Row, Int) => Set[BigDecimal] = { (row: Row, n: Int) => val set = sqlArray2set[BigDecimal](row, n, valueBigDecimal); if (row.wasNull()) Set.empty[BigDecimal] else set }
  private lazy val array2setDate      : (Row, Int) => Set[Date]       = { (row: Row, n: Int) => val set = sqlArray2set[Date](row, n, valueDate); if (row.wasNull()) Set.empty[Date] else set }
  private lazy val array2setUUID      : (Row, Int) => Set[UUID]       = { (row: Row, n: Int) => val set = sqlArray2set[UUID](row, n, valueUUID); if (row.wasNull()) Set.empty[UUID] else set }
  private lazy val array2setURI       : (Row, Int) => Set[URI]        = { (row: Row, n: Int) => val set = sqlArray2set[URI](row, n, valueURI); if (row.wasNull()) Set.empty[URI] else set }
  private lazy val array2setByte      : (Row, Int) => Set[Byte]       = { (row: Row, n: Int) => val set = sqlArray2set[Byte](row, n, valueByte); if (row.wasNull()) Set.empty[Byte] else set }
  private lazy val array2setShort     : (Row, Int) => Set[Short]      = { (row: Row, n: Int) => val set = sqlArray2set[Short](row, n, valueShort); if (row.wasNull()) Set.empty[Short] else set }
  private lazy val array2setChar      : (Row, Int) => Set[Char]       = { (row: Row, n: Int) => val set = sqlArray2set[Char](row, n, valueChar); if (row.wasNull()) Set.empty[Char] else set }


  private def sqlNestedArrays2coalescedSet[T](row: Row, n: Int, getValue: Any => T): Set[T] = {
    val outerArrayResultSet = row.getArray(n).getResultSet
    var set                 = Set.empty[T]
    while (outerArrayResultSet.next()) {
      outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
        set += getValue(value)
      }
    }
    set
  }

  private lazy val nestedArray2coalescedSetString    : (Row, Int) => Set[String]     = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[String] else set }
  private lazy val nestedArray2coalescedSetInt       : (Row, Int) => Set[Int]        = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Int](row, n, j2Int); if (row.wasNull()) Set.empty[Int] else set }
  private lazy val nestedArray2coalescedSetLong      : (Row, Int) => Set[Long]       = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Long](row, n, j2Long); if (row.wasNull()) Set.empty[Long] else set }
  private lazy val nestedArray2coalescedSetFloat     : (Row, Int) => Set[Float]      = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Float] else set.map(_.toFloat) } // Float saved as Double
  private lazy val nestedArray2coalescedSetDouble    : (Row, Int) => Set[Double]     = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Double] else set }
  private lazy val nestedArray2coalescedSetBoolean   : (Row, Int) => Set[Boolean]    = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Boolean](row, n, j2Boolean); if (row.wasNull()) Set.empty[Boolean] else set }
  private lazy val nestedArray2coalescedSetBigInt    : (Row, Int) => Set[BigInt]     = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[BigInt](row, n, j2BigInt); if (row.wasNull()) Set.empty[BigInt] else set }
  private lazy val nestedArray2coalescedSetBigDecimal: (Row, Int) => Set[BigDecimal] = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[BigDecimal](row, n, j2BigDecimal); if (row.wasNull()) Set.empty[BigDecimal] else set }
  private lazy val nestedArray2coalescedSetDate      : (Row, Int) => Set[Date]       = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Date](row, n, j2Date); if (row.wasNull()) Set.empty[Date] else set }
  private lazy val nestedArray2coalescedSetUUID      : (Row, Int) => Set[UUID]       = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[UUID](row, n, j2UUID); if (row.wasNull()) Set.empty[UUID] else set }
  private lazy val nestedArray2coalescedSetURI       : (Row, Int) => Set[URI]        = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[URI] else set.map(s => new URI(s)) } // URI saved as String
  private lazy val nestedArray2coalescedSetByte      : (Row, Int) => Set[Byte]       = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Byte](row, n, j2Byte); if (row.wasNull()) Set.empty[Byte] else set }
  private lazy val nestedArray2coalescedSetShort     : (Row, Int) => Set[Short]      = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Short](row, n, j2Short); if (row.wasNull()) Set.empty[Short] else set }
  private lazy val nestedArray2coalescedSetChar      : (Row, Int) => Set[Char]       = { (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Char](row, n, j2Char); if (row.wasNull()) Set.empty[Char] else set }

  private lazy val j2String    : Any => String     = (v: Any) => v.asInstanceOf[String]
  private lazy val j2Int       : Any => Int        = (v: Any) => v.toString.toInt
  private lazy val j2Long      : Any => Long       = (v: Any) => v.asInstanceOf[Long]
  private lazy val j2Float     : Any => Float      = (v: Any) => v.asInstanceOf[Float]
  private lazy val j2Double    : Any => Double     = (v: Any) => v.asInstanceOf[Double]
  private lazy val j2Boolean   : Any => Boolean    = (v: Any) => v.asInstanceOf[Boolean]
  private lazy val j2BigInt    : Any => BigInt     = (v: Any) => BigInt(v.toString)
  private lazy val j2BigDecimal: Any => BigDecimal = (v: Any) => BigDecimal(v.toString)
  private lazy val j2Date      : Any => Date       = (v: Any) => v.asInstanceOf[Date]
  private lazy val j2UUID      : Any => UUID       = (v: Any) => v.asInstanceOf[UUID]
  private lazy val j2URI       : Any => URI        = (v: Any) => v.asInstanceOf[URI]
  private lazy val j2Byte      : Any => Byte       = (v: Any) => v.asInstanceOf[Integer].toByte
  private lazy val j2Short     : Any => Short      = (v: Any) => v.asInstanceOf[Integer].toShort
  private lazy val j2Char      : Any => Char       = (v: Any) => v.asInstanceOf[String].charAt(0)


  private def sqlNestedArrays2nestedSet[T](row: Row, n: Int, getValue: Any => T): Set[Set[T]] = {
    val outerArrayResultSet = row.getArray(n).getResultSet
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

  private lazy val nestedArray2nestedSetString    : (Row, Int) => Set[Set[String]]     = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[Set[String]] else set }
  private lazy val nestedArray2nestedSetInt       : (Row, Int) => Set[Set[Int]]        = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Int](row, n, j2Int); if (row.wasNull()) Set.empty[Set[Int]] else set }
  private lazy val nestedArray2nestedSetLong      : (Row, Int) => Set[Set[Long]]       = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Long](row, n, j2Long); if (row.wasNull()) Set.empty[Set[Long]] else set }
  private lazy val nestedArray2nestedSetFloat     : (Row, Int) => Set[Set[Float]]      = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Set[Float]] else set.map(set => set.map(_.toFloat)) } // Float saved as Double
  private lazy val nestedArray2nestedSetDouble    : (Row, Int) => Set[Set[Double]]     = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Set[Double]] else set }
  private lazy val nestedArray2nestedSetBoolean   : (Row, Int) => Set[Set[Boolean]]    = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Boolean](row, n, j2Boolean); if (row.wasNull()) Set.empty[Set[Boolean]] else set }
  private lazy val nestedArray2nestedSetBigInt    : (Row, Int) => Set[Set[BigInt]]     = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[BigInt](row, n, j2BigInt); if (row.wasNull()) Set.empty[Set[BigInt]] else set }
  private lazy val nestedArray2nestedSetBigDecimal: (Row, Int) => Set[Set[BigDecimal]] = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[BigDecimal](row, n, j2BigDecimal); if (row.wasNull()) Set.empty[Set[BigDecimal]] else set }
  private lazy val nestedArray2nestedSetDate      : (Row, Int) => Set[Set[Date]]       = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Date](row, n, j2Date); if (row.wasNull()) Set.empty[Set[Date]] else set }
  private lazy val nestedArray2nestedSetUUID      : (Row, Int) => Set[Set[UUID]]       = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[UUID](row, n, j2UUID); if (row.wasNull()) Set.empty[Set[UUID]] else set }
  private lazy val nestedArray2nestedSetURI       : (Row, Int) => Set[Set[URI]]        = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[Set[URI]] else set.map(set => set.map(s => new URI(s))) } // URI saved as String
  private lazy val nestedArray2nestedSetByte      : (Row, Int) => Set[Set[Byte]]       = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Byte](row, n, j2Byte); if (row.wasNull()) Set.empty[Set[Byte]] else set }
  private lazy val nestedArray2nestedSetShort     : (Row, Int) => Set[Set[Short]]      = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Short](row, n, j2Short); if (row.wasNull()) Set.empty[Set[Short]] else set }
  private lazy val nestedArray2nestedSetChar      : (Row, Int) => Set[Set[Char]]       = { (row: Row, n: Int) => val set = sqlNestedArrays2nestedSet[Char](row, n, j2Char); if (row.wasNull()) Set.empty[Set[Char]] else set }


  private lazy val array2setFirstString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).min)
  private lazy val array2setFirstInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Int).min)
  private lazy val array2setFirstLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Long).min)
  private lazy val array2setFirstFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Double).min.toFloat) // Float saved as Double
  private lazy val array2setFirstDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Double).min)
  private lazy val array2setFirstBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Boolean).min)
  private lazy val array2setFirstBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigInt).min)
  private lazy val array2setFirstBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).min)
  private lazy val array2setFirstDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Date).min)
  private lazy val array2setFirstUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2UUID).min)
  private lazy val array2setFirstURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => Set(new URI(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).min)) // URI saved as String
  private lazy val array2setFirstByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Byte).min)
  private lazy val array2setFirstShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Short).min)
  private lazy val array2setFirstChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Char).min)

  private lazy val array2setLastString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).max)
  private lazy val array2setLastInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Int).max)
  private lazy val array2setLastLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Long).max)
  private lazy val array2setLastFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Double).max.toFloat) // Float saved as Double
  private lazy val array2setLastDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Double).max)
  private lazy val array2setLastBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Boolean).max)
  private lazy val array2setLastBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigInt).max)
  private lazy val array2setLastBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2BigDecimal).max)
  private lazy val array2setLastDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Date).max)
  private lazy val array2setLastUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2UUID).max)
  private lazy val array2setLastURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => Set(new URI(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2String).max)) // URI saved as String
  private lazy val array2setLastByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Byte).max)
  private lazy val array2setLastShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Short).max)
  private lazy val array2setLastChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => Set(row.getArray(n).getArray.asInstanceOf[Array[_]].map(j2Char).max)

  private lazy val nestedArray2setAscString    : Int => (Row, Int) => Set[String]     = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[String] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscInt       : Int => (Row, Int) => Set[Int]        = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Int](row, n, j2Int); if (row.wasNull()) Set.empty[Int] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscLong      : Int => (Row, Int) => Set[Long]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Long](row, n, j2Long); if (row.wasNull()) Set.empty[Long] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscFloat     : Int => (Row, Int) => Set[Float]      = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Float] else set.toList.sorted.take(x).map(_.toFloat).toSet } // Float saved as Double
  private lazy val nestedArray2setAscDouble    : Int => (Row, Int) => Set[Double]     = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Double] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscBoolean   : Int => (Row, Int) => Set[Boolean]    = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Boolean](row, n, j2Boolean); if (row.wasNull()) Set.empty[Boolean] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscBigInt    : Int => (Row, Int) => Set[BigInt]     = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[BigInt](row, n, j2BigInt); if (row.wasNull()) Set.empty[BigInt] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscBigDecimal: Int => (Row, Int) => Set[BigDecimal] = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[BigDecimal](row, n, j2BigDecimal); if (row.wasNull()) Set.empty[BigDecimal] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscDate      : Int => (Row, Int) => Set[Date]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Date](row, n, j2Date); if (row.wasNull()) Set.empty[Date] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscUUID      : Int => (Row, Int) => Set[UUID]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[UUID](row, n, j2UUID); if (row.wasNull()) Set.empty[UUID] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscURI       : Int => (Row, Int) => Set[URI]        = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[URI] else set.toList.sorted.take(x).map(s => new URI(s)).toSet } // URI saved as String
  private lazy val nestedArray2setAscByte      : Int => (Row, Int) => Set[Byte]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Byte](row, n, j2Byte); if (row.wasNull()) Set.empty[Byte] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscShort     : Int => (Row, Int) => Set[Short]      = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Short](row, n, j2Short); if (row.wasNull()) Set.empty[Short] else set.toList.sorted.take(x).toSet }
  private lazy val nestedArray2setAscChar      : Int => (Row, Int) => Set[Char]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Char](row, n, j2Char); if (row.wasNull()) Set.empty[Char] else set.toList.sorted.take(x).toSet }

  private lazy val nestedArray2setDescString    : Int => (Row, Int) => Set[String]     = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[String] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescInt       : Int => (Row, Int) => Set[Int]        = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Int](row, n, j2Int); if (row.wasNull()) Set.empty[Int] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescLong      : Int => (Row, Int) => Set[Long]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Long](row, n, j2Long); if (row.wasNull()) Set.empty[Long] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescFloat     : Int => (Row, Int) => Set[Float]      = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Float] else set.toList.sorted.takeRight(x).map(_.toFloat).toSet } // Float saved as Double
  private lazy val nestedArray2setDescDouble    : Int => (Row, Int) => Set[Double]     = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Double](row, n, j2Double); if (row.wasNull()) Set.empty[Double] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescBoolean   : Int => (Row, Int) => Set[Boolean]    = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Boolean](row, n, j2Boolean); if (row.wasNull()) Set.empty[Boolean] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescBigInt    : Int => (Row, Int) => Set[BigInt]     = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[BigInt](row, n, j2BigInt); if (row.wasNull()) Set.empty[BigInt] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescBigDecimal: Int => (Row, Int) => Set[BigDecimal] = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[BigDecimal](row, n, j2BigDecimal); if (row.wasNull()) Set.empty[BigDecimal] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescDate      : Int => (Row, Int) => Set[Date]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Date](row, n, j2Date); if (row.wasNull()) Set.empty[Date] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescUUID      : Int => (Row, Int) => Set[UUID]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[UUID](row, n, j2UUID); if (row.wasNull()) Set.empty[UUID] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescURI       : Int => (Row, Int) => Set[URI]        = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[String](row, n, j2String); if (row.wasNull()) Set.empty[URI] else set.toList.sorted.takeRight(x).map(s => new URI(s)).toSet } // URI saved as String
  private lazy val nestedArray2setDescByte      : Int => (Row, Int) => Set[Byte]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Byte](row, n, j2Byte); if (row.wasNull()) Set.empty[Byte] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescShort     : Int => (Row, Int) => Set[Short]      = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Short](row, n, j2Short); if (row.wasNull()) Set.empty[Short] else set.toList.sorted.takeRight(x).toSet }
  private lazy val nestedArray2setDescChar      : Int => (Row, Int) => Set[Char]       = { (x: Int) => (row: Row, n: Int) => val set = sqlNestedArrays2coalescedSet[Char](row, n, j2Char); if (row.wasNull()) Set.empty[Char] else set.toList.sorted.takeRight(x).toSet }

  private def onlyNumbers = throw new Exception("Casting only for numbers.")

  private lazy val nestedArray2setSumString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => onlyNumbers
  private lazy val nestedArray2setSumInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Int](row, n, j2Int).sum)
  private lazy val nestedArray2setSumLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Long](row, n, j2Long).sum)
  private lazy val nestedArray2setSumFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Double](row, n, j2Double).sum.toFloat) // Float saved as Double
  private lazy val nestedArray2setSumDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Double](row, n, j2Double).sum)
  private lazy val nestedArray2setSumBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => onlyNumbers
  private lazy val nestedArray2setSumBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[BigInt](row, n, j2BigInt).sum)
  private lazy val nestedArray2setSumBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[BigDecimal](row, n, j2BigDecimal).sum)
  private lazy val nestedArray2setSumDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => onlyNumbers
  private lazy val nestedArray2setSumUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => onlyNumbers
  private lazy val nestedArray2setSumURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => onlyNumbers
  private lazy val nestedArray2setSumByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Byte](row, n, j2Byte).sum)
  private lazy val nestedArray2setSumShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Short](row, n, j2Short).sum)
  private lazy val nestedArray2setSumChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => Set(sqlNestedArrays2coalescedSet[Char](row, n, j2Char).sum)


  private lazy val j2sOpSetString = (v: AnyRef) => v match {
    case null            => Option.empty[Set[String]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String])) // attr_?(<expr>))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String]).toSet) // attr_?
  }

  private lazy val j2sOpSetInt = (v: AnyRef) => v match {
    case null => Option.empty[Set[Int]]
    // Datomic can return both Integer or Long
    case set: jSet[_]    => Some(set.asScala.map(_.toString.toInt))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.toString.toInt).toSet)
  }

  private lazy val j2sOpSetLong = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Long]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Long]))
    case map: jMap[_, _] =>
      val list = map.values.iterator.next.asInstanceOf[jList[_]].asScala
      list.head match {
        case _: jLong => Some(list.map(_.asInstanceOf[Long]).toSet)
        // Refs
        case _: jMap[_, _] => Some(list.map(_.asInstanceOf[jMap[_, _]].values.iterator.next.asInstanceOf[Long]).toSet)
      }
  }

  private lazy val j2sOpSetFloat = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Float]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Float]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Float]).toSet)
  }

  private lazy val j2sOpSetDouble = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Double]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Double]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Double]).toSet)
  }

  private lazy val j2sOpSetBoolean = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Boolean]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Boolean]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Boolean]).toSet)
  }

  private lazy val j2sOpSetBigInt = (v: AnyRef) => v match {
    case null            => Option.empty[Set[BigInt]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }

  private lazy val j2sOpSetBigDecimal = (v: AnyRef) => v match {
    case null            => Option.empty[Set[BigDecimal]]
    case set: jSet[_]    => Some(set.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }

  private lazy val j2sOpSetDate = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Date]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Date]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Date]).toSet)
  }

  private lazy val j2sOpSetUUID = (v: AnyRef) => v match {
    case null            => Option.empty[Set[UUID]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[UUID]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[UUID]).toSet)
  }

  private lazy val j2sOpSetURI = (v: AnyRef) => v match {
    case null            => Option.empty[Set[URI]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[URI]))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[URI]).toSet)
  }

  private lazy val j2sOpSetByte = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Byte]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toByte))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toByte).toSet)
  }

  private lazy val j2sOpSetShort = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Short]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[Integer].toShort))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[Integer].toShort).toSet)
  }

  private lazy val j2sOpSetChar = (v: AnyRef) => v match {
    case null            => Option.empty[Set[Char]]
    case set: jSet[_]    => Some(set.asScala.map(_.asInstanceOf[String].charAt(0)))
    case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[jList[_]].asScala.map(_.asInstanceOf[String].charAt(0)).toSet)
  }


  case class ResSetOpt[T](
    tpe: String,
    sql2setOpt: (Row, AttrIndex) => Option[Set[T]],
    set2sql: Set[T] => String,
    set2sqls: Set[T] => Set[String],
    one2sql: T => String,
  )

  lazy val resOptSetString    : ResSetOpt[String]     = ResSetOpt("String", sql2setOptString, set2sqlString, set2sqlsString, one2sqlString)
  lazy val resOptSetInt       : ResSetOpt[Int]        = ResSetOpt("Int", sql2setOptInt, set2sqlInt, set2sqlsInt, one2sqlInt)
  lazy val resOptSetLong      : ResSetOpt[Long]       = ResSetOpt("Long", sql2setOptLong, set2sqlLong, set2sqlsLong, one2sqlLong)
  lazy val resOptSetFloat     : ResSetOpt[Float]      = ResSetOpt("Float", sql2setOptFloat, set2sqlFloat, set2sqlsFloat, one2sqlFloat)
  lazy val resOptSetDouble    : ResSetOpt[Double]     = ResSetOpt("Double", sql2setOptDouble, set2sqlDouble, set2sqlsDouble, one2sqlDouble)
  lazy val resOptSetBoolean   : ResSetOpt[Boolean]    = ResSetOpt("Boolean", sql2setOptBoolean, set2sqlBoolean, set2sqlsBoolean, one2sqlBoolean)
  lazy val resOptSetBigInt    : ResSetOpt[BigInt]     = ResSetOpt("BigInt", sql2setOptBigInt, set2sqlBigInt, set2sqlsBigInt, one2sqlBigInt)
  lazy val resOptSetBigDecimal: ResSetOpt[BigDecimal] = ResSetOpt("BigDecimal", sql2setOptBigDecimal, set2sqlBigDecimal, set2sqlsBigDecimal, one2sqlBigDecimal)
  lazy val resOptSetDate      : ResSetOpt[Date]       = ResSetOpt("Date", sql2setOptDate, set2sqlDate, set2sqlsDate, one2sqlDate)
  lazy val resOptSetUUID      : ResSetOpt[UUID]       = ResSetOpt("UUID", sql2setOptUUID, set2sqlUUID, set2sqlsUUID, one2sqlUUID)
  lazy val resOptSetURI       : ResSetOpt[URI]        = ResSetOpt("URI", sql2setOptURI, set2sqlURI, set2sqlsURI, one2sqlURI)
  lazy val resOptSetByte      : ResSetOpt[Byte]       = ResSetOpt("Byte", sql2setOptByte, set2sqlByte, set2sqlsByte, one2sqlByte)
  lazy val resOptSetShort     : ResSetOpt[Short]      = ResSetOpt("Short", sql2setOptShort, set2sqlShort, set2sqlsShort, one2sqlShort)
  lazy val resOptSetChar      : ResSetOpt[Char]       = ResSetOpt("Char", sql2setOptChar, set2sqlChar, set2sqlsChar, one2sqlChar)


  private def sql2setOpt[T](row: Row, n: Int, getValue: RS => T): Option[Set[T]] = {
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


  // Nested opt ---------------------------------------------------------------------

  lazy val it2SetString    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => nullValue
    case vs: jList[_] => vs.asScala.map(v => v.toString).toSet
    case other        => unexpectedValue(other)
  }
  lazy val it2SetInt       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    // Datomic can return both Integer or Long
    case vs: jList[_] => vs.asScala.map(v => v.toString.toInt).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetLong      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Long]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetFloat     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Float]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetDouble    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Double]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBoolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Boolean]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBigInt    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetBigDecimal: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetDate      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Date]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetUUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[UUID]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetURI       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[URI]).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetByte      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Integer].toByte).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetShort     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[Integer].toShort).toSet
    case `none`       => nullValue
    case other        => unexpectedValue(other)
  }
  lazy val it2SetChar      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => nullValue
    case vs: jList[_] => vs.asScala.map(v => v.asInstanceOf[String].charAt(0)).toSet
    case other        => unexpectedValue(other)
  }


  lazy val it2OptSetString    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[String]).toSet)
  }
  lazy val it2OptSetInt       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    // Datomic can return both Integer or Long
    case vs: jList[_] => Some(vs.asScala.map(v => v.toString.toInt).toSet)
  }
  lazy val it2OptSetLong      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Long]).toSet)
  }
  lazy val it2OptSetFloat     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Float]).toSet)
  }
  lazy val it2OptSetDouble    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Double]).toSet)
  }
  lazy val it2OptSetBoolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Boolean]).toSet)
  }
  lazy val it2OptSetBigInt    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => BigInt(v.asInstanceOf[jBigInt])).toSet)
  }
  lazy val it2OptSetBigDecimal: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => BigDecimal(v.asInstanceOf[jBigDecimal])).toSet)
  }
  lazy val it2OptSetDate      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Date]).toSet)
  }
  lazy val it2OptSetUUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[UUID]).toSet)
  }
  lazy val it2OptSetURI       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[URI]).toSet)
  }
  lazy val it2OptSetByte      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Integer].toByte).toSet)
  }
  lazy val it2OptSetShort     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[Integer].toShort).toSet)
  }
  lazy val it2OptSetChar      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`       => None
    case vs: jList[_] => Some(vs.asScala.map(v => v.asInstanceOf[String].charAt(0)).toSet)
  }
}
