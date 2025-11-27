package molecule

import java.net.URI
import java.time.*
import java.util.{Date, UUID}

/**
 * Domain structure definition
 *
 * Define a Domain structure to be handled by Molecule in an object that extends this `DomainStructure` class.
 *
 * A domain structure consists of traits describing real world entities. Each entity/trait is given a name and
 * defines a list of attributes that are the relevant properties of the entity.
 *
 * {{{
 * package path.to.your.project
 * import molecule.Domain
 *
 * object Community extends Domain { // "Community" domain
 *
 *   trait Person {          // "Person" entity
 *     val name = oneString  // Person "name" String attribute definition
 *     val age  = oneInt     // Person "age" Int attribute definition
 *   }
 *
 *   // Additional entities...
 * }
 * }}}
 * For larger projects, it is recommended to organize the domain structure in
 * segments of related entity traits within objects:
 * {{{
 * object Seattle extends DomainStructure {
 *
 *   object customer { // "customer" segment
 *     trait Person {
 *       val name    = oneString
 *       val age     = oneInt
 *       val address = manyToOne[Address]
 *     }
 *     trait Address {
 *       val street = oneString
 *       val city   = oneInt
 *     }
 *     // ..more entities in the `customer` segment
 *   }
 *
 *   object products { // "products" segment
 *     trait Item {
 *       val title   = oneString
 *       val inStock = oneInt
 *     }
 *     // ..more entities in the `products` segment
 *   }
 *
 *   // Additional segments...
 * }
 * }}}
 */
abstract class DomainStructure {

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
  trait oneInt extends AttrOptions[oneInt, Int, Int] with Validations[oneInt, Int]
  trait oneLong extends AttrOptions[oneLong, Long, Long] with Validations[oneLong, Long]
  trait oneFloat extends AttrOptions[oneFloat, Float, Float] with Validations[oneFloat, Float]
  trait oneDouble extends AttrOptions[oneDouble, Double, Double] with Validations[oneDouble, Double]
  trait oneBoolean extends AttrOptions[oneBoolean, Boolean, Boolean] with Validations[oneBoolean, Boolean]
  trait oneBigInt extends AttrOptions[oneBigInt, BigInt, BigInt] with Validations[oneBigInt, BigInt]
  trait oneBigDecimal extends AttrOptions[oneBigDecimal, BigDecimal, BigDecimal] with Validations[oneBigDecimal, BigDecimal]
  trait oneDate extends AttrOptions[oneDate, Date, Date] with Validations[oneDate, Date]
  trait oneDuration extends AttrOptions[oneDuration, Duration, Duration] with Validations[oneDuration, Duration]
  trait oneInstant extends AttrOptions[oneInstant, Instant, Instant] with Validations[oneInstant, Instant]
  trait oneLocalDate extends AttrOptions[oneLocalDate, LocalDate, LocalDate] with Validations[oneLocalDate, LocalDate]
  trait oneLocalTime extends AttrOptions[oneLocalTime, LocalTime, LocalTime] with Validations[oneLocalTime, LocalTime]
  trait oneLocalDateTime extends AttrOptions[oneLocalDateTime, LocalDateTime, LocalDateTime] with Validations[oneLocalDateTime, LocalDateTime]
  trait oneOffsetTime extends AttrOptions[oneOffsetTime, OffsetTime, OffsetTime] with Validations[oneOffsetTime, OffsetTime]
  trait oneOffsetDateTime extends AttrOptions[oneOffsetDateTime, OffsetDateTime, OffsetDateTime] with Validations[oneOffsetDateTime, OffsetDateTime]
  trait oneZonedDateTime extends AttrOptions[oneZonedDateTime, ZonedDateTime, ZonedDateTime] with Validations[oneZonedDateTime, ZonedDateTime]
  trait oneUUID extends AttrOptions[oneUUID, UUID, UUID] with Validations[oneUUID, UUID]
  trait oneURI extends AttrOptions[oneURI, URI, URI] with Validations[oneURI, URI]
  trait oneByte extends AttrOptions[oneByte, Byte, Byte] with Validations[oneByte, Byte]
  trait oneShort extends AttrOptions[oneShort, Short, Short] with Validations[oneShort, Short]
  trait oneChar extends AttrOptions[oneChar, Char, Char] with Validations[oneChar, Char]


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

