package molecule.coreTests.dataModels.core.dataModel

import molecule.DataModel

object Refs extends DataModel(10) {

  trait A {
    val i    = oneInt
    val ii   = setInt
    val s    = oneString
    val bool = oneBoolean

    val a  = one[A]
    val b  = one[B]
    val b1 = one[B]
    val b2 = one[B]
    val c  = one[C]
    val d  = one[D]

    val aa = many[A]
    val bb = many[B]
    val cc = many[C]
    val dd = many[D]

    val ownB  = one[B].owner
    val ownBb = many[B].owner

    val ownC  = one[C].owner
    val ownCc = many[C].owner
  }

  trait B {
    val i  = oneInt
    val ii = setInt
    val s  = oneString

    val a  = one[A]
    val b  = one[B]
    val c  = one[C]
    val c1 = one[C]
    val d  = one[D]

    val aa = many[A]
    val bb = many[B]
    val cc = many[C]
    val dd = many[D]

    val ownC  = one[C].owner
    val ownCc = many[C].owner
  }

  trait C {
    val i  = oneInt
    val s  = oneString
    val ii = setInt
    val a  = one[A]
    val d  = one[D]
    val dd = many[D]

    val ownD  = one[D].owner
    val ownDd = many[D].owner
  }


  trait D {
    val i  = oneInt
    val s  = oneString
    val e  = one[E]
    val e1 = one[E]
    val ee = many[E]
  }

  trait E {
    val i  = oneInt
    val s  = oneString
    val f  = one[F]
    val ff = many[F]
  }

  trait F {
    val i  = oneInt
    val s  = oneString
    val g  = one[G]
    val gg = many[G]
  }

  trait G {
    val i  = oneInt
    val s  = oneString
    val h  = one[H]
    val hh = many[H]
  }

  trait H {
    val i = oneInt
    val s = oneString
  }
}
