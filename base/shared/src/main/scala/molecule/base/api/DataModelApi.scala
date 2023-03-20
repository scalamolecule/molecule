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

  // Types
  object oneString extends string
  object oneChar extends char
  object oneByte extends byte
  object oneShort extends short
  object oneInt extends int
  object oneLong extends long
  object oneFloat extends float
  object oneDouble extends double
  object oneBoolean extends boolean
  object oneBigInt extends bigInt
  object oneBigDecimal extends bigDecimal
  object oneDate extends date
  object oneUUID extends uuid
  object oneURI extends uri

  object setString extends string
  object setChar extends char
  object setByte extends byte
  object setShort extends short
  object setInt extends int
  object setLong extends long
  object setFloat extends float
  object setDouble extends double
  object setBoolean extends boolean
  object setBigInt extends bigInt
  object setBigDecimal extends bigDecimal
  object setDate extends date
  object setUUID extends uuid
  object setURI extends uri

  object arrayString extends string
  object arrayChar extends char
  object arrayByte extends byte
  object arrayShort extends short
  object arrayInt extends int
  object arrayLong extends long
  object arrayFloat extends float
  object arrayDouble extends double
  object arrayBoolean extends boolean
  object arrayBigInt extends bigInt
  object arrayBigDecimal extends bigDecimal
  object arrayDate extends date
  object arrayUUID extends uuid
  object arrayURI extends uri

  object mapString extends string
  object mapChar extends char
  object mapByte extends byte
  object mapShort extends short
  object mapInt extends int
  object mapLong extends long
  object mapFloat extends float
  object mapDouble extends double
  object mapBoolean extends boolean
  object mapBigInt extends bigInt
  object mapBigDecimal extends bigDecimal
  object mapDate extends date
  object mapUUID extends uuid
  object mapURI extends uri


  // Refs
  object one extends one
  object many extends many

  /** Card-one reference.
   *
   * @group ref
   */
  trait one extends refOptions[one]

  /** Card-many reference.
   *
   * @group ref
   */
  trait many extends refOptions[many]

  trait attr

  trait string extends attr with stringOptions
  trait char extends attr with Options[char, Char]
  trait byte extends attr with Options[byte, Byte]
  trait short extends attr with Options[short, Short]
  trait int extends attr with Options[int, Int]
  trait long extends attr with Options[long, Long]
  trait float extends attr with Options[float, Float]
  trait double extends attr with Options[double, Double]
  trait boolean extends attr with Options[boolean, Boolean]
  trait bigInt extends attr with Options[bigInt, BigInt]
  trait bigDecimal extends attr with Options[bigDecimal, BigDecimal]
  trait date extends attr with Options[date, Date]
  trait uuid extends attr with Options[uuid, UUID]
  trait uri extends attr with Options[uri, URI]


  // Options ---------------------------------------------------------

  /** Attribute options.
   *
   * @group opt
   */
  sealed trait Options[Self, Tpe] {

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


    /** Alias to non-compatible attribute name like `type` or `first-name` etc.
     *
     * Molecule then creates an alias to the special name in the schema so that
     * queries will match both the attribute name and the alias.
     * */
    def alias(altAttrName: String): Self = ???

    def descr(s: String): Self = ???


    // Validation .................

    def validate(ok: Tpe => Boolean, failureMsg: String = ""): Self = ???
    def validate(err2msg: PartialFunction[Tpe, String]): Self = ???


    // Allowed values (like enumerations)
//    def allowed(first: Tpe, more: Tpe*): Self = ???
    def allowed(vs: Tpe*): Self = ???

    // Require other attributes to be asserted
    // Useful for tuples
    def require(attrs: attr*): Self = ???

    // Required for any entity using this namespace.
    // Can be applied to multiple attributes.
    // Used to guarantee minimum of data for namespace.
    val mandatory: Self = ???

    // hmm, difficult to implement... - possible?
    val value: Tpe = ???
  }


  trait stringOptions extends Options[string, String] {
    val fulltext: string = ???

    // Validation .................
    val email: string = ???
    def email(msg: String): string = ???
    def regex(expr: String, msg: String = ""): string = ???
  }


  trait refOptions[Self] extends Options[Self, Long] {

    /** Apply namespace type to reference.
     *
     * @tparam RefNs Ref namespace type
     */
    def apply[RefNs]: Self = ???

    /** Owner option.
     *
     * This entity owns the referenced entity/entities.
     *
     * - If this entity is deleted, this references are deleted too (and recursively if sub entities have owned entities)
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


  //  // Any ---------------------------------------------------------
  //
  //  /** Internal card-one Any attribute for multi-typed `v` values in log, Datoms and indexes.
  //   *
  //   * Do _not_ use in custom data models.
  //   *
  //   * It is only implemented internally for the `v` value of generic log and indexes.
  //   *
  //   * @group one
  //   */
  //  private[molecule] object oneAny extends optionBuilder[oneString, Any]
  //
  //  // Bidirectional ref ---------------------------------------------------------
  //
  //  /** Card-one bi-directional reference.
  //   *
  //   * @group bi
  //   */
  //  trait oneBi extends refOptionBuilder[oneBi]
  //  object oneBi extends oneBi
  //
  //
  //  /** Card-many bi-directional reference.
  //   *
  //   * @group bi
  //   */
  //  trait manyBi extends refOptionBuilder[manyBi]
  //  object manyBi extends manyBi
  //
  //
  //  // Bidirectional edge ---------------------------------------------------------
  //
  //  /** Card-one bi-directional edge reference.
  //   *
  //   * @group edge
  //   */
  //  trait oneBiEdge extends refOptionBuilder[oneBiEdge]
  //  object oneBiEdge extends oneBiEdge
  //
  //
  //  /** Card-many bi-directional edge reference.
  //   *
  //   * @group edge
  //   */
  //  trait manyBiEdge extends refOptionBuilder[manyBiEdge]
  //  object manyBiEdge extends manyBiEdge
  //
  //
  //  /** Bi-directional edge target attribute.
  //   *
  //   * @group edge
  //   */
  //  trait target extends refOptionBuilder[target]
  //  object target extends target
}

