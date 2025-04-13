package molecule.sql.sqlite.compliance.transaction

import molecule.coreTests.spi.action._
import molecule.sql.sqlite.setup.Api_sqlite_zio

object Transactions_zio_ extends Transactions_zio(Api_sqlite_zio)
