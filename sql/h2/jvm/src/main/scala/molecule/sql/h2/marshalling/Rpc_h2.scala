package molecule.sql.h2.marshalling

import molecule.core.marshalling.Boopicklers._
import molecule.sql.core.marshalling.Rpc_SQL
import molecule.sql.h2.spi.Spi_h2_sync


object Rpc_h2 extends Spi_h2_sync with Rpc_SQL
