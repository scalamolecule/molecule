package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api.*
import molecule.sql.postgres.setup.Api_postgres_zio

object ZioApi_ extends ZioApi(Api_postgres_zio)
