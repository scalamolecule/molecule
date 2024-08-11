package molecule.sql.sqlite.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_Adjacent extends Adjacent with TestAsync_sqlite
object Test_CrossNs extends CrossNs with TestAsync_sqlite
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_sqlite
object Test_FilterAttr_id extends FilterAttr_id with TestAsync_sqlite
object Test_FilterAttrNested extends FilterAttrNested with TestAsync_sqlite
object Test_FilterAttrRef extends FilterAttrRef with TestAsync_sqlite
object Test_Semantics extends Semantics with TestAsync_sqlite
object Test_Sorting extends Sorting with TestAsync_sqlite
object Test_Types extends Types with TestAsync_sqlite
