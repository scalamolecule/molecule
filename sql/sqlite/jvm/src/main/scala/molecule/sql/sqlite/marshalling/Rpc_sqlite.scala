package molecule.sql.sqlite.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.sqlite.spi.Spi_sqlite_sync


object Rpc_sqlite extends Spi_sqlite_sync with Rpc_SQL