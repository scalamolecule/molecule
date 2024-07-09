package molecule.sql.core.query.casting

import molecule.sql.core.query.SqlQueryBase


trait NestOptRef[Tpl] extends SqlQueryBase {

  private val branch = new CastNestedBranch_[Option[Any]]
  private val row = new CastRow2Tpl_[Option[Any]]

  private lazy val nestedLevels = castss.length - 1
  private lazy val txAttrs      = aritiess.head.flatten.dropWhile(_ != -1).tail.sum

  // First attr index for each level
  private lazy val i0 = 1 + nestedLevels // 1-based indexes for jdbc Row
  private lazy val i1 = i0 + aritiess.head.flatten.takeWhile(_ != -1).sum
  private lazy val i2 = i1 + aritiess(1).flatten.takeWhile(_ != -1).sum
  private lazy val i3 = i2 + aritiess(2).flatten.takeWhile(_ != -1).sum
  private lazy val i4 = i3 + aritiess(3).flatten.takeWhile(_ != -1).sum
  private lazy val i5 = i4 + aritiess(4).flatten.takeWhile(_ != -1).sum
  private lazy val i6 = i5 + aritiess(5).flatten.takeWhile(_ != -1).sum
  private lazy val i7 = i6 + aritiess(6).flatten.takeWhile(_ != -1).sum

  private var rowCount                                  = -1
  private lazy val rowIndexTx                           = 1 + rowCount - txAttrs // 1-based indexes for jdbc ResultSet
  private lazy val tplBranch0: (RS, Option[Any]) => Tpl = branch.cast[Tpl](aritiess(0), castss(0), i0, rowIndexTx)
  private lazy val tplBranch1: (RS, Option[Any]) => Any = branch.cast[Any](aritiess(1), castss(1), i1, 0)
  private lazy val tplBranch2: (RS, Option[Any]) => Any = branch.cast[Any](aritiess(2), castss(2), i2, 0)
  private lazy val tplBranch3: (RS, Option[Any]) => Any = branch.cast[Any](aritiess(3), castss(3), i3, 0)
  private lazy val tplBranch4: (RS, Option[Any]) => Any = branch.cast[Any](aritiess(4), castss(4), i4, 0)
  private lazy val tplBranch5: (RS, Option[Any]) => Any = branch.cast[Any](aritiess(5), castss(5), i5, 0)
  private lazy val tplBranch6: (RS, Option[Any]) => Any = branch.cast[Any](aritiess(6), castss(6), i6, 0)

  private lazy val tplLeaf1: RS => Any = row.cast(aritiess(1), castss(1), i1, None)
  private lazy val tplLeaf2: RS => Any = row.cast(aritiess(2), castss(2), i2, None)
  private lazy val tplLeaf3: RS => Any = row.cast(aritiess(3), castss(3), i3, None)
  private lazy val tplLeaf4: RS => Any = row.cast(aritiess(4), castss(4), i4, None)
  private lazy val tplLeaf5: RS => Any = row.cast(aritiess(5), castss(5), i5, None)
  private lazy val tplLeaf6: RS => Any = row.cast(aritiess(6), castss(6), i6, None)
  private lazy val tplLeaf7: RS => Any = row.cast(aritiess(7), castss(7), i7, None)

  private var acc0: Option[Tpl] = Option.empty[Tpl]
  private var acc1: Option[Any] = Option.empty[Any]
  private var acc2: Option[Any] = Option.empty[Any]
  private var acc3: Option[Any] = Option.empty[Any]
  private var acc4: Option[Any] = Option.empty[Any]
  private var acc5: Option[Any] = Option.empty[Any]
  private var acc6: Option[Any] = Option.empty[Any]
  private var acc7: Option[Any] = Option.empty[Any]


