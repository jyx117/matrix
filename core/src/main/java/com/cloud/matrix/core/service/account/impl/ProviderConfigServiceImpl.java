package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.ProviderConfig;
import com.cloud.matrix.core.service.account.ProviderConfigService;
import com.cloud.matrix.dal.dao.account.ProviderConfigDAO;
import com.cloud.matrix.dal.model.account.ProviderConfigDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class ProviderConfigServiceImpl implements ProviderConfigService {

    @Resource
    private ProviderConfigDAO providerConfigDAO;

    @Override
    public long add(ProviderConfig request) {
        return providerConfigDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public ProviderConfig getUnique(String providerUid, String k) {
        return Convertor.INSTANCE
            .convert2Model(providerConfigDAO.selectUnique(providerUid, k, CoreContext.getTenant()));
    }

    @Override
    public List<ProviderConfig> getByProviderUid(String providerUid) {
        List<ProviderConfigDO> dos = providerConfigDAO.selectByProviderUid(providerUid,
            CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
