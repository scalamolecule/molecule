package molecule.sql.sqlite.compliance.filterAttr.seq

import molecule.coreTests.spi.filterAttr.seq._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_Adjacent extends Adjacent with Test_sqlite_async
object Test_CrossNs extends CrossNs with Test_sqlite_async
object Test_Types extends Types with Test_sqlite_async
