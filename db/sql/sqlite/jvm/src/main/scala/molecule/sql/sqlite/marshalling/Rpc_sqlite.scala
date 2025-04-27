package molecule.sql.sqlite.marshalling

import molecule.core.marshalling.Boopicklers.*
import molecule.sql.core.marshalling.MoleculeBackend_SQL
import molecule.sql.sqlite.spi.Spi_sqlite_sync


object Rpc_sqlite extends Spi_sqlite_sync with MoleculeBackend_SQL