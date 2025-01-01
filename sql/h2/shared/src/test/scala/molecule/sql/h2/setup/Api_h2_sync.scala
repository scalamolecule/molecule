package molecule.sql.h2.setup

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.sql.h2.spi.Spi_h2_sync

object Api_h2_sync
  extends Api_sync
    with Api_sync_transact
    with Spi_h2_sync
    with DbProviders_h2


