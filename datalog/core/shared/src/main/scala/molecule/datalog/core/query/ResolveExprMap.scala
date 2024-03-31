package molecule.datalog.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.util.JavaConversions

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

  protected def resolveAttrMapOpt(es: List[Var], attr: AttrMapOpt): List[Var] = {
    aritiesAttr()
    attrIndex += 1
    hasOptAttr = true
    val e = es.last
    attr match {
      case at: AttrMapOptID             => noId
      case at: AttrMapOptString         => opt(attr, e, at.vs, resOptMapString)
      case at: AttrMapOptInt            => opt(attr, e, at.vs, resOptMapInt)
      case at: AttrMapOptLong           => opt(attr, e, at.vs, resOptMapLong)
      case at: AttrMapOptFloat          => opt(attr, e, at.vs, resOptMapFloat)
      case at: AttrMapOptDouble         => opt(attr, e, at.vs, resOptMapDouble)
      case at: AttrMapOptBoolean        => opt(attr, e, at.vs, resOptMapBoolean)
      case at: AttrMapOptBigInt         => opt(attr, e, at.vs, resOptMapBigInt)
      case at: AttrMapOptBigDecimal     => opt(attr, e, at.vs, resOptMapBigDecimal)
      case at: AttrMapOptDate           => opt(attr, e, at.vs, resOptMapDate)
      case at: AttrMapOptDuration       => opt(attr, e, at.vs, resOptMapDuration)
      case at: AttrMapOptInstant        => opt(attr, e, at.vs, resOptMapInstant)
      case at: AttrMapOptLocalDate      => opt(attr, e, at.vs, resOptMapLocalDate)
      case at: AttrMapOptLocalTime      => opt(attr, e, at.vs, resOptMapLocalTime)
      case at: AttrMapOptLocalDateTime  => opt(attr, e, at.vs, resOptMapLocalDateTime)
      case at: AttrMapOptOffsetTime     => opt(attr, e, at.vs, resOptMapOffsetTime)
      case at: AttrMapOptOffsetDateTime => opt(attr, e, at.vs, resOptMapOffsetDateTime)
      case at: AttrMapOptZonedDateTime  => opt(attr, e, at.vs, resOptMapZonedDateTime)
      case at: AttrMapOptUUID           => opt(attr, e, at.vs, resOptMapUUID)
      case at: AttrMapOptURI            => opt(attr, e, at.vs, resOptMapURI)
      case at: AttrMapOptByte           => opt(attr, e, at.vs, resOptMapByte)
      case at: AttrMapOptShort          => opt(attr, e, at.vs, resOptMapShort)
      case at: AttrMapOptChar           => opt(attr, e, at.vs, resOptMapChar)
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

  private def man[T](
    attr: Attr, e: Var,
    map: Map[String, T],
    resMap: ResMap[T],
  ): Unit = {
    val v = vv
    find += v
    attr.op match {
      case V       => attrV(attr, e, v, resMap)
      case Has     => key2value(attr, e, v, map.head._1, resMap)
      case Eq      => noCollectionMatching(attr)
      case NoValue => noApplyNothing(attr)
      case other   => unexpectedOp(other)
    }
  }

  private def opt[T](
    attr: Attr, e: Var,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
  ): Unit = {
    val v = vv
    find += v
    attr.op match {
      case V     => optAttr(attr, e, v, resMapOpt)
      case Has   => key2optValue(attr, e, v, optMap.get.head._1, resMapOpt)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }

  private def tac[T](
    attr: Attr, e: Var, map: Map[String, T], resMap: ResMap[T],
  ): Unit = {
    val v = vv
    attr.op match {
      case V       => attrV(attr, e, v, resMap)
      case Eq      => containsKeys(attr, e, v, map)
      case Neq     => containsNoKeys(attr, e, v, map)
      case Has     => hasValues(attr, e, v, map, resMap)
      case HasNo   => hasNoValues(attr, e, v, map, resMap)
      case NoValue => nonAsserted(attr, e)
      case other   => unexpectedOp(other)
    }
  }


  // attr ----------------------------------------------------------------------

  private def attrV[T](
    attr: Attr, e: Var, v: Var, resMap: ResMap[T]
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
    addCast(resMap.j2sMap)
  }

  private def optAttr[T](
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

  private def containsKeys[T](
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


  private def containsNoKeys[T](
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


  private def hasValues[T](
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


  private def hasNoValues[T](
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
      attrV(attr, e, v, resMap)
    }
  }


  // no value ------------------------------------------------------------------

  private def nonAsserted(attr: Attr, e: Var): Unit = {
    val a = nsAttr(attr)
    where += s"(not [$e $a])" -> wNeqOne
  }

  def noApplyNothing(attr: Attr): Unit = {
    val a = attr.cleanName
    throw ModelError(
      s"Applying nothing to mandatory attribute ($a) is reserved for updates to retract."
    )
  }
}