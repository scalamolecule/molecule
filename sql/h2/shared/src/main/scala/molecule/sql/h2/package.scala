package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.h2.spi._

package object h2 {
  object async extends MoleculeImplicits_ with Api_async with Api_async_transact with Spi_h2_async
  object sync extends MoleculeImplicits_ with Api_sync with Api_sync_transact with Spi_h2_sync
  object zio extends MoleculeImplicits_ with Api_zio with Api_zio_transact with Spi_h2_zio
  object io extends MoleculeImplicits_ with Api_io with Api_io_transact with Spi_h2_io
}