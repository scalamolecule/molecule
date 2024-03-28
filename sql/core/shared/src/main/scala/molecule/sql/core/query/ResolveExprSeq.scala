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
      case at: AttrSeqManID             => seqMan(attr, "String", at.vs, resSeqId)
      case at: AttrSeqManString         => seqMan(attr, "String", at.vs, resSeqString)
      case at: AttrSeqManInt            => seqMan(attr, "Int", at.vs, resSeqInt)
      case at: AttrSeqManLong           => seqMan(attr, "Long", at.vs, resSeqLong)
      case at: AttrSeqManFloat          => seqMan(attr, "Float", at.vs, resSeqFloat)
      case at: AttrSeqManDouble         => seqMan(attr, "Double", at.vs, resSeqDouble)
      case at: AttrSeqManBoolean        => seqMan(attr, "Boolean", at.vs, resSeqBoolean)
      case at: AttrSeqManBigInt         => seqMan(attr, "BigInt", at.vs, resSeqBigInt)
      case at: AttrSeqManBigDecimal     => seqMan(attr, "BigDecimal", at.vs, resSeqBigDecimal)
      case at: AttrSeqManDate           => seqMan(attr, "Date", at.vs, resSeqDate)
      case at: AttrSeqManDuration       => seqMan(attr, "Duration", at.vs, resSeqDuration)
      case at: AttrSeqManInstant        => seqMan(attr, "Instant", at.vs, resSeqInstant)
      case at: AttrSeqManLocalDate      => seqMan(attr, "LocalDate", at.vs, resSeqLocalDate)
      case at: AttrSeqManLocalTime      => seqMan(attr, "LocalTime", at.vs, resSeqLocalTime)
      case at: AttrSeqManLocalDateTime  => seqMan(attr, "LocalDateTime", at.vs, resSeqLocalDateTime)
      case at: AttrSeqManOffsetTime     => seqMan(attr, "OffsetTime", at.vs, resSeqOffsetTime)
      case at: AttrSeqManOffsetDateTime => seqMan(attr, "OffsetDateTime", at.vs, resSeqOffsetDateTime)
      case at: AttrSeqManZonedDateTime  => seqMan(attr, "ZonedDateTime", at.vs, resSeqZonedDateTime)
      case at: AttrSeqManUUID           => seqMan(attr, "UUID", at.vs, resSeqUUID)
      case at: AttrSeqManURI            => seqMan(attr, "URI", at.vs, resSeqURI)
      case at: AttrSeqManByte           => manByteArray(attr, at.vs) // Byte Array only semantics
      case at: AttrSeqManShort          => seqMan(attr, "Short", at.vs, resSeqShort)
      case at: AttrSeqManChar           => seqMan(attr, "Char", at.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqTac(attr: AttrSeqTac): Unit = {
    attr match {
      case at: AttrSeqTacID             => seqTac(attr, "String", at.vs, resSeqId)
      case at: AttrSeqTacString         => seqTac(attr, "String", at.vs, resSeqString)
      case at: AttrSeqTacInt            => seqTac(attr, "Int", at.vs, resSeqInt)
      case at: AttrSeqTacLong           => seqTac(attr, "Long", at.vs, resSeqLong)
      case at: AttrSeqTacFloat          => seqTac(attr, "Float", at.vs, resSeqFloat)
      case at: AttrSeqTacDouble         => seqTac(attr, "Double", at.vs, resSeqDouble)
      case at: AttrSeqTacBoolean        => seqTac(attr, "Boolean", at.vs, resSeqBoolean)
      case at: AttrSeqTacBigInt         => seqTac(attr, "BigInt", at.vs, resSeqBigInt)
      case at: AttrSeqTacBigDecimal     => seqTac(attr, "BigDecimal", at.vs, resSeqBigDecimal)
      case at: AttrSeqTacDate           => seqTac(attr, "Date", at.vs, resSeqDate)
      case at: AttrSeqTacDuration       => seqTac(attr, "Duration", at.vs, resSeqDuration)
      case at: AttrSeqTacInstant        => seqTac(attr, "Instant", at.vs, resSeqInstant)
      case at: AttrSeqTacLocalDate      => seqTac(attr, "LocalDate", at.vs, resSeqLocalDate)
      case at: AttrSeqTacLocalTime      => seqTac(attr, "LocalTime", at.vs, resSeqLocalTime)
      case at: AttrSeqTacLocalDateTime  => seqTac(attr, "LocalDateTime", at.vs, resSeqLocalDateTime)
      case at: AttrSeqTacOffsetTime     => seqTac(attr, "OffsetTime", at.vs, resSeqOffsetTime)
      case at: AttrSeqTacOffsetDateTime => seqTac(attr, "OffsetDateTime", at.vs, resSeqOffsetDateTime)
      case at: AttrSeqTacZonedDateTime  => seqTac(attr, "ZonedDateTime", at.vs, resSeqZonedDateTime)
      case at: AttrSeqTacUUID           => seqTac(attr, "UUID", at.vs, resSeqUUID)
      case at: AttrSeqTacURI            => seqTac(attr, "URI", at.vs, resSeqURI)
      case at: AttrSeqTacByte           => tacByteArray(attr, at.vs) // Byte Array only semantics
      case at: AttrSeqTacShort          => seqTac(attr, "Short", at.vs, resSeqShort)
      case at: AttrSeqTacChar           => seqTac(attr, "Char", at.vs, resSeqChar)
    }
  }

  override protected def resolveAttrSeqOpt(attr: AttrSeqOpt): Unit = {
    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
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
    attr: Attr, tpe: String, args: Seq[Seq[T]], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
//    addCast(res.sql2set)
    attr.filterAttr.fold {
      val pathAttr = path :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-seq filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      seqExpr(col, attr.op, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => seqExpr2(col, attr.op, filterAttr.name, true, tpe, res, true)
        case filterAttr          => seqExpr2(col, attr.op, filterAttr.name, false, tpe, res, true)
      }
    }
  }

  protected def seqTac[T: ClassTag](
    attr: Attr, tpe: String, args: Seq[Seq[T]], res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      seqExpr(col, attr.op, args, res, false)
    } { case (dir, filterPath, filterAttr) =>
      filterAttr match {
        case filterAttr: AttrOne => seqExpr2(col, attr.op, filterAttr.name, true, tpe, res, false)
        case filterAttr          => seqExpr2(col, attr.op, filterAttr.name, false, tpe, res, false)
      }
    }
  }

  protected def seqExpr[T: ClassTag](
    col: String, op: Op, seqs: Seq[Seq[T]], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    op match {
      case V         => seqAttr(col, res, mandatory)
      case Eq        => seqEqual(col, seqs, res)
      case Neq       => seqNeq(col, seqs, res, mandatory)
//      case Has       => has(col, seqs, res, res.one2sql, mandatory)
//      case HasNo     => hasNo(col, seqs, res, res.one2sql, mandatory)
      case NoValue   => seqNoValue(col)
      case Fn(kw, n) => seqAggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  protected def seqExpr2[T](
    col: String, op: Op,
    filterAttr: String, cardOne: Boolean, tpe: String,
    res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    op match {
      case Eq    => seqEqual2(col, filterAttr, res, mandatory)
      case Neq   => seqNeq2(col, filterAttr)
      case Has   => has2(col, filterAttr, cardOne, tpe, res, mandatory)
      case HasNo => hasNo2(col, filterAttr, cardOne, tpe, res, mandatory)
      case other => unexpectedOp(other)
    }
  }

  protected def seqOpt[T: ClassTag](
    attr: Attr,
    optSeqs: Option[Seq[Seq[T]]],
    resOpt: ResSeqOpt[T],
    res: ResSeq[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
//    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => seqOptAttr(col, res)
      case Eq    => seqOptEqual(col, optSeqs, res)
      case Neq   => seqOptNeq(col, optSeqs, res)
//      case Has   => optHas(col, optSeqs, res, resOpt.one2sql)
//      case HasNo => optHasNo(col, optSeqs, res, resOpt.one2sql)
      case other => unexpectedOp(other)
    }
    ???
  }


  // attr ----------------------------------------------------------------------

  protected def seqAttr[T: ClassTag](col: String, res: ResSeq[T], mandatory: Boolean): Unit = {
    if (mandatory) {
      select -= col
      select += s"ARRAY_AGG($col)"
      having += "COUNT(*) > 0"
      aggregate = true
//      replaceCast(res.nestedArray2coalescedSet)
    }
  }

  protected def seqOptAttr[T](col: String, res: ResSeq[T]): Unit = {
    select -= col
    groupByCols -= col
    select += s"ARRAY_AGG($col)"
    aggregate = true
//    replaceCast(res.nestedArray2optCoalescedSet)
  }


  // eq ------------------------------------------------------------------------

  protected def seqEqual[T](col: String, seqs: Seq[Seq[T]], res: ResSeq[T]): Unit = {
    val seqsNonEmpty = seqs.filterNot(_.isEmpty)
    seqsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
//      case 1 => where += (("", matchSeq(col, res.set2sqls(seqsNonEmpty.head))))
//      case _ => where += (("", matchSeqs(col, seqsNonEmpty, res.set2sqls)))
    }
  }

  protected def seqOptEqual[T](
    col: String, optSeqs: Option[Seq[Seq[T]]], res: ResSeq[T]
  ): Unit = {
    optSeqs.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { seqs =>
      seqEqual(col, seqs, res)
//      replaceCast(res.nestedArray2optCoalescedSet)
    }
  }


  // neq -----------------------------------------------------------------------

  protected def seqNeq[T](
    col: String, seqs: Seq[Seq[T]], res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      select -= col
      groupByCols -= col
      select += s"ARRAY_AGG($col)"
      aggregate = true
//      replaceCast(res.nestedArray2coalescedSet)
    }
    val setsNonEmpty = seqs.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
//      case 1 => where += (("", "NOT " + matchSeq(col, res.set2sqls(setsNonEmpty.head))))
//      case _ => where += (("", "NOT " + matchSeqs(col, setsNonEmpty, res.set2sqls)))
    }
  }

  protected def seqOptNeq[T](
    col: String, optSeqs: Option[Seq[Seq[T]]], res: ResSeq[T]
  ): Unit = {
    optSeqs match {
      case None | Some(Nil) => seqOptAttr(col, res)
      case Some(seqs)       =>
        seqNeq(col, seqs, res, true)
//        replaceCast(res.nestedArray2optCoalescedSet)
    }
    // Only asserted values
    notNull += col
  }


  // has -----------------------------------------------------------------------

  protected def has[T: ClassTag](
    col: String, seqs: Seq[Seq[T]], res: ResSeq[T], one2sql: T => String, mandatory: Boolean
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
    seqs.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val seq = seqs.head
        seq.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", contains(seq.head)))
          case _ => where += (("", containsSeq(seq)))
        }
      case _ =>
        val expr = seqs
          .filterNot(_.isEmpty)
          .map(containsSeq).mkString("(", " OR\n   ", ")")
        where += (("", expr))
    }
  }

  protected def optHas[T: ClassTag](
    col: String,
    optSeqs: Option[Seq[Seq[T]]],
    res: ResSeq[T],
    one2sql: T => String,
  ): Unit = {
    optSeqs.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { seqs =>
      val seqsWithValues = seqs.filterNot(_.isEmpty)
      if (seqsWithValues.nonEmpty) {
        has(col, seqs, res, one2sql, true)
//        replaceCast(res.nestedArray2optCoalescedSet)
      } else {
        where += (("FALSE", ""))
      }
    }
  }


  // hasNo ---------------------------------------------------------------------

  protected def hasNo[T](
    col: String, seqs: Seq[Seq[T]], res: ResSeq[T], one2sql: T => String, mandatory: Boolean
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
    seqs.length match {
      case 0 => ()
      case 1 =>
        val seq = seqs.head
        seq.size match {
          case 0 => ()
          case 1 => where += (("", notContains(seq.head)))
          case _ => where += (("", notContainsSeq(seq)))
        }
      case _ =>
        val expr = seqs
          .filterNot(_.isEmpty)
          .map(notContainsSeq).mkString("(", " AND\n   ", ")")
        where += (("", expr))
    }
  }

  protected def optHasNo[T: ClassTag](
    col: String,
    optSeqs: Option[Seq[Seq[T]]],
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


  // aggregation ---------------------------------------------------------------

  protected def seqAggr[T: ClassTag](
    col: String, fn: String, optN: Option[Int], res: ResSeq[T]
  ): Unit = {
    checkAggrSet()
    select -= col
    lazy val n = optN.getOrElse(0)
    fn match {
      case "distinct" =>
        noBooleanSeqAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
//        replaceCast(res.nestedArray2nestedSet)

      case "min" =>
        noBooleanSeqAggr(res)
        select += s"MIN($col)"
        groupByCols -= col
        aggregate = true
//        replaceCast(res.array2setFirst)

      case "mins" =>
        noBooleanSeqAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
//        replaceCast(res.nestedArray2setAsc(n))

      case "max" =>
        noBooleanSeqAggr(res)
        select += s"MAX($col)"
        groupByCols -= col
        aggregate = true
//        replaceCast(res.array2setLast)

      case "maxs" =>
        noBooleanSeqAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
//        replaceCast(res.nestedArray2setDesc(n))

      case "sample" =>
        noBooleanSeqAggr(res)
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        hardLimit = 1
//        replaceCast(res.nestedArray2coalescedSet)

      case "samples" =>
        noBooleanSeqAggr(res)
        select +=
          s"""ARRAY_SLICE(
             |    ARRAY_AGG(DISTINCT $col order by RAND()),
             |    1,
             |    LEAST(
             |      $n,
             |      ARRAY_LENGTH(ARRAY_AGG(DISTINCT $col))
             |    )
             |  )""".stripMargin
        groupByCols -= col
        aggregate = true
//        replaceCast(res.nestedArray2coalescedSet)


      // Using brute force in the following aggregate functions to be able to
      // aggregate _unique_ values (Set semantics instead of Array semantics)

      case "count" =>
        noBooleanSeqCounts(n)
        // Count of all (non-unique) values
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var count               = 0
            while (outerArrayResultSet.next()) {
              count += outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].length
            }
            count
          }
        )

      case "countDistinct" =>
        noBooleanSeqCounts(n)
        // Count of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Any]
            while (outerArrayResultSet.next()) {
              outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].foreach { value =>
                set += value
              }
            }
            set.size
          }
        )

      case "sum" =>
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
//        replaceCast(res.nestedArray2sum)

      case "median" =>
        // Using brute force and collecting all unique values to calculate the median value
        // Median of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            val list                = ListBuffer.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => list += v.toString.toDouble) // not the most efficient...
            }
            getMedian(list.toList)
          }
        )

      case "avg" =>
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            val list                = ListBuffer.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => list += v.toString.toDouble) // not the most efficient...
            }
            list.sum / list.size
          }
        )

      case "variance" =>
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            val list                = ListBuffer.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => list += v.toString.toDouble) // not the most efficient...
            }
            varianceOf(list.toList: _*)
          }
        )

      case "stddev" =>
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: RS, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            val list                = ListBuffer.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => list += v.toString.toDouble) // not the most efficient...
            }
            stdDevOf(list.toList: _*)
          }
        )

      case other => unexpectedKw(other)
    }
  }


  // Filter attribute filters --------------------------------------------------

  protected def seqEqual2[T](
    col: String, filterAttr: String, res: ResSeq[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      select -= col
      select += s"ARRAY_AGG($col)"
      having += "COUNT(*) > 0"
      aggregate = true
//      replaceCast(res.nestedArray2coalescedSet)
    }
    where += ((col, "= " + filterAttr))
  }

  protected def seqNeq2(col: String, filterAttr: String): Unit = {
    where += ((col, "<> " + filterAttr))
  }

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

  private def manByteArray(attr: Attr, vs: Seq[Array[Byte]]): Unit = {
//    val v = vv
//    val a = nsAttr(attr)
//    find += v
//    attr.filterAttr.fold {
//      where += s"[$e $a $v]" -> wClause
//      byteArrayOps(attr, v, vs)
//
//    } { _ =>
//      throw ModelError(s"Filter attributes not allowed with byte arrays.")
//    }
//    addCast(identity) // return Byte array as-is
    ???
  }

  private def tacByteArray(attr: Attr, vs: Seq[Array[Byte]]): Unit = {
//    val v = vv
//    val a = nsAttr(attr)
//    attr.filterAttr.fold {
//      where += s"[$e $a $v]" -> wClause
//      byteArrayOps(attr, v, vs)
//    } { _ =>
//      throw ModelError(s"Filter attributes not allowed with byte arrays.")
//    }
    ???
  }


  private def optByteArray(
    attr: Attr, optVs: Option[Seq[Array[Byte]]], resSeqOpt: ResSeqOpt[Byte]
  ): Unit = {
//    val v = vv
//    val a = nsAttr(attr)
//    attr.op match {
//      case V =>
//        find += s"(pull $e-$v [[$a :limit nil]]) "
//        where += s"[(identity $e) $e-$v]" -> wGround
//
//      case Eq =>
//        optVs match {
//          case Some(arrays) if arrays.nonEmpty && arrays.flatten.nonEmpty =>
//            args += arrays.head
//            find += v
//            in += s"$v-input"
//            where += s"[$e $a $v]" -> wClause
//            where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause
//
//          case Some(emptyArrays) =>
//            find += v
//            where += s"[(ground nil) $v]" -> wGround
//
//          case _ =>
//            none(attr, e, v)
//        }
//
//      case Neq =>
//        find += v
//        where += s"[$e $a $v]" -> wClause
//        optVs.collect {
//          case arrays if arrays.nonEmpty && arrays.flatten.nonEmpty =>
//            args += arrays.head
//            in += s"$v-input"
//            where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
//            where += s"[(not $v-equal)]" -> wClause
//        }
//
//      case _ => throw ModelError(
//        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
//    }
//    addCast(resSeqOpt.j2sOptList) // delegates to specialized cast for byte arrays
    ???
  }


  private def byteArrayOps(attr: Attr, vs: Seq[Array[Byte]]) = {
//    attr.op match {
//      case V   => ()
//      case Eq  =>
//        if (vs.nonEmpty && vs.flatten.nonEmpty) {
//          args += vs.head
//          in += s"$v-input"
//          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input)]" -> wClause
//        } else {
//          // Get none
//          where += s"[(ground nil) $v]" -> wGround
//        }
//      case Neq =>
//        if (vs.nonEmpty && vs.flatten.nonEmpty) {
//          args += vs.head
//          in += s"$v-input"
//          where += s"[(java.util.Arrays/equals ^bytes $v ^bytes $v-input) $v-equal]" -> wClause
//          where += s"[(not $v-equal)]" -> wClause
//        } else {
//          // get all
//        }
//
//      case _ => throw ModelError(
//        s"Byte arrays can only be retrieved as-is. Filters not allowed.")
//    }
    ???
  }


  // helpers -------------------------------------------------------------------

  protected def matchSeq(col: String, seq: Seq[String]): String = {
    seq
      .map(v => s"ARRAY_CONTAINS($col, $v)")
      .mkString("(\n    ", " AND\n    ", s" AND\n    CARDINALITY($col) = ${seq.size}\n  )")
  }

  protected def matchSeqs[T](
    col: String, seqs: Seq[Seq[T]], seq2sqls: Seq[T] => Seq[String]
  ): String = {
    seqs.map { seq =>
      seq2sqls(seq)
        .map(v => s"ARRAY_CONTAINS($col, $v)")
        .mkString("", " AND\n      ", s" AND\n      CARDINALITY($col) = " + seq.size)
    }.mkString("(\n    (\n      ", "\n    ) OR (\n      ", "\n    )\n  )")
  }

  protected def noBooleanSeqAggr[T](res: ResSeq[T]): Unit = {
    if (res.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  protected def noBooleanSeqCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}