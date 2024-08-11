package molecule.sql.sqlite.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_Adjacent extends Adjacent with TestAsync_sqlite
object Test_CrossNs extends CrossNs with TestAsync_sqlite
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_sqlite
object Test_Types extends Types with TestAsync_sqlite
