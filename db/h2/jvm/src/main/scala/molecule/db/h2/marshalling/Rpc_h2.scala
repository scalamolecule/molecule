package molecule.db.h2.marshalling

import molecule.db.common.marshalling.MoleculeBackend_SQL
import molecule.db.h2.spi.Spi_h2_sync


object Rpc_h2 extends Spi_h2_sync with MoleculeBackend_SQL
