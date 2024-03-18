package molecule.datalog.datomic.compliance.aggr.seq

import molecule.coreTests.spi.aggr.seq.ref._
import molecule.datalog.datomic.setup.TestAsync_datomic

object AggrSeqRef_count extends AggrSeqRef_count with TestAsync_datomic
object AggrSeqRef_distinct extends AggrSeqRef_distinct with TestAsync_datomic
object AggrSeqRef_max extends AggrSeqRef_max with TestAsync_datomic
object AggrSeqRef_min extends AggrSeqRef_min with TestAsync_datomic
object AggrSeqRef_sample extends AggrSeqRef_sample with TestAsync_datomic
