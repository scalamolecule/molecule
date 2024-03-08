package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import scala.reflect.ClassTag

trait ResolveExprArr[Tpl] { self: Model2DatomicQuery[Tpl] with LambdasArr =>

  protected def resolveAttrArrMan(es: List[Var], attr: AttrArrMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val (e, ns, at, a) = (es.last, attr.ns, attr.attr, s":${attr.ns}/${attr.attr}")
    attr match {
      case att: AttrArrManID             => man(attr, e, ns, at, a, att.vs, resArrId)
      case att: AttrArrManString         => man(attr, e, ns, at, a, att.vs, resArrString)
      case att: AttrArrManInt            => man(attr, e, ns, at, a, att.vs, resArrInt)
      case att: AttrArrManLong           => man(attr, e, ns, at, a, att.vs, resArrLong)
      case att: AttrArrManFloat          => man(attr, e, ns, at, a, att.vs, resArrFloat)
      case att: AttrArrManDouble         => man(attr, e, ns, at, a, att.vs, resArrDouble)
      case att: AttrArrManBoolean        => man(attr, e, ns, at, a, att.vs, resArrBoolean)
      case att: AttrArrManBigInt         => man(attr, e, ns, at, a, att.vs, resArrBigInt)
      case att: AttrArrManBigDecimal     => man(attr, e, ns, at, a, att.vs, resArrBigDecimal)
      case att: AttrArrManDate           => man(attr, e, ns, at, a, att.vs, resArrDate)
      case att: AttrArrManDuration       => man(attr, e, ns, at, a, att.vs, resArrDuration)
      case att: AttrArrManInstant        => man(attr, e, ns, at, a, att.vs, resArrInstant)
      case att: AttrArrManLocalDate      => man(attr, e, ns, at, a, att.vs, resArrLocalDate)
      case att: AttrArrManLocalTime      => man(attr, e, ns, at, a, att.vs, resArrLocalTime)
      case att: AttrArrManLocalDateTime  => man(attr, e, ns, at, a, att.vs, resArrLocalDateTime)
      case att: AttrArrManOffsetTime     => man(attr, e, ns, at, a, att.vs, resArrOffsetTime)
      case att: AttrArrManOffsetDateTime => man(attr, e, ns, at, a, att.vs, resArrOffsetDateTime)
      case att: AttrArrManZonedDateTime  => man(attr, e, ns, at, a, att.vs, resArrZonedDateTime)
      case att: AttrArrManUUID           => man(attr, e, ns, at, a, att.vs, resArrUUID)
      case att: AttrArrManURI            => man(attr, e, ns, at, a, att.vs, resArrURI)
      case att: AttrArrManByte           => man(attr, e, ns, at, a, att.vs, resArrByte)
      case att: AttrArrManShort          => man(attr, e, ns, at, a, att.vs, resArrShort)
      case att: AttrArrManChar           => man(attr, e, ns, at, a, att.vs, resArrChar)
    }
    es
  }

