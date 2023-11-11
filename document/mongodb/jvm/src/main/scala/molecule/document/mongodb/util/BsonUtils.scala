package molecule.document.mongodb.util

import java.net.URI
import java.time._
import java.util
import java.util.{Date, UUID}
import com.mongodb.MongoClientSettings
import molecule.base.ast.{CardOne, CardSet, MetaAttr, MetaNs}
import molecule.base.error.ModelError
import molecule.document.mongodb.transaction.DataType_JVM_mongodb
import org.bson._
import org.bson.conversions.Bson
import org.bson.json.JsonWriterSettings
import org.bson.types.Decimal128

trait BsonUtils extends DataType_JVM_mongodb {

  lazy val pretty = JsonWriterSettings.builder().indent(true).build()

  private lazy val actions = List("insert", "update", "delete")

  def json2data(json: String, nsMap: Map[String, MetaNs]): Data = {
    val bson   = BsonDocument.parse(json)
    val action = bson.getFirstKey
    if (!actions.contains(action))
      throw ModelError(
        """Missing action field "<insert/update/delete>": "<collection>"""" +
          " as first field-value pair in transact json.")

    val collection = bson.get(action).asString.getValue

    val rawRows = {
      bson.get("data") match {
        case null => throw ModelError("""Missing "data": [<rows>] in raw transaction json.""")
        case data => data.asArray
      }
    }


    val rows : util.ArrayList[BsonDocument]        = new util.ArrayList[BsonDocument]()
    val casts: Map[String, BsonValue => BsonValue] = {
      // If the collection name is the namespace name we can map the correct attribute types
      nsMap.get(collection).fold(Map.empty[String, BsonValue => BsonValue]) { metaNs =>
        metaNs.attrs.collect {
          case MetaAttr(attr, CardOne, "ID", Some(_), _, _, _, _, _, _)       => attr -> ((v: BsonValue) => v.asDocument)
          case MetaAttr(attr, CardOne, "ID", _, _, _, _, _, _, _)             => attr -> ((v: BsonValue) => v.asString)
          case MetaAttr(attr, CardOne, "String", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => v.asString)
          case MetaAttr(attr, CardOne, "Int", _, _, _, _, _, _, _)            => attr -> ((v: BsonValue) => v.asInt32)
          case MetaAttr(attr, CardOne, "Long", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v match {case _: BsonInt32 => new BsonInt64(v.asInt32().getValue); case _: BsonInt64 => v})
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
          case MetaAttr(attr, CardSet, "Long", _, _, _, _, _, _, _)           => attr -> {val array = new BsonArray(); (v: BsonValue) => {v.asArray.getValues.forEach(v => array.add(v match { case _: BsonInt32 => new BsonInt64(v.asInt32().getValue); case _: BsonInt64 => v })); array} }
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
      }
    }

    val casted = (field: String, value: BsonValue) => casts.get(field).fold[BsonValue](value)(cast => cast(value))
    rawRows.forEach { row =>
      val fields = row.asDocument().entrySet().iterator()
      val bson   = new BsonDocument()
      while (fields.hasNext) {
        val pair           = fields.next
        val (field, value) = (pair.getKey, pair.getValue)
        bson.put(field, casted(field, value))
      }
      rows.add(bson)
    }
    (collection, rows)
  }


  def data2json(data: Data): String = {
    val (collectionName, documents) = data

    val bson = new BsonDocument()
    bson.put("collection", new BsonString(collectionName))
    val array = new BsonArray(documents)
    bson.put("data", array)

    bson.toJson(pretty)
  }

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


  def caster(metaNs: Option[MetaNs]): (String, BsonValue) => Any = {
    val casts = metaNs.fold(Map.empty[String, BsonValue => Any]) { metaNs =>
      metaNs.attrs.collect {
        case MetaAttr(attr, CardOne, "ID", Some(_), _, _, _, _, _, _)       => attr -> ((v: BsonValue) => v.asDocument.toString)
        case MetaAttr(attr, CardOne, "ID", _, _, _, _, _, _, _)             => attr -> ((v: BsonValue) => v.asString.getValue)
        case MetaAttr(attr, CardOne, "String", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => v.asString.getValue)
        case MetaAttr(attr, CardOne, "Int", _, _, _, _, _, _, _)            => attr -> ((v: BsonValue) => v.asInt32.getValue)
        case MetaAttr(attr, CardOne, "Long", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v.asInt64.getValue)
        case MetaAttr(attr, CardOne, "Float", _, _, _, _, _, _, _)          => attr -> ((v: BsonValue) => v.asDouble.getValue.toFloat)
        case MetaAttr(attr, CardOne, "Double", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => v.asDouble.getValue)
        case MetaAttr(attr, CardOne, "Boolean", _, _, _, _, _, _, _)        => attr -> ((v: BsonValue) => v.asBoolean.getValue)
        case MetaAttr(attr, CardOne, "BigInt", _, _, _, _, _, _, _)         => attr -> ((v: BsonValue) => BigInt(v.asDecimal128.getValue.bigDecimalValue.toBigInteger))
        case MetaAttr(attr, CardOne, "BigDecimal", _, _, _, _, _, _, _)     => attr -> ((v: BsonValue) => BigDecimal(v.asDecimal128.getValue.bigDecimalValue))
        case MetaAttr(attr, CardOne, "Date", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => new Date(v.asDateTime.getValue))
        case MetaAttr(attr, CardOne, "Duration", _, _, _, _, _, _, _)       => attr -> ((v: BsonValue) => Duration.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "Instant", _, _, _, _, _, _, _)        => attr -> ((v: BsonValue) => Instant.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "LocalDate", _, _, _, _, _, _, _)      => attr -> ((v: BsonValue) => LocalDate.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "LocalTime", _, _, _, _, _, _, _)      => attr -> ((v: BsonValue) => LocalTime.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "LocalDateTime", _, _, _, _, _, _, _)  => attr -> ((v: BsonValue) => LocalDateTime.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "OffsetTime", _, _, _, _, _, _, _)     => attr -> ((v: BsonValue) => OffsetTime.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "OffsetDateTime", _, _, _, _, _, _, _) => attr -> ((v: BsonValue) => OffsetDateTime.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "ZonedDateTime", _, _, _, _, _, _, _)  => attr -> ((v: BsonValue) => ZonedDateTime.parse(v.asString.getValue))
        case MetaAttr(attr, CardOne, "UUID", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => UUID.fromString(v.asString.getValue))
        case MetaAttr(attr, CardOne, "URI", _, _, _, _, _, _, _)            => attr -> ((v: BsonValue) => new URI(v.asString.getValue))
        case MetaAttr(attr, CardOne, "Byte", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v.asInt32.getValue.toByte)
        case MetaAttr(attr, CardOne, "Short", _, _, _, _, _, _, _)          => attr -> ((v: BsonValue) => v.asInt32.getValue.toShort)
        case MetaAttr(attr, CardOne, "Char", _, _, _, _, _, _, _)           => attr -> ((v: BsonValue) => v.asString.getValue.charAt(0))

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
