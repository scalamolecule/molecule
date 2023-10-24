package molecule.document.mongodb.query.casting

import java.lang.{Long => jLong}
import molecule.core.query.Model2Query
import molecule.document.mongodb.query.SqlQueryBase


trait NestOpt[Tpl] { self: Model2Query
  with SqlQueryBase
  with CastNestedBranch_ =>

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
  private lazy val txAttrs      = aritiess.head.flatten.dropWhile(_ != -1).tail.sum

  // First attr index for each level
  private lazy val i0 = 1 + nestedLevels // 1-based indexes for jdbc ResultSet
  private lazy val i1 = i0 + aritiess.head.flatten.takeWhile(_ != -1).sum
  private lazy val i2 = i1 + aritiess(1).flatten.takeWhile(_ != -1).sum
  private lazy val i3 = i2 + aritiess(2).flatten.takeWhile(_ != -1).sum
  private lazy val i4 = i3 + aritiess(3).flatten.takeWhile(_ != -1).sum
  private lazy val i5 = i4 + aritiess(4).flatten.takeWhile(_ != -1).sum
  private lazy val i6 = i5 + aritiess(5).flatten.takeWhile(_ != -1).sum
  private lazy val i7 = i6 + aritiess(6).flatten.takeWhile(_ != -1).sum

  private var rowCount                                  = -1
  private lazy val rowIndexTx                           = 1 + rowCount - txAttrs // 1-based indexes for jdbc ResultSet
  private lazy val tplBranch0: (Row, NestedTpls) => Tpl = castBranch[Tpl](aritiess(0), castss(0), i0, rowIndexTx)
  private lazy val tplBranch1: (Row, NestedTpls) => Any = castBranch[Any](aritiess(1), castss(1), i1, 0)
  private lazy val tplBranch2: (Row, NestedTpls) => Any = castBranch[Any](aritiess(2), castss(2), i2, 0)
  private lazy val tplBranch3: (Row, NestedTpls) => Any = castBranch[Any](aritiess(3), castss(3), i3, 0)
  private lazy val tplBranch4: (Row, NestedTpls) => Any = castBranch[Any](aritiess(4), castss(4), i4, 0)
  private lazy val tplBranch5: (Row, NestedTpls) => Any = castBranch[Any](aritiess(5), castss(5), i5, 0)
  private lazy val tplBranch6: (Row, NestedTpls) => Any = castBranch[Any](aritiess(6), castss(6), i6, 0)

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