  protected def resolveAttrArrTac(es: List[Var], attr: AttrArrTac): List[Var] = {
    val (e, ns, at, a) = (es.last, attr.ns, attr.attr, s":${attr.ns}/${attr.attr}")
    attr match {
      case att: AttrArrTacID             => tac(attr, e, ns, at, a, att.vs, resArrId)
      case att: AttrArrTacString         => tac(attr, e, ns, at, a, att.vs, resArrString)
      case att: AttrArrTacInt            => tac(attr, e, ns, at, a, att.vs, resArrInt)
      case att: AttrArrTacLong           => tac(attr, e, ns, at, a, att.vs, resArrLong)
      case att: AttrArrTacFloat          => tac(attr, e, ns, at, a, att.vs, resArrFloat)
      case att: AttrArrTacDouble         => tac(attr, e, ns, at, a, att.vs, resArrDouble)
      case att: AttrArrTacBoolean        => tac(attr, e, ns, at, a, att.vs, resArrBoolean)
      case att: AttrArrTacBigInt         => tac(attr, e, ns, at, a, att.vs, resArrBigInt)
      case att: AttrArrTacBigDecimal     => tac(attr, e, ns, at, a, att.vs, resArrBigDecimal)
      case att: AttrArrTacDate           => tac(attr, e, ns, at, a, att.vs, resArrDate)
      case att: AttrArrTacDuration       => tac(attr, e, ns, at, a, att.vs, resArrDuration)
      case att: AttrArrTacInstant        => tac(attr, e, ns, at, a, att.vs, resArrInstant)
      case att: AttrArrTacLocalDate      => tac(attr, e, ns, at, a, att.vs, resArrLocalDate)
      case att: AttrArrTacLocalTime      => tac(attr, e, ns, at, a, att.vs, resArrLocalTime)
      case att: AttrArrTacLocalDateTime  => tac(attr, e, ns, at, a, att.vs, resArrLocalDateTime)
      case att: AttrArrTacOffsetTime     => tac(attr, e, ns, at, a, att.vs, resArrOffsetTime)
      case att: AttrArrTacOffsetDateTime => tac(attr, e, ns, at, a, att.vs, resArrOffsetDateTime)
      case att: AttrArrTacZonedDateTime  => tac(attr, e, ns, at, a, att.vs, resArrZonedDateTime)
      case att: AttrArrTacUUID           => tac(attr, e, ns, at, a, att.vs, resArrUUID)
      case att: AttrArrTacURI            => tac(attr, e, ns, at, a, att.vs, resArrURI)
      case att: AttrArrTacByte           => tac(attr, e, ns, at, a, att.vs, resArrByte)
      case att: AttrArrTacShort          => tac(attr, e, ns, at, a, att.vs, resArrShort)
      case att: AttrArrTacChar           => tac(attr, e, ns, at, a, att.vs, resArrChar)
    }
    es
  }

  protected def resolveAttrArrOpt(es: List[Var], attr: AttrArrOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val (e, ns, at, a) = (es.last, attr.ns, attr.attr, s":${attr.ns}/${attr.attr}")
    attr match {
      case att: AttrArrOptID             => opt(e, ns, at, a, att.op, att.vs, resOptArrId)
      case att: AttrArrOptString         => opt(e, ns, at, a, att.op, att.vs, resOptArrString)
      case att: AttrArrOptInt            => opt(e, ns, at, a, att.op, att.vs, resOptArrInt)
      case att: AttrArrOptLong           => opt(e, ns, at, a, att.op, att.vs, resOptArrLong)
      case att: AttrArrOptFloat          => opt(e, ns, at, a, att.op, att.vs, resOptArrFloat)
      case att: AttrArrOptDouble         => opt(e, ns, at, a, att.op, att.vs, resOptArrDouble)
      case att: AttrArrOptBoolean        => opt(e, ns, at, a, att.op, att.vs, resOptArrBoolean)
      case att: AttrArrOptBigInt         => opt(e, ns, at, a, att.op, att.vs, resOptArrBigInt)
      case att: AttrArrOptBigDecimal     => opt(e, ns, at, a, att.op, att.vs, resOptArrBigDecimal)
      case att: AttrArrOptDate           => opt(e, ns, at, a, att.op, att.vs, resOptArrDate)
      case att: AttrArrOptDuration       => opt(e, ns, at, a, att.op, att.vs, resOptArrDuration)
      case att: AttrArrOptInstant        => opt(e, ns, at, a, att.op, att.vs, resOptArrInstant)
      case att: AttrArrOptLocalDate      => opt(e, ns, at, a, att.op, att.vs, resOptArrLocalDate)
      case att: AttrArrOptLocalTime      => opt(e, ns, at, a, att.op, att.vs, resOptArrLocalTime)
      case att: AttrArrOptLocalDateTime  => opt(e, ns, at, a, att.op, att.vs, resOptArrLocalDateTime)
      case att: AttrArrOptOffsetTime     => opt(e, ns, at, a, att.op, att.vs, resOptArrOffsetTime)
      case att: AttrArrOptOffsetDateTime => opt(e, ns, at, a, att.op, att.vs, resOptArrOffsetDateTime)
      case att: AttrArrOptZonedDateTime  => opt(e, ns, at, a, att.op, att.vs, resOptArrZonedDateTime)
      case att: AttrArrOptUUID           => opt(e, ns, at, a, att.op, att.vs, resOptArrUUID)
      case att: AttrArrOptURI            => opt(e, ns, at, a, att.op, att.vs, resOptArrURI)
      case att: AttrArrOptByte           => opt(e, ns, at, a, att.op, att.vs, resOptArrByte)
      case att: AttrArrOptShort          => opt(e, ns, at, a, att.op, att.vs, resOptArrShort)
      case att: AttrArrOptChar           => opt(e, ns, at, a, att.op, att.vs, resOptArrChar)
    }
    es
  }


