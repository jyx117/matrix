package com.cloud.matrix.core.service.account.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.cloud.matrix.core.model.account.RolePolicy;
import com.cloud.matrix.core.service.account.RolePolicyService;
import com.cloud.matrix.dal.dao.account.RolePolicyDAO;
import com.cloud.matrix.dal.model.account.RolePolicyDO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $Id: AccountConfigServiceImpl.java, v 0.1 2023-03-16 5:36 PM Michael Exp $$
 */
@Service
public class RolePolicyServiceImpl implements RolePolicyService {

    @Resource
    private RolePolicyDAO rolePolicyDAO;

    @Override
    public long add(RolePolicy request) {
        return rolePolicyDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }

    @Override
    public RolePolicy getUnique(String roleName, String policyName, String providerUid) {
        return Convertor.INSTANCE.convert2Model(rolePolicyDAO.selectUnique(roleName, policyName,
            providerUid, CoreContext.getTenant()));
    }

    @Override
    public List<RolePolicy> getByRoleName(String roleName, String providerUid) {
        List<RolePolicyDO> dos = rolePolicyDAO.selectByRoleName(roleName, providerUid,
                CoreContext.getTenant());
        return null == dos || dos.size() < 1 ? null : dos.stream().map(item -> {
            return Convertor.INSTANCE.convert2Model(item);
        }).collect(Collectors.toList());
    }
}
