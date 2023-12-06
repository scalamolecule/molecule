package molecule.document.mongodb.util

import java.net.URI
import java.time._
import java.util
import java.util.{Date, UUID}
import com.mongodb.MongoClientSettings
import molecule.base.ast.{CardOne, CardSet, MetaAttr, MetaNs}
import molecule.base.error.ModelError
import molecule.document.mongodb.transaction.DataType_JVM_mongodb
import org.bson.{BsonString, _}
import org.bson.conversions.Bson
import org.bson.json.JsonWriterSettings
import org.bson.types.Decimal128

trait BsonUtils extends DataType_JVM_mongodb {

  lazy val pretty = JsonWriterSettings.builder().indent(true).build()

  private lazy val actions = List("insert", "update", "delete")

  def json2data(json: String, nsMap: Map[String, MetaNs]): Data = {
    val bson = BsonDocument.parse(json)

    val firstKey = bson.getFirstKey
    if (firstKey != "action") {
      throw ModelError(
        """Missing field "action": "<insert/update/delete>" as first field-value pair in transaction json.""""
      )
    }

    val data = new BsonDocument()
    bson.get("action") match {
      case null =>
        throw ModelError("""Missing "action": "<insert/update/delete>" in raw transaction json.""")

      case v: BsonString =>
        val action = v.getValue
        if (!actions.contains(action)) {
          throw ModelError("Action can only be: insert, update or delete. Found: " + action)
        }
        action match {
          case "insert" =>
            bson.forEach {
              case ("action", value) => data.append("action", value) // leave as-is
              case (ns, rawRows)     =>
                val metaAttrs = nsMap.get(ns).fold(throw ModelError(s"Couldn't find namespace ´$ns´"))(_.attrs)
                data.append(ns, castInsert(rawRows.asArray, metaAttrs))
            }

          case "update" => ???
          case "delete" => ???
        }

      case other => throw ModelError("Action can only be: insert, update or delete. Found: " + other)
    }
    data
  }

