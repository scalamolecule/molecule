package molecule.db.datalog.datomic.compliance.api

import molecule.coreTests.spi.api.*
import molecule.db.datalog
import molecule.db.datalog.datomic.setup.Api_datomic_zio

object ZioApi_ extends ZioApi(Api_datomic_zio)

