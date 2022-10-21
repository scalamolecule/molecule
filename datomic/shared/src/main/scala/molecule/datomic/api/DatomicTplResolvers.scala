package molecule.datomic.api

import java.util
import molecule.boilerplate.ast.moleculeModel._
import molecule.boilerplate.markers.namespaceMarkers.Molecule_02
import java.util.{Collections, Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
;

trait DatomicTplResolvers {

  // todo: make tail recursion
  def getResolvers(es: Seq[Element]): Seq[Any => AnyRef] = {
    es.foldLeft(Seq.empty[Any => AnyRef]) { case (acc, e) =>
      e match {
        case _: AtomManString            => acc :+ (_.toString)
        case _: AtomManInt               => acc :+ (_.toString.toInt.asInstanceOf[AnyRef])
        case Bond(ns, refAttr, refNs, 1) => acc
        case _                           => acc
      }
    }
  }

  def tpl2stmts_2[A, B](molecule: Molecule_02[A, B]): ((A, B)) => util.ArrayList[AnyRef] = {
//    val sb = new StmtsBuilder

    val es             = getResolvers(molecule.elements)
    val a: A => AnyRef = es.head
    val b: B => AnyRef = es(1)

    (tpl: (A, B)) => {
      val row = new java.util.ArrayList[AnyRef](2)
      row.add(a(tpl._1))
      row.add(b(tpl._2))
      row
    }
  }
}
