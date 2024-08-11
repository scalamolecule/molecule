package molecule.datalog.datomic.compliance.validation.crud.save

import molecule.coreTests.spi.validation.save._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_FormatConstants  extends FormatConstants with TestAsync_datomic
object Test_FormatVariables extends FormatVariables with TestAsync_datomic
object Semantics extends Semantics with TestAsync_datomic
object TypesOne extends TypesOne with TestAsync_datomic
object TypesOneOpt extends TypesOneOpt with TestAsync_datomic
object TypesSeq extends TypesSeq with TestAsync_datomic
object TypesSeqOpt extends TypesSeqOpt with TestAsync_datomic
object TypesSet extends TypesSet with TestAsync_datomic
object TypesSetOpt extends TypesSetOpt with TestAsync_datomic
