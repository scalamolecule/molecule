package molecule.sql.h2.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.h2.setup.Test_h2_async

object Test_FilterOne extends FilterOne with Test_h2_async
object Test_FilterSet extends FilterSet with Test_h2_async
object Test_FilterSeq extends FilterSeq with Test_h2_async
object Test_FilterMap extends FilterMap with Test_h2_async
