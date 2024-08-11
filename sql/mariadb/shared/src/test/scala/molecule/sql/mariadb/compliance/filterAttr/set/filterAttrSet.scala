package molecule.sql.mariadb.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_Adjacent extends Adjacent with TestAsync_mariadb
object Test_CrossNs extends CrossNs with TestAsync_mariadb
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_mariadb
object Test_Types extends Types with TestAsync_mariadb
