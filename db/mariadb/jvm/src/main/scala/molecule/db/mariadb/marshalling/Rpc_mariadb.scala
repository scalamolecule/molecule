package molecule.db.mariadb.marshalling

import molecule.db.common.marshalling.MoleculeBackend_SQL
import molecule.db.mariadb.spi.Spi_mariadb_sync


object Rpc_mariadb extends Spi_mariadb_sync with MoleculeBackend_SQL
