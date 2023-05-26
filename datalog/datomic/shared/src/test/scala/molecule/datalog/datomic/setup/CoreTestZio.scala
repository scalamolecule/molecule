package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.DatomicSpiZio

trait CoreTestZio extends DatomicZioSpec with DatomicSpiZio
