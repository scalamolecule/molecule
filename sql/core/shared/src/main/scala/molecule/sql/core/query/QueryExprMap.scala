package molecule.sql.core.query

import molecule.boilerplate.ast.Model._
import molecule.core.query.{Model2Query, QueryExpr}

trait QueryExprMap extends QueryExpr { self: Model2Query with SqlQueryBase with LambdasMap =>

  override protected def queryAttrMapMan(attr: AttrMapMan): Unit = {
    attr match {
      case at: AttrMapManID             => mapMan(attr, at.keys, resMapId)
      case at: AttrMapManString         => mapMan(attr, at.keys, resMapString)
      case at: AttrMapManInt            => mapMan(attr, at.keys, resMapInt)
      case at: AttrMapManLong           => mapMan(attr, at.keys, resMapLong)
      case at: AttrMapManFloat          => mapMan(attr, at.keys, resMapFloat)
      case at: AttrMapManDouble         => mapMan(attr, at.keys, resMapDouble)
      case at: AttrMapManBoolean        => mapMan(attr, at.keys, resMapBoolean)
      case at: AttrMapManBigInt         => mapMan(attr, at.keys, resMapBigInt)
      case at: AttrMapManBigDecimal     => mapMan(attr, at.keys, resMapBigDecimal)
      case at: AttrMapManDate           => mapMan(attr, at.keys, resMapDate)
      case at: AttrMapManDuration       => mapMan(attr, at.keys, resMapDuration)
      case at: AttrMapManInstant        => mapMan(attr, at.keys, resMapInstant)
      case at: AttrMapManLocalDate      => mapMan(attr, at.keys, resMapLocalDate)
      case at: AttrMapManLocalTime      => mapMan(attr, at.keys, resMapLocalTime)
      case at: AttrMapManLocalDateTime  => mapMan(attr, at.keys, resMapLocalDateTime)
      case at: AttrMapManOffsetTime     => mapMan(attr, at.keys, resMapOffsetTime)
      case at: AttrMapManOffsetDateTime => mapMan(attr, at.keys, resMapOffsetDateTime)
      case at: AttrMapManZonedDateTime  => mapMan(attr, at.keys, resMapZonedDateTime)
      case at: AttrMapManUUID           => mapMan(attr, at.keys, resMapUUID)
      case at: AttrMapManURI            => mapMan(attr, at.keys, resMapURI)
      case at: AttrMapManByte           => mapMan(attr, at.keys, resMapByte)
      case at: AttrMapManShort          => mapMan(attr, at.keys, resMapShort)
      case at: AttrMapManChar           => mapMan(attr, at.keys, resMapChar)
    }
  }

