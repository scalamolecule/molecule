package molecule.sql.mariadb.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mariadb.setup.Api_mariadb_zio

object ZioApi_ extends ZioApi(Api_mariadb_zio)
