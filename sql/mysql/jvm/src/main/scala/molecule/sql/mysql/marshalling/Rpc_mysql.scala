package molecule.sql.mysql.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.mysql.spi.Spi_mysql_sync


object Rpc_mysql extends Spi_mysql_sync with Rpc_SQL
