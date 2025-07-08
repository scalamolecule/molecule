package molecule.db.h2.setup

import molecule.db
import molecule.db.common.api.{Api_io, Api_io_transact}
import molecule.db.h2.spi.Spi_h2_io

object Api_h2_io
  extends Api_io
    with Api_io_transact
    with Spi_h2_io
    with DbProviders_h2


