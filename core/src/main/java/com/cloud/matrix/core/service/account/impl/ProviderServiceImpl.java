package com.cloud.matrix.core.service.account.impl;

import com.cloud.matrix.core.model.account.Provider;
import com.cloud.matrix.core.service.account.ProviderService;
import com.cloud.matrix.dal.dao.account.ProviderDAO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class ProviderServiceImpl implements ProviderService {

    @Resource
    private ProviderDAO providerDAO;

    @Override
    public long add(Provider request) {
        return providerDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public Provider getUnique(String uid) {
        return Convertor.INSTANCE
            .convert2Model(providerDAO.selectUnique(uid, CoreContext.getTenant()));
    }
}
