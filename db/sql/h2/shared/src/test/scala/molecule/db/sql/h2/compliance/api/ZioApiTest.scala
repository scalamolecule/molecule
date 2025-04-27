package molecule.db.sql.h2.compliance.api

import molecule.coreTests.spi.api.*
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_zio

object ZioApiTest extends ZioApi(Api_h2_zio)
