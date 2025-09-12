package molecule.db.common.transaction

import java.sql.PreparedStatement as PS
import scala.collection.mutable.ListBuffer
import boopickle.Default.*
import molecule.core.dataModel.*
import molecule.core.error.ModelError
import molecule.db.common.spi.SpiHelpers
import molecule.db.common.transaction.strategy.SqlOps
import molecule.db.common.transaction.strategy.update.{UpdateAction, UpdateRoot}

trait SqlUpdate extends ValueTransformers with SpiHelpers { self: ResolveUpdate =>

  protected def updateOne[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    op: Op,
    vs: Seq[T],
    valueSetter: (PS, Int, T) => Unit,
    exts: List[String],
  ): (String, PS => Unit) = {
    lazy val cleanAttr = attr.replace("_", "")
    val cast: String = exts(2)
    op match {
      case Eq | NoValue =>
        vs match {
          case Seq(v) =>
            (s"$attr = ?$cast", (ps: PS) => valueSetter(ps, paramIndex, v))

          case Nil =>
            (s"$attr = ?", (ps: PS) => ps.setNull(paramIndex, java.sql.Types.NULL))

          case vs =>
            throw ModelError(
              s"Can only update one value for attribute `$ent.$cleanAttr`. " +
                s"Found: " + vs.mkString(", ")
            )
        }

      case op: AttrOp =>
        val cast: String = exts(2)
        def handle(computedValue: String) = {
          val colSetter = if (vs.isEmpty)
            (_: PS) => ()
          else
            (ps: PS) => valueSetter(ps, paramIndex, vs.head)
          (s"$attr = $computedValue", colSetter)
        }

        op match {
          // String
          case AttrOp.Append                   => handle(handleAppend(attr, cast))
          case AttrOp.Prepend                  => handle(handlePrepend(attr, cast))
          case AttrOp.SubString(start, length) => handle(substring(attr, start, length))
          case AttrOp.ReplaceAll               => handle(handleReplaceAll(attr, vs))
          case AttrOp.ToLower                  => handle(s"LOWER($attr)")
          case AttrOp.ToUpper                  => handle(s"UPPER($attr)")

          // Number
          case AttrOp.Plus   => handle(s"$attr + ?$cast")
          case AttrOp.Minus  => handle(s"$attr - ?$cast")
          case AttrOp.Times  => handle(s"$attr * ?$cast")
          case AttrOp.Divide => handle(s"$attr / ?$cast")
          case AttrOp.Negate => handle(s"-$attr")
          case AttrOp.Abs    => handle(s"ABS($attr)")
          case AttrOp.AbsNeg => handle(s"-ABS($attr)")
          case AttrOp.Ceil   => handle(s"CEIL($attr)")
          case AttrOp.Floor  => handle(s"FLOOR($attr)")

          // Boolean
          case AttrOp.And => handle(s"$attr AND ?$cast")
          case AttrOp.Or  => handle(s"$attr OR ?$cast")
          case AttrOp.Not => handle(s"NOT($attr)")
        }

      case Even | Odd => throw ModelError(
        s"$ent.$cleanAttr.even and $ent.$cleanAttr.odd can't be used with updates."
      )

      case Remainder => throw ModelError(
        s"Modulo operations like $ent.$cleanAttr.%(${vs.head}) can't be used with updates."
      )

      case _ => throw ModelError(
        s"Can't update attribute $ent.$cleanAttr without an applied or computed value."
      )
    }
  }

  def handleAppend(attr: String, cast: String): String = s"($attr || ?$cast)"
  def handlePrepend(attr: String, cast: String): String = s"(?$cast || $attr)"
  def handleReplaceAll[T](attr: String, vs: Seq[T]): String = s"REGEXP_REPLACE($attr, ?, '${vs(1)}')"

  def substring(attr: String, start: Int, end: Int): String = {
    if (start < 0)
      throw ModelError("Start index should be 0 or more")

    if (start >= end)
      throw ModelError("Start index should be smaller than end index")

    val length = end - start
    val s      = start + 1
    s"SUBSTRING($attr, $s, $length)"
  }

