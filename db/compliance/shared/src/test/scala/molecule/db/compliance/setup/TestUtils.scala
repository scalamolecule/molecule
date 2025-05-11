package molecule.db.compliance.setup

import molecule.db.core.util.{AggrUtils, JavaConversions}

trait TestUtils
  extends TestData
    with JavaConversions
    with TolerantEquality
    with AggrUtils
