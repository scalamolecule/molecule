package molecule.sql

import molecule.core.MoleculeImplicits_
import molecule.core.api._
import molecule.sql.postgres.spi._

package object postgres {
  object async extends MoleculeImplicits_ with ApiAsync with Spi_postgres_async
  object sync extends MoleculeImplicits_ with ApiSync with Spi_postgres_sync
  object zio extends MoleculeImplicits_ with ApiZio with Spi_postgres_zio
}