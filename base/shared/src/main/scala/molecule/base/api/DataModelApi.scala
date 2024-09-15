package molecule.base.api

import java.net.URI
import java.time._
import java.util.{Date, UUID}

/** Data Model DSL.
 * <br><br>
 * Define a Domain Data Model in a data model file.
 * <br><br>
 * For small projects, the schema can be defined without partition definitions where
 * all namespaces reside in a default tacit partition:
 * {{{
 * package path.to.your.project
 * import molecule.data.model._       // import data model DSL
 *
 * object Seattle extends DataModel(8) {  // data model object with input/output arity
 *
 *   trait Person {                   // Namespace
 *     val name = oneString.fulltext  // String attribute definition with fulltext search
 *     val age  = oneInt              // Int attribute definition
 *   }
 *
 *   // Additional namespaces...
 * }
 * }}}
 * For larger projects, it is recommended to group namespaces in partitions:
 * {{{
 * package path.to.your.project
 * import molecule.data.model._
 *
 * object Seattle extends DataModel(15) {
 *
 *   object customer {
 *     trait Person {
 *       val name    = oneString.fulltext
 *       val age     = oneInt
 *       val address = one[Address]
 *       val bought  = many[products.Item]
 *     }
 *     trait Address {
 *       val street = oneString.fulltext
 *       val city   = oneInt
 *     }
 *     // ..more namespaces in the `customer` partition
 *   }
 *
 *   object products {
 *     trait Item {
 *       val title   = oneString
 *       val inStock = oneInt
 *     }
 *     // ..more namespaces in the `products` partition
 *   }
 *
 *   // Additional partitions...
 * }
 * }}}
 *
 * @groupname setup Setup
 * @groupprio setup 1
 * @groupname opt Options
 * @groupprio opt 2
 * @groupname one Cardinality-one attributes
 * @groupprio one 3
 * @groupname many Cardinality-many attributes
 * @groupprio many 4
 * @groupname map Map attributes
 * @groupprio map 5
 * @groupname ref References
 * @groupprio ref 6
 * */
trait DataModelApi {

  // Types ..................................................

  object oneString extends oneString
  object oneInt extends oneInt
  object oneLong extends oneLong
  object oneFloat extends oneFloat
  object oneDouble extends oneDouble
  object oneBoolean extends oneBoolean
  object oneBigInt extends oneBigInt
  object oneBigDecimal extends oneBigDecimal {
    def apply(precision: Int, scale: Int): oneBigDecimal = ???
  }
  object oneDate extends oneDate
  object oneDuration extends oneDuration
  object oneInstant extends oneInstant
  object oneLocalDate extends oneLocalDate
  object oneLocalTime extends oneLocalTime
  object oneLocalDateTime extends oneLocalDateTime
  object oneOffsetTime extends oneOffsetTime
  object oneOffsetDateTime extends oneOffsetDateTime
  object oneZonedDateTime extends oneZonedDateTime
  object oneUUID extends oneUUID
  object oneURI extends oneURI
  object oneByte extends oneByte
  object oneShort extends oneShort
  object oneChar extends oneChar

