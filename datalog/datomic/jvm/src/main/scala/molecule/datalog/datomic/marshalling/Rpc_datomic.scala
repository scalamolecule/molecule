package molecule.datalog.datomic.marshalling

import molecule.core.marshalling.Boopicklers.*
import molecule.datalog.datomic.spi.Spi_datomic_sync
import molecule.datalog.datomic.marshalling.MoleculeBackend_datomic

object Rpc_datomic extends Spi_datomic_sync with MoleculeBackend_datomic
