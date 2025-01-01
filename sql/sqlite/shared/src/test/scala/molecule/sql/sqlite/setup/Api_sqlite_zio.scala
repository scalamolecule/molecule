package molecule.sql.sqlite.setup

import molecule.core.api.{Api_zio, Api_zio_transact}
import molecule.sql.sqlite.spi.Spi_sqlite_zio

object Api_sqlite_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_sqlite_zio
    with DbProviders_sqlite