  trait setString extends stringOptions[setString, Set[String]] with Validations[oneString, String]
  trait setInt extends AttrOptions[setInt, Set[Int], Int] with Validations[oneInt, Int]
  trait setLong extends AttrOptions[setLong, Set[Long], Long] with Validations[oneLong, Long]
  trait setFloat extends AttrOptions[setFloat, Set[Float], Float] with Validations[oneFloat, Float]
  trait setDouble extends AttrOptions[setDouble, Set[Double], Double] with Validations[oneDouble, Double]
  trait setBoolean extends AttrOptions[setBoolean, Set[Boolean], Boolean] with Validations[oneBoolean, Boolean]
  trait setBigInt extends AttrOptions[setBigInt, Set[BigInt], BigInt] with Validations[oneBigInt, BigInt]
  trait setBigDecimal extends AttrOptions[setBigDecimal, Set[BigDecimal], BigDecimal] with Validations[oneBigDecimal, BigDecimal]
  trait setDate extends AttrOptions[setDate, Set[Date], Date] with Validations[oneDate, Date]
  trait setDuration extends AttrOptions[setDuration, Set[Duration], Duration] with Validations[oneDuration, Duration]
  trait setInstant extends AttrOptions[setInstant, Set[Instant], Instant] with Validations[oneInstant, Instant]
  trait setLocalDate extends AttrOptions[setLocalDate, Set[LocalDate], LocalDate] with Validations[oneLocalDate, LocalDate]
  trait setLocalTime extends AttrOptions[setLocalTime, Set[LocalTime], LocalTime] with Validations[oneLocalTime, LocalTime]
  trait setLocalDateTime extends AttrOptions[setLocalDateTime, Set[LocalDateTime], LocalDateTime] with Validations[oneLocalDateTime, LocalDateTime]
  trait setOffsetTime extends AttrOptions[setOffsetTime, Set[OffsetTime], OffsetTime] with Validations[oneOffsetTime, OffsetTime]
  trait setOffsetDateTime extends AttrOptions[setOffsetDateTime, Set[OffsetDateTime], OffsetDateTime] with Validations[oneOffsetDateTime, OffsetDateTime]
  trait setZonedDateTime extends AttrOptions[setZonedDateTime, Set[ZonedDateTime], ZonedDateTime] with Validations[oneZonedDateTime, ZonedDateTime]
  trait setUUID extends AttrOptions[setUUID, Set[UUID], UUID] with Validations[oneUUID, UUID]
  trait setURI extends AttrOptions[setURI, Set[URI], URI] with Validations[oneURI, URI]
  trait setByte extends AttrOptions[setByte, Set[Byte], Byte] with Validations[oneByte, Byte]
  trait setShort extends AttrOptions[setShort, Set[Short], Short] with Validations[oneShort, Short]
  trait setChar extends AttrOptions[setChar, Set[Char], Char] with Validations[oneChar, Char]


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
  trait seqInt extends AttrOptions[seqInt, Seq[Int], Int] with Validations[oneInt, Int]
  trait seqLong extends AttrOptions[seqLong, Seq[Long], Long] with Validations[oneLong, Long]
  trait seqFloat extends AttrOptions[seqFloat, Seq[Float], Float] with Validations[oneFloat, Float]
  trait seqDouble extends AttrOptions[seqDouble, Seq[Double], Double] with Validations[oneDouble, Double]
  trait seqBoolean extends AttrOptions[seqBoolean, Seq[Boolean], Boolean] with Validations[oneBoolean, Boolean]
  trait seqBigInt extends AttrOptions[seqBigInt, Seq[BigInt], BigInt] with Validations[oneBigInt, BigInt]
  trait seqBigDecimal extends AttrOptions[seqBigDecimal, Seq[BigDecimal], BigDecimal] with Validations[oneBigDecimal, BigDecimal]
  trait seqDate extends AttrOptions[seqDate, Seq[Date], Date] with Validations[oneDate, Date]
  trait seqDuration extends AttrOptions[seqDuration, Seq[Duration], Duration] with Validations[oneDuration, Duration]
  trait seqInstant extends AttrOptions[seqInstant, Seq[Instant], Instant] with Validations[oneInstant, Instant]
  trait seqLocalDate extends AttrOptions[seqLocalDate, Seq[LocalDate], LocalDate] with Validations[oneLocalDate, LocalDate]
  trait seqLocalTime extends AttrOptions[seqLocalTime, Seq[LocalTime], LocalTime] with Validations[oneLocalTime, LocalTime]
  trait seqLocalDateTime extends AttrOptions[seqLocalDateTime, Seq[LocalDateTime], LocalDateTime] with Validations[oneLocalDateTime, LocalDateTime]
  trait seqOffsetTime extends AttrOptions[seqOffsetTime, Seq[OffsetTime], OffsetTime] with Validations[oneOffsetTime, OffsetTime]
  trait seqOffsetDateTime extends AttrOptions[seqOffsetDateTime, Seq[OffsetDateTime], OffsetDateTime] with Validations[oneOffsetDateTime, OffsetDateTime]
  trait seqZonedDateTime extends AttrOptions[seqZonedDateTime, Seq[ZonedDateTime], ZonedDateTime] with Validations[oneZonedDateTime, ZonedDateTime]
  trait seqUUID extends AttrOptions[seqUUID, Seq[UUID], UUID] with Validations[oneUUID, UUID]
  trait seqURI extends AttrOptions[seqURI, Seq[URI], URI] with Validations[oneURI, URI]
  trait arrayByte extends AttrOptions[arrayByte, Seq[Byte], Byte] with Validations[oneByte, Byte]
  trait seqShort extends AttrOptions[seqShort, Seq[Short], Short] with Validations[oneShort, Short]
  trait seqChar extends AttrOptions[seqChar, Seq[Char], Char] with Validations[oneChar, Char]


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
  trait mapInt extends AttrOptions[mapInt, Map[String, Int], Int]
  trait mapLong extends AttrOptions[mapLong, Map[String, Long], Long]
  trait mapFloat extends AttrOptions[mapFloat, Map[String, Float], Float]
  trait mapDouble extends AttrOptions[mapDouble, Map[String, Double], Double]
  trait mapBoolean extends AttrOptions[mapBoolean, Map[String, Boolean], Boolean]
  trait mapBigInt extends AttrOptions[mapBigInt, Map[String, BigInt], BigInt]
  trait mapBigDecimal extends AttrOptions[mapBigDecimal, Map[String, BigDecimal], BigDecimal]
  trait mapDate extends AttrOptions[mapDate, Map[String, Date], Date]
  trait mapDuration extends AttrOptions[mapDuration, Map[String, Duration], Duration]
  trait mapInstant extends AttrOptions[mapInstant, Map[String, Instant], Instant]
  trait mapLocalDate extends AttrOptions[mapLocalDate, Map[String, LocalDate], LocalDate]
  trait mapLocalTime extends AttrOptions[mapLocalTime, Map[String, LocalTime], LocalTime]
  trait mapLocalDateTime extends AttrOptions[mapLocalDateTime, Map[String, LocalDateTime], LocalDateTime]
  trait mapOffsetTime extends AttrOptions[mapOffsetTime, Map[String, OffsetTime], OffsetTime]
  trait mapOffsetDateTime extends AttrOptions[mapOffsetDateTime, Map[String, OffsetDateTime], OffsetDateTime]
  trait mapZonedDateTime extends AttrOptions[mapZonedDateTime, Map[String, ZonedDateTime], ZonedDateTime]
  trait mapUUID extends AttrOptions[mapUUID, Map[String, UUID], UUID]
  trait mapURI extends AttrOptions[mapURI, Map[String, URI], URI]
  trait mapByte extends AttrOptions[mapByte, Map[String, Byte], Byte]
  trait mapShort extends AttrOptions[mapShort, Map[String, Short], Short]
  trait mapChar extends AttrOptions[mapChar, Map[String, Char], Char]


