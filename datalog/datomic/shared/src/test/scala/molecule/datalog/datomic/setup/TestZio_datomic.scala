package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.SpiZio_datomic

trait TestZio_datomic extends ZioSpec_datomic with SpiZio_datomic
