package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.SpiAsync_datomic

trait TestAsync_datomic extends TestSuite_datomic with SpiAsync_datomic
