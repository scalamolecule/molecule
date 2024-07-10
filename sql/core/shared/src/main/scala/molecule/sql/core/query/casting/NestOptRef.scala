package molecule.sql.core.query.casting

import molecule.sql.core.query.SqlQueryBase


trait NestOptRef[Tpl] extends SqlQueryBase {

  private lazy val topBranch = new CastBranch_[Option[Any]]
  private lazy val branch    = CastOptRefBranch_
  private lazy val leaf      = CastOptTpl_

  // First attr index for each level
  private lazy val i0 = 1 // 1-based indexes for jdbc Row
  private lazy val i1 = i0 + aritiess.head.takeWhile(_ != -1).sum
  private lazy val i2 = i1 + aritiess(1).takeWhile(_ != -1).sum
  private lazy val i3 = i2 + aritiess(2).takeWhile(_ != -1).sum
  private lazy val i4 = i3 + aritiess(3).takeWhile(_ != -1).sum
  private lazy val i5 = i4 + aritiess(4).takeWhile(_ != -1).sum
  private lazy val i6 = i5 + aritiess(5).takeWhile(_ != -1).sum
  private lazy val i7 = i6 + aritiess(6).takeWhile(_ != -1).sum


  private lazy val branch0: (RS, Option[Any]) => Tpl         = topBranch.cast[Tpl](aritiess(0), castss(0), i0)
  private lazy val branch1: (RS, Option[Any]) => Option[Any] = branch.cast(aritiess(1), castss(1), i1)
  private lazy val branch2: (RS, Option[Any]) => Option[Any] = branch.cast(aritiess(2), castss(2), i2)
  private lazy val branch3: (RS, Option[Any]) => Option[Any] = branch.cast(aritiess(3), castss(3), i3)
  private lazy val branch4: (RS, Option[Any]) => Option[Any] = branch.cast(aritiess(4), castss(4), i4)
  private lazy val branch5: (RS, Option[Any]) => Option[Any] = branch.cast(aritiess(5), castss(5), i5)
  private lazy val branch6: (RS, Option[Any]) => Option[Any] = branch.cast(aritiess(6), castss(6), i6)

  private lazy val leaf1: RS => Option[Any] = leaf.cast(aritiess(1), castss(1), i1)
  private lazy val leaf2: RS => Option[Any] = leaf.cast(aritiess(2), castss(2), i2)
  private lazy val leaf3: RS => Option[Any] = leaf.cast(aritiess(3), castss(3), i3)
  private lazy val leaf4: RS => Option[Any] = leaf.cast(aritiess(4), castss(4), i4)
  private lazy val leaf5: RS => Option[Any] = leaf.cast(aritiess(5), castss(5), i5)
  private lazy val leaf6: RS => Option[Any] = leaf.cast(aritiess(6), castss(6), i6)
  private lazy val leaf7: RS => Option[Any] = leaf.cast(aritiess(7), castss(7), i7)


  final def row2nestedOptions: RS => Tpl = {
    castss.length - 1 match {
      case 1 => row2nestedOptions1
      case 2 => row2nestedOptions2
      case 3 => row2nestedOptions3
      case 4 => row2nestedOptions4
      case 5 => row2nestedOptions5
      case 6 => row2nestedOptions6
      case 7 => row2nestedOptions7
    }
  }

  final private def row2nestedOptions1: RS => Tpl = {
    (row: RS) =>
      branch0(row,
        leaf1(row))
  }

  final private def row2nestedOptions2: RS => Tpl = {
    (row: RS) => {
      branch0(row,
        branch1(row,
          leaf2(row)))
    }
  }

  final private def row2nestedOptions3: RS => Tpl = {
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            leaf3(row))))
  }

  final private def row2nestedOptions4: RS => Tpl = {
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              leaf4(row)))))
  }

  final private def row2nestedOptions5: RS => Tpl = {
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              branch4(row,
                leaf5(row))))))
  }

  final private def row2nestedOptions6: RS => Tpl = {
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              branch4(row,
                branch5(row,
                  leaf6(row)))))))
  }

  final private def row2nestedOptions7: RS => Tpl = {
    (row: RS) =>
      branch0(row,
        branch1(row,
          branch2(row,
            branch3(row,
              branch4(row,
                branch5(row,
                  branch6(row,
                    leaf7(row))))))))
  }
}