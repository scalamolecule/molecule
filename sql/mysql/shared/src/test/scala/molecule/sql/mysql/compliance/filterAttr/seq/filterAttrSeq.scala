package molecule.sql.mysql.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_Adjacent extends Adjacent with Test_mysql_async
object Test_CrossNs extends CrossNs with Test_mysql_async
object Test_CrossNsOwned extends CrossNsOwned with Test_mysql_async
object Test_Types extends Types with Test_mysql_async
