package molecule.sql.mysql.marshalling

import molecule.core.marshalling.Boopicklers.*
import molecule.sql.core.marshalling.MoleculeBackend_SQL
import molecule.sql.mysql.spi.Spi_mysql_sync


object Rpc_mysql extends Spi_mysql_sync with MoleculeBackend_SQL