  final def rows2optional(rows: RS): Option[Tpl] = {

    println("aritiess: ")
    aritiess.foreach(println)

    nestedLevels match {
      case 1 => rows2optional1(rows)
      case 2 => rows2optional2(rows)
      //      case 3 => rows2optional3(rows)
      //      case 4 => rows2optional4(rows)
      //      case 5 => rows2optional5(rows)
      //      case 6 => rows2optional6(rows)
      //      case 7 => rows2optional7(rows)
    }
  }


  final private def rows2optional1(rows: RS): Option[Tpl] = {
    rowCount = getRowCount(rows)
    //    println("rowCount " + rowCount)

    acc1 = Option(tplLeaf1(rows))
    acc0 = Option(tplBranch0(rows, acc1))
    ???
  }


  final private def rows2optional2(rows: RS): Option[Tpl] = {
    rowCount = getRowCount(rows)

    ???
  }


  //  final private def rows2optional3(rows: RS): List[Tpl] = {
  //    rowCount = getRowCount(rows)
  //
  //    if (rowCount == 1) {
  //      rows.first()
  //      acc3 = List(tplLeaf3(rows))
  //      acc2 = List(tplBranch2(rows, acc3))
  //      acc1 = List(tplBranch1(rows, acc2))
  //      acc0 = List(tplBranch0(rows, acc1))
  //
  //    } else {
  //      rows.afterLast()
  //      while (rows.previous()) {
  //        e0 = rows.getLong(1)
  //        e1 = rows.getLong(2)
  //        e2 = rows.getLong(3)
  //
  //        if (nextRow) {
  //          if (rows.isFirst) {
  //            if (e0 != p0) {
  //              rows.next()
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //              rows.previous()
  //
  //              acc3 = List(tplLeaf3(rows))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = List(tplBranch1(rows, acc2))
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e1 != p1) {
  //              rows.next()
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              rows.previous()
  //
  //              acc3 = List(tplLeaf3(rows))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e2 != p2) {
  //              rows.next()
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              rows.previous()
  //
  //              acc3 = List(tplLeaf3(rows))
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else /* e3 != p3 */ {
  //              acc3 = tplLeaf3(rows) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //            }
  //
  //          } else if (e0 != p0) {
  //            rows.next()
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            acc0 = tplBranch0(rows, acc1) :: acc0
  //            rows.previous()
  //
  //            acc3 = List(tplLeaf3(rows))
  //            acc2 = Nil
  //            acc1 = Nil
  //
  //          } else if (e1 != p1) {
  //            rows.next()
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            rows.previous()
  //
  //            acc3 = List(tplLeaf3(rows))
  //            acc2 = Nil
  //
  //          } else if (e2 != p2) {
  //            rows.next()
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            rows.previous()
  //
  //            acc3 = List(tplLeaf3(rows))
  //
  //          } else /* e3 != p3 */ {
  //            acc3 = tplLeaf3(rows) :: acc3
  //          }
  //        } else {
  //          acc3 = List(tplLeaf3(rows))
  //          nextRow = true
  //        }
  //
  //        p0 = e0
  //        p1 = e1
  //        p2 = e2
  //      }
  //    }
  //    acc0
  //  }
  //
  //
  //  final private def rows2optional4(rows: RS): List[Tpl] = {
  //    rowCount = getRowCount(rows)
  //
  //    if (rowCount == 1) {
  //      rows.first()
  //      acc4 = List(tplLeaf4(rows))
  //      acc3 = List(tplBranch3(rows, acc4))
  //      acc2 = List(tplBranch2(rows, acc3))
  //      acc1 = List(tplBranch1(rows, acc2))
  //      acc0 = List(tplBranch0(rows, acc1))
  //
  //    } else {
  //      rows.afterLast()
  //      while (rows.previous()) {
  //        e0 = rows.getLong(1)
  //        e1 = rows.getLong(2)
  //        e2 = rows.getLong(3)
  //        e3 = rows.getLong(4)
  //
  //        if (nextRow) {
  //          if (rows.isFirst) {
  //            if (e0 != p0) {
  //              rows.next()
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //              rows.previous()
  //
  //              acc4 = List(tplLeaf4(rows))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = List(tplBranch1(rows, acc2))
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e1 != p1) {
  //              rows.next()
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              rows.previous()
  //
  //              acc4 = List(tplLeaf4(rows))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e2 != p2) {
  //              rows.next()
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              rows.previous()
  //
  //              acc4 = List(tplLeaf4(rows))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e3 != p3) {
  //              rows.next()
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              rows.previous()
  //
  //              acc4 = List(tplLeaf4(rows))
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else /* e4 != p4 */ {
  //              acc4 = tplLeaf4(rows) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //            }
  //
  //          } else if (e0 != p0) {
  //            rows.next()
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            acc0 = tplBranch0(rows, acc1) :: acc0
  //            rows.previous()
  //
  //            acc4 = List(tplLeaf4(rows))
  //            acc3 = Nil
  //            acc2 = Nil
  //            acc1 = Nil
  //
  //          } else if (e1 != p1) {
  //            rows.next()
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            rows.previous()
  //
  //            acc4 = List(tplLeaf4(rows))
  //            acc3 = Nil
  //            acc2 = Nil
  //
  //          } else if (e2 != p2) {
  //            rows.next()
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            rows.previous()
  //
  //            acc4 = List(tplLeaf4(rows))
  //            acc3 = Nil
  //
  //          } else if (e3 != p3) {
  //            rows.next()
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            rows.previous()
  //
  //            acc4 = List(tplLeaf4(rows))
  //
  //          } else /* e4 != p4 */ {
  //            acc4 = tplLeaf4(rows) :: acc4
  //          }
  //        } else {
  //          acc4 = List(tplLeaf4(rows))
  //          nextRow = true
  //        }
  //
  //        p0 = e0
  //        p1 = e1
  //        p2 = e2
  //        p3 = e3
  //      }
  //    }
  //    acc0
  //  }
  //
  //
  //  final private def rows2optional5(rows: RS): List[Tpl] = {
  //    rowCount = getRowCount(rows)
  //
  //    if (rowCount == 1) {
  //      rows.first()
  //      acc5 = List(tplLeaf5(rows))
  //      acc4 = List(tplBranch4(rows, acc5))
  //      acc3 = List(tplBranch3(rows, acc4))
  //      acc2 = List(tplBranch2(rows, acc3))
  //      acc1 = List(tplBranch1(rows, acc2))
  //      acc0 = List(tplBranch0(rows, acc1))
  //
  //    } else {
  //      rows.afterLast()
  //      while (rows.previous()) {
  //        e0 = rows.getLong(1)
  //        e1 = rows.getLong(2)
  //        e2 = rows.getLong(3)
  //        e3 = rows.getLong(4)
  //        e4 = rows.getLong(5)
  //
  //        if (nextRow) {
  //          if (rows.isFirst) {
  //            if (e0 != p0) {
  //              rows.next()
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //              rows.previous()
  //
  //              acc5 = List(tplLeaf5(rows))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = List(tplBranch1(rows, acc2))
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e1 != p1) {
  //              rows.next()
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              rows.previous()
  //
  //              acc5 = List(tplLeaf5(rows))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e2 != p2) {
  //              rows.next()
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              rows.previous()
  //
  //              acc5 = List(tplLeaf5(rows))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e3 != p3) {
  //              rows.next()
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              rows.previous()
  //
  //              acc5 = List(tplLeaf5(rows))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e4 != p4) {
  //              rows.next()
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              rows.previous()
  //
  //              acc5 = List(tplLeaf5(rows))
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else /* e5 != p5 */ {
  //              acc5 = tplLeaf5(rows) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //            }
  //
  //          } else if (e0 != p0) {
  //            rows.next()
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            acc0 = tplBranch0(rows, acc1) :: acc0
  //            rows.previous()
  //
  //            acc5 = List(tplLeaf5(rows))
  //            acc4 = Nil
  //            acc3 = Nil
  //            acc2 = Nil
  //            acc1 = Nil
  //
  //          } else if (e1 != p1) {
  //            rows.next()
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            rows.previous()
  //
  //            acc5 = List(tplLeaf5(rows))
  //            acc4 = Nil
  //            acc3 = Nil
  //            acc2 = Nil
  //
  //          } else if (e2 != p2) {
  //            rows.next()
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            rows.previous()
  //
  //            acc5 = List(tplLeaf5(rows))
  //            acc4 = Nil
  //            acc3 = Nil
  //
  //          } else if (e3 != p3) {
  //            rows.next()
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            rows.previous()
  //
  //            acc5 = List(tplLeaf5(rows))
  //            acc4 = Nil
  //
  //          } else if (e4 != p4) {
  //            rows.next()
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            rows.previous()
  //
  //            acc5 = List(tplLeaf5(rows))
  //
  //          } else /* e5 != p5 */ {
  //            acc5 = tplLeaf5(rows) :: acc5
  //          }
  //        } else {
  //          acc5 = List(tplLeaf5(rows))
  //          nextRow = true
  //        }
  //
  //        p0 = e0
  //        p1 = e1
  //        p2 = e2
  //        p3 = e3
  //        p4 = e4
  //      }
  //    }
  //    acc0
  //  }
  //
  //
  //  final private def rows2optional6(rows: RS): List[Tpl] = {
  //    rowCount = getRowCount(rows)
  //
  //    if (rowCount == 1) {
  //      rows.first()
  //      acc6 = List(tplLeaf6(rows))
  //      acc5 = List(tplBranch5(rows, acc6))
  //      acc4 = List(tplBranch4(rows, acc5))
  //      acc3 = List(tplBranch3(rows, acc4))
  //      acc2 = List(tplBranch2(rows, acc3))
  //      acc1 = List(tplBranch1(rows, acc2))
  //      acc0 = List(tplBranch0(rows, acc1))
  //
  //    } else {
  //      rows.afterLast()
  //      while (rows.previous()) {
  //        e0 = rows.getLong(1)
  //        e1 = rows.getLong(2)
  //        e2 = rows.getLong(3)
  //        e3 = rows.getLong(4)
  //        e4 = rows.getLong(5)
  //        e5 = rows.getLong(6)
  //
  //        if (nextRow) {
  //          if (rows.isFirst) {
  //            if (e0 != p0) {
  //              rows.next()
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //              rows.previous()
  //
  //              acc6 = List(tplLeaf6(rows))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = List(tplBranch1(rows, acc2))
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e1 != p1) {
  //              rows.next()
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              rows.previous()
  //
  //              acc6 = List(tplLeaf6(rows))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e2 != p2) {
  //              rows.next()
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              rows.previous()
  //
  //              acc6 = List(tplLeaf6(rows))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e3 != p3) {
  //              rows.next()
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              rows.previous()
  //
  //              acc6 = List(tplLeaf6(rows))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e4 != p4) {
  //              rows.next()
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              rows.previous()
  //
  //              acc6 = List(tplLeaf6(rows))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e5 != p5) {
  //              rows.next()
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              rows.previous()
  //
  //              acc6 = List(tplLeaf6(rows))
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else /* e6 != p6 */ {
  //              acc6 = tplLeaf6(rows) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //            }
  //
  //          } else if (e0 != p0) {
  //            rows.next()
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            acc0 = tplBranch0(rows, acc1) :: acc0
  //            rows.previous()
  //
  //            acc6 = List(tplLeaf6(rows))
  //            acc5 = Nil
  //            acc4 = Nil
  //            acc3 = Nil
  //            acc2 = Nil
  //            acc1 = Nil
  //
  //          } else if (e1 != p1) {
  //            rows.next()
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            rows.previous()
  //
  //            acc6 = List(tplLeaf6(rows))
  //            acc5 = Nil
  //            acc4 = Nil
  //            acc3 = Nil
  //            acc2 = Nil
  //
  //          } else if (e2 != p2) {
  //            rows.next()
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            rows.previous()
  //
  //            acc6 = List(tplLeaf6(rows))
  //            acc5 = Nil
  //            acc4 = Nil
  //            acc3 = Nil
  //
  //          } else if (e3 != p3) {
  //            rows.next()
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            rows.previous()
  //
  //            acc6 = List(tplLeaf6(rows))
  //            acc5 = Nil
  //            acc4 = Nil
  //
  //          } else if (e4 != p4) {
  //            rows.next()
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            rows.previous()
  //
  //            acc6 = List(tplLeaf6(rows))
  //            acc5 = Nil
  //
  //          } else if (e5 != p5) {
  //            rows.next()
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            rows.previous()
  //
  //            acc6 = List(tplLeaf6(rows))
  //
  //          } else /* e6 != p6 */ {
  //            acc6 = tplLeaf6(rows) :: acc6
  //          }
  //        } else {
  //          acc6 = List(tplLeaf6(rows))
  //          nextRow = true
  //        }
  //
  //        p0 = e0
  //        p1 = e1
  //        p2 = e2
  //        p3 = e3
  //        p4 = e4
  //        p5 = e5
  //      }
  //    }
  //    acc0
  //  }
  //
  //  final private def rows2optional7(rows: RS): List[Tpl] = {
  //    rowCount = getRowCount(rows)
  //
  //    if (rowCount == 1) {
  //      rows.first()
  //      acc7 = List(tplLeaf7(rows))
  //      acc6 = List(tplBranch6(rows, acc7))
  //      acc5 = List(tplBranch5(rows, acc6))
  //      acc4 = List(tplBranch4(rows, acc5))
  //      acc3 = List(tplBranch3(rows, acc4))
  //      acc2 = List(tplBranch2(rows, acc3))
  //      acc1 = List(tplBranch1(rows, acc2))
  //      acc0 = List(tplBranch0(rows, acc1))
  //
  //    } else {
  //      rows.afterLast()
  //      while (rows.previous()) {
  //        e0 = rows.getLong(1)
  //        e1 = rows.getLong(2)
  //        e2 = rows.getLong(3)
  //        e3 = rows.getLong(4)
  //        e4 = rows.getLong(5)
  //        e5 = rows.getLong(6)
  //        e6 = rows.getLong(7)
  //
  //        if (nextRow) {
  //          if (rows.isFirst) {
  //            if (e0 != p0) {
  //              rows.next()
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //              rows.previous()
  //
  //              acc7 = List(tplLeaf7(rows))
  //              acc6 = List(tplBranch6(rows, acc7))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = List(tplBranch1(rows, acc2))
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e1 != p1) {
  //              rows.next()
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              rows.previous()
  //
  //              acc7 = List(tplLeaf7(rows))
  //              acc6 = List(tplBranch6(rows, acc7))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = List(tplBranch2(rows, acc3))
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e2 != p2) {
  //              rows.next()
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              rows.previous()
  //
  //              acc7 = List(tplLeaf7(rows))
  //              acc6 = List(tplBranch6(rows, acc7))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = List(tplBranch3(rows, acc4))
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e3 != p3) {
  //              rows.next()
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              rows.previous()
  //
  //              acc7 = List(tplLeaf7(rows))
  //              acc6 = List(tplBranch6(rows, acc7))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = List(tplBranch4(rows, acc5))
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e4 != p4) {
  //              rows.next()
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              rows.previous()
  //
  //              acc7 = List(tplLeaf7(rows))
  //              acc6 = List(tplBranch6(rows, acc7))
  //              acc5 = List(tplBranch5(rows, acc6))
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e5 != p5) {
  //              rows.next()
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              rows.previous()
  //
  //              acc7 = List(tplLeaf7(rows))
  //              acc6 = List(tplBranch6(rows, acc7))
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else if (e6 != p6) {
  //              rows.next()
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              rows.previous()
  //
  //              acc7 = List(tplLeaf7(rows))
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //
  //            } else /* e7 != p7 */ {
  //              acc7 = tplLeaf7(rows) :: acc7
  //              acc6 = tplBranch6(rows, acc7) :: acc6
  //              acc5 = tplBranch5(rows, acc6) :: acc5
  //              acc4 = tplBranch4(rows, acc5) :: acc4
  //              acc3 = tplBranch3(rows, acc4) :: acc3
  //              acc2 = tplBranch2(rows, acc3) :: acc2
  //              acc1 = tplBranch1(rows, acc2) :: acc1
  //              acc0 = tplBranch0(rows, acc1) :: acc0
  //            }
  //
  //          } else if (e0 != p0) {
  //            rows.next()
  //            acc6 = tplBranch6(rows, acc7) :: acc6
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            acc0 = tplBranch0(rows, acc1) :: acc0
  //            rows.previous()
  //
  //            acc7 = List(tplLeaf7(rows))
  //            acc6 = Nil
  //            acc5 = Nil
  //            acc4 = Nil
  //            acc3 = Nil
  //            acc2 = Nil
  //            acc1 = Nil
  //
  //          } else if (e1 != p1) {
  //            rows.next()
  //            acc6 = tplBranch6(rows, acc7) :: acc6
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            acc1 = tplBranch1(rows, acc2) :: acc1
  //            rows.previous()
  //
  //            acc7 = List(tplLeaf7(rows))
  //            acc6 = Nil
  //            acc5 = Nil
  //            acc4 = Nil
  //            acc3 = Nil
  //            acc2 = Nil
  //
  //          } else if (e2 != p2) {
  //            rows.next()
  //            acc6 = tplBranch6(rows, acc7) :: acc6
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            acc2 = tplBranch2(rows, acc3) :: acc2
  //            rows.previous()
  //
  //            acc7 = List(tplLeaf7(rows))
  //            acc6 = Nil
  //            acc5 = Nil
  //            acc4 = Nil
  //            acc3 = Nil
  //
  //          } else if (e3 != p3) {
  //            rows.next()
  //            acc6 = tplBranch6(rows, acc7) :: acc6
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            acc3 = tplBranch3(rows, acc4) :: acc3
  //            rows.previous()
  //
  //            acc7 = List(tplLeaf7(rows))
  //            acc6 = Nil
  //            acc5 = Nil
  //            acc4 = Nil
  //
  //          } else if (e4 != p4) {
  //            rows.next()
  //            acc6 = tplBranch6(rows, acc7) :: acc6
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            acc4 = tplBranch4(rows, acc5) :: acc4
  //            rows.previous()
  //
  //            acc7 = List(tplLeaf7(rows))
  //            acc6 = Nil
  //            acc5 = Nil
  //
  //          } else if (e5 != p5) {
  //            rows.next()
  //            acc6 = tplBranch6(rows, acc7) :: acc6
  //            acc5 = tplBranch5(rows, acc6) :: acc5
  //            rows.previous()
  //
  //            acc7 = List(tplLeaf7(rows))
  //            acc6 = Nil
  //
  //          } else if (e6 != p6) {
  //            rows.next()
  //            acc6 = tplBranch6(rows, acc7) :: acc6
  //            rows.previous()
  //
  //            acc7 = List(tplLeaf7(rows))
  //
  //          } else /* e7 != p7 */ {
  //            acc7 = tplLeaf7(rows) :: acc7
  //          }
  //        } else {
  //          acc7 = List(tplLeaf7(rows))
  //          nextRow = true
  //        }
  //
  //        p0 = e0
  //        p1 = e1
  //        p2 = e2
  //        p3 = e3
  //        p4 = e4
  //        p5 = e5
  //        p6 = e6
  //      }
  //    }
  //    acc0
  //  }
}