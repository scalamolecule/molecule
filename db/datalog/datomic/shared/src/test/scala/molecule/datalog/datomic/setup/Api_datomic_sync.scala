package molecule.datalog.datomic.setup

import molecule.core.api.Api_sync
import molecule.datalog.datomic.spi.Spi_datomic_sync

object Api_datomic_sync
  extends Api_sync
    with Spi_datomic_sync
    with DbProviders_datomic


