package molecule.datalog.datomic.compliance.aggr.set

import molecule.coreTests.spi.aggr.set.number._
import molecule.datalog.datomic.setup.TestAsync_datomic

object AggrSetNum_Int extends AggrSetNum_Int with TestAsync_datomic
object AggrSetNum_Long_ extends AggrSetNum_Long_ with TestAsync_datomic
object AggrSetNum_Float_ extends AggrSetNum_Float_ with TestAsync_datomic
object AggrSetNum_Double_ extends AggrSetNum_Double_ with TestAsync_datomic
object AggrSetNum_BigInt_ extends AggrSetNum_BigInt_ with TestAsync_datomic
object AggrSetNum_BigDecimal_ extends AggrSetNum_BigDecimal_ with TestAsync_datomic
object AggrSetNum_Byte_ extends AggrSetNum_Byte_ with TestAsync_datomic
object AggrSetNum_Short_ extends AggrSetNum_Short_ with TestAsync_datomic
