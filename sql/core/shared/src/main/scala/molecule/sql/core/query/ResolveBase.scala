package molecule.sql.core.query

import java.net.URI
import java.util.{Date, UUID, List => jList}
import molecule.base.util.BaseHelpers
import molecule.sql.core.query.casting.NullValueException
import java.sql.{ResultSet => RS}


trait ResolveBase extends BaseHelpers { self: Base =>

  // dummy value for null
  val none = "__none__"

  def nullValue = throw new NullValueException

  def unexpectedValue(other: Any) = other match {
    case null  => throw new Exception(s"Unexpected null value".stripMargin)
    case other =>
      throw new Exception(
        s"""Unexpected:
           |  $other
           |  ${other.getClass}""".stripMargin
      )
  }


  // Scala to sql statement

  protected lazy val one2sqlString    : String => String     = (v: String) => s"'${v.replace("'", "''")}'"
  protected lazy val one2sqlInt       : Int => String        = (v: Int) => s"$v"
  protected lazy val one2sqlLong      : Long => String       = (v: Long) => s"$v"
  protected lazy val one2sqlFloat     : Float => String      = (v: Float) => s"$v"
  protected lazy val one2sqlDouble    : Double => String     = (v: Double) => s"$v"
  protected lazy val one2sqlBoolean   : Boolean => String    = (v: Boolean) => s"$v"
  protected lazy val one2sqlBigInt    : BigInt => String     = (v: BigInt) => s"$v"
  protected lazy val one2sqlBigDecimal: BigDecimal => String = (v: BigDecimal) => s"$v"
  protected lazy val one2sqlDate      : Date => String       = (v: Date) => s"'${date2str(v)}'"
  protected lazy val one2sqlUUID      : UUID => String       = (v: UUID) => s"'${v.toString}'"
  protected lazy val one2sqlURI       : URI => String        = (v: URI) => s"'${v.toString.replace("'", "''")}'"
  protected lazy val one2sqlByte      : Byte => String       = (v: Byte) => s"$v"
  protected lazy val one2sqlShort     : Short => String      = (v: Short) => s"$v"
  protected lazy val one2sqlChar      : Char => String       = (v: Char) => s"'${v.toString}'"


  protected lazy val s2jString    : Any => Any = identity
  protected lazy val s2jInt       : Any => Any = (v: Any) => v.asInstanceOf[Int].toLong.asInstanceOf[Any]
  protected lazy val s2jLong      : Any => Any = identity
  protected lazy val s2jFloat     : Any => Any = identity
  protected lazy val s2jDouble    : Any => Any = identity
  protected lazy val s2jBoolean   : Any => Any = identity
  protected lazy val s2jBigInt    : Any => Any = (v: Any) => v.asInstanceOf[BigInt].bigInteger.asInstanceOf[Any]
  protected lazy val s2jBigDecimal: Any => Any = (v: Any) => v.asInstanceOf[BigDecimal].bigDecimal.asInstanceOf[Any]
  protected lazy val s2jDate      : Any => Any = identity
  protected lazy val s2jUUID      : Any => Any = identity
  protected lazy val s2jURI       : Any => Any = identity
  protected lazy val s2jByte      : Any => Any = (v: Any) => v.asInstanceOf[Byte].toInt.asInstanceOf[Any]
  protected lazy val s2jShort     : Any => Any = (v: Any) => v.asInstanceOf[Short].toInt.asInstanceOf[Any]
  protected lazy val s2jChar      : Any => Any = (v: Any) => v.asInstanceOf[Char].toString.asInstanceOf[Any]


  // Used for aggregate count and countDistinct functions
  lazy val toInt: (Row, Int) => Int = (row: Row, n: Int) => row.getLong(n).toInt


  protected lazy val dString    : String => String     = (v: String) => "\"" + escStr(v) + "\""
  protected lazy val dInt       : Int => String        = (v: Int) => v.toString
  protected lazy val dLong      : Long => String       = (v: Long) => v.toString
  protected lazy val dFloat     : Float => String      = (v: Float) => "(float " + v.toString + ")"
  protected lazy val dDouble    : Double => String     = (v: Double) => v.toString
  protected lazy val dBoolean   : Boolean => String    = (v: Boolean) => v.toString
  protected lazy val dBigInt    : BigInt => String     = (v: BigInt) => v.toString + "N"
  protected lazy val dBigDecimal: BigDecimal => String = (v: BigDecimal) => v.toString + "M"
  protected lazy val dDate      : Date => String       = (v: Date) => "#inst \"" + date2datomic(v) + "\""
  protected lazy val dUUID      : UUID => String       = (v: UUID) => "#uuid \"" + v.toString + "\""
  protected lazy val dURI       : URI => String        = (v: URI) => v.toString
  protected lazy val dByte      : Byte => String       = (v: Byte) => v.toString
  protected lazy val dShort     : Short => String      = (v: Short) => v.toString
  protected lazy val dChar      : Char => String       = (v: Char) => "\"" + v.toString + "\""


  protected def jvector2set: AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toSet

  protected def jvector2set(value: AnyRef => Any): AnyRef => AnyRef =
    (v: AnyRef) => v.asInstanceOf[jList[_]].toArray.toSet.map(value)

  protected def sql2set[T](row: Row, n: Int, getValue: RS => T): Set[T] = {
    val arrayResultSet = row.getArray(n).getResultSet
    var set            = Set.empty[T]
    while (arrayResultSet.next()) {
      set += getValue(arrayResultSet)
    }
    set
  }

  protected lazy val valueString    : RS => String     = (rs: RS) => rs.getString(2)
  protected lazy val valueInt       : RS => Int        = (rs: RS) => rs.getInt(2)
  protected lazy val valueLong      : RS => Long       = (rs: RS) => rs.getLong(2)
  protected lazy val valueFloat     : RS => Float      = (rs: RS) => rs.getFloat(2)
  protected lazy val valueDouble    : RS => Double     = (rs: RS) => rs.getDouble(2)
  protected lazy val valueBoolean   : RS => Boolean    = (rs: RS) => rs.getBoolean(2)
  protected lazy val valueBigInt    : RS => BigInt     = (rs: RS) => rs.getBigDecimal(2).toBigInteger
  protected lazy val valueBigDecimal: RS => BigDecimal = (rs: RS) => rs.getBigDecimal(2)
  protected lazy val valueDate      : RS => Date       = (rs: RS) => rs.getDate(2)
  protected lazy val valueUUID      : RS => UUID       = (rs: RS) => UUID.fromString(rs.getString(2))
  protected lazy val valueURI       : RS => URI        = (rs: RS) => new URI(rs.getString(2))
  protected lazy val valueByte      : RS => Byte       = (rs: RS) => rs.getByte(2)
  protected lazy val valueShort     : RS => Short      = (rs: RS) => rs.getShort(2)
  protected lazy val valueChar      : RS => Char       = (rs: RS) => rs.getString(2).charAt(0)

}