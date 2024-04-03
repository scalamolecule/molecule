package molecule.sql.core.query

import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import scala.collection.mutable.ListBuffer
import scala.reflect.ClassTag

trait ResolveExprMap extends ResolveExpr { self: SqlQueryBase with LambdasMap =>

  override protected def resolveAttrMapMan(attr: AttrMapMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrMapManID             => mapMan(attr, "String", at.vs, resMapId)
      case at: AttrMapManString         => mapMan(attr, "String", at.vs, resMapString)
      case at: AttrMapManInt            => mapMan(attr, "Int", at.vs, resMapInt)
      case at: AttrMapManLong           => mapMan(attr, "Long", at.vs, resMapLong)
      case at: AttrMapManFloat          => mapMan(attr, "Float", at.vs, resMapFloat)
      case at: AttrMapManDouble         => mapMan(attr, "Double", at.vs, resMapDouble)
      case at: AttrMapManBoolean        => mapMan(attr, "Boolean", at.vs, resMapBoolean)
      case at: AttrMapManBigInt         => mapMan(attr, "BigInt", at.vs, resMapBigInt)
      case at: AttrMapManBigDecimal     => mapMan(attr, "BigDecimal", at.vs, resMapBigDecimal)
      case at: AttrMapManDate           => mapMan(attr, "Date", at.vs, resMapDate)
      case at: AttrMapManDuration       => mapMan(attr, "Duration", at.vs, resMapDuration)
      case at: AttrMapManInstant        => mapMan(attr, "Instant", at.vs, resMapInstant)
      case at: AttrMapManLocalDate      => mapMan(attr, "LocalDate", at.vs, resMapLocalDate)
      case at: AttrMapManLocalTime      => mapMan(attr, "LocalTime", at.vs, resMapLocalTime)
      case at: AttrMapManLocalDateTime  => mapMan(attr, "LocalDateTime", at.vs, resMapLocalDateTime)
      case at: AttrMapManOffsetTime     => mapMan(attr, "OffsetTime", at.vs, resMapOffsetTime)
      case at: AttrMapManOffsetDateTime => mapMan(attr, "OffsetDateTime", at.vs, resMapOffsetDateTime)
      case at: AttrMapManZonedDateTime  => mapMan(attr, "ZonedDateTime", at.vs, resMapZonedDateTime)
      case at: AttrMapManUUID           => mapMan(attr, "UUID", at.vs, resMapUUID)
      case at: AttrMapManURI            => mapMan(attr, "URI", at.vs, resMapURI)
      case at: AttrMapManByte           => mapMan(attr, "Byte", at.vs, resMapByte)
      case at: AttrMapManShort          => mapMan(attr, "Short", at.vs, resMapShort)
      case at: AttrMapManChar           => mapMan(attr, "Char", at.vs, resMapChar)
    }
  }

  override protected def resolveAttrMapTac(attr: AttrMapTac): Unit = {
    attr match {
      case at: AttrMapTacID             => mapTac(attr, "String", at.vs, resMapId)
      case at: AttrMapTacString         => mapTac(attr, "String", at.vs, resMapString)
      case at: AttrMapTacInt            => mapTac(attr, "Int", at.vs, resMapInt)
      case at: AttrMapTacLong           => mapTac(attr, "Long", at.vs, resMapLong)
      case at: AttrMapTacFloat          => mapTac(attr, "Float", at.vs, resMapFloat)
      case at: AttrMapTacDouble         => mapTac(attr, "Double", at.vs, resMapDouble)
      case at: AttrMapTacBoolean        => mapTac(attr, "Boolean", at.vs, resMapBoolean)
      case at: AttrMapTacBigInt         => mapTac(attr, "BigInt", at.vs, resMapBigInt)
      case at: AttrMapTacBigDecimal     => mapTac(attr, "BigDecimal", at.vs, resMapBigDecimal)
      case at: AttrMapTacDate           => mapTac(attr, "Date", at.vs, resMapDate)
      case at: AttrMapTacDuration       => mapTac(attr, "Duration", at.vs, resMapDuration)
      case at: AttrMapTacInstant        => mapTac(attr, "Instant", at.vs, resMapInstant)
      case at: AttrMapTacLocalDate      => mapTac(attr, "LocalDate", at.vs, resMapLocalDate)
      case at: AttrMapTacLocalTime      => mapTac(attr, "LocalTime", at.vs, resMapLocalTime)
      case at: AttrMapTacLocalDateTime  => mapTac(attr, "LocalDateTime", at.vs, resMapLocalDateTime)
      case at: AttrMapTacOffsetTime     => mapTac(attr, "OffsetTime", at.vs, resMapOffsetTime)
      case at: AttrMapTacOffsetDateTime => mapTac(attr, "OffsetDateTime", at.vs, resMapOffsetDateTime)
      case at: AttrMapTacZonedDateTime  => mapTac(attr, "ZonedDateTime", at.vs, resMapZonedDateTime)
      case at: AttrMapTacUUID           => mapTac(attr, "UUID", at.vs, resMapUUID)
      case at: AttrMapTacURI            => mapTac(attr, "URI", at.vs, resMapURI)
      case at: AttrMapTacByte           => mapTac(attr, "Byte", at.vs, resMapByte)
      case at: AttrMapTacShort          => mapTac(attr, "Short", at.vs, resMapShort)
      case at: AttrMapTacChar           => mapTac(attr, "Char", at.vs, resMapChar)
    }
  }

  override protected def resolveAttrMapOpt(attr: AttrMapOpt): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrMapOptID             => mapOpt(at, at.vs, resOptMapId, resMapId)
      case at: AttrMapOptString         => mapOpt(at, at.vs, resOptMapString, resMapString)
      case at: AttrMapOptInt            => mapOpt(at, at.vs, resOptMapInt, resMapInt)
      case at: AttrMapOptLong           => mapOpt(at, at.vs, resOptMapLong, resMapLong)
      case at: AttrMapOptFloat          => mapOpt(at, at.vs, resOptMapFloat, resMapFloat)
      case at: AttrMapOptDouble         => mapOpt(at, at.vs, resOptMapDouble, resMapDouble)
      case at: AttrMapOptBoolean        => mapOpt(at, at.vs, resOptMapBoolean, resMapBoolean)
      case at: AttrMapOptBigInt         => mapOpt(at, at.vs, resOptMapBigInt, resMapBigInt)
      case at: AttrMapOptBigDecimal     => mapOpt(at, at.vs, resOptMapBigDecimal, resMapBigDecimal)
      case at: AttrMapOptDate           => mapOpt(at, at.vs, resOptMapDate, resMapDate)
      case at: AttrMapOptDuration       => mapOpt(at, at.vs, resOptMapDuration, resMapDuration)
      case at: AttrMapOptInstant        => mapOpt(at, at.vs, resOptMapInstant, resMapInstant)
      case at: AttrMapOptLocalDate      => mapOpt(at, at.vs, resOptMapLocalDate, resMapLocalDate)
      case at: AttrMapOptLocalTime      => mapOpt(at, at.vs, resOptMapLocalTime, resMapLocalTime)
      case at: AttrMapOptLocalDateTime  => mapOpt(at, at.vs, resOptMapLocalDateTime, resMapLocalDateTime)
      case at: AttrMapOptOffsetTime     => mapOpt(at, at.vs, resOptMapOffsetTime, resMapOffsetTime)
      case at: AttrMapOptOffsetDateTime => mapOpt(at, at.vs, resOptMapOffsetDateTime, resMapOffsetDateTime)
      case at: AttrMapOptZonedDateTime  => mapOpt(at, at.vs, resOptMapZonedDateTime, resMapZonedDateTime)
      case at: AttrMapOptUUID           => mapOpt(at, at.vs, resOptMapUUID, resMapUUID)
      case at: AttrMapOptURI            => mapOpt(at, at.vs, resOptMapURI, resMapURI)
      case at: AttrMapOptByte           => mapOpt(at, at.vs, resOptMapByte, resMapByte)
      case at: AttrMapOptShort          => mapOpt(at, at.vs, resOptMapShort, resMapShort)
      case at: AttrMapOptChar           => mapOpt(at, at.vs, resOptMapChar, resMapChar)
    }
  }


  protected def mapMan[T: ClassTag](
    attr: Attr, tpe: String, args: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
    addCast(resMap.sqlJson2map)
    attr.op match {
      case V       => ()
//      case Has     => key2value(attr, e, v, map.head._1, resMap)
//      case NoValue => noMatch(attr, e, v, resMap)
      case Eq      => throw ModelError(
        s"Matching/applying a map for map attribute `${attr.cleanName}` is not supported in queries."
      )
      case other   => unexpectedOp(other)
    }
  }

  protected def mapOpt[T: ClassTag](
    attr: Attr,
    optMap: Option[Map[String, T]],
    resOpt: ResMapOpt[T],
    res: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resOpt.sql2mapOpt)
