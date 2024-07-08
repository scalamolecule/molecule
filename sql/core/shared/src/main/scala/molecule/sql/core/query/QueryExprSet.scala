package molecule.sql.core.query

import molecule.boilerplate.ast.Model._
import molecule.core.query.QueryExpr

trait QueryExprSet extends QueryExpr { self: SqlQueryBase with LambdasSet =>

  override protected def queryAttrSetMan(attr: AttrSetMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrSetManID             => setMan(attr, at.vs, resSetId)
      case at: AttrSetManString         => setMan(attr, at.vs, resSetString)
      case at: AttrSetManInt            => setMan(attr, at.vs, resSetInt)
      case at: AttrSetManLong           => setMan(attr, at.vs, resSetLong)
      case at: AttrSetManFloat          => setMan(attr, at.vs, resSetFloat)
      case at: AttrSetManDouble         => setMan(attr, at.vs, resSetDouble)
      case at: AttrSetManBoolean        => setMan(attr, at.vs, resSetBoolean)
      case at: AttrSetManBigInt         => setMan(attr, at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal     => setMan(attr, at.vs, resSetBigDecimal)
      case at: AttrSetManDate           => setMan(attr, at.vs, resSetDate)
      case at: AttrSetManDuration       => setMan(attr, at.vs, resSetDuration)
      case at: AttrSetManInstant        => setMan(attr, at.vs, resSetInstant)
      case at: AttrSetManLocalDate      => setMan(attr, at.vs, resSetLocalDate)
      case at: AttrSetManLocalTime      => setMan(attr, at.vs, resSetLocalTime)
      case at: AttrSetManLocalDateTime  => setMan(attr, at.vs, resSetLocalDateTime)
      case at: AttrSetManOffsetTime     => setMan(attr, at.vs, resSetOffsetTime)
      case at: AttrSetManOffsetDateTime => setMan(attr, at.vs, resSetOffsetDateTime)
      case at: AttrSetManZonedDateTime  => setMan(attr, at.vs, resSetZonedDateTime)
      case at: AttrSetManUUID           => setMan(attr, at.vs, resSetUUID)
      case at: AttrSetManURI            => setMan(attr, at.vs, resSetURI)
      case at: AttrSetManByte           => setMan(attr, at.vs, resSetByte)
      case at: AttrSetManShort          => setMan(attr, at.vs, resSetShort)
      case at: AttrSetManChar           => setMan(attr, at.vs, resSetChar)
    }
  }

  override protected def queryAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case at: AttrSetTacID             => setTac(attr, at.vs, resSetId)
      case at: AttrSetTacString         => setTac(attr, at.vs, resSetString)
      case at: AttrSetTacInt            => setTac(attr, at.vs, resSetInt)
      case at: AttrSetTacLong           => setTac(attr, at.vs, resSetLong)
      case at: AttrSetTacFloat          => setTac(attr, at.vs, resSetFloat)
      case at: AttrSetTacDouble         => setTac(attr, at.vs, resSetDouble)
      case at: AttrSetTacBoolean        => setTac(attr, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt         => setTac(attr, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal     => setTac(attr, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate           => setTac(attr, at.vs, resSetDate)
      case at: AttrSetTacDuration       => setTac(attr, at.vs, resSetDuration)
      case at: AttrSetTacInstant        => setTac(attr, at.vs, resSetInstant)
      case at: AttrSetTacLocalDate      => setTac(attr, at.vs, resSetLocalDate)
      case at: AttrSetTacLocalTime      => setTac(attr, at.vs, resSetLocalTime)
      case at: AttrSetTacLocalDateTime  => setTac(attr, at.vs, resSetLocalDateTime)
      case at: AttrSetTacOffsetTime     => setTac(attr, at.vs, resSetOffsetTime)
      case at: AttrSetTacOffsetDateTime => setTac(attr, at.vs, resSetOffsetDateTime)
      case at: AttrSetTacZonedDateTime  => setTac(attr, at.vs, resSetZonedDateTime)
      case at: AttrSetTacUUID           => setTac(attr, at.vs, resSetUUID)
      case at: AttrSetTacURI            => setTac(attr, at.vs, resSetURI)
      case at: AttrSetTacByte           => setTac(attr, at.vs, resSetByte)
      case at: AttrSetTacShort          => setTac(attr, at.vs, resSetShort)
      case at: AttrSetTacChar           => setTac(attr, at.vs, resSetChar)
    }
  }

  override protected def queryAttrSetOpt(attr: AttrSetOpt): Unit = {
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


  protected def setMan[T](
    attr: Attr, args: Set[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isOptNested) {
      notNull += col
    }
    addCast(res.sql2set)
    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      setExpr(attr, col, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => setFilterExpr(col, attr.op, filterAttr.name, res, true)
        case filterAttr          => setFilterExpr(col, attr.op, filterAttr.name, res, true)
      }
    }
  }

  protected def setTac[T](
    attr: Attr, args: Set[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      setExpr(attr, col, args, res, false)
    } { case (dir, filterPath, filterAttr) =>
      filterAttr match {
        case filterAttr: AttrOne => setFilterExpr(col, attr.op, filterAttr.name, res, false)
        case filterAttr          => setFilterExpr(col, attr.op, filterAttr.name, res, false)
      }
    }
  }

  protected def setExpr[T](
    attr: Attr, col: String, set: Set[T], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    attr.op match {
      case V       => setAttr(col, res, mandatory)
      case Eq      => noCollectionMatching(attr)
      case Has     => setHas(col, set, res, res.one2sql, mandatory)
      case HasNo   => setHasNo(col, set, res, res.one2sql, mandatory)
      case NoValue => if (mandatory) noApplyNothing(attr) else setNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  protected def setFilterExpr[T](
    col: String, op: Op, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    op match {
      case Has   => setFilterHas(col, filterAttr, res, mandatory)
      case HasNo => setFilterHasNo(col, filterAttr, res, mandatory)
      case other => unexpectedOp(other)
    }
  }

  protected def setOpt[T](
    attr: Attr, resOpt: ResSetOpt[T], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => setOptAttr(col, res)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  protected def setAttr[T](col: String, res: ResSet[T], mandatory: Boolean): Unit = {
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
    select += s"ARRAY_AGG($col)"
    aggregate = true
    replaceCast(res.nestedArray2optCoalescedSet)
  }

  protected def setHas[T](
    col: String, set: Set[T], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def contains(v: T): String = s"ARRAY_CONTAINS($col, ${one2sql(v)})"
    def containsSet(set: Set[T]): String = set.map(contains).mkString("(", " AND\n   ", ")")
    if (mandatory) {
      select -= col
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

  protected def setHasNo[T](
    col: String, set: Set[T], res: ResSet[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT ARRAY_CONTAINS($col, ${one2sql(v)})"
    def notContainsSet(set: Set[T]): String = set.map(notContains).mkString("(", " OR\n   ", ")")
    if (mandatory) {
      select -= col
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

  protected def setFilterHas[T](
    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
  }

  protected def setFilterHasNo[T](
    col: String, filterAttr: String, res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      select -= col
      select += s"ARRAY_AGG($col)"
      having += "COUNT(*) > 0"
      aggregate = true
      replaceCast(res.nestedArray2coalescedSet)
    }
    where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
  }
}