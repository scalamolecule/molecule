//package molecule.db.datomic.marshalling
//
//import java.util.UUID
//import molecule.base.ast.SchemaAST.{Cardinality, MetaNs}
//import molecule.core.marshalling.ConnProxy
//import molecule.core.marshalling.dbView.DbView
//
///**
// *
// * @param protocol     Datomic protocol: mem, dev or pro
// * @param dbIdentifier Datomic db identifier, like "localhost:4334/mbrainz-1968-1973"
// * @param schema       Seq of schema transaction data from generated boilerplate code
// * @param nsMap        Map of attribute meta data per namespace
// * @param attrMap      Map of attribute data from generated boilerplate code
// * @param uniqueAttrs  Names of unique attributes
// * @param testDbStatus Internally applied setting, not intended to be set by user
// * @param testDbView   Internally applied setting, not intended to be set by user
// * @param adhocDbView  Internally applied setting, not intended to be set by user
// * @param uuid         Internally applied setting, not intended to be set by user
// */
//case class DatomicPeerProxy(
//  protocol: String,
//  dbIdentifier: String,
//
//  schema: Seq[String],
//  nsMap: Map[String, MetaNs],
//  attrMap: Map[String, (Cardinality, String)],
//  uniqueAttrs: List[String],
//
//  // Internal settings, not intended to be set by user
//  testDbStatus: Int = 0,
//  testDbView: Option[DbView] = None,
//  adhocDbView: Option[DbView] = None,
//  uuid: UUID = UUID.randomUUID()
//) extends ConnProxy
