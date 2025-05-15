package molecule.db.sql.h2.setup

import molecule.db.core.api.{Api_zio, Api_zio_transact}
import molecule.db.sql.h2.spi.Spi_h2_zio

object Api_h2_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_h2_zio
    with DbProviders_h2_zio


