package molecule.db.sql.sqlite.compliance.api

import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_zio

object ZioApi_ extends ZioApi(Api_sqlite_zio)
