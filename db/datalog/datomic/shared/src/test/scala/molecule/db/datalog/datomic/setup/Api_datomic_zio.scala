package molecule.db.datalog.datomic.setup

import molecule.db.core.api.Api_zio
import molecule.db.datalog.datomic.spi.Spi_datomic_zio

object Api_datomic_zio
  extends Api_zio
    with Spi_datomic_zio
    with DbProviders_datomic_zio


