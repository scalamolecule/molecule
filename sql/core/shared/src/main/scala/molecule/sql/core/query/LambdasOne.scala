package molecule.sql.core.query

import java.lang.{Boolean => jBoolean, Double => jDouble, Float => jFloat, Integer => jInteger, Long => jLong}
import java.math.{BigDecimal => jBigDecimal, BigInteger => jBigInt}
import java.net.URI
import java.sql
import java.util.{Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}

trait LambdasOne extends ResolveBase { self: Base =>

  protected lazy val sql2oneString    : (Row, Int) => String     = (row: Row, n: Int) => row.getString(n)
  protected lazy val sql2oneInt       : (Row, Int) => Int        = (row: Row, n: Int) => row.getInt(n)
  protected lazy val sql2oneLong      : (Row, Int) => Long       = (row: Row, n: Int) => row.getLong(n)
  protected lazy val sql2oneFloat     : (Row, Int) => Float      = (row: Row, n: Int) => row.getFloat(n)
  protected lazy val sql2oneDouble    : (Row, Int) => Double     = (row: Row, n: Int) => row.getDouble(n)
  protected lazy val sql2oneBoolean   : (Row, Int) => Boolean    = (row: Row, n: Int) => row.getBoolean(n)
  protected lazy val sql2oneBigInt    : (Row, Int) => BigInt     = (row: Row, n: Int) => row.getBigDecimal(n).toBigInteger
  protected lazy val sql2oneBigDecimal: (Row, Int) => BigDecimal = (row: Row, n: Int) => row.getBigDecimal(n)
  protected lazy val sql2oneDate      : (Row, Int) => Date       = (row: Row, n: Int) => row.getDate(n)
  protected lazy val sql2oneUUID      : (Row, Int) => UUID       = (row: Row, n: Int) => UUID.fromString(row.getString(n))
  protected lazy val sql2oneURI       : (Row, Int) => URI        = (row: Row, n: Int) => new URI(row.getString(n))
  protected lazy val sql2oneByte      : (Row, Int) => Byte       = (row: Row, n: Int) => row.getByte(n)
  protected lazy val sql2oneShort     : (Row, Int) => Short      = (row: Row, n: Int) => row.getShort(n)
  protected lazy val sql2oneChar      : (Row, Int) => Char       = (row: Row, n: Int) => row.getString(n).charAt(0)


  // Datomic Java to Scala
  protected lazy val j2sString: AnyRef => AnyRef = {
    identity
  }

  // Datomic can return both Integer or Long
  protected lazy val j2sInt: AnyRef => AnyRef = {

    val xx = (row: java.sql.ResultSet, n: Int) => row.getInt(n)

    (v: AnyRef) => v.toString.toInt.asInstanceOf[AnyRef]
  }

  protected lazy val j2sLong      : AnyRef => AnyRef = identity
  protected lazy val j2sFloat     : AnyRef => AnyRef = {
    case v: jFloat  => v.asInstanceOf[AnyRef]
    case v: jDouble => v.toFloat.asInstanceOf[AnyRef]
  }
  protected lazy val j2sDouble    : AnyRef => AnyRef = identity
  protected lazy val j2sBoolean   : AnyRef => AnyRef = identity
  protected lazy val j2sBigInt    : AnyRef => AnyRef = {
    case v: jBigInt => BigInt(v)
    case v          => BigInt(v.toString)
  }
  protected lazy val j2sBigDecimal: AnyRef => AnyRef =
    (v: AnyRef) => BigDecimal(v.asInstanceOf[jBigDecimal])
  protected lazy val j2sDate      : AnyRef => AnyRef = identity
  protected lazy val j2sUUID      : AnyRef => AnyRef = identity
  protected lazy val j2sURI       : AnyRef => AnyRef = identity
  protected lazy val j2sByte      : AnyRef => AnyRef = {
    case v: Integer => v.toByte.asInstanceOf[AnyRef]
    case v: jLong   => v.toByte.asInstanceOf[AnyRef]
  }
  protected lazy val j2sShort     : AnyRef => AnyRef = {
    case v: Integer => v.toShort.asInstanceOf[AnyRef]
    case v: jLong   => v.toShort.asInstanceOf[AnyRef]
  }
  protected lazy val j2sChar      : AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[String].charAt(0).asInstanceOf[AnyRef]


