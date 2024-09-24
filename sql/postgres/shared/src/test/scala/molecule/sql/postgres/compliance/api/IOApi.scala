package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Test_postgres_io

object Test_IOApi extends IOApi with Test_postgres_io
