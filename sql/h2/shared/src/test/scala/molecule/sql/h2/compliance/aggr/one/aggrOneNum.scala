package molecule.sql.h2.compliance.aggr.one

import molecule.coreTests.spi.aggr.one.number._
import molecule.sql.h2.setup.TestAsync_h2

object AggrOneNum_Int extends AggrOneNum_Int with TestAsync_h2
object AggrOneNum_Long extends AggrOneNum_Long_ with TestAsync_h2
object AggrOneNum_Float extends AggrOneNum_Float_ with TestAsync_h2
object AggrOneNum_Double extends AggrOneNum_Double_ with TestAsync_h2
object AggrOneNum_BigInt extends AggrOneNum_BigInt_ with TestAsync_h2
object AggrOneNum_BigDecimal extends AggrOneNum_BigDecimal_ with TestAsync_h2
object AggrOneNum_Byte extends AggrOneNum_Byte_ with TestAsync_h2
object AggrOneNum_Short extends AggrOneNum_Short_ with TestAsync_h2
