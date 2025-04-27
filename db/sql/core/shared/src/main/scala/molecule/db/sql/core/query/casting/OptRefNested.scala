package molecule.db.sql.core.query.casting

import molecule.db.sql.core.query.SqlQueryBase
import molecule.db.sql.core.query.casting.strategy.*

object OptRefNested extends SqlQueryBase {

  final def row2nestedOptions(casters: List[CastTuple]): RS => Any = {
    casters.length match {
      case 1 => row2nestedOptions0(casters)
      case 2 => row2nestedOptions1(casters)
      case 3 => row2nestedOptions2(casters)
      case 4 => row2nestedOptions3(casters)
      case 5 => row2nestedOptions4(casters)
      case 6 => row2nestedOptions5(casters)
      case 7 => row2nestedOptions6(casters)
      case 8 => row2nestedOptions7(casters)
    }
  }

  final private def row2nestedOptions0(casters: List[CastTuple]): RS => Any = {
    val leaf: RS => Option[Any] = casters(0).optRefLeafCaster
    (row: RS) =>
      leaf(row)
  }

  final private def row2nestedOptions1(casters: List[CastTuple]): RS => Any = {
    val branch0: (RS, Option[Any]) => Option[Any] = casters(0).optRefBranchCaster
    val leaf   : RS => Option[Any]                = casters(1).optRefLeafCaster
    (row: RS) =>
      branch0(row,
        leaf(row))
  }

  final private def row2nestedOptions2(casters: List[CastTuple]): RS => Any = {
    val branch0: (RS, Option[Any]) => Option[Any] = casters(0).optRefBranchCaster
    val branch1: (RS, Option[Any]) => Option[Any] = casters(1).optRefBranchCaster
    val leaf   : RS => Option[Any]                = casters(2).optRefLeafCaster
    (row: RS) => {
      branch0(row,
        branch1(row,
          leaf(row)))
    }
  }

  final private def row2nestedOptions3(casters: List[CastTuple]): RS => Any = {
    val branch0: (RS, Option[Any]) => Option[Any] = casters(0).optRefBranchCaster
    val branch1: (RS, Option[Any]) => Option[Any] = casters(1).optRefBranchCaster
    val branch2: (RS, Option[Any]) => Option[Any] = casters(2).optRefBranchCaster
    val leaf   : RS => Option[Any]                = casters(3).optRefLeafCaster
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            leaf(row))))
  }

  final private def row2nestedOptions4(casters: List[CastTuple]): RS => Any = {
    val branch0: (RS, Option[Any]) => Option[Any] = casters(0).optRefBranchCaster
    val branch1: (RS, Option[Any]) => Option[Any] = casters(1).optRefBranchCaster
    val branch2: (RS, Option[Any]) => Option[Any] = casters(2).optRefBranchCaster
    val branch3: (RS, Option[Any]) => Option[Any] = casters(3).optRefBranchCaster
    val leaf   : RS => Option[Any]                = casters(4).optRefLeafCaster
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              leaf(row)))))
  }

  final private def row2nestedOptions5(casters: List[CastTuple]): RS => Any = {
    val branch0: (RS, Option[Any]) => Option[Any] = casters(0).optRefBranchCaster
    val branch1: (RS, Option[Any]) => Option[Any] = casters(1).optRefBranchCaster
    val branch2: (RS, Option[Any]) => Option[Any] = casters(2).optRefBranchCaster
    val branch3: (RS, Option[Any]) => Option[Any] = casters(3).optRefBranchCaster
    val branch4: (RS, Option[Any]) => Option[Any] = casters(4).optRefBranchCaster
    val leaf   : RS => Option[Any]                = casters(5).optRefLeafCaster
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              branch4(row,
                leaf(row))))))
  }

  final private def row2nestedOptions6(casters: List[CastTuple]): RS => Any = {
    val branch0: (RS, Option[Any]) => Option[Any] = casters(0).optRefBranchCaster
    val branch1: (RS, Option[Any]) => Option[Any] = casters(1).optRefBranchCaster
    val branch2: (RS, Option[Any]) => Option[Any] = casters(2).optRefBranchCaster
    val branch3: (RS, Option[Any]) => Option[Any] = casters(3).optRefBranchCaster
    val branch4: (RS, Option[Any]) => Option[Any] = casters(4).optRefBranchCaster
    val branch5: (RS, Option[Any]) => Option[Any] = casters(5).optRefBranchCaster
    val leaf   : RS => Option[Any]                = casters(6).optRefLeafCaster
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              branch4(row,
                branch5(row,
                  leaf(row)))))))
  }

  final private def row2nestedOptions7(casters: List[CastTuple]): RS => Any = {
    val branch0: (RS, Option[Any]) => Option[Any] = casters(0).optRefBranchCaster
    val branch1: (RS, Option[Any]) => Option[Any] = casters(1).optRefBranchCaster
    val branch2: (RS, Option[Any]) => Option[Any] = casters(2).optRefBranchCaster
    val branch3: (RS, Option[Any]) => Option[Any] = casters(3).optRefBranchCaster
    val branch4: (RS, Option[Any]) => Option[Any] = casters(4).optRefBranchCaster
    val branch5: (RS, Option[Any]) => Option[Any] = casters(5).optRefBranchCaster
    val branch6: (RS, Option[Any]) => Option[Any] = casters(6).optRefBranchCaster
    val leaf   : RS => Option[Any]                = casters(7).optRefLeafCaster
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              branch4(row,
                branch5(row,
                  branch6(row,
                    leaf(row))))))))
  }
}