  final def rows2nestedOpt(rows: Row): List[Tpl] = {
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

  final private def flatten(list: List[Any]): List[Any] = {
    list.flatMap {
      case null         => None
      case Some(v)      => Some(Some(v))
      case None         => None
      case tpl: Product =>
        if ((0 until tpl.productArity).forall(i =>
          tpl.productElement(i) == null
            || tpl.productElement(i) == None
            || tpl.productElement(i) == Nil // empty nested
        )) None else Some(tpl)
      case v            => Some(v)
    }
  }

  final private def rows2nested1(rows: Row): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc1 = List(tplLeaf1(rows))
      acc0 = List(tplBranch0(rows, flatten(acc1)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        if (nextRow) {
          if (rows.isFirst) { // last going backwards
            if (e0 != p0) {
              // Use previous row (going backwards)
              rows.next()
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc1 = List(tplLeaf1(rows))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e1 != p1 */ {
              acc1 = tplLeaf1(rows) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            // Use previous row (going backwards)
            rows.next()
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc1 = List(tplLeaf1(rows))

          } else /* e1 != p1 */ {
            acc1 = tplLeaf1(rows) :: acc1
          }
        } else {
          acc1 = List(tplLeaf1(rows))
          nextRow = true
        }
        p0 = e0
      }
    }
    acc0
  }


  final private def rows2nested2(rows: Row): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc2 = List(tplLeaf2(rows))
      acc1 = List(tplBranch1(rows, flatten(acc2)))
      acc0 = List(tplBranch0(rows, flatten(acc1)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        e1 = rows.getLong(2)

        if (nextRow) {
          if (rows.isFirst) {
            if (e0 != p0) {
              rows.next()
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc2 = List(tplLeaf2(rows))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc2 = List(tplLeaf2(rows))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e2 != p2 */ {
              acc2 = tplLeaf2(rows) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc2 = List(tplLeaf2(rows))
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc2 = List(tplLeaf2(rows))

          } else /* e2 != p2 */ {
            acc2 = tplLeaf2(rows) :: acc2
          }
        } else {
          acc2 = List(tplLeaf2(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
      }
    }
    acc0
  }


  final private def rows2nested3(rows: Row): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc3 = List(tplLeaf3(rows))
      acc2 = List(tplBranch2(rows, flatten(acc3)))
      acc1 = List(tplBranch1(rows, flatten(acc2)))
      acc0 = List(tplBranch0(rows, flatten(acc1)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        e1 = rows.getLong(2)
        e2 = rows.getLong(3)

        if (nextRow) {
          if (rows.isFirst) {
            if (e0 != p0) {
              rows.next()
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc3 = List(tplLeaf3(rows))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc3 = List(tplLeaf3(rows))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc3 = List(tplLeaf3(rows))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e3 != p3 */ {
              acc3 = tplLeaf3(rows) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc3 = List(tplLeaf3(rows))
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc3 = List(tplLeaf3(rows))
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            rows.previous()

            acc3 = List(tplLeaf3(rows))

          } else /* e3 != p3 */ {
            acc3 = tplLeaf3(rows) :: acc3
          }
        } else {
          acc3 = List(tplLeaf3(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
        p2 = e2
      }
    }
    acc0
  }


  final private def rows2nested4(rows: Row): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc4 = List(tplLeaf4(rows))
      acc3 = List(tplBranch3(rows, flatten(acc4)))
      acc2 = List(tplBranch2(rows, flatten(acc3)))
      acc1 = List(tplBranch1(rows, flatten(acc2)))
      acc0 = List(tplBranch0(rows, flatten(acc1)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        e1 = rows.getLong(2)
        e2 = rows.getLong(3)
        e3 = rows.getLong(4)

        if (nextRow) {
          if (rows.isFirst) {
            if (e0 != p0) {
              rows.next()
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e4 != p4 */ {
              acc4 = tplLeaf4(rows) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc4 = List(tplLeaf4(rows))
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc4 = List(tplLeaf4(rows))
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            rows.previous()

            acc4 = List(tplLeaf4(rows))
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            rows.previous()

            acc4 = List(tplLeaf4(rows))

          } else /* e4 != p4 */ {
            acc4 = tplLeaf4(rows) :: acc4
          }
        } else {
          acc4 = List(tplLeaf4(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
      }
    }
    acc0
  }


  final private def rows2nested5(rows: Row): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc5 = List(tplLeaf5(rows))
      acc4 = List(tplBranch4(rows, flatten(acc5)))
      acc3 = List(tplBranch3(rows, flatten(acc4)))
      acc2 = List(tplBranch2(rows, flatten(acc3)))
      acc1 = List(tplBranch1(rows, flatten(acc2)))
      acc0 = List(tplBranch0(rows, flatten(acc1)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        e1 = rows.getLong(2)
        e2 = rows.getLong(3)
        e3 = rows.getLong(4)
        e4 = rows.getLong(5)

        if (nextRow) {
          if (rows.isFirst) {
            if (e0 != p0) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e5 != p5 */ {
              acc5 = tplLeaf5(rows) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc5 = List(tplLeaf5(rows))
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc5 = List(tplLeaf5(rows))
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            rows.previous()

            acc5 = List(tplLeaf5(rows))
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            rows.previous()

            acc5 = List(tplLeaf5(rows))
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            rows.previous()

            acc5 = List(tplLeaf5(rows))

          } else /* e5 != p5 */ {
            acc5 = tplLeaf5(rows) :: acc5
          }
        } else {
          acc5 = List(tplLeaf5(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
        p4 = e4
      }
    }
    acc0
  }


  final private def rows2nested6(rows: Row): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc6 = List(tplLeaf6(rows))
      acc5 = List(tplBranch5(rows, flatten(acc6)))
      acc4 = List(tplBranch4(rows, flatten(acc5)))
      acc3 = List(tplBranch3(rows, flatten(acc4)))
      acc2 = List(tplBranch2(rows, flatten(acc3)))
      acc1 = List(tplBranch1(rows, flatten(acc2)))
      acc0 = List(tplBranch0(rows, flatten(acc1)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        e1 = rows.getLong(2)
        e2 = rows.getLong(3)
        e3 = rows.getLong(4)
        e4 = rows.getLong(5)
        e5 = rows.getLong(6)

        if (nextRow) {
          if (rows.isFirst) {
            if (e0 != p0) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e5 != p5) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e6 != p6 */ {
              acc6 = tplLeaf6(rows) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc6 = List(tplLeaf6(rows))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc6 = List(tplLeaf6(rows))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            rows.previous()

            acc6 = List(tplLeaf6(rows))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            rows.previous()

            acc6 = List(tplLeaf6(rows))
            acc5 = Nil
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            rows.previous()

            acc6 = List(tplLeaf6(rows))
            acc5 = Nil

          } else if (e5 != p5) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            rows.previous()

            acc6 = List(tplLeaf6(rows))

          } else /* e6 != p6 */ {
            acc6 = tplLeaf6(rows) :: acc6
          }
        } else {
          acc6 = List(tplLeaf6(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
        p4 = e4
        p5 = e5
      }
    }
    acc0
  }

  final private def rows2nested7(rows: Row): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc7 = List(tplLeaf7(rows))
      acc6 = List(tplBranch6(rows, flatten(acc7)))
      acc5 = List(tplBranch5(rows, flatten(acc6)))
      acc4 = List(tplBranch4(rows, flatten(acc5)))
      acc3 = List(tplBranch3(rows, flatten(acc4)))
      acc2 = List(tplBranch2(rows, flatten(acc3)))
      acc1 = List(tplBranch1(rows, flatten(acc2)))
      acc0 = List(tplBranch0(rows, flatten(acc1)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        e1 = rows.getLong(2)
        e2 = rows.getLong(3)
        e3 = rows.getLong(4)
        e4 = rows.getLong(5)
        e5 = rows.getLong(6)
        e6 = rows.getLong(7)

        if (nextRow) {
          if (rows.isFirst) {
            if (e0 != p0) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e5 != p5) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7)))
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e6 != p6) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e7 != p7 */ {
              acc7 = tplLeaf7(rows) :: acc7
              acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil
            acc5 = Nil

          } else if (e5 != p5) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil

          } else if (e6 != p6) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7)) :: acc6
            rows.previous()

            acc7 = List(tplLeaf7(rows))

          } else /* e7 != p7 */ {
            acc7 = tplLeaf7(rows) :: acc7
          }
        } else {
          acc7 = List(tplLeaf7(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
        p2 = e2
        p3 = e3
        p4 = e4
        p5 = e5
        p6 = e6
      }
    }
    acc0
  }
}