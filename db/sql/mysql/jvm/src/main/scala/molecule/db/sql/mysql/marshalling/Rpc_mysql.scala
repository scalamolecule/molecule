package molecule.db.sql.mysql.marshalling

import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.sql.core.marshalling.MoleculeBackend_SQL
import molecule.db.sql.mysql.spi.Spi_mysql_sync


object Rpc_mysql extends Spi_mysql_sync with MoleculeBackend_SQL
