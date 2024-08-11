package molecule.sql.mysql.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_Adjacent extends Adjacent with TestAsync_mysql
object Test_CrossNs extends CrossNs with TestAsync_mysql
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_mysql
object Test_FilterAttr_id extends FilterAttr_id with TestAsync_mysql
object Test_FilterAttrNested extends FilterAttrNested with TestAsync_mysql
object Test_FilterAttrRef extends FilterAttrRef with TestAsync_mysql
object Test_Semantics extends Semantics with TestAsync_mysql
object Test_Sorting extends Sorting with TestAsync_mysql
object Test_Types extends Types with TestAsync_mysql
