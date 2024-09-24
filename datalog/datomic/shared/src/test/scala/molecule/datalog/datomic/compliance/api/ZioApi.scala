package molecule.datalog.datomic.compliance.api

import molecule.coreTests.spi.api._
import molecule.datalog.datomic.setup.Test_datomic_zio

object Test_ZioApi extends ZioApi with Test_datomic_zio