  private def castInsert(rawRows: BsonArray, metaAttrs: Seq[MetaAttr]): BsonArray = {
    val casts: Map[String, BsonValue => BsonValue] = metaAttrs.collect {
      //      case MetaAttr(attr, CardOne, "ID", Some(_), options, _, _, _, _, _) if !options.contains("owner") =>
      case MetaAttr(attr, CardOne, "ID", Some(_), options, _, _, _, _, _) if options.contains("owner") =>
        attr -> ((v: BsonValue) => v.asDocument)

      case MetaAttr(attr, CardOne, "ID", _, _, _, _, _, _, _)             => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "String", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "Int", _, _, _, _, _, _, _)            => attr -> ((v: BsonValue) => v.asInt32)
      case MetaAttr(attr, CardOne, "Long", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v match {case v: BsonInt32 => new BsonInt64(v.getValue); case _: BsonInt64 => v})
      case MetaAttr(attr, CardOne, "Float", _, _, _, _, _, _, _)          => attr -> ((v: BsonValue) => v.asDouble)
      case MetaAttr(attr, CardOne, "Double", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => v.asDouble)
      case MetaAttr(attr, CardOne, "Boolean", _, _, _, _, _, _, _)        => attr -> ((v: BsonValue) => v.asBoolean)
      case MetaAttr(attr, CardOne, "BigInt", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => new BsonDecimal128(Decimal128.parse(v.asString().getValue)))
      case MetaAttr(attr, CardOne, "BigDecimal", _, _, _, _, _, _, _)     => attr -> ((v: BsonValue) => new BsonDecimal128(Decimal128.parse(v.asString().getValue)))
      case MetaAttr(attr, CardOne, "Date", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => new BsonDateTime(v.asInt64.getValue))
      case MetaAttr(attr, CardOne, "Duration", _, _, _, _, _, _, _)       => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "Instant", _, _, _, _, _, _, _)        => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "LocalDate", _, _, _, _, _, _, _)      => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "LocalTime", _, _, _, _, _, _, _)      => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "LocalDateTime", _, _, _, _, _, _, _)  => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "OffsetTime", _, _, _, _, _, _, _)     => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "OffsetDateTime", _, _, _, _, _, _, _) => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "ZonedDateTime", _, _, _, _, _, _, _)  => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "UUID", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "URI", _, _, _, _, _, _, _)            => attr -> ((v: BsonValue) => v.asString)
      case MetaAttr(attr, CardOne, "Byte", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v.asInt32)
      case MetaAttr(attr, CardOne, "Short", _, _, _, _, _, _, _)          => attr -> ((v: BsonValue) => v.asInt32)
      case MetaAttr(attr, CardOne, "Char", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v.asString)

      case MetaAttr(attr, CardSet, "ID", _, _, _, _, _, _, _)             => attr -> {
        (v: BsonValue) => {
          val array = new BsonArray()
          val vs    = v.asArray.getValues
          if (!vs.get(0).isDocument) {
            throw ModelError("Can't add non-existing ids of embedded documents in MongoDB. " +
              "Please save embedded document together with main document.")
          }
          v.asArray.getValues.forEach(v =>
            array.add(v.asDocument)
          )
          array
        }
      }
      case MetaAttr(attr, CardSet, "String", _, _, _, _, _, _, _)         => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "Int", _, _, _, _, _, _, _)            => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asInt32)); array} }
      case MetaAttr(attr, CardSet, "Long", _, _, _, _, _, _, _)           => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v match { case v: BsonInt32 => new BsonInt64(v.getValue); case _: BsonInt64 => v })); array} }
      case MetaAttr(attr, CardSet, "Float", _, _, _, _, _, _, _)          => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asDouble)); array} }
      case MetaAttr(attr, CardSet, "Double", _, _, _, _, _, _, _)         => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asDouble)); array} }
      case MetaAttr(attr, CardSet, "Boolean", _, _, _, _, _, _, _)        => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asBoolean)); array} }
      case MetaAttr(attr, CardSet, "BigInt", _, _, _, _, _, _, _)         => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(new BsonDecimal128(Decimal128.parse(v.asString().getValue)))); array} }
      case MetaAttr(attr, CardSet, "BigDecimal", _, _, _, _, _, _, _)     => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(new BsonDecimal128(Decimal128.parse(v.asString().getValue)))); array} }
      case MetaAttr(attr, CardSet, "Date", _, _, _, _, _, _, _)           => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(new BsonDateTime(v.asInt64.getValue))); array} }
      case MetaAttr(attr, CardSet, "Duration", _, _, _, _, _, _, _)       => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "Instant", _, _, _, _, _, _, _)        => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "LocalDate", _, _, _, _, _, _, _)      => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "LocalTime", _, _, _, _, _, _, _)      => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "LocalDateTime", _, _, _, _, _, _, _)  => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "OffsetTime", _, _, _, _, _, _, _)     => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "OffsetDateTime", _, _, _, _, _, _, _) => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "ZonedDateTime", _, _, _, _, _, _, _)  => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "UUID", _, _, _, _, _, _, _)           => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "URI", _, _, _, _, _, _, _)            => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
      case MetaAttr(attr, CardSet, "Byte", _, _, _, _, _, _, _)           => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asInt32)); array} }
      case MetaAttr(attr, CardSet, "Short", _, _, _, _, _, _, _)          => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asInt32)); array} }
      case MetaAttr(attr, CardSet, "Char", _, _, _, _, _, _, _)           => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v.asString)); array} }
    }.toMap

    val casted = (field: String, value: BsonValue) => casts.get(field).fold[BsonValue](value)(cast => cast(value))
    val rows   = new BsonArray
    rawRows.forEach { rawRow =>
      val doc = new BsonDocument()
      rawRow.asDocument.forEach { case (k, v) =>
        doc.append(k, casted(k, v))
      }
      rows.add(doc)
    }
    rows
  }


  //  def data2json(data: Data): String = {
  //    val (collectionName, documents) = data
  //
  //    val bson = new BsonDocument()
  //    bson.put("collection", new BsonString(collectionName))
  //    val array = new BsonArray(documents)
  //    bson.put("data", array)
  //
  //    bson.toJson(pretty)
  //  }

  def pipeline2json(pipeline: util.ArrayList[Bson], optCol: Option[String] = None): String = {
    val doc = new BsonDocument()
    optCol.fold(
      throw ModelError("""Missing "collection": "<collection name>" in raw query json.""")
    )(col =>
      doc.append("collection", new BsonString(col))
    )
    val array = new BsonArray()
    pipeline.forEach { stage =>
      array.add(stage.toBsonDocument(classOf[Bson], MongoClientSettings.getDefaultCodecRegistry))
    }
    doc.append("pipeline", array)
    doc.toJson(pretty)
  }

  def json2pipeline(json: String): (String, util.ArrayList[Bson]) = {
    val doc                   = BsonDocument.parse(json)
    val collection: String    = doc.get("collection") match {
      case null => throw ModelError("""Missing "collection": "<collection name>" in raw query json.""")
      case col  => col.asString.getValue
    }
    val bsonArray : BsonArray = doc.get("pipeline") match {
      case null  => throw ModelError("""Missing "pipeline": [ <stages...> ] in raw query json.""")
      case array => array.asArray()
    }
    val pipeline              = new util.ArrayList[Bson]()
    val it                    = bsonArray.iterator
    while (it.hasNext) {
      pipeline.add(it.next.asDocument)
    }
    (collection, pipeline)
  }


  private lazy val castID            : BsonValue => Any = (v: BsonValue) => v.asString.getValue
  private lazy val castString        : BsonValue => Any = (v: BsonValue) => v.asString.getValue
  private lazy val castInt           : BsonValue => Any = (v: BsonValue) => v.asInt32.getValue
  private lazy val castLong          : BsonValue => Any = (v: BsonValue) => v.asInt64.getValue
  private lazy val castFloat         : BsonValue => Any = (v: BsonValue) => v.asDouble.getValue.toFloat
  private lazy val castDouble        : BsonValue => Any = (v: BsonValue) => v.asDouble.getValue
  private lazy val castBoolean       : BsonValue => Any = (v: BsonValue) => v.asBoolean.getValue
  private lazy val castBigInt        : BsonValue => Any = (v: BsonValue) => BigInt(v.asDecimal128.getValue.bigDecimalValue.toBigInteger)
  private lazy val castBigDecimal    : BsonValue => Any = (v: BsonValue) => BigDecimal(v.asDecimal128.getValue.bigDecimalValue)
  private lazy val castDate          : BsonValue => Any = (v: BsonValue) => new Date(v.asDateTime.getValue)
  private lazy val castDuration      : BsonValue => Any = (v: BsonValue) => Duration.parse(v.asString.getValue)
  private lazy val castInstant       : BsonValue => Any = (v: BsonValue) => Instant.parse(v.asString.getValue)
  private lazy val castLocalDate     : BsonValue => Any = (v: BsonValue) => LocalDate.parse(v.asString.getValue)
  private lazy val castLocalTime     : BsonValue => Any = (v: BsonValue) => LocalTime.parse(v.asString.getValue)
  private lazy val castLocalDateTime : BsonValue => Any = (v: BsonValue) => LocalDateTime.parse(v.asString.getValue)
  private lazy val castOffsetTime    : BsonValue => Any = (v: BsonValue) => OffsetTime.parse(v.asString.getValue)
  private lazy val castOffsetDateTime: BsonValue => Any = (v: BsonValue) => OffsetDateTime.parse(v.asString.getValue)
  private lazy val castZonedDateTime : BsonValue => Any = (v: BsonValue) => ZonedDateTime.parse(v.asString.getValue)
  private lazy val castUUID          : BsonValue => Any = (v: BsonValue) => UUID.fromString(v.asString.getValue)
  private lazy val castURI           : BsonValue => Any = (v: BsonValue) => new URI(v.asString.getValue)
  private lazy val castByte          : BsonValue => Any = (v: BsonValue) => v.asInt32.getValue.toByte
  private lazy val castShort         : BsonValue => Any = (v: BsonValue) => v.asInt32.getValue.toShort
  private lazy val castChar          : BsonValue => Any = (v: BsonValue) => v.asString.getValue.charAt(0)


  private lazy val castAnyID            : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castID(v)); set} else castID(v)
  private lazy val castAnyString        : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castString(v)); set} else castString(v)
  private lazy val castAnyInt           : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castInt(v)); set} else castInt(v)
  private lazy val castAnyLong          : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castLong(v)); set} else castLong(v)
  private lazy val castAnyFloat         : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castFloat(v)); set} else castFloat(v)
  private lazy val castAnyDouble        : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castDouble(v)); set} else castDouble(v)
  private lazy val castAnyBoolean       : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castBoolean(v)); set} else castBoolean(v)
  private lazy val castAnyBigInt        : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castBigInt(v)); set} else castBigInt(v)
  private lazy val castAnyBigDecimal    : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castBigDecimal(v)); set} else castBigDecimal(v)
  private lazy val castAnyDate          : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castDate(v)); set} else castDate(v)
  private lazy val castAnyDuration      : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castDuration(v)); set} else castDuration(v)
  private lazy val castAnyInstant       : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castInstant(v)); set} else castInstant(v)
  private lazy val castAnyLocalDate     : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castLocalDate(v)); set} else castLocalDate(v)
  private lazy val castAnyLocalTime     : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castLocalTime(v)); set} else castLocalTime(v)
  private lazy val castAnyLocalDateTime : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castLocalDateTime(v)); set} else castLocalDateTime(v)
  private lazy val castAnyOffsetTime    : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castOffsetTime(v)); set} else castOffsetTime(v)
  private lazy val castAnyOffsetDateTime: BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castOffsetDateTime(v)); set} else castOffsetDateTime(v)
  private lazy val castAnyZonedDateTime : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castZonedDateTime(v)); set} else castZonedDateTime(v)
  private lazy val castAnyUUID          : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castUUID(v)); set} else castUUID(v)
  private lazy val castAnyURI           : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castURI(v)); set} else castURI(v)
  private lazy val castAnyByte          : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castByte(v)); set} else castByte(v)
  private lazy val castAnyShort         : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castShort(v)); set} else castShort(v)
  private lazy val castAnyChar          : BsonValue => Any = (v: BsonValue) => if (v.isArray) {var set = Set.empty[Any]; v.asArray.forEach(v => set += castChar(v)); set} else castChar(v)

  def caster(metaNs: Option[MetaNs]): (String, BsonValue) => Any = {
    val casts = metaNs.fold(Map.empty[String, BsonValue => Any]) { metaNs =>
      metaNs.attrs.collect {
        //        case MetaAttr(attr, CardOne, "ID", Some(_), options, _, _, _, _, _) if options.contains("owner") =>
        case MetaAttr(attr, CardOne, "ID", Some(_), _, _, _, _, _, _)       => attr -> ((v: BsonValue) => v.asDocument.toString) // Always embedded output
        case MetaAttr(attr, CardOne, "ID", _, _, _, _, _, _, _)             => attr -> ((v: BsonValue) => castAnyID(v))
        case MetaAttr(attr, CardOne, "String", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => castAnyString(v))
        case MetaAttr(attr, CardOne, "Int", _, _, _, _, _, _, _)            => attr -> ((v: BsonValue) => castAnyInt(v))
        case MetaAttr(attr, CardOne, "Long", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => castAnyLong(v))
        case MetaAttr(attr, CardOne, "Float", _, _, _, _, _, _, _)          => attr -> ((v: BsonValue) => castAnyFloat(v))
        case MetaAttr(attr, CardOne, "Double", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => castAnyDouble(v))
        case MetaAttr(attr, CardOne, "Boolean", _, _, _, _, _, _, _)        => attr -> ((v: BsonValue) => castAnyBoolean(v))
        case MetaAttr(attr, CardOne, "BigInt", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => castAnyBigInt(v))
        case MetaAttr(attr, CardOne, "BigDecimal", _, _, _, _, _, _, _)     => attr -> ((v: BsonValue) => castAnyBigDecimal(v))
        case MetaAttr(attr, CardOne, "Date", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => castAnyDate(v))
        case MetaAttr(attr, CardOne, "Duration", _, _, _, _, _, _, _)       => attr -> ((v: BsonValue) => castAnyDuration(v))
        case MetaAttr(attr, CardOne, "Instant", _, _, _, _, _, _, _)        => attr -> ((v: BsonValue) => castAnyInstant(v))
        case MetaAttr(attr, CardOne, "LocalDate", _, _, _, _, _, _, _)      => attr -> ((v: BsonValue) => castAnyLocalDate(v))
        case MetaAttr(attr, CardOne, "LocalTime", _, _, _, _, _, _, _)      => attr -> ((v: BsonValue) => castAnyLocalTime(v))
        case MetaAttr(attr, CardOne, "LocalDateTime", _, _, _, _, _, _, _)  => attr -> ((v: BsonValue) => castAnyLocalDateTime(v))
        case MetaAttr(attr, CardOne, "OffsetTime", _, _, _, _, _, _, _)     => attr -> ((v: BsonValue) => castAnyOffsetTime(v))
        case MetaAttr(attr, CardOne, "OffsetDateTime", _, _, _, _, _, _, _) => attr -> ((v: BsonValue) => castAnyOffsetDateTime(v))
        case MetaAttr(attr, CardOne, "ZonedDateTime", _, _, _, _, _, _, _)  => attr -> ((v: BsonValue) => castAnyZonedDateTime(v))
        case MetaAttr(attr, CardOne, "UUID", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => castAnyUUID(v))
        case MetaAttr(attr, CardOne, "URI", _, _, _, _, _, _, _)            => attr -> ((v: BsonValue) => castAnyURI(v))
        case MetaAttr(attr, CardOne, "Byte", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => castAnyByte(v))
        case MetaAttr(attr, CardOne, "Short", _, _, _, _, _, _, _)          => attr -> ((v: BsonValue) => castAnyShort(v))
        case MetaAttr(attr, CardOne, "Char", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => castAnyChar(v))

        case MetaAttr(attr, CardSet, "ID", _, _, _, _, _, _, _)             => attr -> {
          var set = Set.empty[String]
          (v: BsonValue) => {
            //            v.asArray.getValues.forEach(v => set += v.asString.getValue)
            //            v.asDocument().getValues.forEach(v => set += v.asString.getValue)
            set
          }
        }
        case MetaAttr(attr, CardSet, "String", _, _, _, _, _, _, _)         => attr -> {var set = Set.empty[String]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asString.getValue); set} }
        case MetaAttr(attr, CardSet, "Int", _, _, _, _, _, _, _)            => attr -> {var set = Set.empty[Int]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asInt32.getValue); set} }
        case MetaAttr(attr, CardSet, "Long", _, _, _, _, _, _, _)           => attr -> {var set = Set.empty[Long]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asInt64.getValue); set} }
        case MetaAttr(attr, CardSet, "Float", _, _, _, _, _, _, _)          => attr -> {var set = Set.empty[Float]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asDouble.getValue.toFloat); set} }
        case MetaAttr(attr, CardSet, "Double", _, _, _, _, _, _, _)         => attr -> {var set = Set.empty[Double]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asDouble.getValue); set} }
        case MetaAttr(attr, CardSet, "Boolean", _, _, _, _, _, _, _)        => attr -> {var set = Set.empty[Boolean]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asBoolean.getValue); set} }
        case MetaAttr(attr, CardSet, "BigInt", _, _, _, _, _, _, _)         => attr -> {var set = Set.empty[BigInt]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += BigInt(v.asDecimal128.getValue.bigDecimalValue.toBigInteger)); set} }
        case MetaAttr(attr, CardSet, "BigDecimal", _, _, _, _, _, _, _)     => attr -> {var set = Set.empty[BigDecimal]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += BigDecimal(v.asDecimal128.getValue.bigDecimalValue)); set} }
        case MetaAttr(attr, CardSet, "Date", _, _, _, _, _, _, _)           => attr -> {var set = Set.empty[Date]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += new Date(v.asDateTime.getValue)); set} }
        case MetaAttr(attr, CardSet, "Duration", _, _, _, _, _, _, _)       => attr -> {var set = Set.empty[Duration]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += Duration.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "Instant", _, _, _, _, _, _, _)        => attr -> {var set = Set.empty[Instant]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += Instant.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "LocalDate", _, _, _, _, _, _, _)      => attr -> {var set = Set.empty[LocalDate]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += LocalDate.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "LocalTime", _, _, _, _, _, _, _)      => attr -> {var set = Set.empty[LocalTime]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += LocalTime.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "LocalDateTime", _, _, _, _, _, _, _)  => attr -> {var set = Set.empty[LocalDateTime]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += LocalDateTime.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "OffsetTime", _, _, _, _, _, _, _)     => attr -> {var set = Set.empty[OffsetTime]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += OffsetTime.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "OffsetDateTime", _, _, _, _, _, _, _) => attr -> {var set = Set.empty[OffsetDateTime]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += OffsetDateTime.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "ZonedDateTime", _, _, _, _, _, _, _)  => attr -> {var set = Set.empty[ZonedDateTime]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += ZonedDateTime.parse(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "UUID", _, _, _, _, _, _, _)           => attr -> {var set = Set.empty[UUID]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += UUID.fromString(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "URI", _, _, _, _, _, _, _)            => attr -> {var set = Set.empty[URI]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += new URI(v.asString.getValue)); set} }
        case MetaAttr(attr, CardSet, "Byte", _, _, _, _, _, _, _)           => attr -> {var set = Set.empty[Byte]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asInt32.getValue.toByte); set} }
        case MetaAttr(attr, CardSet, "Short", _, _, _, _, _, _, _)          => attr -> {var set = Set.empty[Short]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asInt32.getValue.toShort); set} }
        case MetaAttr(attr, CardSet, "Char", _, _, _, _, _, _, _)           => attr -> {var set = Set.empty[Char]; (v: BsonValue) => {v.asArray.getValues.forEach(v => set += v.asString.getValue.charAt(0)); set} }
      }.toMap
    }
    def genericType(bson: BsonValue): Any = bson match {
      case v: BsonObjectId   => v.getValue
      case v: BsonString     => v.getValue
      case v: BsonInt32      => v.asInt32.getValue
      case v: BsonInt64      => v.asInt64.getValue
      case v: BsonDouble     => v.asDouble.getValue
      case v: BsonBoolean    => v.asBoolean.getValue
      case v: BsonDecimal128 => BigDecimal(v.asDecimal128.getValue.bigDecimalValue)
      case v: BsonDateTime   => new Date(v.asDateTime.getValue)
      case v: BsonDocument   => v.toJson // we could recurse entire data model...
      case v: BsonArray      =>
        var set = Set.empty[Any]
        v.getValues.forEach(v => set += genericType(v))
        set
      case v                 => v
    }

    (field: String, value: BsonValue) => casts.get(field).fold[Any](genericType(value))(cast => cast(value))
  }
}
