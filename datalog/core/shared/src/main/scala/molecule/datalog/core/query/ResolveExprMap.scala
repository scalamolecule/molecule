package molecule.datalog.core.query

import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions

trait ResolveExprMap[Tpl] extends JavaConversions { self: Model2DatomicQuery[Tpl] with LambdasMap =>

  protected def resolveAttrMapMan(es: List[Var], attr: AttrMapMan): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrMapManID             => noId
      case at: AttrMapManString         => mapMan(attr, e, at.vs, resMapString)
      case at: AttrMapManInt            => mapMan(attr, e, at.vs, resMapInt)
      case at: AttrMapManLong           => mapMan(attr, e, at.vs, resMapLong)
      case at: AttrMapManFloat          => mapMan(attr, e, at.vs, resMapFloat)
      case at: AttrMapManDouble         => mapMan(attr, e, at.vs, resMapDouble)
      case at: AttrMapManBoolean        => mapMan(attr, e, at.vs, resMapBoolean)
      case at: AttrMapManBigInt         => mapMan(attr, e, at.vs, resMapBigInt)
      case at: AttrMapManBigDecimal     => mapMan(attr, e, at.vs, resMapBigDecimal)
      case at: AttrMapManDate           => mapMan(attr, e, at.vs, resMapDate)
      case at: AttrMapManDuration       => mapMan(attr, e, at.vs, resMapDuration)
      case at: AttrMapManInstant        => mapMan(attr, e, at.vs, resMapInstant)
      case at: AttrMapManLocalDate      => mapMan(attr, e, at.vs, resMapLocalDate)
      case at: AttrMapManLocalTime      => mapMan(attr, e, at.vs, resMapLocalTime)
      case at: AttrMapManLocalDateTime  => mapMan(attr, e, at.vs, resMapLocalDateTime)
      case at: AttrMapManOffsetTime     => mapMan(attr, e, at.vs, resMapOffsetTime)
      case at: AttrMapManOffsetDateTime => mapMan(attr, e, at.vs, resMapOffsetDateTime)
      case at: AttrMapManZonedDateTime  => mapMan(attr, e, at.vs, resMapZonedDateTime)
      case at: AttrMapManUUID           => mapMan(attr, e, at.vs, resMapUUID)
      case at: AttrMapManURI            => mapMan(attr, e, at.vs, resMapURI)
      case at: AttrMapManByte           => mapMan(attr, e, at.vs, resMapByte)
      case at: AttrMapManShort          => mapMan(attr, e, at.vs, resMapShort)
      case at: AttrMapManChar           => mapMan(attr, e, at.vs, resMapChar)
    }
    es
  }

  protected def resolveAttrMapOpt(es: List[Var], attr: AttrMapOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrMapOptID             => noId
      case at: AttrMapOptString         => mapOpt(attr, e, at.vs, resOptMapString)
      case at: AttrMapOptInt            => mapOpt(attr, e, at.vs, resOptMapInt)
      case at: AttrMapOptLong           => mapOpt(attr, e, at.vs, resOptMapLong)
      case at: AttrMapOptFloat          => mapOpt(attr, e, at.vs, resOptMapFloat)
      case at: AttrMapOptDouble         => mapOpt(attr, e, at.vs, resOptMapDouble)
      case at: AttrMapOptBoolean        => mapOpt(attr, e, at.vs, resOptMapBoolean)
      case at: AttrMapOptBigInt         => mapOpt(attr, e, at.vs, resOptMapBigInt)
      case at: AttrMapOptBigDecimal     => mapOpt(attr, e, at.vs, resOptMapBigDecimal)
      case at: AttrMapOptDate           => mapOpt(attr, e, at.vs, resOptMapDate)
      case at: AttrMapOptDuration       => mapOpt(attr, e, at.vs, resOptMapDuration)
      case at: AttrMapOptInstant        => mapOpt(attr, e, at.vs, resOptMapInstant)
      case at: AttrMapOptLocalDate      => mapOpt(attr, e, at.vs, resOptMapLocalDate)
      case at: AttrMapOptLocalTime      => mapOpt(attr, e, at.vs, resOptMapLocalTime)
      case at: AttrMapOptLocalDateTime  => mapOpt(attr, e, at.vs, resOptMapLocalDateTime)
      case at: AttrMapOptOffsetTime     => mapOpt(attr, e, at.vs, resOptMapOffsetTime)
      case at: AttrMapOptOffsetDateTime => mapOpt(attr, e, at.vs, resOptMapOffsetDateTime)
      case at: AttrMapOptZonedDateTime  => mapOpt(attr, e, at.vs, resOptMapZonedDateTime)
      case at: AttrMapOptUUID           => mapOpt(attr, e, at.vs, resOptMapUUID)
      case at: AttrMapOptURI            => mapOpt(attr, e, at.vs, resOptMapURI)
      case at: AttrMapOptByte           => mapOpt(attr, e, at.vs, resOptMapByte)
      case at: AttrMapOptShort          => mapOpt(attr, e, at.vs, resOptMapShort)
      case at: AttrMapOptChar           => mapOpt(attr, e, at.vs, resOptMapChar)
    }
    es
  }

  protected def resolveAttrMapTac(es: List[Var], attr: AttrMapTac): List[Var] = {
    val e = es.last
    attr match {
      case at: AttrMapTacID             => noId
      case at: AttrMapTacString         => mapTac(attr, e, at.vs, resMapString)
      case at: AttrMapTacInt            => mapTac(attr, e, at.vs, resMapInt)
      case at: AttrMapTacLong           => mapTac(attr, e, at.vs, resMapLong)
      case at: AttrMapTacFloat          => mapTac(attr, e, at.vs, resMapFloat)
      case at: AttrMapTacDouble         => mapTac(attr, e, at.vs, resMapDouble)
      case at: AttrMapTacBoolean        => mapTac(attr, e, at.vs, resMapBoolean)
      case at: AttrMapTacBigInt         => mapTac(attr, e, at.vs, resMapBigInt)
      case at: AttrMapTacBigDecimal     => mapTac(attr, e, at.vs, resMapBigDecimal)
      case at: AttrMapTacDate           => mapTac(attr, e, at.vs, resMapDate)
      case at: AttrMapTacDuration       => mapTac(attr, e, at.vs, resMapDuration)
      case at: AttrMapTacInstant        => mapTac(attr, e, at.vs, resMapInstant)
      case at: AttrMapTacLocalDate      => mapTac(attr, e, at.vs, resMapLocalDate)
      case at: AttrMapTacLocalTime      => mapTac(attr, e, at.vs, resMapLocalTime)
      case at: AttrMapTacLocalDateTime  => mapTac(attr, e, at.vs, resMapLocalDateTime)
      case at: AttrMapTacOffsetTime     => mapTac(attr, e, at.vs, resMapOffsetTime)
      case at: AttrMapTacOffsetDateTime => mapTac(attr, e, at.vs, resMapOffsetDateTime)
      case at: AttrMapTacZonedDateTime  => mapTac(attr, e, at.vs, resMapZonedDateTime)
      case at: AttrMapTacUUID           => mapTac(attr, e, at.vs, resMapUUID)
      case at: AttrMapTacURI            => mapTac(attr, e, at.vs, resMapURI)
      case at: AttrMapTacByte           => mapTac(attr, e, at.vs, resMapByte)
      case at: AttrMapTacShort          => mapTac(attr, e, at.vs, resMapShort)
      case at: AttrMapTacChar           => mapTac(attr, e, at.vs, resMapChar)
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

  private def mapMan[T](
    attr: Attr, e: Var, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val v = vv
    find += v
    attr.op match {
      case V       => mapAttr(attr, e, v, resMap, true)
      case Has     => key2value(attr, e, v, map.head._1, resMap)
      case Eq      => noCollectionMatching(attr)
      case NoValue => noApplyNothing(attr)
      case other   => unexpectedOp(other)
    }
  }

  private def mapOpt[T](
    attr: Attr, e: Var, optMap: Option[Map[String, T]], resMapOpt: ResMapOpt[T]
  ): Unit = {
    val v = vv
    find += v
    attr.op match {
      case V     => mapOptAttr(attr, e, v, resMapOpt)
      case Has   => key2optValue(attr, e, v, optMap.get.head._1, resMapOpt)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }

  private def mapTac[T](
    attr: Attr, e: Var, map: Map[String, T], resMap: ResMap[T],
  ): Unit = {
    val v = vv
    attr.op match {
      case V       => mapAttr(attr, e, v, resMap, false)
      case Eq      => mapContainsKeys(attr, e, v, map)
      case Neq     => mapContainsNoKeys(attr, e, v, map)
      case Has     => mapHasValues(attr, e, v, map, resMap)
      case HasNo   => mapHasNoValues(attr, e, v, map, resMap)
      case NoValue => mapNoValue(attr, e)
      case other   => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def mapAttr[T](
    attr: Attr, e: Var, v: Var, resMap: ResMap[T], mandatory: Boolean
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
    if (mandatory)
      addCast(resMap.j2sMap)
  }

  private def mapOptAttr[T](
    attr: Attr, e: Var, v: Var, resMapOpt: ResMapOpt[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where +=
      s"""[(datomic.api/q
         |          "[:find (pull $e [{($a :limit nil) [$ak $av]}])
         |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
    where += s"[($a $v2) $v]" -> wClause
    addCast(resMapOpt.j2optMap)
  }


  // value lookup by key -------------------------------------------------------

  private def key2value[T](
    attr: Attr, e: Var, v: Var, key: String, resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where += s"[$e $a $v1]" -> wClause
    where += s"[$v1 $ak \"$key\"]" -> wClause
    where += s"[$v1 $av $v]" -> wClause
    addCast(resMap.j2s)
  }


  private def key2optValue[T](
    attr: Attr, e: Var, v: Var, key: String, resMapOpt: ResMapOpt[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    where +=
      s"""[(datomic.api/q
         |          "[:find (pull $e [{($a :limit nil) [$ak $av]}])
         |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
    where += s"[($a $v2) $v3]" -> wClause
    where += s"[(map vals $v3) $v4]" -> wClause
    where += s"[(filter (fn [pair] (= (first pair) \"$key\")) $v4) $v5]" -> wClause
    where += s"[(map second $v5) $v]" -> wClause
    addCast(resMapOpt.j2optValue)
  }


  // tacit ---------------------------------------------------------------------

  private def mapContainsKeys[T](
    attr: Attr, e: Var, v: Var, map: Map[String, T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    args += map.keys.asJava
    in += v1
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $v)
         |            :in $$ $e [$v1 ...]
         |            :where [$e $a $v]
         |                   [$v $ak $v1]]" $$ $e $v1) [[$v2]]]""".stripMargin -> wClause
  }


  private def mapContainsNoKeys[T](
    attr: Attr, e: Var, v: Var, map: Map[String, T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    args += map.keys.asJava
    in += v1
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $k_)
         |            :in $$ $e
         |            :where [$e $a $v]
         |                   [$v $ak $k_]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
    where += s"[(set $v1) $v3]" -> wClause
    where += s"[(clojure.set/intersection $v2 $v3) $v4]" -> wClause
    where += s"[(empty? $v4)]" -> wClause
  }


  private def mapHasValues[T](
    attr: Attr, e: Var, v: Var, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (map.nonEmpty) {
      args += map.values.map(resMap.s2j).asJava
      in += v1
      where += s"[$e $a _]" -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct $v)
           |            :in $$ $e [$v1 ...]
           |            :where [$e $a $v]
           |                   [$v $av $v1]]" $$ $e $v1) [[$v2]]]""".stripMargin -> wClause
    } else {
      // Match nothing
      where += s"[$e $a $v3]" -> wClause
      where += s"[(ground nil) $v3]" -> wGround
    }
  }


  private def mapHasNoValues[T](
    attr: Attr, e: Var, v: Var, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (map.nonEmpty) {
      args += map.values.map(resMap.s2j).asJava
      in += v1
      where += s"[$e $a _]" -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct $v_)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $av $v_]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
      where += s"[(set $v1) $v3]" -> wClause
      where += s"[(clojure.set/intersection $v2 $v3) $v4]" -> wClause
      where += s"[(empty? $v4)]" -> wClause
    } else {
      // Get all
      mapAttr(attr, e, v, resMap, true)
    }
  }


  private def mapNoValue(attr: Attr, e: Var): Unit = {
    val a = nsAttr(attr)
    where += s"(not [$e $a])" -> wNeqOne
  }
}