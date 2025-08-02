package molecule.db.mysql.compliance.transaction

import molecule.db.compliance.test.transaction.Transactions_zio
import molecule.db.mysql.setup.Api_mysql_zio

object Transactions_zio_ extends Transactions_zio(Api_mysql_zio)