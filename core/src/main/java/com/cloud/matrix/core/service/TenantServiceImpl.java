package com.cloud.matrix.core.service;

import com.cloud.matrix.core.model.Convertor;
import com.cloud.matrix.core.model.Tenant;
import com.cloud.matrix.dal.dao.TenantDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: TenantServiceImpl.java, v 0.1 2023-03-02 4:30 PM Michael Exp $$
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Resource
    private TenantDAO tenantDAO;

    @Override
    public Tenant getByCode(String code) {
        return Convertor.INSTANCE.convert2Model(tenantDAO.selectByCode(code));
    }

    @Override
    public long add(Tenant request) {
        if (null == request) {
            return -1;
        }
        return tenantDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }
}