  trait oneString extends stringOptions[oneString, String] with Validations[oneString, String]
  trait oneInt extends Options[oneInt, Int, Int] with Validations[oneInt, Int]
  trait oneLong extends Options[oneLong, Long, Long] with Validations[oneLong, Long]
  trait oneFloat extends Options[oneFloat, Float, Float] with Validations[oneFloat, Float]
  trait oneDouble extends Options[oneDouble, Double, Double] with Validations[oneDouble, Double]
  trait oneBoolean extends Options[oneBoolean, Boolean, Boolean] with Validations[oneBoolean, Boolean]
  trait oneBigInt extends Options[oneBigInt, BigInt, BigInt] with Validations[oneBigInt, BigInt]
  trait oneBigDecimal extends Options[oneBigDecimal, BigDecimal, BigDecimal] with Validations[oneBigDecimal, BigDecimal]
  trait oneDate extends Options[oneDate, Date, Date] with Validations[oneDate, Date]
  trait oneDuration extends Options[oneDuration, Duration, Duration] with Validations[oneDuration, Duration]
  trait oneInstant extends Options[oneInstant, Instant, Instant] with Validations[oneInstant, Instant]
  trait oneLocalDate extends Options[oneLocalDate, LocalDate, LocalDate] with Validations[oneLocalDate, LocalDate]
  trait oneLocalTime extends Options[oneLocalTime, LocalTime, LocalTime] with Validations[oneLocalTime, LocalTime]
  trait oneLocalDateTime extends Options[oneLocalDateTime, LocalDateTime, LocalDateTime] with Validations[oneLocalDateTime, LocalDateTime]
  trait oneOffsetTime extends Options[oneOffsetTime, OffsetTime, OffsetTime] with Validations[oneOffsetTime, OffsetTime]
  trait oneOffsetDateTime extends Options[oneOffsetDateTime, OffsetDateTime, OffsetDateTime] with Validations[oneOffsetDateTime, OffsetDateTime]
  trait oneZonedDateTime extends Options[oneZonedDateTime, ZonedDateTime, ZonedDateTime] with Validations[oneZonedDateTime, ZonedDateTime]
  trait oneUUID extends Options[oneUUID, UUID, UUID] with Validations[oneUUID, UUID]
  trait oneURI extends Options[oneURI, URI, URI] with Validations[oneURI, URI]
  trait oneByte extends Options[oneByte, Byte, Byte] with Validations[oneByte, Byte]
  trait oneShort extends Options[oneShort, Short, Short] with Validations[oneShort, Short]
  trait oneChar extends Options[oneChar, Char, Char] with Validations[oneChar, Char]


  object setString extends setString
  object setInt extends setInt
  object setLong extends setLong
  object setFloat extends setFloat
  object setDouble extends setDouble
  object setBoolean extends setBoolean
  object setBigInt extends setBigInt
  object setBigDecimal extends setBigDecimal {
    def apply(precision: Int, scale: Int): oneBigDecimal = ???
  }
  object setDate extends setDate
  object setDuration extends setDuration
  object setInstant extends setInstant
  object setLocalDate extends setLocalDate
  object setLocalTime extends setLocalTime
  object setLocalDateTime extends setLocalDateTime
  object setOffsetTime extends setOffsetTime
  object setOffsetDateTime extends setOffsetDateTime
  object setZonedDateTime extends setZonedDateTime
  object setUUID extends setUUID
  object setURI extends setURI
  object setByte extends setByte
  object setShort extends setShort
  object setChar extends setChar

