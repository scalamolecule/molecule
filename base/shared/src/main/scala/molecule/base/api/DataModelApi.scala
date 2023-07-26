package molecule.base.api

import java.net.URI
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
 * @groupname bi Bidirectional references
 * @groupprio bi 7
 * @groupname edge Bidirectional edge references
 * @groupprio edge 8
 * */
object DataModelApi extends DataModelApi
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
  object setUUID extends setUUID
  object setURI extends setURI
  object setByte extends setByte
  object setShort extends setShort
  object setChar extends setChar

  trait setString extends stringOptions[oneString, Set[String]]
  trait setInt extends Options[setInt, Set[Int], Int]
  trait setLong extends Options[setLong, Set[Long], Long]
  trait setFloat extends Options[setFloat, Set[Float], Float]
  trait setDouble extends Options[setDouble, Set[Double], Double]
  trait setBoolean extends Options[setBoolean, Set[Boolean], Boolean]
  trait setBigInt extends Options[setBigInt, Set[BigInt], BigInt]
  trait setBigDecimal extends Options[setBigDecimal, Set[BigDecimal], BigDecimal]
  trait setDate extends Options[setDate, Set[Date], Date]
  trait setUUID extends Options[setUUID, Set[UUID], UUID]
  trait setURI extends Options[setURI, Set[URI], URI]
  trait setByte extends Options[setByte, Set[Byte], Byte]
  trait setShort extends Options[setShort, Set[Short], Short]
  trait setChar extends Options[setChar, Set[Char], Char]


  // Refs ..................................................

  object one extends one
  object many extends many

  trait one extends refOptions[one, Long]
  trait many extends refOptions[many, Set[Long]]


  // Options ..................................................

  /** Attribute options.
   *
   * @group opt
   */
  sealed trait Options[Self, Tpe, BaseTpe] {

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
    def require[T <: Options[_, _, _]](attrs: T*): Self = ???

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


  trait refOptions[Self, Tpe] extends Options[Self, Tpe, Long] {

    /** Apply namespace type to reference.
     *
     * @tparam RefNs Ref namespace type
     */
    def apply[RefNs](implicit x: DummyImplicit): refOptions[Self, Tpe] = ???
    def apply[RefNs](description: String): refOptions[Self, Tpe] = ???

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
  }
}

