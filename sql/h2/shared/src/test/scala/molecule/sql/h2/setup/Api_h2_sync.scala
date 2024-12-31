package molecule.sql.h2.setup

import molecule.core.api.Api_sync
import molecule.sql.h2.spi.Spi_h2_sync

object Api_h2_sync
  extends Api_sync
    with Spi_h2_sync
    with DbProviders_h2


