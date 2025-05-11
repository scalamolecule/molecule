package molecule.db.datalog

import datomic.spi.*
import molecule.db.core.api.{Api_async, Api_io, Api_sync, Api_zio}

package object datomic {

  // Top-level transaction api not implemented for Datomic since it hase no rollback
  object async extends Api_async with Spi_datomic_async
  object sync extends Api_sync with Spi_datomic_sync
  object Zio extends Api_zio with Spi_datomic_zio
  object io extends Api_io with Spi_datomic_io
}