//    attr.op match {
//      //      case V     => ()
//      case V     => setOptAttr(col, res)
//      case Eq    => setOptEqual(col, optMap, res)
//      case Neq   => setOptNeq(col, optMap, res)
//      case Has   => optHas(col, optMap, res, resOpt.one2sql)
//      case HasNo => optHasNo(col, optMap, res, resOpt.one2sql)
//      case other => unexpectedOp(other)
//    }
//    attr.op match {
//      case V     => mapOptAttr(attr, e, v, resMapOpt)
//      case Has   => key2optValue(attr, e, v, optMap.get.head._1, resMapOpt)
//      case Eq    => throw ModelError(
//        s"Matching/applying a map for map attribute `${attr.cleanName}` is not supported in queries."
//      )
//      case other => unexpectedOp(other)
//    }
  }

  protected def mapTac[T: ClassTag](
    attr: Attr, tpe: String, args: Map[String, T], res: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    notNull += col
//    attr.filterAttr.fold {
//      setExpr(col, attr.op, args, res, false)
//    } { case (dir, filterPath, filterAttr) =>
//      filterAttr match {
//        case filterAttr: AttrOne => setExpr2(col, attr.op, filterAttr.name, true, tpe, res, false)
//        case filterAttr          => setExpr2(col, attr.op, filterAttr.name, false, tpe, res, false)
//      }
//    }

//    attr.op match {
//      case V       => mapAttr(attr, e, v, resMap)
//      case Eq      => containsKeys(attr, e, v, map)
//      case Neq     => containsNoKeys(attr, e, v, map)
//      case Has     => hasValues(attr, e, v, map, resMap)
//      case HasNo   => hasNoValues(attr, e, v, map, resMap)
//      case NoValue => nonAsserted(attr, e)
//      case other   => unexpectedOp(other)
//    }
  }



  // attr ----------------------------------------------------------------------

  protected def mapAttr[T: ClassTag](col: String, res: ResMap[T]): Unit = {


  }

  protected def mapOptAttr[T](col: String, res: ResMap[T]): Unit = {
    select -= col
    groupByCols -= col
    select += s"ARRAY_AGG($col)"
    aggregate = true
//    replaceCast(res.nestedArray2optCoalescedSet)
  }


  // attr ----------------------------------------------------------------------