  protected def updateSetEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableEq(ent, attr, paramIndex, set, exts, set2array)
  }

  protected def updateSetAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    set2array: Set[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableAdd(ent, attr, paramIndex, set, exts, set2array)
  }

  protected def updateSetRemove[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    set: Set[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    set2array: Set[T] => Array[AnyRef]
  ): (String, PS => Unit) = {
    updateIterableRemove(ent, attr, paramIndex, set, exts, set2array)
  }

  protected def updateSeqEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableEq(ent, attr, paramIndex, seq, exts, seq2array)
  }

  protected def updateSeqAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    seq2array: Seq[T] => Array[AnyRef],
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    updateIterableAdd(ent, attr, paramIndex, seq, exts, seq2array)
  }

  protected def updateSeqRemove[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    seq: Seq[T],
    transformValue: T => Any,
    exts: List[String],
    one2json: T => String,
    seq2array: Seq[T] => Array[AnyRef]
  ): (String, PS => Unit) = {
    updateIterableRemove(ent, attr, paramIndex, seq, exts, seq2array)
  }

  protected def updateByteArray(
    ent: String,
    attr: String,
    paramIndex: Int,
    byteArray: Array[Byte],
  ): (String, PS => Unit) = {
    val colSetter = if (byteArray.isEmpty) {
      (ps: PS) => ps.setNull(paramIndex, 0)
    } else {
      (ps: PS) => ps.setBytes(paramIndex, byteArray)
    }
    (s"$attr = ?", colSetter)
  }

  protected def updateMapEq[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    noValue: Boolean,
    map: Map[String, T],
    transformValue: T => Any,
    value2json: (StringBuffer, T) => StringBuffer
  ): (String, PS => Unit) = {
    //    val paramIndex = updateAction.setCol(s"$attr = ?")
    val colSetter = if (map.isEmpty) {
      (ps: PS) => ps.setNull(paramIndex, 0)
    } else {
      //      setAttrPresence(ent, attr)
      (ps: PS) => ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
    }


    //    val colSetter = if (byteArray.isEmpty) {
    //      (ps: PS) => ps.setNull(paramIndex, 0)
    //    } else {
    //      (ps: PS) => ps.setBytes(paramIndex, byteArray)
    //    }
    (s"$attr = ?", colSetter)
  }

  protected def updateMapAdd[T](
    ent: String,
    attr: String,
    paramIndex: Int,
    map: Map[String, T],
    transformValue: T => Any,
    exts: List[String],
    value2json: (StringBuffer, T) => StringBuffer,
  ): (String, PS => Unit) = {
    if (map.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ())
    } else {
      //      setAttrPresence(ent, attr)
      //      val scalaBaseType = exts.head
      //      val setAttr       = s"$attr = addPairs_$scalaBaseType($attr, ?)"
      //      val paramIndex    = updateAction.setCol(setAttr)
      val scalaBaseType = exts.head
      val colInput      = s"$attr = addPairs_$scalaBaseType($attr, ?)"
      val colSetter     = (ps: PS) => ps.setBytes(paramIndex, map2jsonByteArray(map, value2json))
      (colInput, colSetter)
    }
  }


  protected def updateMapRemove(
    ent: String,
    attr: String,
    paramIndex: Int,
    keys: Seq[String],
    exts: List[String],
  ): (String, PS => Unit) = {
    val scalaBaseType = exts.head
    val colInput      = s"$attr = removePairs_$scalaBaseType($attr, ?)"
    val colSetter     = if (keys.isEmpty) {
      (ps: PS) => ps.setNull(paramIndex, 0)

    } else {
      //      setAttrPresence(ent, attr)

      //      val paramIndex    = updateAction.setCol(setAttr)
      (ps: PS) => ps.setArray(paramIndex, ps.getConnection.createArrayOf("String", keys.toArray))
    }
    (colInput, colSetter)
  }


  // Helpers -------------------------------------------------------------------

  private def updateIterableEq[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    paramIndex: Int,
    iterable: M[T],
    exts: List[String],
    vs2array: M[T] => Array[AnyRef],
  ): (String, PS => Unit) = {
    //    val dbBaseType = exts(1)
    //    val paramIndex = updateAction.setCol(s"$attr = ?")
    //    if (iterable.nonEmpty) {
    //      setAttrPresence(ent, attr)
    //      addArray(paramIndex, dbBaseType, vs2array(iterable))
    //    } else {
    //      updateAction.addColSetter((ps: PS) => ps.setNull(paramIndex, 0))
    //    }
    val colSetter = if (iterable.isEmpty)
      (ps: PS) => ps.setNull(paramIndex, java.sql.Types.NULL)
    else
      addArray(paramIndex, exts(1), vs2array(iterable))

    (s"$attr = ?", colSetter)
  }

  private def updateIterableAdd[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    paramIndex: Int,
    iterable: M[T],
    exts: List[String],
    iterable2array: M[T] => Array[AnyRef],
  ): (String, PS => Unit) = {
    //    if (iterable.nonEmpty) {
    //      setAttrPresence(ent, attr)
    //      val dbBaseType = exts(1)
    //      val cast       = exts(2) match {
    //        case ""  => ""
    //        case tpe => tpe + "[]"
    //      }
    //      val setAttr    = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
    //      val paramIndex = updateAction.setCol(setAttr)
    //      addArray(paramIndex, dbBaseType, iterable2array(iterable))
    //    }

    //    val cast     = exts(2) match {
    //      case ""  => ""
    //      case tpe => tpe + "[]"
    //    }
    //    val colInput = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"

    if (iterable.isEmpty) {
      (s"$attr = $attr", (ps: PS) => ())
    } else {
      val cast      = exts(2) match {
        case ""  => ""
        case tpe => tpe + "[]"
      }
      val colInput  = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
      //      val dbBaseType = exts(1)
      //      val cast       = exts(2) match {
      //        case ""  => ""
      //        case tpe => tpe + "[]"
      //      }
      //      val setAttr    = s"$attr = COALESCE($attr, ARRAY[]$cast) || ?"
      //      val paramIndex = updateAction.setCol(setAttr)
      val colSetter = addArray(paramIndex, exts(1), iterable2array(iterable))
      (colInput, colSetter)
    }

  }

  private def updateIterableRemove[T, M[_] <: Iterable[?]](
    ent: String,
    attr: String,
    paramIndex: Int,
    iterable: M[T],
    exts: List[String],
    iterable2array: M[T] => Array[AnyRef]
  ): (String, PS => Unit) = {
    //    if (iterable.nonEmpty) {
    //      setAttrPresence(ent, attr)
    //      val scalaBaseType = exts.head
    //      val dbBaseType    = exts(1)
    //      val setAttr       = s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
    //      val paramIndex    = updateAction.setCol(setAttr)
    //      addArray(paramIndex, dbBaseType, iterable2array(iterable))
    //    }

    val scalaBaseType = exts.head
    val dbBaseType    = exts(1)
    val colInput      = s"$attr = removeFromArray_$scalaBaseType($attr, ?)"

    val colSetter = if (iterable.isEmpty)
      (ps: PS) => ps.setNull(paramIndex, java.sql.Types.NULL)
    else {
      //      val scalaBaseType = exts.head
      //      val dbBaseType    = exts(1)
      //      val setAttr       = s"$attr = removeFromArray_$scalaBaseType($attr, ?)"
      //      val paramIndex    = updateAction.setCol(setAttr)
      addArray(paramIndex, exts(1), iterable2array(iterable))
    }

    (colInput, colSetter)
  }


  //  protected def setAttrPresence(ent: String, attr: String): (String, PS => Unit) = {
  //    if (isUpsert) {
  //      // Allow finding where clauses for ids query. Not used otherwise
  //      query.filterAttrs += AttrOneOptByte(ent, attr)
  //    } else {
  //      // Attribute value present in updated data
  //      query.filterAttrs += AttrOneTacByte(ent, attr)
  //
  //      // Used for single entity update with ids and no filters
  //      updateAction.mandatoryCols += s"$ent.$attr IS NOT NULL"
  //    }
  //  }

  private def addArray(
    paramIndex: Int, dbBaseType: String, array: Array[AnyRef]
  ): PS => Unit = {
    //    updateAction.addColSetter((ps: PS) => {
    //      val conn = ps.getConnection
    //      ps.setArray(paramIndex, conn.createArrayOf(dbBaseType, array))
    //    })

    (ps: PS) => {
      val conn = ps.getConnection
      ps.setArray(paramIndex, conn.createArrayOf(dbBaseType, array))
    }
  }


  //  protected def getUpdateId: Long = {
  //    updateAction.ids match {
  //      case List(v) => v
  //      case other   => throw ModelError(
  //        "Expected to update one entity. Found multiple ids: " + other
  //      )
  //    }
  //  }

  override protected lazy val extsID             = List("ID", "BIGINT", "")
  override protected lazy val extsString         = List("String", "LONGVARCHAR", "")
  override protected lazy val extsInt            = List("Int", "INT", "")
  override protected lazy val extsLong           = List("Long", "BIGINT", "")
  override protected lazy val extsFloat          = List("Float", "REAL", "")
  override protected lazy val extsDouble         = List("Double", "DOUBLE", "")
  override protected lazy val extsBoolean        = List("Boolean", "BOOLEAN", "")
  override protected lazy val extsBigInt         = List("BigInt", "DECIMAL(100, 0)", "")
  override protected lazy val extsBigDecimal     = List("BigDecimal", "DECIMAL(65535, 38)", "")
  override protected lazy val extsDate           = List("Date", "BIGINT", "")
  override protected lazy val extsDuration       = List("Duration", "VARCHAR", "")
  override protected lazy val extsInstant        = List("Instant", "VARCHAR", "")
  override protected lazy val extsLocalDate      = List("LocalDate", "VARCHAR", "")
  override protected lazy val extsLocalTime      = List("LocalTime", "VARCHAR", "")
  override protected lazy val extsLocalDateTime  = List("LocalDateTime", "VARCHAR", "")
  override protected lazy val extsOffsetTime     = List("OffsetTime", "VARCHAR", "")
  override protected lazy val extsOffsetDateTime = List("OffsetDateTime", "VARCHAR", "")
  override protected lazy val extsZonedDateTime  = List("ZonedDateTime", "VARCHAR", "")
  override protected lazy val extsUUID           = List("UUID", "UUID", "")
  override protected lazy val extsURI            = List("URI", "VARCHAR", "")
  override protected lazy val extsByte           = List("Byte", "TINYINT", "")
  override protected lazy val extsShort          = List("Short", "SMALLINT", "")
  override protected lazy val extsChar           = List("Char", "CHAR", "")
}