package molecule.sql.core.query

import java.lang.{Double => jDouble, Float => jFloat, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.sql.{ResultSet => RS}
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.core.util.JavaConversions

trait LambdasSet extends ResolveBase with JavaConversions { self: Base =>

  protected lazy val sql2setString    : (Row, Int) => Set[String]     = (row: Row, n: Int) => sql2set[String](row, n, valueString)
  protected lazy val sql2setInt       : (Row, Int) => Set[Int]        = (row: Row, n: Int) => sql2set[Int](row, n, valueInt)
  protected lazy val sql2setLong      : (Row, Int) => Set[Long]       = (row: Row, n: Int) => sql2set[Long](row, n, valueLong)
  protected lazy val sql2setFloat     : (Row, Int) => Set[Float]      = (row: Row, n: Int) => sql2set[Float](row, n, valueFloat)
  protected lazy val sql2setDouble    : (Row, Int) => Set[Double]     = (row: Row, n: Int) => sql2set[Double](row, n, valueDouble)
  protected lazy val sql2setBoolean   : (Row, Int) => Set[Boolean]    = (row: Row, n: Int) => sql2set[Boolean](row, n, valueBoolean)
  protected lazy val sql2setBigInt    : (Row, Int) => Set[BigInt]     = (row: Row, n: Int) => sql2set[BigInt](row, n, valueBigInt)
  protected lazy val sql2setBigDecimal: (Row, Int) => Set[BigDecimal] = (row: Row, n: Int) => sql2set[BigDecimal](row, n, valueBigDecimal)
  protected lazy val sql2setDate      : (Row, Int) => Set[Date]       = (row: Row, n: Int) => sql2set[Date](row, n, valueDate)
  protected lazy val sql2setUUID      : (Row, Int) => Set[UUID]       = (row: Row, n: Int) => sql2set[UUID](row, n, valueUUID)
  protected lazy val sql2setURI       : (Row, Int) => Set[URI]        = (row: Row, n: Int) => sql2set[URI](row, n, valueURI)
  protected lazy val sql2setByte      : (Row, Int) => Set[Byte]       = (row: Row, n: Int) => sql2set[Byte](row, n, valueByte)
  protected lazy val sql2setShort     : (Row, Int) => Set[Short]      = (row: Row, n: Int) => sql2set[Short](row, n, valueShort)
  protected lazy val sql2setChar      : (Row, Int) => Set[Char]       = (row: Row, n: Int) => sql2set[Char](row, n, valueChar)


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
    one2sql: T => String
    //    j2s: AnyRef => AnyRef,
    //    sets: AnyRef => AnyRef,
    //    vector2set: AnyRef => AnyRef,
    //    j2sSet: AnyRef => AnyRef
  )

  lazy val resSetString    : ResSet[String]     = ResSet("String", sql2setString, null, set2sqlString, one2sqlString)
  lazy val resSetInt       : ResSet[Int]        = ResSet("Int", sql2setInt, null, set2sqlInt, one2sqlInt)
  lazy val resSetLong      : ResSet[Long]       = ResSet("Long", sql2setLong, null, set2sqlLong, one2sqlLong)
  lazy val resSetFloat     : ResSet[Float]      = ResSet("Float", sql2setFloat, null, set2sqlFloat, one2sqlFloat)
  lazy val resSetDouble    : ResSet[Double]     = ResSet("Double", sql2setDouble, null, set2sqlDouble, one2sqlDouble)
  lazy val resSetBoolean   : ResSet[Boolean]    = ResSet("Boolean", sql2setBoolean, null, set2sqlBoolean, one2sqlBoolean)
  lazy val resSetBigInt    : ResSet[BigInt]     = ResSet("BigInt", sql2setBigInt, null, set2sqlBigInt, one2sqlBigInt)
  lazy val resSetBigDecimal: ResSet[BigDecimal] = ResSet("BigDecimal", sql2setBigDecimal, null, set2sqlBigDecimal, one2sqlBigDecimal)
  lazy val resSetDate      : ResSet[Date]       = ResSet("Date", sql2setDate, null, set2sqlDate, one2sqlDate)
  lazy val resSetUUID      : ResSet[UUID]       = ResSet("UUID", sql2setUUID, null, set2sqlUUID, one2sqlUUID)
  lazy val resSetURI       : ResSet[URI]        = ResSet("URI", sql2setURI, null, set2sqlURI, one2sqlURI)
  lazy val resSetByte      : ResSet[Byte]       = ResSet("Byte", sql2setByte, null, set2sqlByte, one2sqlByte)
  lazy val resSetShort     : ResSet[Short]      = ResSet("Short", sql2setShort, null, set2sqlShort, one2sqlShort)
  lazy val resSetChar      : ResSet[Char]       = ResSet("Char", sql2setChar, null, set2sqlChar, one2sqlChar)

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


  case class ResSetOLD[T](
    tpe: String,
    toDatalog: T => String,
    s2j: Any => Any,
    j2s: AnyRef => AnyRef,
    sets: AnyRef => AnyRef,
    vector2set: AnyRef => AnyRef,
    j2sSet: AnyRef => AnyRef
  )

  lazy val resSetString1    : ResSetOLD[String]     = ResSetOLD("String", dString, s2jString, set2setString, set2setsString, vector2setString, j2sSetString)
  lazy val resSetInt1       : ResSetOLD[Int]        = ResSetOLD("Int", dInt, s2jInt, set2setInt, set2setsInt, vector2setInt, j2sSetInt)
  lazy val resSetLong1      : ResSetOLD[Long]       = ResSetOLD("Long", dLong, s2jLong, set2setLong, set2setsLong, vector2setLong, j2sSetLong)
  lazy val resSetFloat1     : ResSetOLD[Float]      = ResSetOLD("Float", dFloat, s2jFloat, set2setFloat, set2setsFloat, vector2setFloat, j2sSetFloat)
  lazy val resSetDouble1    : ResSetOLD[Double]     = ResSetOLD("Double", dDouble, s2jDouble, set2setDouble, set2setsDouble, vector2setDouble, j2sSetDouble)
  lazy val resSetBoolean1   : ResSetOLD[Boolean]    = ResSetOLD("Boolean", dBoolean, s2jBoolean, set2setBoolean, set2setsBoolean, vector2setBoolean, j2sSetBoolean)
  lazy val resSetBigInt1    : ResSetOLD[BigInt]     = ResSetOLD("BigInt", dBigInt, s2jBigInt, set2setBigInt, set2setsBigInt, vector2setBigInt, j2sSetBigInt)
  lazy val resSetBigDecimal1: ResSetOLD[BigDecimal] = ResSetOLD("BigDecimal", dBigDecimal, s2jBigDecimal, set2setBigDecimal, set2setsBigDecimal, vector2setBigDecimal, j2sSetBigDecimal)
  lazy val resSetDate1      : ResSetOLD[Date]       = ResSetOLD("Date", dDate, s2jDate, set2setDate, set2setsDate, vector2setDate, j2sSetDate)
  lazy val resSetUUID1      : ResSetOLD[UUID]       = ResSetOLD("UUID", dUUID, s2jUUID, set2setUUID, set2setsUUID, vector2setUUID, j2sSetUUID)
  lazy val resSetURI1       : ResSetOLD[URI]        = ResSetOLD("URI", dURI, s2jURI, set2setURI, set2setsURI, vector2setURI, j2sSetURI)
  lazy val resSetByte1      : ResSetOLD[Byte]       = ResSetOLD("Byte", dByte, s2jByte, set2setByte, set2setsByte, vector2setByte, j2sSetByte)
  lazy val resSetShort1     : ResSetOLD[Short]      = ResSetOLD("Short", dShort, s2jShort, set2setShort, set2setsShort, vector2setShort, j2sSetShort)
  lazy val resSetChar1      : ResSetOLD[Char]       = ResSetOLD("Char", dChar, s2jChar, set2setChar, set2setsChar, vector2setChar, j2sSetChar)


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
    one2sql: T => String,
  )

  lazy val resOptSetString    : ResSetOpt[String]     = ResSetOpt("String", sql2setOptString, set2sqlString, one2sqlString)
  lazy val resOptSetInt       : ResSetOpt[Int]        = ResSetOpt("Int", sql2setOptInt, set2sqlInt, one2sqlInt)
  lazy val resOptSetLong      : ResSetOpt[Long]       = ResSetOpt("Long", sql2setOptLong, set2sqlLong, one2sqlLong)
  lazy val resOptSetFloat     : ResSetOpt[Float]      = ResSetOpt("Float", sql2setOptFloat, set2sqlFloat, one2sqlFloat)
  lazy val resOptSetDouble    : ResSetOpt[Double]     = ResSetOpt("Double", sql2setOptDouble, set2sqlDouble, one2sqlDouble)
  lazy val resOptSetBoolean   : ResSetOpt[Boolean]    = ResSetOpt("Boolean", sql2setOptBoolean, set2sqlBoolean, one2sqlBoolean)
  lazy val resOptSetBigInt    : ResSetOpt[BigInt]     = ResSetOpt("BigInt", sql2setOptBigInt, set2sqlBigInt, one2sqlBigInt)
  lazy val resOptSetBigDecimal: ResSetOpt[BigDecimal] = ResSetOpt("BigDecimal", sql2setOptBigDecimal, set2sqlBigDecimal, one2sqlBigDecimal)
  lazy val resOptSetDate      : ResSetOpt[Date]       = ResSetOpt("Date", sql2setOptDate, set2sqlDate, one2sqlDate)
  lazy val resOptSetUUID      : ResSetOpt[UUID]       = ResSetOpt("UUID", sql2setOptUUID, set2sqlUUID, one2sqlUUID)
  lazy val resOptSetURI       : ResSetOpt[URI]        = ResSetOpt("URI", sql2setOptURI, set2sqlURI, one2sqlURI)
  lazy val resOptSetByte      : ResSetOpt[Byte]       = ResSetOpt("Byte", sql2setOptByte, set2sqlByte, one2sqlByte)
  lazy val resOptSetShort     : ResSetOpt[Short]      = ResSetOpt("Short", sql2setOptShort, set2sqlShort, one2sqlShort)
  lazy val resOptSetChar      : ResSetOpt[Char]       = ResSetOpt("Char", sql2setOptChar, set2sqlChar, one2sqlChar)


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
      if (set.isEmpty) Option.empty[Set[T]] else Some(set)
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