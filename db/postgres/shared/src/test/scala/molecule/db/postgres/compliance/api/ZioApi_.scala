package molecule.db.postgres.compliance.api

import molecule.db
import molecule.db.compliance.test.api.ZioApi
import molecule.db.postgres.setup.Api_postgres_zio

object ZioApi_ extends ZioApi(Api_postgres_zio)
