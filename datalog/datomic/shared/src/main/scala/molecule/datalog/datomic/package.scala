package molecule.datalog

import molecule.core.MoleculeImplicits_
import molecule.core.api.{ApiAsync, ApiIO, ApiSync, ApiZio}
import molecule.datalog.datomic.spi._
import molecule.datalog.datomic.spi.Spi_datomic_io

package object datomic {
  object async extends MoleculeImplicits_ with ApiAsync with Spi_datomic_async
  object sync extends MoleculeImplicits_ with ApiSync with Spi_datomic_sync
  object zio extends MoleculeImplicits_ with ApiZio with Spi_datomic_zio
  object io extends MoleculeImplicits_ with ApiIO with Spi_datomic_io
}