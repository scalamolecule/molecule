package molecule.db.h2.compliance.api

import molecule.core.setup.MUnit
import molecule.db.compliance.test.api.SyncApi
import molecule.db.h2.setup.Api_h2_sync

class SyncApiTest extends MUnit {
  SyncApi(this, Api_h2_sync)
}
