package molecule.document.mongodb.query

import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import molecule.boilerplate.ast.Model._
import molecule.core.query.ResolveExpr
import molecule.document.mongodb.query.mongoModel.{Branch, NestedEmbed, NestedRef}
import org.bson._
import org.bson.conversions.Bson

trait ResolveExprMap extends ResolveExpr with LambdasMap { self: MongoQueryBase =>

  override protected def resolveAttrMapMan(attr: AttrMapMan): Unit = {
    attr match {
      case at: AttrMapManID             => mapMan(attr, at.vs, resMapID)
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
      case at: AttrMapTacID             => mapTac(attr, at.vs, resMapID)
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
    attr match {
      case at: AttrMapOptID             => mapOpt(at, at.vs, resOptMapID)
      case at: AttrMapOptString         => mapOpt(at, at.vs, resOptMapString)
      case at: AttrMapOptInt            => mapOpt(at, at.vs, resOptMapInt)
      case at: AttrMapOptLong           => mapOpt(at, at.vs, resOptMapLong)
      case at: AttrMapOptFloat          => mapOpt(at, at.vs, resOptMapFloat)
      case at: AttrMapOptDouble         => mapOpt(at, at.vs, resOptMapDouble)
      case at: AttrMapOptBoolean        => mapOpt(at, at.vs, resOptMapBoolean)
      case at: AttrMapOptBigInt         => mapOpt(at, at.vs, resOptMapBigInt)
      case at: AttrMapOptBigDecimal     => mapOpt(at, at.vs, resOptMapBigDecimal)
      case at: AttrMapOptDate           => mapOpt(at, at.vs, resOptMapDate)
      case at: AttrMapOptDuration       => mapOpt(at, at.vs, resOptMapDuration)
      case at: AttrMapOptInstant        => mapOpt(at, at.vs, resOptMapInstant)
      case at: AttrMapOptLocalDate      => mapOpt(at, at.vs, resOptMapLocalDate)
      case at: AttrMapOptLocalTime      => mapOpt(at, at.vs, resOptMapLocalTime)
      case at: AttrMapOptLocalDateTime  => mapOpt(at, at.vs, resOptMapLocalDateTime)
      case at: AttrMapOptOffsetTime     => mapOpt(at, at.vs, resOptMapOffsetTime)
      case at: AttrMapOptOffsetDateTime => mapOpt(at, at.vs, resOptMapOffsetDateTime)
      case at: AttrMapOptZonedDateTime  => mapOpt(at, at.vs, resOptMapZonedDateTime)
      case at: AttrMapOptUUID           => mapOpt(at, at.vs, resOptMapUUID)
      case at: AttrMapOptURI            => mapOpt(at, at.vs, resOptMapURI)
      case at: AttrMapOptByte           => mapOpt(at, at.vs, resOptMapByte)
      case at: AttrMapOptShort          => mapOpt(at, at.vs, resOptMapShort)
      case at: AttrMapOptChar           => mapOpt(at, at.vs, resOptMapChar)
    }
  }

  protected def mapMan[T](
    attr: Attr, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)

    prefixedFieldPair = if (b.parent.isEmpty)
      (nestedLevel, field, field)
    else
      (nestedLevel, b.path + field, b.alias + field)
    topBranch.groupIdFields += prefixedFieldPair
    attr.op match {
      case V       => mapAttr(field, resMap, true)
      case Has     => key2value(uniqueField, map.head._1, resMap)
      case NoValue => noApplyNothing(attr)
      case Eq      => noCollectionMatching(attr)
      case other   => unexpectedOp(other)
    }
  }