  private def man[T: ClassTag](
    attr: Attr,
    e: Var,
    ns: String,
    at: String,
    a: Att,
    args: Seq[Array[T]],
    res: ResArr[T],
  ): Unit = {
    val v = vv
    find += s"(distinct ${v}_$at)"
    addCast(res.pairs2array)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-set filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(e, ns, at, a, v, attr.op, args, res)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(e, ns, at, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def tac[T: ClassTag](
    attr: Attr,
    e: Var,
    ns: String,
    at: String,
    a: Att,
    args: Seq[Array[T]],
    res: ResArr[T],
  ): Unit = {
    val v = vv
    attr.filterAttr.fold {
      expr(e, ns, at, a, v, attr.op, args, res)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(e, ns, at, a, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}")
    }
    refConfirmed = true
  }

  private def expr[T: ClassTag](
    e: Var,
    ns: String,
    at: String,
    a: Att,
    v: Var,
    op: Op,
    sets: Seq[Array[T]],
    res: ResArr[T],
  ): Unit = {
    op match {
      case V         => attr(e, ns, at, a, v)
      case Eq        => equal(e, a, v, sets, res.s2j)
      case Neq       => neq(e, a, v, sets, res.s2j)
      case Has       => has(e, a, v, sets, res.tpe, res.toDatalog)
      case HasNo     => hasNo(e, a, v, sets, res.tpe, res.toDatalog)
      case NoValue   => noValue(e, a)
      case Fn(kw, n) =>
        if (isRefAttr)
          throw ModelError("Aggregating Sets of ref ids not supported.")
        else
          aggr(e, a, v, kw, n, res)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2(
    e: Var,
    ns: String,
    at: String,
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
    ns: String,
    at: String,
    a: Att,
    op: Op,
    optSets: Option[Seq[Array[T]]],
    resOpt: ResArrOpt[T],
  ): Unit = {
    val v = vv
    addCast(resOpt.j2s)
    op match {
      case V     => optAttr(e, a, v, resOpt)
      case Eq    => optEqual(e, a, v, optSets, resOpt, resOpt.s2j)
      case Neq   => optNeq(e, a, v, optSets, resOpt.s2j)
      case Has   => optHas(e, a, v, optSets, resOpt.tpe, resOpt, resOpt.toDatalog)
      case HasNo => optHasNo(e, a, v, optSets, resOpt.tpe, resOpt.toDatalog)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attr(e: Var, ns: String, at: String, a: Att, r: Var): Unit = {
    where += s"[$e $a $r]" -> wClause
    where += s"[$r $ns.$at/i_ ${r}_i]" -> wClause
    where += s"[$r $ns.$at/$at ${r}_v]" -> wClause
    where += s"[(vector ${r}_i ${r}_v) ${r}_$at]" -> wClause
  }

  private def optAttr[T](e: Var, a: Att, v: Var, resOpt: ResArrOpt[T]): Unit = {
    if (refConfirmed) {
      val (e1, v1, v2, v3) = (e + 1, v + 1, v + 2, v + 3)
      find += s"(distinct $v3)"
      where +=
        s"""[(datomic.api/q
           |          "[:find (pull $e1 [[$a :limit nil]])
           |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
      where += s"[($a $v2) $v3]" -> wClause

    } else {
      val List(e0, card, refAttr, refId) = varPath.takeRight(4)
      val refDatom                       = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      val e                        = varPath.last
      val (e1, v1, v2, v3, v4, v5) = (e0 + 1, v + 1, v + 2, v + 3, v + 4, v + 5)

      if (card == "one") {
        find += s"(distinct $v4)"
        where +=
          s"""[(datomic.api/q
             |          "[:find (pull $e1 [{$refAttr [$a]}] :limit nil)
             |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
        where += s"[(if (nil? $v1) {$refAttr {$a []}} $v1) $v2]" -> wClause
        where += s"[($refAttr $v2) $v3]" -> wClause
        where += s"[($a $v3) $v4]" -> wClause

      } else {
        find += s"(distinct $v5)"
        where +=
          s"""[(datomic.api/q
             |          "[:find (pull $e1 [{$refAttr [$a]}] :limit nil)
             |            :in $$ $e1]" $$ $e) [[$v1]]]""".stripMargin -> wClause
        where += s"[(if (nil? $v1) {$refAttr [{$a []}]} $v1) $v2]" -> wClause
        where += s"[($refAttr $v2) $v3]" -> wClause
        where += s"[(first $v3) $v4]" -> wClause
        where += s"[($a $v4) $v5]" -> wClause
      }
    }
    replaceCast(resOpt.optAttr2s)
  }


  // eq ------------------------------------------------------------------------

  private def equal[T](
    e: Var, a: Att, v: Var, sets: Seq[Array[T]], fromScala: Any => Any
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
    args += sets.map(set => set.map(fromScala)).asJava
  }

  private def optEqual[T](
    e: Var, a: Att, v: Var,
    optSets: Option[Seq[Array[T]]],
    resOpt: ResArrOpt[T],
    fromScala: Any => Any
  ): Unit = {
    optSets.fold[Unit] {
      none(e, a, v, resOpt)
    } { sets =>
      find += s"(distinct $v)"
      equal(e, a, v, sets, fromScala)
    }
  }


  // neq -----------------------------------------------------------------------

  private def neq[T](
    e: Var, a: Att, v: Var, sets: Seq[Array[T]], fromScala: Any => Any
  ): Unit = {
    where += s"[$e $a $v]" -> wClause
    if (sets.nonEmpty && sets.flatten.nonEmpty) {
      val blacklist               = v + "-blacklist"
      val blacklisted             = v + "-blacklisted"
      val (set, set1, v1, v2, e1) = (v + "-set", v + "-set1", v + 1, v + 2, e + 1)

      // Pre-query
      preIn += s"[$set ...]"
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct $v1)
           |            :in $$ $e1
           |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
      preWhere += s"[(into #{} $set) $set1]" -> wClause
      preWhere += s"[(= $v2 $set1)]" -> wClause
      preArgs += sets.map(set => set.map(fromScala)).asJava

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
    }
  }

  private def optNeq[T](
    e: Var, a: Att, v: Var,
    optSets: Option[Seq[Array[T]]],
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
    e: Var, a: Att, v: Var, sets: Seq[Array[T]], tpe: String, toDatalog: T => String
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
    optSets: Option[Seq[Array[T]]],
    tpe: String,
    resOpt: ResArrOpt[T],
    toDatalog: T => String
  ): Unit = {
    optSets.fold[Unit] {
      none(e, a, v, resOpt)
    } { sets =>
      find += s"(distinct $v)"
      has(e, a, v, sets, tpe, toDatalog)
    }
  }


  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    e: Var, a: Att, v: Var, sets: Seq[Array[T]], tpe: String, toDatalog: T => String
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
    optSets: Option[Seq[Array[T]]],
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
    if (refConfirmed) {
      where += s"(not [$e $a])" -> wNeqOne
    } else {
      val List(e0, _, refAttr, refId) = varPath.takeRight(4)
      val refDatom                    = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      where += s"(not [$e0 $refAttr])" -> wNeqOne
    }
  }


  // aggregation ---------------------------------------------------------------

  private def aggr[T](
    e: Var, a: Att, v: Var, fn: String, optN: Option[Int], res: ResArr[T]
  ): Unit = {
    checkAggrSet()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= s"(distinct $v)"
    fn match {
      case "distinct" =>
        noBooleanSetAggr(res)
        val (v1, v2, e1) = (v + 1, v + 2, e + 1)
        find += s"(distinct $v2)"
        where += s"[$e $a $v]" -> wClause
        where +=
          s"""[(datomic.api/q
             |          "[:find (distinct $v1)
             |            :in $$ $e1
             |            :where [$e1 $a $v1]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
        replaceCast(res.set2sets)

      case "min" =>
        noBooleanSetAggr(res)
        find += s"(min 1 $v)"
        replaceCast(res.vector2set)

      case "mins" =>
        noBooleanSetAggr(res)
        find += s"(min $n $v)"
        replaceCast(res.vector2set)

      case "max" =>
        noBooleanSetAggr(res)
        find += s"(max 1 $v)"
        replaceCast(res.vector2set)

      case "maxs" =>
        noBooleanSetAggr(res)
        find += s"(max $n $v)"
        replaceCast(res.vector2set)

      case "sample" =>
        noBooleanSetAggr(res)
        find += s"(sample 1 $v)"
        replaceCast(res.vector2set)

      case "samples" =>
        noBooleanSetAggr(res)
        find += s"(sample $n $v)"
        replaceCast(res.vector2set)

      case "count" =>
        noBooleanSetCounts(n)
        find += s"(count $v)"
        widh += e
        replaceCast(toInt)

      case "countDistinct" =>
        noBooleanSetCounts(n)
        find += s"(count-distinct $v)"
        widh += e
        replaceCast(toInt)

      case "sum" =>
        widh += e
        find += s"(sum $v)"
//        replaceCast(res.j2sSet)
        ???

      case "median" =>
        widh += e
        find += s"(median $v)"
        // Force whole number to cast as double according to aggregate type for median/avg/variance/stddev)
        replaceCast((v: AnyRef) => v.toString.toDouble.asInstanceOf[AnyRef])

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
      //            getMedian(v.asInstanceOf[jArray[_]].toArray
      //              .map(_.toString.toDouble).toSet)
      //        }
      //        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg" =>
        widh += e
        find += s"(avg $v)"
        replaceCast(any2double)

      case "variance" =>
        widh += e
        find += s"(variance $v)"
        replaceCast(any2double)

      case "stddev" =>
        widh += e
        find += s"(stddev $v)"
        replaceCast(any2double)

      case other => unexpectedKw(other)
    }

    where += s"[$e $a $v]" -> wClause
  }


  // Filter attribute filters --------------------------------------------------

  private def equal2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    preFind = e
    where += s"[$e $a $v]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct ${v}1)
         |            :in $$ ${e}1
         |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
    val link: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(= ${v}2 ${v1}2)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> link)
    } { case (e, a) => link(e, a) }
  }


  private def neq2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      val blacklist   = v1 + "-blacklist"
      val blacklisted = v1 + "-blacklisted"

      // Pre-query
      preFind = e1
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      preWhere += s"[(= ${v}2 ${v1}2)]" -> wClause

      // Main query
      inPost += blacklist
      wherePost += s"[(contains? $blacklist $e1) $blacklisted]" -> wClause
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
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
      where += s"[(= ${v1}2 $v1-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def hasNo2(e: Var, a: Att, v: Var, filterAttr: String): Unit = {
    // Common for pre-query and main query
    where += s"[$e $a $v]" -> wClause
    val process: (Var, Var) => Unit = (e1: Var, v1: Var) => {
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v}1)
           |            :in $$ ${e}1
           |            :where [${e}1 $a ${v}1]]" $$ $e) [[${v}2]]]""".stripMargin -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct ${v1}1)
           |            :in $$ ${e1}1
           |            :where [${e1}1 $filterAttr ${v1}1]]" $$ $e1) [[${v1}2]]]""".stripMargin -> wClause
      where += s"[(clojure.set/intersection ${v}2 ${v1}2) $v1-intersection]" -> wClause
      where += s"[(empty? $v1-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  // helpers -------------------------------------------------------------------

  private def none[T](e: Var, a: Att, v: Var, resOpt: ResArrOpt[T]): Unit = {
    if (refConfirmed) {
      find += s"(pull $e-$v [[$a :limit nil]])"
      where += s"[(identity $e) $e-$v]" -> wGround
      where += s"(not [$e $a])" -> wNeqOne

    } else {
      val List(e0, _, refAttr, refId) = varPath.takeRight(4)
      val refDatom                    = s"[$e0 $refAttr $refId]"
      if (where.last == refDatom -> wClause) {
        // cancel previous ref Datom since we will pull it instead
        where.remove(where.size - 1)
        varPath = varPath.dropRight(3)
      }
      find += s"$v"
      where += s"(not [$e0 $refAttr])" -> wNeqOne
      where += s"[(ground #{[]}) $v]" -> wNeqOne
      replaceCast(resOpt.optAttr2s)
    }
  }

  private def mkRules[T](
    e: Var, a: Att, v: Var, sets: Seq[Array[T]], tpe: String, toDatalog: T => String
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

  private def noBooleanSetAggr[T](res: ResArr[T]): Unit = {
    if (res.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  private def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}