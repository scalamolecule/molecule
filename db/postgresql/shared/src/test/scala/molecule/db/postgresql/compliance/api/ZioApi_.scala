package molecule.db.postgresql.compliance.api

import molecule.db
import molecule.db.compliance.test.api.ZioApi
import molecule.db.postgresql.setup.Api_postgresql_zio

object ZioApi_ extends ZioApi(Api_postgresql_zio)
