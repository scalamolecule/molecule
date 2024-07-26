package molecule.sql.mariadb.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.mariadb.spi.SpiSync_mariadb


object Rpc_mariadb extends SpiSync_mariadb with Rpc_SQL
