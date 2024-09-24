package molecule.sql.postgres.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.postgres.spi.Spi_postgres_sync


object Rpc_postgres extends Spi_postgres_sync with Rpc_SQL
