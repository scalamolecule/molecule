package molecule.sql.h2.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.h2.setup.TestAsync_h2

object Test_Adjacent extends Adjacent with TestAsync_h2
object Test_CrossNs extends CrossNs with TestAsync_h2
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_h2
object Test_FilterAttr_id extends FilterAttr_id with TestAsync_h2
object Test_FilterAttrNested extends FilterAttrNested with TestAsync_h2
object Test_FilterAttrRef extends FilterAttrRef with TestAsync_h2
object Test_Semantics extends Semantics with TestAsync_h2
object Test_Sorting extends Sorting with TestAsync_h2
object Test_Types extends Types with TestAsync_h2
