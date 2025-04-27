package molecule.db.sql.mariadb.compliance.api

import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_zio

object ZioApi_ extends ZioApi(Api_mariadb_zio)
