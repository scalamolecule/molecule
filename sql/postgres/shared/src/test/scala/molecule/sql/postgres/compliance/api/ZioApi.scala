package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Test_postgres_zio

object Test_ZioApi extends ZioApi with Test_postgres_zio
