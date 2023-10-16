package molecule.datalog.datomic.compliance.api

import molecule.coreTests.spi.api._
import molecule.datalog.datomic.setup.TestZio_datomic

object ZioApi extends ZioApi with TestZio_datomic
