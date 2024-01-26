package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import scala.reflect.ClassTag

trait ResolveExprSet extends ResolveExpr { self: SqlQueryBase with LambdasSet =>

  override protected def resolveAttrSetMan(attr: AttrSetMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrSetManID             => setMan(attr, "String", at.vs, resSetId)
      case at: AttrSetManString         => setMan(attr, "String", at.vs, resSetString)
      case at: AttrSetManInt            => setMan(attr, "Int", at.vs, resSetInt)
      case at: AttrSetManLong           => setMan(attr, "Long", at.vs, resSetLong)
      case at: AttrSetManFloat          => setMan(attr, "Float", at.vs, resSetFloat)
      case at: AttrSetManDouble         => setMan(attr, "Double", at.vs, resSetDouble)
      case at: AttrSetManBoolean        => setMan(attr, "Boolean", at.vs, resSetBoolean)
      case at: AttrSetManBigInt         => setMan(attr, "BigInt", at.vs, resSetBigInt)
      case at: AttrSetManBigDecimal     => setMan(attr, "BigDecimal", at.vs, resSetBigDecimal)
      case at: AttrSetManDate           => setMan(attr, "Date", at.vs, resSetDate)
      case at: AttrSetManDuration       => setMan(attr, "Duration", at.vs, resSetDuration)
      case at: AttrSetManInstant        => setMan(attr, "Instant", at.vs, resSetInstant)
      case at: AttrSetManLocalDate      => setMan(attr, "LocalDate", at.vs, resSetLocalDate)
      case at: AttrSetManLocalTime      => setMan(attr, "LocalTime", at.vs, resSetLocalTime)
      case at: AttrSetManLocalDateTime  => setMan(attr, "LocalDateTime", at.vs, resSetLocalDateTime)
      case at: AttrSetManOffsetTime     => setMan(attr, "OffsetTime", at.vs, resSetOffsetTime)
      case at: AttrSetManOffsetDateTime => setMan(attr, "OffsetDateTime", at.vs, resSetOffsetDateTime)
      case at: AttrSetManZonedDateTime  => setMan(attr, "ZonedDateTime", at.vs, resSetZonedDateTime)
      case at: AttrSetManUUID           => setMan(attr, "UUID", at.vs, resSetUUID)
      case at: AttrSetManURI            => setMan(attr, "URI", at.vs, resSetURI)
      case at: AttrSetManByte           => setMan(attr, "Byte", at.vs, resSetByte)
      case at: AttrSetManShort          => setMan(attr, "Short", at.vs, resSetShort)
      case at: AttrSetManChar           => setMan(attr, "Char", at.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetTac(attr: AttrSetTac): Unit = {
    attr match {
      case at: AttrSetTacID             => setTac(attr, "String", at.vs, resSetId)
      case at: AttrSetTacString         => setTac(attr, "String", at.vs, resSetString)
      case at: AttrSetTacInt            => setTac(attr, "Int", at.vs, resSetInt)
      case at: AttrSetTacLong           => setTac(attr, "Long", at.vs, resSetLong)
      case at: AttrSetTacFloat          => setTac(attr, "Float", at.vs, resSetFloat)
      case at: AttrSetTacDouble         => setTac(attr, "Double", at.vs, resSetDouble)
      case at: AttrSetTacBoolean        => setTac(attr, "Boolean", at.vs, resSetBoolean)
      case at: AttrSetTacBigInt         => setTac(attr, "BigInt", at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal     => setTac(attr, "BigDecimal", at.vs, resSetBigDecimal)
      case at: AttrSetTacDate           => setTac(attr, "Date", at.vs, resSetDate)
      case at: AttrSetTacDuration       => setTac(attr, "Duration", at.vs, resSetDuration)
      case at: AttrSetTacInstant        => setTac(attr, "Instant", at.vs, resSetInstant)
      case at: AttrSetTacLocalDate      => setTac(attr, "LocalDate", at.vs, resSetLocalDate)
      case at: AttrSetTacLocalTime      => setTac(attr, "LocalTime", at.vs, resSetLocalTime)
      case at: AttrSetTacLocalDateTime  => setTac(attr, "LocalDateTime", at.vs, resSetLocalDateTime)
      case at: AttrSetTacOffsetTime     => setTac(attr, "OffsetTime", at.vs, resSetOffsetTime)
      case at: AttrSetTacOffsetDateTime => setTac(attr, "OffsetDateTime", at.vs, resSetOffsetDateTime)
      case at: AttrSetTacZonedDateTime  => setTac(attr, "ZonedDateTime", at.vs, resSetZonedDateTime)
      case at: AttrSetTacUUID           => setTac(attr, "UUID", at.vs, resSetUUID)
      case at: AttrSetTacURI            => setTac(attr, "URI", at.vs, resSetURI)
      case at: AttrSetTacByte           => setTac(attr, "Byte", at.vs, resSetByte)
      case at: AttrSetTacShort          => setTac(attr, "Short", at.vs, resSetShort)
      case at: AttrSetTacChar           => setTac(attr, "Char", at.vs, resSetChar)
    }
  }

  override protected def resolveAttrSetOpt(attr: AttrSetOpt): Unit = {
    aritiesAttr()
    hasOptAttr = true // to avoid redundant None's
    attr match {
      case at: AttrSetOptID             => setOpt(at, at.vs, resOptSetId, resSetId)
      case at: AttrSetOptString         => setOpt(at, at.vs, resOptSetString, resSetString)
      case at: AttrSetOptInt            => setOpt(at, at.vs, resOptSetInt, resSetInt)
      case at: AttrSetOptLong           => setOpt(at, at.vs, resOptSetLong, resSetLong)
      case at: AttrSetOptFloat          => setOpt(at, at.vs, resOptSetFloat, resSetFloat)
      case at: AttrSetOptDouble         => setOpt(at, at.vs, resOptSetDouble, resSetDouble)
      case at: AttrSetOptBoolean        => setOpt(at, at.vs, resOptSetBoolean, resSetBoolean)
      case at: AttrSetOptBigInt         => setOpt(at, at.vs, resOptSetBigInt, resSetBigInt)
      case at: AttrSetOptBigDecimal     => setOpt(at, at.vs, resOptSetBigDecimal, resSetBigDecimal)
      case at: AttrSetOptDate           => setOpt(at, at.vs, resOptSetDate, resSetDate)
      case at: AttrSetOptDuration       => setOpt(at, at.vs, resOptSetDuration, resSetDuration)
      case at: AttrSetOptInstant        => setOpt(at, at.vs, resOptSetInstant, resSetInstant)
      case at: AttrSetOptLocalDate      => setOpt(at, at.vs, resOptSetLocalDate, resSetLocalDate)
      case at: AttrSetOptLocalTime      => setOpt(at, at.vs, resOptSetLocalTime, resSetLocalTime)
      case at: AttrSetOptLocalDateTime  => setOpt(at, at.vs, resOptSetLocalDateTime, resSetLocalDateTime)
      case at: AttrSetOptOffsetTime     => setOpt(at, at.vs, resOptSetOffsetTime, resSetOffsetTime)
      case at: AttrSetOptOffsetDateTime => setOpt(at, at.vs, resOptSetOffsetDateTime, resSetOffsetDateTime)
      case at: AttrSetOptZonedDateTime  => setOpt(at, at.vs, resOptSetZonedDateTime, resSetZonedDateTime)
      case at: AttrSetOptUUID           => setOpt(at, at.vs, resOptSetUUID, resSetUUID)
      case at: AttrSetOptURI            => setOpt(at, at.vs, resOptSetURI, resSetURI)
      case at: AttrSetOptByte           => setOpt(at, at.vs, resOptSetByte, resSetByte)
      case at: AttrSetOptShort          => setOpt(at, at.vs, resOptSetShort, resSetShort)
      case at: AttrSetOptChar           => setOpt(at, at.vs, resOptSetChar, resSetChar)
    }
  }


  protected def setMan[T: ClassTag](
    attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
    addCast(res.sql2set)
    attr.filterAttr.fold {
      if (filterAttrVars.contains(attr.name) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      setExpr(col, attr.op, args, res, true)
    } {
      case (dir, filterPath, filterAttr) => filterAttr match {
        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe)
        case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe)
      }
    }
  }

  protected def setTac[T: ClassTag](
    attr: Attr, tpe: String, args: Seq[Set[T]], res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
    attr.filterAttr.fold {
      setExpr(col, attr.op, args, res, false)
    } { case (dir, filterPath, filterAttr) =>
      filterAttr match {
        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe)
        case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe)
      }
    }
  }

