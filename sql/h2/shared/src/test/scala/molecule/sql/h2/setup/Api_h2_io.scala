package molecule.sql.h2.setup

import molecule.core.api.Api_io
import molecule.sql.h2.spi.Spi_h2_io

object Api_h2_io
  extends Api_io
    with Spi_h2_io
    with DbProviders_h2


