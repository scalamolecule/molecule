package molecule.db.sql.h2.compliance.transaction

import molecule.db.compliance.test.action.Transactions_zio
import molecule.db.sql.h2.setup.Api_h2_zio

object Transactions_zio_ extends Transactions_zio(Api_h2_zio)