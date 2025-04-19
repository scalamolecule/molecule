package molecule.sql.postgres.marshalling

import molecule.core.marshalling.Boopicklers.*
import molecule.sql.core.marshalling.MoleculeBackend_SQL
import molecule.sql.postgres.spi.Spi_postgres_sync


object Rpc_postgres extends Spi_postgres_sync with MoleculeBackend_SQL
