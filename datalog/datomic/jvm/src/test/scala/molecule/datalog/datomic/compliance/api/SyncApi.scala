package molecule.datalog.datomic.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.datalog.datomic.setup.TestSync_datomic

object SyncApi extends SyncApi with TestSync_datomic
