package molecule.coreTests.setup

import molecule.core.MoleculeImplicits_
import molecule.core.util.{AggrUtils, JavaConversions}

trait TestUtils
  extends TestData
    with JavaConversions
    with TolerantEquality
    with AggrUtils
    with MoleculeImplicits_
