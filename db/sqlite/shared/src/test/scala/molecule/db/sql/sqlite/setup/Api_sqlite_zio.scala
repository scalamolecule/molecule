package molecule.db.sql.sqlite.setup

import molecule.db.common.api.{Api_zio, Api_zio_transact}
import molecule.db.sql.sqlite.spi.Spi_sqlite_zio

object Api_sqlite_zio
  extends Api_zio
    with Api_zio_transact
    with Spi_sqlite_zio
    with DbProviders_sqlite_zio


