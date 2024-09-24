package molecule.sql.mariadb.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.mariadb.spi.Spi_mariadb_sync


object Rpc_mariadb extends Spi_mariadb_sync with Rpc_SQL
