package molecule.datalog.datomic.setup

import molecule.core.api.Api_zio
import molecule.datalog.datomic.spi.Spi_datomic_zio

object Api_datomic_zio
  extends Api_zio
    with Spi_datomic_zio
    with DbProviders_datomic_zio


