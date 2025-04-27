package molecule.db.sql.postgres.compliance.transaction

import molecule.coreTests.spi.action.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_zio

object Transactions_zio_ extends Transactions_zio(Api_postgres_zio)
