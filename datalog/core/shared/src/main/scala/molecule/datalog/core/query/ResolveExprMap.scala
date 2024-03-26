package molecule.datalog.core.query

import java.lang
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
      case at: AttrMapManID             => noId
      case at: AttrMapManString         => man(attr, e, at.vs, resMapString)
      case at: AttrMapManInt            => man(attr, e, at.vs, resMapInt)
      case at: AttrMapManLong           => man(attr, e, at.vs, resMapLong)
      case at: AttrMapManFloat          => man(attr, e, at.vs, resMapFloat)
      case at: AttrMapManDouble         => man(attr, e, at.vs, resMapDouble)
      case at: AttrMapManBoolean        => man(attr, e, at.vs, resMapBoolean)
      case at: AttrMapManBigInt         => man(attr, e, at.vs, resMapBigInt)
      case at: AttrMapManBigDecimal     => man(attr, e, at.vs, resMapBigDecimal)
      case at: AttrMapManDate           => man(attr, e, at.vs, resMapDate)
      case at: AttrMapManDuration       => man(attr, e, at.vs, resMapDuration)
      case at: AttrMapManInstant        => man(attr, e, at.vs, resMapInstant)
      case at: AttrMapManLocalDate      => man(attr, e, at.vs, resMapLocalDate)
      case at: AttrMapManLocalTime      => man(attr, e, at.vs, resMapLocalTime)
      case at: AttrMapManLocalDateTime  => man(attr, e, at.vs, resMapLocalDateTime)
      case at: AttrMapManOffsetTime     => man(attr, e, at.vs, resMapOffsetTime)
      case at: AttrMapManOffsetDateTime => man(attr, e, at.vs, resMapOffsetDateTime)
      case at: AttrMapManZonedDateTime  => man(attr, e, at.vs, resMapZonedDateTime)
      case at: AttrMapManUUID           => man(attr, e, at.vs, resMapUUID)
      case at: AttrMapManURI            => man(attr, e, at.vs, resMapURI)
      case at: AttrMapManByte           => man(attr, e, at.vs, resMapByte)
      case at: AttrMapManShort          => man(attr, e, at.vs, resMapShort)
      case at: AttrMapManChar           => man(attr, e, at.vs, resMapChar)
    }
    es
  }

  protected def resolveAttrMapTac(es: List[Var], attr: AttrMapTac): List[Var] = {
    val e = es.last
    attr match {
      case at: AttrMapTacID             => noId
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
      case at: AttrMapOptID             => noId
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
  ): Unit = {
    val v = vv
    find += v
    addCast(resMap.j2sMap)
    //    expr(false, attr, e, v, attr.op, map, resMap)

    attr.op match {
      case V       => attrV(false, attr, e, v)
      case Has     => has(false, attr, e, v, map, resMap)
      case Eq      => throw ModelError(
        s"Matching/applying a map for map attribute `${attr.cleanName}` is not supported in queries."
      )
//      case Neq     => throw ModelError("Matching the whole Map is not supported for map attributes.")
//      case HasNo   => throw ModelError("Matching the whole Map is not supported for map attributes.")
//      case NoValue => throw ModelError("Matching the whole Map is not supported for map attributes.")
      case other   => unexpectedOp(other)
    }
  }

  private def tac[T: ClassTag](
    attr: Attr, e: Var,
    map: Map[String, T],
    resMap: ResMap[T],
  ): Unit = {
    val v = vv
    //    expr(true, attr, e, v, attr.op, map, resMap)
    attr.op match {
      case V       => attrV(true, attr, e, v)
      case Eq      => equal(attr, e, v, map, resMap)
      case Neq     => neq(true, attr, e, v, map, resMap.s2j)
      case Has     => has(true, attr, e, v, map, resMap)
      case HasNo   => hasNo(attr, e, v, map, resMap)
      case NoValue => noValue(attr, e)
      case other   => unexpectedOp(other)
    }
  }

  //  private def expr[T: ClassTag](
  //    tacit: Boolean,
  //    attr: Attr, e: Var, v: Var, op: Op,
  //    map: Map[String, T],
  //    resMap: ResMap[T],
  //  ): Unit = {
  //    op match {
  //      case V         => attrV(tacit, attr, e, v)
  //      case Eq        => equal(attr, e, v, map, resMap)
  //      case Neq       => neq(tacit, attr, e, v, map, resMap.s2j)
  //      case Has       => has(tacit, attr, e, v, map, resMap)
  //      case HasNo     => hasNo(attr, e, v, map, resMap)
  //      case NoValue   => noValue(attr, e)
  //      case other     => unexpectedOp(other)
  //    }
  //  }

  private def opt[T: ClassTag](
    attr: Attr, e: Var, op: Op,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
    resMap: ResMap[T],
  ): Unit = {
    val v = vv
    addCast(resMapOpt.j2optMap)
    op match {
      case V     => optAttr(attr, e, v)
      case Has   => optHas(attr, e, v, optMap, resMap)
//      case Eq    => optEqual(attr, e, v, optMap, resMapOpt, resMap)
//      case Neq   => optNeq(attr, e, v, optMap, resMapOpt, resMap)
//      case HasNo => optHasNo(attr, e, v, optMap, resMap, resMapOpt)
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
    if (tacit) {
      widh += v // Don't coalesce List to Set
    }
  }

  private def optAttr(
    attr: Attr, e: Var, v: Var
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    find += v3
    where +=
      s"""[(datomic.api/q
         |          "[:find (pull $e [{($a :limit nil) [$ak $av]}])
         |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
    where += s"[($a $v2) $v3]" -> wClause
  }


  // eq ------------------------------------------------------------------------

  private def equal[T](
    attr: Attr, e: Var, v: Var,
    map: Map[String, T],
    resMap: ResMap[T],
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    //    val xx: lang.Iterable[Any]                            =  map.map(resMap.s2j).asJava
    args += map.toList.map(pair => resMap.sPair2jVector(pair)).asJava
    in += s"[$v2 ...]"
    //    in += s"[[$v1]]"
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
    where += s"[(= $v $v2)]" -> wClause
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
      replaceCast(resMapOpt.j2optMap)
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
        replaceCast(resMapOpt.j2optMap)

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
        replaceCast(resMapOpt.j2optMap)

      case _ => optWithoutNone(attr, e, v)
    }
  }


  // no value -----------------------------------------------------------------

  private def noValue(attr: Attr, e: Var): Unit = {
    val a = nsAttr(attr)
    where += s"(not [$e $a])" -> wNeqOne
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