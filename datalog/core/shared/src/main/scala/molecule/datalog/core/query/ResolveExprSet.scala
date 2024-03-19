package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprSet[Tpl] { self: Model2DatomicQuery[Tpl] with LambdasSet =>

  protected def resolveAttrSetMan(es: List[Var], attr: AttrSetMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetManID             => man(attr, e, a, at.vs, resSetId, sortOneID(at, attrIndex))
      case at: AttrSetManString         => man(attr, e, a, at.vs, resSetString, sortOneString(at, attrIndex))
      case at: AttrSetManInt            => man(attr, e, a, at.vs, resSetInt, intSorter(at, attrIndex))
      case at: AttrSetManLong           => man(attr, e, a, at.vs, resSetLong, sortOneLong(at, attrIndex))
      case at: AttrSetManFloat          => man(attr, e, a, at.vs, resSetFloat, floatSorter(at, attrIndex))
      case at: AttrSetManDouble         => man(attr, e, a, at.vs, resSetDouble, sortOneDouble(at, attrIndex))
      case at: AttrSetManBoolean        => man(attr, e, a, at.vs, resSetBoolean, sortOneBoolean(at, attrIndex))
      case at: AttrSetManBigInt         => man(attr, e, a, at.vs, resSetBigInt, bigIntSorter(at, attrIndex))
      case at: AttrSetManBigDecimal     => man(attr, e, a, at.vs, resSetBigDecimal, sortOneBigDecimal(at, attrIndex))
      case at: AttrSetManDate           => man(attr, e, a, at.vs, resSetDate, sortOneDate(at, attrIndex))
      case at: AttrSetManDuration       => man(attr, e, a, at.vs, resSetDuration, sortOneDuration(at, attrIndex))
      case at: AttrSetManInstant        => man(attr, e, a, at.vs, resSetInstant, sortOneInstant(at, attrIndex))
      case at: AttrSetManLocalDate      => man(attr, e, a, at.vs, resSetLocalDate, sortOneLocalDate(at, attrIndex))
      case at: AttrSetManLocalTime      => man(attr, e, a, at.vs, resSetLocalTime, sortOneLocalTime(at, attrIndex))
      case at: AttrSetManLocalDateTime  => man(attr, e, a, at.vs, resSetLocalDateTime, sortOneLocalDateTime(at, attrIndex))
      case at: AttrSetManOffsetTime     => man(attr, e, a, at.vs, resSetOffsetTime, sortOneOffsetTime(at, attrIndex))
      case at: AttrSetManOffsetDateTime => man(attr, e, a, at.vs, resSetOffsetDateTime, sortOneOffsetDateTime(at, attrIndex))
      case at: AttrSetManZonedDateTime  => man(attr, e, a, at.vs, resSetZonedDateTime, sortOneZonedDateTime(at, attrIndex))
      case at: AttrSetManUUID           => man(attr, e, a, at.vs, resSetUUID, sortOneUUID(at, attrIndex))
      case at: AttrSetManURI            => man(attr, e, a, at.vs, resSetURI, sortOneURI(at, attrIndex))
      case at: AttrSetManByte           => man(attr, e, a, at.vs, resSetByte, byteSorter(at, attrIndex))
      case at: AttrSetManShort          => man(attr, e, a, at.vs, resSetShort, shortSorter(at, attrIndex))
      case at: AttrSetManChar           => man(attr, e, a, at.vs, resSetChar, sortOneChar(at, attrIndex))
    }
    es
  }

  protected def resolveAttrSetTac(es: List[Var], attr: AttrSetTac): List[Var] = {
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetTacID             => tac(attr, e, a, at.vs, resSetId)
      case at: AttrSetTacString         => tac(attr, e, a, at.vs, resSetString)
      case at: AttrSetTacInt            => tac(attr, e, a, at.vs, resSetInt)
      case at: AttrSetTacLong           => tac(attr, e, a, at.vs, resSetLong)
      case at: AttrSetTacFloat          => tac(attr, e, a, at.vs, resSetFloat)
      case at: AttrSetTacDouble         => tac(attr, e, a, at.vs, resSetDouble)
      case at: AttrSetTacBoolean        => tac(attr, e, a, at.vs, resSetBoolean)
      case at: AttrSetTacBigInt         => tac(attr, e, a, at.vs, resSetBigInt)
      case at: AttrSetTacBigDecimal     => tac(attr, e, a, at.vs, resSetBigDecimal)
      case at: AttrSetTacDate           => tac(attr, e, a, at.vs, resSetDate)
      case at: AttrSetTacDuration       => tac(attr, e, a, at.vs, resSetDuration)
      case at: AttrSetTacInstant        => tac(attr, e, a, at.vs, resSetInstant)
      case at: AttrSetTacLocalDate      => tac(attr, e, a, at.vs, resSetLocalDate)
      case at: AttrSetTacLocalTime      => tac(attr, e, a, at.vs, resSetLocalTime)
      case at: AttrSetTacLocalDateTime  => tac(attr, e, a, at.vs, resSetLocalDateTime)
      case at: AttrSetTacOffsetTime     => tac(attr, e, a, at.vs, resSetOffsetTime)
      case at: AttrSetTacOffsetDateTime => tac(attr, e, a, at.vs, resSetOffsetDateTime)
      case at: AttrSetTacZonedDateTime  => tac(attr, e, a, at.vs, resSetZonedDateTime)
      case at: AttrSetTacUUID           => tac(attr, e, a, at.vs, resSetUUID)
      case at: AttrSetTacURI            => tac(attr, e, a, at.vs, resSetURI)
      case at: AttrSetTacByte           => tac(attr, e, a, at.vs, resSetByte)
      case at: AttrSetTacShort          => tac(attr, e, a, at.vs, resSetShort)
      case at: AttrSetTacChar           => tac(attr, e, a, at.vs, resSetChar)
    }
    es
  }

  protected def resolveAttrSetOpt(es: List[Var], attr: AttrSetOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val (e, a) = (es.last, s":${attr.ns}/${attr.attr}")
    attr match {
      case at: AttrSetOptID             => opt(e, a, at.op, at.vs, resOptSetId, sortOneOptId(at, attrIndex), sortOneID(at, attrIndex))
      case at: AttrSetOptString         => opt(e, a, at.op, at.vs, resOptSetString, sortOneOptString(at, attrIndex), sortOneString(at, attrIndex))
      case at: AttrSetOptInt            => opt(e, a, at.op, at.vs, resOptSetInt, sortOneOptInt(at, attrIndex), sortOneInt(at, attrIndex))
      case at: AttrSetOptLong           => opt(e, a, at.op, at.vs, resOptSetLong, sorterOneOptLong(at, attrIndex), sortOneLong(at, attrIndex))
      case at: AttrSetOptFloat          => opt(e, a, at.op, at.vs, resOptSetFloat, sortOneOptFloat(at, attrIndex), sortOneFloat(at, attrIndex))
      case at: AttrSetOptDouble         => opt(e, a, at.op, at.vs, resOptSetDouble, sortOneOptDouble(at, attrIndex), sortOneDouble(at, attrIndex))
      case at: AttrSetOptBoolean        => opt(e, a, at.op, at.vs, resOptSetBoolean, sortOneOptBoolean(at, attrIndex), sortOneBoolean(at, attrIndex))
      case at: AttrSetOptBigInt         => opt(e, a, at.op, at.vs, resOptSetBigInt, sortOneOptBigInt(at, attrIndex), sortOneBigInt(at, attrIndex))
      case at: AttrSetOptBigDecimal     => opt(e, a, at.op, at.vs, resOptSetBigDecimal, sortOneOptBigDecimal(at, attrIndex), sortOneBigDecimal(at, attrIndex))
      case at: AttrSetOptDate           => opt(e, a, at.op, at.vs, resOptSetDate, sortOneOptDate(at, attrIndex), sortOneDate(at, attrIndex))
      case at: AttrSetOptDuration       => opt(e, a, at.op, at.vs, resOptSetDuration, sortOneOptDuration(at, attrIndex), sortOneDuration(at, attrIndex))
      case at: AttrSetOptInstant        => opt(e, a, at.op, at.vs, resOptSetInstant, sortOneOptInstant(at, attrIndex), sortOneInstant(at, attrIndex))
      case at: AttrSetOptLocalDate      => opt(e, a, at.op, at.vs, resOptSetLocalDate, sortOneOptLocalDate(at, attrIndex), sortOneLocalDate(at, attrIndex))
      case at: AttrSetOptLocalTime      => opt(e, a, at.op, at.vs, resOptSetLocalTime, sortOneOptLocalTime(at, attrIndex), sortOneLocalTime(at, attrIndex))
      case at: AttrSetOptLocalDateTime  => opt(e, a, at.op, at.vs, resOptSetLocalDateTime, sortOneOptLocalDateTime(at, attrIndex), sortOneLocalDateTime(at, attrIndex))
      case at: AttrSetOptOffsetTime     => opt(e, a, at.op, at.vs, resOptSetOffsetTime, sortOneOptOffsetTime(at, attrIndex), sortOneOffsetTime(at, attrIndex))
      case at: AttrSetOptOffsetDateTime => opt(e, a, at.op, at.vs, resOptSetOffsetDateTime, sortOneOptOffsetDateTime(at, attrIndex), sortOneOffsetDateTime(at, attrIndex))
      case at: AttrSetOptZonedDateTime  => opt(e, a, at.op, at.vs, resOptSetZonedDateTime, sortOneOptZonedDateTime(at, attrIndex), sortOneZonedDateTime(at, attrIndex))
      case at: AttrSetOptUUID           => opt(e, a, at.op, at.vs, resOptSetUUID, sortOneOptUUID(at, attrIndex), sortOneUUID(at, attrIndex))
      case at: AttrSetOptURI            => opt(e, a, at.op, at.vs, resOptSetURI, sortOneOptURI(at, attrIndex), sortOneURI(at, attrIndex))
      case at: AttrSetOptByte           => opt(e, a, at.op, at.vs, resOptSetByte, sortOneOptByte(at, attrIndex), sortOneByte(at, attrIndex))
      case at: AttrSetOptShort          => opt(e, a, at.op, at.vs, resOptSetShort, sortOneOptShort(at, attrIndex), sortOneShort(at, attrIndex))
      case at: AttrSetOptChar           => opt(e, a, at.op, at.vs, resOptSetChar, sortOneOptChar(at, attrIndex), sortOneChar(at, attrIndex))
    }
    es
  }

  private def man[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[Set[T]],
    resSet: ResSet[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    find += s"(distinct $v)"
    addCast(resSet.j2sSet)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(attr, e, a, v, attr.op, args, resSet, sortT)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(e, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
  }

  private def tac[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    args: Seq[Set[T]],
    resSet: ResSet[T],
  ): Unit = {
    val v = vv
    attr.filterAttr.fold {
      expr(attr, e, a, v, attr.op, args, resSet, None)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(e, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
  }

  private def expr[T: ClassTag](
    attr: Attr,
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    sets: Seq[Set[T]],
    resSet: ResSet[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    op match {
      case V         => attrV(e, a, v)
      case Eq        => equal(e, a, v, sets, resSet.s2j)
      case Neq       => neq(e, a, v, sets, resSet.s2j)
      case Has       => has(e, a, v, sets, resSet.tpe, resSet.toDatalog)
      case HasNo     => hasNo(e, a, v, sets, resSet.tpe, resSet.toDatalog)
      case NoValue   => noValue(e, a)
      case Fn(kw, n) =>
        if (isRefAttr)
          throw ModelError("Aggregating Sets of ref ids not supported.")
        else
          aggr(attr, e, a, v, kw, n, resSet, sortT)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2(
    e: Var,
    a: Att,
    v: Var,
    op: Op,
    filterAttr: String
  ): Unit = {
    op match {
      case Eq    => equal2(e, a, v, filterAttr)
      case Neq   => neq2(e, a, v, filterAttr)
      case Has   => has2(e, a, v, filterAttr)
      case HasNo => hasNo2(e, a, v, filterAttr)
      case other => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    e: Var,
    a: Att,
    op: Op,
    optSets: Option[Seq[Set[T]]],
    resSetOpt: ResSetOpt[T],
    sortTOpt: Option[(Int, Int => (Row, Row) => Int)],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = vv
    addCast(resSetOpt.j2sOptSet)
    op match {
      case V     => optAttr(e, a, v, resSetOpt)
      case Eq    => optEqual(e, a, v, optSets, resSetOpt.s2j)
      case Neq   => optNeq(e, a, v, optSets, resSetOpt.s2j)
      case Has   => optHas(e, a, v, optSets, resSetOpt.tpe, resSetOpt.toDatalog)
      case HasNo => optHasNo(e, a, v, optSets, resSetOpt.tpe, resSetOpt.toDatalog)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrV(e: Var, a: Att, v: Var): Unit = {
    where += s"[$e $a $v]" -> wClause
  }

  private def optAttr[T](e: Var, a: Att, v: Var, resSetOpt: ResSetOpt[T]): Unit = {
    val (e1, v1, v2, v3) = (e + 1, v + 1, v + 2, v + 3)
    find += s"(distinct $v3)"
    where +=
      s"""[(datomic.api/q
         |          "[:find (pull $e1 [[$a :limit nil]])
         |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
    where += s"[($a $v2) $v3]" -> wClause
    replaceCast(resSetOpt.optAttr2s)
  }


  // eq ------------------------------------------------------------------------

  private def equal[T](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], fromScala: Any => Any
  ): Unit = {
    val (set, v1, v2, e1) = (v + "-set", v + 1, v + 2, e + 1)
    val aa                = a.split("/").last
    aa match {
      case "id" =>
        in += s"[$e ...]"
      case _    =>
        in += s"[$set ...]"
        where += s"[$e $a $v]" -> wClause
    }
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $v1)
         |            :in $$ $e1
         |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
    where += s"[(= $v2 $set)]" -> wClause
    args += sets.map(set => set.map(fromScala).asJava).asJava
  }

  private def optEqual[T](
    e: Var, a: Att, v: Var, optSets: Option[Seq[Set[T]]], fromScala: Any => Any
  ): Unit = {
    optSets.fold[Unit] {
      none(e, a, v)
    } { sets =>
      find += s"(distinct $v)"
      equal(e, a, v, sets, fromScala)
    }
  }


  // neq -----------------------------------------------------------------------

  private def neq[T](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], fromScala: Any => Any
  ): Unit = {
    where += s"[$e $a $v]" -> wClause
    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      val blacklist               = v + "-blacklist"
      val blacklisted             = v + "-blacklisted"
      val (set, set1, v1, v2, e1) = (v + "-set", v + "-set1", v + 1, v + 2, e + 1)

      // Pre-query
      preArgs += sets.map(set => set.map(fromScala).asJava).asJava
      preIn += s"[$set ...]"
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct $v1)
           |            :in $$ $e1
           |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
      preWhere += s"[(into #{} $set) $set1]" -> wClause
      preWhere += s"[(= $v2 $set1)]" -> wClause

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def optNeq[T](
    e: Var, a: Att, v: Var,
    optSets: Option[Seq[Set[T]]],
    fromScala: Any => Any
  ): Unit = {
    find += s"(distinct $v)"
    optSets.fold[Unit] {
      where += s"[$e $a $v]" -> wClause
    } { sets =>
      neq(e, a, v, sets, fromScala)
    }
  }


  // has -----------------------------------------------------------------------

  private def has[T: ClassTag](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    where += s"[$e $a $v]" -> wClause
    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      where += s"(rule$v $e)" -> wClause
      rules ++= mkRules(e, a, v, sets, tpe, toDatalog)
    } else {
      where += s"[(ground nil) $v]" -> wGround
    }
  }

  private def optHas[T: ClassTag](
    e: Var, a: Att, v: Var,
    optSets: Option[Seq[Set[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    optSets.fold[Unit] {
      none(e, a, v)
    } { sets =>
      find += s"(distinct $v)"
      has(e, a, v, sets, tpe, toDatalog)
    }
  }

  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String
  ): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause

    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      // Pre-query
      preWhere += s"(rule$v $e)" -> wClause
      preRules ++= mkRules(e, a, v, sets, tpe, toDatalog)

      // Main query
      val blacklist   = v + "-blacklist"
      val blacklisted = v + "-blacklisted"
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def optHasNo[T](
    e: Var, a: Att, v: Var,
    optSets: Option[Seq[Set[T]]],
    tpe: String,
    toDatalog: T => String
  ): Unit = {
    find += s"(distinct $v)"
    if (optSets.isDefined) {
      hasNo(e, a, v, optSets.get, tpe, toDatalog)
    } else {
      where += s"[$e $a $v]" -> wClause
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(e: Var, a: Att): Unit = {
    where += s"(not [$e $a])" -> wNeqOne
  }


  // aggregation ---------------------------------------------------------------

  private def aggr[T](
    attr: Attr, e: Var, a: Att, v: Var, fn: String, optN: Option[Int], resSet: ResSet[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    checkAggrSet()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= s"(distinct $v)"
    fn match {
      case "count" =>
        noBooleanSetCounts(n)
        find += s"(count $v)"
        widh += e
        addSort(sortOneInt(attr, attrIndex))
        replaceCast(toInt)

      case "countDistinct" =>
        noBooleanSetCounts(n)
        find += s"(count-distinct $v)"
        widh += e
        addSort(sortOneInt(attr, attrIndex))
        replaceCast(toInt)

      case "sum" =>
        widh += e
        find += s"(sum $v)"
        addSort(sortT)
        replaceCast(resSet.j2s)

      case "min" =>
        noBooleanSetAggr(resSet)
        find += s"(min $v)"
        addSort(sortT)
        replaceCast(resSet.j2s)

      case "max" =>
        noBooleanSetAggr(resSet)
        find += s"(max $v)"
        addSort(sortT)
        replaceCast(resSet.j2s)

      case "sample" =>
        noBooleanSetAggr(resSet)
        find += s"(sample 1 $v)"
        addSort(sortSample(attr, attrIndex))
        replaceCast(resSet.jSet2s)

      case "median" =>
        widh += e
        find += s"(median $v)"
        addSort(sortMedian(attr, attrIndex))
        replaceCast(any2double)

      // OBS! Datomic rounds down to nearest whole number
      // when calculating the median for multiple numbers instead of
      // following the semantic described on wikipedia:
      // https://en.wikipedia.org/wiki/Median
      // See also
      // https://forum.datomic.com/t/unexpected-median-rounding/517
      // So we calculate the correct median value manually instead:
      //        widh += e
      //        find += s"(distinct $v)"
      //        val medianConverter: AnyRef => Double = {
      //          (v: AnyRef) =>
      //            getMedian(v.asInstanceOf[jSet[_]].toArray
      //              .map(_.toString.toDouble).toSet)
      //        }
      //        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg" =>
        widh += e
        find += s"(avg $v)"
        addSort(sortOneDouble(attr, attrIndex))
        replaceCast(any2double)

      case "variance" =>
        widh += e
        find += s"(variance $v)"
        addSort(sortOneDouble(attr, attrIndex))
        replaceCast(any2double)

      case "stddev" =>
        widh += e
        find += s"(stddev $v)"
        addSort(sortOneDouble(attr, attrIndex))
        replaceCast(any2double)

      case "mins" =>
        noBooleanSetAggr(resSet)
        find += s"(min $n $v)"
        replaceCast(resSet.vector2set)

      case "maxs" =>
        noBooleanSetAggr(resSet)
        find += s"(max $n $v)"
        replaceCast(resSet.vector2set)

      case "samples" =>
        noBooleanSetAggr(resSet)
        find += s"(sample $n $v)"
        replaceCast(resSet.vector2set)

      case "distinct" =>
        noBooleanSetAggr(resSet)
        val (v1, v2, e1) = (v + 1, v + 2, e + 1)
        find += s"(distinct $v2)"
        //        where += s"[$e $a $v]" -> wClause
        where += s"[$e $a _]" -> wClause
        where +=
          s"""[(datomic.api/q
             |          "[:find (distinct $v1)
             |            :in $$ $e1
             |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
        replaceCast(resSet.set2sets)

      case other => unexpectedKw(other)
    }

    where += s"[$e $a $v]" -> wClause
  }


  // Filter attribute filters --------------------------------------------------

  private def equal2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct ${v}1)
         |            :in $$ ${e}1
         |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause

    val link: (Var, Var) => Unit = (f: Var, w: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${w}1)
           |            :in $$ ${f}1
           |            :where [${f}1 $filterAttr ${w}1]]" $$ $f) [[${w}2]]]""".stripMargin -> wClause
      where += s"[(= ${v}2 ${w}2)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> link)
    } { case (e, a) => link(e, a) }
  }


  private def neq2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (f: Var, w: Var) => {
      val blacklist   = w + "-blacklist"
      val blacklisted = w + "-blacklisted"

      // Pre-query
      preFind = f
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${w}1)
           |            :in $$ ${f}1
           |            :where [${f}1 $filterAttr ${w}1]]" $$ $f) [[${w}2]]]""".stripMargin -> wClause
      preWhere += s"[(= ${v}2 ${w}2)]" -> wClause

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $f) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def has2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (f: Var, w: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${w}1)
           |            :in $$ ${f}1
           |            :where [${f}1 $filterAttr ${w}1]]" $$ $f) [[${w}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${w}2) $w-intersection]" -> wClause
      where += s"[(= ${w}2 $w-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def hasNo2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (f: Var, w: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${w}1)
           |            :in $$ ${f}1
           |            :where [${f}1 $filterAttr ${w}1]]" $$ $f) [[${w}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${w}2) $w-intersection]" -> wClause
      where += s"[(empty? $w-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  // helpers -------------------------------------------------------------------

  private def none[T](e: Var, a: Att, v: Var): Unit = {
    find += s"(pull $e-$v [[$a :limit nil]])"
    where += s"(not [$e $a])" -> wNeqOne
    where += s"[(identity $e) $e-$v]" -> wGround
  }

  private def mkRules[T](
    e: Var, a: Att, v: Var, sets: Seq[Set[T]], tpe: String, toDatalog: T => String
  ): Seq[String] = {
    tpe match {
      case "Float" =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.zipWithIndex.map { case (arg, i) =>
              // Coerce Datomic float values for correct comparison (don't know why this is necessary)
              // See example: https://clojurians-log.clojureverse.org/datomic/2019-10-29
              s"""[$e $a $v$i] [(float $v$i) $v$i-float] [(= $v$i-float (float $arg))]"""
            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
      case "URI"   =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.zipWithIndex.map { case (arg, i) =>
              s"""[(ground (new java.net.URI "$arg")) $v$i-uri] [$e $a $v$i-uri]"""
            }.mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
      case _       =>
        sets.flatMap {
          case set if set.isEmpty => None
          case set                => Some(
            set.map(arg => s"[$e $a ${toDatalog(arg)}]")
              .mkString(s"[(rule$v $e)\n    ", "\n    ", "]")
          )
        }
    }
  }

  private def noBooleanSetAggr[T](res: ResSet[T]): Unit = {
    if (res.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  private def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}