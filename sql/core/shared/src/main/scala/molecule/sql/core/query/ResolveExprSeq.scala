package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

trait ResolveExprSeq extends ResolveExpr { self: SqlQueryBase with LambdasSeq =>

  override protected def resolveAttrSeqMan(attr: AttrSeqMan): Unit = {
    aritiesAttr()
    attr match {
      case a: AttrSeqManID             => seqMan(attr, a.vs, "String", resSeqId)
      case a: AttrSeqManString         => seqMan(attr, a.vs, "String", resSeqString)
      case a: AttrSeqManInt            => seqMan(attr, a.vs, "Int", resSeqInt)
      case a: AttrSeqManLong           => seqMan(attr, a.vs, "Long", resSeqLong)
      case a: AttrSeqManFloat          => seqMan(attr, a.vs, "Float", resSeqFloat)
      case a: AttrSeqManDouble         => seqMan(attr, a.vs, "Double", resSeqDouble)
      case a: AttrSeqManBoolean        => seqMan(attr, a.vs, "Boolean", resSeqBoolean)
      case a: AttrSeqManBigInt         => seqMan(attr, a.vs, "BigInt", resSeqBigInt)
      case a: AttrSeqManBigDecimal     => seqMan(attr, a.vs, "BigDecimal", resSeqBigDecimal)
      case a: AttrSeqManDate           => seqMan(attr, a.vs, "Date", resSeqDate)
      case a: AttrSeqManDuration       => seqMan(attr, a.vs, "Duration", resSeqDuration)
      case a: AttrSeqManInstant        => seqMan(attr, a.vs, "Instant", resSeqInstant)
      case a: AttrSeqManLocalDate      => seqMan(attr, a.vs, "LocalDate", resSeqLocalDate)
      case a: AttrSeqManLocalTime      => seqMan(attr, a.vs, "LocalTime", resSeqLocalTime)
      case a: AttrSeqManLocalDateTime  => seqMan(attr, a.vs, "LocalDateTime", resSeqLocalDateTime)
      case a: AttrSeqManOffsetTime     => seqMan(attr, a.vs, "OffsetTime", resSeqOffsetTime)
      case a: AttrSeqManOffsetDateTime => seqMan(attr, a.vs, "OffsetDateTime", resSeqOffsetDateTime)
      case a: AttrSeqManZonedDateTime  => seqMan(attr, a.vs, "ZonedDateTime", resSeqZonedDateTime)
      case a: AttrSeqManUUID           => seqMan(attr, a.vs, "UUID", resSeqUUID)
      case a: AttrSeqManURI            => seqMan(attr, a.vs, "URI", resSeqURI)
      case a: AttrSeqManByte           => manByteArray(attr, a.vs) // Byte Array only semantics
      case a: AttrSeqManShort          => seqMan(attr, a.vs, "Short", resSeqShort)
      case a: AttrSeqManChar           => seqMan(attr, a.vs, "Char", resSeqChar)
    }
  }

  override protected def resolveAttrSeqTac(attr: AttrSeqTac): Unit = {
    attr match {
      case a: AttrSeqTacID             => seqTac(attr, a.vs, "String", resSeqId)
      case a: AttrSeqTacString         => seqTac(attr, a.vs, "String", resSeqString)
      case a: AttrSeqTacInt            => seqTac(attr, a.vs, "Int", resSeqInt)
      case a: AttrSeqTacLong           => seqTac(attr, a.vs, "Long", resSeqLong)
      case a: AttrSeqTacFloat          => seqTac(attr, a.vs, "Float", resSeqFloat)
      case a: AttrSeqTacDouble         => seqTac(attr, a.vs, "Double", resSeqDouble)
      case a: AttrSeqTacBoolean        => seqTac(attr, a.vs, "Boolean", resSeqBoolean)
      case a: AttrSeqTacBigInt         => seqTac(attr, a.vs, "BigInt", resSeqBigInt)
      case a: AttrSeqTacBigDecimal     => seqTac(attr, a.vs, "BigDecimal", resSeqBigDecimal)
      case a: AttrSeqTacDate           => seqTac(attr, a.vs, "Date", resSeqDate)
      case a: AttrSeqTacDuration       => seqTac(attr, a.vs, "Duration", resSeqDuration)
      case a: AttrSeqTacInstant        => seqTac(attr, a.vs, "Instant", resSeqInstant)
      case a: AttrSeqTacLocalDate      => seqTac(attr, a.vs, "LocalDate", resSeqLocalDate)
      case a: AttrSeqTacLocalTime      => seqTac(attr, a.vs, "LocalTime", resSeqLocalTime)
      case a: AttrSeqTacLocalDateTime  => seqTac(attr, a.vs, "LocalDateTime", resSeqLocalDateTime)
      case a: AttrSeqTacOffsetTime     => seqTac(attr, a.vs, "OffsetTime", resSeqOffsetTime)
      case a: AttrSeqTacOffsetDateTime => seqTac(attr, a.vs, "OffsetDateTime", resSeqOffsetDateTime)
      case a: AttrSeqTacZonedDateTime  => seqTac(attr, a.vs, "ZonedDateTime", resSeqZonedDateTime)
      case a: AttrSeqTacUUID           => seqTac(attr, a.vs, "UUID", resSeqUUID)
      case a: AttrSeqTacURI            => seqTac(attr, a.vs, "URI", resSeqURI)
      case a: AttrSeqTacByte           => tacByteArray(attr, a.vs) // Byte Array only semantics
      case a: AttrSeqTacShort          => seqTac(attr, a.vs, "Short", resSeqShort)
      case a: AttrSeqTacChar           => seqTac(attr, a.vs, "Char", resSeqChar)
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
      case a: AttrSeqOptByte           => optByteArray(attr, a.vs, resOptSeqByte) // Byte Array only semantics
      case _: AttrSeqOptShort          => seqOpt(attr, resOptSeqShort, resSeqShort)
      case _: AttrSeqOptChar           => seqOpt(attr, resOptSeqChar, resSeqChar)
    }
  }


