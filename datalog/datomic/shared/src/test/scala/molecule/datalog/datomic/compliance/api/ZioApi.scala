package molecule.datalog.datomic.compliance.api

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.api._
import molecule.datalog.datomic.setup.Api_datomic_zio

class ZioApi extends Test {
  ZioApi(this, Api_datomic_zio)
}
