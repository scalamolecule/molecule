package molecule.db.h2.setup

import molecule.db
import molecule.db.common.api.{Api_zio, Api_zio_transact}
import molecule.db.h2.spi.Spi_h2_zio

object Api_h2_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_h2_zio
    with DbProviders_h2_zio


