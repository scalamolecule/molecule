package molecule.db.sql.mysql.compliance.transaction

import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_zio

object Transactions_zio_ extends Transactions_zio(Api_mysql_zio)