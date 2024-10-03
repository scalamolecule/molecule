package molecule.sql.mariadb.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_Adjacent extends Adjacent with Test_mariadb_async
object Test_CrossNs extends CrossNs with Test_mariadb_async
object Test_Types extends Types with Test_mariadb_async
