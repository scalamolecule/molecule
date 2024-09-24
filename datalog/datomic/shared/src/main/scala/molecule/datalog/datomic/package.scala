package molecule.datalog

import molecule.core.MoleculeImplicits_
import molecule.core.api.{Api_async, Api_io, Api_sync, Api_zio}
import molecule.datalog.datomic.spi._
import molecule.datalog.datomic.spi.Spi_datomic_io

package object datomic {
  object async extends MoleculeImplicits_ with Api_async with Spi_datomic_async
  object sync extends MoleculeImplicits_ with Api_sync with Spi_datomic_sync
  object zio extends MoleculeImplicits_ with Api_zio with Spi_datomic_zio
  object io extends MoleculeImplicits_ with Api_io with Spi_datomic_io
}