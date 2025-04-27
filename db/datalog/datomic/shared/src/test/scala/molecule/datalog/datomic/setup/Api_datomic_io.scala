package molecule.datalog.datomic.setup

import molecule.core.api.Api_io
import molecule.datalog.datomic.spi.Spi_datomic_io

object Api_datomic_io
  extends Api_io
    with Spi_datomic_io
    with DbProviders_datomic


