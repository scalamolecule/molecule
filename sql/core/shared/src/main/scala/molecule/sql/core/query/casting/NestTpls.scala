package molecule.sql.core.query.casting

import java.lang.{Long => jLong}
import molecule.sql.core.query.SqlQueryBase

class NestTpls extends SqlQueryBase {

  // Previous entity ids on each level
  protected var p0: jLong = 0L
  protected var p1: jLong = 0L
  protected var p2: jLong = 0L
  protected var p3: jLong = 0L
  protected var p4: jLong = 0L
  protected var p5: jLong = 0L
  protected var p6: jLong = 0L

  // Current entity ids on each level
  protected var e0: jLong = 0L
  protected var e1: jLong = 0L
  protected var e2: jLong = 0L
  protected var e3: jLong = 0L
  protected var e4: jLong = 0L
  protected var e5: jLong = 0L
  protected var e6: jLong = 0L

  protected var nextRow  = false

  protected var acc0: List[Any] = List.empty[Any]
  protected var acc1: List[Any] = List.empty[Any]
  protected var acc2: List[Any] = List.empty[Any]
  protected var acc3: List[Any] = List.empty[Any]
  protected var acc4: List[Any] = List.empty[Any]
  protected var acc5: List[Any] = List.empty[Any]
  protected var acc6: List[Any] = List.empty[Any]
  protected var acc7: List[Any] = List.empty[Any]


  final def rows2nested(rows: RS, casters: List[CastTuple]): List[Any] = {
    casters.length match {
      case 2 => rows2nested1(rows, casters)
      case 3 => rows2nested2(rows, casters)
      case 4 => rows2nested3(rows, casters)
      case 5 => rows2nested4(rows, casters)
      case 6 => rows2nested5(rows, casters)
      case 7 => rows2nested6(rows, casters)
      case 8 => rows2nested7(rows, casters)
    }
  }

