// ============================================================================
//
// Copyright (C) 2006-2016 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.designer.core.ui.preferences;

import org.talend.designer.core.model.process.jobsettings.JobSettingsConstants;

/**
 * This class is used for storing the same variable name of JAVA and Perl.
 * 
 * @author ftang <br/>
 * 
 */
public class StatsAndLogsConstants {

    public static final String ORACLE_WITH_SID_CONN_TYPE = "ORACLE_SID"; //$NON-NLS-1$

    public static final String ORACLE_WITH_SERVICE_CONN_TYPE = "ORACLE_SERVICE_NAME"; //$NON-NLS-1$

    public static final String ORACLE_OCI = "ORACLE_OCI"; //$NON-NLS-1$

    public static final String ORACLE_SID_ALIAS = JobSettingsConstants.ORACLE_OUTPUT_SID_ALIAS;

    public static final String ORACLE_SN_ALIAS = JobSettingsConstants.ORACLE_OUTPUT_SN_ALIAS;

    public static final String ORACLE_OCI_ALIAS = JobSettingsConstants.ORACLE_OUTPUT_OCI_ALIAS;

    private static final String MYSQL = "MYSQL"; //$NON-NLS-1$

    private static final String POSTGRESPLUS = "POSTGRESPLUS"; //$NON-NLS-1$

    private static final String POSTGRESQL = "POSTGRESQL"; //$NON-NLS-1$

    public static final String JDBC = "JDBC";

    private static final String IBM_DB2 = "IBM_DB2"; //$NON-NLS-1$

    private static final String SYBASE = "SYBASE"; //$NON-NLS-1$

    private static final String SQL_SERVER = "MSSQL"; //$NON-NLS-1$

    private static final String INGRES = "INGRES"; //$NON-NLS-1$

    private static final String INTERBASE = "INTERBASE"; //$NON-NLS-1$

    private static final String SQLITE = "SQLITE"; //$NON-NLS-1$

    private static final String FIREBIRD = "FIREBIRD"; //$NON-NLS-1$

    private static final String INFORMIX = "INFORMIX"; //$NON-NLS-1$

    private static final String ACCESS = "ACCESS"; //$NON-NLS-1$

    private static final String TERADATA = "TERADATA"; //$NON-NLS-1$

    public static final String JDBC_OUTPUT = "tJDBCOutput";

