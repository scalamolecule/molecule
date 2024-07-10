package molecule.datalog.core.query.casting

import java.lang.{Long => jLong}
import java.util.{ArrayList => jArrayList}
import molecule.core.query.Model2Query
import molecule.datalog.core.query.DatomicQueryBase


trait Nest[Tpl] { self: Model2Query
  with DatomicQueryBase
  with CastNestedBranch_ =>

  private var row    : Row = new jArrayList[AnyRef]()
  private var prevRow: Row = new jArrayList[AnyRef]()

  // Previous entity ids on each level
  private var p0: jLong = 0L
  private var p1: jLong = 0L
  private var p2: jLong = 0L
  private var p3: jLong = 0L
  private var p4: jLong = 0L
  private var p5: jLong = 0L
  private var p6: jLong = 0L

  // Current entity ids on each level
  private var e0: jLong = 0L
  private var e1: jLong = 0L
  private var e2: jLong = 0L
  private var e3: jLong = 0L
  private var e4: jLong = 0L
  private var e5: jLong = 0L
  private var e6: jLong = 0L

  private var nextRow: Boolean = false

  private lazy val nestedLevels = castss.length - 1
  private lazy val txAttrs      = aritiess.head.dropWhile(_ != -1).tail.sum

  // First attr index for each level
  private lazy val i0 = nestedLevels
  private lazy val i1 = i0 + aritiess.head.takeWhile(_ != -1).sum
  private lazy val i2 = i1 + aritiess(1).takeWhile(_ != -1).sum
  private lazy val i3 = i2 + aritiess(2).takeWhile(_ != -1).sum
  private lazy val i4 = i3 + aritiess(3).takeWhile(_ != -1).sum
  private lazy val i5 = i4 + aritiess(4).takeWhile(_ != -1).sum
  private lazy val i6 = i5 + aritiess(5).takeWhile(_ != -1).sum
  private lazy val i7 = i6 + aritiess(6).takeWhile(_ != -1).sum

  private lazy val rowIndexTx                          = row.size() - txAttrs
  private lazy val tplBranch0: (Row, List[Any]) => Tpl = castBranch[Tpl](aritiess(0), castss(0), i0, rowIndexTx)
  private lazy val tplBranch1: (Row, List[Any]) => Any = castBranch[Any](aritiess(1), castss(1), i1, 0)
  private lazy val tplBranch2: (Row, List[Any]) => Any = castBranch[Any](aritiess(2), castss(2), i2, 0)
  private lazy val tplBranch3: (Row, List[Any]) => Any = castBranch[Any](aritiess(3), castss(3), i3, 0)
  private lazy val tplBranch4: (Row, List[Any]) => Any = castBranch[Any](aritiess(4), castss(4), i4, 0)
  private lazy val tplBranch5: (Row, List[Any]) => Any = castBranch[Any](aritiess(5), castss(5), i5, 0)
  private lazy val tplBranch6: (Row, List[Any]) => Any = castBranch[Any](aritiess(6), castss(6), i6, 0)

  private lazy val tplLeaf1: Row => Any = castRow2AnyTpl(aritiess(1), castss(1), i1, None)
  private lazy val tplLeaf2: Row => Any = castRow2AnyTpl(aritiess(2), castss(2), i2, None)
  private lazy val tplLeaf3: Row => Any = castRow2AnyTpl(aritiess(3), castss(3), i3, None)
  private lazy val tplLeaf4: Row => Any = castRow2AnyTpl(aritiess(4), castss(4), i4, None)
  private lazy val tplLeaf5: Row => Any = castRow2AnyTpl(aritiess(5), castss(5), i5, None)
  private lazy val tplLeaf6: Row => Any = castRow2AnyTpl(aritiess(6), castss(6), i6, None)
  private lazy val tplLeaf7: Row => Any = castRow2AnyTpl(aritiess(7), castss(7), i7, None)

  private var acc0: List[Tpl] = List.empty[Tpl]
  private var acc1: List[Any] = List.empty[Any]
  private var acc2: List[Any] = List.empty[Any]
  private var acc3: List[Any] = List.empty[Any]
  private var acc4: List[Any] = List.empty[Any]
  private var acc5: List[Any] = List.empty[Any]
  private var acc6: List[Any] = List.empty[Any]
  private var acc7: List[Any] = List.empty[Any]


