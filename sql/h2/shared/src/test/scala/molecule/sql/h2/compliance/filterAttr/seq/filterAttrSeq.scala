package molecule.sql.h2.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.h2.setup.Test_h2_async

object Test_Adjacent extends Adjacent with Test_h2_async
object Test_CrossNs extends CrossNs with Test_h2_async
object Test_Types extends Types with Test_h2_async
