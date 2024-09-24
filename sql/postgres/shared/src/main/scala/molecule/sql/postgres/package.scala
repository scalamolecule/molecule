package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.postgres.spi._

package object postgres {
  object async extends MoleculeImplicits_ with Api_async with Spi_postgres_async
  object sync extends MoleculeImplicits_ with Api_sync with Spi_postgres_sync
  object zio extends MoleculeImplicits_ with Api_zio with Spi_postgres_zio
}