  // Single sample value extracted from clojure LazySeq
  protected lazy val firstString    : AnyRef => AnyRef = first
  protected lazy val firstInt       : AnyRef => AnyRef = first
  protected lazy val firstLong      : AnyRef => AnyRef = first
  protected lazy val firstFloat     : AnyRef => AnyRef = first
  protected lazy val firstDouble    : AnyRef => AnyRef = first
  protected lazy val firstBoolean   : AnyRef => AnyRef = first
  protected lazy val firstBigInt    : AnyRef => AnyRef = first((v: Any) => BigInt(v.asInstanceOf[jBigInt]))
  protected lazy val firstBigDecimal: AnyRef => AnyRef = first((v: Any) => BigDecimal(v.asInstanceOf[jBigDecimal]))
  protected lazy val firstDate      : AnyRef => AnyRef = first
  protected lazy val firstUUID      : AnyRef => AnyRef = first
  protected lazy val firstURI       : AnyRef => AnyRef = first
  protected lazy val firstByte      : AnyRef => AnyRef = first((v: Any) => v.asInstanceOf[Integer].toByte)
  protected lazy val firstShort     : AnyRef => AnyRef = first((v: Any) => v.asInstanceOf[Integer].toShort)
  protected lazy val firstChar      : AnyRef => AnyRef = first((v: Any) => v.asInstanceOf[String].charAt(0))

