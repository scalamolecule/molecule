package molecule.db.sql.mariadb.marshalling

import molecule.db.common.marshalling.MoleculeBackend_SQL
import molecule.db.sql.mariadb.spi.Spi_mariadb_sync


object Rpc_mariadb extends Spi_mariadb_sync with MoleculeBackend_SQL
