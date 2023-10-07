package molecule.datalog.datomic.setup

import datomic.Peer
import molecule.base.api.Schema
import molecule.core.marshalling.DatomicProxy
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.coreTests.setup.CoreTestSuite
import molecule.datalog.datomic.facade.DatomicPeer
import scala.concurrent.Await
import scala.concurrent.duration._

trait DatomicTestSuite extends CoreTestSuite {

  override val database = "Datomic"
  override val platform = "jvm"

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
