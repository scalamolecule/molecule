package molecule.datomic.base.facade


import java.net.URI
import java.util.{Date, Collection => jCollection, List => jList, Map => jMap}
import clojure.lang.{PersistentArrayMap, PersistentVector}
import com.cognitect.transit.impl.URIImpl
import datomic.Peer
import molecule.core.ast.elements
import molecule.core.ast.elements.Model
import molecule.datomic.base.ast.query.Query
import molecule.datomic.base.ast.tempDb.TempDb
import molecule.datomic.base.ast.transactionModel.Statement
import molecule.datomic.base.api.DatomicEntity
import molecule.datomic.base.ast.transactionModel
import scala.concurrent.{ExecutionContext, Future}


/** Facade to Datomic Connection.
  * */
trait Conn {

  def usingTempDb(tempDb: TempDb): Conn

  /** Convenience retrieval of time t. */
  def getT(tx: Long): Long = Peer.toT(tx)

  /** Flag to indicate if live database is used */
  def liveDbUsed: Boolean

  /** Manually apply a database to use.
    *
    * @param db
    */
  def testDb(db: DatomicDb): Unit

  /** Use test database as of now. */
  def testDbAsOfNow: Unit

  /** Use test database as of time t / tx id.
    *
    * @param t Long Time t or tx id
    */
  def testDbAsOf(t: Long): Unit

  /** Use test database as of date.
    *
    * @param d Date
    */
  def testDbAsOf(d: Date): Unit

  /** Use test database as of transaction report.
    *
    * @param txR Transaction report
    */
  def testDbAsOf(txR: TxReport): Unit

  /** Use test database since time t.
    *
    * @param t Long
    */
  def testDbSince(t: Long): Unit

  /** Use test database since date.
    *
    * @param d Date
    */
  def testDbSince(d: Date): Unit

  /** Use test database since transaction report.
    *
    * @param txR Transaction report
    */
  def testDbSince(txR: TxReport): Unit


  /** Use test database with temporary transaction data.
    * <br><br>
    * Transaction data can be supplied from any molecule:
    * {{{
    *   val benId = Person.name("Ben").save.eid
    *
    *   // Use temporary db with given transaction data applied
    *   conn.testDbWith(
    *     Person.name("liz").getSaveTx
    *   )
    *
    *   // Query using temporary database including Liz
    *   Person.name.get === List("Ben", "Liz")
    *
    *   // Multiple transactions can be applied
    *   conn.testDbWith(
    *     Person.name("Joe").getSaveTx,
    *     benId.getRetractTx
    *   )
    *   Person.name.get === List("Liz", "Joe")
    * }}}
    *
    * @param txData List of List of transaction [[transactionModel.Statement Statement]]'s
    */
  def testDbWith(txData: Seq[Seq[Statement]]*): Unit

  /** Use test database with temporary raw Java transaction data. */
  def testDbWith(txDataJava: jList[jList[AnyRef]]): Unit

  /* testDbHistory not implemented.
   * Instead, use `testDbAsOfNow`, make changes and get historic data with getHistory calls.
   * */

  /** Get out of test mode and back to live db. */
  def useLiveDb: Unit

  /** Get current test/live db. Test db has preference. */
  def db: DatomicDb

  /** Convenience method to retrieve entity directly from connection. */
  def entity(id: Any): DatomicEntity

  /** Transact Seq of Seqs of [[transactionModel.Statement]]s
    *
    * @param scalaStmts
    * @return [[molecule.datomic.base.facade.TxReport TxReport]]
    */
  def transact(scalaStmts: Seq[Seq[Statement]]): TxReport


  /** Transact edn files or other raw transaction data.
    * {{{
    *   val data_rdr2 = new FileReader("examples/resources/seattle/seattle-data1a.dtm")
    *   val rawTxStmts = Util.readAll(data_rdr2).get(0).asInstanceOf[java.util.List[Object]]
    *
    *   // transact
    *   val result: TxReport = conn.transact(rawTxStmts)
    * }}}
    *
    * @param javaStmts Raw transaction data, typically from edn file.
    * @return [[molecule.datomic.base.facade.TxReport TxReport]]
    */
  def transact(javaStmts: jList[_], scalaStmts: Seq[Seq[Statement]] = Nil): TxReport


  /** Asynchronously transact Seq of Seqs of [[transactionModel.Statement]]s
    *
    * @param scalaStmts
    * @return [[molecule.datomic.base.facade.TxReport TxReport]]
    */
  def transactAsync(scalaStmts: Seq[Seq[Statement]])
                   (implicit ec: ExecutionContext): Future[TxReport]

  /** Asynchronously transact edn files or other raw transaction data.
    * {{{
    *   val data_rdr2 = new FileReader("examples/resources/seattle/seattle-data1a.dtm")
    *   val rawTxStmts = Util.readAll(data_rdr2).get(0).asInstanceOf[java.util.List[Object]]
    *
    *   // transact
    *   val result: Future[TxReport] = conn.transactAsync(rawTxStmts)
    * }}}
    *
    * @param javaStmts Raw transaction data, typically from edn file.
    * @return Future with [[molecule.datomic.base.facade.TxReport TxReport]] with result of transaction
    */
  def transactAsync(javaStmts: jList[_], scalaStmts: Seq[Seq[Statement]] = Nil)
                   (implicit ec: ExecutionContext): Future[TxReport]


