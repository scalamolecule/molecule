package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions
import scala.reflect.ClassTag

trait ResolveExprMap[Tpl] extends JavaConversions { self: Model2DatomicQuery[Tpl] with LambdasMap =>

  protected def resolveAttrMapMan(es: List[Var], attr: AttrMapMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrMapManID             => man(attr, e, at.vs, resMapId, sortOneID(at, attrIndex))
      case at: AttrMapManString         => man(attr, e, at.vs, resMapString, sortOneString(at, attrIndex))
      case at: AttrMapManInt            => man(attr, e, at.vs, resMapInt, intSorter(at, attrIndex))
      case at: AttrMapManLong           => man(attr, e, at.vs, resMapLong, sortOneLong(at, attrIndex))
      case at: AttrMapManFloat          => man(attr, e, at.vs, resMapFloat, floatSorter(at, attrIndex))
      case at: AttrMapManDouble         => man(attr, e, at.vs, resMapDouble, sortOneDouble(at, attrIndex))
      case at: AttrMapManBoolean        => man(attr, e, at.vs, resMapBoolean, sortOneBoolean(at, attrIndex))
      case at: AttrMapManBigInt         => man(attr, e, at.vs, resMapBigInt, bigIntSorter(at, attrIndex))
      case at: AttrMapManBigDecimal     => man(attr, e, at.vs, resMapBigDecimal, sortOneBigDecimal(at, attrIndex))
      case at: AttrMapManDate           => man(attr, e, at.vs, resMapDate, sortOneDate(at, attrIndex))
      case at: AttrMapManDuration       => man(attr, e, at.vs, resMapDuration, sortOneDuration(at, attrIndex))
      case at: AttrMapManInstant        => man(attr, e, at.vs, resMapInstant, sortOneInstant(at, attrIndex))
      case at: AttrMapManLocalDate      => man(attr, e, at.vs, resMapLocalDate, sortOneLocalDate(at, attrIndex))
      case at: AttrMapManLocalTime      => man(attr, e, at.vs, resMapLocalTime, sortOneLocalTime(at, attrIndex))
      case at: AttrMapManLocalDateTime  => man(attr, e, at.vs, resMapLocalDateTime, sortOneLocalDateTime(at, attrIndex))
      case at: AttrMapManOffsetTime     => man(attr, e, at.vs, resMapOffsetTime, sortOneOffsetTime(at, attrIndex))
      case at: AttrMapManOffsetDateTime => man(attr, e, at.vs, resMapOffsetDateTime, sortOneOffsetDateTime(at, attrIndex))
      case at: AttrMapManZonedDateTime  => man(attr, e, at.vs, resMapZonedDateTime, sortOneZonedDateTime(at, attrIndex))
      case at: AttrMapManUUID           => man(attr, e, at.vs, resMapUUID, sortOneUUID(at, attrIndex))
      case at: AttrMapManURI            => man(attr, e, at.vs, resMapURI, sortOneURI(at, attrIndex))
      case at: AttrMapManByte           => man(attr, e, at.vs, resMapByte, sortOneByte(at, attrIndex))
      case at: AttrMapManShort          => man(attr, e, at.vs, resMapShort, shortSorter(at, attrIndex))
      case at: AttrMapManChar           => man(attr, e, at.vs, resMapChar, sortOneChar(at, attrIndex))
    }
    es
  }

  protected def resolveAttrMapTac(es: List[Var], attr: AttrMapTac): List[Var] = {
    val e = es.last
    attr match {
      case at: AttrMapTacID             => tac(attr, e, at.vs, resMapId)
      case at: AttrMapTacString         => tac(attr, e, at.vs, resMapString)
      case at: AttrMapTacInt            => tac(attr, e, at.vs, resMapInt)
      case at: AttrMapTacLong           => tac(attr, e, at.vs, resMapLong)
      case at: AttrMapTacFloat          => tac(attr, e, at.vs, resMapFloat)
      case at: AttrMapTacDouble         => tac(attr, e, at.vs, resMapDouble)
      case at: AttrMapTacBoolean        => tac(attr, e, at.vs, resMapBoolean)
      case at: AttrMapTacBigInt         => tac(attr, e, at.vs, resMapBigInt)
      case at: AttrMapTacBigDecimal     => tac(attr, e, at.vs, resMapBigDecimal)
      case at: AttrMapTacDate           => tac(attr, e, at.vs, resMapDate)
      case at: AttrMapTacDuration       => tac(attr, e, at.vs, resMapDuration)
      case at: AttrMapTacInstant        => tac(attr, e, at.vs, resMapInstant)
      case at: AttrMapTacLocalDate      => tac(attr, e, at.vs, resMapLocalDate)
      case at: AttrMapTacLocalTime      => tac(attr, e, at.vs, resMapLocalTime)
      case at: AttrMapTacLocalDateTime  => tac(attr, e, at.vs, resMapLocalDateTime)
      case at: AttrMapTacOffsetTime     => tac(attr, e, at.vs, resMapOffsetTime)
      case at: AttrMapTacOffsetDateTime => tac(attr, e, at.vs, resMapOffsetDateTime)
      case at: AttrMapTacZonedDateTime  => tac(attr, e, at.vs, resMapZonedDateTime)
      case at: AttrMapTacUUID           => tac(attr, e, at.vs, resMapUUID)
      case at: AttrMapTacURI            => tac(attr, e, at.vs, resMapURI)
      case at: AttrMapTacByte           => tac(attr, e, at.vs, resMapByte)
      case at: AttrMapTacShort          => tac(attr, e, at.vs, resMapShort)
      case at: AttrMapTacChar           => tac(attr, e, at.vs, resMapChar)
    }
    es
  }

  protected def resolveAttrMapOpt(es: List[Var], attr: AttrMapOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true // to avoid redundant None's
    val e = es.last
    attr match {
      case at: AttrMapOptID             => opt(attr, e, at.op, at.vs, resOptMapId, resMapId)
      case at: AttrMapOptString         => opt(attr, e, at.op, at.vs, resOptMapString, resMapString)
      case at: AttrMapOptInt            => opt(attr, e, at.op, at.vs, resOptMapInt, resMapInt)
      case at: AttrMapOptLong           => opt(attr, e, at.op, at.vs, resOptMapLong, resMapLong)
      case at: AttrMapOptFloat          => opt(attr, e, at.op, at.vs, resOptMapFloat, resMapFloat)
      case at: AttrMapOptDouble         => opt(attr, e, at.op, at.vs, resOptMapDouble, resMapDouble)
      case at: AttrMapOptBoolean        => opt(attr, e, at.op, at.vs, resOptMapBoolean, resMapBoolean)
      case at: AttrMapOptBigInt         => opt(attr, e, at.op, at.vs, resOptMapBigInt, resMapBigInt)
      case at: AttrMapOptBigDecimal     => opt(attr, e, at.op, at.vs, resOptMapBigDecimal, resMapBigDecimal)
      case at: AttrMapOptDate           => opt(attr, e, at.op, at.vs, resOptMapDate, resMapDate)
      case at: AttrMapOptDuration       => opt(attr, e, at.op, at.vs, resOptMapDuration, resMapDuration)
      case at: AttrMapOptInstant        => opt(attr, e, at.op, at.vs, resOptMapInstant, resMapInstant)
      case at: AttrMapOptLocalDate      => opt(attr, e, at.op, at.vs, resOptMapLocalDate, resMapLocalDate)
      case at: AttrMapOptLocalTime      => opt(attr, e, at.op, at.vs, resOptMapLocalTime, resMapLocalTime)
      case at: AttrMapOptLocalDateTime  => opt(attr, e, at.op, at.vs, resOptMapLocalDateTime, resMapLocalDateTime)
      case at: AttrMapOptOffsetTime     => opt(attr, e, at.op, at.vs, resOptMapOffsetTime, resMapOffsetTime)
      case at: AttrMapOptOffsetDateTime => opt(attr, e, at.op, at.vs, resOptMapOffsetDateTime, resMapOffsetDateTime)
      case at: AttrMapOptZonedDateTime  => opt(attr, e, at.op, at.vs, resOptMapZonedDateTime, resMapZonedDateTime)
      case at: AttrMapOptUUID           => opt(attr, e, at.op, at.vs, resOptMapUUID, resMapUUID)
      case at: AttrMapOptURI            => opt(attr, e, at.op, at.vs, resOptMapURI, resMapURI)
      case at: AttrMapOptByte           => opt(attr, e, at.op, at.vs, resOptMapByte, resMapByte)
      case at: AttrMapOptShort          => opt(attr, e, at.op, at.vs, resOptMapShort, resMapShort)
      case at: AttrMapOptChar           => opt(attr, e, at.op, at.vs, resOptMapChar, resMapChar)
    }
    es
  }


  private def vars(attr: Attr, v: String) = {
    val (ns, at) = (attr.ns, attr.attr)
    (
      s":$ns/$at", s":$ns.$at/k_", s":$ns.$at/v_",
      v + "-k", v + "-v",
      v + 1, v + 2, v + 3, v + 4, v + 5, v + 6,
      v + "-pair"
    )
  }
  private def nsAttr(attr: Attr): String = s":${attr.ns}/${attr.attr}"

  private def man[T: ClassTag](
    attr: Attr, e: Var,
    map: Map[String, T],
    resMap: ResMap[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val v = vv
//    find += s"${v}3"
    find += v
    addCast(resMap.j2sMap)
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      val pathAttr = varPath :+ attr.cleanAttr
      if (filterAttrVars.contains(pathAttr) && attr.op != V) {
        // Runtime check needed since we can't type infer it
        throw ModelError(s"Cardinality-map filter attributes not allowed to " +
          s"do additional filtering. Found:\n  " + attr)
      }
      expr(false, attr, e, v, attr.op, map, resMap, sortT)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}", resMap)
    }
  }

  private def tac[T: ClassTag](
    attr: Attr, e: Var,
    map: Map[String, T],
    resMap: ResMap[T],
  ): Unit = {
    val v = vv
    val a = nsAttr(attr)
    attr.filterAttr.fold {
      expr(true, attr, e, v, attr.op, map, resMap, None)
      filterAttrVars1 = filterAttrVars1 + (a -> (e, v))
      filterAttrVars2.get(a).foreach(_(e, v))
    } { case (dir, filterPath, filterAttr) =>
      expr2(attr, e, v, attr.op, s":${filterAttr.ns}/${filterAttr.attr}", resMap)
    }
  }

  private def expr[T: ClassTag](
    tacit: Boolean,
    attr: Attr, e: Var, v: Var, op: Op,
    map: Map[String, T],
    resMap: ResMap[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    op match {
      case V         => attrV(tacit, attr, e, v)
      case Eq        => equal(attr, e, v, map, resMap)
      case Neq       => neq(tacit, attr, e, v, map, resMap.s2j)
      case Has       => has(tacit, attr, e, v, map, resMap)
      case HasNo     => hasNo(attr, e, v, map, resMap)
      case NoValue   => noValue(attr, e)
      case Fn(kw, n) =>
        if (isRefAttr)
          throw ModelError("Aggregating Sets of ref ids not supported.")
        else
          aggr(attr, e, v, kw, n, resMap, sortT)
      case other     => unexpectedOp(other)
    }
  }

  private def expr2[T](
    attr: Attr, e: Var, v: Var, op: Op, filterAttr: String, resMap: ResMap[T],
  ): Unit = {
    op match {
      case Eq    => equal2(attr, e, v, filterAttr)
      case Neq   => neq2(attr, e, v, filterAttr)
      case Has   => has2(attr, e, v, filterAttr, resMap)
      case HasNo => hasNo2(attr, e, v, filterAttr, resMap)
      case other => unexpectedOp(other)
    }
  }

  private def opt[T: ClassTag](
    attr: Attr, e: Var, op: Op,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
    resMap: ResMap[T],
  ): Unit = {
    val v = vv
    addCast(resMapOpt.j2sOptList)
    op match {
      case V     => optAttr(attr, e, v)
      case Eq    => optEqual(attr, e, v, optMap, resMapOpt, resMap)
      case Neq   => optNeq(attr, e, v, optMap, resMapOpt, resMap)
      case Has   => optHas(attr, e, v, optMap, resMap)
      case HasNo => optHasNo(attr, e, v, optMap, resMap, resMapOpt)
      case other => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrV(
    tacit: Boolean, attr: Attr, e: Var, v: Var
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $pair)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ak $k_]
         |                   [$v $av $v_]
         |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v]]]""".stripMargin -> wClause
//    where += s"[(sort-by first $v1) $v2]" -> wClause
//    where += s"[(map second $v2) $v3]" -> wClause
    if (tacit) {
      widh += v // Don't coalesce List to Set
    }
  }

  private def optAttr(
    attr: Attr, e: Var, v: Var
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += v6
    where +=
      s"""[(datomic.api/q
         |          "[:find (pull $e [{($a :limit nil) [$ak $av]}])
         |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
    where += s"[($a $v2) $v3]" -> wClause
    where += s"[(map vals $v3) $v4]" -> wClause
    where += s"[(sort-by first $v4) $v5]" -> wClause
    where += s"[(map second $v5) $v6]" -> wClause
  }


  // eq ------------------------------------------------------------------------

  private def equal[T](
    attr: Attr, e: Var, v: Var,
    map: Map[String, T],
    resMap: ResMap[T],
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    args += map.map(resMap.s2j).asJava
    in += s"[$v4 ...]"
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $pair)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ak $k_]
         |                   [$v $av $v_]
         |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(sort-by first $v1) $v2]" -> wClause
    where += s"[(map second $v2) $v3]" -> wClause
    where += s"[(= $v3 $v4)]" -> wClause
  }

  private def optEqual[T](
    attr: Attr, e: Var, v: Var,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
    resMap: ResMap[T],
  ): Unit = {
    optMap.fold[Unit] {
      none(attr, e, v)
    } { maps =>
      find += v + 3
      equal(attr, e, v, maps, resMap)
      replaceCast(resMapOpt.j2sOptList)
    }
  }


  // neq -----------------------------------------------------------------------

  private def neq[T](
    tacit: Boolean,
    attr: Attr, e: Var, v: Var,
    map: Map[String, T],
    s2j: Any => Any,
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (map.nonEmpty) {
      val blacklist   = v + "-blacklist"
      val blacklisted = v + "-blacklisted"

      // In both pre- and main query
      where += s"[$e $a _]" -> wClause

      // Pre-query
      preArgs += map.map(s2j).asJava
      preIn += s"[$v4 ...]"
      // Find blacklisted entities that match input Maps
      preWhere +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ak $k_]
           |                   [$v $av $v_]
           |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      preWhere += s"[(sort-by first $v1) $v2]" -> wClause
      preWhere += s"[(map second $v2) $v3]" -> wClause
      preWhere += s"[(= $v3 $v4)]" -> wClause

      // Main query
      inPost += blacklist
      // filter out blacklisted entities
      wherePost += s"[(contains? $blacklist $firstId) $blacklisted]" -> wClause
      wherePost += s"[(not $blacklisted)]" -> wClause
      // now get whitelisted list
      wherePost +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ak $k_]
           |                   [$v $av $v_]
           |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      wherePost += s"[(sort-by first $v1) $v2]" -> wClause
      wherePost += s"[(map second $v2) $v3]" -> wClause
    } else {
      // Get all
      attrV(false, attr, e, v)
    }
    if (tacit) {
      widh += v3 // Don't coalesce List to Set
    }
  }

  private def optNeq[T](
    attr: Attr, e: Var, v: Var,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
    resMap: ResMap[T],
  ): Unit = {
    optMap match {
      case Some(maps) if maps.nonEmpty =>
        find += v + 3
        neq(false, attr, e, v, maps, resMap.s2j)
        replaceCast(resMapOpt.j2sOptList)

      case _ =>
        // Ignore empty maps
        optWithoutNone(attr, e, v)
    }
  }


  // has -----------------------------------------------------------------------

  private def has[T: ClassTag](
    tacit: Boolean,
    attr: Attr, e: Var, v: Var,
    map: Map[String, T],
    resMap: ResMap[T],
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (map.nonEmpty) {
      args += map.map(resMap.s2j).asJava
      in += v5
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ak $k_]
           |                   [$v $av $v_]
           |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      where += s"[(sort-by first $v1) $v2]" -> wClause
      where += s"[(map second $v2) $v3]" -> wClause
      resMap.tpe match {
        case "Boolean" =>
          // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
          where += s"[(map str $v3) $v3-list]" -> wClause
          where += s"[(set $v3-list) $v3-set]" -> wClause
          where += s"[(map str $v5) $v5-list]" -> wClause
          where += s"[(set $v5-list) $v5-set]" -> wClause
          where += s"[(some $v3-set $v5-set)]" -> wClause

        case _ =>
          where += s"[(set $v3) $v4]" -> wClause
          where += s"[(some $v4 $v5)]" -> wClause
      }

    } else {
      // Match nothing
      where += s"[$e $a $v3]" -> wClause
      where += s"[(ground nil) $v3]" -> wGround
    }
    if (tacit) {
      widh += v3 // Don't coalesce List to Set
    }
  }

  private def optHas[T: ClassTag](
    attr: Attr, e: Var, v: Var,
    optMap: Option[Map[String, T]],
    resMap: ResMap[T],
  ): Unit = {
    optMap.fold[Unit] {
      none(attr, e, v)
    } { maps =>
      find += v + 3
      has(false, attr, e, v, maps, resMap)
    }
  }


  // hasNo ---------------------------------------------------------------------

  private def hasNo[T](
    attr: Attr, e: Var, v: Var,
    map: Map[String, T],
    resMap: ResMap[T],
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (map.nonEmpty) {
      find += v3
      args += map.map(resMap.s2j).asJava
      in += v5
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ak $k_]
           |                   [$v $av $v_]
           |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
      where += s"[(sort-by first $v1) $v2]" -> wClause
      where += s"[(map second $v2) $v3]" -> wClause
      resMap.tpe match {
        case "Boolean" =>
          // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
          where += s"[(map str $v3) $v3-list]" -> wClause
          where += s"[(set $v3-list) $v3-set]" -> wClause
          where += s"[(map str $v5) $v5-list]" -> wClause
          where += s"[(set $v5-list) $v5-set]" -> wClause
          where += s"[(not-any? $v3-set $v5-set)]" -> wClause

        case _ =>
          where += s"[(set $v3) $v4]" -> wClause
          where += s"[(not-any? $v4 $v5)]" -> wClause
      }

    } else {
      // Get all
      attrV(false, attr, e, v)
    }
  }

  private def optHasNo[T](
    attr: Attr, e: Var, v: Var,
    optMap: Option[Map[String, T]],
    resMap: ResMap[T],
    resMapOpt: ResMapOpt[T],
  ): Unit = {
    optMap match {
      case Some(maps) if maps.nonEmpty =>
        hasNo(attr, e, v, optMap.get, resMap)
        replaceCast(resMapOpt.j2sOptList)

      case _ => optWithoutNone(attr, e, v)
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(attr: Attr, e: Var): Unit = {
    val a = nsAttr(attr)
    where += s"(not [$e $a])" -> wNeqOne
  }


  // aggregation ---------------------------------------------------------------

  private def aggr[T](
    attr: Attr, e: Var, v: Var, fn: String, optN: Option[Int], resMap: ResMap[T],
    sortT: Option[(Int, Int => (Row, Row) => Int)]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    checkAggrSet()
    lazy val n = optN.getOrElse(0)
    // Replace find/casting with aggregate function/cast
    find -= v3
    fn match {
      case "count" =>
        noBooleanSetCounts(n)
        find += s"(count $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneInt(attr, attrIndex))
        replaceCast(toInt)

      case "countDistinct" =>
        noBooleanSetCounts(n)
        find += s"(count-distinct $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneInt(attr, attrIndex))
        replaceCast(toInt)

      case "sum" =>
        find += s"(sum $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortT)
        replaceCast(resMap.j2s)

      case "min" =>
        noBooleanSetAggr(resMap)
        find += s"(min $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortT)
        replaceCast(resMap.j2s)

      case "max" =>
        noBooleanSetAggr(resMap)
        find += s"(max $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortT)
        replaceCast(resMap.j2s)

      case "sample" =>
        noBooleanSetAggr(resMap)
        find += s"(sample 1 $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortSample(attr, attrIndex))
        replaceCast(resMap.jSet2s)

      case "median" =>
        find += s"(median $v_)"
        widh += e
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
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
      //            getMedian(v.asInstanceOf[jArray[_]].toArray
      //              .map(_.toString.toDouble).toSet)
      //        }
      //        replaceCast(medianConverter.asInstanceOf[AnyRef => AnyRef])

      case "avg" =>
        find += s"(avg $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneDouble(attr, attrIndex))
        replaceCast(any2double)

      case "variance" =>
        find += s"(variance $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneDouble(attr, attrIndex))
        replaceCast(any2double)

      case "stddev" =>
        find += s"(stddev $v_)"
        widh += v
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        addSort(sortOneDouble(attr, attrIndex))
        replaceCast(any2double)

      case "mins" =>
        noBooleanSetAggr(resMap)
        find += s"(min $n $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        replaceCast(resMap.vector2set)

      case "maxs" =>
        noBooleanSetAggr(resMap)
        find += s"(max $n $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        replaceCast(resMap.vector2set)

      case "samples" =>
        noBooleanSetAggr(resMap)
        find += s"(sample $n $v_)"
        where += s"[$e $a $v]" -> wClause
        where += s"[$v $av $v_]" -> wClause
        replaceCast(resMap.vector2set)

      case "distinct" =>
        noBooleanSetAggr(resMap)
        find += s"(distinct $v3)"
        where += s"[$e $a _]" -> wClause
        where +=
          s"""[(datomic.api/q
             |          "[:find (distinct $pair)
             |            :in $$ $e
             |            :where [$e $a $v]
             |                   [$v $ak $k_]
             |                   [$v $av $v_]
             |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
        where += s"[(sort-by first $v1) $v2]" -> wClause
        where += s"[(map second $v2) $v3]" -> wClause
        replaceCast(resMap.jSetOfLists2s)

      case other => unexpectedKw(other)
    }
  }


  // Filter attribute filters --------------------------------------------------

  private def equal2(
    attr: Attr, e: Var, v: Var, filterAttr: String
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $pair)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ak $k_]
         |                   [$v $av $v_]
         |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(sort-by first $v1) $v2]" -> wClause
    where += s"[(map second $v2) $v3]" -> wClause

    val process: (Var, Var) => Unit = (_: Var, w: Var) => {
      where += s"[(= $v3 ${w}3)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) => process(e, a) }
  }


  private def neq2(
    attr: Attr, e: Var, v: Var, filterAttr: String
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)

    // Common for pre-query and main query
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $pair)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ak $k_]
         |                   [$v $av $v_]
         |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(sort-by first $v1) $v2]" -> wClause
    where += s"[(map second $v2) $v3]" -> wClause

    val process: (Var, Var) => Unit = (f: Var, w: Var) => {
      val blacklist   = w + "-blacklist"
      val blacklisted = w + "-blacklisted"

      // Pre-query (merged with `when` clauses)
      preFind = f
      preWhere += s"[(= $v3 ${w}3)]" -> wClause

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


  private def has2[T](
    attr: Attr, e: Var, v: Var, filterAttr: String, resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $pair)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ak $k_]
         |                   [$v $av $v_]
         |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(sort-by first $v1) $v2]" -> wClause
    where += s"[(map second $v2) $v3]" -> wClause

    val process: (Var, Var) => Unit = if (attr.filterAttr.get._3.isInstanceOf[AttrOne]) {
      (_: Var, w: Var) =>
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(contains? $v4 $w)]" -> wClause

    } else if (resMap.tpe == "Boolean") {
      (_: Var, w: Var) =>
        // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
        where += s"[(map str $v3) $v3-list]" -> wClause
        where += s"[(set $v3-list) $v3-set]" -> wClause
        where += s"[(map str ${w}3) ${w}3-list]" -> wClause
        where += s"[(set ${w}3-list) ${w}3-set]" -> wClause
        where += s"[(clojure.set/intersection $v3-set ${w}3-set) $w-intersection]" -> wClause
        where += s"[(= ${w}3-set $w-intersection)]" -> wClause

    } else {
      (_: Var, w: Var) =>
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(set ${w}3) ${w}4]" -> wClause
        where += s"[(clojure.set/intersection $v4 ${w}4) $w-intersection]" -> wClause
        where += s"[(= ${w}4 $w-intersection)]" -> wClause
    }
    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  private def hasNo2[T](
    attr: Attr, e: Var, v: Var, filterAttr: String, resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $pair)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ak $k_]
         |                   [$v $av $v_]
         |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(sort-by first $v1) $v2]" -> wClause
    where += s"[(map second $v2) $v3]" -> wClause

    val process: (Var, Var) => Unit = if (attr.filterAttr.get._3.isInstanceOf[AttrOne]) {
      (_: Var, w: Var) => {
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(contains? $v4 $w) $v5]" -> wClause
        where += s"[(not $v5)]" -> wClause
      }

    } else if (resMap.tpe == "Boolean") {
      (_: Var, w: Var) =>
        // Need to convert to sets of Strings for `some` to work on boolean false (maybe a bug?)
        where += s"[(map str $v3) $v3-list]" -> wClause
        where += s"[(set $v3-list) $v3-set]" -> wClause
        where += s"[(map str ${w}3) ${w}3-list]" -> wClause
        where += s"[(set ${w}3-list) ${w}3-set]" -> wClause
        where += s"[(clojure.set/intersection $v3-set ${w}3-set) $w-intersection]" -> wClause
        where += s"[(empty? $w-intersection)]" -> wClause

    } else {
      (_: Var, w: Var) =>
        where += s"[(set $v3) $v4]" -> wClause
        where += s"[(set ${w}3) ${w}4]" -> wClause
        where += s"[(clojure.set/intersection $v4 ${w}4) $w-intersection]" -> wClause
        where += s"[(empty? $w-intersection)]" -> wClause
    }

    filterAttrVars1.get(filterAttr).fold {
      filterAttrVars2 = filterAttrVars2 + (filterAttr -> process)
    } { case (e, a) =>
      process(e, a)
    }
  }


  // helpers -------------------------------------------------------------------

  private def none(attr: Attr, e: Var, v: Var): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += s"(pull $e-$v [[$a :limit nil]])"
    where += s"[(identity $e) $e-$v]" -> wGround
    where += s"(not [$e $a])" -> wNeqOne
  }

  private def optWithoutNone(
    attr: Attr, e: Var, v: Var
  ): Unit = {
    // Ignore empty maps
    where += s"[(nil? ${v}1) ${v}1-empty]" -> wClause
    where += s"[(not ${v}1-empty)]" -> wClause
    optAttr(attr, e, v) // Get all available
  }

  private def noBooleanSetAggr[T](resMap: ResMap[T]): Unit = {
    if (resMap.tpe == "Boolean")
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
  private def noBooleanSetCounts(n: Int): Unit = {
    if (n == -1)
      throw ModelError("Aggregate functions not implemented for Sets of boolean values.")
  }
}