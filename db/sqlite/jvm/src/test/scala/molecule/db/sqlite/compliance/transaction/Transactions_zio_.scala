package molecule.db.sqlite.compliance.transaction

import molecule.db.compliance.test.transaction.Transactions_zio
import molecule.db.sqlite.setup.Api_sqlite_zio

object Transactions_zio_ extends Transactions_zio(Api_sqlite_zio)