  /** Query Datomic directly with optional Scala inputs.
    * {{{
    *   // Sample data
    *   Ns.str.int.get === List(
    *     ("Liz", 37),
    *     ("Ben", 42),
    *   )
    *
    *   // Start out easily with a Datomic query from inspect output
    *   Ns.str.int.inspectGet // shows datomic query...
    *
    *   // Paste Datomic query into `q` call
    *   conn.q("""[:find  ?b ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin) === List(
    *     List("Liz", 37),
    *     List("Ben", 42)
    *   )
    *
    *   // Modify Datomic query to see result, for instance
    *   // by adding input to query and applying input value
    *   conn.q("""[:find  ?b ?c
    *            | :in    $ ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin, 42) === List(
    *     List("Ben", 42)
    *   )
    * }}}
    *
    * @param query  Datomic query string
    * @param inputs Optional input(s) to query
    * @return List[List[AnyRef]]
    * */
  def q(query: String, inputs: Any*): List[List[AnyRef]] =
    q(db, query, inputs.toSeq)


  /** Query Datomic directly with db value and optional Scala inputs.
    * {{{
    *   // Sample data
    *   Ns.str.int.get === List(
    *     ("Liz", 37),
    *     ("Ben", 42),
    *   )
    *
    *   // Start out easily with a Datomic query from inspect output
    *   Ns.str.int.inspectGet // shows datomic query...
    *
    *   // Paste Datomic query into `q` call and use some db value
    *   conn.q(conn.db,
    *          """[:find  ?b ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin) === List(
    *     List("Liz", 37),
    *     List("Ben", 42)
    *   )
    *
    *   // Modify Datomic query to see result, for instance
    *   // by adding input to query and applying input value
    *   conn.q(conn.db,
    *          """[:find  ?b ?c
    *            | :in    $ ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin,
    *          Seq(42) // input values in list
    *    ) === List(
    *     List("Ben", 42)
    *   )
    * }}}
    *
    * @param db     Any Datomic Database value (could be asOf(x) etc)
    * @param query  Datomic query string
    * @param inputs Seq of optional input(s) to query
    * @return List[List[AnyRef]]
    * */
  def q(db: DatomicDb, query: String, inputs: Seq[Any]): List[List[AnyRef]]


  /** Query Datomic directly with optional Scala inputs and get raw Java result.
    * {{{
    *   // Sample data
    *   Ns.str.int.get === List(
    *     ("Liz", 37),
    *     ("Ben", 42),
    *   )
    *
    *   // Start out easily with a Datomic query from inspect output
    *   Ns.str.int.inspectGet // shows datomic query...
    *
    *   // Paste Datomic query into `q` call
    *   conn.q("""[:find  ?b ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin)
    *       .toString === """[["Liz" 37], ["Ben" 42]]"""
    *
    *   // Modify Datomic query to see result, for instance
    *   // by adding input to query and applying input value
    *   conn.q("""[:find  ?b ?c
    *            | :in    $ ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin, 42).toString === """[["Ben" 42]]"""
    * }}}
    *
    * @param query  Datomic query string
    * @param inputs Optional input(s) to query
    * @return java.util.Collection[java.util.List[AnyRef]]
    * */
  def qRaw(query: String, inputs: Any*): jCollection[jList[AnyRef]] =
    qRaw(db, query, inputs)


  /** Query Datomic directly with db value and optional Scala inputs and get raw Java result.
    * {{{
    *   // Sample data
    *   Ns.str.int.get === List(
    *     ("Liz", 37),
    *     ("Ben", 42),
    *   )
    *
    *   // Get some Datomic query from inspect output
    *   Ns.str.int.inspectGet // shows datomic query...
    *
    *   // Paste Datomic query into `q` call and use some db value
    *   conn.q(conn.db,
    *          """[:find  ?b ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin)
    *       .toString === """[["Liz" 37], ["Ben" 42]]"""
    *
    *   // Modify Datomic query to see result, for instance
    *   // by adding input to query and applying input value
    *   conn.q(conn.db,
    *          """[:find  ?b ?c
    *            | :in    $ ?c
    *            | :where [?a :Ns/str ?b]
    *            |        [?a :Ns/int ?c]]""".stripMargin,
    *          Seq(42) // input values in list
    *    ).toString === """[["Ben" 42]]"""
    * }}}
    *
    * @param db      Any Datomic Database value (could be asOf(x) etc)
    * @param query   Datomic query string
    * @param inputs0 Seq of optional input(s) to query
    * @return java.util.Collection[java.util.List[AnyRef]]
    * */
  def qRaw(db: DatomicDb, query: String, inputs0: Seq[Any]): jCollection[jList[AnyRef]]


  /** Query Datomic with Model and Query to get raw Java data.
    * <br><br>
    * Will transparently relegate query depending on Model to:
    *
    * - Datalog query execution
    * - Datoms API accessing index
    * - Log API accessing log
    *
    * Return type (tuple matching the molecule) is the same for all 3 APIs so that
    * application code can query and access data of all molecules the same way.
    *
    * @param model [[elements.Model Model]] instance
    * @param query [[molecule.datomic.base.ast.query.Query Query]] instance
    * @return java.util.Collection[java.util.List[AnyRef]]
    * */
  def query(model: Model, query: Query): jCollection[jList[AnyRef]]


  def _query(model: Model, query: Query, _db: Option[DatomicDb] = None): jCollection[jList[AnyRef]]
  def _index(model: Model): jCollection[jList[AnyRef]]
}
