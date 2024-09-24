package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.Spi_datomic_io

trait Test_datomic_io extends TestSuite_datomic_io with Spi_datomic_io
