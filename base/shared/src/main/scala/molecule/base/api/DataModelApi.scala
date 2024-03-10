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

  trait oneString extends stringOptions[oneString, String]
  trait oneInt extends Options[oneInt, Int, Int]
  trait oneLong extends Options[oneLong, Long, Long]
  trait oneFloat extends Options[oneFloat, Float, Float]
  trait oneDouble extends Options[oneDouble, Double, Double]
  trait oneBoolean extends Options[oneBoolean, Boolean, Boolean]
  trait oneBigInt extends Options[oneBigInt, BigInt, BigInt]
  trait oneBigDecimal extends Options[oneBigDecimal, BigDecimal, BigDecimal]
  trait oneDate extends Options[oneDate, Date, Date]
  trait oneDuration extends Options[oneDuration, Duration, Duration]
  trait oneInstant extends Options[oneInstant, Instant, Instant]
  trait oneLocalDate extends Options[oneLocalDate, LocalDate, LocalDate]
  trait oneLocalTime extends Options[oneLocalTime, LocalTime, LocalTime]
  trait oneLocalDateTime extends Options[oneLocalDateTime, LocalDateTime, LocalDateTime]
  trait oneOffsetTime extends Options[oneOffsetTime, OffsetTime, OffsetTime]
  trait oneOffsetDateTime extends Options[oneOffsetDateTime, OffsetDateTime, OffsetDateTime]
  trait oneZonedDateTime extends Options[oneZonedDateTime, ZonedDateTime, ZonedDateTime]
  trait oneUUID extends Options[oneUUID, UUID, UUID]
  trait oneURI extends Options[oneURI, URI, URI]
  trait oneByte extends Options[oneByte, Byte, Byte]
  trait oneShort extends Options[oneShort, Short, Short]
  trait oneChar extends Options[oneChar, Char, Char]


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

  trait setString extends stringOptions[setString, Set[String]]
  trait setInt extends Options[setInt, Set[Int], Int]
  trait setLong extends Options[setLong, Set[Long], Long]
  trait setFloat extends Options[setFloat, Set[Float], Float]
  trait setDouble extends Options[setDouble, Set[Double], Double]
  trait setBoolean extends Options[setBoolean, Set[Boolean], Boolean]
  trait setBigInt extends Options[setBigInt, Set[BigInt], BigInt]
  trait setBigDecimal extends Options[setBigDecimal, Set[BigDecimal], BigDecimal]
  trait setDate extends Options[setDate, Set[Date], Date]
  trait setDuration extends Options[setDuration, Set[Duration], Duration]
  trait setInstant extends Options[setInstant, Set[Instant], Instant]
  trait setLocalDate extends Options[setLocalDate, Set[LocalDate], LocalDate]
  trait setLocalTime extends Options[setLocalTime, Set[LocalTime], LocalTime]
  trait setLocalDateTime extends Options[setLocalDateTime, Set[LocalDateTime], LocalDateTime]
  trait setOffsetTime extends Options[setOffsetTime, Set[OffsetTime], OffsetTime]
  trait setOffsetDateTime extends Options[setOffsetDateTime, Set[OffsetDateTime], OffsetDateTime]
  trait setZonedDateTime extends Options[setZonedDateTime, Set[ZonedDateTime], ZonedDateTime]
  trait setUUID extends Options[setUUID, Set[UUID], UUID]
  trait setURI extends Options[setURI, Set[URI], URI]
  trait setByte extends Options[setByte, Set[Byte], Byte]
  trait setShort extends Options[setShort, Set[Short], Short]
  trait setChar extends Options[setChar, Set[Char], Char]


  object arrayString extends arrayString
  object arrayInt extends arrayInt
  object arrayLong extends arrayLong
  object arrayFloat extends arrayFloat
  object arrayDouble extends arrayDouble
  object arrayBoolean extends arrayBoolean
  object arrayBigInt extends arrayBigInt
  object arrayBigDecimal extends arrayBigDecimal {
    def apply(precision: Int, scale: Int): oneBigDecimal = ???
  }
  object arrayDate extends arrayDate
  object arrayDuration extends arrayDuration
  object arrayInstant extends arrayInstant
  object arrayLocalDate extends arrayLocalDate
  object arrayLocalTime extends arrayLocalTime
  object arrayLocalDateTime extends arrayLocalDateTime
  object arrayOffsetTime extends arrayOffsetTime
  object arrayOffsetDateTime extends arrayOffsetDateTime
  object arrayZonedDateTime extends arrayZonedDateTime
  object arrayUUID extends arrayUUID
  object arrayURI extends arrayURI
  object arrayByte extends arrayByte
  object arrayShort extends arrayShort
  object arrayChar extends arrayChar

  trait arrayString extends stringOptions[arrayString, Array[String]]
  trait arrayInt extends Options[arrayInt, Array[Int], Int]
  trait arrayLong extends Options[arrayLong, Array[Long], Long]
  trait arrayFloat extends Options[arrayFloat, Array[Float], Float]
  trait arrayDouble extends Options[arrayDouble, Array[Double], Double]
  trait arrayBoolean extends Options[arrayBoolean, Array[Boolean], Boolean]
  trait arrayBigInt extends Options[arrayBigInt, Array[BigInt], BigInt]
  trait arrayBigDecimal extends Options[arrayBigDecimal, Array[BigDecimal], BigDecimal]
  trait arrayDate extends Options[arrayDate, Array[Date], Date]
  trait arrayDuration extends Options[arrayDuration, Array[Duration], Duration]
  trait arrayInstant extends Options[arrayInstant, Array[Instant], Instant]
  trait arrayLocalDate extends Options[arrayLocalDate, Array[LocalDate], LocalDate]
  trait arrayLocalTime extends Options[arrayLocalTime, Array[LocalTime], LocalTime]
  trait arrayLocalDateTime extends Options[arrayLocalDateTime, Array[LocalDateTime], LocalDateTime]
  trait arrayOffsetTime extends Options[arrayOffsetTime, Array[OffsetTime], OffsetTime]
  trait arrayOffsetDateTime extends Options[arrayOffsetDateTime, Array[OffsetDateTime], OffsetDateTime]
  trait arrayZonedDateTime extends Options[arrayZonedDateTime, Array[ZonedDateTime], ZonedDateTime]
  trait arrayUUID extends Options[arrayUUID, Array[UUID], UUID]
  trait arrayURI extends Options[arrayURI, Array[URI], URI]
  trait arrayByte extends Options[arrayByte, Array[Byte], Byte]
  trait arrayShort extends Options[arrayShort, Array[Short], Short]
  trait arrayChar extends Options[arrayChar, Array[Char], Char]


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

    /** Index option (defaults to true).
     * <br><br>
     * Generated index for this attribute. By default all attributes are set with
     * the indexed option automatically by Molecule, so you don't need to set this.
     * */
    lazy val index: Self = ???

    /** No history option. */
    lazy val noHistory: Self = ???

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


    /** Alias to non-compatible attribute name like `type` or `first-name` etc.
     *
     * Molecule then creates an alias to the special name in the schema so that
     * queries will match both the attribute name and the alias.
     * */
    def alias(altAttrName: String): Self = ???

    /** Description of attribute
     *
     * @param s
     * @return
     */
    def descr(description: String): Self = ???
    def apply(description: String): Self = ???


    // Validation .................

    def validate(ok: BaseTpe => Boolean, errorMsg: String = ""): Self = ???
    def validate(err2msg: PartialFunction[BaseTpe, String]): Self = ???


    // Allowed values (like enumerations)
    def enums(vs: BaseTpe*): Self = ???
    def enums(vs: Seq[BaseTpe], failureMsg: String): Self = ???

    // Tupled attributes
    def require(attrs: Requierable*): Self = ???

    // Value accessor for validation code
    val value: Tpe = ???
  }


  trait stringOptions[Self, Tpe] extends Options[Self, Tpe, String] {
    // Enable fulltext search
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

