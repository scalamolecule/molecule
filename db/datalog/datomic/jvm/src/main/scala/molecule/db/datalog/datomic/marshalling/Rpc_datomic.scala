package molecule.db.datalog.datomic.marshalling

import boopickle.Default.*
import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.datalog.datomic.spi.Spi_datomic_sync

object Rpc_datomic extends Spi_datomic_sync with MoleculeBackend_datomic