  trait setString extends stringOptions[setString, Set[String]]  with Validations[oneString, String]
  trait setInt extends Options[setInt, Set[Int], Int] with Validations[oneInt, Int]
  trait setLong extends Options[setLong, Set[Long], Long] with Validations[oneLong, Long]
  trait setFloat extends Options[setFloat, Set[Float], Float] with Validations[oneFloat, Float]
  trait setDouble extends Options[setDouble, Set[Double], Double] with Validations[oneDouble, Double]
  trait setBoolean extends Options[setBoolean, Set[Boolean], Boolean] with Validations[oneBoolean, Boolean]
  trait setBigInt extends Options[setBigInt, Set[BigInt], BigInt] with Validations[oneBigInt, BigInt]
  trait setBigDecimal extends Options[setBigDecimal, Set[BigDecimal], BigDecimal] with Validations[oneBigDecimal, BigDecimal]
  trait setDate extends Options[setDate, Set[Date], Date] with Validations[oneDate, Date]
  trait setDuration extends Options[setDuration, Set[Duration], Duration] with Validations[oneDuration, Duration]
  trait setInstant extends Options[setInstant, Set[Instant], Instant] with Validations[oneInstant, Instant]
  trait setLocalDate extends Options[setLocalDate, Set[LocalDate], LocalDate] with Validations[oneLocalDate, LocalDate]
  trait setLocalTime extends Options[setLocalTime, Set[LocalTime], LocalTime] with Validations[oneLocalTime, LocalTime]
  trait setLocalDateTime extends Options[setLocalDateTime, Set[LocalDateTime], LocalDateTime] with Validations[oneLocalDateTime, LocalDateTime]
  trait setOffsetTime extends Options[setOffsetTime, Set[OffsetTime], OffsetTime] with Validations[oneOffsetTime, OffsetTime]
  trait setOffsetDateTime extends Options[setOffsetDateTime, Set[OffsetDateTime], OffsetDateTime] with Validations[oneOffsetDateTime, OffsetDateTime]
  trait setZonedDateTime extends Options[setZonedDateTime, Set[ZonedDateTime], ZonedDateTime] with Validations[oneZonedDateTime, ZonedDateTime]
  trait setUUID extends Options[setUUID, Set[UUID], UUID] with Validations[oneUUID, UUID]
  trait setURI extends Options[setURI, Set[URI], URI] with Validations[oneURI, URI]
  trait setByte extends Options[setByte, Set[Byte], Byte] with Validations[oneByte, Byte]
  trait setShort extends Options[setShort, Set[Short], Short] with Validations[oneShort, Short]
  trait setChar extends Options[setChar, Set[Char], Char] with Validations[oneChar, Char]


  object seqString extends seqString
  object seqInt extends seqInt
  object seqLong extends seqLong
  object seqFloat extends seqFloat
  object seqDouble extends seqDouble
  object seqBoolean extends seqBoolean
  object seqBigInt extends seqBigInt
  object seqBigDecimal extends seqBigDecimal {
    def apply(precision: Int, scale: Int): oneBigDecimal = ???
  }
  object seqDate extends seqDate
  object seqDuration extends seqDuration
  object seqInstant extends seqInstant
  object seqLocalDate extends seqLocalDate
  object seqLocalTime extends seqLocalTime
  object seqLocalDateTime extends seqLocalDateTime
  object seqOffsetTime extends seqOffsetTime
  object seqOffsetDateTime extends seqOffsetDateTime
  object seqZonedDateTime extends seqZonedDateTime
  object seqUUID extends seqUUID
  object seqURI extends seqURI
  object arrayByte extends arrayByte
  object seqShort extends seqShort
  object seqChar extends seqChar

  trait seqString extends stringOptions[seqString, Seq[String]] with Validations[oneString, String]
  trait seqInt extends Options[seqInt, Seq[Int], Int] with Validations[oneInt, Int]
  trait seqLong extends Options[seqLong, Seq[Long], Long] with Validations[oneLong, Long]
  trait seqFloat extends Options[seqFloat, Seq[Float], Float] with Validations[oneFloat, Float]
  trait seqDouble extends Options[seqDouble, Seq[Double], Double] with Validations[oneDouble, Double]
  trait seqBoolean extends Options[seqBoolean, Seq[Boolean], Boolean] with Validations[oneBoolean, Boolean]
  trait seqBigInt extends Options[seqBigInt, Seq[BigInt], BigInt] with Validations[oneBigInt, BigInt]
  trait seqBigDecimal extends Options[seqBigDecimal, Seq[BigDecimal], BigDecimal] with Validations[oneBigDecimal, BigDecimal]
  trait seqDate extends Options[seqDate, Seq[Date], Date] with Validations[oneDate, Date]
  trait seqDuration extends Options[seqDuration, Seq[Duration], Duration] with Validations[oneDuration, Duration]
  trait seqInstant extends Options[seqInstant, Seq[Instant], Instant] with Validations[oneInstant, Instant]
  trait seqLocalDate extends Options[seqLocalDate, Seq[LocalDate], LocalDate] with Validations[oneLocalDate, LocalDate]
  trait seqLocalTime extends Options[seqLocalTime, Seq[LocalTime], LocalTime] with Validations[oneLocalTime, LocalTime]
  trait seqLocalDateTime extends Options[seqLocalDateTime, Seq[LocalDateTime], LocalDateTime] with Validations[oneLocalDateTime, LocalDateTime]
  trait seqOffsetTime extends Options[seqOffsetTime, Seq[OffsetTime], OffsetTime] with Validations[oneOffsetTime, OffsetTime]
  trait seqOffsetDateTime extends Options[seqOffsetDateTime, Seq[OffsetDateTime], OffsetDateTime] with Validations[oneOffsetDateTime, OffsetDateTime]
  trait seqZonedDateTime extends Options[seqZonedDateTime, Seq[ZonedDateTime], ZonedDateTime] with Validations[oneZonedDateTime, ZonedDateTime]
  trait seqUUID extends Options[seqUUID, Seq[UUID], UUID] with Validations[oneUUID, UUID]
  trait seqURI extends Options[seqURI, Seq[URI], URI] with Validations[oneURI, URI]
  trait arrayByte extends Options[arrayByte, Seq[Byte], Byte] with Validations[oneByte, Byte]
  trait seqShort extends Options[seqShort, Seq[Short], Short] with Validations[oneShort, Short]
  trait seqChar extends Options[seqChar, Seq[Char], Char] with Validations[oneChar, Char]


