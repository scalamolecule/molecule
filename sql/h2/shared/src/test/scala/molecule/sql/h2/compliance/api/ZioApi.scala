package molecule.sql.h2.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.h2.setup.Test_h2_zio

object Test_ZioApi extends ZioApi with Test_h2_zio
