package molecule.db.datalog.datomic.setup

import molecule.db.core.api.Api_sync
import molecule.db.datalog
import molecule.db.datalog.datomic.spi.Spi_datomic_sync

object Api_datomic_sync
  extends Api_sync
    with Spi_datomic_sync
    with DbProviders_datomic