  object mapString extends mapString
  object mapInt extends mapInt
  object mapLong extends mapLong
  object mapFloat extends mapFloat
  object mapDouble extends mapDouble
  object mapBoolean extends mapBoolean
  object mapBigInt extends mapBigInt
  object mapBigDecimal extends mapBigDecimal {
    def apply(precision: Int, scale: Int): oneBigDecimal = ???
  }
  object mapDate extends mapDate
  object mapDuration extends mapDuration
  object mapInstant extends mapInstant
  object mapLocalDate extends mapLocalDate
  object mapLocalTime extends mapLocalTime
  object mapLocalDateTime extends mapLocalDateTime
  object mapOffsetTime extends mapOffsetTime
  object mapOffsetDateTime extends mapOffsetDateTime
  object mapZonedDateTime extends mapZonedDateTime
  object mapUUID extends mapUUID
  object mapURI extends mapURI
  object mapByte extends mapByte
  object mapShort extends mapShort
  object mapChar extends mapChar

  trait mapString extends stringOptions[mapString, Map[String, String]]
  trait mapInt extends Options[mapInt, Map[String, Int], Int]
  trait mapLong extends Options[mapLong, Map[String, Long], Long]
  trait mapFloat extends Options[mapFloat, Map[String, Float], Float]
  trait mapDouble extends Options[mapDouble, Map[String, Double], Double]
  trait mapBoolean extends Options[mapBoolean, Map[String, Boolean], Boolean]
  trait mapBigInt extends Options[mapBigInt, Map[String, BigInt], BigInt]
  trait mapBigDecimal extends Options[mapBigDecimal, Map[String, BigDecimal], BigDecimal]
  trait mapDate extends Options[mapDate, Map[String, Date], Date]
  trait mapDuration extends Options[mapDuration, Map[String, Duration], Duration]
  trait mapInstant extends Options[mapInstant, Map[String, Instant], Instant]
  trait mapLocalDate extends Options[mapLocalDate, Map[String, LocalDate], LocalDate]
  trait mapLocalTime extends Options[mapLocalTime, Map[String, LocalTime], LocalTime]
  trait mapLocalDateTime extends Options[mapLocalDateTime, Map[String, LocalDateTime], LocalDateTime]
  trait mapOffsetTime extends Options[mapOffsetTime, Map[String, OffsetTime], OffsetTime]
  trait mapOffsetDateTime extends Options[mapOffsetDateTime, Map[String, OffsetDateTime], OffsetDateTime]
  trait mapZonedDateTime extends Options[mapZonedDateTime, Map[String, ZonedDateTime], ZonedDateTime]
  trait mapUUID extends Options[mapUUID, Map[String, UUID], UUID]
  trait mapURI extends Options[mapURI, Map[String, URI], URI]
  trait mapByte extends Options[mapByte, Map[String, Byte], Byte]
  trait mapShort extends Options[mapShort, Map[String, Short], Short]
  trait mapChar extends Options[mapChar, Map[String, Char], Char]


