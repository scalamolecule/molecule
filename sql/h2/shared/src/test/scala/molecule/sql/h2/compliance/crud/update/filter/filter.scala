package molecule.sql.h2.compliance.crud.update.filter

import molecule.coreTests.spi.crud.update.filter._
import molecule.sql.h2.setup.TestAsync_h2

object Test_FilterOne extends FilterOne with TestAsync_h2
object Test_FilterSet extends FilterSet with TestAsync_h2
object Test_FilterSeq extends FilterSeq with TestAsync_h2
object Test_FilterMap extends FilterMap with TestAsync_h2
