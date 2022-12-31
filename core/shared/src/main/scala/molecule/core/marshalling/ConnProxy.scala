package molecule.core.marshalling

import java.util.UUID
import molecule.base.ast.SchemaAST.{Cardinality, MetaNs}
import molecule.core.marshalling.dbView.DbView

sealed trait ConnProxy {
  /** Seq of edn strings to transact schema. Supplied from generated boilerplate code. */
  val schema: Seq[String]

  /** Map of attribute meta data per namespace. Supplied from generated boilerplate code.
   *
   * Ns -> MetaNs
   * */
  val nsMap: Map[String, MetaNs]

  /** Map of attribute meta data. Supplied from generated boilerplate code.
   *
   * Attr name -> (Cardinality, type string)
   * */
  val attrMap: Map[String, (Cardinality, String)]

  val uniqueAttrs: List[String]

  /** Internal setting for test db status
   *
   * 0 inactive
   * 1 active
   * -1 de-activate signal
   */
  val testDbStatus: Int

  /** Internal holder of optional current test DbView object */
  val testDbView: Option[DbView]

  /** Internal holder of optional current ad-hoc DbView object */
  val adhocDbView: Option[DbView]

  /** Unique internal identifier for cached proxy connection on server side */
  val uuid: UUID
}


/** Datomic Peer proxy connection
 *
 * @param protocol     Datomic protocol: mem, dev or pro
 * @param dbIdentifier Datomic db identifier, like "localhost:4334/mbrainz-1968-1973"
 * @param schema       Seq of schema transaction data from generated boilerplate code
 * @param attrMap      Map of attribute data from generated boilerplate code
 * @param testDbStatus Internally applied setting, not intended to be set by user
 * @param testDbView   Internally applied setting, not intended to be set by user
 * @param adhocDbView  Internally applied setting, not intended to be set by user
 * @param uuid         Internally applied setting, not intended to be set by user
 */
case class DatomicPeerProxy(
  protocol: String,
  dbIdentifier: String,

  schema: Seq[String],
  nsMap: Map[String, MetaNs],
  attrMap: Map[String, (Cardinality, String)],
  uniqueAttrs: List[String],

  // Internal settings, not intended to be set by user
  testDbStatus: Int = 0,
  testDbView: Option[DbView] = None,
  adhocDbView: Option[DbView] = None,
  uuid: UUID = UUID.randomUUID()
) extends ConnProxy