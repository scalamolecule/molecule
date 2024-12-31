package molecule.sql.h2.setup

import molecule.core.api.Api_zio
import molecule.sql.h2.spi.Spi_h2_zio

object Api_h2_zio
  extends Api_zio
    with Spi_h2_zio
    with DbProviders_h2


