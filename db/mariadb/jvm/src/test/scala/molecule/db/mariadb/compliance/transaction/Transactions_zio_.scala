package molecule.db.mariadb.compliance.transaction

import molecule.db.compliance.test.action.Transactions_zio
import molecule.db.mariadb.setup.Api_mariadb_zio

object Transactions_zio_ extends Transactions_zio(Api_mariadb_zio)