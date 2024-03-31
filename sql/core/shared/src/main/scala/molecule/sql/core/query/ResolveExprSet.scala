package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import scala.reflect.ClassTag

trait ResolveExprSet extends ResolveExpr { self: SqlQueryBase with LambdasSet =>

  override protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrSetManID             => setMan(attr, at.vs, "String", resSetId)
      case at: AttrSetManString         => setMan(attr, at.vs, "String", resSetString)
      case at: AttrSetManInt            => setMan(attr, at.vs, "Int", resSetInt)
      case at: AttrSetManLong           => setMan(attr, at.vs, "Long", resSetLong)
      case at: AttrSetManFloat          => setMan(attr, at.vs, "Float", resSetFloat)
      case at: AttrSetManDouble         => setMan(attr, at.vs, "Double", resSetDouble)
      case at: AttrSetManBoolean        => setMan(attr, at.vs, "Boolean", resSetBoolean)
      case at: AttrSetManBigInt         => setMan(attr, at.vs, "BigInt", resSetBigInt)
      case at: AttrSetManBigDecimal     => setMan(attr, at.vs, "BigDecimal", resSetBigDecimal)
      case at: AttrSetManDate           => setMan(attr, at.vs, "Date", resSetDate)
      case at: AttrSetManDuration       => setMan(attr, at.vs, "Duration", resSetDuration)
      case at: AttrSetManInstant        => setMan(attr, at.vs, "Instant", resSetInstant)
      case at: AttrSetManLocalDate      => setMan(attr, at.vs, "LocalDate", resSetLocalDate)
      case at: AttrSetManLocalTime      => setMan(attr, at.vs, "LocalTime", resSetLocalTime)
      case at: AttrSetManLocalDateTime  => setMan(attr, at.vs, "LocalDateTime", resSetLocalDateTime)
      case at: AttrSetManOffsetTime     => setMan(attr, at.vs, "OffsetTime", resSetOffsetTime)
      case at: AttrSetManOffsetDateTime => setMan(attr, at.vs, "OffsetDateTime", resSetOffsetDateTime)
      case at: AttrSetManZonedDateTime  => setMan(attr, at.vs, "ZonedDateTime", resSetZonedDateTime)
      case at: AttrSetManUUID           => setMan(attr, at.vs, "UUID", resSetUUID)
      case at: AttrSetManURI            => setMan(attr, at.vs, "URI", resSetURI)
      case at: AttrSetManByte           => setMan(attr, at.vs, "Byte", resSetByte)
      case at: AttrSetManShort          => setMan(attr, at.vs, "Short", resSetShort)
      case at: AttrSetManChar           => setMan(attr, at.vs, "Char", resSetChar)
    }
  }

  override protected def resolveAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case at: AttrSetTacID             => setTac(attr, at.vs, "String", resSetId)
      case at: AttrSetTacString         => setTac(attr, at.vs, "String", resSetString)
      case at: AttrSetTacInt            => setTac(attr, at.vs, "Int", resSetInt)
      case at: AttrSetTacLong           => setTac(attr, at.vs, "Long", resSetLong)
      case at: AttrSetTacFloat          => setTac(attr, at.vs, "Float", resSetFloat)
      case at: AttrSetTacDouble         => setTac(attr, at.vs, "Double", resSetDouble)
      case at: AttrSetTacBoolean        => setTac(attr, at.vs, "Boolean", resSetBoolean)
      case at: AttrSetTacBigInt         => setTac(attr, at.vs, "BigInt", resSetBigInt)
      case at: AttrSetTacBigDecimal     => setTac(attr, at.vs, "BigDecimal", resSetBigDecimal)
      case at: AttrSetTacDate           => setTac(attr, at.vs, "Date", resSetDate)
      case at: AttrSetTacDuration       => setTac(attr, at.vs, "Duration", resSetDuration)
      case at: AttrSetTacInstant        => setTac(attr, at.vs, "Instant", resSetInstant)
      case at: AttrSetTacLocalDate      => setTac(attr, at.vs, "LocalDate", resSetLocalDate)
      case at: AttrSetTacLocalTime      => setTac(attr, at.vs, "LocalTime", resSetLocalTime)
      case at: AttrSetTacLocalDateTime  => setTac(attr, at.vs, "LocalDateTime", resSetLocalDateTime)
      case at: AttrSetTacOffsetTime     => setTac(attr, at.vs, "OffsetTime", resSetOffsetTime)
      case at: AttrSetTacOffsetDateTime => setTac(attr, at.vs, "OffsetDateTime", resSetOffsetDateTime)
      case at: AttrSetTacZonedDateTime  => setTac(attr, at.vs, "ZonedDateTime", resSetZonedDateTime)
      case at: AttrSetTacUUID           => setTac(attr, at.vs, "UUID", resSetUUID)
      case at: AttrSetTacURI            => setTac(attr, at.vs, "URI", resSetURI)
      case at: AttrSetTacByte           => setTac(attr, at.vs, "Byte", resSetByte)
      case at: AttrSetTacShort          => setTac(attr, at.vs, "Short", resSetShort)
      case at: AttrSetTacChar           => setTac(attr, at.vs, "Char", resSetChar)
    }
  }

  override protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit = {
    aritiesAttr()
    attr match {
      case _: AttrSetOptID             => setOpt(attr, resOptSetId, resSetId)
      case _: AttrSetOptString         => setOpt(attr, resOptSetString, resSetString)
      case _: AttrSetOptInt            => setOpt(attr, resOptSetInt, resSetInt)
      case _: AttrSetOptLong           => setOpt(attr, resOptSetLong, resSetLong)
      case _: AttrSetOptFloat          => setOpt(attr, resOptSetFloat, resSetFloat)
      case _: AttrSetOptDouble         => setOpt(attr, resOptSetDouble, resSetDouble)
      case _: AttrSetOptBoolean        => setOpt(attr, resOptSetBoolean, resSetBoolean)
      case _: AttrSetOptBigInt         => setOpt(attr, resOptSetBigInt, resSetBigInt)
      case _: AttrSetOptBigDecimal     => setOpt(attr, resOptSetBigDecimal, resSetBigDecimal)
      case _: AttrSetOptDate           => setOpt(attr, resOptSetDate, resSetDate)
      case _: AttrSetOptDuration       => setOpt(attr, resOptSetDuration, resSetDuration)
      case _: AttrSetOptInstant        => setOpt(attr, resOptSetInstant, resSetInstant)
      case _: AttrSetOptLocalDate      => setOpt(attr, resOptSetLocalDate, resSetLocalDate)
      case _: AttrSetOptLocalTime      => setOpt(attr, resOptSetLocalTime, resSetLocalTime)
      case _: AttrSetOptLocalDateTime  => setOpt(attr, resOptSetLocalDateTime, resSetLocalDateTime)
      case _: AttrSetOptOffsetTime     => setOpt(attr, resOptSetOffsetTime, resSetOffsetTime)
      case _: AttrSetOptOffsetDateTime => setOpt(attr, resOptSetOffsetDateTime, resSetOffsetDateTime)
      case _: AttrSetOptZonedDateTime  => setOpt(attr, resOptSetZonedDateTime, resSetZonedDateTime)
      case _: AttrSetOptUUID           => setOpt(attr, resOptSetUUID, resSetUUID)
      case _: AttrSetOptURI            => setOpt(attr, resOptSetURI, resSetURI)
      case _: AttrSetOptByte           => setOpt(attr, resOptSetByte, resSetByte)
      case _: AttrSetOptShort          => setOpt(attr, resOptSetShort, resSetShort)
      case _: AttrSetOptChar           => setOpt(attr, resOptSetChar, resSetChar)
    }
  }


  protected def setMan[T: ClassTag](
    attr: Attr,
    args: Set[T],
    tpe: String,
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
    addCast(res.sql2set)
    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      setExpr(attr, col, attr.op, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe, res, true)
        case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe, res, true)
      }
    }
  }

  protected def setTac[T: ClassTag](
    attr: Attr,
    args: Set[T],
    tpe: String,
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      setExpr(attr, col, attr.op, args, res, false)
    } { case (dir, filterPath, filterAttr) =>
      filterAttr match {
        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe, res, false)
        case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe, res, false)
      }
    }
  }

  protected def setExpr[T: ClassTag](
    attr: Attr, col: String, op: Op, set: Set[T], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    op match {
      case V       => setAttr(col, res, mandatory)
      case Eq      => noCollectionMatching(attr)
      case Has     => has(col, set, res, res.one2sql, mandatory)
      case HasNo   => hasNo(col, set, res, res.one2sql, mandatory)
      case NoValue => if (mandatory) noApplyNothing(attr) else setNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  protected def setExpr2[T](
    col: String, op: Op,
    filterAttr: String, cardOne: Boolean, tpe: String,
    res: ResSet[T], mandatory: Boolean
  ): Unit = {
    op match {
      case Has   => has2(col, filterAttr, cardOne, tpe, res, mandatory)
      case HasNo => hasNo2(col, filterAttr, cardOne, tpe, res, mandatory)
      case other => unexpectedOp(other)
    }
  }

  protected def setOpt[T: ClassTag](
    attr: Attr,
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => setOptAttr(col, res)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  protected def setAttr[T: ClassTag](col: String, res: ResSet[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      select -= col
      select += s"ARRAY_AGG($col)"
      having += "COUNT(*) > 0"
      aggregate = true
      replaceCast(res.nestedArray2coalescedSet)
    }
  }

  protected def setOptAttr[T](col: String, res: ResSet[T]): Unit = {
    select -= col
    groupByCols -= col
    select += s"ARRAY_AGG($col)"
    aggregate = true
    replaceCast(res.nestedArray2optCoalescedSet)
  }

  protected def has[T: ClassTag](
    col: String, set: Set[T], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def contains(v: T): String = s"ARRAY_CONTAINS($col, ${one2sql(v)})"
    def containsSet(set: Set[T]): String = set.map(contains).mkString("(", " AND\n   ", ")")
    if (mandatory) {
      select -= col
      groupByCols -= col
      select += s"ARRAY_AGG($col)"
      aggregate = true
      replaceCast(res.nestedArray2coalescedSet)
    }
    set.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", contains(set.head)))
      case _ => where += (("", set.map(v => containsSet(Set(v))).mkString("(", " OR\n   ", ")")))
    }
  }

  protected def hasNo[T](
    col: String, set: Set[T], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT ARRAY_CONTAINS($col, ${one2sql(v)})"
    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR\n   ", ")")
    if (mandatory) {
      select -= col
      groupByCols -= col
      select += s"ARRAY_AGG($col)"
      aggregate = true
      replaceCast(res.nestedArray2coalescedSet)
    }
    set.size match {
      case 0 => ()
      case 1 => where += (("", notContains(set.head)))
      case _ => where += (("", set.map(v => notContainsSet(Set(v))).mkString("(", " AND\n   ", ")")))
    }
  }

  protected def setNoValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }


  // filter attribute ----------------------------------------------------------

  protected def has2[T](
    col: String, filterAttr: String, filterCardOne: Boolean, tpe: String,
    res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (filterCardOne) {
      where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      if (mandatory) {
        select -= col
        select += s"ARRAY_AGG($col)"
        having += "COUNT(*) > 0"
        aggregate = true
        replaceCast(res.nestedArray2coalescedSet)
      }
      where += (("", s"has_$tpe($col, $filterAttr)"))
    }
  }

  protected def hasNo2[T](
    col: String, filterAttr: String, filterCardOne: Boolean, tpe: String,
    res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (filterCardOne) {
      if (mandatory) {
        select -= col
        select += s"ARRAY_AGG($col)"
        having += "COUNT(*) > 0"
        aggregate = true
        replaceCast(res.nestedArray2coalescedSet)
      }
      where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"hasNo_$tpe($col, $filterAttr)"))
    }
  }
}