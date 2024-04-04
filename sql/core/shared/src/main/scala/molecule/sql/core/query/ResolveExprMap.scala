package molecule.sql.core.query

import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import scala.reflect.ClassTag

trait ResolveExprMap extends ResolveExpr { self: SqlQueryBase with LambdasMap =>

  override protected def resolveAttrMapMan(attr: AttrMapMan): Unit = {
    aritiesAttr()
    attr match {
      case at: AttrMapManID             => mapMan(attr, at.vs, resMapId)
      case at: AttrMapManString         => mapMan(attr, at.vs, resMapString)
      case at: AttrMapManInt            => mapMan(attr, at.vs, resMapInt)
      case at: AttrMapManLong           => mapMan(attr, at.vs, resMapLong)
      case at: AttrMapManFloat          => mapMan(attr, at.vs, resMapFloat)
      case at: AttrMapManDouble         => mapMan(attr, at.vs, resMapDouble)
      case at: AttrMapManBoolean        => mapMan(attr, at.vs, resMapBoolean)
      case at: AttrMapManBigInt         => mapMan(attr, at.vs, resMapBigInt)
      case at: AttrMapManBigDecimal     => mapMan(attr, at.vs, resMapBigDecimal)
      case at: AttrMapManDate           => mapMan(attr, at.vs, resMapDate)
      case at: AttrMapManDuration       => mapMan(attr, at.vs, resMapDuration)
      case at: AttrMapManInstant        => mapMan(attr, at.vs, resMapInstant)
      case at: AttrMapManLocalDate      => mapMan(attr, at.vs, resMapLocalDate)
      case at: AttrMapManLocalTime      => mapMan(attr, at.vs, resMapLocalTime)
      case at: AttrMapManLocalDateTime  => mapMan(attr, at.vs, resMapLocalDateTime)
      case at: AttrMapManOffsetTime     => mapMan(attr, at.vs, resMapOffsetTime)
      case at: AttrMapManOffsetDateTime => mapMan(attr, at.vs, resMapOffsetDateTime)
      case at: AttrMapManZonedDateTime  => mapMan(attr, at.vs, resMapZonedDateTime)
      case at: AttrMapManUUID           => mapMan(attr, at.vs, resMapUUID)
      case at: AttrMapManURI            => mapMan(attr, at.vs, resMapURI)
      case at: AttrMapManByte           => mapMan(attr, at.vs, resMapByte)
      case at: AttrMapManShort          => mapMan(attr, at.vs, resMapShort)
      case at: AttrMapManChar           => mapMan(attr, at.vs, resMapChar)
    }
  }

