package molecule.datalog.datomic.setup

import molecule.base.api.Schema
import molecule.core.marshalling.DatomicProxy
import molecule.core.spi.Conn
import molecule.core.util.Executor._
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.datalog.datomic.facade.DatomicPeer
import zio.{ZIO, ZLayer}


trait ZioSpec_datomic extends CoreTestZioSpec {

  override val database = "Datomic"
  override val platform = "jvm"

  override def inMem[T](schema: Schema): ZLayer[T, Throwable, Conn] = {
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
    ZLayer.scoped(
      ZIO.fromFuture(
        _ => DatomicPeer.recreateDb(proxy, "mem", "")
      )
    )
  }
}
