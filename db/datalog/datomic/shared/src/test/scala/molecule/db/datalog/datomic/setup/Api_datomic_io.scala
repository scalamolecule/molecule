package molecule.db.datalog.datomic.setup

import molecule.db.core.api.Api_io
import molecule.db.datalog
import molecule.db.datalog.datomic.spi.Spi_datomic_io

object Api_datomic_io
  extends Api_io
    with Spi_datomic_io
    with DbProviders_datomic


