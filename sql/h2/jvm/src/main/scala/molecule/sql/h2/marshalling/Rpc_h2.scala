package molecule.sql.h2.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.h2.spi.SpiSync_h2


object Rpc_h2 extends SpiSync_h2 with Rpc_SQL
