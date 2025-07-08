package molecule.db.sql.postgres.compliance.transaction

import molecule.db.compliance.test.action.Transactions_zio
import molecule.db.sql.postgres.setup.Api_postgres_zio

object Transactions_zio_ extends Transactions_zio(Api_postgres_zio)
