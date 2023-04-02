package molecule.base.codegen.render

import molecule.base.ast.SchemaAST._


case class Schema(schema: MetaSchema) {

  def get: String =
    s"""|/*
        |* AUTO-GENERATED schema boilerplate code
        |*
        |* To change:
        |* 1. edit data model file in `${schema.pkg}.dataModel/`
        |* 2. `sbt compile -Dmolecule=true`
        |*/
        |package ${schema.pkg}.schema
        |import molecule.base.api.SchemaTransaction
        |import molecule.base.ast.SchemaAST._
        |
        |
        |object ${schema.domain}Schema extends SchemaTransaction {
        |
        |  val metaSchema: MetaSchema =
        |    ${schema.render(2)}
        |
        |
        |  val nsMap: Map[String, MetaNs] = ${schema.nsMap(1)}
        |
        |
        |  val attrMap: Map[String, (Cardinality, String, Seq[String])] = ${schema.attrMap(1)}
        |
        |
        |  val uniqueAttrs: List[String] = ${schema.uniqueAttrs}
        |
        |
        |${Schema_Datomic(schema).get}
        |}""".stripMargin
}
