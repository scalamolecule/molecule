package molecule.db.sql.h2.setup

import molecule.core.api.{Api_io, Api_io_transact}
import molecule.db.sql
import molecule.db.sql.h2.spi.Spi_h2_io

object Api_h2_io
  extends Api_io
    with Api_io_transact
    with Spi_h2_io
    with DbProviders_h2


