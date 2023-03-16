package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.Policy;
import com.cloud.matrix.core.service.account.PolicyService;
import com.cloud.matrix.dal.dao.account.PolicyDAO;
import com.cloud.matrix.dal.model.account.PolicyDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    @Resource
    private PolicyDAO policyDAO;

    @Override
    public long add(Policy request) {
        return policyDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public Policy getByName(String name, String providerUid) {
        return Convertor.INSTANCE.convert2Model(
            policyDAO.selectByName(name, providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<Policy> getByProviderUid(String providerUid) {
        List<PolicyDO> dos = policyDAO.selectByProviderUid(providerUid, CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
