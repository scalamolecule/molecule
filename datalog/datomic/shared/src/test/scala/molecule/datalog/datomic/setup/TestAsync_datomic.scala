package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.SpiAsync_datomic

trait TestAsync_datomic extends DatomicTestSuite with SpiAsync_datomic