  final private def rows2nested1(rows: RS, casters: List[CastTuple]): List[Any] = {
    val branch0: (RS, List[Any]) => Any = casters(0).branchCaster
    val leaf   : RS => Any              = casters(1).tupleCaster

    if (getRowCount(rows) == 1) {
      rows.first()
      acc1 = List(leaf(rows))
      acc0 = List(branch0(rows, acc1))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        if (nextRow) {
          if (rows.isFirst) { // last going backwards
            if (e0 != p0) {
              // Use previous row (going backwards)
              rows.next()
              acc0 = branch0(rows, acc1) :: acc0
              rows.previous()

              acc1 = List(leaf(rows))
              acc0 = branch0(rows, acc1) :: acc0

            } else /* e1 != p1 */ {
              acc1 = leaf(rows) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
            }

          } else if (e0 != p0) {
            // Use previous row (going backwards)
            rows.next()
            acc0 = branch0(rows, acc1) :: acc0
            rows.previous()

            acc1 = List(leaf(rows))

          } else /* e1 != p1 */ {
            acc1 = leaf(rows) :: acc1
          }
        } else {
          acc1 = List(leaf(rows))
          nextRow = true
        }
        p0 = e0
      }
    }
    acc0
  }


  final private def rows2nested2(rows: RS, casters: List[CastTuple]): List[Any] = {
    val branch0: (RS, List[Any]) => Any = casters(0).branchCaster
    val branch1: (RS, List[Any]) => Any = casters(1).branchCaster
    val leaf   : RS => Any              = casters(2).tupleCaster

    if (getRowCount(rows) == 1) {
      rows.first()
      acc2 = List(leaf(rows))
      acc1 = List(branch1(rows, acc2))
      acc0 = List(branch0(rows, acc1))

    } else {
      rows.afterLast()
      while (rows.previous()) {
        e0 = rows.getLong(1)
        e1 = rows.getLong(2)

        if (nextRow) {
          if (rows.isFirst) {
            if (e0 != p0) {
              rows.next()
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
              rows.previous()

              acc2 = List(leaf(rows))
              acc1 = List(branch1(rows, acc2))
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc1 = branch1(rows, acc2) :: acc1
              rows.previous()

              acc2 = List(leaf(rows))
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else /* e2 != p2 */ {
              acc2 = leaf(rows) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc1 = branch1(rows, acc2) :: acc1
            acc0 = branch0(rows, acc1) :: acc0
            rows.previous()

            acc2 = List(leaf(rows))
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc1 = branch1(rows, acc2) :: acc1
            rows.previous()

            acc2 = List(leaf(rows))

          } else /* e2 != p2 */ {
            acc2 = leaf(rows) :: acc2
          }
        } else {
          acc2 = List(leaf(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
      }
    }
    acc0
  }


  final private def rows2nested3(rows: RS, casters: List[CastTuple]): List[Any] = {
    val branch0: (RS, List[Any]) => Any = casters(0).branchCaster
    val branch1: (RS, List[Any]) => Any = casters(1).branchCaster
    val branch2: (RS, List[Any]) => Any = casters(2).branchCaster
    val leaf   : RS => Any              = casters(3).tupleCaster

    if (getRowCount(rows) == 1) {
      rows.first()
      acc3 = List(leaf(rows))
      acc2 = List(branch2(rows, acc3))
      acc1 = List(branch1(rows, acc2))
      acc0 = List(branch0(rows, acc1))

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
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
              rows.previous()

              acc3 = List(leaf(rows))
              acc2 = List(branch2(rows, acc3))
              acc1 = List(branch1(rows, acc2))
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              rows.previous()

              acc3 = List(leaf(rows))
              acc2 = List(branch2(rows, acc3))
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc2 = branch2(rows, acc3) :: acc2
              rows.previous()

              acc3 = List(leaf(rows))
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else /* e3 != p3 */ {
              acc3 = leaf(rows) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            acc0 = branch0(rows, acc1) :: acc0
            rows.previous()

            acc3 = List(leaf(rows))
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            rows.previous()

            acc3 = List(leaf(rows))
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc2 = branch2(rows, acc3) :: acc2
            rows.previous()

            acc3 = List(leaf(rows))

          } else /* e3 != p3 */ {
            acc3 = leaf(rows) :: acc3
          }
        } else {
          acc3 = List(leaf(rows))
          nextRow = true
        }

        p0 = e0
        p1 = e1
        p2 = e2
      }
    }
    acc0
  }


  final private def rows2nested4(rows: RS, casters: List[CastTuple]): List[Any] = {
    val branch0: (RS, List[Any]) => Any = casters(0).branchCaster
    val branch1: (RS, List[Any]) => Any = casters(1).branchCaster
    val branch2: (RS, List[Any]) => Any = casters(2).branchCaster
    val branch3: (RS, List[Any]) => Any = casters(3).branchCaster
    val leaf   : RS => Any              = casters(4).tupleCaster

    if (getRowCount(rows) == 1) {
      rows.first()
      acc4 = List(leaf(rows))
      acc3 = List(branch3(rows, acc4))
      acc2 = List(branch2(rows, acc3))
      acc1 = List(branch1(rows, acc2))
      acc0 = List(branch0(rows, acc1))

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
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
              rows.previous()

              acc4 = List(leaf(rows))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = List(branch1(rows, acc2))
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              rows.previous()

              acc4 = List(leaf(rows))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              rows.previous()

              acc4 = List(leaf(rows))
              acc3 = List(branch3(rows, acc4))
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc3 = branch3(rows, acc4) :: acc3
              rows.previous()

              acc4 = List(leaf(rows))
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else /* e4 != p4 */ {
              acc4 = leaf(rows) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            acc0 = branch0(rows, acc1) :: acc0
            rows.previous()

            acc4 = List(leaf(rows))
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            rows.previous()

            acc4 = List(leaf(rows))
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            rows.previous()

            acc4 = List(leaf(rows))
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc3 = branch3(rows, acc4) :: acc3
            rows.previous()

            acc4 = List(leaf(rows))

          } else /* e4 != p4 */ {
            acc4 = leaf(rows) :: acc4
          }
        } else {
          acc4 = List(leaf(rows))
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


  final private def rows2nested5(rows: RS, casters: List[CastTuple]): List[Any] = {
    val branch0: (RS, List[Any]) => Any = casters(0).branchCaster
    val branch1: (RS, List[Any]) => Any = casters(1).branchCaster
    val branch2: (RS, List[Any]) => Any = casters(2).branchCaster
    val branch3: (RS, List[Any]) => Any = casters(3).branchCaster
    val branch4: (RS, List[Any]) => Any = casters(4).branchCaster
    val leaf   : RS => Any              = casters(5).tupleCaster

    if (getRowCount(rows) == 1) {
      rows.first()
      acc5 = List(leaf(rows))
      acc4 = List(branch4(rows, acc5))
      acc3 = List(branch3(rows, acc4))
      acc2 = List(branch2(rows, acc3))
      acc1 = List(branch1(rows, acc2))
      acc0 = List(branch0(rows, acc1))

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
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
              rows.previous()

              acc5 = List(leaf(rows))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = List(branch1(rows, acc2))
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              rows.previous()

              acc5 = List(leaf(rows))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              rows.previous()

              acc5 = List(leaf(rows))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              rows.previous()

              acc5 = List(leaf(rows))
              acc4 = List(branch4(rows, acc5))
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc4 = branch4(rows, acc5) :: acc4
              rows.previous()

              acc5 = List(leaf(rows))
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else /* e5 != p5 */ {
              acc5 = leaf(rows) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            acc0 = branch0(rows, acc1) :: acc0
            rows.previous()

            acc5 = List(leaf(rows))
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            rows.previous()

            acc5 = List(leaf(rows))
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            rows.previous()

            acc5 = List(leaf(rows))
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            rows.previous()

            acc5 = List(leaf(rows))
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc4 = branch4(rows, acc5) :: acc4
            rows.previous()

            acc5 = List(leaf(rows))

          } else /* e5 != p5 */ {
            acc5 = leaf(rows) :: acc5
          }
        } else {
          acc5 = List(leaf(rows))
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


  final private def rows2nested6(rows: RS, casters: List[CastTuple]): List[Any] = {
    val branch0: (RS, List[Any]) => Any = casters(0).branchCaster
    val branch1: (RS, List[Any]) => Any = casters(1).branchCaster
    val branch2: (RS, List[Any]) => Any = casters(2).branchCaster
    val branch3: (RS, List[Any]) => Any = casters(3).branchCaster
    val branch4: (RS, List[Any]) => Any = casters(4).branchCaster
    val branch5: (RS, List[Any]) => Any = casters(5).branchCaster
    val leaf   : RS => Any              = casters(6).tupleCaster

    if (getRowCount(rows) == 1) {
      rows.first()
      acc6 = List(leaf(rows))
      acc5 = List(branch5(rows, acc6))
      acc4 = List(branch4(rows, acc5))
      acc3 = List(branch3(rows, acc4))
      acc2 = List(branch2(rows, acc3))
      acc1 = List(branch1(rows, acc2))
      acc0 = List(branch0(rows, acc1))

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
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
              rows.previous()

              acc6 = List(leaf(rows))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = List(branch1(rows, acc2))
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              rows.previous()

              acc6 = List(leaf(rows))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              rows.previous()

              acc6 = List(leaf(rows))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              rows.previous()

              acc6 = List(leaf(rows))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              rows.previous()

              acc6 = List(leaf(rows))
              acc5 = List(branch5(rows, acc6))
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e5 != p5) {
              rows.next()
              acc5 = branch5(rows, acc6) :: acc5
              rows.previous()

              acc6 = List(leaf(rows))
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else /* e6 != p6 */ {
              acc6 = leaf(rows) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            acc0 = branch0(rows, acc1) :: acc0
            rows.previous()

            acc6 = List(leaf(rows))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            rows.previous()

            acc6 = List(leaf(rows))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            rows.previous()

            acc6 = List(leaf(rows))
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            rows.previous()

            acc6 = List(leaf(rows))
            acc5 = Nil
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            rows.previous()

            acc6 = List(leaf(rows))
            acc5 = Nil

          } else if (e5 != p5) {
            rows.next()
            acc5 = branch5(rows, acc6) :: acc5
            rows.previous()

            acc6 = List(leaf(rows))

          } else /* e6 != p6 */ {
            acc6 = leaf(rows) :: acc6
          }
        } else {
          acc6 = List(leaf(rows))
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

  final private def rows2nested7(rows: RS, casters: List[CastTuple]): List[Any] = {
    val branch0: (RS, List[Any]) => Any = casters(0).branchCaster
    val branch1: (RS, List[Any]) => Any = casters(1).branchCaster
    val branch2: (RS, List[Any]) => Any = casters(2).branchCaster
    val branch3: (RS, List[Any]) => Any = casters(3).branchCaster
    val branch4: (RS, List[Any]) => Any = casters(4).branchCaster
    val branch5: (RS, List[Any]) => Any = casters(5).branchCaster
    val branch6: (RS, List[Any]) => Any = casters(6).branchCaster
    val leaf   : RS => Any              = casters(7).tupleCaster

    if (getRowCount(rows) == 1) {
      rows.first()
      acc7 = List(leaf(rows))
      acc6 = List(branch6(rows, acc7))
      acc5 = List(branch5(rows, acc6))
      acc4 = List(branch4(rows, acc5))
      acc3 = List(branch3(rows, acc4))
      acc2 = List(branch2(rows, acc3))
      acc1 = List(branch1(rows, acc2))
      acc0 = List(branch0(rows, acc1))

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
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
              rows.previous()

              acc7 = List(leaf(rows))
              acc6 = List(branch6(rows, acc7))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = List(branch1(rows, acc2))
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e1 != p1) {
              rows.next()
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              rows.previous()

              acc7 = List(leaf(rows))
              acc6 = List(branch6(rows, acc7))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = List(branch2(rows, acc3))
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e2 != p2) {
              rows.next()
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              rows.previous()

              acc7 = List(leaf(rows))
              acc6 = List(branch6(rows, acc7))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = List(branch3(rows, acc4))
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e3 != p3) {
              rows.next()
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              rows.previous()

              acc7 = List(leaf(rows))
              acc6 = List(branch6(rows, acc7))
              acc5 = List(branch5(rows, acc6))
              acc4 = List(branch4(rows, acc5))
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e4 != p4) {
              rows.next()
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              rows.previous()

              acc7 = List(leaf(rows))
              acc6 = List(branch6(rows, acc7))
              acc5 = List(branch5(rows, acc6))
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e5 != p5) {
              rows.next()
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              rows.previous()

              acc7 = List(leaf(rows))
              acc6 = List(branch6(rows, acc7))
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else if (e6 != p6) {
              rows.next()
              acc6 = branch6(rows, acc7) :: acc6
              rows.previous()

              acc7 = List(leaf(rows))
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0

            } else /* e7 != p7 */ {
              acc7 = leaf(rows) :: acc7
              acc6 = branch6(rows, acc7) :: acc6
              acc5 = branch5(rows, acc6) :: acc5
              acc4 = branch4(rows, acc5) :: acc4
              acc3 = branch3(rows, acc4) :: acc3
              acc2 = branch2(rows, acc3) :: acc2
              acc1 = branch1(rows, acc2) :: acc1
              acc0 = branch0(rows, acc1) :: acc0
            }

          } else if (e0 != p0) {
            rows.next()
            acc6 = branch6(rows, acc7) :: acc6
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            acc0 = branch0(rows, acc1) :: acc0
            rows.previous()

            acc7 = List(leaf(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil
            acc1 = Nil

          } else if (e1 != p1) {
            rows.next()
            acc6 = branch6(rows, acc7) :: acc6
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            acc1 = branch1(rows, acc2) :: acc1
            rows.previous()

            acc7 = List(leaf(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil
            acc2 = Nil

          } else if (e2 != p2) {
            rows.next()
            acc6 = branch6(rows, acc7) :: acc6
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            acc2 = branch2(rows, acc3) :: acc2
            rows.previous()

            acc7 = List(leaf(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil
            acc3 = Nil

          } else if (e3 != p3) {
            rows.next()
            acc6 = branch6(rows, acc7) :: acc6
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            acc3 = branch3(rows, acc4) :: acc3
            rows.previous()

            acc7 = List(leaf(rows))
            acc6 = Nil
            acc5 = Nil
            acc4 = Nil

          } else if (e4 != p4) {
            rows.next()
            acc6 = branch6(rows, acc7) :: acc6
            acc5 = branch5(rows, acc6) :: acc5
            acc4 = branch4(rows, acc5) :: acc4
            rows.previous()

            acc7 = List(leaf(rows))
            acc6 = Nil
            acc5 = Nil

          } else if (e5 != p5) {
            rows.next()
            acc6 = branch6(rows, acc7) :: acc6
            acc5 = branch5(rows, acc6) :: acc5
            rows.previous()

            acc7 = List(leaf(rows))
            acc6 = Nil

          } else if (e6 != p6) {
            rows.next()
            acc6 = branch6(rows, acc7) :: acc6
            rows.previous()

            acc7 = List(leaf(rows))

          } else /* e7 != p7 */ {
            acc7 = leaf(rows) :: acc7
          }
        } else {
          acc7 = List(leaf(rows))
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