    public static final String[] DB_OUTPUT_COMPONENTS = new String[] {
            "tJDBCOutput", "tMysqlOutput", "tMSSqlOutput", ORACLE_WITH_SID_CONN_TYPE, ORACLE_WITH_SERVICE_CONN_TYPE, ORACLE_OCI,//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
            "tPostgresPlusOutput", "tPostgresqlOutput", "tDB2Output", "tSybaseOutput", "tIngresOutput", "tInterbaseOutput", "tSQLiteOutput", "tFirebirdOutput", "tInformixOutput", "tAccessOutput", "tTeradataOutput" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$

    private static final String[] PERL_DISPLAY_DBNAMES = new String[] {
            "MySQL", "Oracle with SID", "Oracle with service name", "Oracle OCI",//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
            "PostgresPlus", "PostgreSQL", "IBM DB2", "Sybase", "SQLite", "FireBird" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 

    private static final String[] JAVA_DISPLAY_DBNAMES = new String[] {
            "General JDBC", "MySQL", "Microsoft SQL Server", "Oracle with SID", "Oracle with service name", "Oracle OCI",//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$//$NON-NLS-6$
            "PostgresPlus", "PostgreSQL", "IBM DB2", "Sybase", "Ingres", "Interbase", "SQLite", "FireBird", "Informix", "Access", "Teradata" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$

    private static final String[] PERL_DB_COMPONENTS = new String[] {
            "tMysqlOutput", ORACLE_SID_ALIAS, ORACLE_SN_ALIAS, ORACLE_OCI_ALIAS,//$NON-NLS-1$ 
            "tPostgresPlusOutput", "tPostgresqlOutput", "tDB2Output", "tSybaseOutput", "tSQLiteOutput", "tFirebirdOutput" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ 

    private static final String[] JAVA_DB_COMPONENTS = new String[] {
            "tJDBCOutput", "tMysqlOutput", "tMSSqlOutput", ORACLE_SID_ALIAS, ORACLE_SN_ALIAS, ORACLE_OCI_ALIAS,//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
            "tPostgresPlusOutput", "tPostgresqlOutput", "tDB2Output", "tSybaseOutput", "tIngresOutput", "tInterbaseOutput", "tSQLiteOutput", "tFirebirdOutput", "tInformixOutput", "tAccessOutput", "tTeradataOutput" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$

    private static final String[] PERL_REPOSITORY_ITEMS = new String[] { MYSQL, ORACLE_WITH_SID_CONN_TYPE,
            ORACLE_WITH_SERVICE_CONN_TYPE, ORACLE_OCI, POSTGRESPLUS, POSTGRESQL, IBM_DB2, SYBASE, SQLITE, FIREBIRD };

    private static final String[] PERL_CODE_LIST = new String[] { MYSQL, "OCLE", "OCLE", "OCLE_OCI", POSTGRESPLUS, POSTGRESQL, //$NON-NLS-1$ //$NON-NLS-2$
            IBM_DB2, SYBASE, SQLITE, FIREBIRD };

    private static final String[] JAVA_REPOSITORY_ITEMS = new String[] { JDBC, MYSQL, SQL_SERVER, ORACLE_WITH_SID_CONN_TYPE,
            ORACLE_WITH_SERVICE_CONN_TYPE, ORACLE_OCI, POSTGRESPLUS, POSTGRESQL, IBM_DB2, SYBASE, INGRES, INTERBASE, SQLITE,
            FIREBIRD, INFORMIX, ACCESS, TERADATA };

    private static final String[] JAVA_CODE_LIST = new String[] { JDBC, MYSQL, SQL_SERVER,
            "OCLE", "OCLE", "OCLE_OCI", POSTGRESPLUS, POSTGRESQL, //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
            IBM_DB2, SYBASE, INGRES, INTERBASE, SQLITE, FIREBIRD, INFORMIX, ACCESS, TERADATA };

    public static final String[] ORACLE_VERSION_DISPLAY = new String[] {
            "Oracle 12", "Oracle 11", "Oracle 10", "Oracle 9", "Oracle 8" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    public static final String[] ORACLE_VERSION_CODE = new String[] {
            "ORACLE_12", "ORACLE_11", "ORACLE_10", "ORACLE_9", "ORACLE_8" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    public static final String[] ORACLE_VERSION_DRIVER = new String[] {
            "ojdbc7.jar", "ojdbc6.jar", "ojdbc14.jar", "ojdbc14-9i.jar", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            "ojdbc12.jar" }; //$NON-NLS-1$

    // for bug 11487
    public static final String[] MYSQL_VERSION_DISPLAY = new String[] { "MySQL 5", "MySQL 4", "MariaDB" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

    public static final String[] MYSQL_VERSION_CODE = new String[] { "MYSQL_5", "MYSQL_4", "MARIADB" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

    public static final String[] MYSQL_VERSION_DRIVER = new String[] {
            "mysql-connector-java-5.1.30-bin.jar", "mysql-connector-java-3.1.14-bin.jar", "mariadb-java-client-1.1.7.jar" };//$NON-NLS-1$  //$NON-NLS-2$//$NON-NLS-3$ 

    public static final String[] DB_VERSION_DISPLAY = new String[] { "Oracle 12", "Oracle 11", "Oracle 10", "Oracle 9",
            "Oracle 8", "MySQL 5", "MySQL 4", "MariaDB", "Access 2003", "Access 2007", "Prior to v9", "v9 +" };

    public static final String[] DB_VERSION_CODE = new String[] { "ORACLE_12", "ORACLE_11", "ORACLE_10", "ORACLE_9", "ORACLE_8",
            "MYSQL_5", "MYSQL_4", "MariaDB", "ACCESS_2003", "ACCESS_2007", "PRIOR_TO_V9", "V9_X" };

    public static final String[] DB_VERSION_DRIVER = new String[] { "ORACLE_12", "ORACLE_11", "ORACLE_10", "ORACLE_9",
            "ORACLE_8", "MYSQL_5", "MYSQL_4", "MARIADB", "ACCESS_2003", "ACCESS_2007", "PRIOR_TO_V9", "V9_X" };

    public static final String[] ACCESS_VERSION_DISPLAY = new String[] { "Access 2003", "Access 2007" }; //$NON-NLS-1$ //$NON-NLS-2$

    public static final String[] ACCESS_VERSION_CODE = new String[] { "Access_2003", "Access_2007" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public static final String[] ACCESS_VERSION_DRIVER = new String[] { "ACCESS_2003", "ACCESS_2007" }; //$NON-NLS-1$ 

    public static final String[] AS400_VERSION_DISPLAY = new String[] { "V6R1 to V7R2", "V5R3 to V6R1", "V5R2 to V5R4" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    public static final String[] AS400_VERSION_CODE = new String[] { "AS400_V6R1_V7R2", "AS400_V5R3_V6R1", "AS400_V5R2_V5R4" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 

    public static final String[] AS400_VERSION_DRIVER = new String[] { "jt400_V6R1.jar", "jt400_V5R3.jar", "jt400_V5R2.jar" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    public static final String[] PSQL_VERSION_DISPLAY = new String[] { "Prior to v9", "v9 +" }; //$NON-NLS-1$ //$NON-NLS-2$

    public static final String[] PSQL_VERSION_CODE = new String[] { "PRIOR_TO_V9", "V9_X" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public static final String[] PSQL_VERSION_DRIVER = new String[] {
            "postgresql-8.3-603.jdbc3.jar", "postgresql-9.4-1201.jdbc41.jar" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public static final String[][] DISPLAY_DBNAMES = new String[][] { PERL_DISPLAY_DBNAMES, JAVA_DISPLAY_DBNAMES };

    public static final String[][] DB_COMPONENTS = new String[][] { PERL_DB_COMPONENTS, JAVA_DB_COMPONENTS };

    public static final String[][] REPOSITORY_ITEMS = new String[][] { PERL_REPOSITORY_ITEMS, JAVA_REPOSITORY_ITEMS };

    public static final String[][] CODE_LIST = new String[][] { PERL_CODE_LIST, JAVA_CODE_LIST };

}
