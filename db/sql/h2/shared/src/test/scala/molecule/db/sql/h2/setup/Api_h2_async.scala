package molecule.db.sql.h2.setup

import molecule.core.api.{Api_async, Api_async_transact}
import molecule.db.sql
import molecule.db.sql.h2.spi.Spi_h2_async

object Api_h2_async
  extends Api_async
    with Api_async_transact
    with Spi_h2_async
    with DbProviders_h2