  override protected def resolveAttrMapTac(attr: AttrMapTac): Unit = {
    attr match {
      case at: AttrMapTacID             => mapTac(attr, at.vs, resMapId)
      case at: AttrMapTacString         => mapTac(attr, at.vs, resMapString)
      case at: AttrMapTacInt            => mapTac(attr, at.vs, resMapInt)
      case at: AttrMapTacLong           => mapTac(attr, at.vs, resMapLong)
      case at: AttrMapTacFloat          => mapTac(attr, at.vs, resMapFloat)
      case at: AttrMapTacDouble         => mapTac(attr, at.vs, resMapDouble)
      case at: AttrMapTacBoolean        => mapTac(attr, at.vs, resMapBoolean)
      case at: AttrMapTacBigInt         => mapTac(attr, at.vs, resMapBigInt)
      case at: AttrMapTacBigDecimal     => mapTac(attr, at.vs, resMapBigDecimal)
      case at: AttrMapTacDate           => mapTac(attr, at.vs, resMapDate)
      case at: AttrMapTacDuration       => mapTac(attr, at.vs, resMapDuration)
      case at: AttrMapTacInstant        => mapTac(attr, at.vs, resMapInstant)
      case at: AttrMapTacLocalDate      => mapTac(attr, at.vs, resMapLocalDate)
      case at: AttrMapTacLocalTime      => mapTac(attr, at.vs, resMapLocalTime)
      case at: AttrMapTacLocalDateTime  => mapTac(attr, at.vs, resMapLocalDateTime)
      case at: AttrMapTacOffsetTime     => mapTac(attr, at.vs, resMapOffsetTime)
      case at: AttrMapTacOffsetDateTime => mapTac(attr, at.vs, resMapOffsetDateTime)
      case at: AttrMapTacZonedDateTime  => mapTac(attr, at.vs, resMapZonedDateTime)
      case at: AttrMapTacUUID           => mapTac(attr, at.vs, resMapUUID)
      case at: AttrMapTacURI            => mapTac(attr, at.vs, resMapURI)
      case at: AttrMapTacByte           => mapTac(attr, at.vs, resMapByte)
      case at: AttrMapTacShort          => mapTac(attr, at.vs, resMapShort)
      case at: AttrMapTacChar           => mapTac(attr, at.vs, resMapChar)
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


  protected def mapMan[T](
    attr: Attr, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isNestedOpt) {
      notNull += col
    }
    addCast(resMap.sqlJson2map)
    attr.op match {
      case V       => ()
      case Has     => key2value(col, map.head._1, resMap)
      case NoValue => noApplyNothing(attr)
      case Eq      => noCollectionMatching(attr)
      case other   => unexpectedOp(other)
    }
  }

  protected def mapOpt[T](
    attr: Attr,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
    resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    groupByCols += col // if we later need to group by non-aggregated columns
    addCast(resMapOpt.sql2optMap)
    attr.op match {
      case V     => ()
      case Has   => key2optValue(col, optMap.get.head._1, resMap)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }

  protected def mapTac[T](
    attr: Attr, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    attr.op match {
      case V       => where += ((col, s"IS NOT NULL"))
      case Eq      => mapContainsKeys(col, map)
      case Neq     => mapContainsNoKeys(col, map)
      case Has     => mapHasValues(col, map, resMap)
      case HasNo   => mapHasNoValues(col, map, resMap)
      case NoValue => mapNoValue(col)
      case other   => unexpectedOp(other)
    }
  }


  // value lookup by key -------------------------------------------------------

  private def key2value[T](col: String, key: String, resMap: ResMap[T]): Unit = {
    val value = s"""($col)."$key""""
    select -= col
    select += value
    where += ((value, s"IS NOT NULL"))
    replaceCast((row: RS, paramIndex: Int) => resMap.json2tpe(row.getString(paramIndex)))
  }

  private def key2optValue[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    select -= col
    select += s"""($col)."$key""""
    replaceCast((row: RS, paramIndex: Int) => {
      val value = row.getString(paramIndex)
      if (row.wasNull()) Option.empty[T] else Some(resMap.json2tpe(value))
    })
  }


  // tacit ---------------------------------------------------------------------

  protected def mapContainsKeys[T](col: String, map: Map[String, T]): Unit = {
    val keys = map.keys
    keys.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", s"""($col)."${keys.head}" IS NOT NULL"""))
      case _ => where += (("", keys.map(key =>
        s"""($col)."$key" IS NOT NULL"""
      ).mkString("(", " OR\n   ", ")")))
    }
  }

  protected def mapContainsNoKeys[T](
    col: String, map: Map[String, T]
  ): Unit = {
    val keys = map.keys
    keys.size match {
      case 0 => () // get all
      case 1 => where += (("", s"""($col)."${keys.head}" IS NULL"""))
      case _ => where += (("", keys.map(key =>
        s"""($col)."$key" IS NULL"""
      ).mkString("(", " AND\n   ", ")")))
    }
  }

  protected def mapHasValues[T](
    col: String, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    if (map.nonEmpty) {
      val values = map.values.map(resMap.one2json)
      where += (("", s"""REGEXP_LIKE($col, '(${regex(resMap.tpe, values)})')"""))
    } else {
      // Get none
      where += (("FALSE", ""))
    }
  }

  protected def mapHasNoValues[T](
    col: String, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    if (map.nonEmpty) {
      val values = map.values.map(resMap.one2json)
      where += (("", s"""NOT(REGEXP_LIKE($col, '(${regex(resMap.tpe, values)})'))"""))
    } else {
      // Get all
      ()
    }
  }

  protected def mapNoValue(col: String): Unit = {
    where += ((col, s"IS NULL"))
  }


  // helpers -------------------------------------------------------------------

  private def regex[T](tpe: String, values: Iterable[T]): String = {
    lazy val field = """"[^"]+""""
    tpe match {
      case "String" => values.mkString(s"$field:", s"|$field:", "")

      case "OffsetTime" | "OffsetDateTime" | "ZonedDateTime" =>
        values.asInstanceOf[Iterable[String]].map(_.replace("+", "\\+")).mkString("|:")

      case _ => values.mkString(":", "|:", "")
    }
  }
}