  final def rows2nested(rows: jArrayList[Row]): List[Tpl] = {
    nestedLevels match {
      case 1 => rows2nested1(rows)
      case 2 => rows2nested2(rows)
      case 3 => rows2nested3(rows)
      case 4 => rows2nested4(rows)
      case 5 => rows2nested5(rows)
      case 6 => rows2nested6(rows)
      case 7 => rows2nested7(rows)
    }
  }

  final private def rows2nested1(rows: jArrayList[Row]): List[Tpl] = {
    if (rows.size == 1) {
      row = rows.get(0)
      List(tplBranch0(row,
        List(tplLeaf1(row))))

    } else {
      var i = rows.size - 1
      while (i != -1) {
        row = rows.get(i)
        e0 = row.get(0).asInstanceOf[jLong]
        if (nextRow) {
          if (i == 0) {
            if (e0 != p0) {
              acc0 = tplBranch0(prevRow, acc1) :: acc0

              acc1 = List(tplLeaf1(row))
              acc0 = tplBranch0(row, acc1) :: acc0

            } else /* e1 != p1 */ {
              acc1 = tplLeaf1(row) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0
            }

          } else if (e0 != p0) {
            acc0 = tplBranch0(prevRow, acc1) :: acc0

            acc1 = List(tplLeaf1(row))

          } else /* e1 != p1 */ {
            acc1 = tplLeaf1(row) :: acc1
          }
        } else {
          acc1 = List(tplLeaf1(row))
          nextRow = true
        }
        prevRow = row
        p0 = e0
        i -= 1
      }
      acc0
    }
  }


