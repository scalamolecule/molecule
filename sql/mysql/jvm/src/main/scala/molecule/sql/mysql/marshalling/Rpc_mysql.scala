package molecule.sql.mysql.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.mysql.spi.SpiSync_mysql


object Rpc_mysql extends SpiSync_mysql with Rpc_SQL
