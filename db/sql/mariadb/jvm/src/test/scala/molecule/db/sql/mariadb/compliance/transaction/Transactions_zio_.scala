package molecule.db.sql.mariadb.compliance.transaction

import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_zio

object Transactions_zio_ extends Transactions_zio(Api_mariadb_zio)