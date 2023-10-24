package molecule.document.mongodb.compliance.api

import molecule.coreTests.spi.api.SyncApi
import molecule.document.mongodb.setup.TestSync_mongodb
import molecule.document.mongodb.setup.TestSync_mongodb

object SyncApi extends SyncApi with TestSync_mongodb
