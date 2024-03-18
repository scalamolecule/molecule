package molecule.coreTests.spi.aggr.seq.any

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrByteArray extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Aggregation on byte arrays not allowed" - types { implicit conn =>
      compileError("Ns.byteArray(count).query")
      compileError("Ns.byteArray(countDistinct).query")
      compileError("Ns.byteArray(min).query")
      compileError("Ns.byteArray(max).query")
      compileError("Ns.byteArray(sample).query")
      compileError("Ns.byteArray(distinct).query")
    }
  }
}