package molecule.db.h2.compliance.api

import molecule.db
import molecule.db.compliance.test.api.ZioApi
import molecule.db.h2.setup.Api_h2_zio

object ZioApiTest extends ZioApi(Api_h2_zio)
