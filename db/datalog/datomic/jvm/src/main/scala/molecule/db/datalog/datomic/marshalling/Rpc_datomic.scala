package molecule.db.datalog.datomic.marshalling

import molecule.db.datalog.datomic.spi.Spi_datomic_sync

object Rpc_datomic extends Spi_datomic_sync with MoleculeBackend_datomic
