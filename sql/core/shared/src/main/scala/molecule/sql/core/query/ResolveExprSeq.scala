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
      case at: AttrSeqManID             => seqMan(attr, at.vs, "String", resSeqId)
      case at: AttrSeqManString         => seqMan(attr, at.vs, "String", resSeqString)
      case at: AttrSeqManInt            => seqMan(attr, at.vs, "Int", resSeqInt)
      case at: AttrSeqManLong           => seqMan(attr, at.vs, "Long", resSeqLong)
      case at: AttrSeqManFloat          => seqMan(attr, at.vs, "Float", resSeqFloat)
      case at: AttrSeqManDouble         => seqMan(attr, at.vs, "Double", resSeqDouble)
      case at: AttrSeqManBoolean        => seqMan(attr, at.vs, "Boolean", resSeqBoolean)
      case at: AttrSeqManBigInt         => seqMan(attr, at.vs, "BigInt", resSeqBigInt)
      case at: AttrSeqManBigDecimal     => seqMan(attr, at.vs, "BigDecimal", resSeqBigDecimal)
      case at: AttrSeqManDate           => seqMan(attr, at.vs, "Date", resSeqDate)
      case at: AttrSeqManDuration       => seqMan(attr, at.vs, "Duration", resSeqDuration)
      case at: AttrSeqManInstant        => seqMan(attr, at.vs, "Instant", resSeqInstant)
      case at: AttrSeqManLocalDate      => seqMan(attr, at.vs, "LocalDate", resSeqLocalDate)
      case at: AttrSeqManLocalTime      => seqMan(attr, at.vs, "LocalTime", resSeqLocalTime)
      case at: AttrSeqManLocalDateTime  => seqMan(attr, at.vs, "LocalDateTime", resSeqLocalDateTime)
      case at: AttrSeqManOffsetTime     => seqMan(attr, at.vs, "OffsetTime", resSeqOffsetTime)
      case at: AttrSeqManOffsetDateTime => seqMan(attr, at.vs, "OffsetDateTime", resSeqOffsetDateTime)
      case at: AttrSeqManZonedDateTime  => seqMan(attr, at.vs, "ZonedDateTime", resSeqZonedDateTime)
      case at: AttrSeqManUUID           => seqMan(attr, at.vs, "UUID", resSeqUUID)
      case at: AttrSeqManURI            => seqMan(attr, at.vs, "URI", resSeqURI)
      case at: AttrSeqManByte           => manByteArray(attr, at.vs) // Byte Array only semantics
      case at: AttrSeqManShort          => seqMan(attr, at.vs, "Short", resSeqShort)
      case at: AttrSeqManChar           => seqMan(attr, at.vs, "Char", resSeqChar)
    }
  }

  override protected def resolveAttrSeqTac(attr: AttrSeqTac): Unit = {
    attr match {
      case at: AttrSeqTacID             => seqTac(attr, at.vs, "String", resSeqId)
      case at: AttrSeqTacString         => seqTac(attr, at.vs, "String", resSeqString)
      case at: AttrSeqTacInt            => seqTac(attr, at.vs, "Int", resSeqInt)
      case at: AttrSeqTacLong           => seqTac(attr, at.vs, "Long", resSeqLong)
      case at: AttrSeqTacFloat          => seqTac(attr, at.vs, "Float", resSeqFloat)
      case at: AttrSeqTacDouble         => seqTac(attr, at.vs, "Double", resSeqDouble)
      case at: AttrSeqTacBoolean        => seqTac(attr, at.vs, "Boolean", resSeqBoolean)
      case at: AttrSeqTacBigInt         => seqTac(attr, at.vs, "BigInt", resSeqBigInt)
      case at: AttrSeqTacBigDecimal     => seqTac(attr, at.vs, "BigDecimal", resSeqBigDecimal)
      case at: AttrSeqTacDate           => seqTac(attr, at.vs, "Date", resSeqDate)
      case at: AttrSeqTacDuration       => seqTac(attr, at.vs, "Duration", resSeqDuration)
      case at: AttrSeqTacInstant        => seqTac(attr, at.vs, "Instant", resSeqInstant)
      case at: AttrSeqTacLocalDate      => seqTac(attr, at.vs, "LocalDate", resSeqLocalDate)
      case at: AttrSeqTacLocalTime      => seqTac(attr, at.vs, "LocalTime", resSeqLocalTime)
      case at: AttrSeqTacLocalDateTime  => seqTac(attr, at.vs, "LocalDateTime", resSeqLocalDateTime)
      case at: AttrSeqTacOffsetTime     => seqTac(attr, at.vs, "OffsetTime", resSeqOffsetTime)
      case at: AttrSeqTacOffsetDateTime => seqTac(attr, at.vs, "OffsetDateTime", resSeqOffsetDateTime)
      case at: AttrSeqTacZonedDateTime  => seqTac(attr, at.vs, "ZonedDateTime", resSeqZonedDateTime)
      case at: AttrSeqTacUUID           => seqTac(attr, at.vs, "UUID", resSeqUUID)
      case at: AttrSeqTacURI            => seqTac(attr, at.vs, "URI", resSeqURI)
      case at: AttrSeqTacByte           => tacByteArray(attr, at.vs) // Byte Array only semantics
      case at: AttrSeqTacShort          => seqTac(attr, at.vs, "Short", resSeqShort)
      case at: AttrSeqTacChar           => seqTac(attr, at.vs, "Char", resSeqChar)
    }
  }

  override protected def resolveAttrSeqOpt(attr: AttrSeqOpt): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrSeqOptID             => seqOpt(at, at.vs, resOptSeqId, resSeqId)
      case at: AttrSeqOptString         => seqOpt(at, at.vs, resOptSeqString, resSeqString)
      case at: AttrSeqOptInt            => seqOpt(at, at.vs, resOptSeqInt, resSeqInt)
      case at: AttrSeqOptLong           => seqOpt(at, at.vs, resOptSeqLong, resSeqLong)
      case at: AttrSeqOptFloat          => seqOpt(at, at.vs, resOptSeqFloat, resSeqFloat)
      case at: AttrSeqOptDouble         => seqOpt(at, at.vs, resOptSeqDouble, resSeqDouble)
      case at: AttrSeqOptBoolean        => seqOpt(at, at.vs, resOptSeqBoolean, resSeqBoolean)
      case at: AttrSeqOptBigInt         => seqOpt(at, at.vs, resOptSeqBigInt, resSeqBigInt)
      case at: AttrSeqOptBigDecimal     => seqOpt(at, at.vs, resOptSeqBigDecimal, resSeqBigDecimal)
      case at: AttrSeqOptDate           => seqOpt(at, at.vs, resOptSeqDate, resSeqDate)
      case at: AttrSeqOptDuration       => seqOpt(at, at.vs, resOptSeqDuration, resSeqDuration)
      case at: AttrSeqOptInstant        => seqOpt(at, at.vs, resOptSeqInstant, resSeqInstant)
      case at: AttrSeqOptLocalDate      => seqOpt(at, at.vs, resOptSeqLocalDate, resSeqLocalDate)
      case at: AttrSeqOptLocalTime      => seqOpt(at, at.vs, resOptSeqLocalTime, resSeqLocalTime)
      case at: AttrSeqOptLocalDateTime  => seqOpt(at, at.vs, resOptSeqLocalDateTime, resSeqLocalDateTime)
      case at: AttrSeqOptOffsetTime     => seqOpt(at, at.vs, resOptSeqOffsetTime, resSeqOffsetTime)
      case at: AttrSeqOptOffsetDateTime => seqOpt(at, at.vs, resOptSeqOffsetDateTime, resSeqOffsetDateTime)
      case at: AttrSeqOptZonedDateTime  => seqOpt(at, at.vs, resOptSeqZonedDateTime, resSeqZonedDateTime)
      case at: AttrSeqOptUUID           => seqOpt(at, at.vs, resOptSeqUUID, resSeqUUID)
      case at: AttrSeqOptURI            => seqOpt(at, at.vs, resOptSeqURI, resSeqURI)
      case at: AttrSeqOptByte           => optByteArray(attr, at.vs, resOptSeqByte) // Byte Array only semantics
      case at: AttrSeqOptShort          => seqOpt(at, at.vs, resOptSeqShort, resSeqShort)
      case at: AttrSeqOptChar           => seqOpt(at, at.vs, resOptSeqChar, resSeqChar)
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
    optSeqs: Option[Seq[T]],
    resOpt: ResSeqOpt[T],
    res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    //    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => seqOptAttr(col, res)
      case Eq    => noCollectionMatching(attr)
      case Has   => optHas(col, optSeqs, res, resOpt.one2sql)
      case HasNo => optHasNo(col, optSeqs, res, resOpt.one2sql)
      case other => unexpectedOp(other)
    }
    ???
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


  // has -----------------------------------------------------------------------

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

  protected def optHas[T: ClassTag](
    col: String,
    optSeq: Option[Seq[T]],
    res: ResSeq[T],
    one2sql: T => String,
  ): Unit = {
    optSeq.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { seq =>
      //      val seqsWithValues = seqs.filterNot(_.isEmpty)
      //      if (seqsWithValues.nonEmpty) {
      if (seq.nonEmpty) {
        has(col, seq, res, one2sql, true)
        //        replaceCast(res.nestedArray2optCoalescedSet)
      } else {
        where += (("FALSE", ""))
      }
    }
  }


  // hasNo ---------------------------------------------------------------------

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

  protected def optHasNo[T: ClassTag](
    col: String,
    optSeqs: Option[Seq[T]],
    res: ResSeq[T],
    one2sql: T => String
  ): Unit = {
    optSeqs.fold[Unit] {
      seqOptAttr(col, res)
    } { seqs =>
      hasNo(col, seqs, res, one2sql, true)
      //      replaceCast(res.nestedArray2optCoalescedSet)
    }
    // Only asserted values
    notNull += col
  }


  // no value -----------------------------------------------------------------

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

  private def manByteArray(attr: Attr, vs: Array[Byte]): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
    attr.filterAttr.fold {
      byteArrayOps(attr, col, vs)
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
    attr: Attr, optVs: Option[Array[Byte]], resSeqOpt: ResSeqOpt[Byte]
  ): Unit = {
    //    val v = vv
    //    val a = nsAttr(attr)
    attr.op match {
      case V =>
      //        find += s"(pull $e-$v [[$a :limit nil]]) "
      //        where += s"[(identity $e) $e-$v]" -> wGround

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
    ???
  }
}