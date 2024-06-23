package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.sql.core.javaSql.PrepStmt

trait ResolveExprSeq extends ResolveExpr { self: SqlQueryBase with LambdasSeq =>

  override protected def resolveAttrSeqMan(attr: AttrSeqMan): Unit = {
    aritiesAttr()
    attr match {
      case a: AttrSeqManID             => seqMan(a, a.vs, resSeqId)
      case a: AttrSeqManString         => seqMan(a, a.vs, resSeqString)
      case a: AttrSeqManInt            => seqMan(a, a.vs, resSeqInt)
      case a: AttrSeqManLong           => seqMan(a, a.vs, resSeqLong)
      case a: AttrSeqManFloat          => seqMan(a, a.vs, resSeqFloat)
      case a: AttrSeqManDouble         => seqMan(a, a.vs, resSeqDouble)
      case a: AttrSeqManBoolean        => seqMan(a, a.vs, resSeqBoolean)
      case a: AttrSeqManBigInt         => seqMan(a, a.vs, resSeqBigInt)
      case a: AttrSeqManBigDecimal     => seqMan(a, a.vs, resSeqBigDecimal)
      case a: AttrSeqManDate           => seqMan(a, a.vs, resSeqDate)
      case a: AttrSeqManDuration       => seqMan(a, a.vs, resSeqDuration)
      case a: AttrSeqManInstant        => seqMan(a, a.vs, resSeqInstant)
      case a: AttrSeqManLocalDate      => seqMan(a, a.vs, resSeqLocalDate)
      case a: AttrSeqManLocalTime      => seqMan(a, a.vs, resSeqLocalTime)
      case a: AttrSeqManLocalDateTime  => seqMan(a, a.vs, resSeqLocalDateTime)
      case a: AttrSeqManOffsetTime     => seqMan(a, a.vs, resSeqOffsetTime)
      case a: AttrSeqManOffsetDateTime => seqMan(a, a.vs, resSeqOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => seqMan(a, a.vs, resSeqZonedDateTime)
      case a: AttrSeqManUUID           => seqMan(a, a.vs, resSeqUUID)
      case a: AttrSeqManURI            => seqMan(a, a.vs, resSeqURI)
      case a: AttrSeqManByte           => manByteArray(a, a.vs) // Byte Array only semantics
      case a: AttrSeqManShort          => seqMan(a, a.vs, resSeqShort)
      case a: AttrSeqManChar           => seqMan(a, a.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqTac(attr: AttrSeqTac): Unit = {
    attr match {
      case a: AttrSeqTacID             => seqTac(a, a.vs, resSeqId)
      case a: AttrSeqTacString         => seqTac(a, a.vs, resSeqString)
      case a: AttrSeqTacInt            => seqTac(a, a.vs, resSeqInt)
      case a: AttrSeqTacLong           => seqTac(a, a.vs, resSeqLong)
      case a: AttrSeqTacFloat          => seqTac(a, a.vs, resSeqFloat)
      case a: AttrSeqTacDouble         => seqTac(a, a.vs, resSeqDouble)
      case a: AttrSeqTacBoolean        => seqTac(a, a.vs, resSeqBoolean)
      case a: AttrSeqTacBigInt         => seqTac(a, a.vs, resSeqBigInt)
      case a: AttrSeqTacBigDecimal     => seqTac(a, a.vs, resSeqBigDecimal)
      case a: AttrSeqTacDate           => seqTac(a, a.vs, resSeqDate)
      case a: AttrSeqTacDuration       => seqTac(a, a.vs, resSeqDuration)
      case a: AttrSeqTacInstant        => seqTac(a, a.vs, resSeqInstant)
      case a: AttrSeqTacLocalDate      => seqTac(a, a.vs, resSeqLocalDate)
      case a: AttrSeqTacLocalTime      => seqTac(a, a.vs, resSeqLocalTime)
      case a: AttrSeqTacLocalDateTime  => seqTac(a, a.vs, resSeqLocalDateTime)
      case a: AttrSeqTacOffsetTime     => seqTac(a, a.vs, resSeqOffsetTime)
      case a: AttrSeqTacOffsetDateTime => seqTac(a, a.vs, resSeqOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => seqTac(a, a.vs, resSeqZonedDateTime)
      case a: AttrSeqTacUUID           => seqTac(a, a.vs, resSeqUUID)
      case a: AttrSeqTacURI            => seqTac(a, a.vs, resSeqURI)
      case a: AttrSeqTacByte           => tacByteArray(a, a.vs) // Byte Array only semantics
      case a: AttrSeqTacShort          => seqTac(a, a.vs, resSeqShort)
      case a: AttrSeqTacChar           => seqTac(a, a.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqOpt(attr: AttrSeqOpt): Unit = {
    aritiesAttr()
    attr match {
      case a: AttrSeqOptID             => seqOpt(a, resOptSeqId, resSeqId)
      case a: AttrSeqOptString         => seqOpt(a, resOptSeqString, resSeqString)
      case a: AttrSeqOptInt            => seqOpt(a, resOptSeqInt, resSeqInt)
      case a: AttrSeqOptLong           => seqOpt(a, resOptSeqLong, resSeqLong)
      case a: AttrSeqOptFloat          => seqOpt(a, resOptSeqFloat, resSeqFloat)
      case a: AttrSeqOptDouble         => seqOpt(a, resOptSeqDouble, resSeqDouble)
      case a: AttrSeqOptBoolean        => seqOpt(a, resOptSeqBoolean, resSeqBoolean)
      case a: AttrSeqOptBigInt         => seqOpt(a, resOptSeqBigInt, resSeqBigInt)
      case a: AttrSeqOptBigDecimal     => seqOpt(a, resOptSeqBigDecimal, resSeqBigDecimal)
      case a: AttrSeqOptDate           => seqOpt(a, resOptSeqDate, resSeqDate)
      case a: AttrSeqOptDuration       => seqOpt(a, resOptSeqDuration, resSeqDuration)
      case a: AttrSeqOptInstant        => seqOpt(a, resOptSeqInstant, resSeqInstant)
      case a: AttrSeqOptLocalDate      => seqOpt(a, resOptSeqLocalDate, resSeqLocalDate)
      case a: AttrSeqOptLocalTime      => seqOpt(a, resOptSeqLocalTime, resSeqLocalTime)
      case a: AttrSeqOptLocalDateTime  => seqOpt(a, resOptSeqLocalDateTime, resSeqLocalDateTime)
      case a: AttrSeqOptOffsetTime     => seqOpt(a, resOptSeqOffsetTime, resSeqOffsetTime)
      case a: AttrSeqOptOffsetDateTime => seqOpt(a, resOptSeqOffsetDateTime, resSeqOffsetDateTime)
      case a: AttrSeqOptZonedDateTime  => seqOpt(a, resOptSeqZonedDateTime, resSeqZonedDateTime)
      case a: AttrSeqOptUUID           => seqOpt(a, resOptSeqUUID, resSeqUUID)
      case a: AttrSeqOptURI            => seqOpt(a, resOptSeqURI, resSeqURI)
      case a: AttrSeqOptByte           => optByteArray(a) // Byte Array only semantics
      case a: AttrSeqOptShort          => seqOpt(a, resOptSeqShort, resSeqShort)
      case a: AttrSeqOptChar           => seqOpt(a, resOptSeqChar, resSeqChar)
    }
  }


  protected def seqMan[T](
    attr: Attr, args: Seq[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    // Allow empty optional nested rows.
    // So let non-asserted Seq values be checked in NestedOpt
    if (!isNestedOpt) {
      notNull += col
    }
    addCast(res.sql2list)
    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        noCardManyFilterAttrExpr(attr)
      }
      seqExpr(attr, col, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => seqFilterExpr(col, attr.op, filterAttr.name, res, true)
        case filterAttr          => seqFilterExpr(col, attr.op, filterAttr.name, res, true)
      }
    }
  }

  protected def seqTac[T](
    attr: Attr, args: Seq[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      seqExpr(attr, col, args, res, false)
    } { case (dir, filterPath, filterAttr) =>
      filterAttr match {
        case filterAttr: AttrOne => seqFilterExpr(col, attr.op, filterAttr.name, res, false)
        case filterAttr          => seqFilterExpr(col, attr.op, filterAttr.name, res, false)
      }
    }
  }

  protected def seqExpr[T](
    attr: Attr, col: String, seq: Seq[T], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    attr.op match {
      case V       => seqAttr(col, res, mandatory)
      case Eq      => noCollectionMatching(attr)
      case Has     => seqHas(col, seq, res.one2sql, res, mandatory)
      case HasNo   => seqHasNo(col, seq, res.one2sql, res, mandatory)
      case NoValue => if (mandatory) noApplyNothing(attr) else seqNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  protected def seqFilterExpr[T](
    col: String, op: Op, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    op match {
      case Has   => seqFilterHas(col, filterAttr, res, mandatory)
      case HasNo => seqFilterHasNo(col, filterAttr, res, mandatory)
      case other => unexpectedOp(other)
    }
  }

  protected def seqOpt[T](
    attr: Attr, resOpt: ResSeqOpt[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    addCast(resOpt.sql2listOpt)
    attr.op match {
      case V     => seqOptAttr(res)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  protected def seqAttr[T](
    col: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    // override in db implementation if needed
  }

  protected def seqOptAttr[T](
    res: ResSeq[T]
  ): Unit = {
    // override in db implementation if needed
  }

  protected def seqHas[T](
    col: String, seq: Seq[T], one2sql: T => String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    def contains(v: T): String = s"ARRAY_CONTAINS($col, ${one2sql(v)})"
    def containsSeq(seq: Seq[T]): String = seq.map(contains).mkString("(", " AND\n   ", ")")
    seq.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", contains(seq.head)))
      case _ => where += (("", seq.map(v => containsSeq(Seq(v))).mkString("(", " OR\n   ", ")")))
    }
  }

  protected def seqHasNo[T](
    col: String, seq: Seq[T], one2sql: T => String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT ARRAY_CONTAINS($col, ${one2sql(v)})"
    def notContainsSeq(seq: Seq[T]): String = seq.map(notContains).mkString("(", " OR\n   ", ")")
    seq.size match {
      case 0 => ()
      case 1 => where += (("", notContains(seq.head)))
      case _ => where += (("", seq.map(v => notContainsSeq(Seq(v))).mkString("(", " AND\n   ", ")")))
    }
  }

  protected def seqNoValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }


  // filter attribute ----------------------------------------------------------

  protected def seqFilterHas[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
  }

  protected def seqFilterHasNo[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
  }


  // byte array ----------------------------------------------------------------

  private def manByteArray(attr: Attr, byteArray: Array[Byte]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
    attr.filterAttr.fold {
      byteArrayOps(attr, col, byteArray)
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
    // return Byte array as-is
    addCast((row: RS, paramIndex: Int) => row.getBytes(paramIndex))
  }

  private def tacByteArray(attr: Attr, byteArray: Array[Byte]): Unit = {
    val col = getCol(attr: Attr)
    if (!isNestedOpt) {
      notNull += col
    }
    attr.filterAttr.fold {
      byteArrayOps(attr, col, byteArray)
    } { _ =>
      throw ModelError(s"Filter attributes not allowed with byte arrays.")
    }
  }

  private def byteArrayOps(attr: Attr, col: String, byteArray: Array[Byte]): Unit = {
    attr.op match {
      case V  => ()
      case Eq =>
        if (byteArray.length != 0) {
          where += ((col, s"= ?"))
          val paramIndex = inputs.size + 1
          inputs += ((ps: PrepStmt) => ps.setBytes(paramIndex, byteArray))

        } else {
          // Get none
          where += (("FALSE", ""))
        }

      case NoValue =>
        // Get none
        where += (("FALSE", ""))

      case Neq =>
        if (byteArray.length != 0) {
          where += ((col, s"!= ?"))
          val paramIndex = inputs.size + 1
          inputs += ((ps: PrepStmt) => ps.setBytes(paramIndex, byteArray))

        } else {
          // get all
        }

      case _ => throw ModelError(
        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed.")
    }
  }

  private def optByteArray(attr: Attr): Unit = {
    attr.op match {
      case V =>
        val col = getCol(attr: Attr)
        select += col
        // return optional Byte array as-is
        addCast((row: RS, paramIndex: Int) =>
          row.getBytes(paramIndex) match {
            case null      => Option.empty[Array[Byte]]
            case byteArray => Some(byteArray)
          }
        )

      case _ => throw ModelError(
        s"Byte arrays (${attr.cleanName}) can only be retrieved as-is. Filters not allowed."
      )
    }
  }
}