//  private def attrV[T](
//    attr: Attr, e: Var, v: Var, resMap: ResMap[T]
//  ): Unit = {
//    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    where += s"[$e $a _]" -> wClause
//    where +=
//      s"""[(datomic.api/q
//         |          "[:find (distinct $pair)
//         |            :in $$ $e
//         |            :where [$e $a $v]
//         |                   [$v $ak $k_]
//         |                   [$v $av $v_]
//         |                   [(vector $k_ $v_) $pair]]" $$ $e) [[$v]]]""".stripMargin -> wClause
//    addCast(resMap.j2sMap)
//
//    ???
//  }
//
//  private def optAttr[T](
//    attr: Attr, resMapOpt: ResMapOpt[T]
//  ): Unit = {
////    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
////    where +=
////      s"""[(datomic.api/q
////         |          "[:find (pull $e [{($a :limit nil) [$ak $av]}])
////         |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
////    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
////    where += s"[($a $v2) $v]" -> wClause
////    addCast(resMapOpt.j2optMap)
//    ???
//  }


  // value lookup by key -------------------------------------------------------

//  private def key2value[T](
//    attr: Attr, e: Var, v: Var, key: String, resMap: ResMap[T]
//  ): Unit = {
//    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    where += s"[$e $a $v1]" -> wClause
//    where += s"[$v1 $ak \"$key\"]" -> wClause
//    where += s"[$v1 $av $v]" -> wClause
//    addCast(resMap.j2s)
//  }
//
//
//  private def key2optValue[T](
//    attr: Attr, e: Var, v: Var, key: String, resMapOpt: ResMapOpt[T]
//  ): Unit = {
//    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    where +=
//      s"""[(datomic.api/q
//         |          "[:find (pull $e [{($a :limit nil) [$ak $av]}])
//         |            :in $$ $e]" $$ $e) [[$v1]]]""".stripMargin -> wClause
//    where += s"[(if (nil? $v1) {$a []} $v1) $v2]" -> wClause
//    where += s"[($a $v2) $v3]" -> wClause
//    where += s"[(map vals $v3) $v4]" -> wClause
//    where += s"[(filter (fn [pair] (= (first pair) \"$key\")) $v4) $v5]" -> wClause
//    where += s"[(map second $v5) $v]" -> wClause
//    addCast(resMapOpt.j2optValue)
//  }
//
//
//  // tacit ---------------------------------------------------------------------
//
//  private def containsKeys[T](
//    attr: Attr, e: Var, v: Var, map: Map[String, T]
//  ): Unit = {
//    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    args += map.keys.asJava
//    in += v1
//    where += s"[$e $a _]" -> wClause
//    where +=
//      s"""[(datomic.api/q
//         |          "[:find (distinct $v)
//         |            :in $$ $e [$v1 ...]
//         |            :where [$e $a $v]
//         |                   [$v $ak $v1]]" $$ $e $v1) [[$v2]]]""".stripMargin -> wClause
//  }
//
//
//  private def containsNoKeys[T](
//    attr: Attr, e: Var, v: Var, map: Map[String, T]
//  ): Unit = {
//    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    args += map.keys.asJava
//    in += v1
//    where += s"[$e $a _]" -> wClause
//    where +=
//      s"""[(datomic.api/q
//         |          "[:find (distinct $k_)
//         |            :in $$ $e
//         |            :where [$e $a $v]
//         |                   [$v $ak $k_]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
//    where += s"[(set $v1) $v3]" -> wClause
//    where += s"[(clojure.set/intersection $v2 $v3) $v4]" -> wClause
//    where += s"[(empty? $v4)]" -> wClause
//  }
//
//
//  private def hasValues[T](
//    attr: Attr, e: Var, v: Var, map: Map[String, T], resMap: ResMap[T]
//  ): Unit = {
//    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    if (map.nonEmpty) {
//      args += map.values.map(resMap.s2j).asJava
//      in += v1
//      where += s"[$e $a _]" -> wClause
//      where +=
//        s"""[(datomic.api/q
//           |          "[:find (distinct $v)
//           |            :in $$ $e [$v1 ...]
//           |            :where [$e $a $v]
//           |                   [$v $av $v1]]" $$ $e $v1) [[$v2]]]""".stripMargin -> wClause
//    } else {
//      // Match nothing
//      where += s"[$e $a $v3]" -> wClause
//      where += s"[(ground nil) $v3]" -> wGround
//    }
//  }
//
//
//  private def hasNoValues[T](
//    attr: Attr, e: Var, v: Var, map: Map[String, T], resMap: ResMap[T]
//  ): Unit = {
//    val (a, ak, av, k_, v_, v1, v2, v3, v4, v5, v6, pair) = vars(attr, v)
//    if (map.nonEmpty) {
//      args += map.values.map(resMap.s2j).asJava
//      in += v1
//      where += s"[$e $a _]" -> wClause
//      where +=
//        s"""[(datomic.api/q
//           |          "[:find (distinct $v_)
//           |            :in $$ $e
//           |            :where [$e $a $v]
//           |                   [$v $av $v_]]" $$ $e) [[$v2]]]""".stripMargin -> wClause
//      where += s"[(set $v1) $v3]" -> wClause
//      where += s"[(clojure.set/intersection $v2 $v3) $v4]" -> wClause
//      where += s"[(empty? $v4)]" -> wClause
//    } else {
//      // Get all
//      attrV(attr, e, v, resMap)
//    }
//  }
//
//
//  // no value ------------------------------------------------------------------
//
//  private def noMatch[T](attr: Attr, e: Var, v: Var, resMap: ResMap[T]): Unit = {
//    val a = nsAttr(attr)
//    where += s"[$e $a $v]" -> wNeqOne
//    where += s"(not [$e $a])" -> wNeqOne
//    addCast(resMap.j2sMap)
//  }
//
//  private def nonAsserted(attr: Attr, e: Var): Unit = {
//    val a = nsAttr(attr)
//    where += s"(not [$e $a])" -> wNeqOne
//  }
}