  // ManyToOne relationship ..................................................

  object manyToOne extends manyToOne
  trait manyToOne extends refOptions[manyToOne] with Validations[oneLong, Long]

  trait Join

  sealed trait Action
  trait query extends Action
  trait subscribe extends Action
  trait save extends Action
  trait insertMany extends Action
  trait update extends Action
  trait delete extends Action

  trait read extends query with subscribe
  trait write extends save with insertMany with update with delete
  trait all extends read with write

  // User-defined roles need to extend this
  trait Role

  sealed trait Constraint
  object Constraint {
    case object AuthenticatedOnly extends Constraint
    final case class SelfOnly(field: String) extends Constraint // ownerId == ctx.userId
    final case class SameOrg(field: String) extends Constraint // orgId == ctx.orgId
    final case class CustomPolicy(id: String) extends Constraint // hook
  }

  // === Access Control Type Classes (at DomainStructure level for implicit scope) ===

  /** Evidence that A is either a single Role or tuple of Roles */
  trait RolesOnly[A]
  object RolesOnly:
    given single[R <: Role]: RolesOnly[R] = new RolesOnly[R] {}
    given empty: RolesOnly[EmptyTuple] = new RolesOnly[EmptyTuple] {}
    given tuple[H <: Role, T <: Tuple](using RolesOnly[T]): RolesOnly[H *: T] =
      new RolesOnly[H *: T] {}

