package molecule.datalog.core.query

import molecule.boilerplate.ast.Model._
import molecule.core.query.QueryExpr
import molecule.core.util.JavaConversions

trait QueryExprMap[Tpl] extends QueryExpr with JavaConversions { self: Model2DatomicQuery[Tpl] with LambdasMap =>

  override protected def queryAttrMapMan(attr: AttrMapMan): Unit = {
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrMapManID             => noId
      case at: AttrMapManString         => mapMan(attr, e, at.keys, at.values, resMapString)
      case at: AttrMapManInt            => mapMan(attr, e, at.keys, at.values, resMapInt)
      case at: AttrMapManLong           => mapMan(attr, e, at.keys, at.values, resMapLong)
      case at: AttrMapManFloat          => mapMan(attr, e, at.keys, at.values, resMapFloat)
      case at: AttrMapManDouble         => mapMan(attr, e, at.keys, at.values, resMapDouble)
      case at: AttrMapManBoolean        => mapMan(attr, e, at.keys, at.values, resMapBoolean)
      case at: AttrMapManBigInt         => mapMan(attr, e, at.keys, at.values, resMapBigInt)
      case at: AttrMapManBigDecimal     => mapMan(attr, e, at.keys, at.values, resMapBigDecimal)
      case at: AttrMapManDate           => mapMan(attr, e, at.keys, at.values, resMapDate)
      case at: AttrMapManDuration       => mapMan(attr, e, at.keys, at.values, resMapDuration)
      case at: AttrMapManInstant        => mapMan(attr, e, at.keys, at.values, resMapInstant)
      case at: AttrMapManLocalDate      => mapMan(attr, e, at.keys, at.values, resMapLocalDate)
      case at: AttrMapManLocalTime      => mapMan(attr, e, at.keys, at.values, resMapLocalTime)
      case at: AttrMapManLocalDateTime  => mapMan(attr, e, at.keys, at.values, resMapLocalDateTime)
      case at: AttrMapManOffsetTime     => mapMan(attr, e, at.keys, at.values, resMapOffsetTime)
      case at: AttrMapManOffsetDateTime => mapMan(attr, e, at.keys, at.values, resMapOffsetDateTime)
      case at: AttrMapManZonedDateTime  => mapMan(attr, e, at.keys, at.values, resMapZonedDateTime)
      case at: AttrMapManUUID           => mapMan(attr, e, at.keys, at.values, resMapUUID)
      case at: AttrMapManURI            => mapMan(attr, e, at.keys, at.values, resMapURI)
      case at: AttrMapManByte           => mapMan(attr, e, at.keys, at.values, resMapByte)
      case at: AttrMapManShort          => mapMan(attr, e, at.keys, at.values, resMapShort)
      case at: AttrMapManChar           => mapMan(attr, e, at.keys, at.values, resMapChar)
    }
  }

  override protected def queryAttrMapOpt(attr: AttrMapOpt): Unit = {
    attrIndex += 1
    val e = es.last
    attr match {
      case at: AttrMapOptID             => noId
      case at: AttrMapOptString         => mapOpt(attr, e, at.keys, resOptMapString)
      case at: AttrMapOptInt            => mapOpt(attr, e, at.keys, resOptMapInt)
      case at: AttrMapOptLong           => mapOpt(attr, e, at.keys, resOptMapLong)
      case at: AttrMapOptFloat          => mapOpt(attr, e, at.keys, resOptMapFloat)
      case at: AttrMapOptDouble         => mapOpt(attr, e, at.keys, resOptMapDouble)
      case at: AttrMapOptBoolean        => mapOpt(attr, e, at.keys, resOptMapBoolean)
      case at: AttrMapOptBigInt         => mapOpt(attr, e, at.keys, resOptMapBigInt)
      case at: AttrMapOptBigDecimal     => mapOpt(attr, e, at.keys, resOptMapBigDecimal)
      case at: AttrMapOptDate           => mapOpt(attr, e, at.keys, resOptMapDate)
      case at: AttrMapOptDuration       => mapOpt(attr, e, at.keys, resOptMapDuration)
      case at: AttrMapOptInstant        => mapOpt(attr, e, at.keys, resOptMapInstant)
      case at: AttrMapOptLocalDate      => mapOpt(attr, e, at.keys, resOptMapLocalDate)
      case at: AttrMapOptLocalTime      => mapOpt(attr, e, at.keys, resOptMapLocalTime)
      case at: AttrMapOptLocalDateTime  => mapOpt(attr, e, at.keys, resOptMapLocalDateTime)
      case at: AttrMapOptOffsetTime     => mapOpt(attr, e, at.keys, resOptMapOffsetTime)
      case at: AttrMapOptOffsetDateTime => mapOpt(attr, e, at.keys, resOptMapOffsetDateTime)
      case at: AttrMapOptZonedDateTime  => mapOpt(attr, e, at.keys, resOptMapZonedDateTime)
      case at: AttrMapOptUUID           => mapOpt(attr, e, at.keys, resOptMapUUID)
      case at: AttrMapOptURI            => mapOpt(attr, e, at.keys, resOptMapURI)
      case at: AttrMapOptByte           => mapOpt(attr, e, at.keys, resOptMapByte)
      case at: AttrMapOptShort          => mapOpt(attr, e, at.keys, resOptMapShort)
      case at: AttrMapOptChar           => mapOpt(attr, e, at.keys, resOptMapChar)
    }
  }

  override protected def queryAttrMapTac(attr: AttrMapTac): Unit = {
    val e = es.last
    attr match {
      case at: AttrMapTacID             => noId
      case at: AttrMapTacString         => mapTac(attr, e, at.keys, at.values, resMapString)
      case at: AttrMapTacInt            => mapTac(attr, e, at.keys, at.values, resMapInt)
      case at: AttrMapTacLong           => mapTac(attr, e, at.keys, at.values, resMapLong)
      case at: AttrMapTacFloat          => mapTac(attr, e, at.keys, at.values, resMapFloat)
      case at: AttrMapTacDouble         => mapTac(attr, e, at.keys, at.values, resMapDouble)
      case at: AttrMapTacBoolean        => mapTac(attr, e, at.keys, at.values, resMapBoolean)
      case at: AttrMapTacBigInt         => mapTac(attr, e, at.keys, at.values, resMapBigInt)
      case at: AttrMapTacBigDecimal     => mapTac(attr, e, at.keys, at.values, resMapBigDecimal)
      case at: AttrMapTacDate           => mapTac(attr, e, at.keys, at.values, resMapDate)
      case at: AttrMapTacDuration       => mapTac(attr, e, at.keys, at.values, resMapDuration)
      case at: AttrMapTacInstant        => mapTac(attr, e, at.keys, at.values, resMapInstant)
      case at: AttrMapTacLocalDate      => mapTac(attr, e, at.keys, at.values, resMapLocalDate)
      case at: AttrMapTacLocalTime      => mapTac(attr, e, at.keys, at.values, resMapLocalTime)
      case at: AttrMapTacLocalDateTime  => mapTac(attr, e, at.keys, at.values, resMapLocalDateTime)
      case at: AttrMapTacOffsetTime     => mapTac(attr, e, at.keys, at.values, resMapOffsetTime)
      case at: AttrMapTacOffsetDateTime => mapTac(attr, e, at.keys, at.values, resMapOffsetDateTime)
      case at: AttrMapTacZonedDateTime  => mapTac(attr, e, at.keys, at.values, resMapZonedDateTime)
      case at: AttrMapTacUUID           => mapTac(attr, e, at.keys, at.values, resMapUUID)
      case at: AttrMapTacURI            => mapTac(attr, e, at.keys, at.values, resMapURI)
      case at: AttrMapTacByte           => mapTac(attr, e, at.keys, at.values, resMapByte)
      case at: AttrMapTacShort          => mapTac(attr, e, at.keys, at.values, resMapShort)
      case at: AttrMapTacChar           => mapTac(attr, e, at.keys, at.values, resMapChar)
    }
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
    attr: Attr, e: Var, keys: Seq[String], values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    val v = vv
    find += v
    attr.op match {
      case V       => mapAttr(attr, e, v, resMap, true)
      case Eq      => mapManKey2value(attr, e, v, keys, resMap)
      case Has     => mapManHasValues(attr, e, v, values, resMap)
      case HasNo   => mapManHasNoValues(attr, e, v, values, resMap)
      case NoValue => noApplyNothing(attr)
      case other   => unexpectedOp(other)
    }
  }

  private def mapTac[T](
    attr: Attr, e: Var, keys: Seq[String], values: Seq[T], resMap: ResMap[T],
  ): Unit = {
    val v = vv
    attr.op match {
      case V       => mapAttr(attr, e, v, resMap, false)
      case Eq      => mapTacKeys(attr, e, v, keys)
      case Neq     => mapTacNoKeys(attr, e, v, keys)
      case Has     => mapTacHasValues(attr, e, v, values, resMap)
      case HasNo   => mapTacHasNoValues(attr, e, v, values, resMap)
      case NoValue => mapTacNoValue(attr, e)
      case other   => unexpectedOp(other)
    }
  }

  private def mapOpt[T](
    attr: Attr, e: Var, keys: Seq[String], resMapOpt: ResMapOpt[T]
  ): Unit = {
    val v = vv
    find += v
    attr.op match {
      case V     => mapOptAttr(attr, e, v, resMapOpt)
      case Has   => mapOptKey2optValue(attr, e, v, keys.head, resMapOpt)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
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

  private def mapManKey2value[T](
    attr: Attr, e: Var, v: Var, keys: Seq[String], resMap: ResMap[T]
  ): Unit = {
    if (keys.nonEmpty) {
      val key = keys.head
      val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
      where += s"[$e $a $v1]" -> wClause
      where += s"""[$v1 $ak "$key"]""" -> wClause
      where += s"[$v1 $av $v]" -> wClause
      addCast(resMap.j2s)
    } else {
      noCollectionMatching(attr)
    }
  }


  private def mapOptKey2optValue[T](
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
    where += s"""[(filter (fn [pair] (= (first pair) "$key")) $v4) $v5]""" -> wClause
    where += s"[(map second $v5) $v]" -> wClause
    addCast(resMapOpt.j2optValue)
  }


  // mandatory -----------------------------------------------------------------

  private def mapManHasValues[T](
    attr: Attr, e: Var, v: Var, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (values.nonEmpty) {
      args += values.map(resMap.s2j).asJava
      in += s"[$v2 ...]"
      where += s"[$e $a $v1]" -> wClause
      where += s"[$v1 $av $v2]" -> wClause
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ak $k_]
           |                   [$v $av $v_]
           |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v]]]""".stripMargin -> wClause
      addCast(resMap.j2sMap)
    } else {
      // Match nothing
      where += s"[$e $a $v]" -> wClause
      where += s"[(ground nil) $v]" -> wGround
    }
  }

  private def mapManHasNoValues[T](
    attr: Attr, e: Var, v: Var, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (values.nonEmpty) {
      args += values.map(resMap.s2j).asJava
      in += v1
      where += s"[$e $a _]" -> wClause

      // Exclude maps containing needles
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct $v_)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $av $v_]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
      where += s"[(set $v1) $v3]" -> wClause
      where += s"[(clojure.set/intersection $v2 $v3) $v4]" -> wClause
      where += s"[(empty? $v4)]" -> wClause

      // Include full maps
      where +=
        s"""[(datomic.api/q
           |          "[:find (distinct $pair)
           |            :in $$ $e
           |            :where [$e $a $v]
           |                   [$v $ak $k_]
           |                   [$v $av $v_]
           |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v]]]""".stripMargin -> wClause
      addCast(resMap.j2sMap)
    } else {
      // Get all
      mapAttr(attr, e, v, resMap, true)
    }
  }


  // tacit ---------------------------------------------------------------------

  private def mapTacKeys(
    attr: Attr, e: Var, v: Var, keys: Seq[String]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    args += keys.asJava
    in += v1
    where += s"[$e $a _]" -> wClause
    where +=
      s"""[(datomic.api/q
         |          "[:find (distinct $v)
         |            :in $$ $e [$v1 ...]
         |            :where [$e $a $v]
         |                   [$v $ak $v1]]" $$ $e $v1) [[$v2]]]""".stripMargin -> wClause
  }


  private def mapTacNoKeys(
    attr: Attr, e: Var, v: Var, keys: Seq[String]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    args += keys.asJava
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


  private def mapTacHasValues[T](
    attr: Attr, e: Var, v: Var, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (values.nonEmpty) {
      args += values.map(resMap.s2j).asJava
      in += s"[$v2 ...]"
      where += s"[$e $a $v1]" -> wClause
      where += s"[$v1 $av $v2]" -> wClause
    } else {
      // Match nothing
      where += s"[$e $a $v]" -> wClause
      where += s"[(ground nil) $v]" -> wGround
    }
  }


  private def mapTacHasNoValues[T](
    attr: Attr, e: Var, v: Var, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
    if (values.nonEmpty) {
      args += values.map(resMap.s2j).asJava
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
      mapAttr(attr, e, v, resMap, false)
    }
  }


  private def mapTacNoValue(attr: Attr, e: Var): Unit = {
    val a = nsAttr(attr)
    where += s"(not [$e $a])" -> wNeqOne
  }
}