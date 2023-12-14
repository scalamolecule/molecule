package molecule.document.mongodb.dataModel

import molecule.DataModel

object Refs2 extends DataModel(10) {

  trait A {
    val i    = oneInt
    val ii   = setInt
    val s    = oneString
    val bool = oneBoolean

    val a  = one[A].owner
    val b  = one[B].owner
    val b1 = one[B].owner
    val b2 = one[B].owner
    val c  = one[C].owner
    val d  = one[D].owner

    val aa = many[A].owner
    val bb = many[B].owner
    val cc = many[C].owner
    val dd = many[D].owner

    val ownB  = one[B].owner
    val ownBb = many[B].owner

    val ownC  = one[C].owner
    val ownCc = many[C].owner
  }

  trait B {
    val i  = oneInt
    val ii = setInt
    val s  = oneString

    val a  = one[A].owner
    val b  = one[B].owner
    val c  = one[C].owner
    val c1 = one[C].owner
    val d  = one[D].owner

    val aa = many[A].owner
    val bb = many[B].owner
    val cc = many[C].owner
    val dd = many[D].owner

    val ownC  = one[C].owner
    val ownCc = many[C].owner
  }

  trait C {
    val i  = oneInt
    val s  = oneString
    val ii = setInt
    val a  = one[A].owner
    val d  = one[D].owner
    val dd = many[D].owner

    val ownD  = one[D].owner
    val ownDd = many[D].owner
  }


  trait D {
    val i  = oneInt
    val s  = oneString
    val e  = one[E].owner
    val e1 = one[E].owner
    val ee = many[E].owner
  }

  trait E {
    val i  = oneInt
    val s  = oneString
    val f  = one[F].owner
    val ff = many[F].owner
  }

  trait F {
    val i  = oneInt
    val s  = oneString
    val g  = one[G].owner
    val gg = many[G].owner
  }

  trait G {
    val i  = oneInt
    val s  = oneString
    val h  = one[H].owner
    val hh = many[H].owner
  }

  trait H {
    val i = oneInt
    val s = oneString
  }
}
