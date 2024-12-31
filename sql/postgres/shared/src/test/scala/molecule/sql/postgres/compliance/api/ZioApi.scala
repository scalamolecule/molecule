package molecule.sql.postgres.compliance.api

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Api_postgres_zio

class ZioApi extends MUnitSuite {
  ZioApi(this, Api_postgres_zio)
}
