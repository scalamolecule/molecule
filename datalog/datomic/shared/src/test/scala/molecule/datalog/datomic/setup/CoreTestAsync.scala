package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.DatomicSpiAsync

trait CoreTestAsync extends DatomicTestSuite with DatomicSpiAsync