  protected def mapOpt[T](
    attr: Attr,
    optMap: Option[Map[String, T]],
    resMapOpt: ResMapOpt[T],
  ): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    attr.op match {
      case V     => mapAttrOpt(field, resMapOpt)
      case Has   => key2optValue(uniqueField, optMap.get.head._1, resMapOpt)
      case Eq    => noCollectionMatching(attr)
      case other => unexpectedOp(other)
    }
  }

  protected def mapTac[T](
    attr: Attr, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    val field       = attr.attr
    val uniqueField = b.unique(field)
    val pathField   = b.dot + field

    attr.op match {
      case V       => mapAttr(field, resMap, false)
      case Eq      => mapContainsKeys(pathField, map)
      case Neq     => mapContainsNoKeys(pathField, map)
      case Has     => mapHasValues(pathField, map, resMap)
      case HasNo   => mapHasNoValues(pathField, map, resMap)
      case NoValue => mapNoValue(pathField)
      case other   => unexpectedOp(other)
    }
  }



  // attr ----------------------------------------------------------------------

  private def mapAttr[T](field: String, resMap: ResMap[T], retrieve: Boolean): Unit = {
    val prefix = if (b.isEmbedded) b.dot else ""
    b.matches.add(Filters.ne(prefix + field, new BsonNull))
    b.matches.add(Filters.ne(prefix + field, new BsonDocument()))
    if (retrieve) {
      projectField(field)
      addCast(field, resMap.castMap(field))
    }
  }

  private def mapAttrOpt[T](field: String, resMapOpt: ResMapOpt[T]): Unit = {
    projectField(field)
    addCast(field, resMapOpt.castOptMap(field))
  }


  // value lookup by key -------------------------------------------------------

  protected def key2value[T](
    field: String, key: String, resMap: ResMap[T]
  ): Unit = {
    val fieldKey = field + "." + key
    b.base.matches.add(Filters.ne(b.dot + field, new BsonNull))
    b.base.matches.add(Filters.ne(b.dot + fieldKey, new BsonNull))
    projectField(fieldKey)
    addCast(field, resMap.castValue(field, key))
  }

  protected def key2optValue[T](
    field: String, key: String, resMapOpt: ResMapOpt[T]
  ): Unit = {
    projectField(field + "." + key)
    addCast(field, resMapOpt.castOptValue(field, key))
  }


  // tacit ---------------------------------------------------------------------

  protected def mapContainsKeys[T](
    field: String, map: Map[String, T]
  ): Unit = {
    val prefix = field + "."
    map.size match {
      case 0 => b.base.matches.add(Filters.eq("_id", -1))
      case 1 => b.base.matches.add(Filters.ne(prefix + map.head._1, new BsonNull))
      case _ => b.base.matches.add(Filters.or(
        map.map {
          case (k, v) => Filters.ne(prefix + k, new BsonNull)
        }.asJava
      ))
    }
  }

  protected def mapContainsNoKeys[T](
    field: String, map: Map[String, T]
  ): Unit = {
    val prefix = field + "."
    map.size match {
      case 0 => () // all
      case 1 => b.base.matches.add(Filters.eq(prefix + map.head._1, new BsonNull))
      case _ => b.base.matches.add(Filters.and(
        map.map {
          case (k, v) => Filters.eq(prefix + k, new BsonNull)
        }.asJava
      ))
    }
  }

  protected def mapHasValues[T](
    field: String, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    if (map.isEmpty) {
      b.base.matches.add(Filters.eq("_id", -1))
    } else {
      val value = resMap.v2bson
      b.base.matches.add(
        new BsonDocument("$expr",
          new BsonDocument("$reduce", {
            val reduce = new BsonDocument()
            reduce.put("input", new BsonDocument("$map", {
              val input = new BsonDocument()
              input.put("input", new BsonDocument("$objectToArray", new BsonString("$" + b.path + field)))
              input.put("as", new BsonString("pair"))
              input.put("in", new BsonDocument("$in", {
                val inArray = new BsonArray()
                inArray.add(new BsonString("$$pair.v"))
                inArray.add {
                  val args = new BsonArray()
                  map.foreach {
                    case (k, v) => args.add(value(v))
                  }
                  args
                }
                inArray
              }))
              input
            }))
            reduce.put("initialValue", new BsonBoolean(false))
            reduce.put("in", new BsonDocument("$or", {
              val arr = new BsonArray()
              arr.add(new BsonString("$$value"))
              arr.add(new BsonString("$$this"))
              arr
            }))
            reduce
          })
        )
      )
    }
  }

  protected def mapHasNoValues[T](
    field: String, map: Map[String, T], resMap: ResMap[T]
  ): Unit = {
    if (map.isEmpty) {
      () // get all
    } else {
      val value = resMap.v2bson
      b.base.matches.add(
        new BsonDocument("$expr",
          new BsonDocument("$reduce", {
            val reduce = new BsonDocument()
            reduce.put("input", new BsonDocument("$map", {
              val input = new BsonDocument()
              input.put("input", new BsonDocument("$objectToArray", new BsonString("$" + b.path + field)))
              input.put("as", new BsonString("pair"))
              input.put("in", new BsonDocument("$not",
                new BsonDocument("$in", {
                  val inArray = new BsonArray()
                  inArray.add(new BsonString("$$pair.v"))
                  inArray.add {
                    val args = new BsonArray()
                    map.foreach {
                      case (k, v) => args.add(value(v))
                    }
                    args
                  }
                  inArray
                })))
              input
            }))
            reduce.put("initialValue", new BsonBoolean(true))
            reduce.put("in", new BsonDocument("$and", {
              val arr = new BsonArray()
              arr.add(new BsonString("$$value"))
              arr.add(new BsonString("$$this"))
              arr
            }))
            reduce
          })
        )
      )
    }
  }

  protected def mapNoValue(field: String): Unit = {
    b.base.matches.add(Filters.eq(b.dot + field, new BsonNull))
  }
}
