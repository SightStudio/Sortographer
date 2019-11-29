package com.common.config.jpa.queryDSL.dialect;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomMysqlDialect extends MySQL5Dialect {
    public CustomMysqlDialect() {
        super();
        this.registerFunction("group_concat", new SQLFunctionTemplate(StandardBasicTypes.STRING, "group_concat(?1)"));
    }
}