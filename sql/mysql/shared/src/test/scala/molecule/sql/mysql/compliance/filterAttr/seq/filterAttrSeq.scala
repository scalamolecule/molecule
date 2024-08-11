package molecule.sql.mysql.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_Adjacent extends Adjacent with TestAsync_mysql
object Test_CrossNs extends CrossNs with TestAsync_mysql
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_mysql
object Test_Types extends Types with TestAsync_mysql
