package molecule.db.sql.h2.setup

import boopickle.Default.*
import molecule.db.compliance.domains.dsl.Refs.metadb.Refs_MetaDb_h2
import molecule.db.compliance.domains.dsl.Segments.metadb.Segments_MetaDb_h2
import molecule.db.compliance.domains.dsl.Types.metadb.Types_MetaDb_h2
import molecule.db.compliance.domains.dsl.Uniques.metadb.Uniques_MetaDb_h2
import molecule.db.compliance.domains.dsl.Validation.metadb.Validation_MetaDb_h2
import molecule.db.compliance.setup.DbConnection
import molecule.db.core.api.*
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.core.marshalling.JdbcProxy
import molecule.db.core.spi.Conn
import molecule.db.sql.core.facade.JdbcConn_JS
import zio.{ZIO, ZLayer}
import scala.util.Random

trait DbConnection_h2 extends DbConnection {

  pickleMetaDb.addConcreteType[Types_MetaDb_h2]
  pickleMetaDb.addConcreteType[Refs_MetaDb_h2]
  pickleMetaDb.addConcreteType[Uniques_MetaDb_h2]
  pickleMetaDb.addConcreteType[Validation_MetaDb_h2]
  pickleMetaDb.addConcreteType[Segments_MetaDb_h2]

  def run(test: Conn => Any, metaDb: MetaDb_h2): Any = {
    val url   = s"jdbc:h2:mem:test" + Random.nextInt().abs
    val proxy = JdbcProxy(url, metaDb)
    val conn  = JdbcConn_JS(proxy, "localhost", 8080)
    test(conn)
  }

  def connZLayer(metaDb: MetaDb): ZLayer[Any, Throwable, Conn] = {
    val url = "jdbc:h2:mem:test" + Random.nextInt().abs
    ZLayer.scoped(
      ZIO.attemptBlocking {
        val proxy = JdbcProxy(url, metaDb)
        JdbcConn_JS(proxy, "localhost", 8080)
      }
    )
  }
}
