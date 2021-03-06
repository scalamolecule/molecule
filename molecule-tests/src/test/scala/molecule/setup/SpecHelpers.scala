package molecule.setup

import molecule.core.api.InputMolecule
import molecule.core.ast.Molecule
import molecule.core.ast.elements.Model
import molecule.datomic.base.ast.query.Query
import molecule.core.util.testing.MoleculeTestHelper
import molecule.datomic.base.facade.Conn
import molecule.datomic.base.transform.Model2Transaction
import org.specs2.matcher.MatchResult
import org.specs2.mutable.Specification


trait SpecHelpers extends Specification with MoleculeTestHelper {


  implicit class dsl2Model2query2string(molecule: Molecule)(implicit conn: Conn) {
    def -->(model: Model) = new {
      molecule._model === model

      // Molecule -> query
      def -->(query: Query) = new {
        molecule._query.toString === query.toString
        def -->(queryString: String) = {
          query.datalog(30) + formatInputs(query) === queryString
        }
      }

      // Input molecule + insert data
      def -->(data: Seq[Seq[Any]]) = new {
        def -->(txString: String) = {
          //          val (tx, _) = Model2Transaction(conn, model).tx(data)
          val tx = Model2Transaction(conn, model).insertStmts(data).flatten
          formatTx(tx) === txString
        }
        // Inspect
        def --->(txString: String) = {
          val tx = Model2Transaction(conn, model).insertStmts(data).flatten
          tx foreach println
          formatTx(tx) === txString
        }
      }
    }

    def -->(queryString: String) = {
      molecule._query.datalog(30) + formatInputs(molecule._query) === queryString
    }

    def -->(data: Seq[Seq[Any]]) = new {
      def -->(txString: String) = {
        //        val (tx, _) = Model2Transaction(conn, molecule._model).tx(data)
        val tx = Model2Transaction(conn, molecule._model).insertStmts(data).flatten
        formatTx(tx) === txString
      }
    }
  }

  implicit class inputDsl2Model2query2string(inputMolecule: InputMolecule) {
    def -->(model: Model) = new {
      inputMolecule._model === model
      def -->(query: Query) = new {
        inputMolecule._query === query
        def -->(queryString: String) = {
          query.datalog(30) === queryString
        }
      }
    }

    def -->(queryString: String) = {
      inputMolecule._query.datalog(30) === queryString
    }
  }
}