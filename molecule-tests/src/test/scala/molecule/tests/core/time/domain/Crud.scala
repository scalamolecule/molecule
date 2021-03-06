package molecule.tests.core.time.domain

import molecule.tests.core.base.dsl.CoreTest._
import molecule.datomic.api.out2._
import molecule.datomic.base.facade.Conn


// Example domain class doing CRUD operations on the database
// set with the connection object at runtime

object Crud {

  def create(numbers: Int*)(implicit conn: Conn): Unit = {
    Ns.int insert numbers
  }

  def read(implicit conn: Conn): List[Int] = {
    Ns.int.get.sorted
  }

  def update(pair: (Int, Int))(implicit conn: Conn): Unit = {
    val (oldNumber, newNumber) = pair
    val e = Ns.e.int_(oldNumber).get.headOption.getOrElse{
      throw new IllegalArgumentException(s"Old number ($oldNumber) doesn't exist in db.")
    }
    Ns(e).int(newNumber).update
  }

  def delete(numbers: Int*)(implicit conn: Conn): Unit = {
    numbers.foreach(i =>
      // Find entity
      Ns.e.int_(i).get.foreach(e =>
        // Retract attribute value
        Ns(e).int().update
      )
    )
  }
}