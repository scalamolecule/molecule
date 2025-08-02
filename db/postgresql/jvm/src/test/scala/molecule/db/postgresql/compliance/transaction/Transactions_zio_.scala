package molecule.db.postgresql.compliance.transaction

import molecule.db.compliance.test.transaction.Transactions_zio
import molecule.db.postgresql.setup.Api_postgresql_zio

object Transactions_zio_ extends Transactions_zio(Api_postgresql_zio)
