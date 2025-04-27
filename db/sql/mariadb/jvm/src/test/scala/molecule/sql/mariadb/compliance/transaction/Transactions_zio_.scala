package molecule.sql.mariadb.compliance.transaction

import molecule.coreTests.spi.action.*
import molecule.sql.mariadb.setup.Api_mariadb_zio

object Transactions_zio_ extends Transactions_zio(Api_mariadb_zio)