  protected def setExpr[T: ClassTag](
    col: String, op: Op, sets: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    op match {
      case V         => setAttr(col, res, mandatory)
      case Eq        => setEqual(col, sets, res)
      case Neq       => setNeq(col, sets, res, mandatory)
      case Has       => has(col, sets, res, res.one2sql, mandatory)
      case HasNo     => hasNo(col, sets, res, res.one2sql, mandatory)
      case NoValue   => setNoValue(col)
      case Fn(kw, n) => setAggr(col, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  protected def setExpr2(
    col: String, op: Op, filterAttr: String, cardOne: Boolean, tpe: String
  ): Unit = {
    op match {
      case Eq    => setEqual2(col, filterAttr)
      case Neq   => setNeq2(col, filterAttr)
      case Has   => has2(col, filterAttr, cardOne, tpe)
      case HasNo => hasNo2(col, filterAttr, cardOne, tpe)
      case other => unexpectedOp(other)
    }
  }

  protected def setOpt[T: ClassTag](
    attr: Attr,
    optSets: Option[Seq[Set[T]]],
    resOpt: ResSetOpt[T],
    res: ResSet[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resOpt.sql2setOpt)
    attr.op match {
      case V     => setOptAttr(col, res)
      case Eq    => setOptEqual(col, optSets, res)
      case Neq   => setOptNeq(col, optSets, res)
      case Has   => optHas(col, optSets, res, resOpt.one2sql)
      case HasNo => optHasNo(col, optSets, res, resOpt.one2sql)
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


  // eq ------------------------------------------------------------------------

  protected def setEqual[T](col: String, sets: Seq[Set[T]], res: ResSet[T]): Unit = {
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", matchSet(col, res.set2sqls(setsNonEmpty.head))))
      case _ => where += (("", matchSets(col, setsNonEmpty, res.set2sqls)))
    }
  }

  protected def setOptEqual[T](
    col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
  ): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      setEqual(col, sets, res)
      replaceCast(res.nestedArray2optCoalescedSet)
    }
  }


  // neq -----------------------------------------------------------------------

  protected def setNeq[T](
    col: String, sets: Seq[Set[T]], res: ResSet[T], mandatory: Boolean
  ): Unit = {
    if (mandatory) {
      select -= col
      groupByCols -= col
      select += s"ARRAY_AGG($col)"
      aggregate = true
      replaceCast(res.nestedArray2coalescedSet)
    }
    val setsNonEmpty = sets.filterNot(_.isEmpty)
    setsNonEmpty.length match {
      case 0 => ()
      case 1 => where += (("", "NOT " + matchSet(col, res.set2sqls(setsNonEmpty.head))))
      case _ => where += (("", "NOT " + matchSets(col, setsNonEmpty, res.set2sqls)))
    }
  }

  protected def setOptNeq[T](
    col: String, optSets: Option[Seq[Set[T]]], res: ResSet[T]
  ): Unit = {
    optSets match {
      case None | Some(Nil) => setOptAttr(col, res)
      case Some(sets)       =>
        setNeq(col, sets, res, true)
        replaceCast(res.nestedArray2optCoalescedSet)
    }
    // Only asserted values
    notNull += col
  }


  // has -----------------------------------------------------------------------

  protected def has[T: ClassTag](
    col: String, sets: Seq[Set[T]], res: ResSet[T], one2sql: T => String, mandatory: Boolean
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
    sets.length match {
      case 0 => where += (("FALSE", ""))
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => where += (("FALSE", ""))
          case 1 => where += (("", contains(set.head)))
          case _ => where += (("", containsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(containsSet).mkString("(", " OR\n   ", ")")
        where += (("", expr))
    }
  }

  protected def optHas[T: ClassTag](
    col: String,
    optSets: Option[Seq[Set[T]]],
    res: ResSet[T],
    one2sql: T => String,
  ): Unit = {
    optSets.fold[Unit] {
      where += ((col, s"IS NULL"))
    } { sets =>
      val setsWithValues = sets.filterNot(_.isEmpty)
      if (setsWithValues.nonEmpty) {
        has(col, sets, res, one2sql, true)
        replaceCast(res.nestedArray2optCoalescedSet)
      } else {
        where += (("FALSE", ""))
      }
    }
  }


  // hasNo ---------------------------------------------------------------------

  protected def hasNo[T](
    col: String, sets: Seq[Set[T]], res: ResSet[T], one2sql: T => String, mandatory: Boolean
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
    sets.length match {
      case 0 => ()
      case 1 =>
        val set = sets.head
        set.size match {
          case 0 => ()
          case 1 => where += (("", notContains(set.head)))
          case _ => where += (("", notContainsSet(set)))
        }
      case _ =>
        val expr = sets
          .filterNot(_.isEmpty)
          .map(notContainsSet).mkString("(", " AND\n   ", ")")
        where += (("", expr))
    }
  }

  protected def optHasNo[T: ClassTag](
    col: String,
    optSets: Option[Seq[Set[T]]],
    res: ResSet[T],
    one2sql: T => String
  ): Unit = {
    optSets.fold[Unit] {
      setOptAttr(col, res)
    } { sets =>
      hasNo(col, sets, res, one2sql, true)
      replaceCast(res.nestedArray2optCoalescedSet)
    }
    // Only asserted values
    notNull += col
  }


  // no value -----------------------------------------------------------------

  protected def setNoValue(col: String): Unit = {
    notNull -= col
    where += ((col, s"IS NULL"))
  }


  // aggregation ---------------------------------------------------------------

  protected def setAggr[T: ClassTag](
    col: String, fn: String, optN: Option[Int], res: ResSet[T]
  ): Unit = {
    select -= col
    lazy val n = optN.getOrElse(0)
    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2nestedSet)

      case "min" =>
        noBooleanSetAggr(res)
        select += s"MIN($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setFirst)

      case "mins" =>
        noBooleanSetAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2setAsc(n))

      case "max" =>
        noBooleanSetAggr(res)
        select += s"MAX($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setLast)

      case "maxs" =>
        noBooleanSetAggr(res)
        select += s"ARRAY_AGG(DISTINCT $col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.nestedArray2setDesc(n))

      case "sample" =>
        noBooleanSetAggr(res)
        distinct = false
        select += col
        orderBy += ((level, -1, "RAND()", ""))
        hardLimit = 1
        replaceCast(res.nestedArray2coalescedSet)

      case "samples" =>
        noBooleanSetAggr(res)
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
        replaceCast(res.nestedArray2coalescedSet)


      // Using brute force in the following aggregate functions to be able to
      // aggregate _unique_ values (Set semantics instead of Array semantics)

      case "count" =>
        noBooleanSetCounts(n)
        // Count of all (non-unique) values
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var count               = 0
            while (outerArrayResultSet.next()) {
              count += outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]].length
            }
            count
          }
        )

      case "countDistinct" =>
        noBooleanSetCounts(n)
        // Count of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
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
        // Sum of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(res.array2setSum)

      case "median" =>
        // Using brute force and collecting all unique values to calculate the median value
        // Median of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            getMedian(set)
          }
        )

      case "avg" =>
        // Average of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            set.sum / set.size
          }
        )

      case "variance" =>
        // Variance of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            varianceOf(set.toList: _*)
          }
        )

      case "stddev" =>
        // Standard deviation of unique values (Set semantics)
        select += s"ARRAY_AGG($col)"
        groupByCols -= col
        aggregate = true
        replaceCast(
          (row: Row, paramIndex: Int) => {
            val outerArrayResultSet = row.getArray(paramIndex).getResultSet
            var set                 = Set.empty[Double]
            while (outerArrayResultSet.next()) {
              val array = outerArrayResultSet.getArray(2).getArray.asInstanceOf[Array[_]]
              array.foreach(v => set += v.toString.toDouble) // not the most efficient...
            }
            stdDevOf(set.toList: _*)
          }
        )

      case other => unexpectedKw(other)
    }
  }


  // Filter attribute filters --------------------------------------------------

  protected def setEqual2(col: String, filterAttr: String): Unit = {
    where += ((col, "= " + filterAttr))
  }

  protected def setNeq2(col: String, filterAttr: String): Unit = {
    where += ((col, "<> " + filterAttr))
  }

  protected def has2(
    col: String, filterAttr: String, cardOne: Boolean, tpe: String
  ): Unit = {
    if (cardOne) {
      where += (("", s"ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"has_$tpe($col, $filterAttr)"))
    }
  }

  protected def hasNo2(
    col: String, filterAttr: String, cardOne: Boolean, tpe: String
  ): Unit = {
    if (cardOne) {
      where += (("", s"NOT ARRAY_CONTAINS($col, $filterAttr)"))
    } else {
      where += (("", s"hasNo_$tpe($col, $filterAttr)"))
    }
  }


  // helpers -------------------------------------------------------------------

  protected def matchSet(col: String, set: Set[String]): String = {
    set
      .map(v => s"ARRAY_CONTAINS($col, $v)")
      .mkString("(\n    ", " AND\n    ", s" AND\n    CARDINALITY($col) = ${set.size}\n  )")
  }

  protected def matchSets[T](
    col: String, sets: Seq[Set[T]], set2sqls: Set[T] => Set[String]
  ): String = {
    sets.map { set =>
      set2sqls(set)
        .map(v => s"ARRAY_CONTAINS($col, $v)")
        .mkString("", " AND\n      ", s" AND\n      CARDINALITY($col) = " + set.size)
    }.mkString("(\n    (\n      ", "\n    ) OR (\n      ", "\n    )\n  )")
  }

  protected def noBooleanSetAggr[T](res: ResSet[T]): Unit = {
    if (res.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  protected def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}