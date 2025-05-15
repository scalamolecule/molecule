package molecule.db.sql.postgres.marshalling

import molecule.db.sql.core.marshalling.MoleculeBackend_SQL
import molecule.db.sql.postgres.spi.Spi_postgres_sync


object Rpc_postgres extends Spi_postgres_sync with MoleculeBackend_SQL