  final private def rows2nested2(rows: jArrayList[Row]): List[Tpl] = {
    if (rows.size == 1) {
      row = rows.get(0)
      List(tplBranch0(row,
        List(tplBranch1(row,
          List(tplLeaf2(row))))))

    } else {
      var i = rows.size - 1
      while (i != -1) {
        row = rows.get(i)
        e0 = row.get(0).asInstanceOf[jLong]
        e1 = row.get(1).asInstanceOf[jLong]

        if (nextRow) {
          if (i == 0) {
            if (e0 != p0) {
              acc1 = tplBranch1(prevRow, acc2) :: acc1
              acc0 = tplBranch0(prevRow, acc1) :: acc0

              acc2 = List(tplLeaf2(row))
              acc1 = List(tplBranch1(row, acc2))
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e1 != p1) {
              acc1 = tplBranch1(prevRow, acc2) :: acc1

              acc2 = List(tplLeaf2(row))
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else /* e2 != p2 */ {
              acc2 = tplLeaf2(row) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0
            }

          } else if (e0 != p0) {
            acc1 = tplBranch1(prevRow, acc2) :: acc1
            acc0 = tplBranch0(prevRow, acc1) :: acc0

            acc2 = List(tplLeaf2(row))
            acc1 = Nil

          } else if (e1 != p1) {
            acc1 = tplBranch1(prevRow, acc2) :: acc1

            acc2 = List(tplLeaf2(row))

          } else /* e2 != p2 */ {
            acc2 = tplLeaf2(row) :: acc2
          }
        } else {
          acc2 = List(tplLeaf2(row))
          nextRow = true
        }

        prevRow = row
        p0 = e0
        p1 = e1
        i -= 1
      }
      acc0
    }
  }


  final private def rows2nested3(rows: jArrayList[Row]): List[Tpl] = {
    if (rows.size == 1) {
      row = rows.get(0)
      List(tplBranch0(row,
        List(tplBranch1(row,
          List(tplBranch2(row,
            List(tplLeaf3(row))))))))

    } else {
      var i = rows.size - 1
      while (i != -1) {
        row = rows.get(i)
        e0 = row.get(0).asInstanceOf[jLong]
        e1 = row.get(1).asInstanceOf[jLong]
        e2 = row.get(2).asInstanceOf[jLong]

        if (nextRow) {
          if (i == 0) {
            if (e0 != p0) {
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1
              acc0 = tplBranch0(prevRow, acc1) :: acc0

              acc3 = List(tplLeaf3(row))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = List(tplBranch1(row, acc2))
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e1 != p1) {
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1

              acc3 = List(tplLeaf3(row))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e2 != p2) {
              acc2 = tplBranch2(prevRow, acc3) :: acc2

              acc3 = List(tplLeaf3(row))
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else /* e3 != p3 */ {
              acc3 = tplLeaf3(row) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0
            }

          } else if (e0 != p0) {
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1
            acc0 = tplBranch0(prevRow, acc1) :: acc0

            acc3 = List(tplLeaf3(row))
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1

            acc3 = List(tplLeaf3(row))
            acc2 = Nil

          } else if (e2 != p2) {
            acc2 = tplBranch2(prevRow, acc3) :: acc2

            acc3 = List(tplLeaf3(row))

          } else /* e3 != p3 */ {
            acc3 = tplLeaf3(row) :: acc3
          }
        } else {
          acc3 = List(tplLeaf3(row))
          nextRow = true
        }

        prevRow = row
        p0 = e0
        p1 = e1
        p2 = e2
        i -= 1
      }
      acc0
    }
  }


  final private def rows2nested4(rows: jArrayList[Row]): List[Tpl] = {
    if (rows.size == 1) {
      row = rows.get(0)
      List(tplBranch0(row,
        List(tplBranch1(row,
          List(tplBranch2(row,
            List(tplBranch3(row,
              List(tplLeaf4(row))))))))))

    } else {
      var i = rows.size - 1
      while (i != -1) {
        row = rows.get(i)
        e0 = row.get(0).asInstanceOf[jLong]
        e1 = row.get(1).asInstanceOf[jLong]
        e2 = row.get(2).asInstanceOf[jLong]
        e3 = row.get(3).asInstanceOf[jLong]

        if (nextRow) {
          if (i == 0) {
            if (e0 != p0) {
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1
              acc0 = tplBranch0(prevRow, acc1) :: acc0

              acc4 = List(tplLeaf4(row))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = List(tplBranch1(row, acc2))
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e1 != p1) {
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1

              acc4 = List(tplLeaf4(row))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e2 != p2) {
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2

              acc4 = List(tplLeaf4(row))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e3 != p3) {
              acc3 = tplBranch3(prevRow, acc4) :: acc3

              acc4 = List(tplLeaf4(row))
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else /* e4 != p4 */ {
              acc4 = tplLeaf4(row) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0
            }

          } else if (e0 != p0) {
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1
            acc0 = tplBranch0(prevRow, acc1) :: acc0

            acc4 = List(tplLeaf4(row))
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1

            acc4 = List(tplLeaf4(row))
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2

            acc4 = List(tplLeaf4(row))
            acc3 = Nil

          } else if (e3 != p3) {
            acc3 = tplBranch3(prevRow, acc4) :: acc3

            acc4 = List(tplLeaf4(row))

          } else /* e4 != p4 */ {
            acc4 = tplLeaf4(row) :: acc4
          }
        } else {
          acc4 = List(tplLeaf4(row))
          nextRow = true
        }

        prevRow = row
        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
        i -= 1
      }
      acc0
    }
  }


  final private def rows2nested5(rows: jArrayList[Row]): List[Tpl] = {
    if (rows.size == 1) {
      row = rows.get(0)
      List(tplBranch0(row,
        List(tplBranch1(row,
          List(tplBranch2(row,
            List(tplBranch3(row,
              List(tplBranch4(row,
                List(tplLeaf5(row))))))))))))

    } else {
      var i = rows.size - 1
      while (i != -1) {
        row = rows.get(i)
        e0 = row.get(0).asInstanceOf[jLong]
        e1 = row.get(1).asInstanceOf[jLong]
        e2 = row.get(2).asInstanceOf[jLong]
        e3 = row.get(3).asInstanceOf[jLong]
        e4 = row.get(4).asInstanceOf[jLong]

        if (nextRow) {
          if (i == 0) {
            if (e0 != p0) {
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1
              acc0 = tplBranch0(prevRow, acc1) :: acc0

              acc5 = List(tplLeaf5(row))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = List(tplBranch1(row, acc2))
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e1 != p1) {
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1

              acc5 = List(tplLeaf5(row))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e2 != p2) {
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2

              acc5 = List(tplLeaf5(row))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e3 != p3) {
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3

              acc5 = List(tplLeaf5(row))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e4 != p4) {
              acc4 = tplBranch4(prevRow, acc5) :: acc4

              acc5 = List(tplLeaf5(row))
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else /* e5 != p5 */ {
              acc5 = tplLeaf5(row) :: acc5
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0
            }

          } else if (e0 != p0) {
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1
            acc0 = tplBranch0(prevRow, acc1) :: acc0

            acc5 = List(tplLeaf5(row))
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1

            acc5 = List(tplLeaf5(row))
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2

            acc5 = List(tplLeaf5(row))
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3

            acc5 = List(tplLeaf5(row))
            acc4 = Nil

          } else if (e4 != p4) {
            acc4 = tplBranch4(prevRow, acc5) :: acc4

            acc5 = List(tplLeaf5(row))

          } else /* e5 != p5 */ {
            acc5 = tplLeaf5(row) :: acc5
          }
        } else {
          acc5 = List(tplLeaf5(row))
          nextRow = true
        }

        prevRow = row
        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
        p4 = e4
        i -= 1
      }
      acc0
    }
  }


  final private def rows2nested6(rows: jArrayList[Row]): List[Tpl] = {
    if (rows.size == 1) {
      row = rows.get(0)
      List(tplBranch0(row,
        List(tplBranch1(row,
          List(tplBranch2(row,
            List(tplBranch3(row,
              List(tplBranch4(row,
                List(tplBranch5(row,
                  List(tplLeaf6(row))))))))))))))

    } else {
      var i = rows.size - 1
      while (i != -1) {
        row = rows.get(i)
        e0 = row.get(0).asInstanceOf[jLong]
        e1 = row.get(1).asInstanceOf[jLong]
        e2 = row.get(2).asInstanceOf[jLong]
        e3 = row.get(3).asInstanceOf[jLong]
        e4 = row.get(4).asInstanceOf[jLong]
        e5 = row.get(5).asInstanceOf[jLong]

        if (nextRow) {
          if (i == 0) {
            if (e0 != p0) {
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1
              acc0 = tplBranch0(prevRow, acc1) :: acc0

              acc6 = List(tplLeaf6(row))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = List(tplBranch1(row, acc2))
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e1 != p1) {
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1

              acc6 = List(tplLeaf6(row))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e2 != p2) {
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2

              acc6 = List(tplLeaf6(row))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e3 != p3) {
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3

              acc6 = List(tplLeaf6(row))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e4 != p4) {
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4

              acc6 = List(tplLeaf6(row))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e5 != p5) {
              acc5 = tplBranch5(prevRow, acc6) :: acc5

              acc6 = List(tplLeaf6(row))
              acc5 = tplBranch5(row, acc6) :: acc5
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else /* e6 != p6 */ {
              acc6 = tplLeaf6(row) :: acc6
              acc5 = tplBranch5(row, acc6) :: acc5
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0
            }

          } else if (e0 != p0) {
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1
            acc0 = tplBranch0(prevRow, acc1) :: acc0

            acc6 = List(tplLeaf6(row))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1

            acc6 = List(tplLeaf6(row))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2

            acc6 = List(tplLeaf6(row))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3

            acc6 = List(tplLeaf6(row))
            acc5 = Nil
            acc4 = Nil

          } else if (e4 != p4) {
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4

            acc6 = List(tplLeaf6(row))
            acc5 = Nil

          } else if (e5 != p5) {
            acc5 = tplBranch5(prevRow, acc6) :: acc5

            acc6 = List(tplLeaf6(row))

          } else /* e6 != p6 */ {
            acc6 = tplLeaf6(row) :: acc6
          }
        } else {
          acc6 = List(tplLeaf6(row))
          nextRow = true
        }

        prevRow = row
        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
        p4 = e4
        p5 = e5
        i -= 1
      }
      acc0
    }
  }

  final private def rows2nested7(rows: jArrayList[Row]): List[Tpl] = {
    if (rows.size() == 1) {
      row = rows.get(0)
      List(tplBranch0(row,
        List(tplBranch1(row,
          List(tplBranch2(row,
            List(tplBranch3(row,
              List(tplBranch4(row,
                List(tplBranch5(row,
                  List(tplBranch6(row,
                    List(tplLeaf7(row))))))))))))))))

    } else {
      var i = rows.size - 1
      while (i != -1) {
        row = rows.get(i)
        e0 = row.get(0).asInstanceOf[jLong]
        e1 = row.get(1).asInstanceOf[jLong]
        e2 = row.get(2).asInstanceOf[jLong]
        e3 = row.get(3).asInstanceOf[jLong]
        e4 = row.get(4).asInstanceOf[jLong]
        e5 = row.get(5).asInstanceOf[jLong]
        e6 = row.get(6).asInstanceOf[jLong]

        if (nextRow) {
          if (i == 0) {
            if (e0 != p0) {
              acc6 = tplBranch6(prevRow, acc7) :: acc6
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1
              acc0 = tplBranch0(prevRow, acc1) :: acc0

              acc7 = List(tplLeaf7(row))
              acc6 = List(tplBranch6(row, acc7))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = List(tplBranch1(row, acc2))
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e1 != p1) {
              acc6 = tplBranch6(prevRow, acc7) :: acc6
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2
              acc1 = tplBranch1(prevRow, acc2) :: acc1

              acc7 = List(tplLeaf7(row))
              acc6 = List(tplBranch6(row, acc7))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = List(tplBranch2(row, acc3))
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e2 != p2) {
              acc6 = tplBranch6(prevRow, acc7) :: acc6
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3
              acc2 = tplBranch2(prevRow, acc3) :: acc2

              acc7 = List(tplLeaf7(row))
              acc6 = List(tplBranch6(row, acc7))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = List(tplBranch3(row, acc4))
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e3 != p3) {
              acc6 = tplBranch6(prevRow, acc7) :: acc6
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4
              acc3 = tplBranch3(prevRow, acc4) :: acc3

              acc7 = List(tplLeaf7(row))
              acc6 = List(tplBranch6(row, acc7))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = List(tplBranch4(row, acc5))
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e4 != p4) {
              acc6 = tplBranch6(prevRow, acc7) :: acc6
              acc5 = tplBranch5(prevRow, acc6) :: acc5
              acc4 = tplBranch4(prevRow, acc5) :: acc4

              acc7 = List(tplLeaf7(row))
              acc6 = List(tplBranch6(row, acc7))
              acc5 = List(tplBranch5(row, acc6))
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e5 != p5) {
              acc6 = tplBranch6(prevRow, acc7) :: acc6
              acc5 = tplBranch5(prevRow, acc6) :: acc5

              acc7 = List(tplLeaf7(row))
              acc6 = List(tplBranch6(row, acc7))
              acc5 = tplBranch5(row, acc6) :: acc5
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else if (e6 != p6) {
              acc6 = tplBranch6(prevRow, acc7) :: acc6

              acc7 = List(tplLeaf7(row))
              acc6 = tplBranch6(row, acc7) :: acc6
              acc5 = tplBranch5(row, acc6) :: acc5
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0

            } else /* e7 != p7 */ {
              acc7 = tplLeaf7(row) :: acc7
              acc6 = tplBranch6(row, acc7) :: acc6
              acc5 = tplBranch5(row, acc6) :: acc5
              acc4 = tplBranch4(row, acc5) :: acc4
              acc3 = tplBranch3(row, acc4) :: acc3
              acc2 = tplBranch2(row, acc3) :: acc2
              acc1 = tplBranch1(row, acc2) :: acc1
              acc0 = tplBranch0(row, acc1) :: acc0
            }

          } else if (e0 != p0) {
            acc6 = tplBranch6(prevRow, acc7) :: acc6
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1
            acc0 = tplBranch0(prevRow, acc1) :: acc0

            acc7 = List(tplLeaf7(row))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            acc6 = tplBranch6(prevRow, acc7) :: acc6
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2
            acc1 = tplBranch1(prevRow, acc2) :: acc1

            acc7 = List(tplLeaf7(row))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            acc6 = tplBranch6(prevRow, acc7) :: acc6
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3
            acc2 = tplBranch2(prevRow, acc3) :: acc2

            acc7 = List(tplLeaf7(row))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            acc6 = tplBranch6(prevRow, acc7) :: acc6
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4
            acc3 = tplBranch3(prevRow, acc4) :: acc3

            acc7 = List(tplLeaf7(row))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil

          } else if (e4 != p4) {
            acc6 = tplBranch6(prevRow, acc7) :: acc6
            acc5 = tplBranch5(prevRow, acc6) :: acc5
            acc4 = tplBranch4(prevRow, acc5) :: acc4

            acc7 = List(tplLeaf7(row))
            acc6 = Nil
            acc5 = Nil

          } else if (e5 != p5) {
            acc6 = tplBranch6(prevRow, acc7) :: acc6
            acc5 = tplBranch5(prevRow, acc6) :: acc5

            acc7 = List(tplLeaf7(row))
            acc6 = Nil

          } else if (e6 != p6) {
            acc6 = tplBranch6(prevRow, acc7) :: acc6

            acc7 = List(tplLeaf7(row))

          } else /* e7 != p7 */ {
            acc7 = tplLeaf7(row) :: acc7
          }
        } else {
          acc7 = List(tplLeaf7(row))
          nextRow = true
        }

        prevRow = row
        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
        p4 = e4
        p5 = e5
        p6 = e6
        i -= 1
      }
      acc0
    }
  }
}