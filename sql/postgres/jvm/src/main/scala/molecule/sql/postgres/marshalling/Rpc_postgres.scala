package molecule.sql.postgres.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.postgres.spi.SpiSync_postgres


object Rpc_postgres extends SpiSync_postgres with Rpc_SQL
