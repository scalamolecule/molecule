package molecule.db.sql.sqlite.marshalling

import molecule.db.common.marshalling.MoleculeBackend_SQL
import molecule.db.sql.sqlite.spi.Spi_sqlite_sync


object Rpc_sqlite extends Spi_sqlite_sync with MoleculeBackend_SQL