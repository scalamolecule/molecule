package molecule.db.postgresql.marshalling

import molecule.db.common.marshalling.MoleculeBackend_SQL
import molecule.db.postgresql.spi.Spi_postgresql_sync


object Rpc_postgresql extends Spi_postgresql_sync with MoleculeBackend_SQL
