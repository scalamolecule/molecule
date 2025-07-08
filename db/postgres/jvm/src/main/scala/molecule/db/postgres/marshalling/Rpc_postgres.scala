package molecule.db.postgres.marshalling

import molecule.db.common.marshalling.MoleculeBackend_SQL
import molecule.db.postgres.spi.Spi_postgres_sync


object Rpc_postgres extends Spi_postgres_sync with MoleculeBackend_SQL
