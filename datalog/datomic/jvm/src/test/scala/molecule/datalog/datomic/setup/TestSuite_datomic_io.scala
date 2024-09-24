package molecule.datalog.datomic.setup

import molecule.base.api.Schema
import molecule.core.marshalling.DatomicProxy
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.coreTests.setup.CoreTestSuite_io
import molecule.datalog.datomic.facade.DatomicPeer
import scala.concurrent.Await
import scala.concurrent.duration._


trait TestSuite_datomic_io extends CoreTestSuite_io {

  override val platform = "jvm"
  override val database = "Datomic"

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
}
