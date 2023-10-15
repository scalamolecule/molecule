package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.set.number._
import molecule.sql.h2.setup.TestAsync_h2

object AggrSetNum_Int extends AggrSetNum_Int with TestAsync_h2
object AggrSetNum_Long extends AggrSetNum_Long_ with TestAsync_h2
object AggrSetNum_Float extends AggrSetNum_Float_ with TestAsync_h2
object AggrSetNum_Double extends AggrSetNum_Double_ with TestAsync_h2
object AggrSetNum_BigInt extends AggrSetNum_BigInt_ with TestAsync_h2
object AggrSetNum_BigDecimal extends AggrSetNum_BigDecimal_ with TestAsync_h2
object AggrSetNum_Byte extends AggrSetNum_Byte_ with TestAsync_h2
object AggrSetNum_Short extends AggrSetNum_Short_ with TestAsync_h2
object AggrSetNum_ref extends AggrSetNum_ref with TestAsync_h2
