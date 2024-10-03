package molecule.sql.mysql.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_Adjacent extends Adjacent with Test_mysql_async
object Test_CrossNs extends CrossNs with Test_mysql_async
object Test_Types extends Types with Test_mysql_async