  override protected def queryAttrMapTac(attr: AttrMapTac): Unit = {
    attr match {
      case at: AttrMapTacID             => mapTac(attr, at.keys, at.values, resMapId)
      case at: AttrMapTacString         => mapTac(attr, at.keys, at.values, resMapString)
      case at: AttrMapTacInt            => mapTac(attr, at.keys, at.values, resMapInt)
      case at: AttrMapTacLong           => mapTac(attr, at.keys, at.values, resMapLong)
      case at: AttrMapTacFloat          => mapTac(attr, at.keys, at.values, resMapFloat)
      case at: AttrMapTacDouble         => mapTac(attr, at.keys, at.values, resMapDouble)
      case at: AttrMapTacBoolean        => mapTac(attr, at.keys, at.values, resMapBoolean)
      case at: AttrMapTacBigInt         => mapTac(attr, at.keys, at.values, resMapBigInt)
      case at: AttrMapTacBigDecimal     => mapTac(attr, at.keys, at.values, resMapBigDecimal)
      case at: AttrMapTacDate           => mapTac(attr, at.keys, at.values, resMapDate)
      case at: AttrMapTacDuration       => mapTac(attr, at.keys, at.values, resMapDuration)
      case at: AttrMapTacInstant        => mapTac(attr, at.keys, at.values, resMapInstant)
      case at: AttrMapTacLocalDate      => mapTac(attr, at.keys, at.values, resMapLocalDate)
      case at: AttrMapTacLocalTime      => mapTac(attr, at.keys, at.values, resMapLocalTime)
      case at: AttrMapTacLocalDateTime  => mapTac(attr, at.keys, at.values, resMapLocalDateTime)
      case at: AttrMapTacOffsetTime     => mapTac(attr, at.keys, at.values, resMapOffsetTime)
      case at: AttrMapTacOffsetDateTime => mapTac(attr, at.keys, at.values, resMapOffsetDateTime)
      case at: AttrMapTacZonedDateTime  => mapTac(attr, at.keys, at.values, resMapZonedDateTime)
      case at: AttrMapTacUUID           => mapTac(attr, at.keys, at.values, resMapUUID)
      case at: AttrMapTacURI            => mapTac(attr, at.keys, at.values, resMapURI)
      case at: AttrMapTacByte           => mapTac(attr, at.keys, at.values, resMapByte)
      case at: AttrMapTacShort          => mapTac(attr, at.keys, at.values, resMapShort)
      case at: AttrMapTacChar           => mapTac(attr, at.keys, at.values, resMapChar)
    }
  }

  override protected def queryAttrMapOpt(attr: AttrMapOpt): Unit = {
    attr match {
      case at: AttrMapOptID             => mapOpt(at, at.keys, resOptMapId, resMapId)
      case at: AttrMapOptString         => mapOpt(at, at.keys, resOptMapString, resMapString)
      case at: AttrMapOptInt            => mapOpt(at, at.keys, resOptMapInt, resMapInt)
      case at: AttrMapOptLong           => mapOpt(at, at.keys, resOptMapLong, resMapLong)
      case at: AttrMapOptFloat          => mapOpt(at, at.keys, resOptMapFloat, resMapFloat)
      case at: AttrMapOptDouble         => mapOpt(at, at.keys, resOptMapDouble, resMapDouble)
      case at: AttrMapOptBoolean        => mapOpt(at, at.keys, resOptMapBoolean, resMapBoolean)
      case at: AttrMapOptBigInt         => mapOpt(at, at.keys, resOptMapBigInt, resMapBigInt)
      case at: AttrMapOptBigDecimal     => mapOpt(at, at.keys, resOptMapBigDecimal, resMapBigDecimal)
      case at: AttrMapOptDate           => mapOpt(at, at.keys, resOptMapDate, resMapDate)
      case at: AttrMapOptDuration       => mapOpt(at, at.keys, resOptMapDuration, resMapDuration)
      case at: AttrMapOptInstant        => mapOpt(at, at.keys, resOptMapInstant, resMapInstant)
      case at: AttrMapOptLocalDate      => mapOpt(at, at.keys, resOptMapLocalDate, resMapLocalDate)
      case at: AttrMapOptLocalTime      => mapOpt(at, at.keys, resOptMapLocalTime, resMapLocalTime)
      case at: AttrMapOptLocalDateTime  => mapOpt(at, at.keys, resOptMapLocalDateTime, resMapLocalDateTime)
      case at: AttrMapOptOffsetTime     => mapOpt(at, at.keys, resOptMapOffsetTime, resMapOffsetTime)
      case at: AttrMapOptOffsetDateTime => mapOpt(at, at.keys, resOptMapOffsetDateTime, resMapOffsetDateTime)
      case at: AttrMapOptZonedDateTime  => mapOpt(at, at.keys, resOptMapZonedDateTime, resMapZonedDateTime)
      case at: AttrMapOptUUID           => mapOpt(at, at.keys, resOptMapUUID, resMapUUID)
      case at: AttrMapOptURI            => mapOpt(at, at.keys, resOptMapURI, resMapURI)
      case at: AttrMapOptByte           => mapOpt(at, at.keys, resOptMapByte, resMapByte)
      case at: AttrMapOptShort          => mapOpt(at, at.keys, resOptMapShort, resMapShort)
      case at: AttrMapOptChar           => mapOpt(at, at.keys, resOptMapChar, resMapChar)
    }
  }


