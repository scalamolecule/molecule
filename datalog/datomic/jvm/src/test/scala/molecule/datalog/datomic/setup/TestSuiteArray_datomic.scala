package molecule.datalog.datomic.setup

import datomic.Peer
import molecule.base.api.Schema
import molecule.core.marshalling.DatomicProxy
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.coreTests.setup.{CoreTestSuite, CoreTestSuiteBase}
import molecule.coreTests.util.Array2List
import molecule.datalog.datomic.facade.DatomicPeer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

trait TestSuiteArray_datomic extends CoreTestSuiteBase with Array2List {

  override val platform              = "jvm"
  override val database              = "Datomic"
  override val isJsPlatform: Boolean = false

  override def delay[T](ms: Int)(body: => T): Future[T] = ???

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val proxy = DatomicProxy(
      "mem", "",
      schema.datomicPartitions,
      schema.datomicSchema,
      schema.datomicAliases,
      schema.metaSchema,
      schema.nsMap,
      schema.attrMap,
      schema.uniqueAttrs,
    )

    // Block to enable supplying Connection instead of Future[Connection] to tests
    val conn = Await.result(
      DatomicPeer.recreateDb(proxy, "mem", ""),
      10.second
    )
    test(conn)
  }


  def printQuery(q: String)(implicit conn: Conn): Unit = {
    println("-------------------------------------")
    Peer.q(q, conn.db.asInstanceOf[AnyRef]).forEach { r => println(r) }
  }
}