  // Refs ..................................................

  object one extends one
  object many extends many

  trait one extends refOptions[one]
  trait many extends refOptions[many]


  // Options ..................................................

  trait Requierable

  /** Attribute options.
   *
   * @group opt
   */
  sealed trait Options[Self, Tpe, BaseTpe] extends Requierable {

    /** Description of attribute
     *
     * @param description
     * @return
     */
    def descr(description: String): Self = ???
    def apply(description: String): Self = ???

    // Allowed values (like enumerations)
    def enums(vs: BaseTpe*): Self = ???
    def enums(vs: Seq[BaseTpe], failureMsg: String): Self = ???

    /** Alias to non-compatible attribute name like `type` or `first-name` etc.
     *
     * Molecule then creates an alias to the special name in the schema so that
     * queries will match both the attribute name and the alias.
     * */
    def alias(altAttrName: String): Self = ???

    /** Index option (defaults to true).
     * <br><br>
     * Generated index for this attribute.
     *
     * For the Datomic database, all attributes are set by default with the indexed option
     * automatically.
     * */
    lazy val index: Self = ???

    /** Unique identity option.
     * <br><br>
     * Attribute value is unique to each entity and "upsert" is enabled.
     * <br><br>
     * Attempts to insert a duplicate value for a temporary entity id will cause all
     * attributes associated with that temporary id to be merged with the entity
     * already in the database.
     * */
    lazy val uniqueIdentity: Self = ???

    /** Unique value option.
     * <br><br>
     * Attribute value is unique to each entity.
     * <br><br>
     * Attempts to insert a duplicate value for a different entity id will fail.
     * */
    lazy val unique: Self = ???

    lazy val mandatory: Self = ???

    // Tupled attributes
    def require(attrs: Requierable*): Self = ???

    // Value accessor for validation code
    val value: Tpe = ???


    /** No history option (effect in Datomic only). */
    lazy val noHistory: Self = ???

  }


  trait Validations[Self, BaseTpe] {
    def validate(ok: BaseTpe => Boolean, errorMsg: String = ""): Self = ???
    def validate(err2msg: PartialFunction[BaseTpe, String]): Self = ???
  }


  trait stringOptions[Self, Tpe] extends Options[Self, Tpe, String] {
    // Enable fulltext search (for those data sources that supports it)
    val fulltext: Self = ???

    // Validation .................
    val email: Self = ???
    def email(msg: String): Self = ???
    def regex(expr: String, msg: String = ""): Self = ???
  }


  trait refOptions[Self] extends Requierable {

    /** Apply namespace type to reference.
     *
     * @tparam RefNs Ref namespace type
     */
    def apply[RefNs](implicit x: DummyImplicit): refOptions[Self] = ???


    /**
     *
     * @param description
     * @tparam RefNs
     * @return
     */
    def apply[RefNs](description: String): refOptions[Self] = ???

    /** Owner option.
     *
     * This entity owns the referenced entity/entities.
     *
     * - If this entity is deleted, its references are deleted too
     * (and recursively if sub entities have owned entities!)
     *
     * <br><br>
     * Specifies that an attribute whose type is :db.type/ref is a component.
     * <br><br>
     * Referenced entities become subcomponents of the entity to which the attribute is applied.
     * <br><br>
     * When you retract an entity with :db/retractEntity, all subcomponents are also retracted.
     * <br><br>
     * When you get the graph of an entity, all its subcomponent entities are fetched recursively.
     */
    lazy val owner: Self = ???

    lazy val mandatory: Self = ???

    // Tupled attributes
    def require(attrs: Requierable*): Self = ???
  }
}

