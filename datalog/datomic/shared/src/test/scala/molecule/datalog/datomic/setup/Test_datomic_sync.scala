package molecule.datalog.datomic.setup

import molecule.datalog.datomic.spi.Spi_datomic_sync

trait Test_datomic_sync extends TestSuite_datomic with Spi_datomic_sync
