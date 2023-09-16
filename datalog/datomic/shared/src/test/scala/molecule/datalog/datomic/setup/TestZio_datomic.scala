package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.SpiZio_datomic

trait TestZio_datomic extends DatomicZioSpec with SpiZio_datomic
