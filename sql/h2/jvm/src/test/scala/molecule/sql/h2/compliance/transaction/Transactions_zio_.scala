package molecule.sql.h2.compliance.transaction

import molecule.coreTests.spi.action.*
import molecule.sql.h2.setup.Api_h2_zio

object Transactions_zio_ extends Transactions_zio(Api_h2_zio)