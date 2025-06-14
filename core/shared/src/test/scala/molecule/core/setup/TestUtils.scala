package molecule.core.setup

import molecule.core.util.{AggrUtils, JavaConversions}

trait TestUtils
  extends TestData
    with JavaConversions
    with TolerantEquality
    with AggrUtils