  private def first: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jList[_]].get(0).asInstanceOf[AnyRef]

  private def first(value: Any => Any): AnyRef => AnyRef =
    (v: AnyRef) => value(v.asInstanceOf[jList[_]].get(0)).asInstanceOf[AnyRef]


  protected lazy val set2setString    : AnyRef => AnyRef = set2set
  protected lazy val set2setInt       : AnyRef => AnyRef = set2set((v: AnyRef) => v.toString.toInt)
  protected lazy val set2setLong      : AnyRef => AnyRef = set2set
  protected lazy val set2setFloat     : AnyRef => AnyRef = set2set
  protected lazy val set2setDouble    : AnyRef => AnyRef = set2set
  protected lazy val set2setBoolean   : AnyRef => AnyRef = set2set
  protected lazy val set2setBigInt    : AnyRef => AnyRef = set2set((v: AnyRef) => BigInt(v.toString))
  protected lazy val set2setBigDecimal: AnyRef => AnyRef = set2set((v: AnyRef) => BigDecimal(v.toString))
  protected lazy val set2setDate      : AnyRef => AnyRef = set2set
  protected lazy val set2setUUID      : AnyRef => AnyRef = set2set
  protected lazy val set2setURI       : AnyRef => AnyRef = set2set
  protected lazy val set2setByte      : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toByte)
  protected lazy val set2setShort     : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[Integer].toShort)
  protected lazy val set2setChar      : AnyRef => AnyRef = set2set((v: AnyRef) => v.asInstanceOf[String].charAt(0))

  private def set2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.toSet

  private def set2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jSet[_]].toArray.map(value).toSet


  case class ResOne[T](
    tpe: String,
    sql2one: (Row, AttrIndex) => T,
    sql2oneOrNull: (Row, AttrIndex) => Any, // Allow null in optional nested rows
    one2sql: T => String,
    //    seq2t: (Row, Int) => AnyRef,
    //    set2set: (Row, Int) => AnyRef,
    vector2set: (Row, Int) => AnyRef
  )
  protected lazy val resString1    : ResOne[String]     = ResOne("String", sql2oneString, sql2oneStringOrNull, one2sqlString, vector2setString)
  protected lazy val resInt1       : ResOne[Int]        = ResOne("Int", sql2oneInt, sql2oneIntOrNull, one2sqlInt, vector2setInt)
  protected lazy val resLong1      : ResOne[Long]       = ResOne("Long", sql2oneLong, sql2oneLongOrNull, one2sqlLong, vector2setLong)
  protected lazy val resFloat1     : ResOne[Float]      = ResOne("Float", sql2oneFloat, sql2oneFloatOrNull, one2sqlFloat, vector2setFloat)
  protected lazy val resDouble1    : ResOne[Double]     = ResOne("Double", sql2oneDouble, sql2oneDoubleOrNull, one2sqlDouble, vector2setDouble)
  protected lazy val resBoolean1   : ResOne[Boolean]    = ResOne("Boolean", sql2oneBoolean, sql2oneBooleanOrNull, one2sqlBoolean, vector2setBoolean)
  protected lazy val resBigInt1    : ResOne[BigInt]     = ResOne("BigInt", sql2oneBigInt, sql2oneBigIntOrNull, one2sqlBigInt, vector2setBigInt)
  protected lazy val resBigDecimal1: ResOne[BigDecimal] = ResOne("BigDecimal", sql2oneBigDecimal, sql2oneBigDecimalOrNull, one2sqlBigDecimal, vector2setBigDecimal)
  protected lazy val resDate1      : ResOne[Date]       = ResOne("Date", sql2oneDate, sql2oneDateOrNull, one2sqlDate, vector2setDate)
  protected lazy val resUUID1      : ResOne[UUID]       = ResOne("UUID", sql2oneUUID, sql2oneUUIDOrNull, one2sqlUUID, vector2setUUID)
  protected lazy val resURI1       : ResOne[URI]        = ResOne("URI", sql2oneURI, sql2oneURIOrNull, one2sqlURI, vector2setURI)
  protected lazy val resByte1      : ResOne[Byte]       = ResOne("Byte", sql2oneByte, sql2oneByteOrNull, one2sqlByte, vector2setByte)
  protected lazy val resShort1     : ResOne[Short]      = ResOne("Short", sql2oneShort, sql2oneShortOrNull, one2sqlShort, vector2setShort)
  protected lazy val resChar1      : ResOne[Char]       = ResOne("Char", sql2oneChar, sql2oneCharOrNull, one2sqlChar, vector2setChar)

  private lazy val sql2oneStringOrNull    : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getString(n); if (row.wasNull()) null else v }
  private lazy val sql2oneIntOrNull       : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getInt(n); if (row.wasNull()) null else v }
  private lazy val sql2oneLongOrNull      : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getLong(n); if (row.wasNull()) null else v }
  private lazy val sql2oneFloatOrNull     : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getDouble(n); if (row.wasNull()) null else v }
  private lazy val sql2oneDoubleOrNull    : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getDouble(n); if (row.wasNull()) null else v }
  private lazy val sql2oneBooleanOrNull   : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getBoolean(n); if (row.wasNull()) null else v }
  private lazy val sql2oneBigIntOrNull    : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getBigDecimal(n); if (row.wasNull()) null else v.toBigInteger }
  private lazy val sql2oneBigDecimalOrNull: (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getBigDecimal(n); if (row.wasNull()) null else v }
  private lazy val sql2oneDateOrNull      : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getDate(n); if (row.wasNull()) null else v }
  private lazy val sql2oneUUIDOrNull      : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getString(n); if (row.wasNull()) null else UUID.fromString(v) }
  private lazy val sql2oneURIOrNull       : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getString(n); if (row.wasNull()) null else new URI(v) }
  private lazy val sql2oneByteOrNull      : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getByte(n); if (row.wasNull()) null else v }
  private lazy val sql2oneShortOrNull     : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getShort(n); if (row.wasNull()) null else v }
  private lazy val sql2oneCharOrNull      : (Row, Int) => Any = { (row: Row, n: Int) => val v = row.getString(n); if (row.wasNull()) null else v.charAt(0) }

  private lazy val vector2setString    : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[String](row, n, valueString); if (row.wasNull()) null else set }
  private lazy val vector2setInt       : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Int](row, n, valueInt); if (row.wasNull()) null else set }
  private lazy val vector2setLong      : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Long](row, n, valueLong); if (row.wasNull()) null else set }
  private lazy val vector2setFloat     : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Float](row, n, valueFloat); if (row.wasNull()) null else set }
  private lazy val vector2setDouble    : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Double](row, n, valueDouble); if (row.wasNull()) null else set }
  private lazy val vector2setBoolean   : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Boolean](row, n, valueBoolean); if (row.wasNull()) null else set }
  private lazy val vector2setBigInt    : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[BigInt](row, n, valueBigInt); if (row.wasNull()) null else set }
  private lazy val vector2setBigDecimal: (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[BigDecimal](row, n, valueBigDecimal); if (row.wasNull()) null else set }
  private lazy val vector2setDate      : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Date](row, n, valueDate); if (row.wasNull()) null else set }
  private lazy val vector2setUUID      : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[UUID](row, n, valueUUID); if (row.wasNull()) null else set }
  private lazy val vector2setURI       : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[URI](row, n, valueURI); if (row.wasNull()) null else set }
  private lazy val vector2setByte      : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Byte](row, n, valueByte); if (row.wasNull()) null else set }
  private lazy val vector2setShort     : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Short](row, n, valueShort); if (row.wasNull()) null else set }
  private lazy val vector2setChar      : (Row, Int) => AnyRef = { (row: Row, n: Int) => val set = sql2set[Char](row, n, valueChar); if (row.wasNull()) null else set }


  //  case class ResOneOLD[T](
  //    tpe: String,
  //    toDatalog: T => String,
  //    s2j: Any => Any,
  //    j2s: AnyRef => AnyRef,
  //    seq2t: AnyRef => AnyRef,
  //    set2set: AnyRef => AnyRef,
  //    vector2set: AnyRef => AnyRef
  //  )
  //
  //  lazy val resString    : ResOneOLD[String]     = ResOneOLD("String", dString, s2jString, j2sString, firstString, set2setString, vector2setString)
  //  lazy val resInt       : ResOneOLD[Int]        = ResOneOLD("Int", dInt, s2jInt, j2sInt, firstInt, set2setInt, vector2setInt)
  //  lazy val resLong      : ResOneOLD[Long]       = ResOneOLD("Long", dLong, s2jLong, j2sLong, firstLong, set2setLong, vector2setLong)
  //  lazy val resFloat     : ResOneOLD[Float]      = ResOneOLD("Float", dFloat, s2jFloat, j2sFloat, firstFloat, set2setFloat, vector2setFloat)
  //  lazy val resDouble    : ResOneOLD[Double]     = ResOneOLD("Double", dDouble, s2jDouble, j2sDouble, firstDouble, set2setDouble, vector2setDouble)
  //  lazy val resBoolean   : ResOneOLD[Boolean]    = ResOneOLD("Boolean", dBoolean, s2jBoolean, j2sBoolean, firstBoolean, set2setBoolean, vector2setBoolean)
  //  lazy val resBigInt    : ResOneOLD[BigInt]     = ResOneOLD("BigInt", dBigInt, s2jBigInt, j2sBigInt, firstBigInt, set2setBigInt, vector2setBigInt)
  //  lazy val resBigDecimal: ResOneOLD[BigDecimal] = ResOneOLD("BigDecimal", dBigDecimal, s2jBigDecimal, j2sBigDecimal, firstBigDecimal, set2setBigDecimal, vector2setBigDecimal)
  //  lazy val resDate      : ResOneOLD[Date]       = ResOneOLD("Date", dDate, s2jDate, j2sDate, firstDate, set2setDate, vector2setDate)
  //  lazy val resUUID      : ResOneOLD[UUID]       = ResOneOLD("UUID", dUUID, s2jUUID, j2sUUID, firstUUID, set2setUUID, vector2setUUID)
  //  lazy val resURI       : ResOneOLD[URI]        = ResOneOLD("URI", dURI, s2jURI, j2sURI, firstURI, set2setURI, vector2setURI)
  //  lazy val resByte      : ResOneOLD[Byte]       = ResOneOLD("Byte", dByte, s2jByte, j2sByte, firstByte, set2setByte, vector2setByte)
  //  lazy val resShort     : ResOneOLD[Short]      = ResOneOLD("Short", dShort, s2jShort, j2sShort, firstShort, set2setShort, vector2setShort)
  //  lazy val resChar      : ResOneOLD[Char]       = ResOneOLD("Char", dChar, s2jChar, j2sChar, firstChar, set2setChar, vector2setChar)


  private lazy val j2sOptString     = (v: AnyRef) => v match {
    case null          => Option.empty[String]
    case v: String     => Some(v) // attr_?(<expr>))
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String]) // attr_?
  }
  private lazy val j2sOptInt        = (v: AnyRef) => v match {
    case null => Option.empty[Int]
    // Datomic can return both Integer or Long
    case v: jLong      => Some(v.toInt)
    case v: jInteger   => Some(v.toInt)
    case v: jMap[_, _] => Some(v.values.iterator.next.toString.toInt)
  }
  private lazy val j2sOptLong       = (v: AnyRef) => v match {
    case null          => Option.empty[Long]
    case v: jLong      => Some(v)
    case v: jMap[_, _] => v.values.iterator.next match {
      case l: Long => Some(l)
      // ref
      case map: jMap[_, _] => Some(map.values.iterator.next.asInstanceOf[Long])
    }
  }
  private lazy val j2sOptFloat      = (v: AnyRef) => v match {
    case null          => Option.empty[Float]
    case v: jFloat     => Some(v.toFloat)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Float])
  }
  private lazy val j2sOptDouble     = (v: AnyRef) => v match {
    case null          => Option.empty[Double]
    case v: jDouble    => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Double])
  }
  private lazy val j2sOptBoolean    = (v: AnyRef) => v match {
    case null          => Option.empty[Boolean]
    case v: jBoolean   => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Boolean])
  }
  private lazy val j2sOptBigInt     = (v: AnyRef) => v match {
    case null          => Option.empty[BigInt]
    case v: jBigInt    => Some(BigInt(v))
    case v: jMap[_, _] => Some(BigInt(v.values.iterator.next.asInstanceOf[jBigInt]))
  }
  private lazy val j2sOptBigDecimal = (v: AnyRef) => v match {
    case null           => Option.empty[BigDecimal]
    case v: jBigDecimal => Some(BigDecimal(v))
    case v: jMap[_, _]  => Some(BigDecimal(v.values.iterator.next.asInstanceOf[jBigDecimal]))
  }
  private lazy val j2sOptDate       = (v: AnyRef) => v match {
    case null          => Option.empty[Date]
    case v: Date       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Date])
  }
  private lazy val j2sOptUUID       = (v: AnyRef) => v match {
    case null          => Option.empty[UUID]
    case v: UUID       => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[UUID])
  }
  private lazy val j2sOptURI        = (v: AnyRef) => v match {
    case null          => Option.empty[URI]
    case v: URI        => Some(v)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[URI])
  }
  private lazy val j2sOptByte       = (v: AnyRef) => v match {
    case null          => Option.empty[Byte]
    case v: jInteger   => Some(v.toByte)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toByte)
  }
  private lazy val j2sOptShort      = (v: AnyRef) => v match {
    case null          => Option.empty[Short]
    case v: jInteger   => Some(v.toShort)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[Integer].toShort)
  }
  private lazy val j2sOptChar       = (v: AnyRef) => v match {
    case null          => Option.empty[Char]
    case v: String     => Some(v.head)
    case v: jMap[_, _] => Some(v.values.iterator.next.asInstanceOf[String].charAt(0))
  }


  case class ResOneOpt[T](
    tpe: String,
    sql2oneOpt: (Row, AttrIndex) => Option[T],
    one2sql: T => String
  )

  lazy val resOptString    : ResOneOpt[String]     = ResOneOpt("String", sql2oneOptString, one2sqlString)
  lazy val resOptInt       : ResOneOpt[Int]        = ResOneOpt("Int", sql2oneOptInt, one2sqlInt)
  lazy val resOptLong      : ResOneOpt[Long]       = ResOneOpt("Long", sql2oneOptLong, one2sqlLong)
  lazy val resOptFloat     : ResOneOpt[Float]      = ResOneOpt("Float", sql2oneOptFloat, one2sqlFloat)
  lazy val resOptDouble    : ResOneOpt[Double]     = ResOneOpt("Double", sql2oneOptDouble, one2sqlDouble)
  lazy val resOptBoolean   : ResOneOpt[Boolean]    = ResOneOpt("Boolean", sql2oneOptBoolean, one2sqlBoolean)
  lazy val resOptBigInt    : ResOneOpt[BigInt]     = ResOneOpt("BigInt", sql2oneOptBigInt, one2sqlBigInt)
  lazy val resOptBigDecimal: ResOneOpt[BigDecimal] = ResOneOpt("BigDecimal", sql2oneOptBigDecimal, one2sqlBigDecimal)
  lazy val resOptDate      : ResOneOpt[Date]       = ResOneOpt("Date", sql2oneOptDate, one2sqlDate)
  lazy val resOptUUID      : ResOneOpt[UUID]       = ResOneOpt("UUID", sql2oneOptUUID, one2sqlUUID)
  lazy val resOptURI       : ResOneOpt[URI]        = ResOneOpt("URI", sql2oneOptURI, one2sqlURI)
  lazy val resOptByte      : ResOneOpt[Byte]       = ResOneOpt("Byte", sql2oneOptByte, one2sqlByte)
  lazy val resOptShort     : ResOneOpt[Short]      = ResOneOpt("Short", sql2oneOptShort, one2sqlShort)
  lazy val resOptChar      : ResOneOpt[Char]       = ResOneOpt("Char", sql2oneOptChar, one2sqlChar)


  //  protected lazy val j2sStringOrNull: (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getString(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sIntOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getInt(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sLongOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getLong(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sFloatOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getFloat(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sDoubleOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getDouble(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sBooleanOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getBoolean(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sBigIntOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getBigDecimal(n); if (row.wasNull()) null else v.toBigInteger}
  //  protected lazy val j2sBigDecimalOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getBigDecimal(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sDateOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getDate(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sUUIDOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getString(n); if (row.wasNull()) null else UUID.fromString(v)}
  //  protected lazy val j2sURIOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getString(n); if (row.wasNull()) null else new URI(v)}
  //  protected lazy val j2sByteOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getByte(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sShortOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getShort(n); if (row.wasNull()) null else v}
  //  protected lazy val j2sCharOrNull   : (Row, Int) => Any = (row: Row, n: Int) => {val v = row.getString(n); if (row.wasNull()) null else v.charAt(0)}

  protected lazy val sql2oneOptString    : (Row, Int) => Option[String]     = (row: Row, n: Int) => {
    val v = row.getString(n)
    if (row.wasNull()) Option.empty[String] else Some(v)
  }
  protected lazy val sql2oneOptInt       : (Row, Int) => Option[Int]        = (row: Row, n: Int) => {
    val v = row.getInt(n)
    if (row.wasNull()) Option.empty[Int] else Some(v)
    //    if (row.wasNull()) null else v
  }
  protected lazy val sql2oneOptLong      : (Row, Int) => Option[Long]       = (row: Row, n: Int) => {
    val v = row.getLong(n)
    if (row.wasNull()) Option.empty[Long] else Some(v)
  }
  protected lazy val sql2oneOptFloat     : (Row, Int) => Option[Float]      = (row: Row, n: Int) => {
    val v = row.getFloat(n)
    if (row.wasNull()) Option.empty[Float] else Some(v)
  }
  protected lazy val sql2oneOptDouble    : (Row, Int) => Option[Double]     = (row: Row, n: Int) => {
    val v = row.getDouble(n)
    if (row.wasNull()) Option.empty[Double] else Some(v)
  }
  protected lazy val sql2oneOptBoolean   : (Row, Int) => Option[Boolean]    = (row: Row, n: Int) => {
    val v = row.getBoolean(n)
    if (row.wasNull()) Option.empty[Boolean] else Some(v)
  }
  protected lazy val sql2oneOptBigInt    : (Row, Int) => Option[BigInt]     = (row: Row, n: Int) => {
    val v = row.getBigDecimal(n)
    if (row.wasNull()) Option.empty[BigInt] else Some(v.toBigInteger)
  }
  protected lazy val sql2oneOptBigDecimal: (Row, Int) => Option[BigDecimal] = (row: Row, n: Int) => {
    val v = row.getBigDecimal(n)
    if (row.wasNull()) Option.empty[BigDecimal] else Some(v)
  }
  protected lazy val sql2oneOptDate      : (Row, Int) => Option[Date]       = (row: Row, n: Int) => {
    val v = row.getDate(n)
    if (row.wasNull()) Option.empty[Date] else Some(v)
  }
  protected lazy val sql2oneOptUUID      : (Row, Int) => Option[UUID]       = (row: Row, n: Int) => {
    val v = row.getString(n)
    if (row.wasNull()) Option.empty[UUID] else Some(UUID.fromString(v))
  }
  protected lazy val sql2oneOptURI       : (Row, Int) => Option[URI]        = (row: Row, n: Int) => {
    val v = row.getString(n)
    if (row.wasNull()) Option.empty[URI] else Some(new URI(v))
  }
  protected lazy val sql2oneOptByte      : (Row, Int) => Option[Byte]       = (row: Row, n: Int) => {
    val v = row.getByte(n)
    if (row.wasNull()) Option.empty[Byte] else Some(v)
  }
  protected lazy val sql2oneOptShort     : (Row, Int) => Option[Short]      = (row: Row, n: Int) => {
    val v = row.getShort(n)
    if (row.wasNull()) Option.empty[Short] else Some(v)
  }
  protected lazy val sql2oneOptChar      : (Row, Int) => Option[Char]       = (row: Row, n: Int) => {
    val v = row.getString(n)
    if (row.wasNull()) Option.empty[Char] else Some(v.charAt(0))
  }


  //  case class ResOneOptOLD[T](
  //    tpe: String,
  //    toDatalog: T => String,
  //    s2j: Any => Any,
  //    j2s: AnyRef => AnyRef
  //  )
  //
  //  lazy val resOptString    : ResOneOptOLD[String]     = ResOneOptOLD("String", dString, s2jString, j2sOptString)
  //  lazy val resOptInt       : ResOneOptOLD[Int]        = ResOneOptOLD("Int", dInt, s2jInt, j2sOptInt)
  //  lazy val resOptLong      : ResOneOptOLD[Long]       = ResOneOptOLD("Long", dLong, s2jLong, j2sOptLong)
  //  lazy val resOptFloat     : ResOneOptOLD[Float]      = ResOneOptOLD("Float", dFloat, s2jFloat, j2sOptFloat)
  //  lazy val resOptDouble    : ResOneOptOLD[Double]     = ResOneOptOLD("Double", dDouble, s2jDouble, j2sOptDouble)
  //  lazy val resOptBoolean   : ResOneOptOLD[Boolean]    = ResOneOptOLD("Boolean", dBoolean, s2jBoolean, j2sOptBoolean)
  //  lazy val resOptBigInt    : ResOneOptOLD[BigInt]     = ResOneOptOLD("BigInt", dBigInt, s2jBigInt, j2sOptBigInt)
  //  lazy val resOptBigDecimal: ResOneOptOLD[BigDecimal] = ResOneOptOLD("BigDecimal", dBigDecimal, s2jBigDecimal, j2sOptBigDecimal)
  //  lazy val resOptDate      : ResOneOptOLD[Date]       = ResOneOptOLD("Date", dDate, s2jDate, j2sOptDate)
  //  lazy val resOptUUID      : ResOneOptOLD[UUID]       = ResOneOptOLD("UUID", dUUID, s2jUUID, j2sOptUUID)
  //  lazy val resOptURI       : ResOneOptOLD[URI]        = ResOneOptOLD("URI", dURI, s2jURI, j2sOptURI)
  //  lazy val resOptByte      : ResOneOptOLD[Byte]       = ResOneOptOLD("Byte", dByte, s2jByte, j2sOptByte)
  //  lazy val resOptShort     : ResOneOptOLD[Short]      = ResOneOptOLD("Short", dShort, s2jShort, j2sOptShort)
  //  lazy val resOptChar      : ResOneOptOLD[Char]       = ResOneOptOLD("Char", dChar, s2jChar, j2sOptChar)


  // Nested opt ---------------------------------------------------------------------

  lazy val it2String2: AnyRef => AnyRef = {
    case `none`    => nullValue
    case v: String => v.asInstanceOf[AnyRef]
    case other     => unexpectedValue(other)
  }
  lazy val it2Int2   : AnyRef => AnyRef = {
    // Datomic can return both Integer or Long
    case v: jLong   => v.toInt.asInstanceOf[AnyRef]
    case v: Integer => v.toInt.asInstanceOf[AnyRef]
    case `none`     => nullValue
    case other      => unexpectedValue(other)
  }


  lazy val it2String    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => v
    case other     => unexpectedValue(other)
  }
  lazy val it2Int       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    // Datomic can return both Integer or Long
    case v: jLong   => v.toInt
    case v: Integer => v.toInt
    case `none`     => nullValue
    case other      => unexpectedValue(other)
  }
  lazy val it2Long      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: jLong => v.toLong
    case `none`   => nullValue
    case other    => unexpectedValue(other)
  }
  lazy val it2Float     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: jFloat => v.toFloat
    case `none`    => nullValue
    case other     => unexpectedValue(other)
  }
  lazy val it2Double    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: jDouble => v.toDouble
    case `none`     => nullValue
    case other      => unexpectedValue(other)
  }
  lazy val it2Boolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: jBoolean => v
    case `none`      => nullValue
    case other       => unexpectedValue(other)
  }
  lazy val it2BigInt    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: jBigInt => BigInt(v)
    case `none`     => nullValue
    case other      => unexpectedValue(other)
  }
  lazy val it2BigDecimal: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: jBigDecimal => BigDecimal(v)
    case `none`         => nullValue
    case other          => unexpectedValue(other)
  }
  lazy val it2Date      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: Date => v
    case `none`  => nullValue
    case other   => unexpectedValue(other)
  }
  lazy val it2UUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: UUID => v
    case `none`  => nullValue
    case other   => unexpectedValue(other)
  }
  lazy val it2URI       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: URI => v
    case `none` => nullValue
    case other  => unexpectedValue(other)
  }
  lazy val it2Byte      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: Integer => v.toByte
    case `none`     => nullValue
    case other      => unexpectedValue(other)
  }
  lazy val it2Short     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case v: Integer => v.toShort
    case `none`     => nullValue
    case other      => unexpectedValue(other)
  }
  lazy val it2Char      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none`    => nullValue
    case v: String => v.charAt(0)
    case other     => unexpectedValue(other)
  }


  lazy val it2OptString    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.toString)
  }
  lazy val it2OptInt       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    // Datomic can return both Integer or Long
    case `none`     => None
    case v: Integer => Some(v.toInt)
    case v: jLong   => Some(v.toInt)
  }
  lazy val it2OptLong      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[jLong].toLong)
  }
  lazy val it2OptFloat     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[jFloat].toFloat)
  }
  lazy val it2OptDouble    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[jDouble].toDouble)
  }
  lazy val it2OptBoolean   : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Boolean])
  }
  lazy val it2OptBigInt    : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(BigInt(v.asInstanceOf[jBigInt]))
  }
  lazy val it2OptBigDecimal: jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(BigDecimal(v.asInstanceOf[jBigDecimal]))
  }
  lazy val it2OptDate      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Date])
  }
  lazy val it2OptUUID      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[UUID])
  }
  lazy val it2OptURI       : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[URI])
  }
  lazy val it2OptByte      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Integer].toByte)
  }
  lazy val it2OptShort     : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[Integer].toShort)
  }
  lazy val it2OptChar      : jIterator[_] => Any = (it: jIterator[_]) => it.next match {
    case `none` => None
    case v      => Some(v.asInstanceOf[String].charAt(0))
  }
}