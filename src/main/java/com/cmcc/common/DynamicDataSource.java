package com.cmcc.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * override determineCurrentLookupKey
     * Description: �Զ�����datasource
     */
    @Override
    protected Object determineCurrentLookupKey() {
        //���Զ����λ�û�ȡ����Դ��ʶ
        return DynamicDataSourceHolder.getDataSource();
    }
}