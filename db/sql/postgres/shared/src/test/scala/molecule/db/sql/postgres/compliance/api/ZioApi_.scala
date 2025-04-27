package molecule.db.sql.postgres.compliance.api

import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_zio

object ZioApi_ extends ZioApi(Api_postgres_zio)
