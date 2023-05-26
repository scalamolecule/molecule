package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.DatomicSpiSync

trait CoreTestSync extends DatomicTestSuite with DatomicSpiSync
