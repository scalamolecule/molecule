package molecule.datalog.datomic.compliance.aggr.seq

import molecule.coreTests.spi.aggr.seq.number._
import molecule.datalog.datomic.setup.TestAsync_datomic

object AggrSeqNum_Int extends AggrSeqNum_Int with TestAsync_datomic
object AggrSeqNum_Long_ extends AggrSeqNum_Long_ with TestAsync_datomic
object AggrSeqNum_Float_ extends AggrSeqNum_Float_ with TestAsync_datomic
object AggrSeqNum_Double_ extends AggrSeqNum_Double_ with TestAsync_datomic
object AggrSeqNum_BigInt_ extends AggrSeqNum_BigInt_ with TestAsync_datomic
object AggrSeqNum_BigDecimal_ extends AggrSeqNum_BigDecimal_ with TestAsync_datomic
object AggrByteArrayNum extends AggrByteArrayNum with TestAsync_datomic
object AggrSeqNum_Short_ extends AggrSeqNum_Short_ with TestAsync_datomic