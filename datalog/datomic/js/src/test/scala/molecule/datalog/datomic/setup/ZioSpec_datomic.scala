package molecule.datalog.datomic.setup

import molecule.base.api.Schema
import molecule.core.marshalling.{DatomicProxy, RpcRequest}
import molecule.core.spi.Conn
import molecule.coreTests.setup.CoreTestZioSpec
import molecule.datalog.datomic.facade.DatomicConn_JS
import zio.ZLayer


trait ZioSpec_datomic extends CoreTestZioSpec {

  override val platform = "js"
  override val database = "Datomic"

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
    ZLayer.succeed(DatomicConn_JS.apply(proxy, RpcRequest.request))
  }
}