  protected def mapMan[T](
    attr: Attr, keys: Seq[String], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    if (!isOptNested) {
      setNotNull(col)
    }
    casts.add(resMap.sqlJson2map)
    attr.op match {
      case V       => ()
      case Has     => key2value(col, keys.head, resMap)
      case NoValue => noApplyNothing(attr)
      case Eq      => noCollectionMatching(attr)
      case other   => unexpectedOp(other)
    }
  }

  protected def mapTac[T](
    attr: Attr, keys: Seq[String], values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    attr.op match {
      case V       => where += ((col, s"IS NOT NULL"))
      case Eq      => mapContainsKeys(col, keys)
      case Neq     => mapContainsNoKeys(col, keys)
      case Has     => mapHasValues(col, values, resMap)
      case HasNo   => mapHasNoValues(col, values, resMap)
      case NoValue => mapNoValue(col)
      case other   => unexpectedOp(other)
    }
  }

  protected def mapOpt[T](
    attr: Attr, keys: Seq[String], resMapOpt: ResMapOpt[T], resMap: ResMap[T]
  ): Unit = {
    val col = getCol(attr: Attr)
    select += col
    casts.add(resMapOpt.sql2optMap)
    attr.op match {
      case V     => ()
      case Has   => key2optValue(col, keys.head, resMap)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }


  // value lookup by key -------------------------------------------------------

  protected def key2value[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    val value = s"""($col)."$key""""
    select -= col
    select += value
    where += ((value, s"IS NOT NULL"))
    casts.replace((row: RS, paramIndex: Int) =>
      resMap.json2tpe(row.getString(paramIndex))
    )
  }

  protected def key2optValue[T](
    col: String, key: String, resMap: ResMap[T]
  ): Unit = {
    select -= col
    select += s"""($col)."$key""""
    casts.replace((row: RS, paramIndex: Int) => {
      val value = row.getString(paramIndex)
      if (row.wasNull()) Option.empty[T] else Some(resMap.json2tpe(value))
    })
  }


  // tacit ---------------------------------------------------------------------

  protected def mapContainsKeys(
    col: String, keys: Seq[String]
  ): Unit = {
    keys.size match {
      case 0 => where += (("FALSE", ""))
      case 1 => where += (("", s"""($col)."${keys.head}" IS NOT NULL"""))
      case _ => where += (("", keys.map(key =>
        s"""($col)."$key" IS NOT NULL"""
      ).mkString("(", " OR\n   ", ")")))
    }
  }

  protected def mapContainsNoKeys(
    col: String, keys: Seq[String]
  ): Unit = {
    keys.size match {
      case 0 => () // get all
      case 1 => where += (("", s"""($col)."${keys.head}" IS NULL"""))
      case _ => where += (("", keys.map(key =>
        s"""($col)."$key" IS NULL"""
      ).mkString("(", " AND\n   ", ")")))
    }
  }

  protected def mapHasValues[T](
    col: String, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    if (values.nonEmpty) {
      val values1 = values.map(resMap.one2json)
      where += (("", s"""REGEXP_LIKE($col, '(${regex(resMap.tpe, values1)})')"""))
    } else {
      // Get none
      where += (("FALSE", ""))
    }
  }

  protected def mapHasNoValues[T](
    col: String, values: Seq[T], resMap: ResMap[T]
  ): Unit = {
    if (values.nonEmpty) {
      val values1 = values.map(resMap.one2json)
      where += (("", s"""NOT(REGEXP_LIKE($col, '(${regex(resMap.tpe, values1)})'))"""))
    } else {
      // Get all
      ()
    }
  }

  protected def mapNoValue(col: String): Unit = {
    setNull(col)
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