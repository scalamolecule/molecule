package molecule.sql.core.query.casting

import scala.collection.mutable.ListBuffer


trait NestOpt[Tpl] extends Nest[Tpl] {

  final def rows2nestedOpt(rows: RS): List[Tpl] = {
    val result = nestedLevels match {
      case 1 => rows2nested1(rows)
      case 2 => rows2nested2(rows)
      case 3 => rows2nested3(rows)
      case 4 => rows2nested4(rows)
      case 5 => rows2nested5(rows)
      case 6 => rows2nested6(rows)
      case 7 => rows2nested7(rows)
    }
    // Exclude empty rows with no nested
    result.filterNot(_ == Nil)
  }

  final private def flatten(list: List[Any], hasNested: Boolean = true): List[Any] = {
    val buf  = new ListBuffer[Any]
    val rows = list.iterator
    while (rows.hasNext) {
      val row = rows.next()
      //      println("---------- row:  " + row)
      row match {
        // Nested empty single value not to be added
        case set: Set[_] if set.isEmpty    => ()
        case seq: Seq[_] if seq.isEmpty    => ()
        case map: Map[_, _] if map.isEmpty => ()
        case None                          => ()
        case null                          => ()

        // Nested tuple
        case nestedTuple: Product =>
          var hasEmpty = false
          val last     = nestedTuple.productArity - 1
          val allEmpty = (0 until nestedTuple.productArity).foldLeft(0) {

            // When last value is an optional list of further optional nested tuples then it can be empty
            case (acc, `last`) if hasNested => acc

            case (acc, i) => nestedTuple.productElement(i) match {
              case None                          => acc // ok with empty optional
              case set: Set[_] if set.isEmpty    => hasEmpty = true; acc
              case seq: Seq[_] if seq.isEmpty    => hasEmpty = true; acc
              case map: Map[_, _] if map.isEmpty => hasEmpty = true; acc
              case null                          => hasEmpty = true; acc
              case _                             => acc + 1
            }
          } == 0
          if (!allEmpty && !hasEmpty)
            buf += nestedTuple

        // Add existing nested single value
        case v => buf += v
      }
    }
    buf.toList
  }

