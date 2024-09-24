package molecule.sql.postgres.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.postgres.setup.Test_postgres_async

object Test_AsyncApi extends AsyncApi with Test_postgres_async
