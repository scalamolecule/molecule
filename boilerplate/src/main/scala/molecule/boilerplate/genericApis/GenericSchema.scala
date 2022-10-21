//package molecule.boilerplate.genericApis
//
//import molecule.boilerplate.markers.namespaceMarkers._
//import molecule.boilerplate.api.generic.dsl.Schema.{Schema_, Schema_0_0}
//
//
///** Container for Schema object.
//  *
//  * Some Datomic types map to two Scala types:
//  *
//  * Datomic/Scala types:
//  *
//  *  - '''string''' - String
//  *  - '''boolean''' - Boolean
//  *  - '''long''' - Int, Long
//  *  - '''double''' - Double
//  *  - '''bigint''' - BigInt
//  *  - '''bigdec''' - BigDecimal
//  *  - '''instant''' - java.util.Date
//  *  - '''uuid''' - java.util.UUID
//  *  - '''uri''' - java.net.URI
//  *  - '''ref''' - Long
//  * */
//trait GenericSchema {
//
//  /** Schema object to start Schema molecule. */
//  object Schema extends Schema_0_0[Schema_, Init] with FirstNS
//}