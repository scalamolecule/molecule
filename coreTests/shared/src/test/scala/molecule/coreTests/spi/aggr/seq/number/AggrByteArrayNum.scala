package molecule.coreTests.spi.aggr.seq.number

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait AggrByteArrayNum extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Aggregation on byte arrays not allowed" - types { implicit conn =>
      compileError("Ns.byteArray(sum).query")
      compileError("Ns.byteArray(median).query")
      compileError("Ns.byteArray(avg).query")
      compileError("Ns.byteArray(variance).query")
      compileError("Ns.byteArray(stddev).query")
    }
  }
}