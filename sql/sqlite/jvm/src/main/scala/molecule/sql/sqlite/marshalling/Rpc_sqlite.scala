package molecule.sql.sqlite.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.sqlite.spi.SpiSync_sqlite


object Rpc_sqlite extends SpiSync_sqlite with Rpc_SQL