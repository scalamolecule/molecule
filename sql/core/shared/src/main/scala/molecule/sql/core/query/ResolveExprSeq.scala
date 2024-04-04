package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.sql.core.javaSql.PrepStmt
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

trait ResolveExprSeq extends ResolveExpr { self: SqlQueryBase with LambdasSeq =>

  override protected def resolveAttrSeqMan(attr: AttrSeqMan): Unit = {
    aritiesAttr()
    attr match {
      case a: AttrSeqManID             => seqMan(attr, a.vs, resSeqId)
      case a: AttrSeqManString         => seqMan(attr, a.vs, resSeqString)
      case a: AttrSeqManInt            => seqMan(attr, a.vs, resSeqInt)
      case a: AttrSeqManLong           => seqMan(attr, a.vs, resSeqLong)
      case a: AttrSeqManFloat          => seqMan(attr, a.vs, resSeqFloat)
      case a: AttrSeqManDouble         => seqMan(attr, a.vs, resSeqDouble)
      case a: AttrSeqManBoolean        => seqMan(attr, a.vs, resSeqBoolean)
      case a: AttrSeqManBigInt         => seqMan(attr, a.vs, resSeqBigInt)
      case a: AttrSeqManBigDecimal     => seqMan(attr, a.vs, resSeqBigDecimal)
      case a: AttrSeqManDate           => seqMan(attr, a.vs, resSeqDate)
      case a: AttrSeqManDuration       => seqMan(attr, a.vs, resSeqDuration)
      case a: AttrSeqManInstant        => seqMan(attr, a.vs, resSeqInstant)
      case a: AttrSeqManLocalDate      => seqMan(attr, a.vs, resSeqLocalDate)
      case a: AttrSeqManLocalTime      => seqMan(attr, a.vs, resSeqLocalTime)
      case a: AttrSeqManLocalDateTime  => seqMan(attr, a.vs, resSeqLocalDateTime)
      case a: AttrSeqManOffsetTime     => seqMan(attr, a.vs, resSeqOffsetTime)
      case a: AttrSeqManOffsetDateTime => seqMan(attr, a.vs, resSeqOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => seqMan(attr, a.vs, resSeqZonedDateTime)
      case a: AttrSeqManUUID           => seqMan(attr, a.vs, resSeqUUID)
      case a: AttrSeqManURI            => seqMan(attr, a.vs, resSeqURI)
      case a: AttrSeqManByte           => manByteArray(attr, a.vs) // Byte Array only semantics
      case a: AttrSeqManShort          => seqMan(attr, a.vs, resSeqShort)
      case a: AttrSeqManChar           => seqMan(attr, a.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqTac(attr: AttrSeqTac): Unit = {
    attr match {
      case a: AttrSeqTacID             => seqTac(attr, a.vs, resSeqId)
      case a: AttrSeqTacString         => seqTac(attr, a.vs, resSeqString)
      case a: AttrSeqTacInt            => seqTac(attr, a.vs, resSeqInt)
      case a: AttrSeqTacLong           => seqTac(attr, a.vs, resSeqLong)
      case a: AttrSeqTacFloat          => seqTac(attr, a.vs, resSeqFloat)
      case a: AttrSeqTacDouble         => seqTac(attr, a.vs, resSeqDouble)
      case a: AttrSeqTacBoolean        => seqTac(attr, a.vs, resSeqBoolean)
      case a: AttrSeqTacBigInt         => seqTac(attr, a.vs, resSeqBigInt)
      case a: AttrSeqTacBigDecimal     => seqTac(attr, a.vs, resSeqBigDecimal)
      case a: AttrSeqTacDate           => seqTac(attr, a.vs, resSeqDate)
      case a: AttrSeqTacDuration       => seqTac(attr, a.vs, resSeqDuration)
      case a: AttrSeqTacInstant        => seqTac(attr, a.vs, resSeqInstant)
      case a: AttrSeqTacLocalDate      => seqTac(attr, a.vs, resSeqLocalDate)
      case a: AttrSeqTacLocalTime      => seqTac(attr, a.vs, resSeqLocalTime)
      case a: AttrSeqTacLocalDateTime  => seqTac(attr, a.vs, resSeqLocalDateTime)
      case a: AttrSeqTacOffsetTime     => seqTac(attr, a.vs, resSeqOffsetTime)
      case a: AttrSeqTacOffsetDateTime => seqTac(attr, a.vs, resSeqOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => seqTac(attr, a.vs, resSeqZonedDateTime)
      case a: AttrSeqTacUUID           => seqTac(attr, a.vs, resSeqUUID)
      case a: AttrSeqTacURI            => seqTac(attr, a.vs, resSeqURI)
      case a: AttrSeqTacByte           => tacByteArray(attr, a.vs) // Byte Array only semantics
      case a: AttrSeqTacShort          => seqTac(attr, a.vs, resSeqShort)
      case a: AttrSeqTacChar           => seqTac(attr, a.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqOpt(attr: AttrSeqOpt): Unit = {
    aritiesAttr()
    attr match {
      case _: AttrSeqOptID             => seqOpt(attr, resOptSeqId, resSeqId)
      case _: AttrSeqOptString         => seqOpt(attr, resOptSeqString, resSeqString)
      case _: AttrSeqOptInt            => seqOpt(attr, resOptSeqInt, resSeqInt)
      case _: AttrSeqOptLong           => seqOpt(attr, resOptSeqLong, resSeqLong)
      case _: AttrSeqOptFloat          => seqOpt(attr, resOptSeqFloat, resSeqFloat)
      case _: AttrSeqOptDouble         => seqOpt(attr, resOptSeqDouble, resSeqDouble)
      case _: AttrSeqOptBoolean        => seqOpt(attr, resOptSeqBoolean, resSeqBoolean)
      case _: AttrSeqOptBigInt         => seqOpt(attr, resOptSeqBigInt, resSeqBigInt)
      case _: AttrSeqOptBigDecimal     => seqOpt(attr, resOptSeqBigDecimal, resSeqBigDecimal)
      case _: AttrSeqOptDate           => seqOpt(attr, resOptSeqDate, resSeqDate)
      case _: AttrSeqOptDuration       => seqOpt(attr, resOptSeqDuration, resSeqDuration)
      case _: AttrSeqOptInstant        => seqOpt(attr, resOptSeqInstant, resSeqInstant)
      case _: AttrSeqOptLocalDate      => seqOpt(attr, resOptSeqLocalDate, resSeqLocalDate)
      case _: AttrSeqOptLocalTime      => seqOpt(attr, resOptSeqLocalTime, resSeqLocalTime)
      case _: AttrSeqOptLocalDateTime  => seqOpt(attr, resOptSeqLocalDateTime, resSeqLocalDateTime)
      case _: AttrSeqOptOffsetTime     => seqOpt(attr, resOptSeqOffsetTime, resSeqOffsetTime)
      case _: AttrSeqOptOffsetDateTime => seqOpt(attr, resOptSeqOffsetDateTime, resSeqOffsetDateTime)
      case _: AttrSeqOptZonedDateTime  => seqOpt(attr, resOptSeqZonedDateTime, resSeqZonedDateTime)
      case _: AttrSeqOptUUID           => seqOpt(attr, resOptSeqUUID, resSeqUUID)
      case _: AttrSeqOptURI            => seqOpt(attr, resOptSeqURI, resSeqURI)
      case a: AttrSeqOptByte           => optByteArray(attr) // Byte Array only semantics
      case _: AttrSeqOptShort          => seqOpt(attr, resOptSeqShort, resSeqShort)
      case _: AttrSeqOptChar           => seqOpt(attr, resOptSeqChar, resSeqChar)
    }
  }


  protected def seqMan[T](
    attr: Attr, args: Seq[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
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
        case filterAttr: AttrOne => seqFilterExpr(col, attr.op, filterAttr.name)
        case filterAttr          => seqFilterExpr(col, attr.op, filterAttr.name)
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
        case filterAttr: AttrOne => seqFilterExpr(col, attr.op, filterAttr.name)
        case filterAttr          => seqFilterExpr(col, attr.op, filterAttr.name)
      }
    }
  }

  protected def seqExpr[T](
    attr: Attr, col: String, seq: Seq[T], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    attr.op match {
      case V       => () // get array as-is
      case Eq      => noCollectionMatching(attr)
      case Has     => seqHas(col, seq, res.one2sql)
      case HasNo   => seqHasNo(col, seq, res.one2sql)
      case NoValue => if (mandatory) noApplyNothing(attr) else seqNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  protected def seqFilterExpr(
    col: String, op: Op, filterAttr: String
  ): Unit = {
    op match {
      case Has   => seqFilterHas(col, filterAttr)
      case HasNo => seqFilterHasNo(col, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  protected def seqOpt[T](
    attr: Attr, resOpt: ResSeqOpt[T], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resOpt.sql2listOpt)
    attr.op match {
      case V     => () // get array as-is
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  protected def seqHas[T](
    col: String, seq: Seq[T], one2sql: T => String
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
    col: String, seq: Seq[T], one2sql: T => String
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

  protected def seqFilterHas(col: String, filterAttr: String): Unit = {
    where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
  }

  protected def seqFilterHasNo(col: String, filterAttr: String): Unit = {
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

  private def tacByteArray(attr: Attr, vs: Array[Byte]): Unit = {
    val col = getCol(attr: Attr)
    if (!isNestedOpt) {
      notNull += col
    }
    attr.filterAttr.fold {
      byteArrayOps(attr, col, vs)
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
        groupByCols += col // if we later need to group by non-aggregated columns

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