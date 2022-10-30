package codegen

object DatomicTestGenerator extends App {

  db.datomic.test.expr._ExprOne_Types.generate
}
