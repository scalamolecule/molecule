package molecule.db.sql.h2.marshalling

import molecule.db.core.marshalling.Boopicklers.*
import molecule.db.sql.core.marshalling.MoleculeBackend_SQL
import molecule.db.sql.h2.spi.Spi_h2_sync


object Rpc_h2 extends Spi_h2_sync with MoleculeBackend_SQL
