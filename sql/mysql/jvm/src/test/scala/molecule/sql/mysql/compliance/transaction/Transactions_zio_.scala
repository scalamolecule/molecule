package molecule.sql.mysql.compliance.transaction

import molecule.coreTests.spi.action.*
import molecule.sql.mysql.setup.Api_mysql_zio

object Transactions_zio_ extends Transactions_zio(Api_mysql_zio)