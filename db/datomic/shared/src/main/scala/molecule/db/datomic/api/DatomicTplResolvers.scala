package molecule.db.datomic.api

import java.util
import molecule.boilerplate.ast.MoleculeModel._
import java.util.{Collections, Date, UUID, Iterator => jIterator, List => jList, Map => jMap, Set => jSet}
import molecule.boilerplate.api._
;

trait DatomicTplResolvers {

  // todo: make tail recursion
  def getResolvers(es: Seq[Element]): Seq[Any => AnyRef] = {
    es.foldLeft(Seq.empty[Any => AnyRef]) { case (acc, e) =>
      e match {
        case _: AttrOneManString           => acc :+ (_.toString)
        case _: AttrOneManInt             => acc :+ (_.toString.toInt.asInstanceOf[AnyRef])
        case Ref(ns, refAttr, refNs, one) => acc
        case _                            => acc
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
