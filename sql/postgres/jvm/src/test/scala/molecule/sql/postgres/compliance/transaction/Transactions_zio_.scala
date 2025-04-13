package molecule.sql.postgres.compliance.transaction

import molecule.coreTests.spi.action._
import molecule.sql.postgres.setup.Api_postgres_zio

object Transactions_zio_ extends Transactions_zio(Api_postgres_zio)
