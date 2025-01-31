package molecule.datalog

import molecule.core.MoleculeActions_
import molecule.core.api._
import molecule.datalog.datomic.spi._

package object datomic {

  // Top-level transaction api not implemented for Datomic since it hase no rollback
  object async extends MoleculeActions_ with Api_async with Spi_datomic_async
  object sync extends MoleculeActions_ with Api_sync with Spi_datomic_sync
  object zio extends MoleculeActions_ with Api_zio with Spi_datomic_zio
  object io extends MoleculeActions_ with Api_io with Spi_datomic_io
}