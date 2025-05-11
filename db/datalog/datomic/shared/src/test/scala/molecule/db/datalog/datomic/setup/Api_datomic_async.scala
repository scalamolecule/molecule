package molecule.db.datalog.datomic.setup

import molecule.db.core.api.Api_async
import molecule.db.datalog
import molecule.db.datalog.datomic.spi.Spi_datomic_async

object Api_datomic_async
  extends Api_async
    with Spi_datomic_async
    with DbProviders_datomic


