package molecule.sql.mariadb.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_Adjacent extends Adjacent with TestAsync_mariadb
object Test_CrossNs extends CrossNs with TestAsync_mariadb
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_mariadb
object Test_FilterAttr_id extends FilterAttr_id with TestAsync_mariadb
object Test_FilterAttrNested extends FilterAttrNested with TestAsync_mariadb
object Test_FilterAttrRef extends FilterAttrRef with TestAsync_mariadb
object Test_Semantics extends Semantics with TestAsync_mariadb
object Test_Sorting extends Sorting with TestAsync_mariadb
object Test_Types extends Types with TestAsync_mariadb
