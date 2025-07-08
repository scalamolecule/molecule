package molecule.db.mysql.marshalling

import molecule.db.common.marshalling.MoleculeBackend_SQL
import molecule.db.mysql.spi.Spi_mysql_sync


object Rpc_mysql extends Spi_mysql_sync with MoleculeBackend_SQL
