package molecule.sql.core.query.casting

import molecule.sql.core.query.SqlQueryBase


trait NestOptRef[Tpl] extends SqlQueryBase {

  private lazy val branch       = new CastNestedBranch_[Option[Any]]
  private lazy val optionBranch = new CastNestedOptions_[Option[Any]]
  private lazy val optionLeaf   = new CastRow2OptionalTpl_[Option[Any]]

  // First attr index for each level
  private lazy val i0 = 1 // 1-based indexes for jdbc Row
  private lazy val i1 = i0 + aritiess.head.flatten.takeWhile(_ != -1).sum
  private lazy val i2 = i1 + aritiess(1).flatten.takeWhile(_ != -1).sum
  private lazy val i3 = i2 + aritiess(2).flatten.takeWhile(_ != -1).sum
  private lazy val i4 = i3 + aritiess(3).flatten.takeWhile(_ != -1).sum
  private lazy val i5 = i4 + aritiess(4).flatten.takeWhile(_ != -1).sum
  private lazy val i6 = i5 + aritiess(5).flatten.takeWhile(_ != -1).sum
  private lazy val i7 = i6 + aritiess(6).flatten.takeWhile(_ != -1).sum


  private lazy val tplBranch0: (RS, Option[Any]) => Tpl         = branch.cast[Tpl](aritiess(0), castss(0), i0)
  private lazy val tplBranch1: (RS, Option[Any]) => Option[Any] = optionBranch.cast(aritiess(1), castss(1), i1)
  private lazy val tplBranch2: (RS, Option[Any]) => Option[Any] = optionBranch.cast(aritiess(2), castss(2), i2)
  private lazy val tplBranch3: (RS, Option[Any]) => Option[Any] = optionBranch.cast(aritiess(3), castss(3), i3)
  private lazy val tplBranch4: (RS, Option[Any]) => Option[Any] = optionBranch.cast(aritiess(4), castss(4), i4)
  private lazy val tplBranch5: (RS, Option[Any]) => Option[Any] = optionBranch.cast(aritiess(5), castss(5), i5)
  private lazy val tplBranch6: (RS, Option[Any]) => Option[Any] = optionBranch.cast(aritiess(6), castss(6), i6)

  private lazy val tplLeaf1: RS => Option[Any] = optionLeaf.cast(aritiess(1), castss(1), i1)
  private lazy val tplLeaf2: RS => Option[Any] = optionLeaf.cast(aritiess(2), castss(2), i2)
  private lazy val tplLeaf3: RS => Option[Any] = optionLeaf.cast(aritiess(3), castss(3), i3)
  private lazy val tplLeaf4: RS => Option[Any] = optionLeaf.cast(aritiess(4), castss(4), i4)
  private lazy val tplLeaf5: RS => Option[Any] = optionLeaf.cast(aritiess(5), castss(5), i5)
  private lazy val tplLeaf6: RS => Option[Any] = optionLeaf.cast(aritiess(6), castss(6), i6)
  private lazy val tplLeaf7: RS => Option[Any] = optionLeaf.cast(aritiess(7), castss(7), i7)


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
      tplBranch0(row,
        tplLeaf1(row))
  }

  final private def row2nestedOptions2: RS => Tpl = {
    (row: RS) => {
      tplBranch0(row,
        tplBranch1(row,
          tplLeaf2(row)))
    }
  }

  final private def row2nestedOptions3: RS => Tpl = {
    (row: RS) =>
      tplBranch0(row,
        tplBranch1(row,
          tplBranch2(row,
            tplLeaf3(row))))
  }

  final private def row2nestedOptions4: RS => Tpl = {
    (row: RS) =>
      tplBranch0(row,
        tplBranch1(row,
          tplBranch2(row,
            tplBranch3(row,
              tplLeaf4(row)))))
  }

  final private def row2nestedOptions5: RS => Tpl = {
    (row: RS) =>
      tplBranch0(row,
        tplBranch1(row,
          tplBranch2(row,
            tplBranch3(row,
              tplBranch4(row,
                tplLeaf5(row))))))
  }

  final private def row2nestedOptions6: RS => Tpl = {
    (row: RS) =>
      tplBranch0(row,
        tplBranch1(row,
          tplBranch2(row,
            tplBranch3(row,
              tplBranch4(row,
                tplBranch5(row,
                  tplLeaf6(row)))))))
  }

  final private def row2nestedOptions7: RS => Tpl = {
    (row: RS) =>
      tplBranch0(row,
        tplBranch1(row,
          tplBranch2(row,
            tplBranch3(row,
              tplBranch4(row,
                tplBranch5(row,
                  tplBranch6(row,
                    tplLeaf7(row))))))))
  }
}