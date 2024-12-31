package molecule.datalog.datomic.setup

import molecule.core.api.Api_async
import molecule.datalog.datomic.spi.Spi_datomic_async

object Api_datomic_async
  extends Api_async
    with Spi_datomic_async
    with DbProviders_datomic


