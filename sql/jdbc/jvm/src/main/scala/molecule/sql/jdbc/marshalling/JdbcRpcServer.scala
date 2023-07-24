package molecule.sql.jdbc.marshalling

import molecule.core.marshalling.MoleculeRpcServer_AkkaHttp

/**
 * Server app serving http requests/ws from client
 *
 * Run with
 *
 *    sbt datomicJVM/run
 */
object JdbcRpcServer extends MoleculeRpcServer_AkkaHttp(JdbcRpcJVM) with App
