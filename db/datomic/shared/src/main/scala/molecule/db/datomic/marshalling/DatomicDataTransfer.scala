package molecule.db.datomic.marshalling

import java.util.{List => jList}
import molecule.boilerplate.ast.Model
import molecule.boilerplate.ast.Model._
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


case class DatomicDataTransfer(elements: Seq[Element], rows: jList[jList[AnyRef]]) {


  val oneInt1 = new ListBuffer[Int]
  val c = oneInt1.toList
  val oneInt = new ArrayBuffer[Int]

  oneInt.addOne(1)

  val b = oneInt.toArray
//  val oneInt = new mutable.ArraySeq[Int]


  val a = AttrOneManInt("ns", "attr", V, oneInt.toSeq)
  val oneString2 = new ArrayBuffer[String]

  val data: Seq[Element] = ???

  val packRow = (row: jList[AnyRef]) => ()

  def pack: (Seq[Model.Element], List[List[Int]]) = {
    rows.forEach(row => packRow(row))
    (data, Nil)
  }

  def unpack[Tpl]: List[Tpl] = ???
}