  protected def seqMan[T: ClassTag](
    attr: Attr,
    args: Seq[T],
    tpe: String,
    res: ResSeq[T]
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
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-seq filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      seqExpr(attr, col, attr.op, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => seqExpr2(col, attr.op, filterAttr.name, true, tpe, res, true)
        case filterAttr          => seqExpr2(col, attr.op, filterAttr.name, false, tpe, res, true)
      }
    }
  }

  protected def seqTac[T: ClassTag](
    attr: Attr,
    args: Seq[T],
    tpe: String,
    res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      seqExpr(attr, col, attr.op, args, res, false)
    } { case (dir, filterPath, filterAttr) =>
      filterAttr match {
        case filterAttr: AttrOne => seqExpr2(col, attr.op, filterAttr.name, true, tpe, res, false)
        case filterAttr          => seqExpr2(col, attr.op, filterAttr.name, false, tpe, res, false)
      }
    }
  }

  protected def seqExpr[T: ClassTag](
    attr: Attr, col: String, op: Op, seqs: Seq[T], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    op match {
      case V       => seqAttr(col, res, mandatory)
      case Eq      => noCollectionMatching(attr)
      case Has     => has(col, seqs, res, res.one2sql, mandatory)
      case HasNo   => hasNo(col, seqs, res, res.one2sql, mandatory)
      case NoValue => if (mandatory) noApplyNothing(attr) else seqNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  protected def seqExpr2[T](
    col: String, op: Op,
    filterAttr: String, cardOne: Boolean, tpe: String,
    res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    op match {
      case Has   => has2(col, filterAttr, cardOne, tpe, res, mandatory)
      case HasNo => hasNo2(col, filterAttr, cardOne, tpe, res, mandatory)
      case other => unexpectedOp(other)
    }
  }

  protected def seqOpt[T: ClassTag](
    attr: Attr,
    resOpt: ResSeqOpt[T],
    res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resOpt.sql2listOpt)
    attr.op match {
      case V     => seqOptAttr(col, res)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  protected def seqAttr[T: ClassTag](col: String, res: ResSeq[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      //      select -= col
      //      select += s"ARRAY_AGG($col)"
      //      having += "COUNT(*) > 0"
      //      aggregate = true
      //      replaceCast(res.nestedArray2coalescedSet)
    }
  }

  protected def seqOptAttr[T](col: String, res: ResSeq[T]): Unit = {
    //    select -= col
    //    groupByCols -= col
    //    select += s"ARRAY_AGG($col)"
    //    aggregate = true
    //    replaceCast(res.nestedArray2optCoalescedSet)
  }

  protected def has[T: ClassTag](
    col: String, seq: Seq[T], res: ResSeq[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def contains(v: T): String = s"ARRAY_CONTAINS($col, ${one2sql(v)})"
    def containsSeq(seq: Seq[T]): String = seq.map(contains).mkString("(", " AND\n   ", ")")
    if (mandatory) {
      select -= col
      groupByCols -= col
      select += s"ARRAY_AGG($col)"
      aggregate = true
      //      replaceCast(res.nestedArray2coalescedSet)
    }
    //    seq.length match {
    //      case 0 => where += (("FALSE", ""))
    //      case 1 =>
    //        val seq = seq.head
    //        seq.size match {
    //          case 0 => where += (("FALSE", ""))
    //          case 1 => where += (("", contains(seq.head)))
    //          case _ => where += (("", containsSeq(seq)))
    //        }
    //      case _ =>
    //        val expr = seq
    //          .filterNot(_.isEmpty)
    //          .map(containsSeq).mkString("(", " OR\n   ", ")")
    //        where += (("", expr))
    //    }
    seq.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", contains(seq.head)))
      case _ => where += (("", containsSeq(seq)))
    }
  }

  protected def hasNo[T](
    col: String, seq: Seq[T], res: ResSeq[T], one2sql: T => String, mandatory: Boolean
  ): Unit = {
    def notContains(v: T): String = s"NOT ARRAY_CONTAINS($col, ${one2sql(v)})"
    def notContainsSeq(seq: Seq[T]): String = seq.map(notContains).mkString("(", " OR\n   ", ")")
    if (mandatory) {
      select -= col
      groupByCols -= col
      select += s"ARRAY_AGG($col)"
      aggregate = true
      //      replaceCast(res.nestedArray2coalescedSet)
    }
    //    seq.length match {
    //      case 0 => ()
    //      case 1 =>
    //        val seq = seq.head
    //        seq.size match {
    //          case 0 => ()
    //          case 1 => where += (("", notContains(seq.head)))
    //          case _ => where += (("", notContainsSeq(seq)))
    //        }
    //      case _ =>
    //        val expr = seq
    //          .filterNot(_.isEmpty)
    //          .map(notContainsSeq).mkString("(", " AND\n   ", ")")
    //        where += (("", expr))
    //    }
    seq.size match {
      case 0 => ()
      case 1 => where += (("", notContains(seq.head)))
      case _ => where += (("", notContainsSeq(seq)))
    }
  }

  protected def seqNoValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }


  // filter attribute ----------------------------------------------------------

  protected def has2[T](
    col: String, filterAttr: String, filterCardOne: Boolean, tpe: String,
    res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    if (filterCardOne) {
      where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      if (mandatory) {
        select -= col
        select += s"ARRAY_AGG($col)"
        having += "COUNT(*) > 0"
        aggregate = true
        //        replaceCast(res.nestedArray2coalescedSet)
      }
      where += (("", s"has_$tpe($col, $filterAttr)"))
    }
  }

  protected def hasNo2[T](
    col: String, filterAttr: String, filterCardOne: Boolean, tpe: String,
    res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    if (filterCardOne) {
      if (mandatory) {
        select -= col
        select += s"ARRAY_AGG($col)"
        having += "COUNT(*) > 0"
        aggregate = true
        //        replaceCast(res.nestedArray2coalescedSet)
      }
      where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"hasNo_$tpe($col, $filterAttr)"))
    }
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

  private def byteArrayOps(attr: Attr, col: String, vs: Array[Byte]): Unit = {
    attr.op match {
      case V   => ()
      case Eq  =>
        //        if (vs.nonEmpty && vs.flatten.nonEmpty) {
        //        val x = vs.isEmpty
        if (vs.length != 0) {
          //          args += vs.head
          //          in += s"$v-input"
          //          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause
          where += ((col, s"= array[8]"))

        } else {
          // Get none
          where += (("FALSE", ""))
        }
      case Neq =>
        if (vs.length != 0) {
          //          args += vs.head
          //          in += s"$v-input"
          //          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
          //          where += s"[(not $v-equal)]" -> wClause
          where += ((col, s"!= array[8]"))


        } else {
          // get all
        }

      case _ => throw ModelError(
        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
    }
  }


  private def optByteArray(
    attr: Attr, optVs: Option[Array[Byte]], resOpt: ResSeqOpt[Byte]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns

    // return Byte array as-is
    addCast((row: RS, paramIndex: Int) =>
      row.getBytes(paramIndex) match {
        case null      => Option.empty[Array[Byte]]
        case byteArray => Some(byteArray)
      }
    )
    attr.op match {
      case V  => // No additional tokens
      case Eq =>
        optVs match {
          //          case Some(arrays) if arrays.nonEmpty =>
          case Some(arrays) if arrays.length != 0 =>
          //            args += arrays.head
          //            find += v
          //            in += s"$v-input"
          //            where += s"[$e $a $v]" -> wClause
          //            where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause

          case Some(emptyArrays) =>
          //            find += v
          //            where += s"[(ground nil) $v]" -> wGround

          case _ =>
          //            none(attr, e, v)
        }

      case Neq =>
      //        find += v
      //        where += s"[$e $a $v]" -> wClause
      //        optVs.collect {
      //          case arrays if arrays.nonEmpty =>
      //          //            args += arrays.head
      //          //            in += s"$v-input"
      //          //            where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
      //          //            where += s"[(not $v-equal)]" -> wClause
      //        }

      case _ => throw ModelError(
        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
    }
    //    addCast(resSeqOpt.j2sOptList) // delegates to specialized cast for byte arrays
  }
}