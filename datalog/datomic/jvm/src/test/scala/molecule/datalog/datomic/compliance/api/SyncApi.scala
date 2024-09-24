package molecule.datalog.datomic.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.datalog.datomic.setup.Test_datomic_sync

object Test_SyncApi extends SyncApi with Test_datomic_sync
