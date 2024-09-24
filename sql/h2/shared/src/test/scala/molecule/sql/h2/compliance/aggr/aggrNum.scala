package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.number._
import molecule.sql.h2.setup.Test_h2_async

object Test_AggrNum_Int extends AggrNum_Int with Test_h2_async
object Test_AggrNum_Long extends AggrNum_Long_ with Test_h2_async
object Test_AggrNum_Float extends AggrNum_Float_ with Test_h2_async
object Test_AggrNum_Double extends AggrNum_Double_ with Test_h2_async
object Test_AggrNum_BigInt extends AggrNum_BigInt_ with Test_h2_async
object Test_AggrNum_BigDecimal extends AggrNum_BigDecimal_ with Test_h2_async
object Test_AggrNum_Byte extends AggrNum_Byte_ with Test_h2_async
object Test_AggrNum_Short extends AggrNum_Short_ with Test_h2_async
