package molecule.sql.mariadb.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_Adjacent extends Adjacent with TestAsync_mariadb
object Test_CrossNs extends CrossNs with TestAsync_mariadb
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_mariadb
object Test_Types extends Types with TestAsync_mariadb
