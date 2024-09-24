package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.Spi_datomic_zio

trait Test_datomic_zio extends TestSuite_datomic_zio with Spi_datomic_zio