  final private def rows2nested1(rows: RS): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc1 = List(tplLeaf1(rows))
      acc0 = List(tplBranch0(rows, flatten(acc1, false)))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        if (nextRow) {
          if (rows.isFirst) { // last going backwards
            if (e0 != p0) {
              // Use previous row (going backwards)
              rows.next()
              acc0 = tplBranch0(rows, flatten(acc1, false)) :: acc0
              rows.previous()

              acc1 = List(tplLeaf1(rows))
              acc0 = tplBranch0(rows, flatten(acc1, false)) :: acc0

            } else /* e1 != p1 */ {
              acc1 = tplLeaf1(rows) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1, false)) :: acc0
            }

          } else if (e0 != p0) {
            // Use previous row (going backwards)
            rows.next()
            acc0 = tplBranch0(rows, flatten(acc1, false)) :: acc0
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


  final private def rows2nested2(rows: RS): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc2 = List(tplLeaf2(rows))
      acc1 = List(tplBranch1(rows, flatten(acc2, false)))
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
              acc1 = tplBranch1(rows, flatten(acc2, false)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc2 = List(tplLeaf2(rows))
              acc1 = List(tplBranch1(rows, flatten(acc2, false)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc1 = tplBranch1(rows, flatten(acc2, false)) :: acc1
              rows.previous()

              acc2 = List(tplLeaf2(rows))
              acc1 = tplBranch1(rows, flatten(acc2, false)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e2 != p2 */ {
              acc2 = tplLeaf2(rows) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2, false)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc1 = tplBranch1(rows, flatten(acc2, false)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc2 = List(tplLeaf2(rows))
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc1 = tplBranch1(rows, flatten(acc2, false)) :: acc1
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


  final private def rows2nested3(rows: RS): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc3 = List(tplLeaf3(rows))
      acc2 = List(tplBranch2(rows, flatten(acc3, false)))
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
              acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc3 = List(tplLeaf3(rows))
              acc2 = List(tplBranch2(rows, flatten(acc3, false)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc3 = List(tplLeaf3(rows))
              acc2 = List(tplBranch2(rows, flatten(acc3, false)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
              rows.previous()

              acc3 = List(tplLeaf3(rows))
              acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e3 != p3 */ {
              acc3 = tplLeaf3(rows) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            rows.previous()

            acc3 = List(tplLeaf3(rows))
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc3 = List(tplLeaf3(rows))
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc2 = tplBranch2(rows, flatten(acc3, false)) :: acc2
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


  final private def rows2nested4(rows: RS): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc4 = List(tplLeaf4(rows))
      acc3 = List(tplBranch3(rows, flatten(acc4, false)))
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
              acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = List(tplBranch3(rows, flatten(acc4, false)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = List(tplBranch3(rows, flatten(acc4, false)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = List(tplBranch3(rows, flatten(acc4, false)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
              rows.previous()

              acc4 = List(tplLeaf4(rows))
              acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e4 != p4 */ {
              acc4 = tplLeaf4(rows) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
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
            acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
            rows.previous()

            acc4 = List(tplLeaf4(rows))
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            rows.previous()

            acc4 = List(tplLeaf4(rows))
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc3 = tplBranch3(rows, flatten(acc4, false)) :: acc3
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


  final private def rows2nested5(rows: RS): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc5 = List(tplLeaf5(rows))
      acc4 = List(tplBranch4(rows, flatten(acc5, false)))
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
              acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5, false)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5, false)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5, false)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = List(tplBranch4(rows, flatten(acc5, false)))
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
              rows.previous()

              acc5 = List(tplLeaf5(rows))
              acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e5 != p5 */ {
              acc5 = tplLeaf5(rows) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
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
            acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
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
            acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
            rows.previous()

            acc5 = List(tplLeaf5(rows))
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            rows.previous()

            acc5 = List(tplLeaf5(rows))
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc4 = tplBranch4(rows, flatten(acc5, false)) :: acc4
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


  final private def rows2nested6(rows: RS): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc6 = List(tplLeaf6(rows))
      acc5 = List(tplBranch5(rows, flatten(acc6, false)))
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
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6, false)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6, false)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6, false)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6, false)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = List(tplBranch5(rows, flatten(acc6, false)))
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e5 != p5) {
              rows.next()
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              rows.previous()

              acc6 = List(tplLeaf6(rows))
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e6 != p6 */ {
              acc6 = tplLeaf6(rows) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
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
            acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
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
            acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
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
            acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
            rows.previous()

            acc6 = List(tplLeaf6(rows))
            acc5 = Nil
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            rows.previous()

            acc6 = List(tplLeaf6(rows))
            acc5 = Nil

          } else if (e5 != p5) {
            rows.next()
            acc5 = tplBranch5(rows, flatten(acc6, false)) :: acc5
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

  final private def rows2nested7(rows: RS): List[Tpl] = {
    rowCount = getRowCount(rows)

    if (rowCount == 1) {
      rows.first()
      acc7 = List(tplLeaf7(rows))
      acc6 = List(tplBranch6(rows, flatten(acc7, false)))
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
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7, false)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = List(tplBranch1(rows, flatten(acc2)))
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7, false)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = List(tplBranch2(rows, flatten(acc3)))
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7, false)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = List(tplBranch3(rows, flatten(acc4)))
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7, false)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = List(tplBranch4(rows, flatten(acc5)))
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7, false)))
              acc5 = List(tplBranch5(rows, flatten(acc6)))
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e5 != p5) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = List(tplBranch6(rows, flatten(acc7, false)))
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else if (e6 != p6) {
              rows.next()
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              rows.previous()

              acc7 = List(tplLeaf7(rows))
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0

            } else /* e7 != p7 */ {
              acc7 = tplLeaf7(rows) :: acc7
              acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
              acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
              acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
              acc3 = tplBranch3(rows, flatten(acc4)) :: acc3
              acc2 = tplBranch2(rows, flatten(acc3)) :: acc2
              acc1 = tplBranch1(rows, flatten(acc2)) :: acc1
              acc0 = tplBranch0(rows, flatten(acc1)) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
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
            acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
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
            acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
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
            acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
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
            acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            acc4 = tplBranch4(rows, flatten(acc5)) :: acc4
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil
            acc5 = Nil

          } else if (e5 != p5) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
            acc5 = tplBranch5(rows, flatten(acc6)) :: acc5
            rows.previous()

            acc7 = List(tplLeaf7(rows))
            acc6 = Nil

          } else if (e6 != p6) {
            rows.next()
            acc6 = tplBranch6(rows, flatten(acc7, false)) :: acc6
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