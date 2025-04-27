package molecule.db.sql.sqlite.compliance.transaction

import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_zio

object Transactions_zio_ extends Transactions_zio(Api_sqlite_zio)