  /** Evidence that A is either a single Action or tuple of Actions */
  trait ActionsOnly[A]
  object ActionsOnly:
    given single[A <: Action]: ActionsOnly[A] = new ActionsOnly[A] {}
    given empty: ActionsOnly[EmptyTuple] = new ActionsOnly[EmptyTuple] {}
    given tuple[H <: Action, T <: Tuple](using ActionsOnly[T]): ActionsOnly[H *: T] =
      new ActionsOnly[H *: T] {}


  // Enums ..................................................

  object oneEnum extends enumConstructor
  object setEnum extends enumConstructor
  object seqEnum extends enumConstructor

  trait enumConstructor {

    /** Apply enum type to reference.
     *
     * @tparam EnumType Ref entity type
     */
    def apply[EnumType <: Product](implicit x: DummyImplicit): Nothing = ???

    // We could be even more specific with reflect.Enum but avoid it for now to be able to test
    // projects directly (without publishing) in sbt-molecule that runs on Scala 2.12 (not having reflect.Enum)
    //    def apply[EnumType <: reflect.Enum](implicit x: DummyImplicit): Nothing = ???

    /**
     *
     * @param description
     * @tparam EnumType
     * @return
     */
    def apply[EnumType <: Product](description: String): Nothing = ???
  }


  trait Attr

  // Options ..................................................

  /**
   * Elements that can be required
   */
  trait Requierable

  /** Attribute options.
   *
   * @group opt
   */
  sealed trait AttrOptions[Self, Tpe, BaseTpe] extends Requierable {

    /** Description of attribute
     *
     * @param description
     * @return
     */
    def description(description: String): Self = ???
    def apply(description: String): Self = ???

    // Allowed values (like enumerations)
    def allowedValues(v: BaseTpe, vs: BaseTpe*): Self = ???
    def allowedValues(vs: Seq[BaseTpe], failureMsg: String): Self = ???

    /** Alias to non-compatible attribute name like `type` or `first-name` etc.
     *
     * Molecule then creates an alias to the special name in the schema so that
     * queries will match both the attribute name and the alias.
     * */
    def alias(altAttrName: String): Self = ???

    /** Index option (defaults to true).
     * <br><br>
     * Generated index for this attribute.
     * */
    lazy val index: Self = ???

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


    // === Access Control Methods ===============================

    // Restrict to specific role(s)
    def allowRoles[R](using RolesOnly[R]): Self = ???

    // Restrict to specific action(s) (all entity roles)
    def allowActions[A](using ActionsOnly[A]): Self = ???

    // Restrict to specific role(s) AND action(s)
    def allowRoleActions[R, A](using RolesOnly[R], ActionsOnly[A]): Self = ???

    // Attribute constraints
    def authenticated: Self = ???

    // Use validation dsl as authorization conditions
    def authorizeIf(condition: Validated*): Self = ???
  }

  trait Validated
  trait Validations[Self, BaseTpe] {
    def validate(predicate: BaseTpe => Boolean, errorMsg: String = ""): Self & Validated = ???
    def validate(err2msg: PartialFunction[BaseTpe, String]): Self & Validated = ???
  }

  trait stringOptions[Self, Tpe] extends AttrOptions[Self, Tpe, String] {
    // Validation
    val email: Self = ???
    def email(msg: String): Self = ???
    def regex(expr: String, msg: String = ""): Self = ???
  }


  trait refOptions[Self] extends Requierable {

    /** Apply entity type to reference.
     *
     * Without calling `oneToMany` too, the plural name of the defining entity will be used as reverse ref name.
     *
     * @tparam Ref Ref entity type
     */
    def apply[Ref]: refOptions[Self] & Ref & Validations[Self, Long] = ???
//    def apply[Ref]: refOptions[Self] & Ref & Validated = ???

    // Value accessor for validation code
    val value: Long = ???


    /**
     *
     * @param reverseRef Custom reverse ref name
     * @return
     */
    def oneToMany(reverseRef: String): Self = ???

    /**
     * Add description of relationship
     *
     * @param description
     * @return
     */
    def descr(description: String): Self = ???

    // No direct apply of description to avoid confusion with reverse ref name
    // def apply[Ref](description: String): refOptions[Self] & Ref = ???

    lazy val mandatory: Self = ???

    lazy val owner: Self = ???

    // Tupled attributes
    def require(attrs: Requierable*): Self = ???
  }
}
