package molecule.sql.h2.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.h2.setup.TestAsync_h2

object Test_Adjacent extends Adjacent with TestAsync_h2
object Test_CrossNs extends CrossNs with TestAsync_h2
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_h2
object Test_Types extends Types with TestAsync_h2
