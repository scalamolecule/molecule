package molecule

import molecule.base.api.DataModelApi

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
case class DataModel(maxArity: Int) extends DataModelApi
